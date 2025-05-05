package androidx.media3.exoplayer.upstream.experimental;

import androidx.media3.exoplayer.upstream.experimental.SlidingWeightedAverageBandwidthStatistic;
import java.util.Deque;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda0 implements SlidingWeightedAverageBandwidthStatistic.SampleEvictionFunction {
    public final /* synthetic */ long f$0;

    public /* synthetic */ SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda0(long j) {
        this.f$0 = j;
    }

    public final boolean shouldEvictSample(Deque deque) {
        return SlidingWeightedAverageBandwidthStatistic.lambda$getMaxCountEvictionFunction$0(this.f$0, deque);
    }
}
