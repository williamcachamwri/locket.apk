package androidx.camera.core;

import androidx.camera.core.impl.CameraConfig;

public interface Camera {
    CameraControl getCameraControl();

    CameraInfo getCameraInfo();

    CameraConfig getExtendedConfig();

    boolean isUseCasesCombinationSupported(boolean z, UseCase... useCaseArr) {
        return true;
    }

    boolean isUseCasesCombinationSupported(UseCase... useCaseArr) {
        return isUseCasesCombinationSupported(true, useCaseArr);
    }

    boolean isUseCasesCombinationSupportedByFramework(UseCase... useCaseArr) {
        return isUseCasesCombinationSupported(false, useCaseArr);
    }
}
