package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda71 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda71(List list, int i, long j) {
        this.f$0 = list;
        this.f$1 = i;
        this.f$2 = j;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return MediaSessionStub.lambda$setMediaItemsWithStartIndex$32(this.f$0, this.f$1, this.f$2, mediaSessionImpl, controllerInfo, i);
    }
}
