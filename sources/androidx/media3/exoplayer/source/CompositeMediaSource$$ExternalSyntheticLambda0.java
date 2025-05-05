package androidx.media3.exoplayer.source;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.source.MediaSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CompositeMediaSource$$ExternalSyntheticLambda0 implements MediaSource.MediaSourceCaller {
    public final /* synthetic */ CompositeMediaSource f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ CompositeMediaSource$$ExternalSyntheticLambda0(CompositeMediaSource compositeMediaSource, Object obj) {
        this.f$0 = compositeMediaSource;
        this.f$1 = obj;
    }

    public final void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
        this.f$0.m854lambda$prepareChildSource$0$androidxmedia3exoplayersourceCompositeMediaSource(this.f$1, mediaSource, timeline);
    }
}
