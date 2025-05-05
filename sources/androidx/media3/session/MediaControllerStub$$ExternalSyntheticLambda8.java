package androidx.media3.session;

import androidx.media3.session.MediaControllerStub;
import com.google.common.collect.ImmutableList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda8 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ ImmutableList f$1;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda8(int i, ImmutableList immutableList) {
        this.f$0 = i;
        this.f$1 = immutableList;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onSetMediaButtonPreferences(this.f$0, this.f$1);
    }
}
