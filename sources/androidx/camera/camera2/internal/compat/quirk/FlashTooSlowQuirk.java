package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FlashTooSlowQuirk implements UseTorchAsFlashQuirk {
    private static final List<String> AFFECTED_MODEL_PREFIXES = Arrays.asList(new String[]{"PIXEL 3A", "PIXEL 3A XL", "PIXEL 4", "PIXEL 5", "SM-A320", "MOTO G(20)", "ITEL L6006", "RMX3231"});

    static boolean load(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        return isAffectedModel() && ((Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.LENS_FACING)).intValue() == 1;
    }

    private static boolean isAffectedModel() {
        for (String startsWith : AFFECTED_MODEL_PREFIXES) {
            if (Build.MODEL.toUpperCase(Locale.US).startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }
}
