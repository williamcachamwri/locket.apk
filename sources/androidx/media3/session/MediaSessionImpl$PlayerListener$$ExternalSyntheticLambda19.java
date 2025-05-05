package androidx.media3.session;

import androidx.media3.common.PlaybackException;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda19 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ PlaybackException f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda19(PlaybackException playbackException) {
        this.f$0 = playbackException;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onPlayerError(i, this.f$0);
    }
}
