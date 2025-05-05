package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerImplLegacy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ MediaControllerImplLegacy.ControllerCompatCallback f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Bundle f$2;

    public /* synthetic */ MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda1(MediaControllerImplLegacy.ControllerCompatCallback controllerCompatCallback, String str, Bundle bundle) {
        this.f$0 = controllerCompatCallback;
        this.f$1 = str;
        this.f$2 = bundle;
    }

    public final void accept(Object obj) {
        this.f$0.m1023lambda$onSessionEvent$1$androidxmedia3sessionMediaControllerImplLegacy$ControllerCompatCallback(this.f$1, this.f$2, (MediaController.Listener) obj);
    }
}
