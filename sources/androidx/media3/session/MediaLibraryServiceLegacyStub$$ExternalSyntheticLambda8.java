package androidx.media3.session;

import androidx.media3.common.MediaItem;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ ListenableFuture f$0;
    public final /* synthetic */ SettableFuture f$1;
    public final /* synthetic */ MediaItem f$2;

    public /* synthetic */ MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda8(ListenableFuture listenableFuture, SettableFuture settableFuture, MediaItem mediaItem) {
        this.f$0 = listenableFuture;
        this.f$1 = settableFuture;
        this.f$2 = mediaItem;
    }

    public final void run() {
        MediaLibraryServiceLegacyStub.lambda$createMediaItemToBrowserItemAsyncFunction$14(this.f$0, this.f$1, this.f$2);
    }
}
