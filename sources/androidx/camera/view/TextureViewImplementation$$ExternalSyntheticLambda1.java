package androidx.camera.view;

import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TextureViewImplementation$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ TextureViewImplementation f$0;
    public final /* synthetic */ Surface f$1;
    public final /* synthetic */ ListenableFuture f$2;
    public final /* synthetic */ SurfaceRequest f$3;

    public /* synthetic */ TextureViewImplementation$$ExternalSyntheticLambda1(TextureViewImplementation textureViewImplementation, Surface surface, ListenableFuture listenableFuture, SurfaceRequest surfaceRequest) {
        this.f$0 = textureViewImplementation;
        this.f$1 = surface;
        this.f$2 = listenableFuture;
        this.f$3 = surfaceRequest;
    }

    public final void run() {
        this.f$0.m325lambda$tryToProvidePreviewSurface$2$androidxcameraviewTextureViewImplementation(this.f$1, this.f$2, this.f$3);
    }
}
