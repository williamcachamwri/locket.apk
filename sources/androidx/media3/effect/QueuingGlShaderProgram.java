package androidx.media3.effect;

import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlRect;
import androidx.media3.common.util.GlUtil;
import androidx.media3.effect.GlShaderProgram;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

final class QueuingGlShaderProgram<T> implements GlShaderProgram {
    private static final long PROCESSING_TIMEOUT_MS = 500000;
    private final ConcurrentEffect<T> concurrentEffect;
    private GlShaderProgram.ErrorListener errorListener;
    private Executor errorListenerExecutor;
    private final Queue<TimedTextureInfo<T>> frameQueue;
    private int inputHeight;
    private GlShaderProgram.InputListener inputListener;
    private int inputWidth;
    private GlShaderProgram.OutputListener outputListener;
    private final TexturePool outputTexturePool;

    public interface ConcurrentEffect<T> {
        void finishProcessingAndBlend(GlTextureInfo glTextureInfo, long j, T t) throws VideoFrameProcessingException;

        void flush() throws VideoFrameProcessingException;

        Future<T> queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j);

        void release() throws VideoFrameProcessingException;

        void signalEndOfCurrentInputStream() throws VideoFrameProcessingException;
    }

    static /* synthetic */ void lambda$new$0(VideoFrameProcessingException videoFrameProcessingException) {
    }

    public QueuingGlShaderProgram(boolean z, int i, ConcurrentEffect<T> concurrentEffect2) {
        Assertions.checkArgument(i > 0);
        this.concurrentEffect = concurrentEffect2;
        this.frameQueue = new ArrayDeque(i);
        this.outputTexturePool = new TexturePool(z, i);
        this.inputListener = new GlShaderProgram.InputListener() {
        };
        this.outputListener = new GlShaderProgram.OutputListener() {
        };
        this.errorListener = new QueuingGlShaderProgram$$ExternalSyntheticLambda0();
        this.errorListenerExecutor = MoreExecutors.directExecutor();
        this.inputWidth = -1;
        this.inputHeight = -1;
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
                while (outputOneFrame()) {
                }
                this.inputWidth = glTextureInfo.width;
                int i = glTextureInfo.height;
                this.inputHeight = i;
                this.outputTexturePool.ensureConfigured(glObjectsProvider, this.inputWidth, i);
            }
            GlTextureInfo useTexture = this.outputTexturePool.useTexture();
            Assertions.checkState(glTextureInfo.fboId != -1);
            GlUtil.blitFrameBuffer(glTextureInfo.fboId, new GlRect(this.inputWidth, this.inputHeight), useTexture.fboId, new GlRect(this.inputWidth, this.inputHeight));
            this.frameQueue.add(new TimedTextureInfo(useTexture, j, this.concurrentEffect.queueInputFrame(glObjectsProvider, useTexture, j)));
            this.inputListener.onInputFrameProcessed(glTextureInfo);
            if (this.frameQueue.size() == this.outputTexturePool.capacity()) {
                Assertions.checkState(outputOneFrame());
            }
        } catch (GlUtil.GlException e) {
            onError(e);
        }
    }

    public void releaseOutputFrame(GlTextureInfo glTextureInfo) {
        if (this.outputTexturePool.isUsingTexture(glTextureInfo)) {
            this.outputTexturePool.freeTexture(glTextureInfo);
            this.inputListener.onReadyToAcceptInputFrame();
        }
    }

    public void signalEndOfCurrentInputStream() {
        try {
            this.concurrentEffect.signalEndOfCurrentInputStream();
        } catch (VideoFrameProcessingException e) {
            onError(e);
        }
        do {
        } while (outputOneFrame());
        this.outputListener.onCurrentOutputStreamEnded();
    }

    public void flush() {
        try {
            this.concurrentEffect.flush();
        } catch (VideoFrameProcessingException e) {
            onError(e);
        }
        cancelProcessingOfPendingFrames();
        this.outputTexturePool.freeAllTextures();
        this.inputListener.onFlush();
        for (int i = 0; i < this.outputTexturePool.capacity(); i++) {
            this.inputListener.onReadyToAcceptInputFrame();
        }
    }

    public void release() throws VideoFrameProcessingException {
        try {
            cancelProcessingOfPendingFrames();
            this.concurrentEffect.release();
            this.outputTexturePool.deleteAllTextures();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    private boolean outputOneFrame() {
        TimedTextureInfo poll = this.frameQueue.poll();
        if (poll == null) {
            return false;
        }
        try {
            T checked = Futures.getChecked(poll.task, VideoFrameProcessingException.class, PROCESSING_TIMEOUT_MS, TimeUnit.MILLISECONDS);
            GlUtil.focusFramebufferUsingCurrentContext(poll.textureInfo.fboId, poll.textureInfo.width, poll.textureInfo.height);
            this.concurrentEffect.finishProcessingAndBlend(poll.textureInfo, poll.presentationTimeUs, checked);
            this.outputListener.onOutputFrameAvailable(poll.textureInfo, poll.presentationTimeUs);
            return true;
        } catch (VideoFrameProcessingException | GlUtil.GlException e) {
            onError(e);
            return false;
        }
    }

    private void cancelProcessingOfPendingFrames() {
        while (true) {
            TimedTextureInfo poll = this.frameQueue.poll();
            if (poll != null) {
                poll.task.cancel(false);
            } else {
                return;
            }
        }
    }

    private void onError(Exception exc) {
        this.errorListenerExecutor.execute(new QueuingGlShaderProgram$$ExternalSyntheticLambda1(this, exc));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onError$1$androidx-media3-effect-QueuingGlShaderProgram  reason: not valid java name */
    public /* synthetic */ void m451lambda$onError$1$androidxmedia3effectQueuingGlShaderProgram(Exception exc) {
        this.errorListener.onError(VideoFrameProcessingException.from(exc));
    }

    private static class TimedTextureInfo<T> {
        final long presentationTimeUs;
        final Future<T> task;
        final GlTextureInfo textureInfo;

        TimedTextureInfo(GlTextureInfo glTextureInfo, long j, Future<T> future) {
            this.textureInfo = glTextureInfo;
            this.presentationTimeUs = j;
            this.task = future;
        }
    }
}
