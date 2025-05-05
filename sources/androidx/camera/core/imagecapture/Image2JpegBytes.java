package androidx.camera.core.imagecapture;

import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.internal.compat.workaround.JpegMetadataCorrector;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

final class Image2JpegBytes implements Operation<In, Packet<byte[]>> {
    private final JpegMetadataCorrector mJpegMetadataCorrector;

    Image2JpegBytes(Quirks quirks) {
        this.mJpegMetadataCorrector = new JpegMetadataCorrector(quirks);
    }

    public Packet<byte[]> apply(In in) throws ImageCaptureException {
        Packet<byte[]> processYuvImage;
        try {
            int format = in.getPacket().getFormat();
            if (format != 35) {
                if (format != 256) {
                    if (format != 4101) {
                        throw new IllegalArgumentException("Unexpected format: " + format);
                    }
                }
                processYuvImage = processJpegImage(in, format);
            } else {
                processYuvImage = processYuvImage(in);
            }
            return processYuvImage;
        } finally {
            in.getPacket().getData().close();
        }
    }

    private Packet<byte[]> processJpegImage(In in, int i) {
        Packet<ImageProxy> packet = in.getPacket();
        return Packet.of(this.mJpegMetadataCorrector.jpegImageToJpegByteArray(packet.getData()), (Exif) Objects.requireNonNull(packet.getExif()), i, packet.getSize(), packet.getCropRect(), packet.getRotationDegrees(), packet.getSensorToBufferTransform(), packet.getCameraCaptureResult());
    }

    private Packet<byte[]> processYuvImage(In in) throws ImageCaptureException {
        Packet<ImageProxy> packet = in.getPacket();
        ImageProxy data = packet.getData();
        Rect cropRect = packet.getCropRect();
        try {
            byte[] yuvImageToJpegByteArray = ImageUtil.yuvImageToJpegByteArray(data, cropRect, in.getJpegQuality(), packet.getRotationDegrees());
            return Packet.of(yuvImageToJpegByteArray, extractExif(yuvImageToJpegByteArray), 256, new Size(cropRect.width(), cropRect.height()), new Rect(0, 0, cropRect.width(), cropRect.height()), packet.getRotationDegrees(), TransformUtils.updateSensorToBufferTransform(packet.getSensorToBufferTransform(), cropRect), packet.getCameraCaptureResult());
        } catch (ImageUtil.CodecFailedException e) {
            throw new ImageCaptureException(1, "Failed to encode the image to JPEG.", e);
        }
    }

    private static Exif extractExif(byte[] bArr) throws ImageCaptureException {
        try {
            return Exif.createFromInputStream(new ByteArrayInputStream(bArr));
        } catch (IOException e) {
            throw new ImageCaptureException(0, "Failed to extract Exif from YUV-generated JPEG", e);
        }
    }

    static abstract class In {
        /* access modifiers changed from: package-private */
        public abstract int getJpegQuality();

        /* access modifiers changed from: package-private */
        public abstract Packet<ImageProxy> getPacket();

        In() {
        }

        static In of(Packet<ImageProxy> packet, int i) {
            return new AutoValue_Image2JpegBytes_In(packet, i);
        }
    }
}
