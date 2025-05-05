package androidx.camera.video.internal.encoder;

import androidx.camera.video.internal.encoder.EncoderImpl;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ EncoderImpl.ByteBufferInput f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;

    public /* synthetic */ EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda1(EncoderImpl.ByteBufferInput byteBufferInput, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = byteBufferInput;
        this.f$1 = completer;
    }

    public final void run() {
        this.f$0.m295lambda$acquireBuffer$4$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(this.f$1);
    }
}
