package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.session.MediaControllerStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda2 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ SessionCommands f$0;
    public final /* synthetic */ Player.Commands f$1;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda2(SessionCommands sessionCommands, Player.Commands commands) {
        this.f$0 = sessionCommands;
        this.f$1 = commands;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onAvailableCommandsChangedFromSession(this.f$0, this.f$1);
    }
}
