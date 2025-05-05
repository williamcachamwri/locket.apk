package androidx.camera.extensions.internal.sessionprocessor;

import android.util.Size;
import java.util.List;

final class AutoValue_ImageReaderOutputConfig extends ImageReaderOutputConfig {
    private final int id;
    private final int imageFormat;
    private final int maxImages;
    private final String physicalCameraId;
    private final Size size;
    private final int surfaceGroupId;
    private final List<Camera2OutputConfig> surfaceSharingOutputConfigs;

    AutoValue_ImageReaderOutputConfig(int i, int i2, String str, List<Camera2OutputConfig> list, Size size2, int i3, int i4) {
        this.id = i;
        this.surfaceGroupId = i2;
        this.physicalCameraId = str;
        if (list != null) {
            this.surfaceSharingOutputConfigs = list;
            if (size2 != null) {
                this.size = size2;
                this.imageFormat = i3;
                this.maxImages = i4;
                return;
            }
            throw new NullPointerException("Null size");
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
    public Size getSize() {
        return this.size;
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
        return "ImageReaderOutputConfig{id=" + this.id + ", surfaceGroupId=" + this.surfaceGroupId + ", physicalCameraId=" + this.physicalCameraId + ", surfaceSharingOutputConfigs=" + this.surfaceSharingOutputConfigs + ", size=" + this.size + ", imageFormat=" + this.imageFormat + ", maxImages=" + this.maxImages + "}";
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImageReaderOutputConfig)) {
            return false;
        }
        ImageReaderOutputConfig imageReaderOutputConfig = (ImageReaderOutputConfig) obj;
        if (this.id == imageReaderOutputConfig.getId() && this.surfaceGroupId == imageReaderOutputConfig.getSurfaceGroupId() && ((str = this.physicalCameraId) != null ? str.equals(imageReaderOutputConfig.getPhysicalCameraId()) : imageReaderOutputConfig.getPhysicalCameraId() == null) && this.surfaceSharingOutputConfigs.equals(imageReaderOutputConfig.getSurfaceSharingOutputConfigs()) && this.size.equals(imageReaderOutputConfig.getSize()) && this.imageFormat == imageReaderOutputConfig.getImageFormat() && this.maxImages == imageReaderOutputConfig.getMaxImages()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = (((this.id ^ 1000003) * 1000003) ^ this.surfaceGroupId) * 1000003;
        String str = this.physicalCameraId;
        return ((((((((i ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.surfaceSharingOutputConfigs.hashCode()) * 1000003) ^ this.size.hashCode()) * 1000003) ^ this.imageFormat) * 1000003) ^ this.maxImages;
    }
}
