package androidx.camera.core;

import android.graphics.Bitmap;
import androidx.camera.core.ImageCapture;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u001c\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00018BX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0018"}, d2 = {"Landroidx/camera/core/DelegatingImageCapturedCallback;", "Landroidx/camera/core/ImageCapture$OnImageCapturedCallback;", "delegate", "(Landroidx/camera/core/ImageCapture$OnImageCapturedCallback;)V", "_delegate", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "getDelegate", "()Landroidx/camera/core/ImageCapture$OnImageCapturedCallback;", "dispose", "", "onCaptureProcessProgressed", "progress", "", "onCaptureStarted", "onCaptureSuccess", "imageProxy", "Landroidx/camera/core/ImageProxy;", "onError", "exception", "Landroidx/camera/core/ImageCaptureException;", "onPostviewBitmapAvailable", "bitmap", "Landroid/graphics/Bitmap;", "camera-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImageCaptureExt.kt */
final class DelegatingImageCapturedCallback extends ImageCapture.OnImageCapturedCallback {
    private final AtomicReference<ImageCapture.OnImageCapturedCallback> _delegate;

    public DelegatingImageCapturedCallback(ImageCapture.OnImageCapturedCallback onImageCapturedCallback) {
        Intrinsics.checkNotNullParameter(onImageCapturedCallback, "delegate");
        this._delegate = new AtomicReference<>(onImageCapturedCallback);
    }

    private final ImageCapture.OnImageCapturedCallback getDelegate() {
        return this._delegate.get();
    }

    public final void dispose() {
        this._delegate.set((Object) null);
    }

    public void onCaptureStarted() {
        ImageCapture.OnImageCapturedCallback delegate = getDelegate();
        if (delegate != null) {
            delegate.onCaptureStarted();
        }
    }

    public void onCaptureProcessProgressed(int i) {
        ImageCapture.OnImageCapturedCallback delegate = getDelegate();
        if (delegate != null) {
            delegate.onCaptureProcessProgressed(i);
        }
    }

    public void onPostviewBitmapAvailable(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        ImageCapture.OnImageCapturedCallback delegate = getDelegate();
        if (delegate != null) {
            delegate.onPostviewBitmapAvailable(bitmap);
        }
    }

    public void onCaptureSuccess(ImageProxy imageProxy) {
        Unit unit;
        Intrinsics.checkNotNullParameter(imageProxy, "imageProxy");
        ImageCapture.OnImageCapturedCallback delegate = getDelegate();
        if (delegate != null) {
            delegate.onCaptureSuccess(imageProxy);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            DelegatingImageCapturedCallback delegatingImageCapturedCallback = this;
            imageProxy.close();
        }
    }

    public void onError(ImageCaptureException imageCaptureException) {
        Intrinsics.checkNotNullParameter(imageCaptureException, "exception");
        ImageCapture.OnImageCapturedCallback delegate = getDelegate();
        if (delegate != null) {
            delegate.onError(imageCaptureException);
        }
    }
}
