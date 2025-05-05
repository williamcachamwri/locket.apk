package androidx.media3.extractor.text;

import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

public final class CuesWithTimingSubtitle implements Subtitle {
    private static final Ordering<CuesWithTiming> CUES_BY_START_TIME_ASCENDING = Ordering.natural().onResultOf(new CuesWithTimingSubtitle$$ExternalSyntheticLambda0());
    private static final String TAG = "CuesWithTimingSubtitle";
    private final ImmutableList<ImmutableList<Cue>> eventCues;
    private final long[] eventTimesUs;

    private static long normalizeUnsetStartTimeToZero(long j) {
        if (j == C.TIME_UNSET) {
            return 0;
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00d0 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CuesWithTimingSubtitle(java.util.List<androidx.media3.extractor.text.CuesWithTiming> r15) {
        /*
            r14 = this;
            r14.<init>()
            int r0 = r15.size()
            r1 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r3 = 2
            r4 = 0
            r5 = 1
            if (r0 != r5) goto L_0x004a
            java.lang.Object r15 = com.google.common.collect.Iterables.getOnlyElement(r15)
            androidx.media3.extractor.text.CuesWithTiming r15 = (androidx.media3.extractor.text.CuesWithTiming) r15
            long r6 = r15.startTimeUs
            long r6 = normalizeUnsetStartTimeToZero(r6)
            long r8 = r15.durationUs
            int r0 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x0032
            com.google.common.collect.ImmutableList<androidx.media3.common.text.Cue> r15 = r15.cues
            com.google.common.collect.ImmutableList r15 = com.google.common.collect.ImmutableList.of(r15)
            r14.eventCues = r15
            long[] r15 = new long[r5]
            r15[r4] = r6
            r14.eventTimesUs = r15
            goto L_0x0049
        L_0x0032:
            com.google.common.collect.ImmutableList<androidx.media3.common.text.Cue> r0 = r15.cues
            com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.of()
            com.google.common.collect.ImmutableList r0 = com.google.common.collect.ImmutableList.of(r0, r1)
            r14.eventCues = r0
            long[] r0 = new long[r3]
            r0[r4] = r6
            long r1 = r15.durationUs
            long r6 = r6 + r1
            r0[r5] = r6
            r14.eventTimesUs = r0
        L_0x0049:
            return
        L_0x004a:
            int r0 = r15.size()
            int r0 = r0 * r3
            long[] r0 = new long[r0]
            r14.eventTimesUs = r0
            r5 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.util.Arrays.fill(r0, r5)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.google.common.collect.Ordering<androidx.media3.extractor.text.CuesWithTiming> r3 = CUES_BY_START_TIME_ASCENDING
            com.google.common.collect.ImmutableList r15 = com.google.common.collect.ImmutableList.sortedCopyOf(r3, r15)
            r3 = r4
        L_0x0067:
            int r5 = r15.size()
            if (r4 >= r5) goto L_0x00d3
            java.lang.Object r5 = r15.get(r4)
            androidx.media3.extractor.text.CuesWithTiming r5 = (androidx.media3.extractor.text.CuesWithTiming) r5
            long r6 = r5.startTimeUs
            long r6 = normalizeUnsetStartTimeToZero(r6)
            long r8 = r5.durationUs
            long r8 = r8 + r6
            if (r3 == 0) goto L_0x00b0
            long[] r10 = r14.eventTimesUs
            int r11 = r3 + -1
            r12 = r10[r11]
            int r10 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r10 >= 0) goto L_0x0089
            goto L_0x00b0
        L_0x0089:
            int r10 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r10 != 0) goto L_0x009f
            java.lang.Object r10 = r0.get(r11)
            com.google.common.collect.ImmutableList r10 = (com.google.common.collect.ImmutableList) r10
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x009f
            com.google.common.collect.ImmutableList<androidx.media3.common.text.Cue> r6 = r5.cues
            r0.set(r11, r6)
            goto L_0x00bc
        L_0x009f:
            java.lang.String r10 = "CuesWithTimingSubtitle"
            java.lang.String r12 = "Truncating unsupported overlapping cues."
            androidx.media3.common.util.Log.w(r10, r12)
            long[] r10 = r14.eventTimesUs
            r10[r11] = r6
            com.google.common.collect.ImmutableList<androidx.media3.common.text.Cue> r6 = r5.cues
            r0.set(r11, r6)
            goto L_0x00bc
        L_0x00b0:
            long[] r10 = r14.eventTimesUs
            int r11 = r3 + 1
            r10[r3] = r6
            com.google.common.collect.ImmutableList<androidx.media3.common.text.Cue> r3 = r5.cues
            r0.add(r3)
            r3 = r11
        L_0x00bc:
            long r5 = r5.durationUs
            int r5 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r5 == 0) goto L_0x00d0
            long[] r5 = r14.eventTimesUs
            int r6 = r3 + 1
            r5[r3] = r8
            com.google.common.collect.ImmutableList r3 = com.google.common.collect.ImmutableList.of()
            r0.add(r3)
            r3 = r6
        L_0x00d0:
            int r4 = r4 + 1
            goto L_0x0067
        L_0x00d3:
            com.google.common.collect.ImmutableList r15 = com.google.common.collect.ImmutableList.copyOf(r0)
            r14.eventCues = r15
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.CuesWithTimingSubtitle.<init>(java.util.List):void");
    }

    public int getNextEventTimeIndex(long j) {
        int binarySearchCeil = Util.binarySearchCeil(this.eventTimesUs, j, false, false);
        if (binarySearchCeil < this.eventCues.size()) {
            return binarySearchCeil;
        }
        return -1;
    }

    public int getEventTimeCount() {
        return this.eventCues.size();
    }

    public long getEventTime(int i) {
        Assertions.checkArgument(i < this.eventCues.size());
        return this.eventTimesUs[i];
    }

    public ImmutableList<Cue> getCues(long j) {
        int binarySearchFloor = Util.binarySearchFloor(this.eventTimesUs, j, true, false);
        return binarySearchFloor == -1 ? ImmutableList.of() : (ImmutableList) this.eventCues.get(binarySearchFloor);
    }
}
