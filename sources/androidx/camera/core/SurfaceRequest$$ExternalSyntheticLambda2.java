package androidx.camera.core;

import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceRequest$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ Surface f$1;

    public /* synthetic */ SurfaceRequest$$ExternalSyntheticLambda2(Consumer consumer, Surface surface) {
        this.f$0 = consumer;
        this.f$1 = surface;
    }

    public final void run() {
        this.f$0.accept(SurfaceRequest.Result.of(4, this.f$1));
    }
}
