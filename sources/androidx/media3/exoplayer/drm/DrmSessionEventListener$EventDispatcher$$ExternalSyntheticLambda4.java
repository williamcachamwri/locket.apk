package androidx.media3.exoplayer.drm;

import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ DrmSessionEventListener.EventDispatcher f$0;
    public final /* synthetic */ DrmSessionEventListener f$1;
    public final /* synthetic */ Exception f$2;

    public /* synthetic */ DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda4(DrmSessionEventListener.EventDispatcher eventDispatcher, DrmSessionEventListener drmSessionEventListener, Exception exc) {
        this.f$0 = eventDispatcher;
        this.f$1 = drmSessionEventListener;
        this.f$2 = exc;
    }

    public final void run() {
        this.f$0.m510lambda$drmSessionManagerError$2$androidxmedia3exoplayerdrmDrmSessionEventListener$EventDispatcher(this.f$1, this.f$2);
    }
}
