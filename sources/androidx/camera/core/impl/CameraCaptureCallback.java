package androidx.camera.core.impl;

public abstract class CameraCaptureCallback {
    public void onCaptureCancelled(int i) {
    }

    public void onCaptureCompleted(int i, CameraCaptureResult cameraCaptureResult) {
    }

    public void onCaptureFailed(int i, CameraCaptureFailure cameraCaptureFailure) {
    }

    public void onCaptureProcessProgressed(int i, int i2) {
    }

    public void onCaptureStarted(int i) {
    }
}
