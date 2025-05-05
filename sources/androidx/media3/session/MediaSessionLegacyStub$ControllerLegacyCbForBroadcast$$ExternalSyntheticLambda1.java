package androidx.media3.session;

import android.os.Handler;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda1 implements Executor {
    public final /* synthetic */ Handler f$0;

    public /* synthetic */ MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda1(Handler handler) {
        this.f$0 = handler;
    }

    public final void execute(Runnable runnable) {
        boolean unused = this.f$0.post(runnable);
    }
}
