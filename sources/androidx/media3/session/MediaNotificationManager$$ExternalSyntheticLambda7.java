package androidx.media3.session;

import android.os.Handler;
import androidx.media3.common.util.Util;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaNotificationManager$$ExternalSyntheticLambda7 implements Executor {
    public final /* synthetic */ Handler f$0;

    public /* synthetic */ MediaNotificationManager$$ExternalSyntheticLambda7(Handler handler) {
        this.f$0 = handler;
    }

    public final void execute(Runnable runnable) {
        Util.postOrRun(this.f$0, runnable);
    }
}
