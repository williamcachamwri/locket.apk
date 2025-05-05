package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProcessingNode$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ProcessingRequest f$0;
    public final /* synthetic */ Bitmap f$1;

    public /* synthetic */ ProcessingNode$$ExternalSyntheticLambda0(ProcessingRequest processingRequest, Bitmap bitmap) {
        this.f$0 = processingRequest;
        this.f$1 = bitmap;
    }

    public final void run() {
        this.f$0.onPostviewBitmapAvailable(this.f$1);
    }
}
