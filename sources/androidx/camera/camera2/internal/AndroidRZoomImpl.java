package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Range;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.ZoomControl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.params.CaptureRequestParameterCompat;
import androidx.camera.core.CameraControl;
import androidx.camera.core.impl.Config;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;

final class AndroidRZoomImpl implements ZoomControl.ZoomImpl {
    public static final float DEFAULT_ZOOM_RATIO = 1.0f;
    private final CameraCharacteristicsCompat mCameraCharacteristics;
    private float mCurrentZoomRatio = 1.0f;
    private float mPendingZoomRatio = 1.0f;
    private CallbackToFutureAdapter.Completer<Void> mPendingZoomRatioCompleter;
    private boolean mShouldOverrideZoom = false;
    private final Range<Float> mZoomRatioRange;

    AndroidRZoomImpl(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        this.mCameraCharacteristics = cameraCharacteristicsCompat;
        this.mZoomRatioRange = (Range) cameraCharacteristicsCompat.get(CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE);
        this.mShouldOverrideZoom = cameraCharacteristicsCompat.isZoomOverrideAvailable();
    }

    public float getMinZoom() {
        return this.mZoomRatioRange.getLower().floatValue();
    }

    public float getMaxZoom() {
        return this.mZoomRatioRange.getUpper().floatValue();
    }

    public void addRequestOption(Camera2ImplConfig.Builder builder) {
        builder.setCaptureRequestOptionWithPriority(CaptureRequest.CONTROL_ZOOM_RATIO, Float.valueOf(this.mCurrentZoomRatio), Config.OptionPriority.REQUIRED);
        if (this.mShouldOverrideZoom) {
            CaptureRequestParameterCompat.setSettingsOverrideZoom(builder, Config.OptionPriority.REQUIRED);
        }
    }

    public void resetZoom() {
        this.mCurrentZoomRatio = 1.0f;
        CallbackToFutureAdapter.Completer<Void> completer = this.mPendingZoomRatioCompleter;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            this.mPendingZoomRatioCompleter = null;
        }
    }

    public void setZoomRatio(float f, CallbackToFutureAdapter.Completer<Void> completer) {
        this.mCurrentZoomRatio = f;
        CallbackToFutureAdapter.Completer<Void> completer2 = this.mPendingZoomRatioCompleter;
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("There is a new zoomRatio being set"));
        }
        this.mPendingZoomRatio = this.mCurrentZoomRatio;
        this.mPendingZoomRatioCompleter = completer;
    }

    public void onCaptureResult(TotalCaptureResult totalCaptureResult) {
        Float f;
        if (this.mPendingZoomRatioCompleter != null) {
            CaptureRequest request = totalCaptureResult.getRequest();
            if (request == null) {
                f = null;
            } else {
                f = (Float) request.get(CaptureRequest.CONTROL_ZOOM_RATIO);
            }
            if (f != null) {
                if (this.mPendingZoomRatio == f.floatValue()) {
                    this.mPendingZoomRatioCompleter.set(null);
                    this.mPendingZoomRatioCompleter = null;
                }
            }
        }
    }

    public Rect getCropSensorRegion() {
        return (Rect) Preconditions.checkNotNull((Rect) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE));
    }
}
