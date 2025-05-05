package androidx.camera.video.internal.encoder;

import android.view.Surface;
import androidx.camera.video.internal.encoder.Encoder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$SurfaceInput$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Encoder.SurfaceInput.OnSurfaceUpdateListener f$0;
    public final /* synthetic */ Surface f$1;

    public /* synthetic */ EncoderImpl$SurfaceInput$$ExternalSyntheticLambda0(Encoder.SurfaceInput.OnSurfaceUpdateListener onSurfaceUpdateListener, Surface surface) {
        this.f$0 = onSurfaceUpdateListener;
        this.f$1 = surface;
    }

    public final void run() {
        this.f$0.onSurfaceUpdate(this.f$1);
    }
}
