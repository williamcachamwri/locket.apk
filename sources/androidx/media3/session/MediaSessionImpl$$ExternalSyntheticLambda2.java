package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda2 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ Bundle f$0;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda2(Bundle bundle) {
        this.f$0 = bundle;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onSessionExtrasChanged(i, this.f$0);
    }
}
