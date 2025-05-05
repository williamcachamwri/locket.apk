package androidx.media3.common.util;

import androidx.media3.common.C;

public final class ConstantRateTimestampIterator implements TimestampIterator {
    private final long endPositionUs;
    private final float frameRate;
    private int framesAdded;
    private final double framesDurationUs;
    private final long startPositionUs;
    private final int totalNumberOfFramesToAdd;

    public ConstantRateTimestampIterator(long j, float f) {
        this(0, j, f);
    }

    public ConstantRateTimestampIterator(long j, long j2, float f) {
        boolean z = true;
        Assertions.checkArgument(j2 > 0);
        Assertions.checkArgument(f > 0.0f);
        Assertions.checkArgument((0 > j || j >= j2) ? false : z);
        this.startPositionUs = j;
        this.endPositionUs = j2;
        this.frameRate = f;
        this.totalNumberOfFramesToAdd = Math.round((((float) (j2 - j)) / 1000000.0f) * f);
        this.framesDurationUs = (double) (1000000.0f / f);
    }

    public boolean hasNext() {
        return this.framesAdded < this.totalNumberOfFramesToAdd;
    }

    public long next() {
        Assertions.checkState(hasNext());
        int i = this.framesAdded;
        this.framesAdded = i + 1;
        return getTimestampUsAfter(i);
    }

    public ConstantRateTimestampIterator copyOf() {
        return new ConstantRateTimestampIterator(this.startPositionUs, this.endPositionUs, this.frameRate);
    }

    public long getLastTimestampUs() {
        int i = this.totalNumberOfFramesToAdd;
        if (i == 0) {
            return C.TIME_UNSET;
        }
        return getTimestampUsAfter(i - 1);
    }

    private long getTimestampUsAfter(int i) {
        long round = this.startPositionUs + Math.round(this.framesDurationUs * ((double) i));
        Assertions.checkState(round >= 0);
        return round;
    }
}
