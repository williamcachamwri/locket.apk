package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda11 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ PlayerWrapper f$1;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda11(int i, PlayerWrapper playerWrapper) {
        this.f$0 = i;
        this.f$1 = playerWrapper;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onPlaybackStateChanged(i, this.f$0, this.f$1.getPlayerError());
    }
}
