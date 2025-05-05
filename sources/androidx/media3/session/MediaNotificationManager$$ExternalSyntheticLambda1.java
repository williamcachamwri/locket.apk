package androidx.media3.session;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaNotificationManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MediaNotificationManager f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MediaSession f$2;
    public final /* synthetic */ MediaNotification f$3;

    public /* synthetic */ MediaNotificationManager$$ExternalSyntheticLambda1(MediaNotificationManager mediaNotificationManager, int i, MediaSession mediaSession, MediaNotification mediaNotification) {
        this.f$0 = mediaNotificationManager;
        this.f$1 = i;
        this.f$2 = mediaSession;
        this.f$3 = mediaNotification;
    }

    public final void run() {
        this.f$0.m1045lambda$updateNotification$4$androidxmedia3sessionMediaNotificationManager(this.f$1, this.f$2, this.f$3);
    }
}
