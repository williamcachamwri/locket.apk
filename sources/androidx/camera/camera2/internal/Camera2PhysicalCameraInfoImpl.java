package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Range;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraState;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.ExposureState;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import java.util.Set;

public class Camera2PhysicalCameraInfoImpl implements CameraInfo {
    private final Camera2CameraInfo mCamera2CameraInfo = new Camera2CameraInfo(this);
    private final CameraCharacteristicsCompat mCameraCharacteristicsCompat;
    private final String mCameraId;

    public Camera2PhysicalCameraInfoImpl(String str, CameraManagerCompat cameraManagerCompat) throws CameraAccessExceptionCompat {
        this.mCameraId = str;
        this.mCameraCharacteristicsCompat = cameraManagerCompat.getCameraCharacteristicsCompat(str);
    }

    public Camera2CameraInfo getCamera2CameraInfo() {
        return this.mCamera2CameraInfo;
    }

    public String getCameraId() {
        return this.mCameraId;
    }

    public CameraCharacteristicsCompat getCameraCharacteristicsCompat() {
        return this.mCameraCharacteristicsCompat;
    }

    public int getSensorRotationDegrees() {
        return getSensorRotationDegrees(0);
    }

    public int getSensorRotationDegrees(int i) {
        int sensorOrientation = getSensorOrientation();
        int surfaceRotationToDegrees = CameraOrientationUtil.surfaceRotationToDegrees(i);
        boolean z = true;
        if (1 != getLensFacing()) {
            z = false;
        }
        return CameraOrientationUtil.getRelativeImageRotation(surfaceRotationToDegrees, sensorOrientation, z);
    }

    public boolean hasFlashUnit() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public LiveData<Integer> getTorchState() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public LiveData<ZoomState> getZoomState() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public ExposureState getExposureState() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public LiveData<CameraState> getCameraState() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public String getImplementationType() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public CameraSelector getCameraSelector() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public int getLensFacing() {
        Integer num = (Integer) this.mCameraCharacteristicsCompat.get(CameraCharacteristics.LENS_FACING);
        Preconditions.checkArgument(num != null, "Unable to get the lens facing of the camera.");
        return LensFacingUtil.getCameraSelectorLensFacing(num.intValue());
    }

    public float getIntrinsicZoomRatio() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public boolean isFocusMeteringSupported(FocusMeteringAction focusMeteringAction) {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public boolean isZslSupported() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public Set<Range<Integer>> getSupportedFrameRateRanges() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public boolean isLogicalMultiCameraSupported() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public boolean isPrivateReprocessingSupported() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public Set<DynamicRange> querySupportedDynamicRanges(Set<DynamicRange> set) {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    public Set<CameraInfo> getPhysicalCameraInfos() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    /* access modifiers changed from: package-private */
    public int getSensorOrientation() {
        Integer num = (Integer) this.mCameraCharacteristicsCompat.get(CameraCharacteristics.SENSOR_ORIENTATION);
        Preconditions.checkNotNull(num);
        return num.intValue();
    }
}
