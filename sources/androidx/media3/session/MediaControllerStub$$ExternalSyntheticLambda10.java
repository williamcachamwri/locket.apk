package androidx.media3.session;

import androidx.media3.session.MediaControllerStub;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda10 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda10(int i, List list) {
        this.f$0 = i;
        this.f$1 = list;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onSetCustomLayout(this.f$0, this.f$1);
    }
}
