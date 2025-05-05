package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.internal.compat.workaround.InvalidJpegDataParser;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class JpegBytes2Disk implements Operation<In, ImageCapture.OutputFileResults> {
    public ImageCapture.OutputFileResults apply(In in) throws ImageCaptureException {
        Packet<byte[]> packet = in.getPacket();
        ImageCapture.OutputFileOptions outputFileOptions = in.getOutputFileOptions();
        File createTempFile = FileUtil.createTempFile(outputFileOptions);
        writeBytesToFile(createTempFile, packet.getData());
        FileUtil.updateFileExif(createTempFile, (Exif) Objects.requireNonNull(packet.getExif()), outputFileOptions, packet.getRotationDegrees());
        return new ImageCapture.OutputFileResults(FileUtil.moveFileToTarget(createTempFile, outputFileOptions), 256);
    }

    static void writeBytesToFile(File file, byte[] bArr) throws ImageCaptureException {
        FileOutputStream create;
        try {
            create = SentryFileOutputStream.Factory.create(new FileOutputStream(file), file);
            create.write(bArr, 0, new InvalidJpegDataParser().getValidDataLength(bArr));
            create.close();
            return;
        } catch (IOException e) {
            throw new ImageCaptureException(1, "Failed to write to temp file", e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static abstract class In {
        /* access modifiers changed from: package-private */
        public abstract ImageCapture.OutputFileOptions getOutputFileOptions();

        /* access modifiers changed from: package-private */
        public abstract Packet<byte[]> getPacket();

        public static In of(Packet<byte[]> packet, ImageCapture.OutputFileOptions outputFileOptions) {
            return new AutoValue_JpegBytes2Disk_In(packet, outputFileOptions);
        }
    }
}
