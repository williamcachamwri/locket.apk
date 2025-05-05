package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.imagecapture.DngImage2Disk;

final class AutoValue_DngImage2Disk_In extends DngImage2Disk.In {
    private final ImageProxy imageProxy;
    private final ImageCapture.OutputFileOptions outputFileOptions;
    private final int rotationDegrees;

    AutoValue_DngImage2Disk_In(ImageProxy imageProxy2, int i, ImageCapture.OutputFileOptions outputFileOptions2) {
        if (imageProxy2 != null) {
            this.imageProxy = imageProxy2;
            this.rotationDegrees = i;
            if (outputFileOptions2 != null) {
                this.outputFileOptions = outputFileOptions2;
                return;
            }
            throw new NullPointerException("Null outputFileOptions");
        }
        throw new NullPointerException("Null imageProxy");
    }

    /* access modifiers changed from: package-private */
    public ImageProxy getImageProxy() {
        return this.imageProxy;
    }

    /* access modifiers changed from: package-private */
    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    /* access modifiers changed from: package-private */
    public ImageCapture.OutputFileOptions getOutputFileOptions() {
        return this.outputFileOptions;
    }

    public String toString() {
        return "In{imageProxy=" + this.imageProxy + ", rotationDegrees=" + this.rotationDegrees + ", outputFileOptions=" + this.outputFileOptions + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DngImage2Disk.In)) {
            return false;
        }
        DngImage2Disk.In in = (DngImage2Disk.In) obj;
        if (!this.imageProxy.equals(in.getImageProxy()) || this.rotationDegrees != in.getRotationDegrees() || !this.outputFileOptions.equals(in.getOutputFileOptions())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((this.imageProxy.hashCode() ^ 1000003) * 1000003) ^ this.rotationDegrees) * 1000003) ^ this.outputFileOptions.hashCode();
    }
}
