package androidx.camera.view;

import androidx.camera.core.ImageCapture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ScreenFlashView$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImageCapture.ScreenFlashListener f$0;

    public /* synthetic */ ScreenFlashView$1$$ExternalSyntheticLambda0(ImageCapture.ScreenFlashListener screenFlashListener) {
        this.f$0 = screenFlashListener;
    }

    public final void run() {
        this.f$0.onCompleted();
    }
}
