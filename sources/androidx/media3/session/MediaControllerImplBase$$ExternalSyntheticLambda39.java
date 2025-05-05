package androidx.media3.session;

import androidx.media3.session.MediaControllerImplBase;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda39 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda39(MediaControllerImplBase mediaControllerImplBase, List list, int i, int i2) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = list;
        this.f$2 = i;
        this.f$3 = i2;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m961lambda$replaceMediaItems$40$androidxmedia3sessionMediaControllerImplBase(this.f$1, this.f$2, this.f$3, iMediaSession, i);
    }
}
