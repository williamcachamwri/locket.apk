package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda64 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ String f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda64(String str) {
        this.f$0 = str;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return ((MediaLibrarySessionImpl) mediaSessionImpl).onGetItemOnHandler(controllerInfo, this.f$0);
    }
}
