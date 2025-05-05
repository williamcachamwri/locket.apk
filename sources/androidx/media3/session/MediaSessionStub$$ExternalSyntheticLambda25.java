package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda25 implements MediaSessionStub.ControllerPlayerTask {
    public final /* synthetic */ Consumer f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda25(Consumer consumer) {
        this.f$0 = consumer;
    }

    public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        this.f$0.accept(playerWrapper);
    }
}
