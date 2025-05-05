package androidx.camera.core;

import androidx.camera.core.ImageCapture;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageCapture$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ImageCapture f$0;
    public final /* synthetic */ ImageCapture.OutputFileOptions f$1;
    public final /* synthetic */ ImageCapture.OutputFileOptions f$2;
    public final /* synthetic */ Executor f$3;
    public final /* synthetic */ ImageCapture.OnImageSavedCallback f$4;

    public /* synthetic */ ImageCapture$$ExternalSyntheticLambda1(ImageCapture imageCapture, ImageCapture.OutputFileOptions outputFileOptions, ImageCapture.OutputFileOptions outputFileOptions2, Executor executor, ImageCapture.OnImageSavedCallback onImageSavedCallback) {
        this.f$0 = imageCapture;
        this.f$1 = outputFileOptions;
        this.f$2 = outputFileOptions2;
        this.f$3 = executor;
        this.f$4 = onImageSavedCallback;
    }

    public final void run() {
        this.f$0.m147lambda$takePicture$3$androidxcameracoreImageCapture(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
