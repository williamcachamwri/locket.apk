package androidx.camera.core.processing;

import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceProcessorNode$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SurfaceProcessorNode f$0;
    public final /* synthetic */ SurfaceEdge f$1;
    public final /* synthetic */ Map.Entry f$2;

    public /* synthetic */ SurfaceProcessorNode$$ExternalSyntheticLambda1(SurfaceProcessorNode surfaceProcessorNode, SurfaceEdge surfaceEdge, Map.Entry entry) {
        this.f$0 = surfaceProcessorNode;
        this.f$1 = surfaceEdge;
        this.f$2 = entry;
    }

    public final void run() {
        this.f$0.m211lambda$sendSurfaceOutputs$0$androidxcameracoreprocessingSurfaceProcessorNode(this.f$1, this.f$2);
    }
}
