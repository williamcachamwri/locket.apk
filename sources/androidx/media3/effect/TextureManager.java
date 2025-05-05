package androidx.media3.effect;

import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.effect.GlShaderProgram;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

abstract class TextureManager implements GlShaderProgram.InputListener {
    private final Object lock = new Object();
    private VideoFrameProcessingTaskExecutor.Task onFlushCompleteTask;
    protected final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;

    public void dropIncomingRegisteredFrames() {
    }

    public abstract int getPendingFrameCount();

    public abstract void release() throws VideoFrameProcessingException;

    public void releaseAllRegisteredFrames() {
    }

    public void setInputFrameInfo(FrameInfo frameInfo, boolean z) {
    }

    public abstract void setSamplingGlShaderProgram(GlShaderProgram glShaderProgram);

    public abstract void signalEndOfCurrentInputStream();

    public TextureManager(VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2) {
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
    }

    public void setDefaultBufferSize(int i, int i2) {
        throw new UnsupportedOperationException();
    }

    public void queueInputBitmap(Bitmap bitmap, FrameInfo frameInfo, TimestampIterator timestampIterator) {
        throw new UnsupportedOperationException();
    }

    public void queueInputTexture(int i, long j) {
        throw new UnsupportedOperationException();
    }

    public void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener) {
        throw new UnsupportedOperationException();
    }

    public Surface getInputSurface() {
        throw new UnsupportedOperationException();
    }

    public void registerInputFrame(FrameInfo frameInfo) {
        throw new UnsupportedOperationException();
    }

    public final void setOnFlushCompleteListener(VideoFrameProcessingTaskExecutor.Task task) {
        synchronized (this.lock) {
            this.onFlushCompleteTask = task;
        }
    }

    public final void onFlush() {
        this.videoFrameProcessingTaskExecutor.submit(new TextureManager$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: protected */
    public void flush() throws VideoFrameProcessingException {
        synchronized (this.lock) {
            VideoFrameProcessingTaskExecutor.Task task = this.onFlushCompleteTask;
            if (task != null) {
                this.videoFrameProcessingTaskExecutor.submitWithHighPriority(task);
            }
        }
    }
}
