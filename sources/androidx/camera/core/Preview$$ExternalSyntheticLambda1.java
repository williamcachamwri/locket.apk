package androidx.camera.core;

import androidx.camera.core.Preview;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Preview$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Preview.SurfaceProvider f$0;
    public final /* synthetic */ SurfaceRequest f$1;

    public /* synthetic */ Preview$$ExternalSyntheticLambda1(Preview.SurfaceProvider surfaceProvider, SurfaceRequest surfaceRequest) {
        this.f$0 = surfaceProvider;
        this.f$1 = surfaceRequest;
    }

    public final void run() {
        this.f$0.onSurfaceRequested(this.f$1);
    }
}
