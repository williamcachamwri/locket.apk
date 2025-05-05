package androidx.camera.camera2.internal.compat.params;

import android.hardware.camera2.params.OutputConfiguration;
import android.view.Surface;
import androidx.core.util.Preconditions;
import java.util.Objects;

class OutputConfigurationCompatApi28Impl extends OutputConfigurationCompatApi26Impl {
    public String getPhysicalCameraId() {
        return null;
    }

    OutputConfigurationCompatApi28Impl(Surface surface) {
        this((Object) new OutputConfigurationParamsApi28(new OutputConfiguration(surface)));
    }

    OutputConfigurationCompatApi28Impl(int i, Surface surface) {
        this((Object) new OutputConfigurationParamsApi28(new OutputConfiguration(i, surface)));
    }

    OutputConfigurationCompatApi28Impl(Object obj) {
        super(obj);
    }

    static OutputConfigurationCompatApi28Impl wrap(OutputConfiguration outputConfiguration) {
        return new OutputConfigurationCompatApi28Impl((Object) new OutputConfigurationParamsApi28(outputConfiguration));
    }

    public void removeSurface(Surface surface) {
        ((OutputConfiguration) getOutputConfiguration()).removeSurface(surface);
    }

    public int getMaxSharedSurfaceCount() {
        return ((OutputConfiguration) getOutputConfiguration()).getMaxSharedSurfaceCount();
    }

    public void setPhysicalCameraId(String str) {
        ((OutputConfiguration) getOutputConfiguration()).setPhysicalCameraId(str);
    }

    public long getDynamicRangeProfile() {
        return ((OutputConfigurationParamsApi28) this.mObject).mDynamicRangeProfile;
    }

    public void setDynamicRangeProfile(long j) {
        ((OutputConfigurationParamsApi28) this.mObject).mDynamicRangeProfile = j;
    }

    public Object getOutputConfiguration() {
        Preconditions.checkArgument(this.mObject instanceof OutputConfigurationParamsApi28);
        return ((OutputConfigurationParamsApi28) this.mObject).mOutputConfiguration;
    }

    private static final class OutputConfigurationParamsApi28 {
        long mDynamicRangeProfile = 1;
        final OutputConfiguration mOutputConfiguration;

        OutputConfigurationParamsApi28(OutputConfiguration outputConfiguration) {
            this.mOutputConfiguration = outputConfiguration;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof OutputConfigurationParamsApi28)) {
                return false;
            }
            OutputConfigurationParamsApi28 outputConfigurationParamsApi28 = (OutputConfigurationParamsApi28) obj;
            if (!Objects.equals(this.mOutputConfiguration, outputConfigurationParamsApi28.mOutputConfiguration) || this.mDynamicRangeProfile != outputConfigurationParamsApi28.mDynamicRangeProfile) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int hashCode = this.mOutputConfiguration.hashCode() ^ 31;
            return Long.hashCode(this.mDynamicRangeProfile) ^ ((hashCode << 5) - hashCode);
        }
    }
}
