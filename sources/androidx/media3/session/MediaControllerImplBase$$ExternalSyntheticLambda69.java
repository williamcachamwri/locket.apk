package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.ListenerSet;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda69 implements ListenerSet.Event {
    public final /* synthetic */ PlayerInfo f$0;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda69(PlayerInfo playerInfo) {
        this.f$0 = playerInfo;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onCues((List<Cue>) this.f$0.cueGroup.cues);
    }
}
