package androidx.media3.exoplayer.upstream.experimental;

import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.upstream.experimental.SlidingWeightedAverageBandwidthStatistic;
import java.util.Deque;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda1 implements SlidingWeightedAverageBandwidthStatistic.SampleEvictionFunction {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ Clock f$1;

    public /* synthetic */ SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda1(long j, Clock clock) {
        this.f$0 = j;
        this.f$1 = clock;
    }

    public final boolean shouldEvictSample(Deque deque) {
        return SlidingWeightedAverageBandwidthStatistic.lambda$getAgeBasedEvictionFunction$1(this.f$0, this.f$1, deque);
    }
}
