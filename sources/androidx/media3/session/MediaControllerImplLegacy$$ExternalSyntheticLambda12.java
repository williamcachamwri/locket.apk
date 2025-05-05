package androidx.media3.session;

import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplLegacy$$ExternalSyntheticLambda12 implements ListenerSet.Event {
    public final /* synthetic */ PlaybackException f$0;

    public /* synthetic */ MediaControllerImplLegacy$$ExternalSyntheticLambda12(PlaybackException playbackException) {
        this.f$0 = playbackException;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onPlayerErrorChanged(this.f$0);
    }
}
