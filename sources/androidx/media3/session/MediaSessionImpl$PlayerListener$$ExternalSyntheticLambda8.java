package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda8 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ boolean f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda8(boolean z) {
        this.f$0 = z;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onIsPlayingChanged(i, this.f$0);
    }
}
