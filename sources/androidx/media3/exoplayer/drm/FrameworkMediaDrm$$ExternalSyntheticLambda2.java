package androidx.media3.exoplayer.drm;

import android.media.MediaDrm;
import androidx.media3.exoplayer.drm.ExoMediaDrm;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FrameworkMediaDrm$$ExternalSyntheticLambda2 implements MediaDrm.OnEventListener {
    public final /* synthetic */ FrameworkMediaDrm f$0;
    public final /* synthetic */ ExoMediaDrm.OnEventListener f$1;

    public /* synthetic */ FrameworkMediaDrm$$ExternalSyntheticLambda2(FrameworkMediaDrm frameworkMediaDrm, ExoMediaDrm.OnEventListener onEventListener) {
        this.f$0 = frameworkMediaDrm;
        this.f$1 = onEventListener;
    }

    public final void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
        this.f$0.m512lambda$setOnEventListener$1$androidxmedia3exoplayerdrmFrameworkMediaDrm(this.f$1, mediaDrm, bArr, i, i2, bArr2);
    }
}
