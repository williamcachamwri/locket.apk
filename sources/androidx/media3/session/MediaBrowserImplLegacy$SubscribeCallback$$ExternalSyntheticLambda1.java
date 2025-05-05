package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaBrowserImplLegacy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaBrowserImplLegacy$SubscribeCallback$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ MediaBrowserImplLegacy.SubscribeCallback f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Bundle f$2;

    public /* synthetic */ MediaBrowserImplLegacy$SubscribeCallback$$ExternalSyntheticLambda1(MediaBrowserImplLegacy.SubscribeCallback subscribeCallback, String str, Bundle bundle) {
        this.f$0 = subscribeCallback;
        this.f$1 = str;
        this.f$2 = bundle;
    }

    public final void accept(Object obj) {
        this.f$0.m920lambda$onErrorInternal$0$androidxmedia3sessionMediaBrowserImplLegacy$SubscribeCallback(this.f$1, this.f$2, (MediaBrowser.Listener) obj);
    }
}
