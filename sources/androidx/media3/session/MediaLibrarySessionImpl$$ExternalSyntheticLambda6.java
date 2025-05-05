package androidx.media3.session;

import androidx.media3.session.MediaSession;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibrarySessionImpl$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ MediaLibrarySessionImpl f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ MediaLibrarySessionImpl$$ExternalSyntheticLambda6(MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaSession.ControllerInfo controllerInfo, String str) {
        this.f$0 = mediaLibrarySessionImpl;
        this.f$1 = controllerInfo;
        this.f$2 = str;
    }

    public final void run() {
        this.f$0.m1041lambda$onUnsubscribeOnHandler$3$androidxmedia3sessionMediaLibrarySessionImpl(this.f$1, this.f$2);
    }
}
