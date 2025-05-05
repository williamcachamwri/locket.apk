package androidx.media3.session;

import androidx.media3.session.MediaSessionLegacyStub;
import androidx.media3.session.legacy.MediaSessionManager;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ MediaSessionLegacyStub f$0;
    public final /* synthetic */ SessionCommand f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ MediaSessionManager.RemoteUserInfo f$3;
    public final /* synthetic */ MediaSessionLegacyStub.SessionTask f$4;

    public /* synthetic */ MediaSessionLegacyStub$$ExternalSyntheticLambda7(MediaSessionLegacyStub mediaSessionLegacyStub, SessionCommand sessionCommand, int i, MediaSessionManager.RemoteUserInfo remoteUserInfo, MediaSessionLegacyStub.SessionTask sessionTask) {
        this.f$0 = mediaSessionLegacyStub;
        this.f$1 = sessionCommand;
        this.f$2 = i;
        this.f$3 = remoteUserInfo;
        this.f$4 = sessionTask;
    }

    public final void run() {
        this.f$0.m1068lambda$dispatchSessionTaskWithSessionCommandInternal$22$androidxmedia3sessionMediaSessionLegacyStub(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
