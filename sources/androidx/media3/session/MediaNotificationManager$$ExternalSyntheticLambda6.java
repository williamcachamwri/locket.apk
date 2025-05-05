package androidx.media3.session;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaNotificationManager$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ MediaNotificationManager f$0;
    public final /* synthetic */ MediaSession f$1;
    public final /* synthetic */ MediaNotification f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ MediaNotificationManager$$ExternalSyntheticLambda6(MediaNotificationManager mediaNotificationManager, MediaSession mediaSession, MediaNotification mediaNotification, boolean z) {
        this.f$0 = mediaNotificationManager;
        this.f$1 = mediaSession;
        this.f$2 = mediaNotification;
        this.f$3 = z;
    }

    public final void run() {
        this.f$0.m1047lambda$updateNotification$6$androidxmedia3sessionMediaNotificationManager(this.f$1, this.f$2, this.f$3);
    }
}
