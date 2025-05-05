package androidx.media3.session;

import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda52 implements AsyncFunction {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ MediaSessionStub.MediaItemPlayerTask f$2;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda52(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, MediaSessionStub.MediaItemPlayerTask mediaItemPlayerTask) {
        this.f$0 = mediaSessionImpl;
        this.f$1 = controllerInfo;
        this.f$2 = mediaItemPlayerTask;
    }

    public final ListenableFuture apply(Object obj) {
        return Util.postOrRunWithCompletion(this.f$0.getApplicationHandler(), this.f$0.callWithControllerForCurrentRequestSet(this.f$1, new MediaSessionStub$$ExternalSyntheticLambda73(this.f$0, this.f$2, this.f$1, (List) obj)), new SessionResult(0));
    }
}
