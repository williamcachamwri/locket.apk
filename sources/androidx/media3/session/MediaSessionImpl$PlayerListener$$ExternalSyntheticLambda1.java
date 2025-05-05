package androidx.media3.session;

import androidx.media3.common.MediaMetadata;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda1 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ MediaMetadata f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda1(MediaMetadata mediaMetadata) {
        this.f$0 = mediaMetadata;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onPlaylistMetadataChanged(i, this.f$0);
    }
}
