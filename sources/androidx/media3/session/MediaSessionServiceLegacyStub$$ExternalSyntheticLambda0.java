package androidx.media3.session;

import androidx.media3.common.util.ConditionVariable;
import androidx.media3.session.MediaSession;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionServiceLegacyStub$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionServiceLegacyStub f$0;
    public final /* synthetic */ AtomicReference f$1;
    public final /* synthetic */ MediaSession.ControllerInfo f$2;
    public final /* synthetic */ ConditionVariable f$3;

    public /* synthetic */ MediaSessionServiceLegacyStub$$ExternalSyntheticLambda0(MediaSessionServiceLegacyStub mediaSessionServiceLegacyStub, AtomicReference atomicReference, MediaSession.ControllerInfo controllerInfo, ConditionVariable conditionVariable) {
        this.f$0 = mediaSessionServiceLegacyStub;
        this.f$1 = atomicReference;
        this.f$2 = controllerInfo;
        this.f$3 = conditionVariable;
    }

    public final void run() {
        this.f$0.m1099lambda$onGetRoot$0$androidxmedia3sessionMediaSessionServiceLegacyStub(this.f$1, this.f$2, this.f$3);
    }
}
