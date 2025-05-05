package androidx.media3.transformer;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Util;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.muxer.Muxer;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

final class FrameworkMuxer implements Muxer {
    public static final String MUXER_STOPPING_FAILED_ERROR_MESSAGE = "Failed to stop the MediaMuxer";
    /* access modifiers changed from: private */
    public static final ImmutableList<String> SUPPORTED_AUDIO_SAMPLE_MIME_TYPES = ImmutableList.of(MimeTypes.AUDIO_AAC, MimeTypes.AUDIO_AMR_NB, MimeTypes.AUDIO_AMR_WB);
    /* access modifiers changed from: private */
    public static final ImmutableList<String> SUPPORTED_VIDEO_SAMPLE_MIME_TYPES = getSupportedVideoSampleMimeTypes();
    private static final String TAG = "FrameworkMuxer";
    private boolean isReleased;
    private boolean isStarted;
    private final MediaMuxer mediaMuxer;
    private final Map<Muxer.TrackToken, Long> trackTokenToLastPresentationTimeUs;
    private final Map<Muxer.TrackToken, Long> trackTokenToPresentationTimeOffsetUs;
    private final long videoDurationUs;
    private Muxer.TrackToken videoTrackToken;

    public static final class Factory implements Muxer.Factory {
        private long videoDurationUs = C.TIME_UNSET;

        public Factory setVideoDurationUs(long j) {
            this.videoDurationUs = j;
            return this;
        }

        public FrameworkMuxer create(String str) throws Muxer.MuxerException {
            try {
                return new FrameworkMuxer(new MediaMuxer(str, 0), this.videoDurationUs);
            } catch (IOException e) {
                throw new Muxer.MuxerException("Error creating muxer", e);
            }
        }

        public ImmutableList<String> getSupportedSampleMimeTypes(int i) {
            if (i == 2) {
                return FrameworkMuxer.SUPPORTED_VIDEO_SAMPLE_MIME_TYPES;
            }
            if (i == 1) {
                return FrameworkMuxer.SUPPORTED_AUDIO_SAMPLE_MIME_TYPES;
            }
            return ImmutableList.of();
        }
    }

    private FrameworkMuxer(MediaMuxer mediaMuxer2, long j) {
        this.mediaMuxer = mediaMuxer2;
        this.videoDurationUs = j;
        this.trackTokenToLastPresentationTimeUs = new HashMap();
        this.trackTokenToPresentationTimeOffsetUs = new HashMap();
    }

    public Muxer.TrackToken addTrack(Format format) throws Muxer.MuxerException {
        MediaFormat mediaFormat;
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        boolean isVideo = MimeTypes.isVideo(str);
        if (isVideo) {
            mediaFormat = MediaFormat.createVideoFormat(str, format.width, format.height);
            MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
            try {
                this.mediaMuxer.setOrientationHint(format.rotationDegrees);
            } catch (RuntimeException e) {
                throw new Muxer.MuxerException("Failed to set orientation hint with rotationDegrees=" + format.rotationDegrees, e);
            }
        } else {
            mediaFormat = MediaFormat.createAudioFormat(str, format.sampleRate, format.channelCount);
            MediaFormatUtil.maybeSetString(mediaFormat, "language", format.language);
        }
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        try {
            TrackTokenImpl trackTokenImpl = new TrackTokenImpl(this.mediaMuxer.addTrack(mediaFormat));
            if (isVideo) {
                this.videoTrackToken = trackTokenImpl;
            }
            return trackTokenImpl;
        } catch (RuntimeException e2) {
            throw new Muxer.MuxerException("Failed to add track with format=" + format, e2);
        }
    }

