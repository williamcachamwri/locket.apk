package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda7 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ int f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda7(int i) {
        this.f$0 = i;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onPlaybackSuppressionReasonChanged(i, this.f$0);
    }
}
