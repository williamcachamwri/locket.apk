package androidx.media3.exoplayer.audio;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAudioSink$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DefaultAudioSink f$0;

    public /* synthetic */ DefaultAudioSink$$ExternalSyntheticLambda0(DefaultAudioSink defaultAudioSink) {
        this.f$0 = defaultAudioSink;
    }

    public final void run() {
        this.f$0.maybeReportSkippedSilence();
    }
}
