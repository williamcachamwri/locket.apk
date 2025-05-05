package androidx.camera.core.processing.concurrent;

import androidx.camera.core.SurfaceOutput;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DualSurfaceProcessor$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ DualSurfaceProcessor f$0;
    public final /* synthetic */ SurfaceOutput f$1;

    public /* synthetic */ DualSurfaceProcessor$$ExternalSyntheticLambda3(DualSurfaceProcessor dualSurfaceProcessor, SurfaceOutput surfaceOutput) {
        this.f$0 = dualSurfaceProcessor;
        this.f$1 = surfaceOutput;
    }

    public final void accept(Object obj) {
        this.f$0.m219lambda$onOutputSurface$2$androidxcameracoreprocessingconcurrentDualSurfaceProcessor(this.f$1, (SurfaceOutput.Event) obj);
    }
}
