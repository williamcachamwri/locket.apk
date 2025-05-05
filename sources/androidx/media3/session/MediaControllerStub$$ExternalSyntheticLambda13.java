package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.session.MediaControllerStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda13 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ Player.Commands f$0;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda13(Player.Commands commands) {
        this.f$0 = commands;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onAvailableCommandsChangedFromPlayer(this.f$0);
    }
}
