package androidx.camera.extensions.internal.sessionprocessor;

import android.view.Surface;
import java.util.List;

final class AutoValue_SurfaceOutputConfig extends SurfaceOutputConfig {
    private final int id;
    private final String physicalCameraId;
    private final Surface surface;
    private final int surfaceGroupId;
    private final List<Camera2OutputConfig> surfaceSharingOutputConfigs;

    AutoValue_SurfaceOutputConfig(int i, int i2, String str, List<Camera2OutputConfig> list, Surface surface2) {
        this.id = i;
        this.surfaceGroupId = i2;
        this.physicalCameraId = str;
        if (list != null) {
            this.surfaceSharingOutputConfigs = list;
            if (surface2 != null) {
                this.surface = surface2;
                return;
            }
            throw new NullPointerException("Null surface");
        }
        throw new NullPointerException("Null surfaceSharingOutputConfigs");
    }

    public int getId() {
        return this.id;
    }

    public int getSurfaceGroupId() {
        return this.surfaceGroupId;
    }

    public String getPhysicalCameraId() {
        return this.physicalCameraId;
    }

    public List<Camera2OutputConfig> getSurfaceSharingOutputConfigs() {
        return this.surfaceSharingOutputConfigs;
    }

    /* access modifiers changed from: package-private */
    public Surface getSurface() {
        return this.surface;
    }

    public String toString() {
        return "SurfaceOutputConfig{id=" + this.id + ", surfaceGroupId=" + this.surfaceGroupId + ", physicalCameraId=" + this.physicalCameraId + ", surfaceSharingOutputConfigs=" + this.surfaceSharingOutputConfigs + ", surface=" + this.surface + "}";
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SurfaceOutputConfig)) {
            return false;
        }
        SurfaceOutputConfig surfaceOutputConfig = (SurfaceOutputConfig) obj;
        if (this.id != surfaceOutputConfig.getId() || this.surfaceGroupId != surfaceOutputConfig.getSurfaceGroupId() || ((str = this.physicalCameraId) != null ? !str.equals(surfaceOutputConfig.getPhysicalCameraId()) : surfaceOutputConfig.getPhysicalCameraId() != null) || !this.surfaceSharingOutputConfigs.equals(surfaceOutputConfig.getSurfaceSharingOutputConfigs()) || !this.surface.equals(surfaceOutputConfig.getSurface())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = (((this.id ^ 1000003) * 1000003) ^ this.surfaceGroupId) * 1000003;
        String str = this.physicalCameraId;
        return ((((i ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.surfaceSharingOutputConfigs.hashCode()) * 1000003) ^ this.surface.hashCode();
    }
}
