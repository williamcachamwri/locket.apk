package androidx.camera.video.internal.encoder;

import android.media.MediaCodec;
import androidx.camera.video.internal.encoder.EncoderImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ EncoderImpl.MediaCodecCallback f$0;
    public final /* synthetic */ MediaCodec.BufferInfo f$1;
    public final /* synthetic */ MediaCodec f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda7(EncoderImpl.MediaCodecCallback mediaCodecCallback, MediaCodec.BufferInfo bufferInfo, MediaCodec mediaCodec, int i) {
        this.f$0 = mediaCodecCallback;
        this.f$1 = bufferInfo;
        this.f$2 = mediaCodec;
        this.f$3 = i;
    }

    public final void run() {
        this.f$0.m303lambda$onOutputBufferAvailable$1$androidxcameravideointernalencoderEncoderImpl$MediaCodecCallback(this.f$1, this.f$2, this.f$3);
    }
}
