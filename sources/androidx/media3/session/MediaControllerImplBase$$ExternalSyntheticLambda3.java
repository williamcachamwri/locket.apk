package androidx.media3.session;

import android.view.Surface;
import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$$ExternalSyntheticLambda3 implements MediaControllerImplBase.RemoteSessionTask {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ Surface f$1;

    public /* synthetic */ MediaControllerImplBase$$ExternalSyntheticLambda3(MediaControllerImplBase mediaControllerImplBase, Surface surface) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = surface;
    }

    public final void run(IMediaSession iMediaSession, int i) {
        this.f$0.m1000lambda$setVideoSurfaceHolder$71$androidxmedia3sessionMediaControllerImplBase(this.f$1, iMediaSession, i);
    }
}
