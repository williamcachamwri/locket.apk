package androidx.camera.camera2.internal.compat.workaround;

import androidx.camera.camera2.internal.compat.quirk.CaptureSessionStuckWhenCreatingBeforeClosingCameraQuirk;
import androidx.camera.camera2.internal.compat.quirk.LegacyCameraOutputConfigNullPointerQuirk;
import androidx.camera.core.impl.Quirks;

public class CloseCameraBeforeCreateNewSession {
    public static boolean shouldCloseCamera(Quirks quirks) {
        return quirks.contains(LegacyCameraOutputConfigNullPointerQuirk.class) || quirks.contains(CaptureSessionStuckWhenCreatingBeforeClosingCameraQuirk.class);
    }

    private CloseCameraBeforeCreateNewSession() {
    }
}
