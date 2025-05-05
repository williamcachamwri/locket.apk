package androidx.camera.view;

import androidx.camera.core.SurfaceRequest;
import androidx.camera.view.PreviewViewImplementation;
import androidx.camera.view.SurfaceViewImplementation;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceViewImplementation$SurfaceRequestCallback$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ PreviewViewImplementation.OnSurfaceNotInUseListener f$0;

    public /* synthetic */ SurfaceViewImplementation$SurfaceRequestCallback$$ExternalSyntheticLambda0(PreviewViewImplementation.OnSurfaceNotInUseListener onSurfaceNotInUseListener) {
        this.f$0 = onSurfaceNotInUseListener;
    }

    public final void accept(Object obj) {
        SurfaceViewImplementation.SurfaceRequestCallback.lambda$tryToComplete$0(this.f$0, (SurfaceRequest.Result) obj);
    }
}
