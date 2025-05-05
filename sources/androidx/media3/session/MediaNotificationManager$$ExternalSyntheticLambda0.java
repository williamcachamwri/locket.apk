package androidx.media3.session;

import android.os.Bundle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaNotificationManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaNotificationManager f$0;
    public final /* synthetic */ MediaSession f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Bundle f$3;
    public final /* synthetic */ MediaController f$4;

    public /* synthetic */ MediaNotificationManager$$ExternalSyntheticLambda0(MediaNotificationManager mediaNotificationManager, MediaSession mediaSession, String str, Bundle bundle, MediaController mediaController) {
        this.f$0 = mediaNotificationManager;
        this.f$1 = mediaSession;
        this.f$2 = str;
        this.f$3 = bundle;
        this.f$4 = mediaController;
    }

    public final void run() {
        this.f$0.m1044lambda$onCustomAction$3$androidxmedia3sessionMediaNotificationManager(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
