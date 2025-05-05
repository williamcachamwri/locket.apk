package androidx.camera.camera2.internal.compat.params;

import android.hardware.camera2.params.OutputConfiguration;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

class OutputConfigurationCompatApi26Impl extends OutputConfigurationCompatApi24Impl {
    private static final String MAX_SHARED_SURFACES_COUNT_FIELD = "MAX_SURFACES_COUNT";
    private static final String SURFACES_FIELD = "mSurfaces";

    OutputConfigurationCompatApi26Impl(Surface surface) {
        this((Object) new OutputConfigurationParamsApi26(new OutputConfiguration(surface)));
    }

    OutputConfigurationCompatApi26Impl(int i, Surface surface) {
        this((Object) new OutputConfigurationParamsApi26(new OutputConfiguration(i, surface)));
    }

    OutputConfigurationCompatApi26Impl(Object obj) {
        super(obj);
    }

    static OutputConfigurationCompatApi26Impl wrap(OutputConfiguration outputConfiguration) {
        return new OutputConfigurationCompatApi26Impl((Object) new OutputConfigurationParamsApi26(outputConfiguration));
    }

    private static int getMaxSharedSurfaceCountApi26() throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = OutputConfiguration.class.getDeclaredField(MAX_SHARED_SURFACES_COUNT_FIELD);
        declaredField.setAccessible(true);
        return declaredField.getInt((Object) null);
    }

    private static List<Surface> getMutableSurfaceListApi26(OutputConfiguration outputConfiguration) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = OutputConfiguration.class.getDeclaredField(SURFACES_FIELD);
        declaredField.setAccessible(true);
        return (List) declaredField.get(outputConfiguration);
    }

    public void enableSurfaceSharing() {
        ((OutputConfiguration) getOutputConfiguration()).enableSurfaceSharing();
    }

    /* access modifiers changed from: package-private */
    public final boolean isSurfaceSharingEnabled() {
        throw new AssertionError("isSurfaceSharingEnabled() should not be called on API >= 26");
    }

    public void addSurface(Surface surface) {
        ((OutputConfiguration) getOutputConfiguration()).addSurface(surface);
    }

    public void setPhysicalCameraId(String str) {
        ((OutputConfigurationParamsApi26) this.mObject).mPhysicalCameraId = str;
    }

    public String getPhysicalCameraId() {
        return ((OutputConfigurationParamsApi26) this.mObject).mPhysicalCameraId;
    }

    public long getDynamicRangeProfile() {
        return ((OutputConfigurationParamsApi26) this.mObject).mDynamicRangeProfile;
    }

    public void setDynamicRangeProfile(long j) {
        ((OutputConfigurationParamsApi26) this.mObject).mDynamicRangeProfile = j;
    }

    public void removeSurface(Surface surface) {
        if (getSurface() != surface) {
            try {
                if (!getMutableSurfaceListApi26((OutputConfiguration) getOutputConfiguration()).remove(surface)) {
                    throw new IllegalArgumentException("Surface is not part of this output configuration");
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                Logger.e("OutputConfigCompat", "Unable to remove surface from this output configuration.", e);
            }
        } else {
            throw new IllegalArgumentException("Cannot remove surface associated with this output configuration");
        }
    }

    public int getMaxSharedSurfaceCount() {
        try {
            return getMaxSharedSurfaceCountApi26();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Logger.e("OutputConfigCompat", "Unable to retrieve max shared surface count.", e);
            return super.getMaxSharedSurfaceCount();
        }
    }

    public List<Surface> getSurfaces() {
        return ((OutputConfiguration) getOutputConfiguration()).getSurfaces();
    }

    public Object getOutputConfiguration() {
        Preconditions.checkArgument(this.mObject instanceof OutputConfigurationParamsApi26);
        return ((OutputConfigurationParamsApi26) this.mObject).mOutputConfiguration;
    }

    private static final class OutputConfigurationParamsApi26 {
        long mDynamicRangeProfile = 1;
        final OutputConfiguration mOutputConfiguration;
        String mPhysicalCameraId;

        OutputConfigurationParamsApi26(OutputConfiguration outputConfiguration) {
            this.mOutputConfiguration = outputConfiguration;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof OutputConfigurationParamsApi26)) {
                return false;
            }
            OutputConfigurationParamsApi26 outputConfigurationParamsApi26 = (OutputConfigurationParamsApi26) obj;
            if (!Objects.equals(this.mOutputConfiguration, outputConfigurationParamsApi26.mOutputConfiguration) || this.mDynamicRangeProfile != outputConfigurationParamsApi26.mDynamicRangeProfile || !Objects.equals(this.mPhysicalCameraId, outputConfigurationParamsApi26.mPhysicalCameraId)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int hashCode = this.mOutputConfiguration.hashCode() ^ 31;
            int i = (hashCode << 5) - hashCode;
            String str = this.mPhysicalCameraId;
            int hashCode2 = (str == null ? 0 : str.hashCode()) ^ i;
            return Long.hashCode(this.mDynamicRangeProfile) ^ ((hashCode2 << 5) - hashCode2);
        }
    }
}
