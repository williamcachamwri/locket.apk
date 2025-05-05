package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda12 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ Player.PositionInfo f$0;
    public final /* synthetic */ Player.PositionInfo f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda12(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
        this.f$0 = positionInfo;
        this.f$1 = positionInfo2;
        this.f$2 = i;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onPositionDiscontinuity(i, this.f$0, this.f$1, this.f$2);
    }
}
