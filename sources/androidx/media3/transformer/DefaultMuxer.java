package androidx.media3.transformer;

import android.media.MediaCodec;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Util;
import androidx.media3.muxer.Muxer;
import androidx.media3.transformer.FrameworkMuxer;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;

public final class DefaultMuxer implements Muxer {
    private final Muxer muxer;

    public static final class Factory implements Muxer.Factory {
        private final FrameworkMuxer.Factory muxerFactory;

        public Factory() {
            this.muxerFactory = new FrameworkMuxer.Factory();
        }

        @Deprecated
        public Factory(long j) {
            this.muxerFactory = new FrameworkMuxer.Factory().setVideoDurationUs(Util.msToUs(j));
        }

        public Factory setVideoDurationUs(long j) {
            this.muxerFactory.setVideoDurationUs(j);
            return this;
        }

        public Muxer create(String str) throws Muxer.MuxerException {
            return new DefaultMuxer(this.muxerFactory.create(str));
        }

        public ImmutableList<String> getSupportedSampleMimeTypes(int i) {
            return this.muxerFactory.getSupportedSampleMimeTypes(i);
        }
    }

    private DefaultMuxer(Muxer muxer2) {
        this.muxer = muxer2;
    }

    public Muxer.TrackToken addTrack(Format format) throws Muxer.MuxerException {
        return this.muxer.addTrack(format);
    }

    public void writeSampleData(Muxer.TrackToken trackToken, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws Muxer.MuxerException {
        this.muxer.writeSampleData(trackToken, byteBuffer, bufferInfo);
    }

    public void addMetadataEntry(Metadata.Entry entry) {
        this.muxer.addMetadataEntry(entry);
    }

    public void close() throws Muxer.MuxerException {
        this.muxer.close();
    }
}
