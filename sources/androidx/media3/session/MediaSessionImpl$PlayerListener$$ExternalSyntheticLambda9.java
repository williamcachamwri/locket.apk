package androidx.media3.session;

import androidx.media3.common.VideoSize;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda9 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ VideoSize f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda9(VideoSize videoSize) {
        this.f$0 = videoSize;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onVideoSizeChanged(i, this.f$0);
    }
}
