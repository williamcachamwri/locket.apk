package androidx.media3.exoplayer.drm;

import com.google.common.util.concurrent.SettableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OfflineLicenseHelper$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ OfflineLicenseHelper f$0;
    public final /* synthetic */ DrmSession f$1;
    public final /* synthetic */ SettableFuture f$2;

    public /* synthetic */ OfflineLicenseHelper$$ExternalSyntheticLambda1(OfflineLicenseHelper offlineLicenseHelper, DrmSession drmSession, SettableFuture settableFuture) {
        this.f$0 = offlineLicenseHelper;
        this.f$1 = drmSession;
        this.f$2 = settableFuture;
    }

    public final void run() {
        this.f$0.m516lambda$acquireFirstSessionOnHandlerThread$3$androidxmedia3exoplayerdrmOfflineLicenseHelper(this.f$1, this.f$2);
    }
}
