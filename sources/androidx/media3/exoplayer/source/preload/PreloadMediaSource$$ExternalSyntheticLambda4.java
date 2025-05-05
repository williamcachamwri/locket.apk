package androidx.media3.exoplayer.source.preload;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PreloadMediaSource$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ PreloadMediaSource f$0;

    public /* synthetic */ PreloadMediaSource$$ExternalSyntheticLambda4(PreloadMediaSource preloadMediaSource) {
        this.f$0 = preloadMediaSource;
    }

    public final void run() {
        this.f$0.checkForPreloadError();
    }
}
