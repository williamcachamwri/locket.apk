package androidx.camera.extensions.internal.sessionprocessor;

import android.media.ImageReader;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SessionProcessorBase$$ExternalSyntheticLambda1 implements ImageReader.OnImageAvailableListener {
    public final /* synthetic */ ImageProcessor f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ SessionProcessorBase$$ExternalSyntheticLambda1(ImageProcessor imageProcessor, int i, String str) {
        this.f$0 = imageProcessor;
        this.f$1 = i;
        this.f$2 = str;
    }

    public final void onImageAvailable(ImageReader imageReader) {
        SessionProcessorBase.lambda$setImageProcessor$1(this.f$0, this.f$1, this.f$2, imageReader);
    }
}
