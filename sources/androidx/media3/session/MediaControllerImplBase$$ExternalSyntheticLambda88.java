package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda88 implements ListenerSet.Event {
    public final /* synthetic */ PlayerInfo f$0;
    public final /* synthetic */ Integer f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda88(PlayerInfo playerInfo, Integer num) {
        this.f$0 = playerInfo;
        this.f$1 = num;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onPlayWhenReadyChanged(this.f$0.playWhenReady, this.f$1.intValue());
    }
}
