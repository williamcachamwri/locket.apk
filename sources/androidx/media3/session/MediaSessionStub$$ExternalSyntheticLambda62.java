package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import com.google.common.collect.ImmutableList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda62 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ MediaItem f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda62(MediaItem mediaItem, long j) {
        this.f$0 = mediaItem;
        this.f$1 = j;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, ImmutableList.of(this.f$0), 0, this.f$1);
    }
}
