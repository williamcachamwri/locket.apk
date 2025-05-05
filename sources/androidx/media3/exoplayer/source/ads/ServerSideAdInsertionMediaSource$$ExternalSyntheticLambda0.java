package androidx.media3.exoplayer.source.ads;

import androidx.media3.common.Timeline;
import com.google.common.collect.ImmutableMap;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ServerSideAdInsertionMediaSource$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ServerSideAdInsertionMediaSource f$0;
    public final /* synthetic */ ImmutableMap f$1;
    public final /* synthetic */ Timeline f$2;

    public /* synthetic */ ServerSideAdInsertionMediaSource$$ExternalSyntheticLambda0(ServerSideAdInsertionMediaSource serverSideAdInsertionMediaSource, ImmutableMap immutableMap, Timeline timeline) {
        this.f$0 = serverSideAdInsertionMediaSource;
        this.f$1 = immutableMap;
        this.f$2 = timeline;
    }

    public final void run() {
        this.f$0.m871lambda$setAdPlaybackStates$0$androidxmedia3exoplayersourceadsServerSideAdInsertionMediaSource(this.f$1, this.f$2);
    }
}
