package androidx.media3.session;

import android.app.PendingIntent;
import androidx.media3.session.MediaControllerStub;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerStub$$ExternalSyntheticLambda16 implements MediaControllerStub.ControllerTask {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ PendingIntent f$1;

    public /* synthetic */ MediaControllerStub$$ExternalSyntheticLambda16(int i, PendingIntent pendingIntent) {
        this.f$0 = i;
        this.f$1 = pendingIntent;
    }

    public final void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onSetSessionActivity(this.f$0, this.f$1);
    }
}
