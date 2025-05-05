package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TakePictureRequest$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ TakePictureRequest f$0;
    public final /* synthetic */ Bitmap f$1;

    public /* synthetic */ TakePictureRequest$$ExternalSyntheticLambda1(TakePictureRequest takePictureRequest, Bitmap bitmap) {
        this.f$0 = takePictureRequest;
        this.f$1 = bitmap;
    }

    public final void run() {
        this.f$0.m172lambda$onPostviewBitmapAvailable$4$androidxcameracoreimagecaptureTakePictureRequest(this.f$1);
    }
}
