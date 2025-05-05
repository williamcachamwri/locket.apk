package androidx.media3.session;

import android.app.PendingIntent;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda35 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ PendingIntent f$0;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda35(PendingIntent pendingIntent) {
        this.f$0 = pendingIntent;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onSessionActivityChanged(i, this.f$0);
    }
}
