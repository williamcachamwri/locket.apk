package androidx.media3.session;

import androidx.media3.session.MediaControllerImplBase;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda49 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda49(MediaControllerImplBase mediaControllerImplBase, List list, boolean z) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = list;
        this.f$2 = z;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m988lambda$setMediaItems$26$androidxmedia3sessionMediaControllerImplBase(this.f$1, this.f$2, iMediaSession, i);
    }
}
