package androidx.camera.core.impl;

import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.SessionConfig;
import java.util.List;

final class AutoValue_SessionConfig_OutputConfig extends SessionConfig.OutputConfig {
    private final DynamicRange dynamicRange;
    private final int mirrorMode;
    private final String physicalCameraId;
    private final List<DeferrableSurface> sharedSurfaces;
    private final DeferrableSurface surface;
    private final int surfaceGroupId;

    private AutoValue_SessionConfig_OutputConfig(DeferrableSurface deferrableSurface, List<DeferrableSurface> list, String str, int i, int i2, DynamicRange dynamicRange2) {
        this.surface = deferrableSurface;
        this.sharedSurfaces = list;
        this.physicalCameraId = str;
        this.mirrorMode = i;
        this.surfaceGroupId = i2;
        this.dynamicRange = dynamicRange2;
    }

    public DeferrableSurface getSurface() {
        return this.surface;
    }

    public List<DeferrableSurface> getSharedSurfaces() {
        return this.sharedSurfaces;
    }

    public String getPhysicalCameraId() {
        return this.physicalCameraId;
    }

    public int getMirrorMode() {
        return this.mirrorMode;
    }

    public int getSurfaceGroupId() {
        return this.surfaceGroupId;
    }

    public DynamicRange getDynamicRange() {
        return this.dynamicRange;
    }

    public String toString() {
        return "OutputConfig{surface=" + this.surface + ", sharedSurfaces=" + this.sharedSurfaces + ", physicalCameraId=" + this.physicalCameraId + ", mirrorMode=" + this.mirrorMode + ", surfaceGroupId=" + this.surfaceGroupId + ", dynamicRange=" + this.dynamicRange + "}";
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SessionConfig.OutputConfig)) {
            return false;
        }
        SessionConfig.OutputConfig outputConfig = (SessionConfig.OutputConfig) obj;
        if (!this.surface.equals(outputConfig.getSurface()) || !this.sharedSurfaces.equals(outputConfig.getSharedSurfaces()) || ((str = this.physicalCameraId) != null ? !str.equals(outputConfig.getPhysicalCameraId()) : outputConfig.getPhysicalCameraId() != null) || this.mirrorMode != outputConfig.getMirrorMode() || this.surfaceGroupId != outputConfig.getSurfaceGroupId() || !this.dynamicRange.equals(outputConfig.getDynamicRange())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((this.surface.hashCode() ^ 1000003) * 1000003) ^ this.sharedSurfaces.hashCode()) * 1000003;
        String str = this.physicalCameraId;
        return ((((((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.mirrorMode) * 1000003) ^ this.surfaceGroupId) * 1000003) ^ this.dynamicRange.hashCode();
    }

    static final class Builder extends SessionConfig.OutputConfig.Builder {
        private DynamicRange dynamicRange;
        private Integer mirrorMode;
        private String physicalCameraId;
        private List<DeferrableSurface> sharedSurfaces;
        private DeferrableSurface surface;
        private Integer surfaceGroupId;

        Builder() {
        }

        public SessionConfig.OutputConfig.Builder setSurface(DeferrableSurface deferrableSurface) {
            if (deferrableSurface != null) {
                this.surface = deferrableSurface;
                return this;
            }
            throw new NullPointerException("Null surface");
        }

        public SessionConfig.OutputConfig.Builder setSharedSurfaces(List<DeferrableSurface> list) {
            if (list != null) {
                this.sharedSurfaces = list;
                return this;
            }
            throw new NullPointerException("Null sharedSurfaces");
        }

        public SessionConfig.OutputConfig.Builder setPhysicalCameraId(String str) {
            this.physicalCameraId = str;
            return this;
        }

        public SessionConfig.OutputConfig.Builder setMirrorMode(int i) {
            this.mirrorMode = Integer.valueOf(i);
            return this;
        }

        public SessionConfig.OutputConfig.Builder setSurfaceGroupId(int i) {
            this.surfaceGroupId = Integer.valueOf(i);
            return this;
        }

        public SessionConfig.OutputConfig.Builder setDynamicRange(DynamicRange dynamicRange2) {
            if (dynamicRange2 != null) {
                this.dynamicRange = dynamicRange2;
                return this;
            }
            throw new NullPointerException("Null dynamicRange");
        }

        public SessionConfig.OutputConfig build() {
            String str = this.surface == null ? " surface" : "";
            if (this.sharedSurfaces == null) {
                str = str + " sharedSurfaces";
            }
            if (this.mirrorMode == null) {
                str = str + " mirrorMode";
            }
            if (this.surfaceGroupId == null) {
                str = str + " surfaceGroupId";
            }
            if (this.dynamicRange == null) {
                str = str + " dynamicRange";
            }
            if (str.isEmpty()) {
                return new AutoValue_SessionConfig_OutputConfig(this.surface, this.sharedSurfaces, this.physicalCameraId, this.mirrorMode.intValue(), this.surfaceGroupId.intValue(), this.dynamicRange);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
