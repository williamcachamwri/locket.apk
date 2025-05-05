package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerImplLegacy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplLegacy$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ MediaControllerImplLegacy f$0;
    public final /* synthetic */ MediaControllerImplLegacy.ControllerInfo f$1;

    public /* synthetic */ MediaControllerImplLegacy$$ExternalSyntheticLambda4(MediaControllerImplLegacy mediaControllerImplLegacy, MediaControllerImplLegacy.ControllerInfo controllerInfo) {
        this.f$0 = mediaControllerImplLegacy;
        this.f$1 = controllerInfo;
    }

    public final void accept(Object obj) {
        this.f$0.m1016lambda$updateControllerInfo$23$androidxmedia3sessionMediaControllerImplLegacy(this.f$1, (MediaController.Listener) obj);
    }
}
