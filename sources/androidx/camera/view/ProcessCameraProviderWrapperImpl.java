package androidx.camera.view;

import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.UseCase;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;

class ProcessCameraProviderWrapperImpl implements ProcessCameraProviderWrapper {
    private final ProcessCameraProvider mProcessCameraProvider;

    ProcessCameraProviderWrapperImpl(ProcessCameraProvider processCameraProvider) {
        this.mProcessCameraProvider = processCameraProvider;
    }

    public boolean hasCamera(CameraSelector cameraSelector) throws CameraInfoUnavailableException {
        return this.mProcessCameraProvider.hasCamera(cameraSelector);
    }

    public void unbind(UseCase... useCaseArr) {
        this.mProcessCameraProvider.unbind(useCaseArr);
    }

    public void unbindAll() {
        this.mProcessCameraProvider.unbindAll();
    }

    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCaseGroup useCaseGroup) {
        return this.mProcessCameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, useCaseGroup);
    }

    public ListenableFuture<Void> shutdownAsync() {
        return this.mProcessCameraProvider.shutdownAsync();
    }

    public CameraInfo getCameraInfo(CameraSelector cameraSelector) {
        return this.mProcessCameraProvider.getCameraInfo(cameraSelector);
    }
}
