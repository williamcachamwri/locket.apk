package androidx.media3.extractor;

import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;

public final class IndexSeekMap implements SeekMap {
    private long durationUs;
    private final LongArray positions;
    private final LongArray timesUs;

    public IndexSeekMap(long[] jArr, long[] jArr2, long j) {
        Assertions.checkArgument(jArr.length == jArr2.length);
        int length = jArr2.length;
        if (length <= 0 || jArr2[0] <= 0) {
            this.positions = new LongArray(length);
            this.timesUs = new LongArray(length);
        } else {
            int i = length + 1;
            LongArray longArray = new LongArray(i);
            this.positions = longArray;
            LongArray longArray2 = new LongArray(i);
            this.timesUs = longArray2;
            longArray.add(0);
            longArray2.add(0);
        }
        this.positions.addAll(jArr);
        this.timesUs.addAll(jArr2);
        this.durationUs = j;
    }

    public boolean isSeekable() {
        return this.timesUs.size() > 0;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j) {
        if (this.timesUs.size() == 0) {
            return new SeekMap.SeekPoints(SeekPoint.START);
        }
        int binarySearchFloor = Util.binarySearchFloor(this.timesUs, j, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs.get(binarySearchFloor), this.positions.get(binarySearchFloor));
        if (seekPoint.timeUs == j || binarySearchFloor == this.timesUs.size() - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i = binarySearchFloor + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.timesUs.get(i), this.positions.get(i)));
    }

    public void addSeekPoint(long j, long j2) {
        if (this.timesUs.size() == 0 && j > 0) {
            this.positions.add(0);
            this.timesUs.add(0);
        }
        this.positions.add(j2);
        this.timesUs.add(j);
    }

    public long getTimeUs(long j) {
        if (this.timesUs.size() == 0) {
            return C.TIME_UNSET;
        }
        return this.timesUs.get(Util.binarySearchFloor(this.positions, j, true, true));
    }

    public boolean isTimeUsInIndex(long j, long j2) {
        if (this.timesUs.size() == 0) {
            return false;
        }
        LongArray longArray = this.timesUs;
        if (j - longArray.get(longArray.size() - 1) < j2) {
            return true;
        }
        return false;
    }

    public void setDurationUs(long j) {
        this.durationUs = j;
    }
}
