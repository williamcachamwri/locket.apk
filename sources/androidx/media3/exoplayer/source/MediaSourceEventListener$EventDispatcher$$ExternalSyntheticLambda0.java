package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ MediaSourceEventListener f$1;

    public /* synthetic */ MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda0(Consumer consumer, MediaSourceEventListener mediaSourceEventListener) {
        this.f$0 = consumer;
        this.f$1 = mediaSourceEventListener;
    }

    public final void run() {
        this.f$0.accept(this.f$1);
    }
}
