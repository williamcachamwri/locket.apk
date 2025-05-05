package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.MediaSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BasePreloadManager$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ BasePreloadManager f$0;
    public final /* synthetic */ PreloadException f$1;
    public final /* synthetic */ MediaSource f$2;

    public /* synthetic */ BasePreloadManager$$ExternalSyntheticLambda5(BasePreloadManager basePreloadManager, PreloadException preloadException, MediaSource mediaSource) {
        this.f$0 = basePreloadManager;
        this.f$1 = preloadException;
        this.f$2 = mediaSource;
    }

    public final void run() {
        this.f$0.m873lambda$onPreloadError$4$androidxmedia3exoplayersourcepreloadBasePreloadManager(this.f$1, this.f$2);
    }
}
