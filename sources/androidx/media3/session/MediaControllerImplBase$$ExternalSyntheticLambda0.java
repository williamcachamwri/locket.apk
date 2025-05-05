package androidx.media3.session;

import androidx.media3.common.Rating;
import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda0 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Rating f$2;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda0(MediaControllerImplBase mediaControllerImplBase, String str, Rating rating) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = str;
        this.f$2 = rating;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m994lambda$setRating$19$androidxmedia3sessionMediaControllerImplBase(this.f$1, this.f$2, iMediaSession, i);
    }
}
