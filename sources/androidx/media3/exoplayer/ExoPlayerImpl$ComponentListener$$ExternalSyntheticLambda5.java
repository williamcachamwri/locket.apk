package androidx.media3.exoplayer;

import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda5 implements ListenerSet.Event {
    public final /* synthetic */ DeviceInfo f$0;

    public /* synthetic */ ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda5(DeviceInfo deviceInfo) {
        this.f$0 = deviceInfo;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onDeviceInfoChanged(this.f$0);
    }
}
