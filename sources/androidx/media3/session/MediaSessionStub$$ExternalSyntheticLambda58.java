package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda58 implements MediaSessionStub.ControllerPlayerTask {
    public final /* synthetic */ MediaSessionStub f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda58(MediaSessionStub mediaSessionStub, int i) {
        this.f$0 = mediaSessionStub;
        this.f$1 = i;
    }

    public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        this.f$0.m1110lambda$removeMediaItem$42$androidxmedia3sessionMediaSessionStub(this.f$1, playerWrapper, controllerInfo);
    }
}
