package androidx.camera.camera2.internal;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.workaround.FlashAvailabilityChecker;
import androidx.camera.core.CameraControl;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Objects;
import java.util.concurrent.Executor;

final class TorchControl {
    static final int DEFAULT_TORCH_STATE = 0;
    private static final String TAG = "TorchControl";
    private final Camera2CameraControlImpl mCamera2CameraControlImpl;
    CallbackToFutureAdapter.Completer<Void> mEnableTorchCompleter;
    private final Executor mExecutor;
    private final boolean mHasFlashUnit;
    private boolean mIsActive;
    boolean mTargetTorchEnabled;
    private final MutableLiveData<Integer> mTorchState = new MutableLiveData<>(0);

    TorchControl(Camera2CameraControlImpl camera2CameraControlImpl, CameraCharacteristicsCompat cameraCharacteristicsCompat, Executor executor) {
        this.mCamera2CameraControlImpl = camera2CameraControlImpl;
        this.mExecutor = executor;
        Objects.requireNonNull(cameraCharacteristicsCompat);
        this.mHasFlashUnit = FlashAvailabilityChecker.isFlashAvailable(new Camera2CameraInfoImpl$$ExternalSyntheticLambda0(cameraCharacteristicsCompat));
        camera2CameraControlImpl.addCaptureResultListener(new TorchControl$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-camera-camera2-internal-TorchControl  reason: not valid java name */
    public /* synthetic */ boolean m96lambda$new$0$androidxcameracamera2internalTorchControl(TotalCaptureResult totalCaptureResult) {
        if (this.mEnableTorchCompleter != null) {
            Integer num = (Integer) totalCaptureResult.getRequest().get(CaptureRequest.FLASH_MODE);
            if ((num != null && num.intValue() == 2) == this.mTargetTorchEnabled) {
                this.mEnableTorchCompleter.set(null);
                this.mEnableTorchCompleter = null;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void setActive(boolean z) {
        if (this.mIsActive != z) {
            this.mIsActive = z;
            if (!z) {
                if (this.mTargetTorchEnabled) {
                    this.mTargetTorchEnabled = false;
                    this.mCamera2CameraControlImpl.enableTorchInternal(false);
                    setLiveDataValue(this.mTorchState, 0);
                }
                CallbackToFutureAdapter.Completer<Void> completer = this.mEnableTorchCompleter;
                if (completer != null) {
                    completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
                    this.mEnableTorchCompleter = null;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> enableTorch(boolean z) {
        if (!this.mHasFlashUnit) {
            Logger.d(TAG, "Unable to enableTorch due to there is no flash unit.");
            return Futures.immediateFailedFuture(new IllegalStateException("No flash unit"));
        }
        setLiveDataValue(this.mTorchState, Integer.valueOf(z ? 1 : 0));
        return CallbackToFutureAdapter.getFuture(new TorchControl$$ExternalSyntheticLambda1(this, z));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$enableTorch$2$androidx-camera-camera2-internal-TorchControl  reason: not valid java name */
    public /* synthetic */ Object m95lambda$enableTorch$2$androidxcameracamera2internalTorchControl(boolean z, CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new TorchControl$$ExternalSyntheticLambda0(this, completer, z));
        return "enableTorch: " + z;
    }

    /* access modifiers changed from: package-private */
    public LiveData<Integer> getTorchState() {
        return this.mTorchState;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: enableTorchInternal */
    public void m94lambda$enableTorch$1$androidxcameracamera2internalTorchControl(CallbackToFutureAdapter.Completer<Void> completer, boolean z) {
        if (!this.mHasFlashUnit) {
            if (completer != null) {
                completer.setException(new IllegalStateException("No flash unit"));
            }
        } else if (!this.mIsActive) {
            setLiveDataValue(this.mTorchState, 0);
            if (completer != null) {
                completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            }
        } else {
            this.mTargetTorchEnabled = z;
            this.mCamera2CameraControlImpl.enableTorchInternal(z);
            setLiveDataValue(this.mTorchState, Integer.valueOf(z ? 1 : 0));
            CallbackToFutureAdapter.Completer<Void> completer2 = this.mEnableTorchCompleter;
            if (completer2 != null) {
                completer2.setException(new CameraControl.OperationCanceledException("There is a new enableTorch being set"));
            }
            this.mEnableTorchCompleter = completer;
        }
    }

    private <T> void setLiveDataValue(MutableLiveData<T> mutableLiveData, T t) {
        if (Threads.isMainThread()) {
            mutableLiveData.setValue(t);
        } else {
            mutableLiveData.postValue(t);
        }
    }
}
