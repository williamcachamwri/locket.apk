package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda9 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda9(List list, boolean z) {
        this.f$0 = list;
        this.f$1 = z;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return MediaSessionStub.lambda$setMediaItemsWithResetPosition$31(this.f$0, this.f$1, mediaSessionImpl, controllerInfo, i);
    }
}
