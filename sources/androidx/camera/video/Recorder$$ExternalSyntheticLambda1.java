package androidx.camera.video;

import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.Timebase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Recorder f$0;
    public final /* synthetic */ SurfaceRequest f$1;
    public final /* synthetic */ Timebase f$2;

    public /* synthetic */ Recorder$$ExternalSyntheticLambda1(Recorder recorder, SurfaceRequest surfaceRequest, Timebase timebase) {
        this.f$0 = recorder;
        this.f$1 = surfaceRequest;
        this.f$2 = timebase;
    }

    public final void run() {
        this.f$0.m243lambda$onSurfaceRequested$0$androidxcameravideoRecorder(this.f$1, this.f$2);
    }
}
