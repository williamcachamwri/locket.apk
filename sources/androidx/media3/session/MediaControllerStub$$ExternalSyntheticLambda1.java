package androidx.media3.session;

import androidx.media3.session.MediaControllerStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MediaControllerImplBase f$0;
    public final /* synthetic */ MediaControllerStub.ControllerTask f$1;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda1(MediaControllerImplBase mediaControllerImplBase, MediaControllerStub.ControllerTask controllerTask) {
        this.f$0 = mediaControllerImplBase;
        this.f$1 = controllerTask;
    }

    public final void run() {
        MediaControllerStub.lambda$dispatchControllerTaskOnHandler$16(this.f$0, this.f$1);
    }
}
