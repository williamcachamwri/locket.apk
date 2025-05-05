package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaLibraryService;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaBrowserImplBase$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ MediaBrowserImplBase f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ MediaLibraryService.LibraryParams f$3;

    public /* synthetic */ MediaBrowserImplBase$$ExternalSyntheticLambda0(MediaBrowserImplBase mediaBrowserImplBase, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        this.f$0 = mediaBrowserImplBase;
        this.f$1 = str;
        this.f$2 = i;
        this.f$3 = libraryParams;
    }

    public final void accept(Object obj) {
        this.f$0.m915lambda$notifyChildrenChanged$1$androidxmedia3sessionMediaBrowserImplBase(this.f$1, this.f$2, this.f$3, (MediaBrowser.Listener) obj);
    }
}
