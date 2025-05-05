package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.os.Looper;
import android.util.Range;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.CameraControl;
import androidx.camera.core.Logger;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.ImmutableZoomState;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

final class ZoomControl {
    public static final float DEFAULT_ZOOM_RATIO = 1.0f;
    private static final String TAG = "ZoomControl";
    private final Camera2CameraControlImpl mCamera2CameraControlImpl;
    private Camera2CameraControlImpl.CaptureResultListener mCaptureResultListener = new Camera2CameraControlImpl.CaptureResultListener() {
        public boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
            ZoomControl.this.mZoomImpl.onCaptureResult(totalCaptureResult);
            return false;
        }
    };
    private final ZoomStateImpl mCurrentZoomState;
    private final Executor mExecutor;
    private boolean mIsActive = false;
    final ZoomImpl mZoomImpl;
    private final MutableLiveData<ZoomState> mZoomStateLiveData;

    interface ZoomImpl {
        void addRequestOption(Camera2ImplConfig.Builder builder);

        Rect getCropSensorRegion();

        float getMaxZoom();

        float getMinZoom();

        void onCaptureResult(TotalCaptureResult totalCaptureResult);

        void resetZoom();

        void setZoomRatio(float f, CallbackToFutureAdapter.Completer<Void> completer);
    }

    ZoomControl(Camera2CameraControlImpl camera2CameraControlImpl, CameraCharacteristicsCompat cameraCharacteristicsCompat, Executor executor) {
        this.mCamera2CameraControlImpl = camera2CameraControlImpl;
        this.mExecutor = executor;
        ZoomImpl createZoomImpl = createZoomImpl(cameraCharacteristicsCompat);
        this.mZoomImpl = createZoomImpl;
        ZoomStateImpl zoomStateImpl = new ZoomStateImpl(createZoomImpl.getMaxZoom(), createZoomImpl.getMinZoom());
        this.mCurrentZoomState = zoomStateImpl;
        zoomStateImpl.setZoomRatio(1.0f);
        this.mZoomStateLiveData = new MutableLiveData<>(ImmutableZoomState.create(zoomStateImpl));
        camera2CameraControlImpl.addCaptureResultListener(this.mCaptureResultListener);
    }

    static ZoomState getDefaultZoomState(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        ZoomImpl createZoomImpl = createZoomImpl(cameraCharacteristicsCompat);
        ZoomStateImpl zoomStateImpl = new ZoomStateImpl(createZoomImpl.getMaxZoom(), createZoomImpl.getMinZoom());
        zoomStateImpl.setZoomRatio(1.0f);
        return ImmutableZoomState.create(zoomStateImpl);
    }

    private static ZoomImpl createZoomImpl(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        if (isAndroidRZoomSupported(cameraCharacteristicsCompat)) {
            return new AndroidRZoomImpl(cameraCharacteristicsCompat);
        }
        return new CropRegionZoomImpl(cameraCharacteristicsCompat);
    }

    static boolean isAndroidRZoomSupported(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        return Build.VERSION.SDK_INT >= 30 && getZoomRatioRange(cameraCharacteristicsCompat) != null;
    }

    private static Range<Float> getZoomRatioRange(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        try {
            return (Range) cameraCharacteristicsCompat.get(CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE);
        } catch (AssertionError e) {
            Logger.w(TAG, "AssertionError, fail to get camera characteristic.", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void addZoomOption(Camera2ImplConfig.Builder builder) {
        this.mZoomImpl.addRequestOption(builder);
    }

    /* access modifiers changed from: package-private */
    public Rect getCropSensorRegion() {
        return this.mZoomImpl.getCropSensorRegion();
    }

    /* access modifiers changed from: package-private */
    public void setActive(boolean z) {
        ZoomState create;
        if (this.mIsActive != z) {
            this.mIsActive = z;
            if (!z) {
                synchronized (this.mCurrentZoomState) {
                    this.mCurrentZoomState.setZoomRatio(1.0f);
                    create = ImmutableZoomState.create(this.mCurrentZoomState);
                }
                updateLiveData(create);
                this.mZoomImpl.resetZoom();
                this.mCamera2CameraControlImpl.updateSessionConfigSynchronous();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> setZoomRatio(float f) {
        ZoomState create;
        synchronized (this.mCurrentZoomState) {
            try {
                this.mCurrentZoomState.setZoomRatio(f);
                create = ImmutableZoomState.create(this.mCurrentZoomState);
            } catch (IllegalArgumentException e) {
                return Futures.immediateFailedFuture(e);
            }
        }
        updateLiveData(create);
        return CallbackToFutureAdapter.getFuture(new ZoomControl$$ExternalSyntheticLambda0(this, create));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setZoomRatio$1$androidx-camera-camera2-internal-ZoomControl  reason: not valid java name */
    public /* synthetic */ Object m100lambda$setZoomRatio$1$androidxcameracamera2internalZoomControl(ZoomState zoomState, CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new ZoomControl$$ExternalSyntheticLambda2(this, completer, zoomState));
        return "setZoomRatio";
    }

    /* access modifiers changed from: private */
    /* renamed from: submitCameraZoomRatio */
    public void m99lambda$setZoomRatio$0$androidxcameracamera2internalZoomControl(CallbackToFutureAdapter.Completer<Void> completer, ZoomState zoomState) {
        ZoomState create;
        if (!this.mIsActive) {
            synchronized (this.mCurrentZoomState) {
                this.mCurrentZoomState.setZoomRatio(1.0f);
                create = ImmutableZoomState.create(this.mCurrentZoomState);
            }
            updateLiveData(create);
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            return;
        }
        this.mZoomImpl.setZoomRatio(zoomState.getZoomRatio(), completer);
        this.mCamera2CameraControlImpl.updateSessionConfigSynchronous();
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> setLinearZoom(float f) {
        ZoomState create;
        synchronized (this.mCurrentZoomState) {
            try {
                this.mCurrentZoomState.setLinearZoom(f);
                create = ImmutableZoomState.create(this.mCurrentZoomState);
            } catch (IllegalArgumentException e) {
                return Futures.immediateFailedFuture(e);
            }
        }
        updateLiveData(create);
        return CallbackToFutureAdapter.getFuture(new ZoomControl$$ExternalSyntheticLambda3(this, create));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setLinearZoom$3$androidx-camera-camera2-internal-ZoomControl  reason: not valid java name */
    public /* synthetic */ Object m98lambda$setLinearZoom$3$androidxcameracamera2internalZoomControl(ZoomState zoomState, CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new ZoomControl$$ExternalSyntheticLambda1(this, completer, zoomState));
        return "setLinearZoom";
    }

    private void updateLiveData(ZoomState zoomState) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.mZoomStateLiveData.setValue(zoomState);
        } else {
            this.mZoomStateLiveData.postValue(zoomState);
        }
    }

    /* access modifiers changed from: package-private */
    public LiveData<ZoomState> getZoomState() {
        return this.mZoomStateLiveData;
    }
}
