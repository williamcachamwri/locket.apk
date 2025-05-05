package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda65 implements ListenerSet.Event {
    public final /* synthetic */ PlayerInfo f$0;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda65(PlayerInfo playerInfo) {
        this.f$0 = playerInfo;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onShuffleModeEnabledChanged(this.f$0.shuffleModeEnabled);
    }
}
