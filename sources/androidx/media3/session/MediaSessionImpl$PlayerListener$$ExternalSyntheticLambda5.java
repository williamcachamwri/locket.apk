package androidx.media3.session;

import androidx.media3.common.AudioAttributes;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda5 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ AudioAttributes f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda5(AudioAttributes audioAttributes) {
        this.f$0 = audioAttributes;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onAudioAttributesChanged(i, this.f$0);
    }
}
