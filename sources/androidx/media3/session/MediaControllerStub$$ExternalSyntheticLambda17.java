package androidx.media3.session;

import androidx.media3.session.MediaControllerStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda17 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ SessionPositionInfo f$0;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda17(SessionPositionInfo sessionPositionInfo) {
        this.f$0 = sessionPositionInfo;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.notifyPeriodicSessionPositionInfoChanged(this.f$0);
    }
}
