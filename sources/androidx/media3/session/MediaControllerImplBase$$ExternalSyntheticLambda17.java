package androidx.media3.session;

import androidx.media3.common.MediaMetadata;
import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda17 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ MediaMetadata f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda17(MediaControllerImplBase mediaControllerImplBase, MediaMetadata mediaMetadata) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = mediaMetadata;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m993lambda$setPlaylistMetadata$28$androidxmedia3sessionMediaControllerImplBase(this.f$1, iMediaSession, i);
    }
}
