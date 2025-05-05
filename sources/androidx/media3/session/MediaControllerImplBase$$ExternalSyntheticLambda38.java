package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda38 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MediaItem f$2;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda38(MediaControllerImplBase mediaControllerImplBase, int i, MediaItem mediaItem) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = i;
        this.f$2 = mediaItem;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m926lambda$addMediaItem$31$androidxmedia3sessionMediaControllerImplBase(this.f$1, this.f$2, iMediaSession, i);
    }
}
