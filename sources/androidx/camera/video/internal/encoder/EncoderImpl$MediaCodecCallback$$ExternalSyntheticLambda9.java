package androidx.camera.video.internal.encoder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ EncoderCallback f$0;
    public final /* synthetic */ EncodedDataImpl f$1;

    public /* synthetic */ EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda9(EncoderCallback encoderCallback, EncodedDataImpl encodedDataImpl) {
        this.f$0 = encoderCallback;
        this.f$1 = encodedDataImpl;
    }

    public final void run() {
        this.f$0.onEncodedData(this.f$1);
    }
}
