package io.sentry.android.core;

import io.sentry.EventProcessor;
import io.sentry.Hint;
import io.sentry.SentryEvent;
import io.sentry.util.Objects;

final class PerformanceAndroidEventProcessor implements EventProcessor {
    private final ActivityFramesTracker activityFramesTracker;
    private final SentryAndroidOptions options;
    private boolean sentStartMeasurement = false;

    public SentryEvent process(SentryEvent sentryEvent, Hint hint) {
        return sentryEvent;
    }

    PerformanceAndroidEventProcessor(SentryAndroidOptions sentryAndroidOptions, ActivityFramesTracker activityFramesTracker2) {
        this.options = (SentryAndroidOptions) Objects.requireNonNull(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.activityFramesTracker = (ActivityFramesTracker) Objects.requireNonNull(activityFramesTracker2, "ActivityFramesTracker is required");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0080, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized io.sentry.protocol.SentryTransaction process(io.sentry.protocol.SentryTransaction r4, io.sentry.Hint r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            io.sentry.android.core.SentryAndroidOptions r5 = r3.options     // Catch:{ all -> 0x0081 }
            boolean r5 = r5.isTracingEnabled()     // Catch:{ all -> 0x0081 }
            if (r5 != 0) goto L_0x000b
            monitor-exit(r3)
            return r4
        L_0x000b:
            boolean r5 = r3.sentStartMeasurement     // Catch:{ all -> 0x0081 }
            if (r5 != 0) goto L_0x0054
            java.util.List r5 = r4.getSpans()     // Catch:{ all -> 0x0081 }
            boolean r5 = r3.hasAppStartSpan(r5)     // Catch:{ all -> 0x0081 }
            if (r5 == 0) goto L_0x0054
            io.sentry.android.core.AppStartState r5 = io.sentry.android.core.AppStartState.getInstance()     // Catch:{ all -> 0x0081 }
            java.lang.Long r5 = r5.getAppStartInterval()     // Catch:{ all -> 0x0081 }
            if (r5 == 0) goto L_0x0054
            io.sentry.protocol.MeasurementValue r0 = new io.sentry.protocol.MeasurementValue     // Catch:{ all -> 0x0081 }
            long r1 = r5.longValue()     // Catch:{ all -> 0x0081 }
            float r5 = (float) r1     // Catch:{ all -> 0x0081 }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ all -> 0x0081 }
            io.sentry.MeasurementUnit$Duration r1 = io.sentry.MeasurementUnit.Duration.MILLISECOND     // Catch:{ all -> 0x0081 }
            java.lang.String r1 = r1.apiName()     // Catch:{ all -> 0x0081 }
            r0.<init>(r5, r1)     // Catch:{ all -> 0x0081 }
            io.sentry.android.core.AppStartState r5 = io.sentry.android.core.AppStartState.getInstance()     // Catch:{ all -> 0x0081 }
            java.lang.Boolean r5 = r5.isColdStart()     // Catch:{ all -> 0x0081 }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x0081 }
            if (r5 == 0) goto L_0x0048
            java.lang.String r5 = "app_start_cold"
            goto L_0x004a
        L_0x0048:
            java.lang.String r5 = "app_start_warm"
        L_0x004a:
            java.util.Map r1 = r4.getMeasurements()     // Catch:{ all -> 0x0081 }
            r1.put(r5, r0)     // Catch:{ all -> 0x0081 }
            r5 = 1
            r3.sentStartMeasurement = r5     // Catch:{ all -> 0x0081 }
        L_0x0054:
            io.sentry.protocol.SentryId r5 = r4.getEventId()     // Catch:{ all -> 0x0081 }
            io.sentry.protocol.Contexts r0 = r4.getContexts()     // Catch:{ all -> 0x0081 }
            io.sentry.SpanContext r0 = r0.getTrace()     // Catch:{ all -> 0x0081 }
            if (r5 == 0) goto L_0x007f
            if (r0 == 0) goto L_0x007f
            java.lang.String r0 = r0.getOperation()     // Catch:{ all -> 0x0081 }
            java.lang.String r1 = "ui.load"
            boolean r0 = r0.contentEquals(r1)     // Catch:{ all -> 0x0081 }
            if (r0 == 0) goto L_0x007f
            io.sentry.android.core.ActivityFramesTracker r0 = r3.activityFramesTracker     // Catch:{ all -> 0x0081 }
            java.util.Map r5 = r0.takeMetrics(r5)     // Catch:{ all -> 0x0081 }
            if (r5 == 0) goto L_0x007f
            java.util.Map r0 = r4.getMeasurements()     // Catch:{ all -> 0x0081 }
            r0.putAll(r5)     // Catch:{ all -> 0x0081 }
        L_0x007f:
            monitor-exit(r3)
            return r4
        L_0x0081:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.PerformanceAndroidEventProcessor.process(io.sentry.protocol.SentryTransaction, io.sentry.Hint):io.sentry.protocol.SentryTransaction");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean hasAppStartSpan(java.util.List<io.sentry.protocol.SentrySpan> r4) {
        /*
            r3 = this;
            java.util.Iterator r4 = r4.iterator()
        L_0x0004:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x002a
            java.lang.Object r0 = r4.next()
            io.sentry.protocol.SentrySpan r0 = (io.sentry.protocol.SentrySpan) r0
            java.lang.String r1 = r0.getOp()
            java.lang.String r2 = "app.start.cold"
            boolean r1 = r1.contentEquals(r2)
            if (r1 != 0) goto L_0x0028
            java.lang.String r0 = r0.getOp()
            java.lang.String r1 = "app.start.warm"
            boolean r0 = r0.contentEquals(r1)
            if (r0 == 0) goto L_0x0004
        L_0x0028:
            r4 = 1
            return r4
        L_0x002a:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.PerformanceAndroidEventProcessor.hasAppStartSpan(java.util.List):boolean");
    }
}
