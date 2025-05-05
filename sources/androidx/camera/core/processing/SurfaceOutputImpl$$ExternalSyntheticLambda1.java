package androidx.camera.core.processing;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceOutputImpl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SurfaceOutputImpl f$0;
    public final /* synthetic */ AtomicReference f$1;

    public /* synthetic */ SurfaceOutputImpl$$ExternalSyntheticLambda1(SurfaceOutputImpl surfaceOutputImpl, AtomicReference atomicReference) {
        this.f$0 = surfaceOutputImpl;
        this.f$1 = atomicReference;
    }

    public final void run() {
        this.f$0.m209lambda$requestClose$1$androidxcameracoreprocessingSurfaceOutputImpl(this.f$1);
    }
}
