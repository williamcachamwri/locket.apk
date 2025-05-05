package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.ApiCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Camera2CaptureCallbacks {
    private Camera2CaptureCallbacks() {
    }

    public static CameraCaptureSession.CaptureCallback createNoOpCallback() {
        return new NoOpSessionCaptureCallback();
    }

    static CameraCaptureSession.CaptureCallback createComboCallback(List<CameraCaptureSession.CaptureCallback> list) {
        return new ComboSessionCaptureCallback(list);
    }

    public static CameraCaptureSession.CaptureCallback createComboCallback(CameraCaptureSession.CaptureCallback... captureCallbackArr) {
        return createComboCallback((List<CameraCaptureSession.CaptureCallback>) Arrays.asList(captureCallbackArr));
    }

    static final class NoOpSessionCaptureCallback extends CameraCaptureSession.CaptureCallback {
        public void onCaptureBufferLost(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, Surface surface, long j) {
        }

        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
        }

        public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
        }

        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
        }

        public void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
        }

        public void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i, long j) {
        }

        public void onCaptureStarted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j, long j2) {
        }

        NoOpSessionCaptureCallback() {
        }
    }

    private static final class ComboSessionCaptureCallback extends CameraCaptureSession.CaptureCallback {
        private final List<CameraCaptureSession.CaptureCallback> mCallbacks = new ArrayList();

        ComboSessionCaptureCallback(List<CameraCaptureSession.CaptureCallback> list) {
            for (CameraCaptureSession.CaptureCallback next : list) {
                if (!(next instanceof NoOpSessionCaptureCallback)) {
                    this.mCallbacks.add(next);
                }
            }
        }

        public void onCaptureBufferLost(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, Surface surface, long j) {
            for (CameraCaptureSession.CaptureCallback onCaptureBufferLost : this.mCallbacks) {
                ApiCompat.Api24Impl.onCaptureBufferLost(onCaptureBufferLost, cameraCaptureSession, captureRequest, surface, j);
            }
        }

        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            for (CameraCaptureSession.CaptureCallback onCaptureCompleted : this.mCallbacks) {
                onCaptureCompleted.onCaptureCompleted(cameraCaptureSession, captureRequest, totalCaptureResult);
            }
        }

        public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            for (CameraCaptureSession.CaptureCallback onCaptureFailed : this.mCallbacks) {
                onCaptureFailed.onCaptureFailed(cameraCaptureSession, captureRequest, captureFailure);
            }
        }

        public void onCaptureProgressed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
            for (CameraCaptureSession.CaptureCallback onCaptureProgressed : this.mCallbacks) {
                onCaptureProgressed.onCaptureProgressed(cameraCaptureSession, captureRequest, captureResult);
            }
        }

        public void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
            for (CameraCaptureSession.CaptureCallback onCaptureSequenceAborted : this.mCallbacks) {
                onCaptureSequenceAborted.onCaptureSequenceAborted(cameraCaptureSession, i);
            }
        }

        public void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i, long j) {
            for (CameraCaptureSession.CaptureCallback onCaptureSequenceCompleted : this.mCallbacks) {
                onCaptureSequenceCompleted.onCaptureSequenceCompleted(cameraCaptureSession, i, j);
            }
        }

        public void onCaptureStarted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j, long j2) {
            for (CameraCaptureSession.CaptureCallback onCaptureStarted : this.mCallbacks) {
                onCaptureStarted.onCaptureStarted(cameraCaptureSession, captureRequest, j, j2);
            }
        }
    }
}
