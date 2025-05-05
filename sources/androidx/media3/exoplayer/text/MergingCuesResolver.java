package androidx.media3.exoplayer.text;

import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.text.CuesWithTiming;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import java.util.ArrayList;
import java.util.List;

final class MergingCuesResolver implements CuesResolver {
    private static final Ordering<CuesWithTiming> CUES_DISPLAY_PRIORITY_COMPARATOR = Ordering.natural().onResultOf(new MergingCuesResolver$$ExternalSyntheticLambda0()).compound(Ordering.natural().reverse().onResultOf(new MergingCuesResolver$$ExternalSyntheticLambda1()));
    private final List<CuesWithTiming> cuesWithTimingList = new ArrayList();

    public boolean addCues(CuesWithTiming cuesWithTiming, long j) {
        Assertions.checkArgument(cuesWithTiming.startTimeUs != C.TIME_UNSET);
        Assertions.checkArgument(cuesWithTiming.durationUs != C.TIME_UNSET);
        boolean z = cuesWithTiming.startTimeUs <= j && j < cuesWithTiming.endTimeUs;
        for (int size = this.cuesWithTimingList.size() - 1; size >= 0; size--) {
            if (cuesWithTiming.startTimeUs >= this.cuesWithTimingList.get(size).startTimeUs) {
                this.cuesWithTimingList.add(size + 1, cuesWithTiming);
                return z;
            }
        }
        this.cuesWithTimingList.add(0, cuesWithTiming);
        return z;
    }

    public ImmutableList<Cue> getCuesAtTimeUs(long j) {
        if (!this.cuesWithTimingList.isEmpty()) {
            if (j >= this.cuesWithTimingList.get(0).startTimeUs) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < this.cuesWithTimingList.size(); i++) {
                    CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(i);
                    if (j >= cuesWithTiming.startTimeUs && j < cuesWithTiming.endTimeUs) {
                        arrayList.add(cuesWithTiming);
                    }
                    if (j < cuesWithTiming.startTimeUs) {
                        break;
                    }
                }
                ImmutableList<E> sortedCopyOf = ImmutableList.sortedCopyOf(CUES_DISPLAY_PRIORITY_COMPARATOR, arrayList);
                ImmutableList.Builder builder = ImmutableList.builder();
                for (int i2 = 0; i2 < sortedCopyOf.size(); i2++) {
                    builder.addAll((Iterable) ((CuesWithTiming) sortedCopyOf.get(i2)).cues);
                }
                return builder.build();
            }
        }
        return ImmutableList.of();
    }

    public void discardCuesBeforeTimeUs(long j) {
        int i = 0;
        while (i < this.cuesWithTimingList.size()) {
            int i2 = (j > this.cuesWithTimingList.get(i).startTimeUs ? 1 : (j == this.cuesWithTimingList.get(i).startTimeUs ? 0 : -1));
            if (i2 > 0 && j > this.cuesWithTimingList.get(i).endTimeUs) {
                this.cuesWithTimingList.remove(i);
                i--;
            } else if (i2 < 0) {
                return;
            }
            i++;
        }
    }

    public long getPreviousCueChangeTimeUs(long j) {
        if (this.cuesWithTimingList.isEmpty()) {
            return C.TIME_UNSET;
        }
        if (j < this.cuesWithTimingList.get(0).startTimeUs) {
            return C.TIME_UNSET;
        }
        long j2 = this.cuesWithTimingList.get(0).startTimeUs;
        for (int i = 0; i < this.cuesWithTimingList.size(); i++) {
            long j3 = this.cuesWithTimingList.get(i).startTimeUs;
            long j4 = this.cuesWithTimingList.get(i).endTimeUs;
            if (j4 > j) {
                if (j3 > j) {
                    break;
                }
                j2 = Math.max(j2, j3);
            } else {
                j2 = Math.max(j2, j4);
            }
        }
        return j2;
    }

    public long getNextCueChangeTimeUs(long j) {
        int i = 0;
        long j2 = -9223372036854775807L;
        while (true) {
            if (i >= this.cuesWithTimingList.size()) {
                break;
            }
            long j3 = this.cuesWithTimingList.get(i).startTimeUs;
            long j4 = this.cuesWithTimingList.get(i).endTimeUs;
            if (j >= j3) {
                if (j < j4) {
                    if (j2 == C.TIME_UNSET) {
                        j2 = j4;
                    } else {
                        j2 = Math.min(j2, j4);
                    }
                }
                i++;
            } else if (j2 == C.TIME_UNSET) {
                j2 = j3;
            } else {
                j2 = Math.min(j2, j3);
            }
        }
        if (j2 != C.TIME_UNSET) {
            return j2;
        }
        return Long.MIN_VALUE;
    }

    public void clear() {
        this.cuesWithTimingList.clear();
    }
}
