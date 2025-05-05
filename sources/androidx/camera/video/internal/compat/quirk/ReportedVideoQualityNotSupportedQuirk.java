package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.internal.compat.quirk.SurfaceProcessingQuirk;
import androidx.camera.video.Quality;
import java.util.Arrays;
import java.util.Locale;

public class ReportedVideoQualityNotSupportedQuirk implements VideoQualityQuirk, SurfaceProcessingQuirk {
    static boolean load() {
        return isHuaweiMate20() || isHuaweiMate20Pro() || isVivoY91i() || isHuaweiP40Lite() || isOppoPht110();
    }

    private static boolean isHuaweiMate20() {
        return "Huawei".equalsIgnoreCase(Build.BRAND) && "HMA-L29".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean isHuaweiMate20Pro() {
        return "Huawei".equalsIgnoreCase(Build.BRAND) && "LYA-AL00".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean isVivoY91i() {
        return "Vivo".equalsIgnoreCase(Build.BRAND) && "vivo 1820".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean isHuaweiP40Lite() {
        return "Huawei".equalsIgnoreCase(Build.MANUFACTURER) && Arrays.asList(new String[]{"JNY-L21A", "JNY-L01A", "JNY-L21B", "JNY-L22A", "JNY-L02A", "JNY-L22B", "JNY-LX1"}).contains(Build.MODEL.toUpperCase(Locale.US));
    }

    private static boolean isOppoPht110() {
        return "OPPO".equalsIgnoreCase(Build.BRAND) && "PHT110".equalsIgnoreCase(Build.MODEL);
    }

    public boolean isProblematicVideoQuality(CameraInfoInternal cameraInfoInternal, Quality quality) {
        if (isHuaweiMate20() || isHuaweiMate20Pro()) {
            if (quality == Quality.UHD) {
                return true;
            }
            return false;
        } else if (isVivoY91i()) {
            if (quality == Quality.HD || quality == Quality.FHD) {
                return true;
            }
            return false;
        } else if (isHuaweiP40Lite()) {
            if (cameraInfoInternal.getLensFacing() == 0 && (quality == Quality.FHD || quality == Quality.HD)) {
                return true;
            }
            return false;
        } else if (!isOppoPht110()) {
            return false;
        } else {
            if (cameraInfoInternal.getLensFacing() == 1 && quality == Quality.UHD) {
                return true;
            }
            return false;
        }
    }

    public boolean workaroundBySurfaceProcessing() {
        return isHuaweiMate20() || isHuaweiMate20Pro() || isHuaweiP40Lite() || isOppoPht110();
    }
}
