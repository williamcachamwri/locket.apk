package androidx.media3.effect;

import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import androidx.media3.effect.GlShaderProgram;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executor;

public abstract class BaseGlShaderProgram implements GlShaderProgram {
    private GlShaderProgram.ErrorListener errorListener = new BaseGlShaderProgram$$ExternalSyntheticLambda2();
    private Executor errorListenerExecutor = MoreExecutors.directExecutor();
    private int inputHeight = -1;
    private GlShaderProgram.InputListener inputListener = new GlShaderProgram.InputListener() {
    };
    private int inputWidth = -1;
    private GlShaderProgram.OutputListener outputListener = new GlShaderProgram.OutputListener() {
    };
    protected final TexturePool outputTexturePool;

    static /* synthetic */ void lambda$new$0(VideoFrameProcessingException videoFrameProcessingException) {
    }

    public abstract Size configure(int i, int i2) throws VideoFrameProcessingException;

    public abstract void drawFrame(int i, long j) throws VideoFrameProcessingException;

    public boolean shouldClearTextureBuffer() {
        return true;
    }

    public BaseGlShaderProgram(boolean z, int i) {
        this.outputTexturePool = new TexturePool(z, i);
    }

    public void setInputListener(GlShaderProgram.InputListener inputListener2) {
        this.inputListener = inputListener2;
        for (int i = 0; i < this.outputTexturePool.freeTextureCount(); i++) {
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
        try {
            if (!(this.inputWidth == glTextureInfo.width && this.inputHeight == glTextureInfo.height && this.outputTexturePool.isConfigured())) {
                this.inputWidth = glTextureInfo.width;
                this.inputHeight = glTextureInfo.height;
                Size configure = configure(glTextureInfo.width, glTextureInfo.height);
                this.outputTexturePool.ensureConfigured(glObjectsProvider, configure.getWidth(), configure.getHeight());
            }
            GlTextureInfo useTexture = this.outputTexturePool.useTexture();
            GlUtil.focusFramebufferUsingCurrentContext(useTexture.fboId, useTexture.width, useTexture.height);
            if (shouldClearTextureBuffer()) {
                GlUtil.clearFocusedBuffers();
            }
            drawFrame(glTextureInfo.texId, j);
            this.inputListener.onInputFrameProcessed(glTextureInfo);
            this.outputListener.onOutputFrameAvailable(useTexture, j);
        } catch (VideoFrameProcessingException | GlUtil.GlException e) {
            this.errorListenerExecutor.execute(new BaseGlShaderProgram$$ExternalSyntheticLambda0(this, e));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$queueInputFrame$1$androidx-media3-effect-BaseGlShaderProgram  reason: not valid java name */
    public /* synthetic */ void m408lambda$queueInputFrame$1$androidxmedia3effectBaseGlShaderProgram(Exception exc) {
        this.errorListener.onError(VideoFrameProcessingException.from(exc));
    }

    public void releaseOutputFrame(GlTextureInfo glTextureInfo) {
        if (this.outputTexturePool.isUsingTexture(glTextureInfo)) {
            this.outputTexturePool.freeTexture(glTextureInfo);
            this.inputListener.onReadyToAcceptInputFrame();
        }
    }

    public void signalEndOfCurrentInputStream() {
        this.outputListener.onCurrentOutputStreamEnded();
    }

    public void flush() {
        this.outputTexturePool.freeAllTextures();
        this.inputListener.onFlush();
        for (int i = 0; i < this.outputTexturePool.capacity(); i++) {
            this.inputListener.onReadyToAcceptInputFrame();
        }
    }

    public void release() throws VideoFrameProcessingException {
        try {
            this.outputTexturePool.deleteAllTextures();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public final GlShaderProgram.InputListener getInputListener() {
        return this.inputListener;
    }

    /* access modifiers changed from: protected */
    public final GlShaderProgram.OutputListener getOutputListener() {
        return this.outputListener;
    }

    /* access modifiers changed from: protected */
    public final void onError(Exception exc) {
        this.errorListenerExecutor.execute(new BaseGlShaderProgram$$ExternalSyntheticLambda1(this, exc));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onError$2$androidx-media3-effect-BaseGlShaderProgram  reason: not valid java name */
    public /* synthetic */ void m407lambda$onError$2$androidxmedia3effectBaseGlShaderProgram(Exception exc) {
        this.errorListener.onError(VideoFrameProcessingException.from(exc));
    }
}
