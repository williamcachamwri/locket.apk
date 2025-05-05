package androidx.media3.exoplayer.upstream.experimental;

public interface BandwidthStatistic {
    void addSample(long j, long j2);

    long getBandwidthEstimate();

    void reset();
}
