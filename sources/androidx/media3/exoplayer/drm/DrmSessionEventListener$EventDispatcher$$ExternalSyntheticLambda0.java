package androidx.media3.exoplayer.drm;

import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DrmSessionEventListener.EventDispatcher f$0;
    public final /* synthetic */ DrmSessionEventListener f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda0(DrmSessionEventListener.EventDispatcher eventDispatcher, DrmSessionEventListener drmSessionEventListener, int i) {
        this.f$0 = eventDispatcher;
        this.f$1 = drmSessionEventListener;
        this.f$2 = i;
    }

    public final void run() {
        this.f$0.m509lambda$drmSessionAcquired$0$androidxmedia3exoplayerdrmDrmSessionEventListener$EventDispatcher(this.f$1, this.f$2);
    }
}
