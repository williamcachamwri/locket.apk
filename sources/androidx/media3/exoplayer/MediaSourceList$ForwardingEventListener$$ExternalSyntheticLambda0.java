package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f$0;
    public final /* synthetic */ Pair f$1;

    public /* synthetic */ MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda0(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair) {
        this.f$0 = forwardingEventListener;
        this.f$1 = pair;
    }

    public final void run() {
        this.f$0.m478lambda$onDrmKeysRemoved$10$androidxmedia3exoplayerMediaSourceList$ForwardingEventListener(this.f$1);
    }
}
