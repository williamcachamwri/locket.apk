package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda39 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ MediaSessionStub.SessionTask f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda39(MediaSessionStub.SessionTask sessionTask) {
        this.f$0 = sessionTask;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return MediaSessionStub.handleSessionTaskWhenReady(mediaSessionImpl, controllerInfo, i, this.f$0, new MediaSessionStub$$ExternalSyntheticLambda35(controllerInfo, i));
    }
}
