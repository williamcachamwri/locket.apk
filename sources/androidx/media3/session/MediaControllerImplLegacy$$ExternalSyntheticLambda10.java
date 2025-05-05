package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaControllerImplLegacy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplLegacy$$ExternalSyntheticLambda10 implements ListenerSet.Event {
    public final /* synthetic */ MediaControllerImplLegacy.ControllerInfo f$0;
    public final /* synthetic */ Integer f$1;

    public /* synthetic */ MediaControllerImplLegacy$$ExternalSyntheticLambda10(MediaControllerImplLegacy.ControllerInfo controllerInfo, Integer num) {
        this.f$0 = controllerInfo;
        this.f$1 = num;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onMediaItemTransition(this.f$0.playerInfo.getCurrentMediaItem(), this.f$1.intValue());
    }
}
