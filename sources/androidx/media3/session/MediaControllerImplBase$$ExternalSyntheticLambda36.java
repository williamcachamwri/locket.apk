package androidx.media3.session;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda36 implements Runnable {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ ListenableFuture f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda36(MediaControllerImplBase mediaControllerImplBase, ListenableFuture listenableFuture, int i) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = listenableFuture;
        this.f$2 = i;
    }

    public final void run() {
        this.f$0.m972lambda$sendControllerResultWhenReady$106$androidxmedia3sessionMediaControllerImplBase(this.f$1, this.f$2);
    }
}
