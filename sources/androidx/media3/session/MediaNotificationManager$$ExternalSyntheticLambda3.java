package androidx.media3.session;

import androidx.media3.session.MediaNotification;
import com.google.common.collect.ImmutableList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaNotificationManager$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ MediaNotificationManager f$0;
    public final /* synthetic */ MediaSession f$1;
    public final /* synthetic */ ImmutableList f$2;
    public final /* synthetic */ MediaNotification.Provider.Callback f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ MediaNotificationManager$$ExternalSyntheticLambda3(MediaNotificationManager mediaNotificationManager, MediaSession mediaSession, ImmutableList immutableList, MediaNotification.Provider.Callback callback, boolean z) {
        this.f$0 = mediaNotificationManager;
        this.f$1 = mediaSession;
        this.f$2 = immutableList;
        this.f$3 = callback;
        this.f$4 = z;
    }

    public final void run() {
        this.f$0.m1048lambda$updateNotification$7$androidxmedia3sessionMediaNotificationManager(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
