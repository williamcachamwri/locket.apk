package androidx.camera.camera2.internal.compat.params;

import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class OutputConfigurationCompatBaseImpl implements OutputConfigurationCompat.OutputConfigurationCompatImpl {
    static final String TAG = "OutputConfigCompat";
    final Object mObject;

    public int getMaxSharedSurfaceCount() {
        return 1;
    }

    public int getMirrorMode() {
        return 0;
    }

    public Object getOutputConfiguration() {
        return null;
    }

    public long getStreamUseCase() {
        return -1;
    }

    public int getSurfaceGroupId() {
        return -1;
    }

    public void setMirrorMode(int i) {
    }

    public void setStreamUseCase(long j) {
    }

    OutputConfigurationCompatBaseImpl(Surface surface) {
        this.mObject = new OutputConfigurationParamsApi21(surface);
    }

    OutputConfigurationCompatBaseImpl(Object obj) {
        this.mObject = obj;
    }

    public void enableSurfaceSharing() {
        ((OutputConfigurationParamsApi21) this.mObject).mIsShared = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isSurfaceSharingEnabled() {
        return ((OutputConfigurationParamsApi21) this.mObject).mIsShared;
    }

    public void setPhysicalCameraId(String str) {
        ((OutputConfigurationParamsApi21) this.mObject).mPhysicalCameraId = str;
    }

    public String getPhysicalCameraId() {
        return ((OutputConfigurationParamsApi21) this.mObject).mPhysicalCameraId;
    }

    public void addSurface(Surface surface) {
        Preconditions.checkNotNull(surface, "Surface must not be null");
        if (getSurface() == surface) {
            throw new IllegalStateException("Surface is already added!");
        } else if (!isSurfaceSharingEnabled()) {
            throw new IllegalStateException("Cannot have 2 surfaces for a non-sharing configuration");
        } else {
            throw new IllegalArgumentException("Exceeds maximum number of surfaces");
        }
    }

    public void removeSurface(Surface surface) {
        if (getSurface() == surface) {
            throw new IllegalArgumentException("Cannot remove surface associated with this output configuration");
        }
        throw new IllegalArgumentException("Surface is not part of this output configuration");
    }

    public long getDynamicRangeProfile() {
        return ((OutputConfigurationParamsApi21) this.mObject).mDynamicRangeProfile;
    }

    public void setDynamicRangeProfile(long j) {
        ((OutputConfigurationParamsApi21) this.mObject).mDynamicRangeProfile = j;
    }

    public Surface getSurface() {
        List<Surface> list = ((OutputConfigurationParamsApi21) this.mObject).mSurfaces;
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<Surface> getSurfaces() {
        return ((OutputConfigurationParamsApi21) this.mObject).mSurfaces;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof OutputConfigurationCompatBaseImpl)) {
            return false;
        }
        return Objects.equals(this.mObject, ((OutputConfigurationCompatBaseImpl) obj).mObject);
    }

    public int hashCode() {
        return this.mObject.hashCode();
    }

    private static final class OutputConfigurationParamsApi21 {
        private static final String DETECT_SURFACE_TYPE_METHOD = "detectSurfaceType";
        private static final String GET_GENERATION_ID_METHOD = "getGenerationId";
        private static final String GET_SURFACE_SIZE_METHOD = "getSurfaceSize";
        private static final String LEGACY_CAMERA_DEVICE_CLASS = "android.hardware.camera2.legacy.LegacyCameraDevice";
        static final int MAX_SURFACES_COUNT = 1;
        final int mConfiguredFormat;
        final int mConfiguredGenerationId;
        final Size mConfiguredSize;
        long mDynamicRangeProfile = 1;
        boolean mIsShared = false;
        String mPhysicalCameraId;
        final List<Surface> mSurfaces;

        OutputConfigurationParamsApi21(Surface surface) {
            Preconditions.checkNotNull(surface, "Surface must not be null");
            this.mSurfaces = Collections.singletonList(surface);
            this.mConfiguredSize = getSurfaceSize(surface);
            this.mConfiguredFormat = getSurfaceFormat(surface);
            this.mConfiguredGenerationId = getSurfaceGenerationId(surface);
        }

        private static Size getSurfaceSize(Surface surface) {
            try {
                Method declaredMethod = Class.forName(LEGACY_CAMERA_DEVICE_CLASS).getDeclaredMethod(GET_SURFACE_SIZE_METHOD, new Class[]{Surface.class});
                declaredMethod.setAccessible(true);
                return (Size) declaredMethod.invoke((Object) null, new Object[]{surface});
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Logger.e(OutputConfigurationCompatBaseImpl.TAG, "Unable to retrieve surface size.", e);
                return null;
            }
        }

        private static int getSurfaceFormat(Surface surface) {
            try {
                return ((Integer) Class.forName(LEGACY_CAMERA_DEVICE_CLASS).getDeclaredMethod(DETECT_SURFACE_TYPE_METHOD, new Class[]{Surface.class}).invoke((Object) null, new Object[]{surface})).intValue();
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Logger.e(OutputConfigurationCompatBaseImpl.TAG, "Unable to retrieve surface format.", e);
                return 0;
            }
        }

        private static int getSurfaceGenerationId(Surface surface) {
            try {
                return ((Integer) Surface.class.getDeclaredMethod(GET_GENERATION_ID_METHOD, new Class[0]).invoke(surface, new Object[0])).intValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Logger.e(OutputConfigurationCompatBaseImpl.TAG, "Unable to retrieve surface generation id.", e);
                return -1;
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof OutputConfigurationParamsApi21)) {
                return false;
            }
            OutputConfigurationParamsApi21 outputConfigurationParamsApi21 = (OutputConfigurationParamsApi21) obj;
            if (!this.mConfiguredSize.equals(outputConfigurationParamsApi21.mConfiguredSize) || this.mConfiguredFormat != outputConfigurationParamsApi21.mConfiguredFormat || this.mConfiguredGenerationId != outputConfigurationParamsApi21.mConfiguredGenerationId || this.mIsShared != outputConfigurationParamsApi21.mIsShared || this.mDynamicRangeProfile != outputConfigurationParamsApi21.mDynamicRangeProfile || !Objects.equals(this.mPhysicalCameraId, outputConfigurationParamsApi21.mPhysicalCameraId)) {
                return false;
            }
            int min = Math.min(this.mSurfaces.size(), outputConfigurationParamsApi21.mSurfaces.size());
            for (int i = 0; i < min; i++) {
                if (this.mSurfaces.get(i) != outputConfigurationParamsApi21.mSurfaces.get(i)) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int hashCode = this.mSurfaces.hashCode() ^ 31;
            int i = this.mConfiguredGenerationId ^ ((hashCode << 5) - hashCode);
            int hashCode2 = this.mConfiguredSize.hashCode() ^ ((i << 5) - i);
            int i2 = this.mConfiguredFormat ^ ((hashCode2 << 5) - hashCode2);
            boolean z = this.mIsShared ^ ((i2 << 5) - i2);
            int i3 = ((z ? 1 : 0) << true) - z;
            String str = this.mPhysicalCameraId;
            int hashCode3 = (str == null ? 0 : str.hashCode()) ^ i3;
            return Long.hashCode(this.mDynamicRangeProfile) ^ ((hashCode3 << 5) - hashCode3);
        }
    }
}
