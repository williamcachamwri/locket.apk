package androidx.camera.video.internal.encoder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ EncoderImpl f$0;

    public /* synthetic */ EncoderImpl$$ExternalSyntheticLambda11(EncoderImpl encoderImpl) {
        this.f$0 = encoderImpl;
    }

    public final void run() {
        this.f$0.signalEndOfInputStream();
    }
}
