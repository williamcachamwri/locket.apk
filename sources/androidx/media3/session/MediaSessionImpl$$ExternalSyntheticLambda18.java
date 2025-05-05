package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda18 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ PlayerWrapper f$0;
    public final /* synthetic */ PlayerWrapper f$1;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda18(PlayerWrapper playerWrapper, PlayerWrapper playerWrapper2) {
        this.f$0 = playerWrapper;
        this.f$1 = playerWrapper2;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onPlayerChanged(i, this.f$0, this.f$1);
    }
}
