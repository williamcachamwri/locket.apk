package androidx.camera.video.internal.encoder;

import android.media.MediaCodec;
import androidx.camera.video.internal.encoder.EncoderImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ EncoderImpl.MediaCodecCallback f$0;
    public final /* synthetic */ MediaCodec.CodecException f$1;

    public /* synthetic */ EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda3(EncoderImpl.MediaCodecCallback mediaCodecCallback, MediaCodec.CodecException codecException) {
        this.f$0 = mediaCodecCallback;
        this.f$1 = codecException;
    }

    public final void run() {
        this.f$0.m301lambda$onError$4$androidxcameravideointernalencoderEncoderImpl$MediaCodecCallback(this.f$1);
    }
}
