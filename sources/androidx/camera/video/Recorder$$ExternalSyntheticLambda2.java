package androidx.camera.video;

import androidx.camera.video.Recorder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ Recorder f$0;
    public final /* synthetic */ Recorder.RecordingRecord f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ Recorder$$ExternalSyntheticLambda2(Recorder recorder, Recorder.RecordingRecord recordingRecord, boolean z) {
        this.f$0 = recorder;
        this.f$1 = recordingRecord;
        this.f$2 = z;
    }

    public final void run() {
        this.f$0.m241lambda$mute$6$androidxcameravideoRecorder(this.f$1, this.f$2);
    }
}
