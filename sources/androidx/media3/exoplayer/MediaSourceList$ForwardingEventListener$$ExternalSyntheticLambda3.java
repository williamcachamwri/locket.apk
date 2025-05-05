package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f$0;
    public final /* synthetic */ Pair f$1;
    public final /* synthetic */ Exception f$2;

    public /* synthetic */ MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda3(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, Exception exc) {
        this.f$0 = forwardingEventListener;
        this.f$1 = pair;
        this.f$2 = exc;
    }

    public final void run() {
        this.f$0.m481lambda$onDrmSessionManagerError$8$androidxmedia3exoplayerMediaSourceList$ForwardingEventListener(this.f$1, this.f$2);
    }
}
