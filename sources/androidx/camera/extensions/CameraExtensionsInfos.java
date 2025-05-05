package androidx.camera.extensions;

import androidx.camera.core.CameraInfo;
import androidx.camera.core.impl.RestrictedCameraInfo;
import androidx.camera.core.impl.SessionProcessor;
import androidx.core.util.Preconditions;

class CameraExtensionsInfos {
    private static final CameraExtensionsInfo NORMAL_MODE_CAMERA_EXTENSIONS_INFO = new CameraExtensionsInfo() {
    };

    static CameraExtensionsInfo from(CameraInfo cameraInfo) {
        Preconditions.checkArgument(cameraInfo instanceof RestrictedCameraInfo, "The input camera info must be an instance retrieved from the camera that is returned by invoking CameraProvider#bindToLifecycle() with an extension enabled camera selector.");
        SessionProcessor sessionProcessor = ((RestrictedCameraInfo) cameraInfo).getSessionProcessor();
        if (sessionProcessor instanceof CameraExtensionsInfo) {
            return (CameraExtensionsInfo) sessionProcessor;
        }
        return NORMAL_MODE_CAMERA_EXTENSIONS_INFO;
    }

    private CameraExtensionsInfos() {
    }
}
