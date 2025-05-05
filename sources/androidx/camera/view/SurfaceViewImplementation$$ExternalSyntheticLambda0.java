package androidx.camera.view;

import androidx.camera.view.PreviewViewImplementation;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceViewImplementation$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ PreviewViewImplementation.OnSurfaceNotInUseListener f$0;

    public /* synthetic */ SurfaceViewImplementation$$ExternalSyntheticLambda0(PreviewViewImplementation.OnSurfaceNotInUseListener onSurfaceNotInUseListener) {
        this.f$0 = onSurfaceNotInUseListener;
    }

    public final void run() {
        this.f$0.onSurfaceNotInUse();
    }
}
