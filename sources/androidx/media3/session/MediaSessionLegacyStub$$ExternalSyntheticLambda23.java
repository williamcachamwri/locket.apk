package androidx.media3.session;

import androidx.media3.session.MediaSessionLegacyStub;
import androidx.media3.session.legacy.MediaSessionManager;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ MediaSessionLegacyStub f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MediaSessionManager.RemoteUserInfo f$2;
    public final /* synthetic */ MediaSessionLegacyStub.SessionTask f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ MediaSessionLegacyStub$$ExternalSyntheticLambda23(MediaSessionLegacyStub mediaSessionLegacyStub, int i, MediaSessionManager.RemoteUserInfo remoteUserInfo, MediaSessionLegacyStub.SessionTask sessionTask, boolean z) {
        this.f$0 = mediaSessionLegacyStub;
        this.f$1 = i;
        this.f$2 = remoteUserInfo;
        this.f$3 = sessionTask;
        this.f$4 = z;
    }

    public final void run() {
        this.f$0.m1067lambda$dispatchSessionTaskWithPlayerCommand$21$androidxmedia3sessionMediaSessionLegacyStub(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
