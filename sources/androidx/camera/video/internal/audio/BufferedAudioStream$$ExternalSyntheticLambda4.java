package androidx.camera.video.internal.audio;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferedAudioStream$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ BufferedAudioStream f$0;

    public /* synthetic */ BufferedAudioStream$$ExternalSyntheticLambda4(BufferedAudioStream bufferedAudioStream) {
        this.f$0 = bufferedAudioStream;
    }

    public final void run() {
        this.f$0.collectAudioData();
    }
}
