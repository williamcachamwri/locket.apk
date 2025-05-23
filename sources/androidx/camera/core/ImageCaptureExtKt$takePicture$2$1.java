package androidx.camera.core;

import android.graphics.Bitmap;
import androidx.camera.core.ImageCapture;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"androidx/camera/core/ImageCaptureExtKt$takePicture$2$1", "Landroidx/camera/core/ImageCapture$OnImageCapturedCallback;", "onCaptureProcessProgressed", "", "progress", "", "onCaptureStarted", "onCaptureSuccess", "imageProxy", "Landroidx/camera/core/ImageProxy;", "onError", "exception", "Landroidx/camera/core/ImageCaptureException;", "onPostviewBitmapAvailable", "bitmap", "Landroid/graphics/Bitmap;", "camera-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImageCaptureExt.kt */
public final class ImageCaptureExtKt$takePicture$2$1 extends ImageCapture.OnImageCapturedCallback {
    final /* synthetic */ CancellableContinuation<ImageProxy> $continuation;
    final /* synthetic */ Ref.ObjectRef<DelegatingImageCapturedCallback> $delegatingCallback;
    final /* synthetic */ Function1<Integer, Unit> $onCaptureProcessProgressed;
    final /* synthetic */ Function0<Unit> $onCaptureStarted;
    final /* synthetic */ Function1<Bitmap, Unit> $onPostviewBitmapAvailable;

    ImageCaptureExtKt$takePicture$2$1(Function0<Unit> function0, Function1<? super Integer, Unit> function1, Function1<? super Bitmap, Unit> function12, Ref.ObjectRef<DelegatingImageCapturedCallback> objectRef, CancellableContinuation<? super ImageProxy> cancellableContinuation) {
        this.$onCaptureStarted = function0;
        this.$onCaptureProcessProgressed = function1;
        this.$onPostviewBitmapAvailable = function12;
        this.$delegatingCallback = objectRef;
        this.$continuation = cancellableContinuation;
    }

    public void onCaptureStarted() {
        Function0<Unit> function0 = this.$onCaptureStarted;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public void onCaptureProcessProgressed(int i) {
        Function1<Integer, Unit> function1 = this.$onCaptureProcessProgressed;
        if (function1 != null) {
            function1.invoke(Integer.valueOf(i));
        }
    }

    public void onPostviewBitmapAvailable(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Function1<Bitmap, Unit> function1 = this.$onPostviewBitmapAvailable;
        if (function1 != null) {
            function1.invoke(bitmap);
        }
    }

    public void onCaptureSuccess(ImageProxy imageProxy) {
        DelegatingImageCapturedCallback delegatingImageCapturedCallback;
        Intrinsics.checkNotNullParameter(imageProxy, "imageProxy");
        if (this.$delegatingCallback.element == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delegatingCallback");
            delegatingImageCapturedCallback = null;
        } else {
            delegatingImageCapturedCallback = (DelegatingImageCapturedCallback) this.$delegatingCallback.element;
        }
        delegatingImageCapturedCallback.dispose();
        Result.Companion companion = Result.Companion;
        this.$continuation.resumeWith(Result.m2444constructorimpl(imageProxy));
    }

    public void onError(ImageCaptureException imageCaptureException) {
        DelegatingImageCapturedCallback delegatingImageCapturedCallback;
        Intrinsics.checkNotNullParameter(imageCaptureException, "exception");
        if (this.$delegatingCallback.element == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delegatingCallback");
            delegatingImageCapturedCallback = null;
        } else {
            delegatingImageCapturedCallback = (DelegatingImageCapturedCallback) this.$delegatingCallback.element;
        }
        delegatingImageCapturedCallback.dispose();
        Result.Companion companion = Result.Companion;
        this.$continuation.resumeWith(Result.m2444constructorimpl(ResultKt.createFailure(imageCaptureException)));
    }
}
