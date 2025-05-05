package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda59 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ MediaSessionStub.SessionTask f$0;
    public final /* synthetic */ MediaSessionStub.MediaItemsWithStartPositionPlayerTask f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda59(MediaSessionStub.SessionTask sessionTask, MediaSessionStub.MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask) {
        this.f$0 = sessionTask;
        this.f$1 = mediaItemsWithStartPositionPlayerTask;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return MediaSessionStub.lambda$handleMediaItemsWithStartPositionWhenReady$9(this.f$0, this.f$1, mediaSessionImpl, controllerInfo, i);
    }
}
