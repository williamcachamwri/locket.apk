package androidx.media3.session;

import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda29 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ TrackSelectionParameters f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda29(MediaControllerImplBase mediaControllerImplBase, TrackSelectionParameters trackSelectionParameters) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = trackSelectionParameters;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m998lambda$setTrackSelectionParameters$75$androidxmedia3sessionMediaControllerImplBase(this.f$1, iMediaSession, i);
    }
}
