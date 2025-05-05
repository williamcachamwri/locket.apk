package androidx.media3.effect;

import android.content.Context;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.SurfaceInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.LongArrayQueue;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.effect.GlShaderProgram;
import androidx.media3.effect.GlTextureProducer;
import androidx.media3.effect.ScaleAndRotateTransformation;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

final class FinalShaderProgramWrapper implements GlShaderProgram, GlTextureProducer {
    private static final int SURFACE_INPUT_CAPACITY = 1;
    private static final String TAG = "FinalShaderWrapper";
    private final Queue<Pair<GlTextureInfo, Long>> availableFrames;
    private final Context context;
    private SurfaceView debugSurfaceView;
    private SurfaceViewWrapper debugSurfaceViewWrapper;
    private final DebugViewProvider debugViewProvider;
    private DefaultShaderProgram defaultShaderProgram;
    private final EGLContext eglContext;
    private final EGLDisplay eglDisplay;
    private int inputHeight;
    private GlShaderProgram.InputListener inputListener;
    private int inputWidth;
    private boolean isInputStreamEndedWithPendingAvailableFrames;
    private final List<GlMatrixTransformation> matrixTransformations = new ArrayList();
    private boolean matrixTransformationsChanged;
    private OnInputStreamProcessedListener onInputStreamProcessedListener;
    private final ColorInfo outputColorInfo;
    private EGLSurface outputEglSurface;
    private Size outputSizeBeforeSurfaceTransformation;
    private SurfaceInfo outputSurfaceInfo;
    private boolean outputSurfaceInfoChanged;
    private final TexturePool outputTexturePool;
    private final LongArrayQueue outputTextureTimestamps;
    private final EGLSurface placeholderSurface;
    private final boolean renderFramesAutomatically;
    private final List<RgbMatrix> rgbMatrices = new ArrayList();
    private final int sdrWorkingColorSpace;
    private final LongArrayQueue syncObjects;
    private final GlTextureProducer.Listener textureOutputListener;
    private final VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor;
    private final VideoFrameProcessor.Listener videoFrameProcessorListener;
    private final Executor videoFrameProcessorListenerExecutor;

    interface OnInputStreamProcessedListener {
        void onInputStreamProcessed();
    }

    public FinalShaderProgramWrapper(Context context2, EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, DebugViewProvider debugViewProvider2, ColorInfo colorInfo, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor2, Executor executor, VideoFrameProcessor.Listener listener, GlTextureProducer.Listener listener2, int i, int i2, boolean z) {
        this.context = context2;
        this.eglDisplay = eGLDisplay;
        this.eglContext = eGLContext;
        this.placeholderSurface = eGLSurface;
        this.debugViewProvider = debugViewProvider2;
        this.outputColorInfo = colorInfo;
        this.videoFrameProcessingTaskExecutor = videoFrameProcessingTaskExecutor2;
        this.videoFrameProcessorListenerExecutor = executor;
        this.videoFrameProcessorListener = listener;
        this.textureOutputListener = listener2;
        this.sdrWorkingColorSpace = i2;
        this.renderFramesAutomatically = z;
        this.inputListener = new GlShaderProgram.InputListener() {
        };
        this.availableFrames = new ConcurrentLinkedQueue();
        this.outputTexturePool = new TexturePool(ColorInfo.isTransferHdr(colorInfo), i);
        this.outputTextureTimestamps = new LongArrayQueue(i);
        this.syncObjects = new LongArrayQueue(i);
    }

    public void releaseOutputTexture(long j) {
        this.videoFrameProcessingTaskExecutor.submit(new FinalShaderProgramWrapper$$ExternalSyntheticLambda5(this, j));
    }

    /* access modifiers changed from: private */
    /* renamed from: releaseOutputTextureInternal */
    public void m437lambda$releaseOutputTexture$0$androidxmedia3effectFinalShaderProgramWrapper(long j) throws GlUtil.GlException {
        Assertions.checkState(this.textureOutputListener != null);
        while (this.outputTexturePool.freeTextureCount() < this.outputTexturePool.capacity() && this.outputTextureTimestamps.element() <= j) {
            this.outputTexturePool.freeTexture();
            this.outputTextureTimestamps.remove();
            GlUtil.deleteSyncObject(this.syncObjects.remove());
            this.inputListener.onReadyToAcceptInputFrame();
        }
    }

