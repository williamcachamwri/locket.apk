package androidx.camera.camera2.internal.compat.quirk;

import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.Quirks;

public interface CaptureIntentPreviewQuirk extends Quirk {
    boolean workaroundByCaptureIntentPreview() {
        return true;
    }

    static boolean workaroundByCaptureIntentPreview(Quirks quirks) {
        for (CaptureIntentPreviewQuirk workaroundByCaptureIntentPreview : quirks.getAll(CaptureIntentPreviewQuirk.class)) {
            if (workaroundByCaptureIntentPreview.workaroundByCaptureIntentPreview()) {
                return true;
            }
        }
        return false;
    }
}
