package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaControllerStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda15 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ Bundle f$0;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda15(Bundle bundle) {
        this.f$0 = bundle;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onExtrasChanged(this.f$0);
    }
}
