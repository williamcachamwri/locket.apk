package androidx.camera.lifecycle;

import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.camera.lifecycle.LifecycleCameraRepository;
import androidx.lifecycle.LifecycleOwner;

final class AutoValue_LifecycleCameraRepository_Key extends LifecycleCameraRepository.Key {
    private final CameraUseCaseAdapter.CameraId cameraId;
    private final LifecycleOwner lifecycleOwner;

    AutoValue_LifecycleCameraRepository_Key(LifecycleOwner lifecycleOwner2, CameraUseCaseAdapter.CameraId cameraId2) {
        if (lifecycleOwner2 != null) {
            this.lifecycleOwner = lifecycleOwner2;
            if (cameraId2 != null) {
                this.cameraId = cameraId2;
                return;
            }
            throw new NullPointerException("Null cameraId");
        }
        throw new NullPointerException("Null lifecycleOwner");
    }

    public LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public CameraUseCaseAdapter.CameraId getCameraId() {
        return this.cameraId;
    }

    public String toString() {
        return "Key{lifecycleOwner=" + this.lifecycleOwner + ", cameraId=" + this.cameraId + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LifecycleCameraRepository.Key)) {
            return false;
        }
        LifecycleCameraRepository.Key key = (LifecycleCameraRepository.Key) obj;
        if (!this.lifecycleOwner.equals(key.getLifecycleOwner()) || !this.cameraId.equals(key.getCameraId())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.lifecycleOwner.hashCode() ^ 1000003) * 1000003) ^ this.cameraId.hashCode();
    }
}
