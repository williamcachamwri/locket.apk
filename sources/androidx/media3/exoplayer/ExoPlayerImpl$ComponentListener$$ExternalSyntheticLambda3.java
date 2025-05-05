package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda3 implements ListenerSet.Event {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda3(int i, boolean z) {
        this.f$0 = i;
        this.f$1 = z;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onDeviceVolumeChanged(this.f$0, this.f$1);
    }
}
