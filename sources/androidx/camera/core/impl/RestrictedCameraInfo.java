package androidx.camera.core.impl;

import android.util.Range;
import android.util.Rational;
import androidx.camera.core.ExposureState;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.utils.SessionProcessorUtil;
import androidx.camera.core.internal.ImmutableZoomState;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RestrictedCameraInfo extends ForwardingCameraInfo {
    public static final int CAMERA_OPERATION_AE_REGION = 3;
    public static final int CAMERA_OPERATION_AF_REGION = 2;
    public static final int CAMERA_OPERATION_AUTO_FOCUS = 1;
    public static final int CAMERA_OPERATION_AWB_REGION = 4;
    public static final int CAMERA_OPERATION_EXPOSURE_COMPENSATION = 7;
    public static final int CAMERA_OPERATION_EXTENSION_STRENGTH = 8;
    public static final int CAMERA_OPERATION_FLASH = 5;
    public static final int CAMERA_OPERATION_TORCH = 6;
    public static final int CAMERA_OPERATION_ZOOM = 0;
    private final CameraConfig mCameraConfig;
    private final CameraInfoInternal mCameraInfo;
    private boolean mIsCaptureProcessProgressSupported = false;
    private boolean mIsPostviewSupported = false;
    private final SessionProcessor mSessionProcessor;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CameraOperation {
    }

    public RestrictedCameraInfo(CameraInfoInternal cameraInfoInternal, CameraConfig cameraConfig) {
        super(cameraInfoInternal);
        this.mCameraInfo = cameraInfoInternal;
        this.mCameraConfig = cameraConfig;
        this.mSessionProcessor = cameraConfig.getSessionProcessor((SessionProcessor) null);
        setPostviewSupported(cameraConfig.isPostviewSupported());
        setCaptureProcessProgressSupported(cameraConfig.isCaptureProcessProgressSupported());
    }

    public CameraConfig getCameraConfig() {
        return this.mCameraConfig;
    }

    public CameraInfoInternal getImplementation() {
        return this.mCameraInfo;
    }

    public SessionProcessor getSessionProcessor() {
        return this.mSessionProcessor;
    }

    public boolean hasFlashUnit() {
        if (!SessionProcessorUtil.isOperationSupported(this.mSessionProcessor, 5)) {
            return false;
        }
        return this.mCameraInfo.hasFlashUnit();
    }

    public LiveData<Integer> getTorchState() {
        if (!SessionProcessorUtil.isOperationSupported(this.mSessionProcessor, 6)) {
            return new MutableLiveData(0);
        }
        return this.mCameraInfo.getTorchState();
    }

    public LiveData<ZoomState> getZoomState() {
        if (!SessionProcessorUtil.isOperationSupported(this.mSessionProcessor, 0)) {
            return new MutableLiveData(ImmutableZoomState.create(1.0f, 1.0f, 1.0f, 0.0f));
        }
        return this.mCameraInfo.getZoomState();
    }

    public ExposureState getExposureState() {
        return !SessionProcessorUtil.isOperationSupported(this.mSessionProcessor, 7) ? new ExposureState() {
            public int getExposureCompensationIndex() {
                return 0;
            }

            public boolean isExposureCompensationSupported() {
                return false;
            }

            public Range<Integer> getExposureCompensationRange() {
                return new Range<>(0, 0);
            }

            public Rational getExposureCompensationStep() {
                return Rational.ZERO;
            }
        } : this.mCameraInfo.getExposureState();
    }

    public boolean isFocusMeteringSupported(FocusMeteringAction focusMeteringAction) {
        FocusMeteringAction modifiedFocusMeteringAction = SessionProcessorUtil.getModifiedFocusMeteringAction(this.mSessionProcessor, focusMeteringAction);
        if (modifiedFocusMeteringAction == null) {
            return false;
        }
        return this.mCameraInfo.isFocusMeteringSupported(modifiedFocusMeteringAction);
    }

    public void setPostviewSupported(boolean z) {
        this.mIsPostviewSupported = z;
    }

    public void setCaptureProcessProgressSupported(boolean z) {
        this.mIsCaptureProcessProgressSupported = z;
    }

    public boolean isPostviewSupported() {
        return this.mIsPostviewSupported;
    }

    public boolean isCaptureProcessProgressSupported() {
        return this.mIsCaptureProcessProgressSupported;
    }
}
