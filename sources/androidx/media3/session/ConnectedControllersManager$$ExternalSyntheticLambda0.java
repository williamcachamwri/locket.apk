package androidx.media3.session;

import androidx.media3.session.MediaSession;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConnectedControllersManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;

    public /* synthetic */ ConnectedControllersManager$$ExternalSyntheticLambda0(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo) {
        this.f$0 = mediaSessionImpl;
        this.f$1 = controllerInfo;
    }

    public final void run() {
        ConnectedControllersManager.lambda$removeController$0(this.f$0, this.f$1);
    }
}
