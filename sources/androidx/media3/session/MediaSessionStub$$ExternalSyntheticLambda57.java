package androidx.media3.session;

import androidx.media3.session.ConnectedControllersManager;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda57 implements ConnectedControllersManager.AsyncCommand {
    public final /* synthetic */ MediaSessionStub.SessionTask f$0;
    public final /* synthetic */ MediaSessionImpl f$1;
    public final /* synthetic */ MediaSession.ControllerInfo f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda57(MediaSessionStub.SessionTask sessionTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        this.f$0 = sessionTask;
        this.f$1 = mediaSessionImpl;
        this.f$2 = controllerInfo;
        this.f$3 = i;
    }

    public final ListenableFuture run() {
        return MediaSessionStub.lambda$queueSessionTaskWithPlayerCommandForControllerInfo$13(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
