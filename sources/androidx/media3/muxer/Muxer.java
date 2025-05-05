package androidx.media3.muxer;

import android.media.MediaCodec;
import androidx.media3.common.Format;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.Metadata;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;

public interface Muxer {

    public interface Factory {
        Muxer create(String str) throws MuxerException;

        ImmutableList<String> getSupportedSampleMimeTypes(int i);
    }

    public interface TrackToken {
    }

    void addMetadataEntry(Metadata.Entry entry);

    TrackToken addTrack(Format format) throws MuxerException;

    void close() throws MuxerException;

    void writeSampleData(TrackToken trackToken, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws MuxerException;

    public static final class MuxerException extends Exception {
        static {
            MediaLibraryInfo.registerModule("media3.muxer");
        }

        public MuxerException(String str, Throwable th) {
            super(str, th);
        }
    }
}
