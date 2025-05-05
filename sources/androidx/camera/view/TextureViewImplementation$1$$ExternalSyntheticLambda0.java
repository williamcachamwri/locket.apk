package androidx.camera.view;

import android.graphics.SurfaceTexture;
import androidx.camera.view.PreviewView;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TextureViewImplementation$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ PreviewView.OnFrameUpdateListener f$0;
    public final /* synthetic */ SurfaceTexture f$1;

    public /* synthetic */ TextureViewImplementation$1$$ExternalSyntheticLambda0(PreviewView.OnFrameUpdateListener onFrameUpdateListener, SurfaceTexture surfaceTexture) {
        this.f$0 = onFrameUpdateListener;
        this.f$1 = surfaceTexture;
    }

    public final void run() {
        this.f$0.onFrameUpdate(this.f$1.getTimestamp());
    }
}
