package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda10 implements ListenerSet.Event {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda10(int i, int i2) {
        this.f$0 = i;
        this.f$1 = i2;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onSurfaceSizeChanged(this.f$0, this.f$1);
    }
}
