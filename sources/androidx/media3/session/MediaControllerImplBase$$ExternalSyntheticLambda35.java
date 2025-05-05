package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda35 implements ListenerSet.Event {
    public final /* synthetic */ float f$0;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda35(float f) {
        this.f$0 = f;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onVolumeChanged(this.f$0);
    }
}
