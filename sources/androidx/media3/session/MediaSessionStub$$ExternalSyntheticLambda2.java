package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda2 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ MediaItem f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda2(MediaItem mediaItem, boolean z) {
        this.f$0 = mediaItem;
        this.f$1 = z;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return MediaSessionStub.lambda$setMediaItemWithResetPosition$30(this.f$0, this.f$1, mediaSessionImpl, controllerInfo, i);
    }
}
