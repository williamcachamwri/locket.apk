package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda112 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ MediaItem f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda112(MediaControllerImplBase mediaControllerImplBase, MediaItem mediaItem, boolean z) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = mediaItem;
        this.f$2 = z;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m986lambda$setMediaItem$24$androidxmedia3sessionMediaControllerImplBase(this.f$1, this.f$2, iMediaSession, i);
    }
}
