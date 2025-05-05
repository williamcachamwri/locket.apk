package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda70 implements Runnable {
    public final /* synthetic */ MediaSessionStub f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ SessionCommand f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ MediaSessionStub.SessionTask f$5;
    public final /* synthetic */ MediaSessionImpl f$6;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda70(MediaSessionStub mediaSessionStub, MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, int i, int i2, MediaSessionStub.SessionTask sessionTask, MediaSessionImpl mediaSessionImpl) {
        this.f$0 = mediaSessionStub;
        this.f$1 = controllerInfo;
        this.f$2 = sessionCommand;
        this.f$3 = i;
        this.f$4 = i2;
        this.f$5 = sessionTask;
        this.f$6 = mediaSessionImpl;
    }

    public final void run() {
        this.f$0.m1105lambda$dispatchSessionTaskWithSessionCommand$15$androidxmedia3sessionMediaSessionStub(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
