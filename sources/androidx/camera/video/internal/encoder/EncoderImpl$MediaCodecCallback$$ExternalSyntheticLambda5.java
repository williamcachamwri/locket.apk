package androidx.camera.video.internal.encoder;

import android.media.MediaFormat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ EncoderCallback f$0;
    public final /* synthetic */ MediaFormat f$1;

    public /* synthetic */ EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda5(EncoderCallback encoderCallback, MediaFormat mediaFormat) {
        this.f$0 = encoderCallback;
        this.f$1 = mediaFormat;
    }

    public final void run() {
        this.f$0.onOutputConfigUpdate(new EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda6(this.f$1));
    }
}
