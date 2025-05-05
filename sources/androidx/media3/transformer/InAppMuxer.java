package androidx.media3.transformer;

import android.media.MediaCodec;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.container.Mp4OrientationData;
import androidx.media3.muxer.FragmentedMp4Muxer;
import androidx.media3.muxer.Mp4Muxer;
import androidx.media3.muxer.Muxer;
import androidx.media3.muxer.MuxerUtil;
import com.google.common.collect.ImmutableList;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

public final class InAppMuxer implements Muxer {
    private static final String TAG = "InAppMuxer";
    private final Set<Metadata.Entry> metadataEntries;
    private final MetadataProvider metadataProvider;
    private final Muxer muxer;
    private final long videoDurationUs;
    private Muxer.TrackToken videoTrackToken;

    public interface MetadataProvider {
        void updateMetadataEntries(Set<Metadata.Entry> set);
    }

    public static final class Factory implements Muxer.Factory {
        private static final ImmutableList<String> SUPPORTED_AUDIO_SAMPLE_MIME_TYPES = ImmutableList.of(MimeTypes.AUDIO_AAC, MimeTypes.AUDIO_AMR_NB, MimeTypes.AUDIO_AMR_WB, MimeTypes.AUDIO_OPUS, MimeTypes.AUDIO_VORBIS);
        private static final ImmutableList<String> SUPPORTED_VIDEO_SAMPLE_MIME_TYPES = ImmutableList.of(MimeTypes.VIDEO_AV1, MimeTypes.VIDEO_H263, MimeTypes.VIDEO_H264, MimeTypes.VIDEO_H265, MimeTypes.VIDEO_MP4V);
        private final long fragmentDurationMs;
        private final MetadataProvider metadataProvider;
        private final boolean outputFragmentedMp4;
        private long videoDurationUs;

        public static final class Builder {
            private long fragmentDurationMs = C.TIME_UNSET;
            private MetadataProvider metadataProvider;
            private boolean outputFragmentedMp4;

            public Builder setMetadataProvider(MetadataProvider metadataProvider2) {
                this.metadataProvider = metadataProvider2;
                return this;
            }

            public Builder setOutputFragmentedMp4(boolean z) {
                this.outputFragmentedMp4 = z;
                return this;
            }

            public Builder setFragmentDurationMs(long j) {
                this.fragmentDurationMs = j;
                return this;
            }

            public Factory build() {
                return new Factory(this.metadataProvider, this.outputFragmentedMp4, this.fragmentDurationMs);
            }
        }

        private Factory(MetadataProvider metadataProvider2, boolean z, long j) {
            this.metadataProvider = metadataProvider2;
            this.outputFragmentedMp4 = z;
            this.fragmentDurationMs = j;
            this.videoDurationUs = C.TIME_UNSET;
        }

        public Factory setVideoDurationUs(long j) {
            this.videoDurationUs = j;
            return this;
        }

        public InAppMuxer create(String str) throws Muxer.MuxerException {
            Muxer muxer;
            try {
                FileOutputStream create = SentryFileOutputStream.Factory.create(new FileOutputStream(str), str);
                if (this.outputFragmentedMp4) {
                    FragmentedMp4Muxer.Builder builder = new FragmentedMp4Muxer.Builder(create);
                    long j = this.fragmentDurationMs;
                    if (j != C.TIME_UNSET) {
                        builder.setFragmentDurationMs(j);
                    }
                    muxer = builder.build();
                } else {
                    Mp4Muxer.Builder builder2 = new Mp4Muxer.Builder(create);
                    if (this.videoDurationUs != C.TIME_UNSET) {
                        builder2.setLastSampleDurationBehavior(1);
                    }
                    muxer = builder2.build();
                }
                return new InAppMuxer(muxer, this.metadataProvider, this.videoDurationUs);
            } catch (FileNotFoundException e) {
                throw new Muxer.MuxerException("Error creating file output stream", e);
            }
        }

        public ImmutableList<String> getSupportedSampleMimeTypes(int i) {
            if (i == 2) {
                return SUPPORTED_VIDEO_SAMPLE_MIME_TYPES;
            }
            if (i == 1) {
                return SUPPORTED_AUDIO_SAMPLE_MIME_TYPES;
            }
            return ImmutableList.of();
        }
    }

    private InAppMuxer(Muxer muxer2, MetadataProvider metadataProvider2, long j) {
        this.muxer = muxer2;
        this.metadataProvider = metadataProvider2;
        this.videoDurationUs = j;
        this.metadataEntries = new LinkedHashSet();
    }

    public Muxer.TrackToken addTrack(Format format) throws Muxer.MuxerException {
        Muxer.TrackToken addTrack = this.muxer.addTrack(format);
        if (MimeTypes.isVideo(format.sampleMimeType)) {
            this.muxer.addMetadataEntry(new Mp4OrientationData(format.rotationDegrees));
            this.videoTrackToken = addTrack;
        }
        return addTrack;
    }

    public void writeSampleData(Muxer.TrackToken trackToken, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws Muxer.MuxerException {
        if (this.videoDurationUs == C.TIME_UNSET || trackToken != this.videoTrackToken || bufferInfo.presentationTimeUs <= this.videoDurationUs) {
            this.muxer.writeSampleData(trackToken, byteBuffer, bufferInfo);
        } else {
            Log.w(TAG, String.format(Locale.US, "Skipped sample with presentation time (%d) > video duration (%d)", new Object[]{Long.valueOf(bufferInfo.presentationTimeUs), Long.valueOf(this.videoDurationUs)}));
        }
    }

    public void addMetadataEntry(Metadata.Entry entry) {
        if (MuxerUtil.isMetadataSupported(entry)) {
            this.metadataEntries.add(entry);
        }
    }

    public void close() throws Muxer.MuxerException {
        if (!(this.videoDurationUs == C.TIME_UNSET || this.videoTrackToken == null)) {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            bufferInfo.set(0, 0, this.videoDurationUs, 4);
            writeSampleData((Muxer.TrackToken) Assertions.checkNotNull(this.videoTrackToken), ByteBuffer.allocateDirect(0), bufferInfo);
        }
        writeMetadata();
        this.muxer.close();
    }

    private void writeMetadata() {
        if (this.metadataProvider != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(this.metadataEntries);
            this.metadataProvider.updateMetadataEntries(linkedHashSet);
            this.metadataEntries.clear();
            this.metadataEntries.addAll(linkedHashSet);
        }
        for (Metadata.Entry addMetadataEntry : this.metadataEntries) {
            this.muxer.addMetadataEntry(addMetadataEntry);
        }
    }
}
