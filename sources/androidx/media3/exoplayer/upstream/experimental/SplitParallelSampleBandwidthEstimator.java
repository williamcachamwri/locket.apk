package androidx.media3.exoplayer.upstream.experimental;

import android.os.Handler;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.upstream.BandwidthMeter;

public class SplitParallelSampleBandwidthEstimator implements BandwidthEstimator {
    private long bandwidthEstimate;
    private final BandwidthStatistic bandwidthStatistic;
    private final Clock clock;
    private final BandwidthMeter.EventListener.EventDispatcher eventDispatcher;
    private long lastReportedBandwidthEstimate;
    private final long minBytesTransferred;
    private final int minSamples;
    private long sampleBytesTransferred;
    private long sampleStartTimeMs;
    private int streamCount;
    private long totalBytesTransferred;
    private int totalSamplesAdded;

    public void onTransferInitializing(DataSource dataSource) {
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public BandwidthStatistic bandwidthStatistic = new SlidingWeightedAverageBandwidthStatistic();
        /* access modifiers changed from: private */
        public Clock clock = Clock.DEFAULT;
        /* access modifiers changed from: private */
        public long minBytesTransferred;
        /* access modifiers changed from: private */
        public int minSamples;

        public Builder setBandwidthStatistic(BandwidthStatistic bandwidthStatistic2) {
            Assertions.checkNotNull(bandwidthStatistic2);
            this.bandwidthStatistic = bandwidthStatistic2;
            return this;
        }

        public Builder setMinSamples(int i) {
            Assertions.checkArgument(i >= 0);
            this.minSamples = i;
            return this;
        }

        public Builder setMinBytesTransferred(long j) {
            Assertions.checkArgument(j >= 0);
            this.minBytesTransferred = j;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setClock(Clock clock2) {
            this.clock = clock2;
            return this;
        }

        public SplitParallelSampleBandwidthEstimator build() {
            return new SplitParallelSampleBandwidthEstimator(this);
        }
    }

    private SplitParallelSampleBandwidthEstimator(Builder builder) {
        this.bandwidthStatistic = builder.bandwidthStatistic;
        this.minSamples = builder.minSamples;
        this.minBytesTransferred = builder.minBytesTransferred;
        this.clock = builder.clock;
        this.eventDispatcher = new BandwidthMeter.EventListener.EventDispatcher();
        this.bandwidthEstimate = Long.MIN_VALUE;
        this.lastReportedBandwidthEstimate = Long.MIN_VALUE;
    }

    public void addEventListener(Handler handler, BandwidthMeter.EventListener eventListener) {
        this.eventDispatcher.addListener(handler, eventListener);
    }

    public void removeEventListener(BandwidthMeter.EventListener eventListener) {
        this.eventDispatcher.removeListener(eventListener);
    }

    public void onTransferStart(DataSource dataSource) {
        if (this.streamCount == 0) {
            this.sampleStartTimeMs = this.clock.elapsedRealtime();
        }
        this.streamCount++;
    }

    public void onBytesTransferred(DataSource dataSource, int i) {
        long j = (long) i;
        this.sampleBytesTransferred += j;
        this.totalBytesTransferred += j;
    }

    public void onTransferEnd(DataSource dataSource) {
        Assertions.checkState(this.streamCount > 0);
        long elapsedRealtime = this.clock.elapsedRealtime();
        long j = (long) ((int) (elapsedRealtime - this.sampleStartTimeMs));
        if (j > 0) {
            this.bandwidthStatistic.addSample(this.sampleBytesTransferred, 1000 * j);
            int i = this.totalSamplesAdded + 1;
            this.totalSamplesAdded = i;
            if (i > this.minSamples && this.totalBytesTransferred > this.minBytesTransferred) {
                this.bandwidthEstimate = this.bandwidthStatistic.getBandwidthEstimate();
            }
            maybeNotifyBandwidthSample((int) j, this.sampleBytesTransferred, this.bandwidthEstimate);
            this.sampleStartTimeMs = elapsedRealtime;
            this.sampleBytesTransferred = 0;
        }
        this.streamCount--;
    }

    public long getBandwidthEstimate() {
        return this.bandwidthEstimate;
    }

    public void onNetworkTypeChange(long j) {
        long elapsedRealtime = this.clock.elapsedRealtime();
        maybeNotifyBandwidthSample(this.streamCount > 0 ? (int) (elapsedRealtime - this.sampleStartTimeMs) : 0, this.sampleBytesTransferred, j);
        this.bandwidthStatistic.reset();
        this.bandwidthEstimate = Long.MIN_VALUE;
        this.sampleStartTimeMs = elapsedRealtime;
        this.sampleBytesTransferred = 0;
        this.totalSamplesAdded = 0;
        this.totalBytesTransferred = 0;
    }

    private void maybeNotifyBandwidthSample(int i, long j, long j2) {
        if (j2 == Long.MIN_VALUE) {
            return;
        }
        if (i != 0 || j != 0 || j2 != this.lastReportedBandwidthEstimate) {
            this.lastReportedBandwidthEstimate = j2;
            this.eventDispatcher.bandwidthSample(i, j, j2);
        }
    }
}
