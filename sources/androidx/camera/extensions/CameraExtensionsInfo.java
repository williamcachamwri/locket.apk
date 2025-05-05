package androidx.camera.extensions;

import androidx.lifecycle.LiveData;

public interface CameraExtensionsInfo {
    LiveData<Integer> getCurrentExtensionMode() {
        return null;
    }

    LiveData<Integer> getExtensionStrength() {
        return null;
    }

    boolean isCurrentExtensionModeAvailable() {
        return false;
    }

    boolean isExtensionStrengthAvailable() {
        return false;
    }
}
