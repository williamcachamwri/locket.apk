package androidx.media3.exoplayer.video;

import androidx.media3.common.util.HandlerWrapper;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PlaybackVideoGraphWrapper$$ExternalSyntheticLambda2 implements Executor {
    public final /* synthetic */ HandlerWrapper f$0;

    public /* synthetic */ PlaybackVideoGraphWrapper$$ExternalSyntheticLambda2(HandlerWrapper handlerWrapper) {
        this.f$0 = handlerWrapper;
    }

    public final void execute(Runnable runnable) {
        this.f$0.post(runnable);
    }
}
