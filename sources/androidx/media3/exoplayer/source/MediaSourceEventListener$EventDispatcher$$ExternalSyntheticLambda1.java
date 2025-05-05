package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.MediaSourceEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher f$0;
    public final /* synthetic */ MediaLoadData f$1;

    public /* synthetic */ MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda1(MediaSourceEventListener.EventDispatcher eventDispatcher, MediaLoadData mediaLoadData) {
        this.f$0 = eventDispatcher;
        this.f$1 = mediaLoadData;
    }

    public final void accept(Object obj) {
        this.f$0.m857lambda$downstreamFormatChanged$5$androidxmedia3exoplayersourceMediaSourceEventListener$EventDispatcher(this.f$1, (MediaSourceEventListener) obj);
    }
}