    public void setInputListener(GlShaderProgram.InputListener inputListener2) {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        this.inputListener = inputListener2;
        for (int i = 0; i < getInputCapacity(); i++) {
            inputListener2.onReadyToAcceptInputFrame();
        }
    }

    public void setOutputListener(GlShaderProgram.OutputListener outputListener) {
        throw new UnsupportedOperationException();
    }

    public void setErrorListener(Executor executor, GlShaderProgram.ErrorListener errorListener) {
        throw new UnsupportedOperationException();
    }

    public void setOnInputStreamProcessedListener(OnInputStreamProcessedListener onInputStreamProcessedListener2) {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        this.onInputStreamProcessedListener = onInputStreamProcessedListener2;
    }

    public void signalEndOfCurrentInputStream() {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        if (this.availableFrames.isEmpty()) {
            ((OnInputStreamProcessedListener) Assertions.checkNotNull(this.onInputStreamProcessedListener)).onInputStreamProcessed();
            this.isInputStreamEndedWithPendingAvailableFrames = false;
            return;
        }
        Assertions.checkState(!this.renderFramesAutomatically);
        this.isInputStreamEndedWithPendingAvailableFrames = true;
    }

    public void queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j) {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        this.videoFrameProcessorListenerExecutor.execute(new FinalShaderProgramWrapper$$ExternalSyntheticLambda2(this, j));
        if (this.textureOutputListener == null) {
            if (this.renderFramesAutomatically) {
                renderFrame(glObjectsProvider, glTextureInfo, j, j * 1000);
            } else {
                this.availableFrames.add(Pair.create(glTextureInfo, Long.valueOf(j)));
            }
            this.inputListener.onReadyToAcceptInputFrame();
            return;
        }
        Assertions.checkState(this.outputTexturePool.freeTextureCount() > 0);
        renderFrame(glObjectsProvider, glTextureInfo, j, j * 1000);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$queueInputFrame$1$androidx-media3-effect-FinalShaderProgramWrapper  reason: not valid java name */
    public /* synthetic */ void m436lambda$queueInputFrame$1$androidxmedia3effectFinalShaderProgramWrapper(long j) {
        this.videoFrameProcessorListener.onOutputFrameAvailableForRendering(j);
    }

    public void releaseOutputFrame(GlTextureInfo glTextureInfo) {
        throw new UnsupportedOperationException();
    }

    public void flush() {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        if (this.textureOutputListener != null) {
            this.outputTexturePool.freeAllTextures();
            this.outputTextureTimestamps.clear();
            this.syncObjects.clear();
        }
        this.availableFrames.clear();
        this.isInputStreamEndedWithPendingAvailableFrames = false;
        DefaultShaderProgram defaultShaderProgram2 = this.defaultShaderProgram;
        if (defaultShaderProgram2 != null) {
            defaultShaderProgram2.flush();
        }
        this.inputListener.onFlush();
        for (int i = 0; i < getInputCapacity(); i++) {
            this.inputListener.onReadyToAcceptInputFrame();
        }
    }

    public void release() throws VideoFrameProcessingException {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        DefaultShaderProgram defaultShaderProgram2 = this.defaultShaderProgram;
        if (defaultShaderProgram2 != null) {
            defaultShaderProgram2.release();
        }
        try {
            this.outputTexturePool.deleteAllTextures();
            GlUtil.destroyEglSurface(this.eglDisplay, this.outputEglSurface);
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    public void setMatrixTransformations(List<GlMatrixTransformation> list, List<RgbMatrix> list2) {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        this.matrixTransformations.clear();
        this.matrixTransformations.addAll(list);
        this.rgbMatrices.clear();
        this.rgbMatrices.addAll(list2);
        this.matrixTransformationsChanged = true;
    }

    public void renderOutputFrame(GlObjectsProvider glObjectsProvider, long j) {
        this.videoFrameProcessingTaskExecutor.verifyVideoFrameProcessingThread();
        if (this.textureOutputListener == null) {
            Assertions.checkState(!this.renderFramesAutomatically);
            Pair remove = this.availableFrames.remove();
            renderFrame(glObjectsProvider, (GlTextureInfo) remove.first, ((Long) remove.second).longValue(), j);
            if (this.availableFrames.isEmpty() && this.isInputStreamEndedWithPendingAvailableFrames) {
                ((OnInputStreamProcessedListener) Assertions.checkNotNull(this.onInputStreamProcessedListener)).onInputStreamProcessed();
                this.isInputStreamEndedWithPendingAvailableFrames = false;
            }
        }
    }

    public void setOutputSurfaceInfo(SurfaceInfo surfaceInfo) {
        try {
            this.videoFrameProcessingTaskExecutor.invoke(new FinalShaderProgramWrapper$$ExternalSyntheticLambda6(this, surfaceInfo));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.videoFrameProcessorListenerExecutor.execute(new FinalShaderProgramWrapper$$ExternalSyntheticLambda7(this, e));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOutputSurfaceInfo$3$androidx-media3-effect-FinalShaderProgramWrapper  reason: not valid java name */
    public /* synthetic */ void m441lambda$setOutputSurfaceInfo$3$androidxmedia3effectFinalShaderProgramWrapper(InterruptedException interruptedException) {
        this.videoFrameProcessorListener.onError(VideoFrameProcessingException.from(interruptedException));
    }

    /* access modifiers changed from: private */
    /* renamed from: setOutputSurfaceInfoInternal */
    public void m440lambda$setOutputSurfaceInfo$2$androidxmedia3effectFinalShaderProgramWrapper(SurfaceInfo surfaceInfo) {
        if (this.textureOutputListener == null && !Util.areEqual(this.outputSurfaceInfo, surfaceInfo)) {
            SurfaceInfo surfaceInfo2 = this.outputSurfaceInfo;
            if (surfaceInfo2 != null && (surfaceInfo == null || !surfaceInfo2.surface.equals(surfaceInfo.surface))) {
                destroyOutputEglSurface();
            }
            SurfaceInfo surfaceInfo3 = this.outputSurfaceInfo;
            this.outputSurfaceInfoChanged = (surfaceInfo3 != null && surfaceInfo != null && surfaceInfo3.width == surfaceInfo.width && this.outputSurfaceInfo.height == surfaceInfo.height && this.outputSurfaceInfo.orientationDegrees == surfaceInfo.orientationDegrees) ? false : true;
            this.outputSurfaceInfo = surfaceInfo;
        }
    }

    private int getInputCapacity() {
        if (this.textureOutputListener == null) {
            return 1;
        }
        return this.outputTexturePool.freeTextureCount();
    }

    private void destroyOutputEglSurface() {
        if (this.outputEglSurface != null) {
            try {
                GlUtil.focusEglSurface(this.eglDisplay, this.eglContext, this.placeholderSurface, 1, 1);
                GlUtil.destroyEglSurface(this.eglDisplay, this.outputEglSurface);
            } catch (GlUtil.GlException e) {
                this.videoFrameProcessorListenerExecutor.execute(new FinalShaderProgramWrapper$$ExternalSyntheticLambda1(this, e));
            } catch (Throwable th) {
                this.outputEglSurface = null;
                throw th;
            }
            this.outputEglSurface = null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$destroyOutputEglSurface$4$androidx-media3-effect-FinalShaderProgramWrapper  reason: not valid java name */
    public /* synthetic */ void m434lambda$destroyOutputEglSurface$4$androidxmedia3effectFinalShaderProgramWrapper(GlUtil.GlException glException) {
        this.videoFrameProcessorListener.onError(VideoFrameProcessingException.from(glException));
    }

    private void renderFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j, long j2) {
        if (j2 != -2) {
            try {
                if (ensureConfigured(glObjectsProvider, glTextureInfo.width, glTextureInfo.height)) {
                    if (this.outputSurfaceInfo != null) {
                        renderFrameToOutputSurface(glTextureInfo, j, j2);
                    } else if (this.textureOutputListener != null) {
                        renderFrameToOutputTexture(glTextureInfo, j);
                    }
                    if (!(this.debugSurfaceViewWrapper == null || this.defaultShaderProgram == null)) {
                        renderFrameToDebugSurface(glObjectsProvider, glTextureInfo, j);
                    }
                    this.inputListener.onInputFrameProcessed(glTextureInfo);
                    return;
                }
            } catch (VideoFrameProcessingException | GlUtil.GlException e) {
                this.videoFrameProcessorListenerExecutor.execute(new FinalShaderProgramWrapper$$ExternalSyntheticLambda0(this, e, j));
            }
        }
        this.inputListener.onInputFrameProcessed(glTextureInfo);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$renderFrame$5$androidx-media3-effect-FinalShaderProgramWrapper  reason: not valid java name */
    public /* synthetic */ void m438lambda$renderFrame$5$androidxmedia3effectFinalShaderProgramWrapper(Exception exc, long j) {
        this.videoFrameProcessorListener.onError(VideoFrameProcessingException.from(exc, j));
    }

    private void renderFrameToOutputSurface(GlTextureInfo glTextureInfo, long j, long j2) throws VideoFrameProcessingException, GlUtil.GlException {
        EGLSurface eGLSurface = (EGLSurface) Assertions.checkNotNull(this.outputEglSurface);
        SurfaceInfo surfaceInfo = (SurfaceInfo) Assertions.checkNotNull(this.outputSurfaceInfo);
        GlUtil.focusEglSurface(this.eglDisplay, this.eglContext, eGLSurface, surfaceInfo.width, surfaceInfo.height);
        GlUtil.clearFocusedBuffers();
        ((DefaultShaderProgram) Assertions.checkNotNull(this.defaultShaderProgram)).drawFrame(glTextureInfo.texId, j);
        if (j2 == -1) {
            j2 = System.nanoTime();
        } else if (j2 == -3) {
            Assertions.checkState(j != C.TIME_UNSET);
            j2 = 1000 * j;
        }
        EGLExt.eglPresentationTimeANDROID(this.eglDisplay, eGLSurface, j2);
        EGL14.eglSwapBuffers(this.eglDisplay, eGLSurface);
        DebugTraceUtil.logEvent(DebugTraceUtil.COMPONENT_VFP, DebugTraceUtil.EVENT_RENDERED_TO_OUTPUT_SURFACE, j);
    }

    private void renderFrameToOutputTexture(GlTextureInfo glTextureInfo, long j) throws GlUtil.GlException, VideoFrameProcessingException {
        GlTextureInfo useTexture = this.outputTexturePool.useTexture();
        this.outputTextureTimestamps.add(j);
        GlUtil.focusFramebufferUsingCurrentContext(useTexture.fboId, useTexture.width, useTexture.height);
        GlUtil.clearFocusedBuffers();
        ((DefaultShaderProgram) Assertions.checkNotNull(this.defaultShaderProgram)).drawFrame(glTextureInfo.texId, j);
        long createGlSyncFence = GlUtil.createGlSyncFence();
        this.syncObjects.add(createGlSyncFence);
        ((GlTextureProducer.Listener) Assertions.checkNotNull(this.textureOutputListener)).onTextureRendered(this, useTexture, j, createGlSyncFence);
    }

    private boolean ensureConfigured(GlObjectsProvider glObjectsProvider, int i, int i2) throws VideoFrameProcessingException, GlUtil.GlException {
        int i3;
        int i4;
        boolean z = true;
        boolean z2 = (this.inputWidth == i && this.inputHeight == i2 && this.outputSizeBeforeSurfaceTransformation != null) ? false : true;
        if (z2) {
            this.inputWidth = i;
            this.inputHeight = i2;
            Size configureAndGetOutputSize = MatrixUtils.configureAndGetOutputSize(i, i2, this.matrixTransformations);
            if (!Util.areEqual(this.outputSizeBeforeSurfaceTransformation, configureAndGetOutputSize)) {
                this.outputSizeBeforeSurfaceTransformation = configureAndGetOutputSize;
                this.videoFrameProcessorListenerExecutor.execute(new FinalShaderProgramWrapper$$ExternalSyntheticLambda3(this, configureAndGetOutputSize));
            }
        }
        Assertions.checkNotNull(this.outputSizeBeforeSurfaceTransformation);
        SurfaceInfo surfaceInfo = this.outputSurfaceInfo;
        if (surfaceInfo == null && this.textureOutputListener == null) {
            if (this.outputEglSurface != null) {
                z = false;
            }
            Assertions.checkState(z);
            DefaultShaderProgram defaultShaderProgram2 = this.defaultShaderProgram;
            if (defaultShaderProgram2 != null) {
                defaultShaderProgram2.release();
                this.defaultShaderProgram = null;
            }
            Log.w(TAG, "Output surface and size not set, dropping frame.");
            return false;
        }
        if (surfaceInfo == null) {
            i3 = this.outputSizeBeforeSurfaceTransformation.getWidth();
        } else {
            i3 = surfaceInfo.width;
        }
        SurfaceInfo surfaceInfo2 = this.outputSurfaceInfo;
        if (surfaceInfo2 == null) {
            i4 = this.outputSizeBeforeSurfaceTransformation.getHeight();
        } else {
            i4 = surfaceInfo2.height;
        }
        SurfaceInfo surfaceInfo3 = this.outputSurfaceInfo;
        if (surfaceInfo3 != null && this.outputEglSurface == null) {
            this.outputEglSurface = glObjectsProvider.createEglSurface(this.eglDisplay, surfaceInfo3.surface, this.outputColorInfo.colorTransfer, this.outputSurfaceInfo.isEncoderInputSurface);
        }
        if (this.textureOutputListener != null) {
            this.outputTexturePool.ensureConfigured(glObjectsProvider, i3, i4);
        }
        SurfaceView debugPreviewSurfaceView = this.debugViewProvider.getDebugPreviewSurfaceView(i3, i4);
        if (debugPreviewSurfaceView != null && !Util.areEqual(this.debugSurfaceView, debugPreviewSurfaceView)) {
            this.debugSurfaceViewWrapper = new SurfaceViewWrapper(this.eglDisplay, this.eglContext, debugPreviewSurfaceView, this.outputColorInfo.colorTransfer);
        }
        this.debugSurfaceView = debugPreviewSurfaceView;
        DefaultShaderProgram defaultShaderProgram3 = this.defaultShaderProgram;
        if (defaultShaderProgram3 != null && (this.outputSurfaceInfoChanged || z2 || this.matrixTransformationsChanged)) {
            defaultShaderProgram3.release();
            this.defaultShaderProgram = null;
            this.outputSurfaceInfoChanged = false;
            this.matrixTransformationsChanged = false;
        }
        if (this.defaultShaderProgram == null) {
            SurfaceInfo surfaceInfo4 = this.outputSurfaceInfo;
            this.defaultShaderProgram = createDefaultShaderProgram(surfaceInfo4 == null ? 0 : surfaceInfo4.orientationDegrees, i3, i4);
            this.outputSurfaceInfoChanged = false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$ensureConfigured$6$androidx-media3-effect-FinalShaderProgramWrapper  reason: not valid java name */
    public /* synthetic */ void m435lambda$ensureConfigured$6$androidxmedia3effectFinalShaderProgramWrapper(Size size) {
        this.videoFrameProcessorListener.onOutputSizeChanged(size.getWidth(), size.getHeight());
    }

    private DefaultShaderProgram createDefaultShaderProgram(int i, int i2, int i3) throws VideoFrameProcessingException {
        ImmutableList.Builder addAll = new ImmutableList.Builder().addAll((Iterable) this.matrixTransformations);
        if (i != 0) {
            addAll.add((Object) new ScaleAndRotateTransformation.Builder().setRotationDegrees((float) i).build());
        }
        boolean z = false;
        addAll.add((Object) Presentation.createForWidthAndHeight(i2, i3, 0));
        DefaultShaderProgram createApplyingOetf = DefaultShaderProgram.createApplyingOetf(this.context, addAll.build(), this.rgbMatrices, this.outputColorInfo, this.sdrWorkingColorSpace);
        Size configure = createApplyingOetf.configure(this.inputWidth, this.inputHeight);
        SurfaceInfo surfaceInfo = this.outputSurfaceInfo;
        if (surfaceInfo != null) {
            SurfaceInfo surfaceInfo2 = (SurfaceInfo) Assertions.checkNotNull(surfaceInfo);
            Assertions.checkState(configure.getWidth() == surfaceInfo2.width);
            if (configure.getHeight() == surfaceInfo2.height) {
                z = true;
            }
            Assertions.checkState(z);
        }
        return createApplyingOetf;
    }

    private void renderFrameToDebugSurface(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j) {
        DefaultShaderProgram defaultShaderProgram2 = (DefaultShaderProgram) Assertions.checkNotNull(this.defaultShaderProgram);
        SurfaceViewWrapper surfaceViewWrapper = (SurfaceViewWrapper) Assertions.checkNotNull(this.debugSurfaceViewWrapper);
        try {
            ((SurfaceViewWrapper) Assertions.checkNotNull(surfaceViewWrapper)).maybeRenderToSurfaceView(new FinalShaderProgramWrapper$$ExternalSyntheticLambda4(this, defaultShaderProgram2, surfaceViewWrapper, glTextureInfo, j), glObjectsProvider);
        } catch (VideoFrameProcessingException | GlUtil.GlException e) {
            Log.d(TAG, "Error rendering to debug preview", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$renderFrameToDebugSurface$7$androidx-media3-effect-FinalShaderProgramWrapper  reason: not valid java name */
    public /* synthetic */ void m439lambda$renderFrameToDebugSurface$7$androidxmedia3effectFinalShaderProgramWrapper(DefaultShaderProgram defaultShaderProgram2, SurfaceViewWrapper surfaceViewWrapper, GlTextureInfo glTextureInfo, long j) throws VideoFrameProcessingException, GlUtil.GlException {
        GlUtil.clearFocusedBuffers();
        if (this.sdrWorkingColorSpace == 2) {
            int outputColorTransfer = defaultShaderProgram2.getOutputColorTransfer();
            defaultShaderProgram2.setOutputColorTransfer(surfaceViewWrapper.outputColorTransfer);
            defaultShaderProgram2.drawFrame(glTextureInfo.texId, j);
            defaultShaderProgram2.setOutputColorTransfer(outputColorTransfer);
            return;
        }
        defaultShaderProgram2.drawFrame(glTextureInfo.texId, j);
    }

    private static final class SurfaceViewWrapper implements SurfaceHolder.Callback {
        private final EGLContext eglContext;
        private final EGLDisplay eglDisplay;
        private EGLSurface eglSurface;
        private int height;
        public final int outputColorTransfer;
        private Surface surface;
        private int width;

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
        }

        public SurfaceViewWrapper(EGLDisplay eGLDisplay, EGLContext eGLContext, SurfaceView surfaceView, int i) {
            this.eglDisplay = eGLDisplay;
            this.eglContext = eGLContext;
            if (i == 7 && Util.SDK_INT < 34) {
                i = 6;
            }
            this.outputColorTransfer = i;
            surfaceView.getHolder().addCallback(this);
            this.surface = surfaceView.getHolder().getSurface();
            this.width = surfaceView.getWidth();
            this.height = surfaceView.getHeight();
        }

        public synchronized void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            this.width = i2;
            this.height = i3;
            Surface surface2 = surfaceHolder.getSurface();
            Surface surface3 = this.surface;
            if (surface3 == null || !surface3.equals(surface2)) {
                this.surface = surface2;
                this.eglSurface = null;
            }
        }

        public synchronized void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.surface = null;
            this.eglSurface = null;
            this.width = -1;
            this.height = -1;
        }

        public synchronized void maybeRenderToSurfaceView(VideoFrameProcessingTaskExecutor.Task task, GlObjectsProvider glObjectsProvider) throws GlUtil.GlException, VideoFrameProcessingException {
            Surface surface2 = this.surface;
            if (surface2 != null) {
                if (this.eglSurface == null) {
                    this.eglSurface = glObjectsProvider.createEglSurface(this.eglDisplay, surface2, this.outputColorTransfer, false);
                }
                EGLSurface eGLSurface = this.eglSurface;
                GlUtil.focusEglSurface(this.eglDisplay, this.eglContext, eGLSurface, this.width, this.height);
                task.run();
                EGL14.eglSwapBuffers(this.eglDisplay, eGLSurface);
                GLES20.glFinish();
            }
        }
    }
}
