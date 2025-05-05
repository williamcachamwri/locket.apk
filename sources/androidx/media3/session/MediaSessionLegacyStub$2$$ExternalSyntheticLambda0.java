package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionLegacyStub.AnonymousClass2 f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ MediaSession.ControllerInfo f$3;

    public /* synthetic */ MediaSessionLegacyStub$2$$ExternalSyntheticLambda0(MediaSessionLegacyStub.AnonymousClass2 r1, int i, List list, MediaSession.ControllerInfo controllerInfo) {
        this.f$0 = r1;
        this.f$1 = i;
        this.f$2 = list;
        this.f$3 = controllerInfo;
    }

    public final void run() {
        this.f$0.m1094lambda$onSuccess$0$androidxmedia3sessionMediaSessionLegacyStub$2(this.f$1, this.f$2, this.f$3);
    }
}
