package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda73 implements Runnable {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ MediaSessionStub.MediaItemPlayerTask f$1;
    public final /* synthetic */ MediaSession.ControllerInfo f$2;
    public final /* synthetic */ List f$3;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda73(MediaSessionImpl mediaSessionImpl, MediaSessionStub.MediaItemPlayerTask mediaItemPlayerTask, MediaSession.ControllerInfo controllerInfo, List list) {
        this.f$0 = mediaSessionImpl;
        this.f$1 = mediaItemPlayerTask;
        this.f$2 = controllerInfo;
        this.f$3 = list;
    }

    public final void run() {
        MediaSessionStub.lambda$handleMediaItemsWhenReady$4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
