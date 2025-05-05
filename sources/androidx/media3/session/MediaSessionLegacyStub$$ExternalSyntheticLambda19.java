package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$$ExternalSyntheticLambda19 implements Runnable {
    public final /* synthetic */ MediaSessionLegacyStub.SessionTask f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;

    public /* synthetic */ MediaSessionLegacyStub$$ExternalSyntheticLambda19(MediaSessionLegacyStub.SessionTask sessionTask, MediaSession.ControllerInfo controllerInfo) {
        this.f$0 = sessionTask;
        this.f$1 = controllerInfo;
    }

    public final void run() {
        MediaSessionLegacyStub.lambda$dispatchSessionTaskWithPlayerCommand$20(this.f$0, this.f$1);
    }
}
