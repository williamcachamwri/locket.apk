package androidx.media3.exoplayer.text;

import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.text.CuesWithTiming;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.util.ArrayList;

final class ReplacingCuesResolver implements CuesResolver {
    private final ArrayList<CuesWithTiming> cuesWithTimingList = new ArrayList<>();

    public boolean addCues(CuesWithTiming cuesWithTiming, long j) {
        Assertions.checkArgument(cuesWithTiming.startTimeUs != C.TIME_UNSET);
        boolean z = cuesWithTiming.startTimeUs <= j && (cuesWithTiming.endTimeUs == C.TIME_UNSET || j < cuesWithTiming.endTimeUs);
        for (int size = this.cuesWithTimingList.size() - 1; size >= 0; size--) {
            if (cuesWithTiming.startTimeUs >= this.cuesWithTimingList.get(size).startTimeUs) {
                this.cuesWithTimingList.add(size + 1, cuesWithTiming);
                return z;
            }
            if (this.cuesWithTimingList.get(size).startTimeUs <= j) {
                z = false;
            }
        }
        this.cuesWithTimingList.add(0, cuesWithTiming);
        return z;
    }

    public ImmutableList<Cue> getCuesAtTimeUs(long j) {
        int indexOfCuesStartingAfter = getIndexOfCuesStartingAfter(j);
        if (indexOfCuesStartingAfter == 0) {
            return ImmutableList.of();
        }
        CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(indexOfCuesStartingAfter - 1);
        if (cuesWithTiming.endTimeUs == C.TIME_UNSET || j < cuesWithTiming.endTimeUs) {
            return cuesWithTiming.cues;
        }
        return ImmutableList.of();
    }

    public void discardCuesBeforeTimeUs(long j) {
        int indexOfCuesStartingAfter = getIndexOfCuesStartingAfter(j);
        if (indexOfCuesStartingAfter != 0) {
            CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(indexOfCuesStartingAfter - 1);
            if (cuesWithTiming.endTimeUs == C.TIME_UNSET || cuesWithTiming.endTimeUs >= j) {
                indexOfCuesStartingAfter--;
            }
            this.cuesWithTimingList.subList(0, indexOfCuesStartingAfter).clear();
        }
    }

    public long getPreviousCueChangeTimeUs(long j) {
        if (this.cuesWithTimingList.isEmpty() || j < this.cuesWithTimingList.get(0).startTimeUs) {
            return C.TIME_UNSET;
        }
        for (int i = 1; i < this.cuesWithTimingList.size(); i++) {
            long j2 = this.cuesWithTimingList.get(i).startTimeUs;
            int i2 = (j > j2 ? 1 : (j == j2 ? 0 : -1));
            if (i2 == 0) {
                return j2;
            }
            if (i2 < 0) {
                CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(i - 1);
                if (cuesWithTiming.endTimeUs == C.TIME_UNSET || cuesWithTiming.endTimeUs > j) {
                    return cuesWithTiming.startTimeUs;
                }
                return cuesWithTiming.endTimeUs;
            }
        }
        CuesWithTiming cuesWithTiming2 = (CuesWithTiming) Iterables.getLast(this.cuesWithTimingList);
        if (cuesWithTiming2.endTimeUs == C.TIME_UNSET || j < cuesWithTiming2.endTimeUs) {
            return cuesWithTiming2.startTimeUs;
        }
        return cuesWithTiming2.endTimeUs;
    }

    public long getNextCueChangeTimeUs(long j) {
        if (this.cuesWithTimingList.isEmpty()) {
            return Long.MIN_VALUE;
        }
        if (j < this.cuesWithTimingList.get(0).startTimeUs) {
            return this.cuesWithTimingList.get(0).startTimeUs;
        }
        for (int i = 1; i < this.cuesWithTimingList.size(); i++) {
            CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(i);
            if (j < cuesWithTiming.startTimeUs) {
                CuesWithTiming cuesWithTiming2 = this.cuesWithTimingList.get(i - 1);
                if (cuesWithTiming2.endTimeUs == C.TIME_UNSET || cuesWithTiming2.endTimeUs <= j || cuesWithTiming2.endTimeUs >= cuesWithTiming.startTimeUs) {
                    return cuesWithTiming.startTimeUs;
                }
                return cuesWithTiming2.endTimeUs;
            }
        }
        CuesWithTiming cuesWithTiming3 = (CuesWithTiming) Iterables.getLast(this.cuesWithTimingList);
        if (cuesWithTiming3.endTimeUs == C.TIME_UNSET || j >= cuesWithTiming3.endTimeUs) {
            return Long.MIN_VALUE;
        }
        return cuesWithTiming3.endTimeUs;
    }

    public void clear() {
        this.cuesWithTimingList.clear();
    }

    private int getIndexOfCuesStartingAfter(long j) {
        for (int i = 0; i < this.cuesWithTimingList.size(); i++) {
            if (j < this.cuesWithTimingList.get(i).startTimeUs) {
                return i;
            }
        }
        return this.cuesWithTimingList.size();
    }
}
