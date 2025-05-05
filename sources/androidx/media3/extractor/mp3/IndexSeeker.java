package androidx.media3.extractor.mp3;

import androidx.media3.common.C;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.SeekMap;
import java.math.RoundingMode;

final class IndexSeeker implements Seeker {
    static final long MIN_TIME_BETWEEN_POINTS_US = 100000;
    private final int averageBitrate;
    private final long dataEndPosition;
    private final IndexSeekMap indexSeekMap;

    public IndexSeeker(long j, long j2, long j3) {
        long j4 = j3;
        this.indexSeekMap = new IndexSeekMap(new long[]{j2}, new long[]{0}, j);
        this.dataEndPosition = j4;
        int i = (j > C.TIME_UNSET ? 1 : (j == C.TIME_UNSET ? 0 : -1));
        int i2 = C.RATE_UNSET_INT;
        if (i != 0) {
            long scaleLargeValue = Util.scaleLargeValue(j2 - j4, 8, j, RoundingMode.HALF_UP);
            if (scaleLargeValue > 0 && scaleLargeValue <= 2147483647L) {
                i2 = (int) scaleLargeValue;
            }
            this.averageBitrate = i2;
            return;
        }
        this.averageBitrate = C.RATE_UNSET_INT;
    }

    public long getTimeUs(long j) {
        return this.indexSeekMap.getTimeUs(j);
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    public boolean isSeekable() {
        return this.indexSeekMap.isSeekable();
    }

    public long getDurationUs() {
        return this.indexSeekMap.getDurationUs();
    }

    public SeekMap.SeekPoints getSeekPoints(long j) {
        return this.indexSeekMap.getSeekPoints(j);
    }

    public int getAverageBitrate() {
        return this.averageBitrate;
    }

    public void maybeAddSeekPoint(long j, long j2) {
        if (!isTimeUsInIndex(j)) {
            this.indexSeekMap.addSeekPoint(j, j2);
        }
    }

    public boolean isTimeUsInIndex(long j) {
        return this.indexSeekMap.isTimeUsInIndex(j, 100000);
    }

    /* access modifiers changed from: package-private */
    public void setDurationUs(long j) {
        this.indexSeekMap.setDurationUs(j);
    }
}
