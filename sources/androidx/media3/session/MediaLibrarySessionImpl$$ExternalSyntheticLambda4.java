package androidx.media3.session;

import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibrarySessionImpl$$ExternalSyntheticLambda4 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ MediaLibrarySessionImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ MediaLibraryService.LibraryParams f$3;

    public /* synthetic */ MediaLibrarySessionImpl$$ExternalSyntheticLambda4(MediaLibrarySessionImpl mediaLibrarySessionImpl, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        this.f$0 = mediaLibrarySessionImpl;
        this.f$1 = str;
        this.f$2 = i;
        this.f$3 = libraryParams;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        this.f$0.m1035lambda$notifyChildrenChanged$4$androidxmedia3sessionMediaLibrarySessionImpl(this.f$1, this.f$2, this.f$3, controllerCb, i);
    }
}
