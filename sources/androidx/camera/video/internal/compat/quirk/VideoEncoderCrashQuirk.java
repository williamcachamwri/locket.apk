package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.video.Quality;

public class VideoEncoderCrashQuirk implements VideoQualityQuirk {
    static boolean load() {
        return isPositivoTwist2Pro();
    }

    private static boolean isPositivoTwist2Pro() {
        return "positivo".equalsIgnoreCase(Build.BRAND) && "twist 2 pro".equalsIgnoreCase(Build.MODEL);
    }

    public boolean isProblematicVideoQuality(CameraInfoInternal cameraInfoInternal, Quality quality) {
        if (isPositivoTwist2Pro() && cameraInfoInternal.getLensFacing() == 0 && quality == Quality.SD) {
            return true;
        }
        return false;
    }
}
