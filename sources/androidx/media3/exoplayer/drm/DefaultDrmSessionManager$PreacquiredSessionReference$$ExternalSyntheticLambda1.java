package androidx.media3.exoplayer.drm;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultDrmSessionManager$PreacquiredSessionReference$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DefaultDrmSessionManager.PreacquiredSessionReference f$0;
    public final /* synthetic */ Format f$1;

    public /* synthetic */ DefaultDrmSessionManager$PreacquiredSessionReference$$ExternalSyntheticLambda1(DefaultDrmSessionManager.PreacquiredSessionReference preacquiredSessionReference, Format format) {
        this.f$0 = preacquiredSessionReference;
        this.f$1 = format;
    }

    public final void run() {
        this.f$0.m504lambda$acquire$0$androidxmedia3exoplayerdrmDefaultDrmSessionManager$PreacquiredSessionReference(this.f$1);
    }
}
