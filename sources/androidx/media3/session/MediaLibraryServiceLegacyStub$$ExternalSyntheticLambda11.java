package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ MediaLibraryServiceLegacyStub f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ MediaBrowserServiceCompat.Result f$2;
    public final /* synthetic */ Bundle f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda11(MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub, MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, Bundle bundle, String str) {
        this.f$0 = mediaLibraryServiceLegacyStub;
        this.f$1 = controllerInfo;
        this.f$2 = result;
        this.f$3 = bundle;
        this.f$4 = str;
    }

    public final void run() {
        this.f$0.m1029lambda$onLoadChildren$3$androidxmedia3sessionMediaLibraryServiceLegacyStub(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
