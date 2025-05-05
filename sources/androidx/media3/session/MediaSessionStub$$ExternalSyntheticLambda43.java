package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import com.google.common.collect.ImmutableList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda43 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ MediaItem f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda43(MediaItem mediaItem) {
        this.f$0 = mediaItem;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, ImmutableList.of(this.f$0));
    }
}
