package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda18 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ MediaSessionStub.ControllerPlayerTask f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda18(MediaSessionStub.ControllerPlayerTask controllerPlayerTask) {
        this.f$0 = controllerPlayerTask;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return MediaSessionStub.lambda$sendSessionResultSuccess$1(this.f$0, mediaSessionImpl, controllerInfo, i);
    }
}
