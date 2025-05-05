package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerImplLegacy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ MediaControllerImplLegacy.ControllerCompatCallback f$0;
    public final /* synthetic */ Bundle f$1;

    public /* synthetic */ MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda2(MediaControllerImplLegacy.ControllerCompatCallback controllerCompatCallback, Bundle bundle) {
        this.f$0 = controllerCompatCallback;
        this.f$1 = bundle;
    }

    public final void accept(Object obj) {
        this.f$0.m1022lambda$onExtrasChanged$2$androidxmedia3sessionMediaControllerImplLegacy$ControllerCompatCallback(this.f$1, (MediaController.Listener) obj);
    }
}
