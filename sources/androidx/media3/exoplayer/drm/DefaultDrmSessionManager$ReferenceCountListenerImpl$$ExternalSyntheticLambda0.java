package androidx.media3.exoplayer.drm;

import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultDrmSessionManager$ReferenceCountListenerImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DefaultDrmSession f$0;

    public /* synthetic */ DefaultDrmSessionManager$ReferenceCountListenerImpl$$ExternalSyntheticLambda0(DefaultDrmSession defaultDrmSession) {
        this.f$0 = defaultDrmSession;
    }

    public final void run() {
        this.f$0.release((DrmSessionEventListener.EventDispatcher) null);
    }
}
