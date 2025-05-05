package androidx.media3.exoplayer.drm;

import android.media.MediaDrm;
import androidx.media3.exoplayer.drm.ExoMediaDrm;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FrameworkMediaDrm$$ExternalSyntheticLambda0 implements MediaDrm.OnExpirationUpdateListener {
    public final /* synthetic */ FrameworkMediaDrm f$0;
    public final /* synthetic */ ExoMediaDrm.OnExpirationUpdateListener f$1;

    public /* synthetic */ FrameworkMediaDrm$$ExternalSyntheticLambda0(FrameworkMediaDrm frameworkMediaDrm, ExoMediaDrm.OnExpirationUpdateListener onExpirationUpdateListener) {
        this.f$0 = frameworkMediaDrm;
        this.f$1 = onExpirationUpdateListener;
    }

    public final void onExpirationUpdate(MediaDrm mediaDrm, byte[] bArr, long j) {
        this.f$0.m513lambda$setOnExpirationUpdateListener$3$androidxmedia3exoplayerdrmFrameworkMediaDrm(this.f$1, mediaDrm, bArr, j);
    }
}
