package androidx.media3.exoplayer.mediacodec;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaCodecUtil$$ExternalSyntheticLambda0 implements MediaCodecUtil.ScoreProvider {
    public final /* synthetic */ Format f$0;

    public /* synthetic */ MediaCodecUtil$$ExternalSyntheticLambda0(Format format) {
        this.f$0 = format;
    }

    public final int getScore(Object obj) {
        return MediaCodecUtil.lambda$getDecoderInfosSortedByFormatSupport$0(this.f$0, (MediaCodecInfo) obj);
    }
}
