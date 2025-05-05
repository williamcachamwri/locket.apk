package androidx.media3.session;

import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda14 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ TrackSelectionParameters f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda14(TrackSelectionParameters trackSelectionParameters) {
        this.f$0 = trackSelectionParameters;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onTrackSelectionParametersChanged(i, this.f$0);
    }
}
