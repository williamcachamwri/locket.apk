package androidx.camera.core.processing.concurrent;

import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.processing.SurfaceEdge;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DualSurfaceProcessorNode$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DualSurfaceProcessorNode f$0;
    public final /* synthetic */ CameraInternal f$1;
    public final /* synthetic */ CameraInternal f$2;
    public final /* synthetic */ SurfaceEdge f$3;
    public final /* synthetic */ SurfaceEdge f$4;
    public final /* synthetic */ Map.Entry f$5;

    public /* synthetic */ DualSurfaceProcessorNode$$ExternalSyntheticLambda1(DualSurfaceProcessorNode dualSurfaceProcessorNode, CameraInternal cameraInternal, CameraInternal cameraInternal2, SurfaceEdge surfaceEdge, SurfaceEdge surfaceEdge2, Map.Entry entry) {
        this.f$0 = dualSurfaceProcessorNode;
        this.f$1 = cameraInternal;
        this.f$2 = cameraInternal2;
        this.f$3 = surfaceEdge;
        this.f$4 = surfaceEdge2;
        this.f$5 = entry;
    }

    public final void run() {
        this.f$0.m223lambda$sendSurfaceOutputs$0$androidxcameracoreprocessingconcurrentDualSurfaceProcessorNode(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
