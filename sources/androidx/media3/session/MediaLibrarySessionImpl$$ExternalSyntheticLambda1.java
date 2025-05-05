package androidx.media3.session;

import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibrarySessionImpl$$ExternalSyntheticLambda1 implements Executor {
    public final /* synthetic */ MediaLibrarySessionImpl f$0;

    public /* synthetic */ MediaLibrarySessionImpl$$ExternalSyntheticLambda1(MediaLibrarySessionImpl mediaLibrarySessionImpl) {
        this.f$0 = mediaLibrarySessionImpl;
    }

    public final void execute(Runnable runnable) {
        this.f$0.postOrRunOnApplicationHandler(runnable);
    }
}
