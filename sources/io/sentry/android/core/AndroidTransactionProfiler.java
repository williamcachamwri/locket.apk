package io.sentry.android.core;

import android.app.ActivityManager;
import android.content.Context;
import io.sentry.HubAdapter;
import io.sentry.IHub;
import io.sentry.ITransaction;
import io.sentry.ITransactionProfiler;
import io.sentry.PerformanceCollectionData;
import io.sentry.ProfilingTraceData;
import io.sentry.ProfilingTransactionData;
import io.sentry.SentryLevel;
import io.sentry.android.core.AndroidProfiler;
import io.sentry.android.core.internal.util.SentryFrameMetricsCollector;
import io.sentry.util.Objects;
import java.util.List;
import java.util.concurrent.TimeUnit;

final class AndroidTransactionProfiler implements ITransactionProfiler {
    private final BuildInfoProvider buildInfoProvider;
    private final Context context;
    private ProfilingTransactionData currentProfilingTransactionData;
    private ITransaction currentTransaction;
    private final SentryFrameMetricsCollector frameMetricsCollector;
    private final IHub hub;
    private boolean isInitialized;
    private final SentryAndroidOptions options;
    private long profileStartCpuMillis;
    private AndroidProfiler profiler;
    private long transactionStartNanos;
    private int transactionsCounter;

    public AndroidTransactionProfiler(Context context2, SentryAndroidOptions sentryAndroidOptions, BuildInfoProvider buildInfoProvider2, SentryFrameMetricsCollector sentryFrameMetricsCollector) {
        this(context2, sentryAndroidOptions, buildInfoProvider2, sentryFrameMetricsCollector, HubAdapter.getInstance());
    }

    public AndroidTransactionProfiler(Context context2, SentryAndroidOptions sentryAndroidOptions, BuildInfoProvider buildInfoProvider2, SentryFrameMetricsCollector sentryFrameMetricsCollector, IHub iHub) {
        this.isInitialized = false;
        this.transactionsCounter = 0;
        this.currentTransaction = null;
        this.profiler = null;
        this.context = (Context) Objects.requireNonNull(context2, "The application context is required");
        this.options = (SentryAndroidOptions) Objects.requireNonNull(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.hub = (IHub) Objects.requireNonNull(iHub, "Hub is required");
        this.frameMetricsCollector = (SentryFrameMetricsCollector) Objects.requireNonNull(sentryFrameMetricsCollector, "SentryFrameMetricsCollector is required");
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider2, "The BuildInfoProvider is required.");
    }

