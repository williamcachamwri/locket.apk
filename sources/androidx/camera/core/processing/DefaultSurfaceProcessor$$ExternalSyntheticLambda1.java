package androidx.camera.core.processing;

import androidx.camera.core.SurfaceRequest;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda1 implements SurfaceRequest.TransformationInfoListener {
    public final /* synthetic */ DefaultSurfaceProcessor f$0;
    public final /* synthetic */ SurfaceRequest f$1;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda1(DefaultSurfaceProcessor defaultSurfaceProcessor, SurfaceRequest surfaceRequest) {
        this.f$0 = defaultSurfaceProcessor;
        this.f$1 = surfaceRequest;
    }

    public final void onTransformationInfoUpdate(SurfaceRequest.TransformationInfo transformationInfo) {
        this.f$0.m192lambda$onInputSurface$0$androidxcameracoreprocessingDefaultSurfaceProcessor(this.f$1, transformationInfo);
    }
}
