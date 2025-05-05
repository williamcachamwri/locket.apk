package androidx.media3.exoplayer.upstream.experimental;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.TreeSet;

public class SlidingPercentileBandwidthStatistic implements BandwidthStatistic {
    public static final int DEFAULT_MAX_SAMPLES_COUNT = 10;
    public static final double DEFAULT_PERCENTILE = 0.5d;
    private long bitrateEstimate;
    private final int maxSampleCount;
    private final double percentile;
    private final ArrayDeque<Sample> samples;
    private final TreeSet<Sample> sortedSamples;
    private double weightSum;

    public SlidingPercentileBandwidthStatistic() {
        this(10, 0.5d);
    }

    public SlidingPercentileBandwidthStatistic(int i, double d) {
        Assertions.checkArgument(d >= 0.0d && d <= 1.0d);
        this.maxSampleCount = i;
        this.percentile = d;
        this.samples = new ArrayDeque<>();
        this.sortedSamples = new TreeSet<>();
        this.bitrateEstimate = Long.MIN_VALUE;
    }

    public void addSample(long j, long j2) {
        while (this.samples.size() >= this.maxSampleCount) {
            Sample remove = this.samples.remove();
            this.sortedSamples.remove(remove);
            this.weightSum -= remove.weight;
        }
        double sqrt = Math.sqrt((double) j);
        Sample sample = new Sample((j * 8000000) / j2, sqrt);
        this.samples.add(sample);
        this.sortedSamples.add(sample);
        this.weightSum += sqrt;
        this.bitrateEstimate = calculateBitrateEstimate();
    }

    public long getBandwidthEstimate() {
        return this.bitrateEstimate;
    }

    public void reset() {
        this.samples.clear();
        this.sortedSamples.clear();
        this.weightSum = 0.0d;
        this.bitrateEstimate = Long.MIN_VALUE;
    }

    private long calculateBitrateEstimate() {
        if (this.samples.isEmpty()) {
            return Long.MIN_VALUE;
        }
        double d = this.weightSum * this.percentile;
        Iterator<Sample> it = this.sortedSamples.iterator();
        double d2 = 0.0d;
        long j = 0;
        double d3 = 0.0d;
        while (it.hasNext()) {
            Sample next = it.next();
            double access$000 = d2 + (next.weight / 2.0d);
            if (access$000 < d) {
                j = next.bitrate;
                double d4 = access$000;
                d2 = (next.weight / 2.0d) + access$000;
                d3 = d4;
            } else if (j == 0) {
                return next.bitrate;
            } else {
                return j + ((long) ((((double) (next.bitrate - j)) * (d - d3)) / (access$000 - d3)));
            }
        }
        return j;
    }

    private static class Sample implements Comparable<Sample> {
        /* access modifiers changed from: private */
        public final long bitrate;
        /* access modifiers changed from: private */
        public final double weight;

        public Sample(long j, double d) {
            this.bitrate = j;
            this.weight = d;
        }

        public int compareTo(Sample sample) {
            return Util.compareLong(this.bitrate, sample.bitrate);
        }
    }
}
