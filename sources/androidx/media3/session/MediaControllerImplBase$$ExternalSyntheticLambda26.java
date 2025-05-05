package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda26 implements Consumer {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ SessionError f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda26(MediaControllerImplBase mediaControllerImplBase, SessionError sessionError) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = sessionError;
    }

    public final void accept(Object obj) {
        this.f$0.m949lambda$onError$117$androidxmedia3sessionMediaControllerImplBase(this.f$1, (MediaController.Listener) obj);
    }
}
