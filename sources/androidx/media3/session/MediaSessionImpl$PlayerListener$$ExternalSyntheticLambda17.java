package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda17 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ MediaItem f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda17(MediaItem mediaItem, int i) {
        this.f$0 = mediaItem;
        this.f$1 = i;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onMediaItemTransition(i, this.f$0, this.f$1);
    }
}
