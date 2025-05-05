package androidx.media3.transformer;

import android.os.HandlerThread;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TransformerInternal$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ HandlerThread f$0;

    public /* synthetic */ TransformerInternal$$ExternalSyntheticLambda1(HandlerThread handlerThread) {
        this.f$0 = handlerThread;
    }

    public final void run() {
        boolean unused = this.f$0.quitSafely();
    }
}
