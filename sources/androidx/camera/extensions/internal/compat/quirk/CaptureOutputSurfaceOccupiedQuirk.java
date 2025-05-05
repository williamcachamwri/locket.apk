package androidx.camera.extensions.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;

public class CaptureOutputSurfaceOccupiedQuirk implements Quirk {
    static boolean load() {
        return Build.BRAND.equalsIgnoreCase("Xiaomi");
    }
}
