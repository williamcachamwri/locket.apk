package androidx.media3.session;

import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda12 implements Executor {
    public final /* synthetic */ MediaSessionImpl f$0;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda12(MediaSessionImpl mediaSessionImpl) {
        this.f$0 = mediaSessionImpl;
    }

    public final void execute(Runnable runnable) {
        this.f$0.postOrRunOnApplicationHandler(runnable);
    }
}
