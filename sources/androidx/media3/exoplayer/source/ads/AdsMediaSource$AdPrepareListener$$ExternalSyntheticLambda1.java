package androidx.media3.exoplayer.source.ads;

import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;
import java.io.IOException;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AdsMediaSource$AdPrepareListener$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ AdsMediaSource.AdPrepareListener f$0;
    public final /* synthetic */ MediaSource.MediaPeriodId f$1;
    public final /* synthetic */ IOException f$2;

    public /* synthetic */ AdsMediaSource$AdPrepareListener$$ExternalSyntheticLambda1(AdsMediaSource.AdPrepareListener adPrepareListener, MediaSource.MediaPeriodId mediaPeriodId, IOException iOException) {
        this.f$0 = adPrepareListener;
        this.f$1 = mediaPeriodId;
        this.f$2 = iOException;
    }

    public final void run() {
        this.f$0.m869lambda$onPrepareError$1$androidxmedia3exoplayersourceadsAdsMediaSource$AdPrepareListener(this.f$1, this.f$2);
    }
}
