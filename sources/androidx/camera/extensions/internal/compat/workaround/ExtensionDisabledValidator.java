package androidx.camera.extensions.internal.compat.workaround;

import androidx.camera.extensions.internal.compat.quirk.DeviceQuirks;
import androidx.camera.extensions.internal.compat.quirk.ExtensionDisabledQuirk;

public class ExtensionDisabledValidator {
    private final ExtensionDisabledQuirk mQuirk = ((ExtensionDisabledQuirk) DeviceQuirks.get(ExtensionDisabledQuirk.class));

    public boolean shouldDisableExtension(String str) {
        ExtensionDisabledQuirk extensionDisabledQuirk = this.mQuirk;
        return extensionDisabledQuirk != null && extensionDisabledQuirk.shouldDisableExtension(str);
    }
}
