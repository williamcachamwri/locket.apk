package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda20 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda20(boolean z, int i) {
        this.f$0 = z;
        this.f$1 = i;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onPlayWhenReadyChanged(i, this.f$0, this.f$1);
    }
}
