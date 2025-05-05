package androidx.media3.session;

import androidx.media3.session.MediaNotification;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaNotificationManager$$ExternalSyntheticLambda2 implements MediaNotification.Provider.Callback {
    public final /* synthetic */ MediaNotificationManager f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MediaSession f$2;

    public /* synthetic */ MediaNotificationManager$$ExternalSyntheticLambda2(MediaNotificationManager mediaNotificationManager, int i, MediaSession mediaSession) {
        this.f$0 = mediaNotificationManager;
        this.f$1 = i;
        this.f$2 = mediaSession;
    }

    public final void onNotificationChanged(MediaNotification mediaNotification) {
        this.f$0.m1046lambda$updateNotification$5$androidxmedia3sessionMediaNotificationManager(this.f$1, this.f$2, mediaNotification);
    }
}
