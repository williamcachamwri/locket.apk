package androidx.camera.core.processing;

import androidx.camera.core.CameraEffect;
import androidx.camera.core.Logger;
import androidx.camera.core.ProcessingException;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceProcessor;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.core.util.Consumer;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Objects;
import java.util.concurrent.Executor;

public class SurfaceProcessorWithExecutor implements SurfaceProcessorInternal {
    private static final String TAG = "SurfaceProcessor";
    private final Consumer<Throwable> mErrorListener;
    private final Executor mExecutor;
    private final SurfaceProcessor mSurfaceProcessor;

    public void release() {
    }

    public SurfaceProcessorWithExecutor(CameraEffect cameraEffect) {
        this.mSurfaceProcessor = (SurfaceProcessor) Objects.requireNonNull(cameraEffect.getSurfaceProcessor());
        this.mExecutor = cameraEffect.getExecutor();
        this.mErrorListener = cameraEffect.getErrorListener();
    }

    public SurfaceProcessor getProcessor() {
        return this.mSurfaceProcessor;
    }

    public Executor getExecutor() {
        return this.mExecutor;
    }

    public void onInputSurface(SurfaceRequest surfaceRequest) {
        this.mExecutor.execute(new SurfaceProcessorWithExecutor$$ExternalSyntheticLambda0(this, surfaceRequest));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onInputSurface$0$androidx-camera-core-processing-SurfaceProcessorWithExecutor  reason: not valid java name */
    public /* synthetic */ void m212lambda$onInputSurface$0$androidxcameracoreprocessingSurfaceProcessorWithExecutor(SurfaceRequest surfaceRequest) {
        try {
            this.mSurfaceProcessor.onInputSurface(surfaceRequest);
        } catch (ProcessingException e) {
            Logger.e(TAG, "Failed to setup SurfaceProcessor input.", e);
            this.mErrorListener.accept(e);
        }
    }

    public void onOutputSurface(SurfaceOutput surfaceOutput) {
        this.mExecutor.execute(new SurfaceProcessorWithExecutor$$ExternalSyntheticLambda1(this, surfaceOutput));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onOutputSurface$1$androidx-camera-core-processing-SurfaceProcessorWithExecutor  reason: not valid java name */
    public /* synthetic */ void m213lambda$onOutputSurface$1$androidxcameracoreprocessingSurfaceProcessorWithExecutor(SurfaceOutput surfaceOutput) {
        try {
            this.mSurfaceProcessor.onOutputSurface(surfaceOutput);
        } catch (ProcessingException e) {
            Logger.e(TAG, "Failed to setup SurfaceProcessor output.", e);
            this.mErrorListener.accept(e);
        }
    }

    public ListenableFuture<Void> snapshot(int i, int i2) {
        return Futures.immediateFailedFuture(new Exception("Snapshot not supported by external SurfaceProcessor"));
    }
}