    private void init() {
        if (!this.isInitialized) {
            this.isInitialized = true;
            String profilingTracesDirPath = this.options.getProfilingTracesDirPath();
            if (!this.options.isProfilingEnabled()) {
                this.options.getLogger().log(SentryLevel.INFO, "Profiling is disabled in options.", new Object[0]);
            } else if (profilingTracesDirPath == null) {
                this.options.getLogger().log(SentryLevel.WARNING, "Disabling profiling because no profiling traces dir path is defined in options.", new Object[0]);
            } else {
                int profilingTracesHz = this.options.getProfilingTracesHz();
                if (profilingTracesHz <= 0) {
                    this.options.getLogger().log(SentryLevel.WARNING, "Disabling profiling because trace rate is set to %d", Integer.valueOf(profilingTracesHz));
                } else {
                    this.profiler = new AndroidProfiler(profilingTracesDirPath, ((int) TimeUnit.SECONDS.toMicros(1)) / profilingTracesHz, this.frameMetricsCollector, this.options.getExecutorService(), this.options.getLogger(), this.buildInfoProvider);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onTransactionStart(io.sentry.ITransaction r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            io.sentry.android.core.BuildInfoProvider r0 = r7.buildInfoProvider     // Catch:{ all -> 0x006c }
            int r0 = r0.getSdkInfoVersion()     // Catch:{ all -> 0x006c }
            r1 = 21
            if (r0 >= r1) goto L_0x000d
            monitor-exit(r7)
            return
        L_0x000d:
            r7.init()     // Catch:{ all -> 0x006c }
            int r0 = r7.transactionsCounter     // Catch:{ all -> 0x006c }
            r1 = 1
            int r0 = r0 + r1
            r7.transactionsCounter = r0     // Catch:{ all -> 0x006c }
            r2 = 0
            r3 = 2
            if (r0 != r1) goto L_0x0044
            boolean r0 = r7.onFirstTransactionStarted(r8)     // Catch:{ all -> 0x006c }
            if (r0 == 0) goto L_0x006a
            io.sentry.android.core.SentryAndroidOptions r0 = r7.options     // Catch:{ all -> 0x006c }
            io.sentry.ILogger r0 = r0.getLogger()     // Catch:{ all -> 0x006c }
            io.sentry.SentryLevel r4 = io.sentry.SentryLevel.DEBUG     // Catch:{ all -> 0x006c }
            java.lang.String r5 = "Transaction %s (%s) started and being profiled."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x006c }
            java.lang.String r6 = r8.getName()     // Catch:{ all -> 0x006c }
            r3[r2] = r6     // Catch:{ all -> 0x006c }
            io.sentry.SpanContext r8 = r8.getSpanContext()     // Catch:{ all -> 0x006c }
            io.sentry.protocol.SentryId r8 = r8.getTraceId()     // Catch:{ all -> 0x006c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x006c }
            r3[r1] = r8     // Catch:{ all -> 0x006c }
            r0.log((io.sentry.SentryLevel) r4, (java.lang.String) r5, (java.lang.Object[]) r3)     // Catch:{ all -> 0x006c }
            goto L_0x006a
        L_0x0044:
            int r0 = r0 - r1
            r7.transactionsCounter = r0     // Catch:{ all -> 0x006c }
            io.sentry.android.core.SentryAndroidOptions r0 = r7.options     // Catch:{ all -> 0x006c }
            io.sentry.ILogger r0 = r0.getLogger()     // Catch:{ all -> 0x006c }
            io.sentry.SentryLevel r4 = io.sentry.SentryLevel.WARNING     // Catch:{ all -> 0x006c }
            java.lang.String r5 = "A transaction is already being profiled. Transaction %s (%s) will be ignored."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x006c }
            java.lang.String r6 = r8.getName()     // Catch:{ all -> 0x006c }
            r3[r2] = r6     // Catch:{ all -> 0x006c }
            io.sentry.SpanContext r8 = r8.getSpanContext()     // Catch:{ all -> 0x006c }
            io.sentry.protocol.SentryId r8 = r8.getTraceId()     // Catch:{ all -> 0x006c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x006c }
            r3[r1] = r8     // Catch:{ all -> 0x006c }
            r0.log((io.sentry.SentryLevel) r4, (java.lang.String) r5, (java.lang.Object[]) r3)     // Catch:{ all -> 0x006c }
        L_0x006a:
            monitor-exit(r7)
            return
        L_0x006c:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.AndroidTransactionProfiler.onTransactionStart(io.sentry.ITransaction):void");
    }

    private boolean onFirstTransactionStarted(ITransaction iTransaction) {
        AndroidProfiler.ProfileStartData start;
        AndroidProfiler androidProfiler = this.profiler;
        if (androidProfiler == null || (start = androidProfiler.start()) == null) {
            return false;
        }
        this.transactionStartNanos = start.startNanos;
        this.profileStartCpuMillis = start.startCpuMillis;
        this.currentTransaction = iTransaction;
        this.currentProfilingTransactionData = new ProfilingTransactionData(iTransaction, Long.valueOf(this.transactionStartNanos), Long.valueOf(this.profileStartCpuMillis));
        return true;
    }

    public synchronized ProfilingTraceData onTransactionFinish(ITransaction iTransaction, List<PerformanceCollectionData> list) {
        return onTransactionFinish(iTransaction, false, list);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0083, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized io.sentry.ProfilingTraceData onTransactionFinish(io.sentry.ITransaction r27, boolean r28, java.util.List<io.sentry.PerformanceCollectionData> r29) {
        /*
            r26 = this;
            r1 = r26
            monitor-enter(r26)
            io.sentry.android.core.AndroidProfiler r0 = r1.profiler     // Catch:{ all -> 0x016c }
            r2 = 0
            if (r0 != 0) goto L_0x000a
            monitor-exit(r26)
            return r2
        L_0x000a:
            io.sentry.android.core.BuildInfoProvider r0 = r1.buildInfoProvider     // Catch:{ all -> 0x016c }
            int r0 = r0.getSdkInfoVersion()     // Catch:{ all -> 0x016c }
            r3 = 21
            if (r0 >= r3) goto L_0x0016
            monitor-exit(r26)
            return r2
        L_0x0016:
            io.sentry.ProfilingTransactionData r0 = r1.currentProfilingTransactionData     // Catch:{ all -> 0x016c }
            r3 = 2
            r4 = 1
            r5 = 0
            if (r0 == 0) goto L_0x0147
            java.lang.String r0 = r0.getId()     // Catch:{ all -> 0x016c }
            io.sentry.protocol.SentryId r6 = r27.getEventId()     // Catch:{ all -> 0x016c }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x016c }
            boolean r0 = r0.equals(r6)     // Catch:{ all -> 0x016c }
            if (r0 != 0) goto L_0x0031
            goto L_0x0147
        L_0x0031:
            int r0 = r1.transactionsCounter     // Catch:{ all -> 0x016c }
            if (r0 <= 0) goto L_0x0038
            int r0 = r0 - r4
            r1.transactionsCounter = r0     // Catch:{ all -> 0x016c }
        L_0x0038:
            io.sentry.android.core.SentryAndroidOptions r0 = r1.options     // Catch:{ all -> 0x016c }
            io.sentry.ILogger r0 = r0.getLogger()     // Catch:{ all -> 0x016c }
            io.sentry.SentryLevel r6 = io.sentry.SentryLevel.DEBUG     // Catch:{ all -> 0x016c }
            java.lang.String r7 = "Transaction %s (%s) finished."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x016c }
            java.lang.String r8 = r27.getName()     // Catch:{ all -> 0x016c }
            r3[r5] = r8     // Catch:{ all -> 0x016c }
            io.sentry.SpanContext r8 = r27.getSpanContext()     // Catch:{ all -> 0x016c }
            io.sentry.protocol.SentryId r8 = r8.getTraceId()     // Catch:{ all -> 0x016c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x016c }
            r3[r4] = r8     // Catch:{ all -> 0x016c }
            r0.log((io.sentry.SentryLevel) r6, (java.lang.String) r7, (java.lang.Object[]) r3)     // Catch:{ all -> 0x016c }
            int r0 = r1.transactionsCounter     // Catch:{ all -> 0x016c }
            if (r0 == 0) goto L_0x0084
            io.sentry.ProfilingTransactionData r0 = r1.currentProfilingTransactionData     // Catch:{ all -> 0x016c }
            if (r0 == 0) goto L_0x0082
            long r3 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch:{ all -> 0x016c }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x016c }
            long r4 = r1.transactionStartNanos     // Catch:{ all -> 0x016c }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x016c }
            long r5 = android.os.Process.getElapsedCpuTime()     // Catch:{ all -> 0x016c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x016c }
            long r6 = r1.profileStartCpuMillis     // Catch:{ all -> 0x016c }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x016c }
            r0.notifyFinish(r3, r4, r5, r6)     // Catch:{ all -> 0x016c }
        L_0x0082:
            monitor-exit(r26)
            return r2
        L_0x0084:
            io.sentry.android.core.AndroidProfiler r0 = r1.profiler     // Catch:{ all -> 0x016c }
            r3 = r29
            io.sentry.android.core.AndroidProfiler$ProfileEndData r0 = r0.endAndCollect(r5, r3)     // Catch:{ all -> 0x016c }
            if (r0 != 0) goto L_0x0090
            monitor-exit(r26)
            return r2
        L_0x0090:
            long r6 = r0.endNanos     // Catch:{ all -> 0x016c }
            long r8 = r1.transactionStartNanos     // Catch:{ all -> 0x016c }
            long r6 = r6 - r8
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x016c }
            r10.<init>(r4)     // Catch:{ all -> 0x016c }
            io.sentry.ProfilingTransactionData r3 = r1.currentProfilingTransactionData     // Catch:{ all -> 0x016c }
            if (r3 == 0) goto L_0x00a1
            r10.add(r3)     // Catch:{ all -> 0x016c }
        L_0x00a1:
            r1.currentProfilingTransactionData = r2     // Catch:{ all -> 0x016c }
            r1.transactionsCounter = r5     // Catch:{ all -> 0x016c }
            r1.currentTransaction = r2     // Catch:{ all -> 0x016c }
            java.lang.String r2 = "0"
            android.app.ActivityManager$MemoryInfo r3 = r26.getMemInfo()     // Catch:{ all -> 0x016c }
            if (r3 == 0) goto L_0x00b5
            long r2 = r3.totalMem     // Catch:{ all -> 0x016c }
            java.lang.String r2 = java.lang.Long.toString(r2)     // Catch:{ all -> 0x016c }
        L_0x00b5:
            r20 = r2
            java.lang.String[] r2 = android.os.Build.SUPPORTED_ABIS     // Catch:{ all -> 0x016c }
            java.util.Iterator r3 = r10.iterator()     // Catch:{ all -> 0x016c }
        L_0x00bd:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x016c }
            if (r4 == 0) goto L_0x00e5
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x016c }
            io.sentry.ProfilingTransactionData r4 = (io.sentry.ProfilingTransactionData) r4     // Catch:{ all -> 0x016c }
            long r8 = r0.endNanos     // Catch:{ all -> 0x016c }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x016c }
            long r11 = r1.transactionStartNanos     // Catch:{ all -> 0x016c }
            java.lang.Long r9 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x016c }
            long r11 = r0.endCpuMillis     // Catch:{ all -> 0x016c }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x016c }
            long r12 = r1.profileStartCpuMillis     // Catch:{ all -> 0x016c }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x016c }
            r4.notifyFinish(r8, r9, r11, r12)     // Catch:{ all -> 0x016c }
            goto L_0x00bd
        L_0x00e5:
            io.sentry.ProfilingTraceData r3 = new io.sentry.ProfilingTraceData     // Catch:{ all -> 0x016c }
            java.io.File r9 = r0.traceFile     // Catch:{ all -> 0x016c }
            java.lang.String r12 = java.lang.Long.toString(r6)     // Catch:{ all -> 0x016c }
            io.sentry.android.core.BuildInfoProvider r4 = r1.buildInfoProvider     // Catch:{ all -> 0x016c }
            int r13 = r4.getSdkInfoVersion()     // Catch:{ all -> 0x016c }
            if (r2 == 0) goto L_0x00fb
            int r4 = r2.length     // Catch:{ all -> 0x016c }
            if (r4 <= 0) goto L_0x00fb
            r2 = r2[r5]     // Catch:{ all -> 0x016c }
            goto L_0x00fd
        L_0x00fb:
            java.lang.String r2 = ""
        L_0x00fd:
            r14 = r2
            io.sentry.android.core.AndroidTransactionProfiler$$ExternalSyntheticLambda0 r15 = new io.sentry.android.core.AndroidTransactionProfiler$$ExternalSyntheticLambda0     // Catch:{ all -> 0x016c }
            r15.<init>()     // Catch:{ all -> 0x016c }
            io.sentry.android.core.BuildInfoProvider r2 = r1.buildInfoProvider     // Catch:{ all -> 0x016c }
            java.lang.String r16 = r2.getManufacturer()     // Catch:{ all -> 0x016c }
            io.sentry.android.core.BuildInfoProvider r2 = r1.buildInfoProvider     // Catch:{ all -> 0x016c }
            java.lang.String r17 = r2.getModel()     // Catch:{ all -> 0x016c }
            io.sentry.android.core.BuildInfoProvider r2 = r1.buildInfoProvider     // Catch:{ all -> 0x016c }
            java.lang.String r18 = r2.getVersionRelease()     // Catch:{ all -> 0x016c }
            io.sentry.android.core.BuildInfoProvider r2 = r1.buildInfoProvider     // Catch:{ all -> 0x016c }
            java.lang.Boolean r19 = r2.isEmulator()     // Catch:{ all -> 0x016c }
            io.sentry.android.core.SentryAndroidOptions r2 = r1.options     // Catch:{ all -> 0x016c }
            java.lang.String r21 = r2.getProguardUuid()     // Catch:{ all -> 0x016c }
            io.sentry.android.core.SentryAndroidOptions r2 = r1.options     // Catch:{ all -> 0x016c }
            java.lang.String r22 = r2.getRelease()     // Catch:{ all -> 0x016c }
            io.sentry.android.core.SentryAndroidOptions r2 = r1.options     // Catch:{ all -> 0x016c }
            java.lang.String r23 = r2.getEnvironment()     // Catch:{ all -> 0x016c }
            boolean r2 = r0.didTimeout     // Catch:{ all -> 0x016c }
            if (r2 != 0) goto L_0x0137
            if (r28 == 0) goto L_0x0134
            goto L_0x0137
        L_0x0134:
            java.lang.String r2 = "normal"
            goto L_0x0139
        L_0x0137:
            java.lang.String r2 = "timeout"
        L_0x0139:
            r24 = r2
            java.util.Map<java.lang.String, io.sentry.profilemeasurements.ProfileMeasurement> r0 = r0.measurementsMap     // Catch:{ all -> 0x016c }
            r8 = r3
            r11 = r27
            r25 = r0
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)     // Catch:{ all -> 0x016c }
            monitor-exit(r26)
            return r3
        L_0x0147:
            io.sentry.android.core.SentryAndroidOptions r0 = r1.options     // Catch:{ all -> 0x016c }
            io.sentry.ILogger r0 = r0.getLogger()     // Catch:{ all -> 0x016c }
            io.sentry.SentryLevel r6 = io.sentry.SentryLevel.INFO     // Catch:{ all -> 0x016c }
            java.lang.String r7 = "Transaction %s (%s) finished, but was not currently being profiled. Skipping"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x016c }
            java.lang.String r8 = r27.getName()     // Catch:{ all -> 0x016c }
            r3[r5] = r8     // Catch:{ all -> 0x016c }
            io.sentry.SpanContext r5 = r27.getSpanContext()     // Catch:{ all -> 0x016c }
            io.sentry.protocol.SentryId r5 = r5.getTraceId()     // Catch:{ all -> 0x016c }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x016c }
            r3[r4] = r5     // Catch:{ all -> 0x016c }
            r0.log((io.sentry.SentryLevel) r6, (java.lang.String) r7, (java.lang.Object[]) r3)     // Catch:{ all -> 0x016c }
            monitor-exit(r26)
            return r2
        L_0x016c:
            r0 = move-exception
            monitor-exit(r26)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.AndroidTransactionProfiler.onTransactionFinish(io.sentry.ITransaction, boolean, java.util.List):io.sentry.ProfilingTraceData");
    }

    public void close() {
        ITransaction iTransaction = this.currentTransaction;
        if (iTransaction != null) {
            onTransactionFinish(iTransaction, true, (List<PerformanceCollectionData>) null);
        }
        AndroidProfiler androidProfiler = this.profiler;
        if (androidProfiler != null) {
            androidProfiler.close();
        }
    }

    private ActivityManager.MemoryInfo getMemInfo() {
        try {
            ActivityManager activityManager = (ActivityManager) this.context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            if (activityManager != null) {
                activityManager.getMemoryInfo(memoryInfo);
                return memoryInfo;
            }
            this.options.getLogger().log(SentryLevel.INFO, "Error getting MemoryInfo.", new Object[0]);
            return null;
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Error getting MemoryInfo.", th);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public ITransaction getCurrentTransaction() {
        return this.currentTransaction;
    }
}
