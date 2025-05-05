package androidx.media3.session;

import androidx.media3.session.MediaSession;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibrarySessionImpl$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ MediaLibrarySessionImpl f$0;
    public final /* synthetic */ ListenableFuture f$1;
    public final /* synthetic */ MediaSession.ControllerInfo f$2;

    public /* synthetic */ MediaLibrarySessionImpl$$ExternalSyntheticLambda3(MediaLibrarySessionImpl mediaLibrarySessionImpl, ListenableFuture listenableFuture, MediaSession.ControllerInfo controllerInfo) {
        this.f$0 = mediaLibrarySessionImpl;
        this.f$1 = listenableFuture;
        this.f$2 = controllerInfo;
    }

    public final void run() {
        this.f$0.m1039lambda$onSearchOnHandler$5$androidxmedia3sessionMediaLibrarySessionImpl(this.f$1, this.f$2);
    }
}
