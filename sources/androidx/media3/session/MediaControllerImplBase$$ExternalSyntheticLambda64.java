package androidx.media3.session;

import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda64 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda64(MediaControllerImplBase mediaControllerImplBase, boolean z, int i) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = z;
        this.f$2 = i;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m977lambda$setDeviceMuted$65$androidxmedia3sessionMediaControllerImplBase(this.f$1, this.f$2, iMediaSession, i);
    }
}
