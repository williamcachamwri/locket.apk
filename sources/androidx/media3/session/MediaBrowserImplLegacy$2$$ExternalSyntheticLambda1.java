package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaBrowserImplLegacy;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaBrowserImplLegacy$2$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ MediaBrowserImplLegacy.AnonymousClass2 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ List f$2;

    public /* synthetic */ MediaBrowserImplLegacy$2$$ExternalSyntheticLambda1(MediaBrowserImplLegacy.AnonymousClass2 r1, String str, List list) {
        this.f$0 = r1;
        this.f$1 = str;
        this.f$2 = list;
    }

    public final void accept(Object obj) {
        this.f$0.m918lambda$onSearchResult$0$androidxmedia3sessionMediaBrowserImplLegacy$2(this.f$1, this.f$2, (MediaBrowser.Listener) obj);
    }
}
