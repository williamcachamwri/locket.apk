package androidx.media3.session;

import androidx.media3.common.Rating;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda12 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ Rating f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda12(Rating rating) {
        this.f$0 = rating;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return mediaSessionImpl.onSetRatingOnHandler(controllerInfo, this.f$0);
    }
}
