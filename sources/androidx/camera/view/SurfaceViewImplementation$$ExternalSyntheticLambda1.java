package androidx.camera.view;

import androidx.camera.core.SurfaceRequest;
import androidx.camera.view.PreviewViewImplementation;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceViewImplementation$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SurfaceViewImplementation f$0;
    public final /* synthetic */ SurfaceRequest f$1;
    public final /* synthetic */ PreviewViewImplementation.OnSurfaceNotInUseListener f$2;

    public /* synthetic */ SurfaceViewImplementation$$ExternalSyntheticLambda1(SurfaceViewImplementation surfaceViewImplementation, SurfaceRequest surfaceRequest, PreviewViewImplementation.OnSurfaceNotInUseListener onSurfaceNotInUseListener) {
        this.f$0 = surfaceViewImplementation;
        this.f$1 = surfaceRequest;
        this.f$2 = onSurfaceNotInUseListener;
    }

    public final void run() {
        this.f$0.m322lambda$onSurfaceRequested$0$androidxcameraviewSurfaceViewImplementation(this.f$1, this.f$2);
    }
}
