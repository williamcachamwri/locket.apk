package androidx.camera.view;

import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.view.PreviewView;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PreviewView$1$$ExternalSyntheticLambda2 implements SurfaceRequest.TransformationInfoListener {
    public final /* synthetic */ PreviewView.AnonymousClass1 f$0;
    public final /* synthetic */ CameraInternal f$1;
    public final /* synthetic */ SurfaceRequest f$2;

    public /* synthetic */ PreviewView$1$$ExternalSyntheticLambda2(PreviewView.AnonymousClass1 r1, CameraInternal cameraInternal, SurfaceRequest surfaceRequest) {
        this.f$0 = r1;
        this.f$1 = cameraInternal;
        this.f$2 = surfaceRequest;
    }

    public final void onTransformationInfoUpdate(SurfaceRequest.TransformationInfo transformationInfo) {
        this.f$0.m318lambda$onSurfaceRequested$1$androidxcameraviewPreviewView$1(this.f$1, this.f$2, transformationInfo);
    }
}
