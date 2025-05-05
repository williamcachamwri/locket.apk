package androidx.media3.exoplayer.upstream.experimental;

import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWeightedAverageBandwidthStatistic implements BandwidthStatistic {
    public static final int DEFAULT_MAX_SAMPLES_COUNT = 10;
    private double bitrateWeightProductSum;
    private final Clock clock;
    private final SampleEvictionFunction sampleEvictionFunction;
    private final ArrayDeque<Sample> samples;
    private double weightSum;

    public interface SampleEvictionFunction {
        boolean shouldEvictSample(Deque<Sample> deque);
    }

    public static class Sample {
        public final long bitrate;
        public final long timeAddedMs;
        public final double weight;

        public Sample(long j, double d, long j2) {
            this.bitrate = j;
            this.weight = d;
            this.timeAddedMs = j2;
        }
    }

    public static SampleEvictionFunction getMaxCountEvictionFunction(long j) {
        return new SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda0(j);
    }

    static /* synthetic */ boolean lambda$getMaxCountEvictionFunction$0(long j, Deque deque) {
        return ((long) deque.size()) >= j;
    }

    public static SampleEvictionFunction getAgeBasedEvictionFunction(long j) {
        return getAgeBasedEvictionFunction(j, Clock.DEFAULT);
    }

    static SampleEvictionFunction getAgeBasedEvictionFunction(long j, Clock clock2) {
        return new SlidingWeightedAverageBandwidthStatistic$$ExternalSyntheticLambda1(j, clock2);
    }

    static /* synthetic */ boolean lambda$getAgeBasedEvictionFunction$1(long j, Clock clock2, Deque deque) {
        if (!deque.isEmpty() && ((Sample) Util.castNonNull((Sample) deque.peek())).timeAddedMs + j < clock2.elapsedRealtime()) {
            return true;
        }
        return false;
    }

    public SlidingWeightedAverageBandwidthStatistic() {
        this(getMaxCountEvictionFunction(10));
    }

    public SlidingWeightedAverageBandwidthStatistic(SampleEvictionFunction sampleEvictionFunction2) {
        this(sampleEvictionFunction2, Clock.DEFAULT);
    }

    SlidingWeightedAverageBandwidthStatistic(SampleEvictionFunction sampleEvictionFunction2, Clock clock2) {
        this.samples = new ArrayDeque<>();
        this.sampleEvictionFunction = sampleEvictionFunction2;
        this.clock = clock2;
    }

    public void addSample(long j, long j2) {
        while (this.sampleEvictionFunction.shouldEvictSample(this.samples)) {
            Sample remove = this.samples.remove();
            this.bitrateWeightProductSum -= ((double) remove.bitrate) * remove.weight;
            this.weightSum -= remove.weight;
        }
        Sample sample = new Sample((j * 8000000) / j2, Math.sqrt((double) j), this.clock.elapsedRealtime());
        this.samples.add(sample);
        this.bitrateWeightProductSum += ((double) sample.bitrate) * sample.weight;
        this.weightSum += sample.weight;
    }

    public long getBandwidthEstimate() {
        if (this.samples.isEmpty()) {
            return Long.MIN_VALUE;
        }
        return (long) (this.bitrateWeightProductSum / this.weightSum);
    }

    public void reset() {
        this.samples.clear();
        this.bitrateWeightProductSum = 0.0d;
        this.weightSum = 0.0d;
    }
}
