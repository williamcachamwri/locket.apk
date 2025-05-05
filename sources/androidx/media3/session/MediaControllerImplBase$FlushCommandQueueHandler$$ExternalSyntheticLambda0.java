package androidx.media3.session;

import android.os.Handler;
import android.os.Message;
import androidx.media3.session.MediaControllerImplBase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplBase$FlushCommandQueueHandler$$ExternalSyntheticLambda0 implements Handler.Callback {
    public final /* synthetic */ MediaControllerImplBase.FlushCommandQueueHandler f$0;

    public /* synthetic */ MediaControllerImplBase$FlushCommandQueueHandler$$ExternalSyntheticLambda0(MediaControllerImplBase.FlushCommandQueueHandler flushCommandQueueHandler) {
        this.f$0 = flushCommandQueueHandler;
    }

    public final boolean handleMessage(Message message) {
        return this.f$0.handleMessage(message);
    }
}
