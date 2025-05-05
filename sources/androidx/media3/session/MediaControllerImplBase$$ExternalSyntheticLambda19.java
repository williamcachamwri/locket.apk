package androidx.media3.session;

import android.app.PendingIntent;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda19 implements Consumer {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ PendingIntent f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda19(MediaControllerImplBase mediaControllerImplBase, PendingIntent pendingIntent) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = pendingIntent;
    }

    public final void accept(Object obj) {
        this.f$0.m953lambda$onSetSessionActivity$116$androidxmedia3sessionMediaControllerImplBase(this.f$1, (MediaController.Listener) obj);
    }
}
