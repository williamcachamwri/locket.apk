package androidx.camera.core.processing.concurrent;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DualSurfaceProcessor$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ DualSurfaceProcessor f$0;
    public final /* synthetic */ SurfaceTexture f$1;
    public final /* synthetic */ Surface f$2;

    public /* synthetic */ DualSurfaceProcessor$$ExternalSyntheticLambda5(DualSurfaceProcessor dualSurfaceProcessor, SurfaceTexture surfaceTexture, Surface surface) {
        this.f$0 = dualSurfaceProcessor;
        this.f$1 = surfaceTexture;
        this.f$2 = surface;
    }

    public final void accept(Object obj) {
        this.f$0.m217lambda$onInputSurface$0$androidxcameracoreprocessingconcurrentDualSurfaceProcessor(this.f$1, this.f$2, (SurfaceRequest.Result) obj);
    }
}
