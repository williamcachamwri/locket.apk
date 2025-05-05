package androidx.camera.video.internal.encoder;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$$ExternalSyntheticLambda14 implements Runnable {
    public final /* synthetic */ EncoderImpl f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;

    public /* synthetic */ EncoderImpl$$ExternalSyntheticLambda14(EncoderImpl encoderImpl, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = encoderImpl;
        this.f$1 = completer;
    }

    public final void run() {
        this.f$0.m281lambda$acquireInputBuffer$14$androidxcameravideointernalencoderEncoderImpl(this.f$1);
    }
}
