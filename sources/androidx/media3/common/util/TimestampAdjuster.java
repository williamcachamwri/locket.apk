package androidx.media3.common.util;

import androidx.media3.common.C;

public final class TimestampAdjuster {
    private static final long MAX_PTS_PLUS_ONE = 8589934592L;
    public static final long MODE_NO_OFFSET = Long.MAX_VALUE;
    public static final long MODE_SHARED = 9223372036854775806L;
    private long firstSampleTimestampUs;
    private long lastUnadjustedTimestampUs;
    private final ThreadLocal<Long> nextSampleTimestampUs = new ThreadLocal<>();
    private long timestampOffsetUs;

    public TimestampAdjuster(long j) {
        reset(j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void sharedInitializeOrWait(boolean r10, long r11, long r13) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            r9 = this;
            monitor-enter(r9)
            long r0 = r9.firstSampleTimestampUs     // Catch:{ all -> 0x0080 }
            r2 = 9223372036854775806(0x7ffffffffffffffe, double:NaN)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0010
            r0 = r1
            goto L_0x0011
        L_0x0010:
            r0 = r2
        L_0x0011:
            androidx.media3.common.util.Assertions.checkState(r0)     // Catch:{ all -> 0x0080 }
            boolean r0 = r9.isInitialized()     // Catch:{ all -> 0x0080 }
            if (r0 == 0) goto L_0x001c
            monitor-exit(r9)
            return
        L_0x001c:
            if (r10 == 0) goto L_0x0028
            java.lang.ThreadLocal<java.lang.Long> r10 = r9.nextSampleTimestampUs     // Catch:{ all -> 0x0080 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x0080 }
            r10.set(r11)     // Catch:{ all -> 0x0080 }
            goto L_0x007e
        L_0x0028:
            r10 = 0
            r5 = r10
            r3 = r13
        L_0x002c:
            boolean r12 = r9.isInitialized()     // Catch:{ all -> 0x0080 }
            if (r12 != 0) goto L_0x007e
            int r12 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            if (r12 != 0) goto L_0x003a
            r9.wait()     // Catch:{ all -> 0x0080 }
            goto L_0x002c
        L_0x003a:
            int r12 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r12 <= 0) goto L_0x0040
            r12 = r1
            goto L_0x0041
        L_0x0040:
            r12 = r2
        L_0x0041:
            androidx.media3.common.util.Assertions.checkState(r12)     // Catch:{ all -> 0x0080 }
            long r7 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0080 }
            r9.wait(r3)     // Catch:{ all -> 0x0080 }
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0080 }
            long r3 = r3 - r7
            long r5 = r5 + r3
            int r12 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r12 < 0) goto L_0x007b
            boolean r12 = r9.isInitialized()     // Catch:{ all -> 0x0080 }
            if (r12 == 0) goto L_0x005c
            goto L_0x007b
        L_0x005c:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0080 }
            r10.<init>()     // Catch:{ all -> 0x0080 }
            java.lang.String r11 = "TimestampAdjuster failed to initialize in "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0080 }
            java.lang.StringBuilder r10 = r10.append(r13)     // Catch:{ all -> 0x0080 }
            java.lang.String r11 = " milliseconds"
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0080 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0080 }
            java.util.concurrent.TimeoutException r11 = new java.util.concurrent.TimeoutException     // Catch:{ all -> 0x0080 }
            r11.<init>(r10)     // Catch:{ all -> 0x0080 }
            throw r11     // Catch:{ all -> 0x0080 }
        L_0x007b:
            long r3 = r13 - r5
            goto L_0x002c
        L_0x007e:
            monitor-exit(r9)
            return
        L_0x0080:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.util.TimestampAdjuster.sharedInitializeOrWait(boolean, long, long):void");
    }

    public synchronized long getFirstSampleTimestampUs() {
        long j;
        j = this.firstSampleTimestampUs;
        if (j == Long.MAX_VALUE || j == MODE_SHARED) {
            j = C.TIME_UNSET;
        }
        return j;
    }

    public synchronized long getLastAdjustedTimestampUs() {
        long j;
        long j2 = this.lastUnadjustedTimestampUs;
        if (j2 != C.TIME_UNSET) {
            j = j2 + this.timestampOffsetUs;
        } else {
            j = getFirstSampleTimestampUs();
        }
        return j;
    }

    public synchronized long getTimestampOffsetUs() {
        return this.timestampOffsetUs;
    }

    public synchronized void reset(long j) {
        this.firstSampleTimestampUs = j;
        this.timestampOffsetUs = j == Long.MAX_VALUE ? 0 : -9223372036854775807L;
        this.lastUnadjustedTimestampUs = C.TIME_UNSET;
    }

    public synchronized long adjustTsTimestamp(long j) {
        if (j == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        long j2 = this.lastUnadjustedTimestampUs;
        if (j2 != C.TIME_UNSET) {
            long usToNonWrappedPts = usToNonWrappedPts(j2);
            long j3 = (4294967296L + usToNonWrappedPts) / MAX_PTS_PLUS_ONE;
            long j4 = ((j3 - 1) * MAX_PTS_PLUS_ONE) + j;
            j += j3 * MAX_PTS_PLUS_ONE;
            if (Math.abs(j4 - usToNonWrappedPts) < Math.abs(j - usToNonWrappedPts)) {
                j = j4;
            }
        }
        return adjustSampleTimestamp(ptsToUs(j));
    }

    public synchronized long adjustTsTimestampGreaterThanPreviousTimestamp(long j) {
        if (j == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        long j2 = this.lastUnadjustedTimestampUs;
        if (j2 != C.TIME_UNSET) {
            long usToNonWrappedPts = usToNonWrappedPts(j2);
            long j3 = usToNonWrappedPts / MAX_PTS_PLUS_ONE;
            long j4 = (j3 * MAX_PTS_PLUS_ONE) + j;
            j += (j3 + 1) * MAX_PTS_PLUS_ONE;
            if (j4 >= usToNonWrappedPts) {
                j = j4;
            }
        }
        return adjustSampleTimestamp(ptsToUs(j));
    }

    public synchronized long adjustSampleTimestamp(long j) {
        if (j == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        if (!isInitialized()) {
            long j2 = this.firstSampleTimestampUs;
            if (j2 == MODE_SHARED) {
                j2 = ((Long) Assertions.checkNotNull(this.nextSampleTimestampUs.get())).longValue();
            }
            this.timestampOffsetUs = j2 - j;
            notifyAll();
        }
        this.lastUnadjustedTimestampUs = j;
        return j + this.timestampOffsetUs;
    }

    public synchronized boolean isInitialized() {
        return this.timestampOffsetUs != C.TIME_UNSET;
    }

    public static long ptsToUs(long j) {
        return Util.scaleLargeTimestamp(j, 1000000, 90000);
    }

    public static long usToWrappedPts(long j) {
        return usToNonWrappedPts(j) % MAX_PTS_PLUS_ONE;
    }

    public static long usToNonWrappedPts(long j) {
        return Util.scaleLargeTimestamp(j, 90000, 1000000);
    }
}
