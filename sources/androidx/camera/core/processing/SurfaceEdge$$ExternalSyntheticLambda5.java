package androidx.camera.core.processing;

import androidx.camera.core.processing.SurfaceEdge;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceEdge$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ SurfaceEdge.SettableSurface f$0;

    public /* synthetic */ SurfaceEdge$$ExternalSyntheticLambda5(SurfaceEdge.SettableSurface settableSurface) {
        this.f$0 = settableSurface;
    }

    public final void run() {
        this.f$0.decrementUseCount();
    }
}
