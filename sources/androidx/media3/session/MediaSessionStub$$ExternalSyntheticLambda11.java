package androidx.media3.session;

import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda11 implements AsyncFunction {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ MediaSessionStub.MediaItemsWithStartPositionPlayerTask f$2;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda11(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, MediaSessionStub.MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask) {
        this.f$0 = mediaSessionImpl;
        this.f$1 = controllerInfo;
        this.f$2 = mediaItemsWithStartPositionPlayerTask;
    }

    public final ListenableFuture apply(Object obj) {
        return Util.postOrRunWithCompletion(this.f$0.getApplicationHandler(), this.f$0.callWithControllerForCurrentRequestSet(this.f$1, new MediaSessionStub$$ExternalSyntheticLambda32(this.f$0, this.f$2, (MediaSession.MediaItemsWithStartPosition) obj)), new SessionResult(0));
    }
}
