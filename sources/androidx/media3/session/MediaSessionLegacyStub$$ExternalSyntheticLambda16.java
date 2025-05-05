package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$$ExternalSyntheticLambda16 implements MediaSessionLegacyStub.SessionTask {
    public final /* synthetic */ MediaSessionLegacyStub f$0;
    public final /* synthetic */ SessionCommand f$1;
    public final /* synthetic */ Bundle f$2;

    public /* synthetic */ MediaSessionLegacyStub$$ExternalSyntheticLambda16(MediaSessionLegacyStub mediaSessionLegacyStub, SessionCommand sessionCommand, Bundle bundle) {
        this.f$0 = mediaSessionLegacyStub;
        this.f$1 = sessionCommand;
        this.f$2 = bundle;
    }

    public final void run(MediaSession.ControllerInfo controllerInfo) {
        this.f$0.m1073lambda$onCustomAction$1$androidxmedia3sessionMediaSessionLegacyStub(this.f$1, this.f$2, controllerInfo);
    }
}
