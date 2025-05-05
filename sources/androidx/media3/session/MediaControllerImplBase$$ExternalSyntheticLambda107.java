package androidx.media3.session;

import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda107 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ MediaItem f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda107(MediaControllerImplBase mediaControllerImplBase, MediaItem mediaItem) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = mediaItem;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m925lambda$addMediaItem$30$androidxmedia3sessionMediaControllerImplBase(this.f$1, iMediaSession, i);
    }
}
