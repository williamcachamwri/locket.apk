package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher f$0;
    public final /* synthetic */ MediaSource.MediaPeriodId f$1;
    public final /* synthetic */ MediaLoadData f$2;

    public /* synthetic */ MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda4(MediaSourceEventListener.EventDispatcher eventDispatcher, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
        this.f$0 = eventDispatcher;
        this.f$1 = mediaPeriodId;
        this.f$2 = mediaLoadData;
    }

    public final void accept(Object obj) {
        this.f$0.m862lambda$upstreamDiscarded$4$androidxmedia3exoplayersourceMediaSourceEventListener$EventDispatcher(this.f$1, this.f$2, (MediaSourceEventListener) obj);
    }
}
