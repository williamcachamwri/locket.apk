package androidx.camera.core.imagecapture;

import android.graphics.Matrix;
import android.graphics.Rect;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.impl.CameraCaptureCallback;
import java.util.List;
import java.util.concurrent.Executor;

final class AutoValue_TakePictureRequest extends TakePictureRequest {
    private final Executor appExecutor;
    private final int captureMode;
    private final Rect cropRect;
    private final ImageCapture.OnImageCapturedCallback inMemoryCallback;
    private final int jpegQuality;
    private final ImageCapture.OnImageSavedCallback onDiskCallback;
    private final ImageCapture.OutputFileOptions outputFileOptions;
    private final int rotationDegrees;
    private final ImageCapture.OutputFileOptions secondaryOutputFileOptions;
    private final Matrix sensorToBufferTransform;
    private final List<CameraCaptureCallback> sessionConfigCameraCaptureCallbacks;
    private final boolean simultaneousCapture;

    AutoValue_TakePictureRequest(Executor executor, ImageCapture.OnImageCapturedCallback onImageCapturedCallback, ImageCapture.OnImageSavedCallback onImageSavedCallback, ImageCapture.OutputFileOptions outputFileOptions2, ImageCapture.OutputFileOptions outputFileOptions3, Rect rect, Matrix matrix, int i, int i2, int i3, boolean z, List<CameraCaptureCallback> list) {
        if (executor != null) {
            this.appExecutor = executor;
            this.inMemoryCallback = onImageCapturedCallback;
            this.onDiskCallback = onImageSavedCallback;
            this.outputFileOptions = outputFileOptions2;
            this.secondaryOutputFileOptions = outputFileOptions3;
            if (rect != null) {
                this.cropRect = rect;
                if (matrix != null) {
                    this.sensorToBufferTransform = matrix;
                    this.rotationDegrees = i;
                    this.jpegQuality = i2;
                    this.captureMode = i3;
                    this.simultaneousCapture = z;
                    if (list != null) {
                        this.sessionConfigCameraCaptureCallbacks = list;
                        return;
                    }
                    throw new NullPointerException("Null sessionConfigCameraCaptureCallbacks");
                }
                throw new NullPointerException("Null sensorToBufferTransform");
            }
            throw new NullPointerException("Null cropRect");
        }
        throw new NullPointerException("Null appExecutor");
    }

    /* access modifiers changed from: package-private */
    public Executor getAppExecutor() {
        return this.appExecutor;
    }

    public ImageCapture.OnImageCapturedCallback getInMemoryCallback() {
        return this.inMemoryCallback;
    }

    public ImageCapture.OnImageSavedCallback getOnDiskCallback() {
        return this.onDiskCallback;
    }

    public ImageCapture.OutputFileOptions getOutputFileOptions() {
        return this.outputFileOptions;
    }

    public ImageCapture.OutputFileOptions getSecondaryOutputFileOptions() {
        return this.secondaryOutputFileOptions;
    }

    public Rect getCropRect() {
        return this.cropRect;
    }

    /* access modifiers changed from: package-private */
    public Matrix getSensorToBufferTransform() {
        return this.sensorToBufferTransform;
    }

    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    public int getJpegQuality() {
        return this.jpegQuality;
    }

    /* access modifiers changed from: package-private */
    public int getCaptureMode() {
        return this.captureMode;
    }

    /* access modifiers changed from: package-private */
    public boolean isSimultaneousCapture() {
        return this.simultaneousCapture;
    }

    /* access modifiers changed from: package-private */
    public List<CameraCaptureCallback> getSessionConfigCameraCaptureCallbacks() {
        return this.sessionConfigCameraCaptureCallbacks;
    }

