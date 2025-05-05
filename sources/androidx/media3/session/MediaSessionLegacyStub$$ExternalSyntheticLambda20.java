package androidx.media3.session;

import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$$ExternalSyntheticLambda20 implements MediaSessionLegacyStub.SessionTask {
    public final /* synthetic */ MediaSessionLegacyStub f$0;
    public final /* synthetic */ SessionCommand f$1;
    public final /* synthetic */ Bundle f$2;
    public final /* synthetic */ ResultReceiver f$3;

    public /* synthetic */ MediaSessionLegacyStub$$ExternalSyntheticLambda20(MediaSessionLegacyStub mediaSessionLegacyStub, SessionCommand sessionCommand, Bundle bundle, ResultReceiver resultReceiver) {
        this.f$0 = mediaSessionLegacyStub;
        this.f$1 = sessionCommand;
        this.f$2 = bundle;
        this.f$3 = resultReceiver;
    }

    public final void run(MediaSession.ControllerInfo controllerInfo) {
        this.f$0.m1072lambda$onCommand$0$androidxmedia3sessionMediaSessionLegacyStub(this.f$1, this.f$2, this.f$3, controllerInfo);
    }
}
