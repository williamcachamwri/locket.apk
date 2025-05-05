package androidx.media3.session;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionService$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MediaNotificationManager f$0;
    public final /* synthetic */ MediaSession f$1;

    public /* synthetic */ MediaSessionService$$ExternalSyntheticLambda1(MediaNotificationManager mediaNotificationManager, MediaSession mediaSession) {
        this.f$0 = mediaNotificationManager;
        this.f$1 = mediaSession;
    }

    public final void run() {
        MediaSessionService.lambda$removeSession$1(this.f$0, this.f$1);
    }
}
