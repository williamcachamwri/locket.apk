package androidx.media3.exoplayer.drm;

import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ DrmSessionEventListener.EventDispatcher f$0;
    public final /* synthetic */ DrmSessionEventListener f$1;

    public /* synthetic */ DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda5(DrmSessionEventListener.EventDispatcher eventDispatcher, DrmSessionEventListener drmSessionEventListener) {
        this.f$0 = eventDispatcher;
        this.f$1 = drmSessionEventListener;
    }

    public final void run() {
        this.f$0.m506lambda$drmKeysLoaded$1$androidxmedia3exoplayerdrmDrmSessionEventListener$EventDispatcher(this.f$1);
    }
}
