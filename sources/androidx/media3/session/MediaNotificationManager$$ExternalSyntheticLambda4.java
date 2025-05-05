package androidx.media3.session;

import android.os.Bundle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaNotificationManager$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ MediaNotificationManager f$0;
    public final /* synthetic */ MediaController f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Bundle f$3;

    public /* synthetic */ MediaNotificationManager$$ExternalSyntheticLambda4(MediaNotificationManager mediaNotificationManager, MediaController mediaController, String str, Bundle bundle) {
        this.f$0 = mediaNotificationManager;
        this.f$1 = mediaController;
        this.f$2 = str;
        this.f$3 = bundle;
    }

    public final void run() {
        this.f$0.m1043lambda$onCustomAction$2$androidxmedia3sessionMediaNotificationManager(this.f$1, this.f$2, this.f$3);
    }
}
