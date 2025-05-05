package androidx.media3.session;

import androidx.media3.session.MediaSessionLegacyStub;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionLegacyStub.ControllerLegacyCbForBroadcast f$0;
    public final /* synthetic */ AtomicInteger f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ List f$3;

    public /* synthetic */ MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0(MediaSessionLegacyStub.ControllerLegacyCbForBroadcast controllerLegacyCbForBroadcast, AtomicInteger atomicInteger, List list, List list2) {
        this.f$0 = controllerLegacyCbForBroadcast;
        this.f$1 = atomicInteger;
        this.f$2 = list;
        this.f$3 = list2;
    }

    public final void run() {
        this.f$0.m1095lambda$updateQueue$0$androidxmedia3sessionMediaSessionLegacyStub$ControllerLegacyCbForBroadcast(this.f$1, this.f$2, this.f$3);
    }
}
