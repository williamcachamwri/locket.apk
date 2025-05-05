package androidx.camera.video.internal.encoder;

import androidx.camera.video.internal.encoder.EncoderImpl;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda3 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ EncoderImpl.ByteBufferInput f$0;

    public /* synthetic */ EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda3(EncoderImpl.ByteBufferInput byteBufferInput) {
        this.f$0 = byteBufferInput;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m296lambda$acquireBuffer$5$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(completer);
    }
}
