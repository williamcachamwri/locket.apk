package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda40 implements Runnable {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ SettableFuture f$1;
    public final /* synthetic */ Consumer f$2;
    public final /* synthetic */ ListenableFuture f$3;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda40(MediaSessionImpl mediaSessionImpl, SettableFuture settableFuture, Consumer consumer, ListenableFuture listenableFuture) {
        this.f$0 = mediaSessionImpl;
        this.f$1 = settableFuture;
        this.f$2 = consumer;
        this.f$3 = listenableFuture;
    }

    public final void run() {
        MediaSessionStub.lambda$handleSessionTaskWhenReady$16(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
