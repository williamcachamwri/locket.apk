package androidx.media3.transformer;

import android.media.MediaCodecInfo;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public interface EncoderSelector {
    public static final EncoderSelector DEFAULT = new EncoderSelector$$ExternalSyntheticLambda0();

    ImmutableList<MediaCodecInfo> selectEncoderInfos(String str);

    static /* synthetic */ ImmutableList lambda$static$1(String str) {
        ImmutableList<MediaCodecInfo> supportedEncoders = EncoderUtil.getSupportedEncoders(str);
        ImmutableList<E> copyOf = ImmutableList.copyOf(Iterables.filter(supportedEncoders, new EncoderSelector$$ExternalSyntheticLambda1(str)));
        return copyOf.isEmpty() ? supportedEncoders : copyOf;
    }
}
