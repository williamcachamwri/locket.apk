package androidx.media3.session;

import androidx.media3.common.DeviceInfo;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda16 implements MediaSessionImpl.RemoteControllerTask {
    public final /* synthetic */ DeviceInfo f$0;

    public /* synthetic */ MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda16(DeviceInfo deviceInfo) {
        this.f$0 = deviceInfo;
    }

    public final void run(MediaSession.ControllerCb controllerCb, int i) {
        controllerCb.onDeviceInfoChanged(i, this.f$0);
    }
}
