package androidx.camera.core.impl;

import android.util.Pair;
import android.util.Size;
import androidx.camera.core.CameraInfo;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SessionProcessor {

    public interface CaptureCallback {
        void onCaptureCompleted(long j, int i, CameraCaptureResult cameraCaptureResult) {
        }

        void onCaptureFailed(int i) {
        }

        void onCaptureProcessProgressed(int i) {
        }

        void onCaptureProcessStarted(int i) {
        }

        void onCaptureSequenceAborted(int i) {
        }

        void onCaptureSequenceCompleted(int i) {
        }

        void onCaptureStarted(int i, long j) {
        }
    }

    void abortCapture(int i);

    void deInitSession();

    Pair<Long, Long> getRealtimeCaptureLatency() {
        return null;
    }

    SessionConfig initSession(CameraInfo cameraInfo, OutputSurfaceConfiguration outputSurfaceConfiguration);

    void onCaptureSessionEnd();

    void onCaptureSessionStart(RequestProcessor requestProcessor);

    void setParameters(Config config);

    int startCapture(boolean z, TagBundle tagBundle, CaptureCallback captureCallback);

    int startRepeating(TagBundle tagBundle, CaptureCallback captureCallback);

    int startTrigger(Config config, TagBundle tagBundle, CaptureCallback captureCallback) {
        return -1;
    }

    void stopRepeating();

    Map<Integer, List<Size>> getSupportedPostviewSize(Size size) {
        return Collections.emptyMap();
    }

    Set<Integer> getSupportedCameraOperations() {
        return Collections.emptySet();
    }
}
