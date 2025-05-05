package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.ListenerSet;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda0 implements ListenerSet.Event {
    public final /* synthetic */ List f$0;

    public /* synthetic */ ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda0(List list) {
        this.f$0 = list;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onCues((List<Cue>) this.f$0);
    }
}
