package androidx.media3.session;

import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda48 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ MediaLibraryService.LibraryParams f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda48(String str, MediaLibraryService.LibraryParams libraryParams) {
        this.f$0 = str;
        this.f$1 = libraryParams;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return ((MediaLibrarySessionImpl) mediaSessionImpl).onSearchOnHandler(controllerInfo, this.f$0, this.f$1);
    }
}
