package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.MediaSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BasePreloadManager$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ BasePreloadManager f$0;
    public final /* synthetic */ MediaSource f$1;

    public /* synthetic */ BasePreloadManager$$ExternalSyntheticLambda3(BasePreloadManager basePreloadManager, MediaSource mediaSource) {
        this.f$0 = basePreloadManager;
        this.f$1 = mediaSource;
    }

    public final void run() {
        this.f$0.m872lambda$onPreloadCompleted$2$androidxmedia3exoplayersourcepreloadBasePreloadManager(this.f$1);
    }
}
