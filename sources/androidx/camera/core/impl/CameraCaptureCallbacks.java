package androidx.camera.core.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CameraCaptureCallbacks {
    private CameraCaptureCallbacks() {
    }

    public static CameraCaptureCallback createNoOpCallback() {
        return new NoOpCameraCaptureCallback();
    }

    static CameraCaptureCallback createComboCallback(List<CameraCaptureCallback> list) {
        if (list.isEmpty()) {
            return createNoOpCallback();
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return new ComboCameraCaptureCallback(list);
    }

    public static CameraCaptureCallback createComboCallback(CameraCaptureCallback... cameraCaptureCallbackArr) {
        return createComboCallback((List<CameraCaptureCallback>) Arrays.asList(cameraCaptureCallbackArr));
    }

    static final class NoOpCameraCaptureCallback extends CameraCaptureCallback {
        public void onCaptureCompleted(int i, CameraCaptureResult cameraCaptureResult) {
        }

        public void onCaptureFailed(int i, CameraCaptureFailure cameraCaptureFailure) {
        }

        public void onCaptureStarted(int i) {
        }

        NoOpCameraCaptureCallback() {
        }
    }

    public static final class ComboCameraCaptureCallback extends CameraCaptureCallback {
        private final List<CameraCaptureCallback> mCallbacks = new ArrayList();

        ComboCameraCaptureCallback(List<CameraCaptureCallback> list) {
            for (CameraCaptureCallback next : list) {
                if (!(next instanceof NoOpCameraCaptureCallback)) {
                    this.mCallbacks.add(next);
                }
            }
        }

        public void onCaptureStarted(int i) {
            for (CameraCaptureCallback onCaptureStarted : this.mCallbacks) {
                onCaptureStarted.onCaptureStarted(i);
            }
        }

        public void onCaptureCompleted(int i, CameraCaptureResult cameraCaptureResult) {
            for (CameraCaptureCallback onCaptureCompleted : this.mCallbacks) {
                onCaptureCompleted.onCaptureCompleted(i, cameraCaptureResult);
            }
        }

        public void onCaptureFailed(int i, CameraCaptureFailure cameraCaptureFailure) {
            for (CameraCaptureCallback onCaptureFailed : this.mCallbacks) {
                onCaptureFailed.onCaptureFailed(i, cameraCaptureFailure);
            }
        }

        public void onCaptureCancelled(int i) {
            for (CameraCaptureCallback onCaptureCancelled : this.mCallbacks) {
                onCaptureCancelled.onCaptureCancelled(i);
            }
        }

        public List<CameraCaptureCallback> getCallbacks() {
            return this.mCallbacks;
        }

        public void onCaptureProcessProgressed(int i, int i2) {
            for (CameraCaptureCallback onCaptureProcessProgressed : this.mCallbacks) {
                onCaptureProcessProgressed.onCaptureProcessProgressed(i, i2);
            }
        }
    }
}
