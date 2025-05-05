package androidx.media3.session;

import androidx.media3.common.Tracks;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda3 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ Tracks f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda3(Tracks tracks) {
        this.f$0 = tracks;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onTracksChanged(i, this.f$0);
    }
}
