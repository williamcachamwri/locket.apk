package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionStub f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ MediaSessionImpl f$4;
    public final /* synthetic */ MediaSessionStub.SessionTask f$5;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda0(MediaSessionStub mediaSessionStub, MediaSession.ControllerInfo controllerInfo, int i, int i2, MediaSessionImpl mediaSessionImpl, MediaSessionStub.SessionTask sessionTask) {
        this.f$0 = mediaSessionStub;
        this.f$1 = controllerInfo;
        this.f$2 = i;
        this.f$3 = i2;
        this.f$4 = mediaSessionImpl;
        this.f$5 = sessionTask;
    }

    public final void run() {
        this.f$0.m1108lambda$queueSessionTaskWithPlayerCommandForControllerInfo$14$androidxmedia3sessionMediaSessionStub(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
