package androidx.media3.session;

import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda5 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda5(MediaControllerImplBase mediaControllerImplBase, int i, int i2) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = i;
        this.f$2 = i2;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m981lambda$setDeviceVolume$53$androidxmedia3sessionMediaControllerImplBase(this.f$1, this.f$2, iMediaSession, i);
    }
}
