package androidx.camera.core.internal;

import androidx.camera.core.impl.Identifier;
import androidx.camera.core.internal.CameraUseCaseAdapter;

final class AutoValue_CameraUseCaseAdapter_CameraId extends CameraUseCaseAdapter.CameraId {
    private final Identifier cameraConfigId;
    private final String cameraIdString;

    AutoValue_CameraUseCaseAdapter_CameraId(String str, Identifier identifier) {
        if (str != null) {
            this.cameraIdString = str;
            if (identifier != null) {
                this.cameraConfigId = identifier;
                return;
            }
            throw new NullPointerException("Null cameraConfigId");
        }
        throw new NullPointerException("Null cameraIdString");
    }

    public String getCameraIdString() {
        return this.cameraIdString;
    }

    public Identifier getCameraConfigId() {
        return this.cameraConfigId;
    }

    public String toString() {
        return "CameraId{cameraIdString=" + this.cameraIdString + ", cameraConfigId=" + this.cameraConfigId + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CameraUseCaseAdapter.CameraId)) {
            return false;
        }
        CameraUseCaseAdapter.CameraId cameraId = (CameraUseCaseAdapter.CameraId) obj;
        if (!this.cameraIdString.equals(cameraId.getCameraIdString()) || !this.cameraConfigId.equals(cameraId.getCameraConfigId())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.cameraIdString.hashCode() ^ 1000003) * 1000003) ^ this.cameraConfigId.hashCode();
    }
}
