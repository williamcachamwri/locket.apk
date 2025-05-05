package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda15 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ SessionCommands f$0;
    public final /* synthetic */ Player.Commands f$1;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda15(SessionCommands sessionCommands, Player.Commands commands) {
        this.f$0 = sessionCommands;
        this.f$1 = commands;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onAvailableCommandsChangedFromSession(i, this.f$0, this.f$1);
    }
}
