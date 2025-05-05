package androidx.media3.session;

import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaLibraryService;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda5 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MediaLibraryService.LibraryParams f$2;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda5(String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        this.f$0 = str;
        this.f$1 = i;
        this.f$2 = libraryParams;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        ((MediaBrowserImplBase) mediaControllerImplBase).notifySearchResultChanged(this.f$0, this.f$1, this.f$2);
    }
}
