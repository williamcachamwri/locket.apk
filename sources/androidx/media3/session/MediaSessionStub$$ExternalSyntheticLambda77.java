package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import com.google.common.collect.ImmutableList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda77 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ ImmutableList f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda77(ImmutableList immutableList) {
        this.f$0 = immutableList;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, this.f$0);
    }
}
