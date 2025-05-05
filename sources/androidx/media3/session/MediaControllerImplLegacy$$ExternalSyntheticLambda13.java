package androidx.media3.session;

import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplLegacy$$ExternalSyntheticLambda13 implements ListenerSet.Event {
    public final /* synthetic */ PlaybackException f$0;

    public /* synthetic */ MediaControllerImplLegacy$$ExternalSyntheticLambda13(PlaybackException playbackException) {
        this.f$0 = playbackException;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onPlayerError(this.f$0);
    }
}
