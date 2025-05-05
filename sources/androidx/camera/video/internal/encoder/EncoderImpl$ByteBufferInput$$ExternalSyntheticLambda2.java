package androidx.camera.video.internal.encoder;

import androidx.camera.core.impl.Observable;
import androidx.camera.video.internal.BufferProvider;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ Observable.Observer f$0;
    public final /* synthetic */ BufferProvider.State f$1;

    public /* synthetic */ EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda2(Observable.Observer observer, BufferProvider.State state) {
        this.f$0 = observer;
        this.f$1 = state;
    }

    public final void run() {
        this.f$0.onNewData(this.f$1);
    }
}
