package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda61 implements MediaSessionStub.ControllerPlayerTask {
    public final /* synthetic */ MediaSessionStub f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda61(MediaSessionStub mediaSessionStub, int i, long j) {
        this.f$0 = mediaSessionStub;
        this.f$1 = i;
        this.f$2 = j;
    }

    public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        this.f$0.m1115lambda$seekToWithMediaItemIndex$23$androidxmedia3sessionMediaSessionStub(this.f$1, this.f$2, playerWrapper, controllerInfo);
    }
}
