package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.source.MediaSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceList$$ExternalSyntheticLambda0 implements MediaSource.MediaSourceCaller {
    public final /* synthetic */ MediaSourceList f$0;

    public /* synthetic */ MediaSourceList$$ExternalSyntheticLambda0(MediaSourceList mediaSourceList) {
        this.f$0 = mediaSourceList;
    }

    public final void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
        this.f$0.m475lambda$prepareChildSource$0$androidxmedia3exoplayerMediaSourceList(mediaSource, timeline);
    }
}
