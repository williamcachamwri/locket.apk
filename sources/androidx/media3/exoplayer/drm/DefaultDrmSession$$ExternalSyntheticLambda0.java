package androidx.media3.exoplayer.drm;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultDrmSession$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ Throwable f$0;

    public /* synthetic */ DefaultDrmSession$$ExternalSyntheticLambda0(Throwable th) {
        this.f$0 = th;
    }

    public final void accept(Object obj) {
        ((DrmSessionEventListener.EventDispatcher) obj).drmSessionManagerError((Exception) this.f$0);
    }
}
