package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ MediaLibraryServiceLegacyStub f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ MediaSession.ControllerInfo f$2;
    public final /* synthetic */ MediaBrowserServiceCompat.Result f$3;
    public final /* synthetic */ Bundle f$4;

    public /* synthetic */ MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda13(MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub, String str, MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, Bundle bundle) {
        this.f$0 = mediaLibraryServiceLegacyStub;
        this.f$1 = str;
        this.f$2 = controllerInfo;
        this.f$3 = result;
        this.f$4 = bundle;
    }

    public final void run() {
        this.f$0.m1027lambda$onCustomAction$6$androidxmedia3sessionMediaLibraryServiceLegacyStub(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
