package androidx.media3.session;

import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibraryService$MediaLibrarySession$Callback$$ExternalSyntheticLambda0 implements AsyncFunction {
    public final /* synthetic */ MediaSession.ControllerInfo f$0;
    public final /* synthetic */ MediaLibraryService.MediaLibrarySession f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ MediaLibraryService.LibraryParams f$3;

    public /* synthetic */ MediaLibraryService$MediaLibrarySession$Callback$$ExternalSyntheticLambda0(MediaSession.ControllerInfo controllerInfo, MediaLibraryService.MediaLibrarySession mediaLibrarySession, String str, MediaLibraryService.LibraryParams libraryParams) {
        this.f$0 = controllerInfo;
        this.f$1 = mediaLibrarySession;
        this.f$2 = str;
        this.f$3 = libraryParams;
    }

    public final ListenableFuture apply(Object obj) {
        return MediaLibraryService.MediaLibrarySession.Callback.lambda$onSubscribe$0(this.f$0, this.f$1, this.f$2, this.f$3, (LibraryResult) obj);
    }
}
