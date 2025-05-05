package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaBrowserImplLegacy;
import androidx.media3.session.MediaLibraryService;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaBrowserImplLegacy$SubscribeCallback$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ MediaBrowserImplLegacy.SubscribeCallback f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ MediaLibraryService.LibraryParams f$3;

    public /* synthetic */ MediaBrowserImplLegacy$SubscribeCallback$$ExternalSyntheticLambda0(MediaBrowserImplLegacy.SubscribeCallback subscribeCallback, String str, List list, MediaLibraryService.LibraryParams libraryParams) {
        this.f$0 = subscribeCallback;
        this.f$1 = str;
        this.f$2 = list;
        this.f$3 = libraryParams;
    }

    public final void accept(Object obj) {
        this.f$0.m919lambda$onChildrenLoadedInternal$1$androidxmedia3sessionMediaBrowserImplLegacy$SubscribeCallback(this.f$1, this.f$2, this.f$3, (MediaBrowser.Listener) obj);
    }
}
