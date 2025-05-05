package androidx.media3.session;

import androidx.media3.session.MediaSessionService;
import androidx.media3.session.legacy.MediaSessionManager;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionService$MediaSessionServiceStub$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaSessionService.MediaSessionServiceStub f$0;
    public final /* synthetic */ IMediaController f$1;
    public final /* synthetic */ MediaSessionManager.RemoteUserInfo f$2;
    public final /* synthetic */ ConnectionRequest f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ MediaSessionService$MediaSessionServiceStub$$ExternalSyntheticLambda0(MediaSessionService.MediaSessionServiceStub mediaSessionServiceStub, IMediaController iMediaController, MediaSessionManager.RemoteUserInfo remoteUserInfo, ConnectionRequest connectionRequest, boolean z) {
        this.f$0 = mediaSessionServiceStub;
        this.f$1 = iMediaController;
        this.f$2 = remoteUserInfo;
        this.f$3 = connectionRequest;
        this.f$4 = z;
    }

    public final void run() {
        this.f$0.m1098lambda$connect$0$androidxmedia3sessionMediaSessionService$MediaSessionServiceStub(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
