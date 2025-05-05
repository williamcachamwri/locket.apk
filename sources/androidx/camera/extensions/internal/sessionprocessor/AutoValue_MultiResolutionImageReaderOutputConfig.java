package androidx.camera.extensions.internal.sessionprocessor;

import java.util.List;

final class AutoValue_MultiResolutionImageReaderOutputConfig extends MultiResolutionImageReaderOutputConfig {
    private final int id;
    private final int imageFormat;
    private final int maxImages;
    private final String physicalCameraId;
    private final int surfaceGroupId;
    private final List<Camera2OutputConfig> surfaceSharingOutputConfigs;

    AutoValue_MultiResolutionImageReaderOutputConfig(int i, int i2, String str, List<Camera2OutputConfig> list, int i3, int i4) {
        this.id = i;
        this.surfaceGroupId = i2;
        this.physicalCameraId = str;
        if (list != null) {
            this.surfaceSharingOutputConfigs = list;
            this.imageFormat = i3;
            this.maxImages = i4;
            return;
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
    public int getImageFormat() {
        return this.imageFormat;
    }

    /* access modifiers changed from: package-private */
    public int getMaxImages() {
        return this.maxImages;
    }

    public String toString() {
        return "MultiResolutionImageReaderOutputConfig{id=" + this.id + ", surfaceGroupId=" + this.surfaceGroupId + ", physicalCameraId=" + this.physicalCameraId + ", surfaceSharingOutputConfigs=" + this.surfaceSharingOutputConfigs + ", imageFormat=" + this.imageFormat + ", maxImages=" + this.maxImages + "}";
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultiResolutionImageReaderOutputConfig)) {
            return false;
        }
        MultiResolutionImageReaderOutputConfig multiResolutionImageReaderOutputConfig = (MultiResolutionImageReaderOutputConfig) obj;
        if (this.id == multiResolutionImageReaderOutputConfig.getId() && this.surfaceGroupId == multiResolutionImageReaderOutputConfig.getSurfaceGroupId() && ((str = this.physicalCameraId) != null ? str.equals(multiResolutionImageReaderOutputConfig.getPhysicalCameraId()) : multiResolutionImageReaderOutputConfig.getPhysicalCameraId() == null) && this.surfaceSharingOutputConfigs.equals(multiResolutionImageReaderOutputConfig.getSurfaceSharingOutputConfigs()) && this.imageFormat == multiResolutionImageReaderOutputConfig.getImageFormat() && this.maxImages == multiResolutionImageReaderOutputConfig.getMaxImages()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = (((this.id ^ 1000003) * 1000003) ^ this.surfaceGroupId) * 1000003;
        String str = this.physicalCameraId;
        return ((((((i ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.surfaceSharingOutputConfigs.hashCode()) * 1000003) ^ this.imageFormat) * 1000003) ^ this.maxImages;
    }
}
