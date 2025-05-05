package androidx.media3.session;

import androidx.media3.common.AudioAttributes;
import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda7 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ AudioAttributes f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda7(MediaControllerImplBase mediaControllerImplBase, AudioAttributes audioAttributes, boolean z) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = audioAttributes;
        this.f$2 = z;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m974lambda$setAudioAttributes$67$androidxmedia3sessionMediaControllerImplBase(this.f$1, this.f$2, iMediaSession, i);
    }
}
