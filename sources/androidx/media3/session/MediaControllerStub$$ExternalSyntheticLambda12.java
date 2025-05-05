package androidx.media3.session;

import androidx.media3.session.MediaControllerStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda12 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ ConnectionState f$0;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda12(ConnectionState connectionState) {
        this.f$0 = connectionState;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onConnected(this.f$0);
    }
}
