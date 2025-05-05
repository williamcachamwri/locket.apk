package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda21 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ SessionCommand f$0;
    public final /* synthetic */ Bundle f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda21(SessionCommand sessionCommand, Bundle bundle) {
        this.f$0 = sessionCommand;
        this.f$1 = bundle;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return mediaSessionImpl.onCustomCommandOnHandler(controllerInfo, this.f$0, this.f$1);
    }
}
