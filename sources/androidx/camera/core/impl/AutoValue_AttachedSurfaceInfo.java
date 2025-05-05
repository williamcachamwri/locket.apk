package androidx.camera.core.impl;

import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.UseCaseConfigFactory;
import java.util.List;

final class AutoValue_AttachedSurfaceInfo extends AttachedSurfaceInfo {
    private final List<UseCaseConfigFactory.CaptureType> captureTypes;
    private final DynamicRange dynamicRange;
    private final int imageFormat;
    private final Config implementationOptions;
    private final Size size;
    private final SurfaceConfig surfaceConfig;
    private final Range<Integer> targetFrameRate;

    AutoValue_AttachedSurfaceInfo(SurfaceConfig surfaceConfig2, int i, Size size2, DynamicRange dynamicRange2, List<UseCaseConfigFactory.CaptureType> list, Config config, Range<Integer> range) {
        if (surfaceConfig2 != null) {
            this.surfaceConfig = surfaceConfig2;
            this.imageFormat = i;
            if (size2 != null) {
                this.size = size2;
                if (dynamicRange2 != null) {
                    this.dynamicRange = dynamicRange2;
                    if (list != null) {
                        this.captureTypes = list;
                        this.implementationOptions = config;
                        this.targetFrameRate = range;
                        return;
                    }
                    throw new NullPointerException("Null captureTypes");
                }
                throw new NullPointerException("Null dynamicRange");
            }
            throw new NullPointerException("Null size");
        }
        throw new NullPointerException("Null surfaceConfig");
    }

    public SurfaceConfig getSurfaceConfig() {
        return this.surfaceConfig;
    }

    public int getImageFormat() {
        return this.imageFormat;
    }

    public Size getSize() {
        return this.size;
    }

    public DynamicRange getDynamicRange() {
        return this.dynamicRange;
    }

    public List<UseCaseConfigFactory.CaptureType> getCaptureTypes() {
        return this.captureTypes;
    }

    public Config getImplementationOptions() {
        return this.implementationOptions;
    }

    public Range<Integer> getTargetFrameRate() {
        return this.targetFrameRate;
    }

    public String toString() {
        return "AttachedSurfaceInfo{surfaceConfig=" + this.surfaceConfig + ", imageFormat=" + this.imageFormat + ", size=" + this.size + ", dynamicRange=" + this.dynamicRange + ", captureTypes=" + this.captureTypes + ", implementationOptions=" + this.implementationOptions + ", targetFrameRate=" + this.targetFrameRate + "}";
    }

    public boolean equals(Object obj) {
        Config config;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AttachedSurfaceInfo)) {
            return false;
        }
        AttachedSurfaceInfo attachedSurfaceInfo = (AttachedSurfaceInfo) obj;
        if (this.surfaceConfig.equals(attachedSurfaceInfo.getSurfaceConfig()) && this.imageFormat == attachedSurfaceInfo.getImageFormat() && this.size.equals(attachedSurfaceInfo.getSize()) && this.dynamicRange.equals(attachedSurfaceInfo.getDynamicRange()) && this.captureTypes.equals(attachedSurfaceInfo.getCaptureTypes()) && ((config = this.implementationOptions) != null ? config.equals(attachedSurfaceInfo.getImplementationOptions()) : attachedSurfaceInfo.getImplementationOptions() == null)) {
            Range<Integer> range = this.targetFrameRate;
            if (range == null) {
                if (attachedSurfaceInfo.getTargetFrameRate() == null) {
                    return true;
                }
            } else if (range.equals(attachedSurfaceInfo.getTargetFrameRate())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((((this.surfaceConfig.hashCode() ^ 1000003) * 1000003) ^ this.imageFormat) * 1000003) ^ this.size.hashCode()) * 1000003) ^ this.dynamicRange.hashCode()) * 1000003) ^ this.captureTypes.hashCode()) * 1000003;
        Config config = this.implementationOptions;
        int i = 0;
        int hashCode2 = (hashCode ^ (config == null ? 0 : config.hashCode())) * 1000003;
        Range<Integer> range = this.targetFrameRate;
        if (range != null) {
            i = range.hashCode();
        }
        return hashCode2 ^ i;
    }
}
