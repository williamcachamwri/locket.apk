package androidx.media3.effect;

import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;

final class SimpleFrameDroppingShaderProgram extends PassthroughShaderProgram {
    private int framesReceived;
    private final int n;

    public SimpleFrameDroppingShaderProgram(float f, float f2) {
        int round = Math.round(f / f2);
        this.n = round;
        Assertions.checkArgument(round < 1 ? false : true, "The input frame rate should be greater than the target frame rate.");
    }

    public void queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j) {
        if (this.framesReceived % this.n == 0) {
            super.queueInputFrame(glObjectsProvider, glTextureInfo, j);
        } else {
            getInputListener().onInputFrameProcessed(glTextureInfo);
            getInputListener().onReadyToAcceptInputFrame();
        }
        this.framesReceived++;
    }

    public void signalEndOfCurrentInputStream() {
        super.signalEndOfCurrentInputStream();
        this.framesReceived = 0;
    }

    public void flush() {
        super.flush();
        this.framesReceived = 0;
    }

    public void release() throws VideoFrameProcessingException {
        super.release();
        this.framesReceived = 0;
    }
}
