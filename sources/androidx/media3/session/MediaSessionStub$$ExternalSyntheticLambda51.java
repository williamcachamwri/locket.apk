package androidx.media3.session;

import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda51 implements MediaSessionStub.SessionTask {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ MediaLibraryService.LibraryParams f$3;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda51(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        this.f$0 = str;
        this.f$1 = i;
        this.f$2 = i2;
        this.f$3 = libraryParams;
    }

    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return ((MediaLibrarySessionImpl) mediaSessionImpl).onGetChildrenOnHandler(controllerInfo, this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
