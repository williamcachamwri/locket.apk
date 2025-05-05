package androidx.camera.core;

import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.impl.CameraInternal;

final class AutoValue_SurfaceOutput_CameraInputInfo extends SurfaceOutput.CameraInputInfo {
    private final CameraInternal cameraInternal;
    private final Rect inputCropRect;
    private final Size inputSize;
    private final boolean mirroring;
    private final int rotationDegrees;

    AutoValue_SurfaceOutput_CameraInputInfo(Size size, Rect rect, CameraInternal cameraInternal2, int i, boolean z) {
        if (size != null) {
            this.inputSize = size;
            if (rect != null) {
                this.inputCropRect = rect;
                this.cameraInternal = cameraInternal2;
                this.rotationDegrees = i;
                this.mirroring = z;
                return;
            }
            throw new NullPointerException("Null inputCropRect");
        }
        throw new NullPointerException("Null inputSize");
    }

    public Size getInputSize() {
        return this.inputSize;
    }

    public Rect getInputCropRect() {
        return this.inputCropRect;
    }

    public CameraInternal getCameraInternal() {
        return this.cameraInternal;
    }

    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    public boolean getMirroring() {
        return this.mirroring;
    }

    public String toString() {
        return "CameraInputInfo{inputSize=" + this.inputSize + ", inputCropRect=" + this.inputCropRect + ", cameraInternal=" + this.cameraInternal + ", rotationDegrees=" + this.rotationDegrees + ", mirroring=" + this.mirroring + "}";
    }

    public boolean equals(Object obj) {
        CameraInternal cameraInternal2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SurfaceOutput.CameraInputInfo)) {
            return false;
        }
        SurfaceOutput.CameraInputInfo cameraInputInfo = (SurfaceOutput.CameraInputInfo) obj;
        if (!this.inputSize.equals(cameraInputInfo.getInputSize()) || !this.inputCropRect.equals(cameraInputInfo.getInputCropRect()) || ((cameraInternal2 = this.cameraInternal) != null ? !cameraInternal2.equals(cameraInputInfo.getCameraInternal()) : cameraInputInfo.getCameraInternal() != null) || this.rotationDegrees != cameraInputInfo.getRotationDegrees() || this.mirroring != cameraInputInfo.getMirroring()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((this.inputSize.hashCode() ^ 1000003) * 1000003) ^ this.inputCropRect.hashCode()) * 1000003;
        CameraInternal cameraInternal2 = this.cameraInternal;
        return ((((hashCode ^ (cameraInternal2 == null ? 0 : cameraInternal2.hashCode())) * 1000003) ^ this.rotationDegrees) * 1000003) ^ (this.mirroring ? 1231 : 1237);
    }
}
