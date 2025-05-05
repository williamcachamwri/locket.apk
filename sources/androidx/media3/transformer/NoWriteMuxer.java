package androidx.media3.transformer;

import android.media.MediaCodec;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.muxer.Muxer;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;

final class NoWriteMuxer implements Muxer {
    public void addMetadataEntry(Metadata.Entry entry) {
    }

    public void close() {
    }

    public void writeSampleData(Muxer.TrackToken trackToken, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
    }

    NoWriteMuxer() {
    }

    public static final class Factory implements Muxer.Factory {
        private final ImmutableList<String> audioMimeTypes;
        private final ImmutableList<String> videoMimeTypes;

        public Factory(ImmutableList<String> immutableList, ImmutableList<String> immutableList2) {
            this.audioMimeTypes = immutableList;
            this.videoMimeTypes = immutableList2;
        }

        public Muxer create(String str) {
            return new NoWriteMuxer();
        }

        public ImmutableList<String> getSupportedSampleMimeTypes(int i) {
            if (i == 1) {
                return this.audioMimeTypes;
            }
            if (i == 2) {
                return this.videoMimeTypes;
            }
            return ImmutableList.of();
        }
    }

    public Muxer.TrackToken addTrack(Format format) {
        return new Muxer.TrackToken() {
        };
    }
}
