package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.impl.Quirk;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TorchFlashRequiredFor3aUpdateQuirk implements Quirk {
    private static final List<String> AFFECTED_PIXEL_MODELS = Arrays.asList(new String[]{"PIXEL 6A", "PIXEL 6 PRO", "PIXEL 7", "PIXEL 7A", "PIXEL 7 PRO", "PIXEL 8", "PIXEL 8 PRO"});
    private final CameraCharacteristicsCompat mCameraCharacteristics;

    public TorchFlashRequiredFor3aUpdateQuirk(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        this.mCameraCharacteristics = cameraCharacteristicsCompat;
    }

    static boolean load(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        return isAffectedModel(cameraCharacteristicsCompat);
    }

    public boolean isFlashModeTorchRequired() {
        return !isExternalFlashAeModeSupported(this.mCameraCharacteristics);
    }

    private static boolean isAffectedModel(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        return isAffectedPixelModel() && isFrontCamera(cameraCharacteristicsCompat);
    }

    private static boolean isAffectedPixelModel() {
        for (String equals : AFFECTED_PIXEL_MODELS) {
            if (Build.MODEL.toUpperCase(Locale.US).equals(equals)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFrontCamera(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        return ((Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
    }

    private static boolean isExternalFlashAeModeSupported(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        if (Build.VERSION.SDK_INT >= 28 && Camera2CameraControlImpl.getSupportedAeMode(cameraCharacteristicsCompat, 5) == 5) {
            return true;
        }
        return false;
    }
}
