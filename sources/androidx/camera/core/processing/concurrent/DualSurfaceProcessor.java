package androidx.camera.core.processing.concurrent;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import androidx.camera.core.CompositionSettings;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.ProcessingException;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.processing.DefaultSurfaceProcessor$$ExternalSyntheticLambda10;
import androidx.camera.core.processing.DefaultSurfaceProcessor$$ExternalSyntheticLambda7;
import androidx.camera.core.processing.ShaderProvider;
import androidx.camera.core.processing.SurfaceProcessorInternal;
import androidx.camera.core.processing.util.GLUtils;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function3;

public class DualSurfaceProcessor implements SurfaceProcessorInternal, SurfaceTexture.OnFrameAvailableListener {
    private static final String TAG = "DualSurfaceProcessor";
    private final Executor mGlExecutor;
    final Handler mGlHandler;
    private final DualOpenGlRenderer mGlRenderer;
    final HandlerThread mGlThread;
    private int mInputSurfaceCount;
    private final AtomicBoolean mIsReleaseRequested;
    private boolean mIsReleased;
    final Map<SurfaceOutput, Surface> mOutputSurfaces;
    private SurfaceTexture mPrimarySurfaceTexture;
    private SurfaceTexture mSecondarySurfaceTexture;

    static /* synthetic */ void lambda$executeSafely$7() {
    }

    DualSurfaceProcessor(DynamicRange dynamicRange, CompositionSettings compositionSettings, CompositionSettings compositionSettings2) {
        this(dynamicRange, Collections.emptyMap(), compositionSettings, compositionSettings2);
    }

    DualSurfaceProcessor(DynamicRange dynamicRange, Map<GLUtils.InputFormat, ShaderProvider> map, CompositionSettings compositionSettings, CompositionSettings compositionSettings2) {
        this.mInputSurfaceCount = 0;
        this.mIsReleased = false;
        this.mIsReleaseRequested = new AtomicBoolean(false);
        this.mOutputSurfaces = new LinkedHashMap();
        HandlerThread handlerThread = new HandlerThread("CameraX-GL Thread");
        this.mGlThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.mGlHandler = handler;
        this.mGlExecutor = CameraXExecutors.newHandlerExecutor(handler);
        this.mGlRenderer = new DualOpenGlRenderer(compositionSettings, compositionSettings2);
        try {
            initGlRenderer(dynamicRange, map);
        } catch (RuntimeException e) {
            release();
            throw e;
        }
    }

    public void onInputSurface(SurfaceRequest surfaceRequest) throws ProcessingException {
        if (this.mIsReleaseRequested.get()) {
            surfaceRequest.willNotProvideSurface();
            return;
        }
        DualSurfaceProcessor$$ExternalSyntheticLambda6 dualSurfaceProcessor$$ExternalSyntheticLambda6 = new DualSurfaceProcessor$$ExternalSyntheticLambda6(this, surfaceRequest);
        Objects.requireNonNull(surfaceRequest);
        executeSafely(dualSurfaceProcessor$$ExternalSyntheticLambda6, new DefaultSurfaceProcessor$$ExternalSyntheticLambda10(surfaceRequest));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onInputSurface$1$androidx-camera-core-processing-concurrent-DualSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m218lambda$onInputSurface$1$androidxcameracoreprocessingconcurrentDualSurfaceProcessor(SurfaceRequest surfaceRequest) {
        this.mInputSurfaceCount++;
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.mGlRenderer.getTextureName(surfaceRequest.isPrimary()));
        surfaceTexture.setDefaultBufferSize(surfaceRequest.getResolution().getWidth(), surfaceRequest.getResolution().getHeight());
        Surface surface = new Surface(surfaceTexture);
        surfaceRequest.provideSurface(surface, this.mGlExecutor, new DualSurfaceProcessor$$ExternalSyntheticLambda5(this, surfaceTexture, surface));
        if (surfaceRequest.isPrimary()) {
            this.mPrimarySurfaceTexture = surfaceTexture;
            return;
        }
        this.mSecondarySurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this, this.mGlHandler);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onInputSurface$0$androidx-camera-core-processing-concurrent-DualSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m217lambda$onInputSurface$0$androidxcameracoreprocessingconcurrentDualSurfaceProcessor(SurfaceTexture surfaceTexture, Surface surface, SurfaceRequest.Result result) {
        surfaceTexture.setOnFrameAvailableListener((SurfaceTexture.OnFrameAvailableListener) null);
        surfaceTexture.release();
        surface.release();
        this.mInputSurfaceCount--;
        checkReadyToRelease();
    }

