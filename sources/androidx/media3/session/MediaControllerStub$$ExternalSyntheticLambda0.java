package androidx.media3.session;

import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.PlayerInfo;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda0 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ PlayerInfo f$0;
    public final /* synthetic */ PlayerInfo.BundlingExclusions f$1;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda0(PlayerInfo playerInfo, PlayerInfo.BundlingExclusions bundlingExclusions) {
        this.f$0 = playerInfo;
        this.f$1 = bundlingExclusions;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onPlayerInfoChanged(this.f$0, this.f$1);
    }
}
