package androidx.media3.session;

import com.google.common.util.concurrent.SettableFuture;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ SettableFuture f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda3(SettableFuture settableFuture, List list) {
        this.f$0 = settableFuture;
        this.f$1 = list;
    }

    public final void run() {
        MediaLibraryServiceLegacyStub.lambda$createMediaItemsToBrowserItemsAsyncFunction$10(this.f$0, this.f$1);
    }
}
