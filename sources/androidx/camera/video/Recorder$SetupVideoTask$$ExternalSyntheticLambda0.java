package androidx.camera.video;

import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.Timebase;
import androidx.camera.video.Recorder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$SetupVideoTask$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Recorder.SetupVideoTask f$0;
    public final /* synthetic */ SurfaceRequest f$1;
    public final /* synthetic */ Timebase f$2;

    public /* synthetic */ Recorder$SetupVideoTask$$ExternalSyntheticLambda0(Recorder.SetupVideoTask setupVideoTask, SurfaceRequest surfaceRequest, Timebase timebase) {
        this.f$0 = setupVideoTask;
        this.f$1 = surfaceRequest;
        this.f$2 = timebase;
    }

    public final void run() {
        this.f$0.m253lambda$setupVideo$0$androidxcameravideoRecorder$SetupVideoTask(this.f$1, this.f$2);
    }
}
