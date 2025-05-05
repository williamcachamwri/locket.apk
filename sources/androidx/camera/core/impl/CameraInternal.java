package androidx.camera.core.impl;

import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.UseCase;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;

public interface CameraInternal extends Camera, UseCase.StateChangeCallback {
    void attachUseCases(Collection<UseCase> collection);

    void close();

    void detachUseCases(Collection<UseCase> collection);

    CameraControlInternal getCameraControlInternal();

    CameraInfoInternal getCameraInfoInternal();

    Observable<State> getCameraState();

    boolean getHasTransform() {
        return true;
    }

    void open();

    ListenableFuture<Void> release();

    void setActiveResumingMode(boolean z) {
    }

    void setExtendedConfig(CameraConfig cameraConfig) {
    }

    void setPrimary(boolean z) {
    }

    public enum State {
        RELEASED(false),
        RELEASING(true),
        CLOSED(false),
        PENDING_OPEN(false),
        CLOSING(true),
        OPENING(true),
        OPEN(true),
        CONFIGURED(true);
        
        private final boolean mHoldsCameraSlot;

        private State(boolean z) {
            this.mHoldsCameraSlot = z;
        }

        /* access modifiers changed from: package-private */
        public boolean holdsCameraSlot() {
            return this.mHoldsCameraSlot;
        }
    }

    boolean isFrontFacing() {
        return getCameraInfo().getLensFacing() == 0;
    }

    CameraControl getCameraControl() {
        return getCameraControlInternal();
    }

    CameraInfo getCameraInfo() {
        return getCameraInfoInternal();
    }

    CameraConfig getExtendedConfig() {
        return CameraConfigs.defaultConfig();
    }
}
