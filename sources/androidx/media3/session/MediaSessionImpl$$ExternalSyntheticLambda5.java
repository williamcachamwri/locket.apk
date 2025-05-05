package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda5 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ SessionPositionInfo f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ MediaSession.ControllerInfo f$3;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda5(SessionPositionInfo sessionPositionInfo, boolean z, boolean z2, MediaSession.ControllerInfo controllerInfo) {
        this.f$0 = sessionPositionInfo;
        this.f$1 = z;
        this.f$2 = z2;
        this.f$3 = controllerInfo;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onPeriodicSessionPositionInfoChanged(i, this.f$0, this.f$1, this.f$2, this.f$3.getInterfaceVersion());
    }
}
