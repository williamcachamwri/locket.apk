package androidx.media3.session;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplLegacy$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaControllerImplLegacy f$0;
    public final /* synthetic */ AtomicInteger f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ List f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ MediaControllerImplLegacy$$ExternalSyntheticLambda0(MediaControllerImplLegacy mediaControllerImplLegacy, AtomicInteger atomicInteger, List list, List list2, int i) {
        this.f$0 = mediaControllerImplLegacy;
        this.f$1 = atomicInteger;
        this.f$2 = list;
        this.f$3 = list2;
        this.f$4 = i;
    }

    public final void run() {
        this.f$0.m1010lambda$addQueueItems$4$androidxmedia3sessionMediaControllerImplLegacy(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
