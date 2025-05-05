package androidx.camera.video.internal.encoder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ EncoderImpl f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Throwable f$3;

    public /* synthetic */ EncoderImpl$$ExternalSyntheticLambda2(EncoderImpl encoderImpl, int i, String str, Throwable th) {
        this.f$0 = encoderImpl;
        this.f$1 = i;
        this.f$2 = str;
        this.f$3 = th;
    }

    public final void run() {
        this.f$0.m282lambda$handleEncodeError$10$androidxcameravideointernalencoderEncoderImpl(this.f$1, this.f$2, this.f$3);
    }
}
