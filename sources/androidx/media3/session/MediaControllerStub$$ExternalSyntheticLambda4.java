package androidx.media3.session;

import androidx.media3.session.MediaControllerStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda4 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ SessionError f$1;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda4(int i, SessionError sessionError) {
        this.f$0 = i;
        this.f$1 = sessionError;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onError(this.f$0, this.f$1);
    }
}
