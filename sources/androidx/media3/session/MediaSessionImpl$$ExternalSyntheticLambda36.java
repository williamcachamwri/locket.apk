package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda36 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ SessionError f$0;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda36(SessionError sessionError) {
        this.f$0 = sessionError;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onError(i, this.f$0);
    }
}
