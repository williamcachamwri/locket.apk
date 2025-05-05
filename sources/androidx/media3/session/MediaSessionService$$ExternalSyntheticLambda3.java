package androidx.media3.session;

import android.content.Intent;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionService$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ MediaSessionService$$ExternalSyntheticLambda3(MediaSessionImpl mediaSessionImpl, Intent intent) {
        this.f$0 = mediaSessionImpl;
        this.f$1 = intent;
    }

    public final void run() {
        MediaSessionService.lambda$onStartCommand$2(this.f$0, this.f$1);
    }
}
