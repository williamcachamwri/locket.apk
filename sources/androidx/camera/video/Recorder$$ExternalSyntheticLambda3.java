package androidx.camera.video;

import androidx.camera.video.Recorder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ Recorder f$0;
    public final /* synthetic */ Recorder.RecordingRecord f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Throwable f$4;

    public /* synthetic */ Recorder$$ExternalSyntheticLambda3(Recorder recorder, Recorder.RecordingRecord recordingRecord, long j, int i, Throwable th) {
        this.f$0 = recorder;
        this.f$1 = recordingRecord;
        this.f$2 = j;
        this.f$3 = i;
        this.f$4 = th;
    }

    public final void run() {
        this.f$0.m248lambda$stop$5$androidxcameravideoRecorder(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
