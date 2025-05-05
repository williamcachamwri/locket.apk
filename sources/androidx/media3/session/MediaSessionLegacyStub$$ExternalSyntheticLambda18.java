package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;
import androidx.media3.session.legacy.MediaDescriptionCompat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$$ExternalSyntheticLambda18 implements MediaSessionLegacyStub.SessionTask {
    public final /* synthetic */ MediaSessionLegacyStub f$0;
    public final /* synthetic */ MediaDescriptionCompat f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MediaSessionLegacyStub$$ExternalSyntheticLambda18(MediaSessionLegacyStub mediaSessionLegacyStub, MediaDescriptionCompat mediaDescriptionCompat, int i) {
        this.f$0 = mediaSessionLegacyStub;
        this.f$1 = mediaDescriptionCompat;
        this.f$2 = i;
    }

    public final void run(MediaSession.ControllerInfo controllerInfo) {
        this.f$0.m1071lambda$handleOnAddQueueItem$26$androidxmedia3sessionMediaSessionLegacyStub(this.f$1, this.f$2, controllerInfo);
    }
}
