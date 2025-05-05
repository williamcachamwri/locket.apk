package expo.modules.camera.next;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import expo.modules.camera.next.CameraExceptions;
import expo.modules.camera.next.analyzers.BarcodeAnalyzerKt;
import expo.modules.kotlin.Promise;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"expo/modules/camera/next/ExpoCameraView$takePicture$1", "Landroidx/camera/core/ImageCapture$OnImageCapturedCallback;", "onCaptureSuccess", "", "image", "Landroidx/camera/core/ImageProxy;", "onError", "exception", "Landroidx/camera/core/ImageCaptureException;", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoCameraView.kt */
public final class ExpoCameraView$takePicture$1 extends ImageCapture.OnImageCapturedCallback {
    final /* synthetic */ File $cacheDirectory;
    final /* synthetic */ PictureOptions $options;
    final /* synthetic */ Promise $promise;
    final /* synthetic */ ExpoCameraView this$0;

    ExpoCameraView$takePicture$1(PictureOptions pictureOptions, Promise promise, File file, ExpoCameraView expoCameraView) {
        this.$options = pictureOptions;
        this.$promise = promise;
        this.$cacheDirectory = file;
        this.this$0 = expoCameraView;
    }

    public void onCaptureSuccess(ImageProxy imageProxy) {
        Intrinsics.checkNotNullParameter(imageProxy, "image");
        ImageProxy.PlaneProxy[] planes = imageProxy.getPlanes();
        Intrinsics.checkNotNullExpressionValue(planes, "getPlanes(...)");
        byte[] byteArray = BarcodeAnalyzerKt.toByteArray(planes);
        if (this.$options.getFastMode()) {
            this.$promise.resolve((Object) null);
        }
        File file = this.$cacheDirectory;
        ExpoCameraView expoCameraView = this.this$0;
        Promise promise = this.$promise;
        PictureOptions pictureOptions = this.$options;
        Job unused = BuildersKt__Builders_commonKt.launch$default(expoCameraView.scope, (CoroutineContext) null, (CoroutineStart) null, new ExpoCameraView$takePicture$1$onCaptureSuccess$1$1(byteArray, promise, pictureOptions, file, expoCameraView, (Continuation<? super ExpoCameraView$takePicture$1$onCaptureSuccess$1$1>) null), 3, (Object) null);
        imageProxy.close();
    }

    public void onError(ImageCaptureException imageCaptureException) {
        Intrinsics.checkNotNullParameter(imageCaptureException, "exception");
        this.$promise.reject(new CameraExceptions.ImageCaptureFailed());
    }
}
