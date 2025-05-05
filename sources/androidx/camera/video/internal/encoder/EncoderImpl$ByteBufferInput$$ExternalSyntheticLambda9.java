package androidx.camera.video.internal.encoder;

import androidx.camera.core.impl.Observable;
import androidx.camera.video.internal.encoder.EncoderImpl;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ EncoderImpl.ByteBufferInput f$0;
    public final /* synthetic */ Observable.Observer f$1;
    public final /* synthetic */ Executor f$2;

    public /* synthetic */ EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda9(EncoderImpl.ByteBufferInput byteBufferInput, Observable.Observer observer, Executor executor) {
        this.f$0 = byteBufferInput;
        this.f$1 = observer;
        this.f$2 = executor;
    }

    public final void run() {
        this.f$0.m297lambda$addObserver$7$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(this.f$1, this.f$2);
    }
}
