package androidx.media3.transformer;

import android.media.MediaCodecInfo;
import androidx.media3.transformer.DefaultEncoderFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultEncoderFactory$$ExternalSyntheticLambda0 implements DefaultEncoderFactory.EncoderFallbackCost {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DefaultEncoderFactory$$ExternalSyntheticLambda0(String str, int i, int i2) {
        this.f$0 = str;
        this.f$1 = i;
        this.f$2 = i2;
    }

    public final int getParameterSupportGap(MediaCodecInfo mediaCodecInfo) {
        return DefaultEncoderFactory.lambda$filterEncodersByResolution$0(this.f$0, this.f$1, this.f$2, mediaCodecInfo);
    }
}
