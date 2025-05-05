package androidx.media3.session;

import androidx.media3.common.util.ConditionVariable;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ MediaLibraryServiceLegacyStub f$0;
    public final /* synthetic */ AtomicReference f$1;
    public final /* synthetic */ MediaSession.ControllerInfo f$2;
    public final /* synthetic */ MediaLibraryService.LibraryParams f$3;
    public final /* synthetic */ ConditionVariable f$4;

    public /* synthetic */ MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda12(MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub, AtomicReference atomicReference, MediaSession.ControllerInfo controllerInfo, MediaLibraryService.LibraryParams libraryParams, ConditionVariable conditionVariable) {
        this.f$0 = mediaLibraryServiceLegacyStub;
        this.f$1 = atomicReference;
        this.f$2 = controllerInfo;
        this.f$3 = libraryParams;
        this.f$4 = conditionVariable;
    }

    public final void run() {
        this.f$0.m1028lambda$onGetRoot$0$androidxmedia3sessionMediaLibraryServiceLegacyStub(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
