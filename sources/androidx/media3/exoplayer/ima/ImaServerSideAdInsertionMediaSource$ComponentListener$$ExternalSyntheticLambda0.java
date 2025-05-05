package androidx.media3.exoplayer.ima;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.ima.ImaServerSideAdInsertionMediaSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImaServerSideAdInsertionMediaSource$ComponentListener$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImaServerSideAdInsertionMediaSource.ComponentListener f$0;
    public final /* synthetic */ Timeline f$1;

    public /* synthetic */ ImaServerSideAdInsertionMediaSource$ComponentListener$$ExternalSyntheticLambda0(ImaServerSideAdInsertionMediaSource.ComponentListener componentListener, Timeline timeline) {
        this.f$0 = componentListener;
        this.f$1 = timeline;
    }

    public final void run() {
        this.f$0.m527lambda$onAdPlaybackStateUpdateRequested$0$androidxmedia3exoplayerimaImaServerSideAdInsertionMediaSource$ComponentListener(this.f$1);
    }
}
