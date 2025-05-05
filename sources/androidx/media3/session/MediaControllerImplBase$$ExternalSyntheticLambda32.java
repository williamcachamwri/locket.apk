package androidx.media3.session;

import androidx.media3.session.MediaControllerImplBase;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda32 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda32(MediaControllerImplBase mediaControllerImplBase, List list, int i, long j) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = list;
        this.f$2 = i;
        this.f$3 = j;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m989lambda$setMediaItems$27$androidxmedia3sessionMediaControllerImplBase(this.f$1, this.f$2, this.f$3, iMediaSession, i);
    }
}
