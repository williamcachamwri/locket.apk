package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaControllerImplLegacy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplLegacy$$ExternalSyntheticLambda9 implements ListenerSet.Event {
    public final /* synthetic */ MediaControllerImplLegacy.ControllerInfo f$0;
    public final /* synthetic */ MediaControllerImplLegacy.ControllerInfo f$1;
    public final /* synthetic */ Integer f$2;

    public /* synthetic */ MediaControllerImplLegacy$$ExternalSyntheticLambda9(MediaControllerImplLegacy.ControllerInfo controllerInfo, MediaControllerImplLegacy.ControllerInfo controllerInfo2, Integer num) {
        this.f$0 = controllerInfo;
        this.f$1 = controllerInfo2;
        this.f$2 = num;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onPositionDiscontinuity(this.f$0.playerInfo.sessionPositionInfo.positionInfo, this.f$1.playerInfo.sessionPositionInfo.positionInfo, this.f$2.intValue());
    }
}
