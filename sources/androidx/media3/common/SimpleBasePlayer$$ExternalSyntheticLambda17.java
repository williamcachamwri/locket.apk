package androidx.media3.common;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda17 implements ListenerSet.Event {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Player.PositionInfo f$1;
    public final /* synthetic */ Player.PositionInfo f$2;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda17(int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2) {
        this.f$0 = i;
        this.f$1 = positionInfo;
        this.f$2 = positionInfo2;
    }

    public final void invoke(Object obj) {
        SimpleBasePlayer.lambda$updateStateAndInformListeners$33(this.f$0, this.f$1, this.f$2, (Player.Listener) obj);
    }
}
