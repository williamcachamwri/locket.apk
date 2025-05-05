package androidx.media3.exoplayer.hls;

import androidx.media3.exoplayer.hls.HlsSampleStreamWrapper;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class HlsSampleStreamWrapper$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ HlsSampleStreamWrapper.Callback f$0;

    public /* synthetic */ HlsSampleStreamWrapper$$ExternalSyntheticLambda0(HlsSampleStreamWrapper.Callback callback) {
        this.f$0 = callback;
    }

    public final void run() {
        this.f$0.onPrepared();
    }
}