    public void onOutputSurface(SurfaceOutput surfaceOutput) throws ProcessingException {
        if (this.mIsReleaseRequested.get()) {
            surfaceOutput.close();
            return;
        }
        DualSurfaceProcessor$$ExternalSyntheticLambda4 dualSurfaceProcessor$$ExternalSyntheticLambda4 = new DualSurfaceProcessor$$ExternalSyntheticLambda4(this, surfaceOutput);
        Objects.requireNonNull(surfaceOutput);
        executeSafely(dualSurfaceProcessor$$ExternalSyntheticLambda4, new DefaultSurfaceProcessor$$ExternalSyntheticLambda7(surfaceOutput));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onOutputSurface$3$androidx-camera-core-processing-concurrent-DualSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m220lambda$onOutputSurface$3$androidxcameracoreprocessingconcurrentDualSurfaceProcessor(SurfaceOutput surfaceOutput) {
        Surface surface = surfaceOutput.getSurface(this.mGlExecutor, new DualSurfaceProcessor$$ExternalSyntheticLambda3(this, surfaceOutput));
        this.mGlRenderer.registerOutputSurface(surface);
        this.mOutputSurfaces.put(surfaceOutput, surface);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onOutputSurface$2$androidx-camera-core-processing-concurrent-DualSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m219lambda$onOutputSurface$2$androidxcameracoreprocessingconcurrentDualSurfaceProcessor(SurfaceOutput surfaceOutput, SurfaceOutput.Event event) {
        surfaceOutput.close();
        Surface remove = this.mOutputSurfaces.remove(surfaceOutput);
        if (remove != null) {
            this.mGlRenderer.unregisterOutputSurface(remove);
        }
    }

    public void release() {
        if (!this.mIsReleaseRequested.getAndSet(true)) {
            executeSafely(new DualSurfaceProcessor$$ExternalSyntheticLambda2(this));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$4$androidx-camera-core-processing-concurrent-DualSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m221lambda$release$4$androidxcameracoreprocessingconcurrentDualSurfaceProcessor() {
        this.mIsReleased = true;
        checkReadyToRelease();
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        SurfaceTexture surfaceTexture2;
        if (!this.mIsReleaseRequested.get() && (surfaceTexture2 = this.mPrimarySurfaceTexture) != null && this.mSecondarySurfaceTexture != null) {
            surfaceTexture2.updateTexImage();
            this.mSecondarySurfaceTexture.updateTexImage();
            for (Map.Entry next : this.mOutputSurfaces.entrySet()) {
                Surface surface = (Surface) next.getValue();
                SurfaceOutput surfaceOutput = (SurfaceOutput) next.getKey();
                if (surfaceOutput.getFormat() == 34) {
                    try {
                        this.mGlRenderer.render(surfaceTexture.getTimestamp(), surface, surfaceOutput, this.mPrimarySurfaceTexture, this.mSecondarySurfaceTexture);
                    } catch (RuntimeException e) {
                        Logger.e(TAG, "Failed to render with OpenGL.", e);
                    }
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Exception} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.RuntimeException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.lang.RuntimeException} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initGlRenderer(androidx.camera.core.DynamicRange r2, java.util.Map<androidx.camera.core.processing.util.GLUtils.InputFormat, androidx.camera.core.processing.ShaderProvider> r3) {
        /*
            r1 = this;
            androidx.camera.core.processing.concurrent.DualSurfaceProcessor$$ExternalSyntheticLambda7 r0 = new androidx.camera.core.processing.concurrent.DualSurfaceProcessor$$ExternalSyntheticLambda7
            r0.<init>(r1, r2, r3)
            com.google.common.util.concurrent.ListenableFuture r2 = androidx.concurrent.futures.CallbackToFutureAdapter.getFuture(r0)
            r2.get()     // Catch:{ ExecutionException -> 0x000f, InterruptedException -> 0x000d }
            return
        L_0x000d:
            r2 = move-exception
            goto L_0x0010
        L_0x000f:
            r2 = move-exception
        L_0x0010:
            boolean r3 = r2 instanceof java.util.concurrent.ExecutionException
            if (r3 == 0) goto L_0x0018
            java.lang.Throwable r2 = r2.getCause()
        L_0x0018:
            boolean r3 = r2 instanceof java.lang.RuntimeException
            if (r3 == 0) goto L_0x001f
            java.lang.RuntimeException r2 = (java.lang.RuntimeException) r2
            throw r2
        L_0x001f:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r0 = "Failed to create DefaultSurfaceProcessor"
            r3.<init>(r0, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.processing.concurrent.DualSurfaceProcessor.initGlRenderer(androidx.camera.core.DynamicRange, java.util.Map):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initGlRenderer$6$androidx-camera-core-processing-concurrent-DualSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ Object m216lambda$initGlRenderer$6$androidxcameracoreprocessingconcurrentDualSurfaceProcessor(DynamicRange dynamicRange, Map map, CallbackToFutureAdapter.Completer completer) throws Exception {
        executeSafely(new DualSurfaceProcessor$$ExternalSyntheticLambda0(this, dynamicRange, map, completer));
        return "Init GlRenderer";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initGlRenderer$5$androidx-camera-core-processing-concurrent-DualSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m215lambda$initGlRenderer$5$androidxcameracoreprocessingconcurrentDualSurfaceProcessor(DynamicRange dynamicRange, Map map, CallbackToFutureAdapter.Completer completer) {
        try {
            this.mGlRenderer.init(dynamicRange, map);
            completer.set(null);
        } catch (RuntimeException e) {
            completer.setException(e);
        }
    }

    private void executeSafely(Runnable runnable) {
        executeSafely(runnable, new DualSurfaceProcessor$$ExternalSyntheticLambda8());
    }

    private void executeSafely(Runnable runnable, Runnable runnable2) {
        try {
            this.mGlExecutor.execute(new DualSurfaceProcessor$$ExternalSyntheticLambda1(this, runnable2, runnable));
        } catch (RejectedExecutionException e) {
            Logger.w(TAG, "Unable to executor runnable", e);
            runnable2.run();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$executeSafely$8$androidx-camera-core-processing-concurrent-DualSurfaceProcessor  reason: not valid java name */
    public /* synthetic */ void m214lambda$executeSafely$8$androidxcameracoreprocessingconcurrentDualSurfaceProcessor(Runnable runnable, Runnable runnable2) {
        if (this.mIsReleased) {
            runnable.run();
        } else {
            runnable2.run();
        }
    }

    private void checkReadyToRelease() {
        if (this.mIsReleased && this.mInputSurfaceCount == 0) {
            for (SurfaceOutput close : this.mOutputSurfaces.keySet()) {
                close.close();
            }
            this.mOutputSurfaces.clear();
            this.mGlRenderer.release();
            this.mGlThread.quit();
        }
    }

    public static class Factory {
        private static Function3<DynamicRange, CompositionSettings, CompositionSettings, SurfaceProcessorInternal> sSupplier = new DualSurfaceProcessor$Factory$$ExternalSyntheticLambda0();

        private Factory() {
        }

        public static SurfaceProcessorInternal newInstance(DynamicRange dynamicRange, CompositionSettings compositionSettings, CompositionSettings compositionSettings2) {
            return sSupplier.invoke(dynamicRange, compositionSettings, compositionSettings2);
        }

        public static void setSupplier(Function3<DynamicRange, CompositionSettings, CompositionSettings, SurfaceProcessorInternal> function3) {
            sSupplier = function3;
        }
    }
}
