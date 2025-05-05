package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerImplLegacy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ MediaControllerImplLegacy.ControllerCompatCallback f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ MediaControllerImplLegacy$ControllerCompatCallback$$ExternalSyntheticLambda0(MediaControllerImplLegacy.ControllerCompatCallback controllerCompatCallback, boolean z) {
        this.f$0 = controllerCompatCallback;
        this.f$1 = z;
    }

    public final void accept(Object obj) {
        this.f$0.m1021lambda$onCaptioningEnabledChanged$3$androidxmedia3sessionMediaControllerImplLegacy$ControllerCompatCallback(this.f$1, (MediaController.Listener) obj);
    }
}
