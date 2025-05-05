package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import com.google.common.collect.ImmutableList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda14 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ ImmutableList f$0;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda14(ImmutableList immutableList) {
        this.f$0 = immutableList;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.setCustomLayout(i, this.f$0);
    }
}