    public void writeSampleData(Muxer.TrackToken trackToken, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws Muxer.MuxerException {
        Muxer.TrackToken trackToken2 = trackToken;
        MediaCodec.BufferInfo bufferInfo2 = bufferInfo;
        long j = bufferInfo2.presentationTimeUs;
        long j2 = this.videoDurationUs;
        if (j2 == C.TIME_UNSET || trackToken2 != this.videoTrackToken || j <= j2) {
            if (!this.isStarted) {
                if (Util.SDK_INT < 30 && j < 0) {
                    this.trackTokenToPresentationTimeOffsetUs.put(trackToken2, Long.valueOf(-j));
                }
                startMuxer();
            }
            long longValue = this.trackTokenToPresentationTimeOffsetUs.containsKey(trackToken2) ? this.trackTokenToPresentationTimeOffsetUs.get(trackToken2).longValue() : 0;
            long j3 = j + longValue;
            long longValue2 = this.trackTokenToLastPresentationTimeUs.containsKey(trackToken2) ? this.trackTokenToLastPresentationTimeUs.get(trackToken2).longValue() : 0;
            boolean z = false;
            Assertions.checkState(Util.SDK_INT > 24 || j3 >= longValue2, "Samples not in presentation order (" + j3 + " < " + longValue2 + ") unsupported on this API version");
            this.trackTokenToLastPresentationTimeUs.put(trackToken2, Long.valueOf(j3));
            if (longValue == 0 || j3 >= 0) {
                z = true;
            }
            Assertions.checkState(z, String.format(Locale.US, "Sample presentation time (%d) < first sample presentation time (%d). Ensure the first sample has the smallest timestamp when using the negative PTS workaround.", new Object[]{Long.valueOf(j3 - longValue), Long.valueOf(-longValue)}));
            bufferInfo.set(bufferInfo2.offset, bufferInfo2.size, j3, bufferInfo2.flags);
            try {
                Assertions.checkState(trackToken2 instanceof TrackTokenImpl);
                this.mediaMuxer.writeSampleData(((TrackTokenImpl) trackToken2).trackIndex, byteBuffer, bufferInfo2);
            } catch (RuntimeException e) {
                throw new Muxer.MuxerException("Failed to write sample for presentationTimeUs=" + j3 + ", size=" + bufferInfo2.size, e);
            }
        } else {
            Log.w(TAG, String.format(Locale.US, "Skipped sample with presentation time (%d) > video duration (%d)", new Object[]{Long.valueOf(j), Long.valueOf(this.videoDurationUs)}));
        }
    }

    public void addMetadataEntry(Metadata.Entry entry) {
        if (entry instanceof Mp4LocationData) {
            Mp4LocationData mp4LocationData = (Mp4LocationData) entry;
            this.mediaMuxer.setLocation(mp4LocationData.latitude, mp4LocationData.longitude);
        }
    }

    public void close() throws Muxer.MuxerException {
        if (!this.isReleased) {
            if (!this.isStarted) {
                startMuxer();
            }
            if (!(this.videoDurationUs == C.TIME_UNSET || this.videoTrackToken == null)) {
                MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                bufferInfo.set(0, 0, this.videoDurationUs, TransformerUtil.getMediaCodecFlags(4));
                writeSampleData((Muxer.TrackToken) Assertions.checkNotNull(this.videoTrackToken), ByteBuffer.allocateDirect(0), bufferInfo);
            }
            this.isStarted = false;
            try {
                stopMuxer(this.mediaMuxer);
                this.mediaMuxer.release();
                this.isReleased = true;
            } catch (RuntimeException e) {
                throw new Muxer.MuxerException(MUXER_STOPPING_FAILED_ERROR_MESSAGE, e);
            } catch (Throwable th) {
                this.mediaMuxer.release();
                this.isReleased = true;
                throw th;
            }
        }
    }

    private void startMuxer() throws Muxer.MuxerException {
        try {
            this.mediaMuxer.start();
            this.isStarted = true;
        } catch (RuntimeException e) {
            throw new Muxer.MuxerException("Failed to start the muxer", e);
        }
    }

    private static void stopMuxer(MediaMuxer mediaMuxer2) {
        try {
            mediaMuxer2.stop();
        } catch (RuntimeException e) {
            if (Util.SDK_INT < 30) {
                try {
                    Field declaredField = MediaMuxer.class.getDeclaredField("MUXER_STATE_STOPPED");
                    declaredField.setAccessible(true);
                    int intValue = ((Integer) Util.castNonNull((Integer) declaredField.get(mediaMuxer2))).intValue();
                    Field declaredField2 = MediaMuxer.class.getDeclaredField("mState");
                    declaredField2.setAccessible(true);
                    declaredField2.set(mediaMuxer2, Integer.valueOf(intValue));
                } catch (Exception unused) {
                }
            }
            throw e;
        }
    }

    private static ImmutableList<String> getSupportedVideoSampleMimeTypes() {
        ImmutableList.Builder add = new ImmutableList.Builder().add((Object[]) new String[]{MimeTypes.VIDEO_H264, MimeTypes.VIDEO_H263, MimeTypes.VIDEO_MP4V});
        if (Util.SDK_INT >= 24) {
            add.add((Object) MimeTypes.VIDEO_H265);
        }
        if (Util.SDK_INT >= 34) {
            add.add((Object) MimeTypes.VIDEO_AV1);
        }
        return add.build();
    }

    private static class TrackTokenImpl implements Muxer.TrackToken {
        public final int trackIndex;

        public TrackTokenImpl(int i) {
            this.trackIndex = i;
        }
    }
}
