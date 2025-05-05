package androidx.camera.core.internal.compat;

import android.media.MediaActionSound;

class MediaActionSoundCompatApi33Impl {
    static boolean mustPlayShutterSound() {
        return MediaActionSound.mustPlayShutterSound();
    }

    private MediaActionSoundCompatApi33Impl() {
    }
}
