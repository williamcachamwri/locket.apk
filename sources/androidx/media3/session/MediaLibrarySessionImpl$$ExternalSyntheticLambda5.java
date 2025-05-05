package androidx.media3.session;

import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibrarySessionImpl$$ExternalSyntheticLambda5 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MediaLibraryService.LibraryParams f$2;

    public /* synthetic */ MediaLibrarySessionImpl$$ExternalSyntheticLambda5(String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        this.f$0 = str;
        this.f$1 = i;
        this.f$2 = libraryParams;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onSearchResultChanged(i, this.f$0, this.f$1, this.f$2);
    }
}
