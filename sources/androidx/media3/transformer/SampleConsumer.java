package androidx.media3.transformer;

import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.decoder.DecoderInputBuffer;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface SampleConsumer {
    public static final int INPUT_RESULT_END_OF_STREAM = 3;
    public static final int INPUT_RESULT_SUCCESS = 1;
    public static final int INPUT_RESULT_TRY_AGAIN_LATER = 2;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface InputResult {
    }

    DecoderInputBuffer getInputBuffer() {
        throw new UnsupportedOperationException();
    }

    boolean queueInputBuffer() {
        throw new UnsupportedOperationException();
    }

    int queueInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
        throw new UnsupportedOperationException();
    }

    void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener) {
        throw new UnsupportedOperationException();
    }

    void setOnInputSurfaceReadyListener(Runnable runnable) {
        throw new UnsupportedOperationException();
    }

    int queueInputTexture(int i, long j) {
        throw new UnsupportedOperationException();
    }

    Surface getInputSurface() {
        throw new UnsupportedOperationException();
    }

    int getPendingVideoFrameCount() {
        throw new UnsupportedOperationException();
    }

    boolean registerVideoFrame(long j) {
        throw new UnsupportedOperationException();
    }

    void signalEndOfVideoInput() {
        throw new UnsupportedOperationException();
    }
}
