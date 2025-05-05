package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda4 implements ListenerSet.Event {
    public final /* synthetic */ CueGroup f$0;

    public /* synthetic */ ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda4(CueGroup cueGroup) {
        this.f$0 = cueGroup;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onCues(this.f$0);
    }
}
