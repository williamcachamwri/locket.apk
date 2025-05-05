package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.C;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;

final class DefaultFrameDroppingShaderProgram extends FrameCacheGlShaderProgram {
    private int framesReceived;
    private long lastQueuedPresentationTimeUs = C.TIME_UNSET;
    private long previousPresentationTimeUs = C.TIME_UNSET;
    private GlTextureInfo previousTexture;
    private final long targetFrameDeltaUs;
    private final boolean useHdr;

    public DefaultFrameDroppingShaderProgram(Context context, boolean z, float f) throws VideoFrameProcessingException {
        super(context, 1, z);
        this.useHdr = z;
        this.targetFrameDeltaUs = (long) (1000000.0f / f);
    }

    public void queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j) {
        int i = this.framesReceived + 1;
        this.framesReceived = i;
        if (i == 1) {
            copyTextureToPreviousFrame(glObjectsProvider, glTextureInfo, j);
            queuePreviousFrame(glObjectsProvider);
            getInputListener().onInputFrameProcessed(glTextureInfo);
            getInputListener().onReadyToAcceptInputFrame();
            return;
        }
        if (shouldQueuePreviousFrame(j)) {
            queuePreviousFrame(glObjectsProvider);
        }
        copyTextureToPreviousFrame(glObjectsProvider, glTextureInfo, j);
        getInputListener().onInputFrameProcessed(glTextureInfo);
        if (this.outputTexturePool.freeTextureCount() > 0) {
            getInputListener().onReadyToAcceptInputFrame();
        }
    }

    public void signalEndOfCurrentInputStream() {
        super.signalEndOfCurrentInputStream();
        reset();
    }

    public void flush() {
        super.flush();
        reset();
    }

    public void release() throws VideoFrameProcessingException {
        super.release();
        try {
            GlTextureInfo glTextureInfo = this.previousTexture;
            if (glTextureInfo != null) {
                glTextureInfo.release();
            }
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    private void reset() {
        try {
            GlTextureInfo glTextureInfo = this.previousTexture;
            if (glTextureInfo != null) {
                glTextureInfo.release();
            }
        } catch (GlUtil.GlException e) {
            onError(e);
        }
        this.lastQueuedPresentationTimeUs = C.TIME_UNSET;
        this.previousPresentationTimeUs = C.TIME_UNSET;
        this.framesReceived = 0;
    }

    private void copyTextureToPreviousFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j) {
        try {
            if (this.previousTexture == null) {
                this.previousTexture = glObjectsProvider.createBuffersForTexture(GlUtil.createTexture(glTextureInfo.width, glTextureInfo.height, this.useHdr), glTextureInfo.width, glTextureInfo.height);
            }
            GlTextureInfo glTextureInfo2 = (GlTextureInfo) Assertions.checkNotNull(this.previousTexture);
            if (!(glTextureInfo2.height == glTextureInfo.height && glTextureInfo2.width == glTextureInfo.width)) {
                glTextureInfo2.release();
                glTextureInfo2 = glObjectsProvider.createBuffersForTexture(GlUtil.createTexture(glTextureInfo.width, glTextureInfo.height, this.useHdr), glTextureInfo.width, glTextureInfo.height);
            }
            GlUtil.focusFramebufferUsingCurrentContext(glTextureInfo2.fboId, glTextureInfo2.width, glTextureInfo2.height);
            GlUtil.clearFocusedBuffers();
            drawFrame(glTextureInfo.texId, j);
            this.previousPresentationTimeUs = j;
            this.previousTexture = glTextureInfo2;
        } catch (VideoFrameProcessingException | GlUtil.GlException e) {
            onError(e);
        }
    }

    private boolean shouldQueuePreviousFrame(long j) {
        if (this.framesReceived == 2) {
            return false;
        }
        long j2 = this.previousPresentationTimeUs;
        long j3 = this.lastQueuedPresentationTimeUs;
        if (Math.abs((j2 - j3) - this.targetFrameDeltaUs) < Math.abs((j - j3) - this.targetFrameDeltaUs)) {
            return true;
        }
        return false;
    }

    private void queuePreviousFrame(GlObjectsProvider glObjectsProvider) {
        try {
            GlTextureInfo glTextureInfo = (GlTextureInfo) Assertions.checkNotNull(this.previousTexture);
            Size configure = configure(glTextureInfo.width, glTextureInfo.height);
            this.outputTexturePool.ensureConfigured(glObjectsProvider, configure.getWidth(), configure.getHeight());
            GlTextureInfo useTexture = this.outputTexturePool.useTexture();
            GlUtil.focusFramebufferUsingCurrentContext(useTexture.fboId, useTexture.width, useTexture.height);
            GlUtil.clearFocusedBuffers();
            drawFrame(glTextureInfo.texId, this.previousPresentationTimeUs);
            getOutputListener().onOutputFrameAvailable(useTexture, this.previousPresentationTimeUs);
            this.lastQueuedPresentationTimeUs = this.previousPresentationTimeUs;
        } catch (VideoFrameProcessingException | GlUtil.GlException e) {
            onError(e);
        }
    }
}
