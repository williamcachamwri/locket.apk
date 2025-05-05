package androidx.media3.exoplayer.drm;

import androidx.media3.common.Format;
import com.google.common.util.concurrent.SettableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OfflineLicenseHelper$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ OfflineLicenseHelper f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ byte[] f$2;
    public final /* synthetic */ SettableFuture f$3;
    public final /* synthetic */ Format f$4;

    public /* synthetic */ OfflineLicenseHelper$$ExternalSyntheticLambda0(OfflineLicenseHelper offlineLicenseHelper, int i, byte[] bArr, SettableFuture settableFuture, Format format) {
        this.f$0 = offlineLicenseHelper;
        this.f$1 = i;
        this.f$2 = bArr;
        this.f$3 = settableFuture;
        this.f$4 = format;
    }

    public final void run() {
        this.f$0.m515lambda$acquireFirstSessionOnHandlerThread$2$androidxmedia3exoplayerdrmOfflineLicenseHelper(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
