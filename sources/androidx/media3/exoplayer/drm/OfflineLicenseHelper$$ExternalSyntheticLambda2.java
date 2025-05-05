package androidx.media3.exoplayer.drm;

import com.google.common.util.concurrent.SettableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OfflineLicenseHelper$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ OfflineLicenseHelper f$0;
    public final /* synthetic */ SettableFuture f$1;
    public final /* synthetic */ DrmSession f$2;

    public /* synthetic */ OfflineLicenseHelper$$ExternalSyntheticLambda2(OfflineLicenseHelper offlineLicenseHelper, SettableFuture settableFuture, DrmSession drmSession) {
        this.f$0 = offlineLicenseHelper;
        this.f$1 = settableFuture;
        this.f$2 = drmSession;
    }

    public final void run() {
        this.f$0.m518lambda$getLicenseDurationRemainingSec$0$androidxmedia3exoplayerdrmOfflineLicenseHelper(this.f$1, this.f$2);
    }
}
