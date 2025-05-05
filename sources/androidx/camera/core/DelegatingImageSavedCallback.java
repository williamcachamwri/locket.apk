package androidx.camera.core;

import android.graphics.Bitmap;
import androidx.camera.core.ImageCapture;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u001c\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00018BX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0018"}, d2 = {"Landroidx/camera/core/DelegatingImageSavedCallback;", "Landroidx/camera/core/ImageCapture$OnImageSavedCallback;", "delegate", "(Landroidx/camera/core/ImageCapture$OnImageSavedCallback;)V", "_delegate", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "getDelegate", "()Landroidx/camera/core/ImageCapture$OnImageSavedCallback;", "dispose", "", "onCaptureProcessProgressed", "progress", "", "onCaptureStarted", "onError", "exception", "Landroidx/camera/core/ImageCaptureException;", "onImageSaved", "outputFileResults", "Landroidx/camera/core/ImageCapture$OutputFileResults;", "onPostviewBitmapAvailable", "bitmap", "Landroid/graphics/Bitmap;", "camera-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImageCaptureExt.kt */
final class DelegatingImageSavedCallback implements ImageCapture.OnImageSavedCallback {
    private final AtomicReference<ImageCapture.OnImageSavedCallback> _delegate;

    public DelegatingImageSavedCallback(ImageCapture.OnImageSavedCallback onImageSavedCallback) {
        Intrinsics.checkNotNullParameter(onImageSavedCallback, "delegate");
        this._delegate = new AtomicReference<>(onImageSavedCallback);
    }

    private final ImageCapture.OnImageSavedCallback getDelegate() {
        return this._delegate.get();
    }

    public final void dispose() {
        this._delegate.set((Object) null);
    }

    public void onCaptureStarted() {
        ImageCapture.OnImageSavedCallback delegate = getDelegate();
        if (delegate != null) {
            delegate.onCaptureStarted();
        }
    }

    public void onCaptureProcessProgressed(int i) {
        ImageCapture.OnImageSavedCallback delegate = getDelegate();
        if (delegate != null) {
            delegate.onCaptureProcessProgressed(i);
        }
    }

    public void onPostviewBitmapAvailable(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        ImageCapture.OnImageSavedCallback delegate = getDelegate();
        if (delegate != null) {
            delegate.onPostviewBitmapAvailable(bitmap);
        }
    }

    public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
        Intrinsics.checkNotNullParameter(outputFileResults, "outputFileResults");
        ImageCapture.OnImageSavedCallback delegate = getDelegate();
        if (delegate != null) {
            delegate.onImageSaved(outputFileResults);
        }
    }

    public void onError(ImageCaptureException imageCaptureException) {
        Intrinsics.checkNotNullParameter(imageCaptureException, "exception");
        ImageCapture.OnImageSavedCallback delegate = getDelegate();
        if (delegate != null) {
            delegate.onError(imageCaptureException);
        }
    }
}
