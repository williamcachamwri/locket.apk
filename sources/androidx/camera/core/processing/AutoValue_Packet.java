package androidx.camera.core.processing;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.utils.Exif;

final class AutoValue_Packet<T> extends Packet<T> {
    private final CameraCaptureResult cameraCaptureResult;
    private final Rect cropRect;
    private final T data;
    private final Exif exif;
    private final int format;
    private final int rotationDegrees;
    private final Matrix sensorToBufferTransform;
    private final Size size;

    AutoValue_Packet(T t, Exif exif2, int i, Size size2, Rect rect, int i2, Matrix matrix, CameraCaptureResult cameraCaptureResult2) {
        if (t != null) {
            this.data = t;
            this.exif = exif2;
            this.format = i;
            if (size2 != null) {
                this.size = size2;
                if (rect != null) {
                    this.cropRect = rect;
                    this.rotationDegrees = i2;
                    if (matrix != null) {
                        this.sensorToBufferTransform = matrix;
                        if (cameraCaptureResult2 != null) {
                            this.cameraCaptureResult = cameraCaptureResult2;
                            return;
                        }
                        throw new NullPointerException("Null cameraCaptureResult");
                    }
                    throw new NullPointerException("Null sensorToBufferTransform");
                }
                throw new NullPointerException("Null cropRect");
            }
            throw new NullPointerException("Null size");
        }
        throw new NullPointerException("Null data");
    }

    public T getData() {
        return this.data;
    }

    public Exif getExif() {
        return this.exif;
    }

    public int getFormat() {
        return this.format;
    }

    public Size getSize() {
        return this.size;
    }

    public Rect getCropRect() {
        return this.cropRect;
    }

    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    public Matrix getSensorToBufferTransform() {
        return this.sensorToBufferTransform;
    }

    public CameraCaptureResult getCameraCaptureResult() {
        return this.cameraCaptureResult;
    }

    public String toString() {
        return "Packet{data=" + this.data + ", exif=" + this.exif + ", format=" + this.format + ", size=" + this.size + ", cropRect=" + this.cropRect + ", rotationDegrees=" + this.rotationDegrees + ", sensorToBufferTransform=" + this.sensorToBufferTransform + ", cameraCaptureResult=" + this.cameraCaptureResult + "}";
    }

    public boolean equals(Object obj) {
        Exif exif2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Packet)) {
            return false;
        }
        Packet packet = (Packet) obj;
        if (!this.data.equals(packet.getData()) || ((exif2 = this.exif) != null ? !exif2.equals(packet.getExif()) : packet.getExif() != null) || this.format != packet.getFormat() || !this.size.equals(packet.getSize()) || !this.cropRect.equals(packet.getCropRect()) || this.rotationDegrees != packet.getRotationDegrees() || !this.sensorToBufferTransform.equals(packet.getSensorToBufferTransform()) || !this.cameraCaptureResult.equals(packet.getCameraCaptureResult())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (this.data.hashCode() ^ 1000003) * 1000003;
        Exif exif2 = this.exif;
        return ((((((((((((hashCode ^ (exif2 == null ? 0 : exif2.hashCode())) * 1000003) ^ this.format) * 1000003) ^ this.size.hashCode()) * 1000003) ^ this.cropRect.hashCode()) * 1000003) ^ this.rotationDegrees) * 1000003) ^ this.sensorToBufferTransform.hashCode()) * 1000003) ^ this.cameraCaptureResult.hashCode();
    }
}
