package androidx.media3.session;

import androidx.media3.common.Timeline;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda13 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ Timeline f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda13(Timeline timeline, int i) {
        this.f$0 = timeline;
        this.f$1 = i;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onTimelineChanged(i, this.f$0, this.f$1);
    }
}
