package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda109 implements Consumer {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ Bundle f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda109(MediaControllerImplBase mediaControllerImplBase, Bundle bundle) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = bundle;
    }

    public final void accept(Object obj) {
        this.f$0.m950lambda$onExtrasChanged$115$androidxmedia3sessionMediaControllerImplBase(this.f$1, (MediaController.Listener) obj);
    }
}
