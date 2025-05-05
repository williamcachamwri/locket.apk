package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import java.io.IOException;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f$0;
    public final /* synthetic */ Pair f$1;
    public final /* synthetic */ LoadEventInfo f$2;
    public final /* synthetic */ MediaLoadData f$3;
    public final /* synthetic */ IOException f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda2(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        this.f$0 = forwardingEventListener;
        this.f$1 = pair;
        this.f$2 = loadEventInfo;
        this.f$3 = mediaLoadData;
        this.f$4 = iOException;
        this.f$5 = z;
    }

    public final void run() {
        this.f$0.m485lambda$onLoadError$3$androidxmedia3exoplayerMediaSourceList$ForwardingEventListener(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
