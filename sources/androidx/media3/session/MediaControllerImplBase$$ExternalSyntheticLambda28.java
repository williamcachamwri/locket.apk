package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda28 implements ListenerSet.Event {
    public final /* synthetic */ int f$0;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda28(int i) {
        this.f$0 = i;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onRepeatModeChanged(this.f$0);
    }
}
