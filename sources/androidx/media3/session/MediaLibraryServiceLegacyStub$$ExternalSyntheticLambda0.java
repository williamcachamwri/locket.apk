package androidx.media3.session;

import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ListenableFuture f$0;
    public final /* synthetic */ MediaBrowserServiceCompat.Result f$1;

    public /* synthetic */ MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda0(ListenableFuture listenableFuture, MediaBrowserServiceCompat.Result result) {
        this.f$0 = listenableFuture;
        this.f$1 = result;
    }

    public final void run() {
        MediaLibraryServiceLegacyStub.lambda$sendCustomActionResultWhenReady$7(this.f$0, this.f$1);
    }
}
