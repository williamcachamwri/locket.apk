package io.sentry.android.core;

import android.os.SystemClock;
import io.sentry.DateUtils;
import io.sentry.SentryDate;
import io.sentry.SentryLongDate;

public final class AppStartState {
    private static final int MAX_APP_START_MILLIS = 60000;
    private static AppStartState instance = new AppStartState();
    private Long appStartEndMillis;
    private Long appStartMillis;
    private SentryDate appStartTime;
    private Boolean coldStart = null;

    private AppStartState() {
    }

    public static AppStartState getInstance() {
        return instance;
    }

    /* access modifiers changed from: package-private */
    public void resetInstance() {
        instance = new AppStartState();
    }

    /* access modifiers changed from: package-private */
    public synchronized void setAppStartEnd() {
        setAppStartEnd(SystemClock.uptimeMillis());
    }

    /* access modifiers changed from: package-private */
    public void setAppStartEnd(long j) {
        this.appStartEndMillis = Long.valueOf(j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Long getAppStartInterval() {
        /*
            r6 = this;
            monitor-enter(r6)
            java.lang.Long r0 = r6.appStartMillis     // Catch:{ all -> 0x002b }
            r1 = 0
            if (r0 == 0) goto L_0x0029
            java.lang.Long r0 = r6.appStartEndMillis     // Catch:{ all -> 0x002b }
            if (r0 == 0) goto L_0x0029
            java.lang.Boolean r2 = r6.coldStart     // Catch:{ all -> 0x002b }
            if (r2 != 0) goto L_0x000f
            goto L_0x0029
        L_0x000f:
            long r2 = r0.longValue()     // Catch:{ all -> 0x002b }
            java.lang.Long r0 = r6.appStartMillis     // Catch:{ all -> 0x002b }
            long r4 = r0.longValue()     // Catch:{ all -> 0x002b }
            long r2 = r2 - r4
            r4 = 60000(0xea60, double:2.9644E-319)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 < 0) goto L_0x0023
            monitor-exit(r6)
            return r1
        L_0x0023:
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x002b }
            monitor-exit(r6)
            return r0
        L_0x0029:
            monitor-exit(r6)
            return r1
        L_0x002b:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.AppStartState.getAppStartInterval():java.lang.Long");
    }

    public Boolean isColdStart() {
        return this.coldStart;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setColdStart(boolean z) {
        if (this.coldStart == null) {
            this.coldStart = Boolean.valueOf(z);
        }
    }

    public SentryDate getAppStartTime() {
        return this.appStartTime;
    }

    public SentryDate getAppStartEndTime() {
        Long appStartInterval;
        SentryDate appStartTime2 = getAppStartTime();
        if (appStartTime2 == null || (appStartInterval = getAppStartInterval()) == null) {
            return null;
        }
        return new SentryLongDate(appStartTime2.nanoTimestamp() + DateUtils.millisToNanos(appStartInterval.longValue()));
    }

    public Long getAppStartMillis() {
        return this.appStartMillis;
    }

    /* access modifiers changed from: package-private */
    public synchronized void setAppStartTime(long j, SentryDate sentryDate) {
        if (this.appStartTime == null || this.appStartMillis == null) {
            this.appStartTime = sentryDate;
            this.appStartMillis = Long.valueOf(j);
        }
    }

    public synchronized void setAppStartMillis(long j) {
        this.appStartMillis = Long.valueOf(j);
    }

    public synchronized void reset() {
        this.appStartTime = null;
        this.appStartMillis = null;
        this.appStartEndMillis = null;
    }
}