    public String toString() {
        return "TakePictureRequest{appExecutor=" + this.appExecutor + ", inMemoryCallback=" + this.inMemoryCallback + ", onDiskCallback=" + this.onDiskCallback + ", outputFileOptions=" + this.outputFileOptions + ", secondaryOutputFileOptions=" + this.secondaryOutputFileOptions + ", cropRect=" + this.cropRect + ", sensorToBufferTransform=" + this.sensorToBufferTransform + ", rotationDegrees=" + this.rotationDegrees + ", jpegQuality=" + this.jpegQuality + ", captureMode=" + this.captureMode + ", simultaneousCapture=" + this.simultaneousCapture + ", sessionConfigCameraCaptureCallbacks=" + this.sessionConfigCameraCaptureCallbacks + "}";
    }

    public boolean equals(Object obj) {
        ImageCapture.OnImageCapturedCallback onImageCapturedCallback;
        ImageCapture.OnImageSavedCallback onImageSavedCallback;
        ImageCapture.OutputFileOptions outputFileOptions2;
        ImageCapture.OutputFileOptions outputFileOptions3;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TakePictureRequest)) {
            return false;
        }
        TakePictureRequest takePictureRequest = (TakePictureRequest) obj;
        if (!this.appExecutor.equals(takePictureRequest.getAppExecutor()) || ((onImageCapturedCallback = this.inMemoryCallback) != null ? !onImageCapturedCallback.equals(takePictureRequest.getInMemoryCallback()) : takePictureRequest.getInMemoryCallback() != null) || ((onImageSavedCallback = this.onDiskCallback) != null ? !onImageSavedCallback.equals(takePictureRequest.getOnDiskCallback()) : takePictureRequest.getOnDiskCallback() != null) || ((outputFileOptions2 = this.outputFileOptions) != null ? !outputFileOptions2.equals(takePictureRequest.getOutputFileOptions()) : takePictureRequest.getOutputFileOptions() != null) || ((outputFileOptions3 = this.secondaryOutputFileOptions) != null ? !outputFileOptions3.equals(takePictureRequest.getSecondaryOutputFileOptions()) : takePictureRequest.getSecondaryOutputFileOptions() != null) || !this.cropRect.equals(takePictureRequest.getCropRect()) || !this.sensorToBufferTransform.equals(takePictureRequest.getSensorToBufferTransform()) || this.rotationDegrees != takePictureRequest.getRotationDegrees() || this.jpegQuality != takePictureRequest.getJpegQuality() || this.captureMode != takePictureRequest.getCaptureMode() || this.simultaneousCapture != takePictureRequest.isSimultaneousCapture() || !this.sessionConfigCameraCaptureCallbacks.equals(takePictureRequest.getSessionConfigCameraCaptureCallbacks())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (this.appExecutor.hashCode() ^ 1000003) * 1000003;
        ImageCapture.OnImageCapturedCallback onImageCapturedCallback = this.inMemoryCallback;
        int i = 0;
        int hashCode2 = (hashCode ^ (onImageCapturedCallback == null ? 0 : onImageCapturedCallback.hashCode())) * 1000003;
        ImageCapture.OnImageSavedCallback onImageSavedCallback = this.onDiskCallback;
        int hashCode3 = (hashCode2 ^ (onImageSavedCallback == null ? 0 : onImageSavedCallback.hashCode())) * 1000003;
        ImageCapture.OutputFileOptions outputFileOptions2 = this.outputFileOptions;
        int hashCode4 = (hashCode3 ^ (outputFileOptions2 == null ? 0 : outputFileOptions2.hashCode())) * 1000003;
        ImageCapture.OutputFileOptions outputFileOptions3 = this.secondaryOutputFileOptions;
        if (outputFileOptions3 != null) {
            i = outputFileOptions3.hashCode();
        }
        return ((((((((((((((hashCode4 ^ i) * 1000003) ^ this.cropRect.hashCode()) * 1000003) ^ this.sensorToBufferTransform.hashCode()) * 1000003) ^ this.rotationDegrees) * 1000003) ^ this.jpegQuality) * 1000003) ^ this.captureMode) * 1000003) ^ (this.simultaneousCapture ? 1231 : 1237)) * 1000003) ^ this.sessionConfigCameraCaptureCallbacks.hashCode();
    }
}
