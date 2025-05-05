package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda30 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ Player.Commands f$0;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda30(Player.Commands commands) {
        this.f$0 = commands;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onAvailableCommandsChangedFromPlayer(i, this.f$0);
    }
}
