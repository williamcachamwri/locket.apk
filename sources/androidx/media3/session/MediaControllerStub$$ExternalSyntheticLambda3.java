package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaControllerStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda3 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ SessionCommand f$1;
    public final /* synthetic */ Bundle f$2;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda3(int i, SessionCommand sessionCommand, Bundle bundle) {
        this.f$0 = i;
        this.f$1 = sessionCommand;
        this.f$2 = bundle;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onCustomCommand(this.f$0, this.f$1, this.f$2);
    }
}
