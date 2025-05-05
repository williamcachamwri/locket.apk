package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda82 implements MediaSessionStub.ControllerPlayerTask {
    public final /* synthetic */ MediaSessionStub f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda82(MediaSessionStub mediaSessionStub, int i, int i2) {
        this.f$0 = mediaSessionStub;
        this.f$1 = i;
        this.f$2 = i2;
    }

    public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        this.f$0.m1111lambda$removeMediaItems$43$androidxmedia3sessionMediaSessionStub(this.f$1, this.f$2, playerWrapper, controllerInfo);
    }
}
