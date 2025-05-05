package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionLegacyStub.AnonymousClass1 f$0;
    public final /* synthetic */ MediaSession.MediaItemsWithStartPosition f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ MediaSession.ControllerInfo f$3;

    public /* synthetic */ MediaSessionLegacyStub$1$$ExternalSyntheticLambda0(MediaSessionLegacyStub.AnonymousClass1 r1, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition, boolean z, MediaSession.ControllerInfo controllerInfo) {
        this.f$0 = r1;
        this.f$1 = mediaItemsWithStartPosition;
        this.f$2 = z;
        this.f$3 = controllerInfo;
    }

    public final void run() {
        this.f$0.m1093lambda$onSuccess$0$androidxmedia3sessionMediaSessionLegacyStub$1(this.f$1, this.f$2, this.f$3);
    }
}
