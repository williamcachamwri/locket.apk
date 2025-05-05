package androidx.camera.core.internal.compat.workaround;

import androidx.camera.core.UseCase;
import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.ImageCaptureFailedForSpecificCombinationQuirk;
import androidx.camera.core.internal.compat.quirk.PreviewGreenTintQuirk;
import java.util.Collection;

public class StreamSharingForceEnabler {
    private final PreviewGreenTintQuirk mPreviewGreenTintQuirk = ((PreviewGreenTintQuirk) DeviceQuirks.get(PreviewGreenTintQuirk.class));
    private final ImageCaptureFailedForSpecificCombinationQuirk mSpecificCombinationQuirk = ((ImageCaptureFailedForSpecificCombinationQuirk) DeviceQuirks.get(ImageCaptureFailedForSpecificCombinationQuirk.class));

    public boolean shouldForceEnableStreamSharing(String str, Collection<UseCase> collection) {
        ImageCaptureFailedForSpecificCombinationQuirk imageCaptureFailedForSpecificCombinationQuirk = this.mSpecificCombinationQuirk;
        if (imageCaptureFailedForSpecificCombinationQuirk != null) {
            return imageCaptureFailedForSpecificCombinationQuirk.shouldForceEnableStreamSharing(str, collection);
        }
        if (this.mPreviewGreenTintQuirk != null) {
            return PreviewGreenTintQuirk.shouldForceEnableStreamSharing(str, collection);
        }
        return false;
    }
}
