package androidx.media3.session;

import android.view.KeyEvent;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$MediaPlayPauseKeyHandler$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionImpl.MediaPlayPauseKeyHandler f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ KeyEvent f$2;

    public /* synthetic */ MediaSessionImpl$MediaPlayPauseKeyHandler$$ExternalSyntheticLambda0(MediaSessionImpl.MediaPlayPauseKeyHandler mediaPlayPauseKeyHandler, MediaSession.ControllerInfo controllerInfo, KeyEvent keyEvent) {
        this.f$0 = mediaPlayPauseKeyHandler;
        this.f$1 = controllerInfo;
        this.f$2 = keyEvent;
    }

    public final void run() {
        this.f$0.m1066lambda$setPendingPlayPauseTask$0$androidxmedia3sessionMediaSessionImpl$MediaPlayPauseKeyHandler(this.f$1, this.f$2);
    }
}
