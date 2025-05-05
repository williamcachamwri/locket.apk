package androidx.camera.core.processing;

import androidx.camera.core.impl.DeferrableSurface;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceEdge$SettableSurface$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DeferrableSurface f$0;

    public /* synthetic */ SurfaceEdge$SettableSurface$$ExternalSyntheticLambda1(DeferrableSurface deferrableSurface) {
        this.f$0 = deferrableSurface;
    }

    public final void run() {
        this.f$0.decrementUseCount();
    }
}
