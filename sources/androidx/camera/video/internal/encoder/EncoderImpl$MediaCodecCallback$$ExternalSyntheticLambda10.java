package androidx.camera.video.internal.encoder;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ EncoderCallback f$0;

    public /* synthetic */ EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda10(EncoderCallback encoderCallback) {
        this.f$0 = encoderCallback;
    }

    public final void run() {
        this.f$0.onEncodeStart();
    }
}
