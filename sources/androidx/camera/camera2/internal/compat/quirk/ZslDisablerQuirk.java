package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ZslDisablerQuirk implements Quirk {
    private static final List<String> AFFECTED_SAMSUNG_MODEL = Arrays.asList(new String[]{"SM-F936", "SM-S901U", "SM-S908U", "SM-S908U1", "SM-F721U1", "SM-S928U1"});
    private static final List<String> AFFECTED_XIAOMI_MODEL = Arrays.asList(new String[]{"MI 8"});

    static boolean load() {
        return isAffectedSamsungDevices() || isAffectedXiaoMiDevices();
    }

    private static boolean isAffectedSamsungDevices() {
        return "samsung".equalsIgnoreCase(Build.BRAND) && isAffectedModel(AFFECTED_SAMSUNG_MODEL);
    }

    private static boolean isAffectedXiaoMiDevices() {
        return "xiaomi".equalsIgnoreCase(Build.BRAND) && isAffectedModel(AFFECTED_XIAOMI_MODEL);
    }

    private static boolean isAffectedModel(List<String> list) {
        for (String startsWith : list) {
            if (Build.MODEL.toUpperCase(Locale.US).startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }
}
