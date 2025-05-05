package androidx.camera.extensions.internal;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureResult;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.impl.SessionProcessor;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface VendorExtender {
    SessionProcessor createSessionProcessor(Context context) {
        return null;
    }

    Range<Long> getEstimatedCaptureLatencyRange(Size size) {
        return null;
    }

    Size[] getSupportedYuvAnalysisResolutions() {
        return new Size[0];
    }

    void init(CameraInfo cameraInfo) {
    }

    boolean isCaptureProcessProgressAvailable() {
        return false;
    }

    boolean isCurrentExtensionModeAvailable() {
        return false;
    }

    boolean isExtensionAvailable(String str, Map<String, CameraCharacteristics> map) {
        return false;
    }

    boolean isExtensionStrengthAvailable() {
        return false;
    }

    boolean isPostviewAvailable() {
        return false;
    }

    List<Pair<Integer, Size[]>> getSupportedPreviewOutputResolutions() {
        return Collections.emptyList();
    }

    List<Pair<Integer, Size[]>> getSupportedCaptureOutputResolutions() {
        return Collections.emptyList();
    }

    Map<Integer, List<Size>> getSupportedPostviewResolutions(Size size) {
        return Collections.emptyMap();
    }

    List<CaptureResult.Key> getSupportedCaptureResultKeys() {
        return Collections.emptyList();
    }

    boolean willReceiveOnCaptureCompleted() {
        if (ClientVersion.isMaximumCompatibleVersion(Version.VERSION_1_2) || ExtensionVersion.isMaximumCompatibleVersion(Version.VERSION_1_2)) {
            return false;
        }
        return !getSupportedCaptureResultKeys().isEmpty();
    }
}
