package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda54 implements Runnable {
    public final /* synthetic */ MediaSessionStub.SessionTask f$0;
    public final /* synthetic */ MediaSessionImpl f$1;
    public final /* synthetic */ MediaSession.ControllerInfo f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda54(MediaSessionStub.SessionTask sessionTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        this.f$0 = sessionTask;
        this.f$1 = mediaSessionImpl;
        this.f$2 = controllerInfo;
        this.f$3 = i;
    }

    public final void run() {
        this.f$0.run(this.f$1, this.f$2, this.f$3);
    }
}
