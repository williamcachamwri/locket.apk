package androidx.media3.session;

import androidx.media3.common.PlaybackParameters;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda23 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ PlaybackParameters f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda23(PlaybackParameters playbackParameters) {
        this.f$0 = playbackParameters;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onPlaybackParametersChanged(i, this.f$0);
    }
}
