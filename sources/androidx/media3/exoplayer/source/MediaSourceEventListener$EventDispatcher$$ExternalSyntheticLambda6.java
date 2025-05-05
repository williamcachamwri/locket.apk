package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.MediaSourceEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher f$0;
    public final /* synthetic */ LoadEventInfo f$1;
    public final /* synthetic */ MediaLoadData f$2;

    public /* synthetic */ MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda6(MediaSourceEventListener.EventDispatcher eventDispatcher, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        this.f$0 = eventDispatcher;
        this.f$1 = loadEventInfo;
        this.f$2 = mediaLoadData;
    }

    public final void accept(Object obj) {
        this.f$0.m859lambda$loadCompleted$1$androidxmedia3exoplayersourceMediaSourceEventListener$EventDispatcher(this.f$1, this.f$2, (MediaSourceEventListener) obj);
    }
}
