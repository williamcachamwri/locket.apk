package androidx.media3.session;

import androidx.media3.session.MediaControllerImplBase;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda102 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda102(MediaControllerImplBase mediaControllerImplBase, List list) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = list;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m927lambda$addMediaItems$32$androidxmedia3sessionMediaControllerImplBase(this.f$1, iMediaSession, i);
    }
}
