package androidx.camera.video.internal.encoder;

import android.media.MediaFormat;
import androidx.camera.video.internal.encoder.EncoderImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ EncoderImpl.MediaCodecCallback f$0;
    public final /* synthetic */ MediaFormat f$1;

    public /* synthetic */ EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda1(EncoderImpl.MediaCodecCallback mediaCodecCallback, MediaFormat mediaFormat) {
        this.f$0 = mediaCodecCallback;
        this.f$1 = mediaFormat;
    }

    public final void run() {
        this.f$0.m304lambda$onOutputFormatChanged$7$androidxcameravideointernalencoderEncoderImpl$MediaCodecCallback(this.f$1);
    }
}
