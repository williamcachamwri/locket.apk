package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda2 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ long f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda2(long j) {
        this.f$0 = j;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onSeekBackIncrementChanged(i, this.f$0);
    }
}
