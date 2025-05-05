package androidx.media3.session;

import androidx.media3.session.MediaSession;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda41 implements Runnable {
    public final /* synthetic */ MediaSessionStub f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda41(MediaSessionStub mediaSessionStub, MediaSession.ControllerInfo controllerInfo) {
        this.f$0 = mediaSessionStub;
        this.f$1 = controllerInfo;
    }

    public final void run() {
        this.f$0.m1106lambda$flushCommandQueue$64$androidxmedia3sessionMediaSessionStub(this.f$1);
    }
}
