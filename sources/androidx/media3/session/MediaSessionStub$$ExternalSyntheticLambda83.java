package androidx.media3.session;

import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda83 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ MediaLibraryService.LibraryParams f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda83(MediaLibraryService.LibraryParams libraryParams) {
        this.f$0 = libraryParams;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return ((MediaLibrarySessionImpl) mediaSessionImpl).onGetLibraryRootOnHandler(controllerInfo, this.f$0);
    }
}
