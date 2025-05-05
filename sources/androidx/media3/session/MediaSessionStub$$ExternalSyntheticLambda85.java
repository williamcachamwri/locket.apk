package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda85 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ List f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda85(List list) {
        this.f$0 = list;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, this.f$0);
    }
}
