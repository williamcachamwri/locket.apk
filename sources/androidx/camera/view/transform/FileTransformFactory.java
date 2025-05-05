package androidx.camera.view.transform;

import android.content.ContentResolver;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.impl.utils.TransformUtils;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class FileTransformFactory {
    private boolean mUsingExifOrientation;

    public void setUsingExifOrientation(boolean z) {
        this.mUsingExifOrientation = z;
    }

    public boolean isUsingExifOrientation() {
        return this.mUsingExifOrientation;
    }

    public OutputTransform getOutputTransform(ContentResolver contentResolver, Uri uri) throws IOException {
        InputStream openInputStream = contentResolver.openInputStream(uri);
        try {
            OutputTransform outputTransform = getOutputTransform(openInputStream);
            if (openInputStream != null) {
                openInputStream.close();
            }
            return outputTransform;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public OutputTransform getOutputTransform(File file) throws IOException {
        FileInputStream create = SentryFileInputStream.Factory.create(new FileInputStream(file), file);
        try {
            OutputTransform outputTransform = getOutputTransform((InputStream) create);
            create.close();
            return outputTransform;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public OutputTransform getOutputTransform(InputStream inputStream) throws IOException {
        Exif createFromInputStream = Exif.createFromInputStream(inputStream);
        Rect rect = new Rect(0, 0, createFromInputStream.getWidth(), createFromInputStream.getHeight());
        Matrix normalizedToBuffer = TransformUtils.getNormalizedToBuffer(rect);
        if (this.mUsingExifOrientation) {
            normalizedToBuffer.postConcat(TransformUtils.getExifTransform(createFromInputStream.getOrientation(), createFromInputStream.getWidth(), createFromInputStream.getHeight()));
        }
        return new OutputTransform(normalizedToBuffer, TransformUtils.rectToSize(rect));
    }
}
