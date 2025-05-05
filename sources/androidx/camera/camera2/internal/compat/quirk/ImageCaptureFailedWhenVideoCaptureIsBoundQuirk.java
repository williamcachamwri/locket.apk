package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.internal.compat.quirk.SurfaceProcessingQuirk;

public class ImageCaptureFailedWhenVideoCaptureIsBoundQuirk implements CaptureIntentPreviewQuirk, SurfaceProcessingQuirk {
    static boolean load() {
        return isBluStudioX10() || isItelW6004() || isVivo1805() || isPositivoTwist2Pro() || isPixel4XLApi29() || isMotoE13() || isSamsungTabA8();
    }

    public static boolean isBluStudioX10() {
        return "blu".equalsIgnoreCase(Build.BRAND) && "studio x10".equalsIgnoreCase(Build.MODEL);
    }

    public static boolean isItelW6004() {
        return "itel".equalsIgnoreCase(Build.BRAND) && "itel w6004".equalsIgnoreCase(Build.MODEL);
    }

    public static boolean isVivo1805() {
        return "vivo".equalsIgnoreCase(Build.BRAND) && "vivo 1805".equalsIgnoreCase(Build.MODEL);
    }

    public static boolean isPositivoTwist2Pro() {
        return "positivo".equalsIgnoreCase(Build.BRAND) && "twist 2 pro".equalsIgnoreCase(Build.MODEL);
    }

    private static boolean isPixel4XLApi29() {
        return "pixel 4 xl".equalsIgnoreCase(Build.MODEL) && Build.VERSION.SDK_INT == 29;
    }

    public static boolean isMotoE13() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto e13".equalsIgnoreCase(Build.MODEL);
    }

    public static boolean isSamsungTabA8() {
        return "samsung".equalsIgnoreCase(Build.BRAND) && ("gta8".equalsIgnoreCase(Build.DEVICE) || "gta8wifi".equalsIgnoreCase(Build.DEVICE));
    }

    public boolean workaroundByCaptureIntentPreview() {
        return isBluStudioX10() || isItelW6004() || isVivo1805() || isPositivoTwist2Pro();
    }

    public boolean workaroundBySurfaceProcessing() {
        return isBluStudioX10() || isItelW6004() || isVivo1805() || isPositivoTwist2Pro() || isPixel4XLApi29() || isMotoE13() || isSamsungTabA8();
    }
}
