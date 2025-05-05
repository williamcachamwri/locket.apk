package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda1 implements MediaSessionStub.MediaItemPlayerTask {
    public final void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.addMediaItems(list);
    }
}
