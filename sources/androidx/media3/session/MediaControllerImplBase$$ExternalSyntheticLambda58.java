package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda58 implements Consumer {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ SessionCommand f$1;
    public final /* synthetic */ Bundle f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda58(MediaControllerImplBase mediaControllerImplBase, SessionCommand sessionCommand, Bundle bundle, int i) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = sessionCommand;
        this.f$2 = bundle;
        this.f$3 = i;
    }

    public final void accept(Object obj) {
        this.f$0.m948lambda$onCustomCommand$107$androidxmedia3sessionMediaControllerImplBase(this.f$1, this.f$2, this.f$3, (MediaController.Listener) obj);
    }
}
