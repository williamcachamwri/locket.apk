package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda11 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ SessionCommand f$0;
    public final /* synthetic */ Bundle f$1;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda11(SessionCommand sessionCommand, Bundle bundle) {
        this.f$0 = sessionCommand;
        this.f$1 = bundle;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.sendCustomCommand(i, this.f$0, this.f$1);
    }
}
