package androidx.camera.core.internal.compat;

import android.os.Build;

public final class MediaActionSoundCompat {
    public static boolean mustPlayShutterSound() {
        if (Build.VERSION.SDK_INT >= 33) {
            return MediaActionSoundCompatApi33Impl.mustPlayShutterSound();
        }
        return MediaActionSoundCompatBaseImpl.mustPlayShutterSound();
    }

    private MediaActionSoundCompat() {
    }
}
