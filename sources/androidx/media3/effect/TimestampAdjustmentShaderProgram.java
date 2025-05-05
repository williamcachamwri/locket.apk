package androidx.media3.effect;

import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.effect.GlShaderProgram;
import androidx.media3.effect.TimestampAdjustment;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TimestampAdjustmentShaderProgram implements GlShaderProgram {
    private GlShaderProgram.InputListener inputListener = new GlShaderProgram.InputListener() {
    };
    private GlTextureInfo inputTexture;
    private GlShaderProgram.OutputListener outputListener = new GlShaderProgram.OutputListener() {
    };
    private final AtomicInteger pendingCallbacksCount;
    private final AtomicBoolean pendingEndOfStream;
    private final TimestampAdjustment.TimestampMap timestampMap;

    public void setErrorListener(Executor executor, GlShaderProgram.ErrorListener errorListener) {
    }

    public TimestampAdjustmentShaderProgram(TimestampAdjustment.TimestampMap timestampMap2) {
        this.timestampMap = timestampMap2;
        this.pendingCallbacksCount = new AtomicInteger();
        this.pendingEndOfStream = new AtomicBoolean();
    }

    public void setInputListener(GlShaderProgram.InputListener inputListener2) {
        this.inputListener = inputListener2;
        if (this.inputTexture == null) {
            inputListener2.onReadyToAcceptInputFrame();
        }
    }

    public void setOutputListener(GlShaderProgram.OutputListener outputListener2) {
        this.outputListener = outputListener2;
    }

    public void queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j) {
        this.inputTexture = glTextureInfo;
        this.timestampMap.calculateOutputTimeUs(j, new TimestampAdjustmentShaderProgram$$ExternalSyntheticLambda0(this));
        this.pendingCallbacksCount.incrementAndGet();
    }

    public void signalEndOfCurrentInputStream() {
        if (this.pendingCallbacksCount.get() == 0) {
            this.outputListener.onCurrentOutputStreamEnded();
        } else {
            this.pendingEndOfStream.set(true);
        }
    }

    public void releaseOutputFrame(GlTextureInfo glTextureInfo) {
        Assertions.checkState(glTextureInfo.texId == ((GlTextureInfo) Assertions.checkNotNull(this.inputTexture)).texId);
        this.inputListener.onInputFrameProcessed(glTextureInfo);
        this.inputListener.onReadyToAcceptInputFrame();
    }

    public void flush() {
        throw new UnsupportedOperationException("This effect is not supported for previewing.");
    }

    public void release() throws VideoFrameProcessingException {
        this.inputTexture = null;
    }

    /* access modifiers changed from: private */
    public void onOutputTimeAvailable(long j) {
        this.outputListener.onOutputFrameAvailable((GlTextureInfo) Assertions.checkNotNull(this.inputTexture), j);
        if (this.pendingEndOfStream.get()) {
            this.outputListener.onCurrentOutputStreamEnded();
            this.pendingEndOfStream.set(false);
        }
        this.pendingCallbacksCount.decrementAndGet();
    }
}
