package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$$ExternalSyntheticLambda15 implements MediaSessionLegacyStub.SessionTask {
    public final /* synthetic */ MediaSessionLegacyStub f$0;
    public final /* synthetic */ MediaItem f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ MediaSessionLegacyStub$$ExternalSyntheticLambda15(MediaSessionLegacyStub mediaSessionLegacyStub, MediaItem mediaItem, boolean z) {
        this.f$0 = mediaSessionLegacyStub;
        this.f$1 = mediaItem;
        this.f$2 = z;
    }

    public final void run(MediaSession.ControllerInfo controllerInfo) {
        this.f$0.m1070lambda$handleMediaRequest$25$androidxmedia3sessionMediaSessionLegacyStub(this.f$1, this.f$2, controllerInfo);
    }
}
