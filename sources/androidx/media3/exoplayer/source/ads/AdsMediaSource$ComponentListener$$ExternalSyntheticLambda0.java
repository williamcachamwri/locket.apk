package androidx.media3.exoplayer.source.ads;

import androidx.media3.common.AdPlaybackState;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AdsMediaSource$ComponentListener$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AdsMediaSource.ComponentListener f$0;
    public final /* synthetic */ AdPlaybackState f$1;

    public /* synthetic */ AdsMediaSource$ComponentListener$$ExternalSyntheticLambda0(AdsMediaSource.ComponentListener componentListener, AdPlaybackState adPlaybackState) {
        this.f$0 = componentListener;
        this.f$1 = adPlaybackState;
    }

    public final void run() {
        this.f$0.m870lambda$onAdPlaybackState$0$androidxmedia3exoplayersourceadsAdsMediaSource$ComponentListener(this.f$1);
    }
}
