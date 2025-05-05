package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda32 implements Runnable {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ MediaSessionStub.MediaItemsWithStartPositionPlayerTask f$1;
    public final /* synthetic */ MediaSession.MediaItemsWithStartPosition f$2;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda32(MediaSessionImpl mediaSessionImpl, MediaSessionStub.MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
        this.f$0 = mediaSessionImpl;
        this.f$1 = mediaItemsWithStartPositionPlayerTask;
        this.f$2 = mediaItemsWithStartPosition;
    }

    public final void run() {
        MediaSessionStub.lambda$handleMediaItemsWithStartPositionWhenReady$7(this.f$0, this.f$1, this.f$2);
    }
}
