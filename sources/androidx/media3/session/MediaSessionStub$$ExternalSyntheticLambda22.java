package androidx.media3.session;

import androidx.media3.common.Rating;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda22 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Rating f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda22(String str, Rating rating) {
        this.f$0 = str;
        this.f$1 = rating;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return mediaSessionImpl.onSetRatingOnHandler(controllerInfo, this.f$0, this.f$1);
    }
}
