package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.Timeline;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PreloadMediaSource$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ PreloadMediaSource f$0;
    public final /* synthetic */ Timeline f$1;

    public /* synthetic */ PreloadMediaSource$$ExternalSyntheticLambda1(PreloadMediaSource preloadMediaSource, Timeline timeline) {
        this.f$0 = preloadMediaSource;
        this.f$1 = timeline;
    }

    public final void run() {
        this.f$0.m877lambda$onChildSourceInfoRefreshed$2$androidxmedia3exoplayersourcepreloadPreloadMediaSource(this.f$1);
    }
}
