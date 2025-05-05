package androidx.camera.core.processing;

import android.view.Surface;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.processing.SurfaceEdge;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceEdge$$ExternalSyntheticLambda4 implements AsyncFunction {
    public final /* synthetic */ SurfaceEdge f$0;
    public final /* synthetic */ SurfaceEdge.SettableSurface f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ SurfaceOutput.CameraInputInfo f$3;
    public final /* synthetic */ SurfaceOutput.CameraInputInfo f$4;

    public /* synthetic */ SurfaceEdge$$ExternalSyntheticLambda4(SurfaceEdge surfaceEdge, SurfaceEdge.SettableSurface settableSurface, int i, SurfaceOutput.CameraInputInfo cameraInputInfo, SurfaceOutput.CameraInputInfo cameraInputInfo2) {
        this.f$0 = surfaceEdge;
        this.f$1 = settableSurface;
        this.f$2 = i;
        this.f$3 = cameraInputInfo;
        this.f$4 = cameraInputInfo2;
    }

    public final ListenableFuture apply(Object obj) {
        return this.f$0.m202lambda$createSurfaceOutputFuture$2$androidxcameracoreprocessingSurfaceEdge(this.f$1, this.f$2, this.f$3, this.f$4, (Surface) obj);
    }
}
