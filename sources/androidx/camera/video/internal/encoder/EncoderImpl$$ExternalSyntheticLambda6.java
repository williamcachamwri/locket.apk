package androidx.camera.video.internal.encoder;

import androidx.camera.video.internal.encoder.EncoderImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ EncoderImpl.MediaCodecCallback f$0;

    public /* synthetic */ EncoderImpl$$ExternalSyntheticLambda6(EncoderImpl.MediaCodecCallback mediaCodecCallback) {
        this.f$0 = mediaCodecCallback;
    }

    public final void run() {
        this.f$0.reachEndData();
    }
}
