package androidx.camera.core;

import android.util.Range;
import androidx.camera.core.impl.DynamicRanges;
import androidx.camera.core.internal.compat.MediaActionSoundCompat;
import androidx.lifecycle.LiveData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.Set;

public interface CameraInfo {
    public static final String IMPLEMENTATION_TYPE_CAMERA2 = "androidx.camera.camera2";
    public static final String IMPLEMENTATION_TYPE_CAMERA2_LEGACY = "androidx.camera.camera2.legacy";
    public static final String IMPLEMENTATION_TYPE_FAKE = "androidx.camera.fake";
    public static final String IMPLEMENTATION_TYPE_UNKNOWN = "<unknown>";
    public static final float INTRINSIC_ZOOM_RATIO_UNKNOWN = 1.0f;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ImplementationType {
    }

    CameraSelector getCameraSelector();

    LiveData<CameraState> getCameraState();

    ExposureState getExposureState();

    String getImplementationType();

    float getIntrinsicZoomRatio() {
        return 1.0f;
    }

    int getLensFacing() {
        return -1;
    }

    int getSensorRotationDegrees();

    int getSensorRotationDegrees(int i);

    LiveData<Integer> getTorchState();

    LiveData<ZoomState> getZoomState();

    boolean hasFlashUnit();

    boolean isFocusMeteringSupported(FocusMeteringAction focusMeteringAction) {
        return false;
    }

    boolean isLogicalMultiCameraSupported() {
        return false;
    }

    boolean isPrivateReprocessingSupported() {
        return false;
    }

    boolean isZslSupported() {
        return false;
    }

    static boolean mustPlayShutterSound() {
        return MediaActionSoundCompat.mustPlayShutterSound();
    }

    Set<Range<Integer>> getSupportedFrameRateRanges() {
        return Collections.emptySet();
    }

    Set<DynamicRange> querySupportedDynamicRanges(Set<DynamicRange> set) {
        return DynamicRanges.findAllPossibleMatches(set, Collections.singleton(DynamicRange.SDR));
    }

    Set<CameraInfo> getPhysicalCameraInfos() {
        return Collections.emptySet();
    }
}
