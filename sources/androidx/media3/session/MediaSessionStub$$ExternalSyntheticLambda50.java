package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda50 implements MediaSessionStub.MediaItemPlayerTask {
    public final /* synthetic */ MediaSessionStub f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda50(MediaSessionStub mediaSessionStub, int i) {
        this.f$0 = mediaSessionStub;
        this.f$1 = i;
    }

    public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        this.f$0.m1103lambda$addMediaItemsWithIndex$41$androidxmedia3sessionMediaSessionStub(this.f$1, playerWrapper, controllerInfo, list);
    }
}
