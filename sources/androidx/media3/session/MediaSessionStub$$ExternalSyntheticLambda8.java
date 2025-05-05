package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda8 implements MediaSessionStub.ControllerPlayerTask {
    public final /* synthetic */ MediaSessionStub f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda8(MediaSessionStub mediaSessionStub, int i) {
        this.f$0 = mediaSessionStub;
        this.f$1 = i;
    }

    public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        this.f$0.m1114lambda$seekToDefaultPositionWithMediaItemIndex$21$androidxmedia3sessionMediaSessionStub(this.f$1, playerWrapper, controllerInfo);
    }
}
