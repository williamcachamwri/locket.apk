package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ImageCaptureFailedForVideoSnapshotQuirk implements Quirk {
    private static final Set<String> PROBLEMATIC_UNI_SOC_MODELS = new HashSet(Arrays.asList(new String[]{"itel l6006", "itel w6004", "moto g(20)", "moto e13", "moto e20", "rmx3231", "rmx3511", "sm-a032f", "sm-a035m", "sm-f946u1", "tecno mobile bf6"}));

    static boolean load() {
        return isUniSocChipsetDevice() || isHuaweiPSmart();
    }

    private static boolean isUniSocChipsetDevice() {
        return PROBLEMATIC_UNI_SOC_MODELS.contains(Build.MODEL.toLowerCase(Locale.US)) || (Build.VERSION.SDK_INT >= 31 && "Spreadtrum".equalsIgnoreCase(Build.SOC_MANUFACTURER)) || Build.HARDWARE.toLowerCase(Locale.US).startsWith("ums") || ("itel".equalsIgnoreCase(Build.BRAND) && Build.HARDWARE.toLowerCase(Locale.US).startsWith("sp"));
    }

    private static boolean isHuaweiPSmart() {
        return "HUAWEI".equalsIgnoreCase(Build.BRAND) && "FIG-LX1".equalsIgnoreCase(Build.MODEL);
    }
}
