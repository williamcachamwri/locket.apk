package androidx.camera.video.internal.encoder;

import androidx.camera.video.internal.encoder.EncoderImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ EncoderImpl.MediaCodecCallback f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda8(EncoderImpl.MediaCodecCallback mediaCodecCallback, int i) {
        this.f$0 = mediaCodecCallback;
        this.f$1 = i;
    }

    public final void run() {
        this.f$0.m302lambda$onInputBufferAvailable$0$androidxcameravideointernalencoderEncoderImpl$MediaCodecCallback(this.f$1);
    }
}
