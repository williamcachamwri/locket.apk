package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionImpl.AnonymousClass1 f$0;
    public final /* synthetic */ MediaSession.MediaItemsWithStartPosition f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ MediaSession.ControllerInfo f$3;
    public final /* synthetic */ Player.Commands f$4;

    public /* synthetic */ MediaSessionImpl$1$$ExternalSyntheticLambda0(MediaSessionImpl.AnonymousClass1 r1, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition, boolean z, MediaSession.ControllerInfo controllerInfo, Player.Commands commands) {
        this.f$0 = r1;
        this.f$1 = mediaItemsWithStartPosition;
        this.f$2 = z;
        this.f$3 = controllerInfo;
        this.f$4 = commands;
    }

    public final void run() {
        this.f$0.m1065lambda$onSuccess$0$androidxmedia3sessionMediaSessionImpl$1(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
