package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda6 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ float f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda6(float f) {
        this.f$0 = f;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onVolumeChanged(i, this.f$0);
    }
}
