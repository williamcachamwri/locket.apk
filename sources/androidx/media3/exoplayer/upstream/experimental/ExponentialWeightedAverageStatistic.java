package androidx.media3.exoplayer.upstream.experimental;

public class ExponentialWeightedAverageStatistic implements BandwidthStatistic {
    public static final double DEFAULT_SMOOTHING_FACTOR = 0.9999d;
    private long bitrateEstimate;
    private final double smoothingFactor;

    public ExponentialWeightedAverageStatistic() {
        this(0.9999d);
    }

    public ExponentialWeightedAverageStatistic(double d) {
        this.smoothingFactor = d;
        this.bitrateEstimate = Long.MIN_VALUE;
    }

    public void addSample(long j, long j2) {
        long j3 = (8000000 * j) / j2;
        if (this.bitrateEstimate == Long.MIN_VALUE) {
            this.bitrateEstimate = j3;
            return;
        }
        double pow = Math.pow(this.smoothingFactor, Math.sqrt((double) j));
        this.bitrateEstimate = (long) ((((double) this.bitrateEstimate) * pow) + ((1.0d - pow) * ((double) j3)));
    }

    public long getBandwidthEstimate() {
        return this.bitrateEstimate;
    }

    public void reset() {
        this.bitrateEstimate = Long.MIN_VALUE;
    }
}
