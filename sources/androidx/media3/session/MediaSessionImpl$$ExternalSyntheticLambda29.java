package androidx.media3.session;

import androidx.media3.session.MediaSession;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda29 implements Runnable {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ MediaSession.ControllerInfo f$2;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda29(MediaSessionImpl mediaSessionImpl, Runnable runnable, MediaSession.ControllerInfo controllerInfo) {
        this.f$0 = mediaSessionImpl;
        this.f$1 = runnable;
        this.f$2 = controllerInfo;
    }

    public final void run() {
        this.f$0.m1059lambda$applyMediaButtonKeyEvent$33$androidxmedia3sessionMediaSessionImpl(this.f$1, this.f$2);
    }
}
