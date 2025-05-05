package androidx.camera.view;

import android.view.PixelCopy;
import java.util.concurrent.Semaphore;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceViewImplementation$$ExternalSyntheticLambda2 implements PixelCopy.OnPixelCopyFinishedListener {
    public final /* synthetic */ Semaphore f$0;

    public /* synthetic */ SurfaceViewImplementation$$ExternalSyntheticLambda2(Semaphore semaphore) {
        this.f$0 = semaphore;
    }

    public final void onPixelCopyFinished(int i) {
        SurfaceViewImplementation.lambda$getPreviewBitmap$1(this.f$0, i);
    }
}
