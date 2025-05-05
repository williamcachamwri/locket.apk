package androidx.camera.video.internal.encoder;

import androidx.camera.video.internal.encoder.EncoderImpl;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ EncoderImpl.MediaCodecCallback f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ EncoderCallback f$2;

    public /* synthetic */ EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda2(EncoderImpl.MediaCodecCallback mediaCodecCallback, Executor executor, EncoderCallback encoderCallback) {
        this.f$0 = mediaCodecCallback;
        this.f$1 = executor;
        this.f$2 = encoderCallback;
    }

    public final void run() {
        this.f$0.m305lambda$reachEndData$2$androidxcameravideointernalencoderEncoderImpl$MediaCodecCallback(this.f$1, this.f$2);
    }
}
