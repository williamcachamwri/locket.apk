package androidx.media3.session;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ SettableFuture f$0;
    public final /* synthetic */ ListenableFuture f$1;

    public /* synthetic */ MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda7(SettableFuture settableFuture, ListenableFuture listenableFuture) {
        this.f$0 = settableFuture;
        this.f$1 = listenableFuture;
    }

    public final void run() {
        MediaLibraryServiceLegacyStub.lambda$createMediaItemToBrowserItemAsyncFunction$13(this.f$0, this.f$1);
    }
}
