package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ MediaLibraryServiceLegacyStub f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ MediaBrowserServiceCompat.Result f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda10(MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub, MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, String str) {
        this.f$0 = mediaLibraryServiceLegacyStub;
        this.f$1 = controllerInfo;
        this.f$2 = result;
        this.f$3 = str;
    }

    public final void run() {
        this.f$0.m1030lambda$onLoadItem$4$androidxmedia3sessionMediaLibraryServiceLegacyStub(this.f$1, this.f$2, this.f$3);
    }
}
