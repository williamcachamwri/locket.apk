package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.preload.PreloadMediaSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PreloadMediaSource$PreloadMediaPeriodCallback$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ PreloadMediaSource.PreloadMediaPeriodCallback f$0;
    public final /* synthetic */ MediaPeriod f$1;

    public /* synthetic */ PreloadMediaSource$PreloadMediaPeriodCallback$$ExternalSyntheticLambda1(PreloadMediaSource.PreloadMediaPeriodCallback preloadMediaPeriodCallback, MediaPeriod mediaPeriod) {
        this.f$0 = preloadMediaPeriodCallback;
        this.f$1 = mediaPeriod;
    }

    public final void run() {
        this.f$0.m881lambda$onPrepared$0$androidxmedia3exoplayersourcepreloadPreloadMediaSource$PreloadMediaPeriodCallback(this.f$1);
    }
}
