package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import java.io.IOException;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher f$0;
    public final /* synthetic */ LoadEventInfo f$1;
    public final /* synthetic */ MediaLoadData f$2;
    public final /* synthetic */ IOException f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda3(MediaSourceEventListener.EventDispatcher eventDispatcher, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        this.f$0 = eventDispatcher;
        this.f$1 = loadEventInfo;
        this.f$2 = mediaLoadData;
        this.f$3 = iOException;
        this.f$4 = z;
    }

    public final void accept(Object obj) {
        this.f$0.m860lambda$loadError$3$androidxmedia3exoplayersourceMediaSourceEventListener$EventDispatcher(this.f$1, this.f$2, this.f$3, this.f$4, (MediaSourceEventListener) obj);
    }
}
