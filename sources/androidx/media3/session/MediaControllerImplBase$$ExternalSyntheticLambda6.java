package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda6 implements ListenerSet.Event {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda6(MediaControllerImplBase mediaControllerImplBase, int i) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = i;
    }

    public final void invoke(Object obj) {
        this.f$0.m982lambda$setDeviceVolume$54$androidxmedia3sessionMediaControllerImplBase(this.f$1, (Player.Listener) obj);
    }
}
