package androidx.media3.effect;

import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.effect.GlShaderProgram;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executor;

public class PassthroughShaderProgram implements GlShaderProgram {
    private GlShaderProgram.ErrorListener errorListener = new PassthroughShaderProgram$$ExternalSyntheticLambda0();
    private Executor errorListenerExecutor = MoreExecutors.directExecutor();
    private GlShaderProgram.InputListener inputListener = new GlShaderProgram.InputListener() {
    };
    private GlShaderProgram.OutputListener outputListener = new GlShaderProgram.OutputListener() {
    };
    private int texIdInUse = -1;

    static /* synthetic */ void lambda$new$0(VideoFrameProcessingException videoFrameProcessingException) {
    }

    public void setInputListener(GlShaderProgram.InputListener inputListener2) {
        this.inputListener = inputListener2;
        if (this.texIdInUse == -1) {
            inputListener2.onReadyToAcceptInputFrame();
        }
    }

    public void setOutputListener(GlShaderProgram.OutputListener outputListener2) {
        this.outputListener = outputListener2;
    }

    public void setErrorListener(Executor executor, GlShaderProgram.ErrorListener errorListener2) {
        this.errorListenerExecutor = executor;
        this.errorListener = errorListener2;
    }

    public void queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j) {
        this.texIdInUse = glTextureInfo.texId;
        this.outputListener.onOutputFrameAvailable(glTextureInfo, j);
    }

    public void releaseOutputFrame(GlTextureInfo glTextureInfo) {
        Assertions.checkState(glTextureInfo.texId == this.texIdInUse);
        this.texIdInUse = -1;
        this.inputListener.onInputFrameProcessed(glTextureInfo);
        this.inputListener.onReadyToAcceptInputFrame();
    }

    public void signalEndOfCurrentInputStream() {
        this.outputListener.onCurrentOutputStreamEnded();
    }

    public void flush() {
        this.texIdInUse = -1;
        this.inputListener.onFlush();
        this.inputListener.onReadyToAcceptInputFrame();
    }

    public void release() throws VideoFrameProcessingException {
        this.texIdInUse = -1;
    }

    /* access modifiers changed from: protected */
    public final GlShaderProgram.InputListener getInputListener() {
        return this.inputListener;
    }

    /* access modifiers changed from: protected */
    public final void onError(Exception exc) {
        this.errorListenerExecutor.execute(new PassthroughShaderProgram$$ExternalSyntheticLambda1(this, exc));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onError$1$androidx-media3-effect-PassthroughShaderProgram  reason: not valid java name */
    public /* synthetic */ void m450lambda$onError$1$androidxmedia3effectPassthroughShaderProgram(Exception exc) {
        this.errorListener.onError(VideoFrameProcessingException.from(exc));
    }
}
