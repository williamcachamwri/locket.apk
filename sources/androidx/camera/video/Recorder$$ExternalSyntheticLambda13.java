package androidx.camera.video;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ Recorder f$0;

    public /* synthetic */ Recorder$$ExternalSyntheticLambda13(Recorder recorder) {
        this.f$0 = recorder;
    }

    public final void run() {
        this.f$0.tryServicePendingRecording();
    }
}
