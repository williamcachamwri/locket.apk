package androidx.media3.transformer;

import android.media.MediaCodecInfo;
import androidx.media3.transformer.DefaultEncoderFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultEncoderFactory$$ExternalSyntheticLambda1 implements DefaultEncoderFactory.EncoderFallbackCost {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ DefaultEncoderFactory$$ExternalSyntheticLambda1(String str, int i) {
        this.f$0 = str;
        this.f$1 = i;
    }

    public final int getParameterSupportGap(MediaCodecInfo mediaCodecInfo) {
        return Math.abs(EncoderUtil.getSupportedBitrateRange(mediaCodecInfo, this.f$0).clamp(Integer.valueOf(this.f$1)).intValue() - this.f$1);
    }
}
