package androidx.media3.muxer;

import android.media.MediaCodec;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.muxer.Muxer;
import com.google.common.io.ByteStreams;
import io.sentry.instrumentation.file.SentryFileInputStream;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public final class Mp4Muxer implements Muxer {
    public static final int FILE_FORMAT_DEFAULT = 0;
    public static final int FILE_FORMAT_EDITABLE_VIDEO = 1;
    public static final int LAST_SAMPLE_DURATION_BEHAVIOR_SET_FROM_END_OF_STREAM_BUFFER_OR_DUPLICATE_PREVIOUS = 1;
    public static final int LAST_SAMPLE_DURATION_BEHAVIOR_SET_TO_ZERO = 0;
    private static final String TAG = "Mp4Muxer";
    private final AnnexBToAvccConverter annexBToAvccConverter;
    private final boolean attemptStreamableOutputEnabled;
    private FileOutputStream cacheFileOutputStream;
    private String cacheFilePath;
    private MetadataCollector editableVideoMetadataCollector;
    private Mp4Writer editableVideoMp4Writer;
    private final EditableVideoParameters editableVideoParameters;
    private final List<Track> editableVideoTracks;
    private final int lastSampleDurationBehavior;
    private final MetadataCollector metadataCollector;
    private final Mp4Writer mp4Writer;
    private final FileChannel outputChannel;
    private final int outputFileFormat;
    private final FileOutputStream outputStream;
    private final boolean sampleBatchingEnabled;
    private final boolean sampleCopyEnabled;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FileFormat {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LastSampleDurationBehavior {
    }

    public static final class EditableVideoParameters {
        public final CacheFileProvider cacheFileProvider;
        public final boolean shouldInterleaveSamples;

        public interface CacheFileProvider {
            String getCacheFilePath();
        }

        public EditableVideoParameters(boolean z, CacheFileProvider cacheFileProvider2) {
            Assertions.checkArgument(z || cacheFileProvider2 != null);
            this.shouldInterleaveSamples = z;
            this.cacheFileProvider = cacheFileProvider2;
        }
    }

    public static final class Builder {
        private AnnexBToAvccConverter annexBToAvccConverter;
        private boolean attemptStreamableOutputEnabled = true;
        private EditableVideoParameters editableVideoParameters;
        private int lastSampleDurationBehavior = 1;
        private int outputFileFormat = 0;
        private final FileOutputStream outputStream;
        private boolean sampleBatchingEnabled = true;
        private boolean sampleCopyEnabled = true;

        public Builder(FileOutputStream fileOutputStream) {
            this.outputStream = fileOutputStream;
        }

        public Builder setLastSampleDurationBehavior(int i) {
            this.lastSampleDurationBehavior = i;
            return this;
        }

        public Builder setAnnexBToAvccConverter(AnnexBToAvccConverter annexBToAvccConverter2) {
            this.annexBToAvccConverter = annexBToAvccConverter2;
            return this;
        }

        public Builder setSampleCopyEnabled(boolean z) {
            this.sampleCopyEnabled = z;
            return this;
        }

        public Builder setSampleBatchingEnabled(boolean z) {
            this.sampleBatchingEnabled = z;
            return this;
        }

        public Builder setAttemptStreamableOutputEnabled(boolean z) {
            this.attemptStreamableOutputEnabled = z;
            return this;
        }

        public Builder setOutputFileFormat(int i) {
            this.outputFileFormat = i;
            return this;
        }

        public Builder setEditableVideoParameters(EditableVideoParameters editableVideoParameters2) {
            this.editableVideoParameters = editableVideoParameters2;
            return this;
        }

        public Mp4Muxer build() {
            boolean z = false;
            if (this.outputFileFormat != 1 ? this.editableVideoParameters == null : this.editableVideoParameters != null) {
                z = true;
            }
            Assertions.checkArgument(z, "EditablevideoParameters must be set for FILE_FORMAT_EDITABLE_VIDEO");
            FileOutputStream fileOutputStream = this.outputStream;
            int i = this.lastSampleDurationBehavior;
            AnnexBToAvccConverter annexBToAvccConverter2 = this.annexBToAvccConverter;
            if (annexBToAvccConverter2 == null) {
                annexBToAvccConverter2 = AnnexBToAvccConverter.DEFAULT;
            }
            return new Mp4Muxer(fileOutputStream, i, annexBToAvccConverter2, this.sampleCopyEnabled, this.sampleBatchingEnabled, this.attemptStreamableOutputEnabled, this.outputFileFormat, this.editableVideoParameters);
        }
    }

    private Mp4Muxer(FileOutputStream fileOutputStream, int i, AnnexBToAvccConverter annexBToAvccConverter2, boolean z, boolean z2, boolean z3, int i2, EditableVideoParameters editableVideoParameters2) {
        this.outputStream = fileOutputStream;
        FileChannel channel = fileOutputStream.getChannel();
        this.outputChannel = channel;
        int i3 = i;
        this.lastSampleDurationBehavior = i3;
        AnnexBToAvccConverter annexBToAvccConverter3 = annexBToAvccConverter2;
        this.annexBToAvccConverter = annexBToAvccConverter3;
        boolean z4 = z;
        this.sampleCopyEnabled = z4;
        boolean z5 = z2;
        this.sampleBatchingEnabled = z5;
        boolean z6 = z3;
        this.attemptStreamableOutputEnabled = z6;
        this.outputFileFormat = i2;
        this.editableVideoParameters = editableVideoParameters2;
        MetadataCollector metadataCollector2 = new MetadataCollector();
        this.metadataCollector = metadataCollector2;
        this.mp4Writer = new Mp4Writer(channel, metadataCollector2, annexBToAvccConverter3, i3, z4, z5, z6);
        this.editableVideoTracks = new ArrayList();
    }

    public Muxer.TrackToken addTrack(Format format) throws Muxer.MuxerException {
        return addTrack(1, format);
    }

    public Muxer.TrackToken addTrack(int i, Format format) throws Muxer.MuxerException {
        if (this.outputFileFormat != 1 || !MuxerUtil.isEditableVideoTrack(format)) {
            return this.mp4Writer.addTrack(i, format);
        }
        if (((EditableVideoParameters) Assertions.checkNotNull(this.editableVideoParameters)).shouldInterleaveSamples) {
            return this.mp4Writer.addEditableVideoTrack(i, format);
        }
        try {
            ensureSetupForEditableVideoTracks();
            Track addTrack = this.editableVideoMp4Writer.addTrack(i, format);
            this.editableVideoTracks.add(addTrack);
            return addTrack;
        } catch (FileNotFoundException e) {
            throw new Muxer.MuxerException("Cache file not found", e);
        }
    }

    public void writeSampleData(Muxer.TrackToken trackToken, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws Muxer.MuxerException {
        Assertions.checkState(trackToken instanceof Track);
        Track track = (Track) trackToken;
        try {
            if (this.editableVideoTracks.contains(trackToken)) {
                ((Mp4Writer) Assertions.checkNotNull(this.editableVideoMp4Writer)).writeSampleData(track, byteBuffer, bufferInfo);
            } else {
                this.mp4Writer.writeSampleData(track, byteBuffer, bufferInfo);
            }
        } catch (IOException e) {
            throw new Muxer.MuxerException("Failed to write sample for presentationTimeUs=" + bufferInfo.presentationTimeUs + ", size=" + bufferInfo.size, e);
        }
    }

    public void addMetadataEntry(Metadata.Entry entry) {
        Assertions.checkArgument(MuxerUtil.isMetadataSupported(entry), "Unsupported metadata");
        this.metadataCollector.addMetadata(entry);
    }

    public void close() throws Muxer.MuxerException {
        Muxer.MuxerException muxerException;
        try {
            finishWritingEditableVideoTracks();
            finishWritingPrimaryVideoTracks();
            appendEditableVideoTracksDataToTheOutputFile();
            muxerException = null;
        } catch (IOException e) {
            muxerException = new Muxer.MuxerException("Failed to finish writing data", e);
        }
        try {
            this.outputStream.close();
        } catch (IOException e2) {
            if (muxerException == null) {
                muxerException = new Muxer.MuxerException("Failed to close output stream", e2);
            } else {
                Log.e(TAG, "Failed to close output stream", e2);
            }
        }
        FileOutputStream fileOutputStream = this.cacheFileOutputStream;
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e3) {
                if (muxerException == null) {
                    muxerException = new Muxer.MuxerException("Failed to close the cache file output stream", e3);
                } else {
                    Log.e(TAG, "Failed to close cache file output stream", e3);
                }
            }
        }
        if (muxerException != null) {
            throw muxerException;
        }
    }

    @EnsuresNonNull({"editableVideoMp4Writer"})
    private void ensureSetupForEditableVideoTracks() throws FileNotFoundException {
        if (this.editableVideoMp4Writer == null) {
            this.cacheFilePath = ((EditableVideoParameters.CacheFileProvider) Assertions.checkNotNull(((EditableVideoParameters) Assertions.checkNotNull(this.editableVideoParameters)).cacheFileProvider)).getCacheFilePath();
            String str = this.cacheFilePath;
            this.cacheFileOutputStream = SentryFileOutputStream.Factory.create(new FileOutputStream(str), str);
            this.editableVideoMetadataCollector = new MetadataCollector();
            this.editableVideoMp4Writer = new Mp4Writer(this.cacheFileOutputStream.getChannel(), (MetadataCollector) Assertions.checkNotNull(this.editableVideoMetadataCollector), this.annexBToAvccConverter, this.lastSampleDurationBehavior, this.sampleCopyEnabled, this.sampleBatchingEnabled, this.attemptStreamableOutputEnabled);
        }
    }

    private void finishWritingEditableVideoTracks() throws IOException {
        if (this.editableVideoMp4Writer != null) {
            MuxerUtil.populateEditableVideoTracksMetadata((MetadataCollector) Assertions.checkNotNull(this.editableVideoMetadataCollector), this.metadataCollector.timestampData, false, this.editableVideoTracks);
            ((Mp4Writer) Assertions.checkNotNull(this.editableVideoMp4Writer)).finishWritingSamplesAndFinalizeMoovBox();
        }
    }

    private void finishWritingPrimaryVideoTracks() throws IOException {
        MdtaMetadataEntry editableTracksOffsetMetadata = MuxerUtil.getEditableTracksOffsetMetadata(0);
        if (this.editableVideoMp4Writer != null) {
            this.metadataCollector.addMetadata(MuxerUtil.getEditableTracksLengthMetadata(((FileOutputStream) Assertions.checkNotNull(this.cacheFileOutputStream)).getChannel().size() + 16));
            this.metadataCollector.addMetadata(editableTracksOffsetMetadata);
        }
        this.mp4Writer.finishWritingSamplesAndFinalizeMoovBox();
        if (this.editableVideoMp4Writer != null) {
            long size = this.outputChannel.size();
            this.metadataCollector.removeMdtaMetadataEntry(editableTracksOffsetMetadata);
            this.metadataCollector.addMetadata(MuxerUtil.getEditableTracksOffsetMetadata(size));
            this.mp4Writer.finalizeMoovBox();
            Assertions.checkState(this.outputChannel.size() == size, "The editable tracks offset should remain the same");
        }
    }

    private void appendEditableVideoTracksDataToTheOutputFile() throws IOException {
        if (this.editableVideoMp4Writer != null) {
            FileChannel fileChannel = this.outputChannel;
            fileChannel.position(fileChannel.size());
            String str = (String) Assertions.checkNotNull(this.cacheFilePath);
            FileInputStream create = SentryFileInputStream.Factory.create(new FileInputStream(str), str);
            this.outputChannel.write(Boxes.getEdvdBoxHeader(create.getChannel().size()));
            ByteStreams.copy((InputStream) create, (OutputStream) this.outputStream);
            create.close();
        }
    }
}
