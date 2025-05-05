package androidx.media3.exoplayer.hls;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class HlsSampleStreamWrapper$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ HlsSampleStreamWrapper f$0;

    public /* synthetic */ HlsSampleStreamWrapper$$ExternalSyntheticLambda3(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.f$0 = hlsSampleStreamWrapper;
    }

    public final void run() {
        this.f$0.onTracksEnded();
    }
}
