package androidx.camera.core.processing;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ DefaultSurfaceProcessor f$0;
    public final /* synthetic */ SurfaceRequest f$1;
    public final /* synthetic */ SurfaceTexture f$2;
    public final /* synthetic */ Surface f$3;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda2(DefaultSurfaceProcessor defaultSurfaceProcessor, SurfaceRequest surfaceRequest, SurfaceTexture surfaceTexture, Surface surface) {
        this.f$0 = defaultSurfaceProcessor;
        this.f$1 = surfaceRequest;
        this.f$2 = surfaceTexture;
        this.f$3 = surface;
    }

    public final void accept(Object obj) {
        this.f$0.m193lambda$onInputSurface$1$androidxcameracoreprocessingDefaultSurfaceProcessor(this.f$1, this.f$2, this.f$3, (SurfaceRequest.Result) obj);
    }
}
