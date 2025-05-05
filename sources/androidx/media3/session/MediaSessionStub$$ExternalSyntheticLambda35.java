package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaSession;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda35 implements Consumer {
    public final /* synthetic */ MediaSession.ControllerInfo f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda35(MediaSession.ControllerInfo controllerInfo, int i) {
        this.f$0 = controllerInfo;
        this.f$1 = i;
    }

    public final void accept(Object obj) {
        MediaSessionStub.lambda$sendSessionResultWhenReady$2(this.f$0, this.f$1, (ListenableFuture) obj);
    }
}
