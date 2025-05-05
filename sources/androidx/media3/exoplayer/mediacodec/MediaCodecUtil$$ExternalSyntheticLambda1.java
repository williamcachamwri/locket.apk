package androidx.media3.exoplayer.mediacodec;

import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import java.util.Comparator;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaCodecUtil$$ExternalSyntheticLambda1 implements Comparator {
    public final /* synthetic */ MediaCodecUtil.ScoreProvider f$0;

    public /* synthetic */ MediaCodecUtil$$ExternalSyntheticLambda1(MediaCodecUtil.ScoreProvider scoreProvider) {
        this.f$0 = scoreProvider;
    }

    public final int compare(Object obj, Object obj2) {
        return MediaCodecUtil.lambda$sortByScore$2(this.f$0, obj, obj2);
    }
}
