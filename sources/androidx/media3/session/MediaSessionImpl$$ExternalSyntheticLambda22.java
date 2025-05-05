package androidx.media3.session;

import androidx.media3.session.MediaSession;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda22 implements Runnable {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda22(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, Runnable runnable) {
        this.f$0 = mediaSessionImpl;
        this.f$1 = controllerInfo;
        this.f$2 = runnable;
    }

    public final void run() {
        this.f$0.m1060lambda$callWithControllerForCurrentRequestSet$3$androidxmedia3sessionMediaSessionImpl(this.f$1, this.f$2);
    }
}
