package androidx.camera.camera2.interop;

import android.hardware.camera2.CameraCharacteristics;
import androidx.camera.camera2.internal.Camera2CameraInfoImpl;
import androidx.camera.camera2.internal.Camera2PhysicalCameraInfoImpl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.core.util.Preconditions;
import java.util.Collections;
import java.util.Map;

public final class Camera2CameraInfo {
    private static final String TAG = "Camera2CameraInfo";
    private Camera2CameraInfoImpl mCamera2CameraInfoImpl;
    private Camera2PhysicalCameraInfoImpl mCamera2PhysicalCameraInfo;

    public Camera2CameraInfo(Camera2CameraInfoImpl camera2CameraInfoImpl) {
        this.mCamera2CameraInfoImpl = camera2CameraInfoImpl;
    }

    public Camera2CameraInfo(Camera2PhysicalCameraInfoImpl camera2PhysicalCameraInfoImpl) {
        this.mCamera2PhysicalCameraInfo = camera2PhysicalCameraInfoImpl;
    }

    public static Camera2CameraInfo from(CameraInfo cameraInfo) {
        if (cameraInfo instanceof Camera2PhysicalCameraInfoImpl) {
            return ((Camera2PhysicalCameraInfoImpl) cameraInfo).getCamera2CameraInfo();
        }
        CameraInfoInternal implementation = ((CameraInfoInternal) cameraInfo).getImplementation();
        Preconditions.checkArgument(implementation instanceof Camera2CameraInfoImpl, "CameraInfo doesn't contain Camera2 implementation.");
        return ((Camera2CameraInfoImpl) implementation).getCamera2CameraInfo();
    }

    public String getCameraId() {
        Camera2PhysicalCameraInfoImpl camera2PhysicalCameraInfoImpl = this.mCamera2PhysicalCameraInfo;
        if (camera2PhysicalCameraInfoImpl != null) {
            return camera2PhysicalCameraInfoImpl.getCameraId();
        }
        return this.mCamera2CameraInfoImpl.getCameraId();
    }

    public <T> T getCameraCharacteristic(CameraCharacteristics.Key<T> key) {
        Camera2PhysicalCameraInfoImpl camera2PhysicalCameraInfoImpl = this.mCamera2PhysicalCameraInfo;
        if (camera2PhysicalCameraInfoImpl != null) {
            return camera2PhysicalCameraInfoImpl.getCameraCharacteristicsCompat().get(key);
        }
        return this.mCamera2CameraInfoImpl.getCameraCharacteristicsCompat().get(key);
    }

    public static CameraCharacteristics extractCameraCharacteristics(CameraInfo cameraInfo) {
        if (cameraInfo instanceof Camera2PhysicalCameraInfoImpl) {
            return ((Camera2PhysicalCameraInfoImpl) cameraInfo).getCameraCharacteristicsCompat().toCameraCharacteristics();
        }
        CameraInfoInternal implementation = ((CameraInfoInternal) cameraInfo).getImplementation();
        Preconditions.checkState(implementation instanceof Camera2CameraInfoImpl, "CameraInfo does not contain any Camera2 information.");
        return ((Camera2CameraInfoImpl) implementation).getCameraCharacteristicsCompat().toCameraCharacteristics();
    }

    public Map<String, CameraCharacteristics> getCameraCharacteristicsMap() {
        if (this.mCamera2PhysicalCameraInfo != null) {
            return Collections.emptyMap();
        }
        return this.mCamera2CameraInfoImpl.getCameraCharacteristicsMap();
    }
}
