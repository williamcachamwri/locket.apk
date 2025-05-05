package androidx.camera.video;

import android.view.Surface;
import androidx.camera.video.internal.encoder.Encoder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$$ExternalSyntheticLambda4 implements Encoder.SurfaceInput.OnSurfaceUpdateListener {
    public final /* synthetic */ Recorder f$0;

    public /* synthetic */ Recorder$$ExternalSyntheticLambda4(Recorder recorder) {
        this.f$0 = recorder;
    }

    public final void onSurfaceUpdate(Surface surface) {
        this.f$0.setLatestSurface(surface);
    }
}
