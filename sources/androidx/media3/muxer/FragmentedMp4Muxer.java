package androidx.media3.muxer;

import android.media.MediaCodec;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.muxer.Muxer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class FragmentedMp4Muxer implements Muxer {
    public static final long DEFAULT_FRAGMENT_DURATION_MS = 2000;
    private final FragmentedMp4Writer fragmentedMp4Writer;
    private final MetadataCollector metadataCollector;

    public static final class Builder {
        private final FileOutputStream fileOutputStream;
        private long fragmentDurationMs = 2000;
        private boolean sampleCopyEnabled = true;

        public Builder(FileOutputStream fileOutputStream2) {
            this.fileOutputStream = fileOutputStream2;
        }

        public Builder setFragmentDurationMs(long j) {
            this.fragmentDurationMs = j;
            return this;
        }

        public Builder setSampleCopyEnabled(boolean z) {
            this.sampleCopyEnabled = z;
            return this;
        }

        public FragmentedMp4Muxer build() {
            return new FragmentedMp4Muxer(this.fileOutputStream, this.fragmentDurationMs, this.sampleCopyEnabled);
        }
    }

    private FragmentedMp4Muxer(FileOutputStream fileOutputStream, long j, boolean z) {
        Assertions.checkNotNull(fileOutputStream);
        MetadataCollector metadataCollector2 = new MetadataCollector();
        this.metadataCollector = metadataCollector2;
        this.fragmentedMp4Writer = new FragmentedMp4Writer(fileOutputStream, metadataCollector2, AnnexBToAvccConverter.DEFAULT, j, z);
    }

    public Muxer.TrackToken addTrack(Format format) {
        return this.fragmentedMp4Writer.addTrack(1, format);
    }

    public void writeSampleData(Muxer.TrackToken trackToken, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws Muxer.MuxerException {
        try {
            this.fragmentedMp4Writer.writeSampleData(trackToken, byteBuffer, bufferInfo);
        } catch (IOException e) {
            throw new Muxer.MuxerException("Failed to write sample for presentationTimeUs=" + bufferInfo.presentationTimeUs + ", size=" + bufferInfo.size, e);
        }
    }

    public void addMetadataEntry(Metadata.Entry entry) {
        Assertions.checkArgument(MuxerUtil.isMetadataSupported(entry), "Unsupported metadata");
        this.metadataCollector.addMetadata(entry);
    }

    public void close() throws Muxer.MuxerException {
        try {
            this.fragmentedMp4Writer.close();
        } catch (IOException e) {
            throw new Muxer.MuxerException("Failed to close the muxer", e);
        }
    }
}
