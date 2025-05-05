package androidx.camera.core.internal.compat.quirk;

import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.Quirks;

public interface SurfaceProcessingQuirk extends Quirk {
    boolean workaroundBySurfaceProcessing() {
        return true;
    }

    static boolean workaroundBySurfaceProcessing(Quirks quirks) {
        for (SurfaceProcessingQuirk workaroundBySurfaceProcessing : quirks.getAll(SurfaceProcessingQuirk.class)) {
            if (workaroundBySurfaceProcessing.workaroundBySurfaceProcessing()) {
                return true;
            }
        }
        return false;
    }
}
