package androidx.media3.transformer;

import android.media.MediaCodecInfo;
import com.google.common.base.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EncoderSelector$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ EncoderSelector$$ExternalSyntheticLambda1(String str) {
        this.f$0 = str;
    }

    public final boolean apply(Object obj) {
        return EncoderUtil.isHardwareAccelerated((MediaCodecInfo) obj, this.f$0);
    }
}
