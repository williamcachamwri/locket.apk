package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda80 implements ListenerSet.Event {
    public final /* synthetic */ MediaItem f$0;
    public final /* synthetic */ Integer f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda80(MediaItem mediaItem, Integer num) {
        this.f$0 = mediaItem;
        this.f$1 = num;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onMediaItemTransition(this.f$0, this.f$1.intValue());
    }
}
