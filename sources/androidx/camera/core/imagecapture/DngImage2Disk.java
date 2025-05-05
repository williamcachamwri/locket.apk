package androidx.camera.core.imagecapture;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.DngCreator;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.processing.Operation;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DngImage2Disk implements Operation<In, ImageCapture.OutputFileResults> {
    private DngCreator mDngCreator;

    static int computeExifOrientation(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 90) {
            return 6;
        }
        if (i != 180) {
            return i != 270 ? 0 : 8;
        }
        return 3;
    }

    public DngImage2Disk(CameraCharacteristics cameraCharacteristics, CaptureResult captureResult) {
        this(new DngCreator(cameraCharacteristics, captureResult));
    }

    DngImage2Disk(DngCreator dngCreator) {
        this.mDngCreator = dngCreator;
    }

    public ImageCapture.OutputFileResults apply(In in) throws ImageCaptureException {
        ImageCapture.OutputFileOptions outputFileOptions = in.getOutputFileOptions();
        File createTempFile = FileUtil.createTempFile(outputFileOptions);
        writeImageToFile(createTempFile, in.getImageProxy(), in.getRotationDegrees());
        return new ImageCapture.OutputFileResults(FileUtil.moveFileToTarget(createTempFile, outputFileOptions), 32);
    }

    private void writeImageToFile(File file, ImageProxy imageProxy, int i) throws ImageCaptureException {
        FileOutputStream create;
        try {
            create = SentryFileOutputStream.Factory.create(new FileOutputStream(file), file);
            this.mDngCreator.setOrientation(computeExifOrientation(i));
            this.mDngCreator.writeImage(create, imageProxy.getImage());
            create.close();
            imageProxy.close();
            return;
        } catch (IllegalArgumentException e) {
            throw new ImageCaptureException(1, "Image with an unsupported format was used", e);
        } catch (IllegalStateException e2) {
            throw new ImageCaptureException(1, "Not enough metadata information has been set to write a well-formatted DNG file", e2);
        } catch (IOException e3) {
            try {
                throw new ImageCaptureException(1, "Failed to write to temp file", e3);
            } catch (Throwable th) {
                imageProxy.close();
                throw th;
            }
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    static abstract class In {
        /* access modifiers changed from: package-private */
        public abstract ImageProxy getImageProxy();

        /* access modifiers changed from: package-private */
        public abstract ImageCapture.OutputFileOptions getOutputFileOptions();

        /* access modifiers changed from: package-private */
        public abstract int getRotationDegrees();

        In() {
        }

        static In of(ImageProxy imageProxy, int i, ImageCapture.OutputFileOptions outputFileOptions) {
            return new AutoValue_DngImage2Disk_In(imageProxy, i, outputFileOptions);
        }
    }
}
