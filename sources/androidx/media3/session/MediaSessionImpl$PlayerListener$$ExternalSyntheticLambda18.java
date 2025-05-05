package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda18 implements MediaSessionImpl.RemoteControllerTask {
    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onRenderedFirstFrame(i);
    }
}
