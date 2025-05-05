package androidx.media3.session;

import androidx.media3.session.MediaNotificationManager;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaNotificationManager$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ MediaNotificationManager f$0;
    public final /* synthetic */ ListenableFuture f$1;
    public final /* synthetic */ MediaNotificationManager.MediaControllerListener f$2;
    public final /* synthetic */ MediaSession f$3;

    public /* synthetic */ MediaNotificationManager$$ExternalSyntheticLambda5(MediaNotificationManager mediaNotificationManager, ListenableFuture listenableFuture, MediaNotificationManager.MediaControllerListener mediaControllerListener, MediaSession mediaSession) {
        this.f$0 = mediaNotificationManager;
        this.f$1 = listenableFuture;
        this.f$2 = mediaControllerListener;
        this.f$3 = mediaSession;
    }

    public final void run() {
        this.f$0.m1042lambda$addSession$1$androidxmedia3sessionMediaNotificationManager(this.f$1, this.f$2, this.f$3);
    }
}
