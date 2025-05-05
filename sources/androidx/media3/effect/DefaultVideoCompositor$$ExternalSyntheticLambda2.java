package androidx.media3.effect;

import androidx.media3.effect.DefaultVideoCompositor;
import com.google.common.base.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoCompositor$$ExternalSyntheticLambda2 implements Predicate {
    public final /* synthetic */ long f$0;

    public /* synthetic */ DefaultVideoCompositor$$ExternalSyntheticLambda2(long j) {
        this.f$0 = j;
    }

    public final boolean apply(Object obj) {
        return DefaultVideoCompositor.lambda$releaseExcessFramesInSecondaryStream$1(this.f$0, (DefaultVideoCompositor.InputFrameInfo) obj);
    }
}
