package androidx.camera.video.internal.encoder;

import androidx.camera.core.impl.Observable;
import androidx.camera.video.internal.encoder.EncoderImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ EncoderImpl.ByteBufferInput f$0;
    public final /* synthetic */ Observable.Observer f$1;

    public /* synthetic */ EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda7(EncoderImpl.ByteBufferInput byteBufferInput, Observable.Observer observer) {
        this.f$0 = byteBufferInput;
        this.f$1 = observer;
    }

    public final void run() {
        this.f$0.m300lambda$removeObserver$8$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(this.f$1);
    }
}
