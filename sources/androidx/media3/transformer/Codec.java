package androidx.media3.transformer;

import android.media.MediaCodec;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.decoder.DecoderInputBuffer;
import java.nio.ByteBuffer;

public interface Codec {

    public interface DecoderFactory {
        Codec createForAudioDecoding(Format format) throws ExportException;

        Codec createForVideoDecoding(Format format, Surface surface, boolean z) throws ExportException;
    }

    public interface EncoderFactory {
        boolean audioNeedsEncoding() {
            return false;
        }

        Codec createForAudioEncoding(Format format) throws ExportException;

        Codec createForVideoEncoding(Format format) throws ExportException;

        boolean videoNeedsEncoding() {
            return false;
        }
    }

    Format getConfigurationFormat();

    Surface getInputSurface();

    int getMaxPendingFrameCount() {
        return 5;
    }

    String getName();

    ByteBuffer getOutputBuffer() throws ExportException;

    MediaCodec.BufferInfo getOutputBufferInfo() throws ExportException;

    Format getOutputFormat() throws ExportException;

    boolean isEnded();

    boolean maybeDequeueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExportException;

    void queueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExportException;

    void release();

    void releaseOutputBuffer(long j) throws ExportException;

    void releaseOutputBuffer(boolean z) throws ExportException;

    void signalEndOfInputStream() throws ExportException;
}
