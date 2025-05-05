package androidx.media3.session;

import androidx.media3.common.PlaybackParameters;
import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda31 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ PlaybackParameters f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda31(MediaControllerImplBase mediaControllerImplBase, PlaybackParameters playbackParameters) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = playbackParameters;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m991lambda$setPlaybackParameters$15$androidxmedia3sessionMediaControllerImplBase(this.f$1, iMediaSession, i);
    }
}
