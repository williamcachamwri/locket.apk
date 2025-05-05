package androidx.camera.video.internal.encoder;

import android.media.MediaFormat;
import androidx.camera.video.internal.encoder.EncoderImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda6 implements OutputConfig {
    public final /* synthetic */ MediaFormat f$0;

    public /* synthetic */ EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda6(MediaFormat mediaFormat) {
        this.f$0 = mediaFormat;
    }

    public final MediaFormat getMediaFormat() {
        return EncoderImpl.MediaCodecCallback.lambda$onOutputFormatChanged$5(this.f$0);
    }
}
