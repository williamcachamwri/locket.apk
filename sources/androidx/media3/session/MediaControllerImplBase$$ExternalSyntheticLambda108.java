package androidx.media3.session;

import androidx.media3.common.Rating;
import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda108 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ Rating f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda108(MediaControllerImplBase mediaControllerImplBase, Rating rating) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = rating;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m995lambda$setRating$20$androidxmedia3sessionMediaControllerImplBase(this.f$1, iMediaSession, i);
    }
}
