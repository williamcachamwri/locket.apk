package androidx.media3.exoplayer;

import androidx.media3.common.Metadata;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda2 implements ListenerSet.Event {
    public final /* synthetic */ Metadata f$0;

    public /* synthetic */ ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda2(Metadata metadata) {
        this.f$0 = metadata;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onMetadata(this.f$0);
    }
}
