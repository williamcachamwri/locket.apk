package androidx.media3.session;

import androidx.media3.session.MediaSession;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda69 implements Runnable {
    public final /* synthetic */ MediaSessionStub f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ MediaSessionImpl f$2;
    public final /* synthetic */ IMediaController f$3;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda69(MediaSessionStub mediaSessionStub, MediaSession.ControllerInfo controllerInfo, MediaSessionImpl mediaSessionImpl, IMediaController iMediaController) {
        this.f$0 = mediaSessionStub;
        this.f$1 = controllerInfo;
        this.f$2 = mediaSessionImpl;
        this.f$3 = iMediaController;
    }

    public final void run() {
        this.f$0.m1104lambda$connect$17$androidxmedia3sessionMediaSessionStub(this.f$1, this.f$2, this.f$3);
    }
}
