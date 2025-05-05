package androidx.camera.camera2.interop;

import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.CameraControl;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

public final class Camera2CameraControl {
    private Camera2ImplConfig.Builder mBuilder = new Camera2ImplConfig.Builder();
    private final Camera2CameraControlImpl mCamera2CameraControlImpl;
    CallbackToFutureAdapter.Completer<Void> mCompleter;
    final Executor mExecutor;
    private boolean mIsActive = false;
    final Object mLock = new Object();
    private boolean mPendingUpdate = false;

    public Camera2CameraControl(Camera2CameraControlImpl camera2CameraControlImpl, Executor executor) {
        this.mCamera2CameraControlImpl = camera2CameraControlImpl;
        this.mExecutor = executor;
    }

    public static Camera2CameraControl from(CameraControl cameraControl) {
        CameraControlInternal implementation = ((CameraControlInternal) cameraControl).getImplementation();
        Preconditions.checkArgument(implementation instanceof Camera2CameraControlImpl, "CameraControl doesn't contain Camera2 implementation.");
        return ((Camera2CameraControlImpl) implementation).getCamera2CameraControl();
    }

    public ListenableFuture<Void> setCaptureRequestOptions(CaptureRequestOptions captureRequestOptions) {
        clearCaptureRequestOptionsInternal();
        addCaptureRequestOptionsInternal(captureRequestOptions);
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new Camera2CameraControl$$ExternalSyntheticLambda6(this)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setCaptureRequestOptions$1$androidx-camera-camera2-interop-Camera2CameraControl  reason: not valid java name */
    public /* synthetic */ Object m132lambda$setCaptureRequestOptions$1$androidxcameracamera2interopCamera2CameraControl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Camera2CameraControl$$ExternalSyntheticLambda5(this, completer));
        return "setCaptureRequestOptions";
    }

    public ListenableFuture<Void> addCaptureRequestOptions(CaptureRequestOptions captureRequestOptions) {
        addCaptureRequestOptionsInternal(captureRequestOptions);
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new Camera2CameraControl$$ExternalSyntheticLambda4(this)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addCaptureRequestOptions$3$androidx-camera-camera2-interop-Camera2CameraControl  reason: not valid java name */
    public /* synthetic */ Object m127lambda$addCaptureRequestOptions$3$androidxcameracamera2interopCamera2CameraControl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Camera2CameraControl$$ExternalSyntheticLambda1(this, completer));
        return "addCaptureRequestOptions";
    }

    public CaptureRequestOptions getCaptureRequestOptions() {
        CaptureRequestOptions build;
        synchronized (this.mLock) {
            build = CaptureRequestOptions.Builder.from(this.mBuilder.build()).build();
        }
        return build;
    }

    public ListenableFuture<Void> clearCaptureRequestOptions() {
        clearCaptureRequestOptionsInternal();
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new Camera2CameraControl$$ExternalSyntheticLambda3(this)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$clearCaptureRequestOptions$5$androidx-camera-camera2-interop-Camera2CameraControl  reason: not valid java name */
    public /* synthetic */ Object m129lambda$clearCaptureRequestOptions$5$androidxcameracamera2interopCamera2CameraControl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Camera2CameraControl$$ExternalSyntheticLambda0(this, completer));
        return "clearCaptureRequestOptions";
    }

    public Camera2ImplConfig getCamera2ImplConfig() {
        Camera2ImplConfig build;
        synchronized (this.mLock) {
            build = this.mBuilder.build();
        }
        return build;
    }

    public void applyOptionsToBuilder(Camera2ImplConfig.Builder builder) {
        synchronized (this.mLock) {
            builder.insertAllOptions(this.mBuilder.getMutableConfig(), Config.OptionPriority.ALWAYS_OVERRIDE);
        }
    }

    private void addCaptureRequestOptionsInternal(CaptureRequestOptions captureRequestOptions) {
        synchronized (this.mLock) {
            this.mBuilder.insertAllOptions(captureRequestOptions);
        }
    }

    private void clearCaptureRequestOptionsInternal() {
        synchronized (this.mLock) {
            this.mBuilder = new Camera2ImplConfig.Builder();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateConfig */
    public void m131lambda$setCaptureRequestOptions$0$androidxcameracamera2interopCamera2CameraControl(CallbackToFutureAdapter.Completer<Void> completer) {
        this.mPendingUpdate = true;
        failInFlightUpdate(new CameraControl.OperationCanceledException("Camera2CameraControl was updated with new options."));
        this.mCompleter = completer;
        if (this.mIsActive) {
            updateSession();
        }
    }

    private void updateSession() {
        this.mCamera2CameraControlImpl.updateSessionConfigAsync().addListener(new Camera2CameraControl$$ExternalSyntheticLambda7(this), this.mExecutor);
        this.mPendingUpdate = false;
    }

    public void setActive(boolean z) {
        this.mExecutor.execute(new Camera2CameraControl$$ExternalSyntheticLambda2(this, z));
    }

    /* access modifiers changed from: private */
    /* renamed from: setActiveInternal */
    public void m130lambda$setActive$6$androidxcameracamera2interopCamera2CameraControl(boolean z) {
        if (this.mIsActive != z) {
            this.mIsActive = z;
            if (!z) {
                failInFlightUpdate(new CameraControl.OperationCanceledException("The camera control has became inactive."));
            } else if (this.mPendingUpdate) {
                updateSession();
            }
        }
    }

    /* access modifiers changed from: private */
    public void completeInFlightUpdate() {
        CallbackToFutureAdapter.Completer<Void> completer = this.mCompleter;
        if (completer != null) {
            completer.set(null);
            this.mCompleter = null;
        }
    }

    private void failInFlightUpdate(Exception exc) {
        CallbackToFutureAdapter.Completer<Void> completer = this.mCompleter;
        if (completer != null) {
            if (exc == null) {
                exc = new Exception("Camera2CameraControl failed with unknown error.");
            }
            completer.setException(exc);
            this.mCompleter = null;
        }
    }
}
