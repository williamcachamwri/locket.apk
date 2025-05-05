package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.source.MediaLoadData;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f$0;
    public final /* synthetic */ Pair f$1;
    public final /* synthetic */ MediaLoadData f$2;

    public /* synthetic */ MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda4(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, MediaLoadData mediaLoadData) {
        this.f$0 = forwardingEventListener;
        this.f$1 = pair;
        this.f$2 = mediaLoadData;
    }

    public final void run() {
        this.f$0.m487lambda$onUpstreamDiscarded$4$androidxmedia3exoplayerMediaSourceList$ForwardingEventListener(this.f$1, this.f$2);
    }
}
