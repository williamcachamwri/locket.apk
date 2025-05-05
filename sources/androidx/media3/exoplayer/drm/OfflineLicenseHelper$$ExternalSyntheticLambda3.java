package androidx.media3.exoplayer.drm;

import com.google.common.util.concurrent.SettableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OfflineLicenseHelper$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ OfflineLicenseHelper f$0;
    public final /* synthetic */ SettableFuture f$1;

    public /* synthetic */ OfflineLicenseHelper$$ExternalSyntheticLambda3(OfflineLicenseHelper offlineLicenseHelper, SettableFuture settableFuture) {
        this.f$0 = offlineLicenseHelper;
        this.f$1 = settableFuture;
    }

    public final void run() {
        this.f$0.m519lambda$releaseManagerOnHandlerThread$4$androidxmedia3exoplayerdrmOfflineLicenseHelper(this.f$1);
    }
}
