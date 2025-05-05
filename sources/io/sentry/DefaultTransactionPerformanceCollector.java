package io.sentry;

import io.sentry.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class DefaultTransactionPerformanceCollector implements TransactionPerformanceCollector {
    private static final long TRANSACTION_COLLECTION_INTERVAL_MILLIS = 100;
    private static final long TRANSACTION_COLLECTION_TIMEOUT_MILLIS = 30000;
    /* access modifiers changed from: private */
    public final List<ICollector> collectors;
    private final AtomicBoolean isStarted = new AtomicBoolean(false);
    private final SentryOptions options;
    /* access modifiers changed from: private */
    public final Map<String, List<PerformanceCollectionData>> performanceDataMap = new ConcurrentHashMap();
    private volatile Timer timer = null;
    private final Object timerLock = new Object();

    public DefaultTransactionPerformanceCollector(SentryOptions sentryOptions) {
        this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "The options object is required.");
        this.collectors = sentryOptions.getCollectors();
    }

    public void start(ITransaction iTransaction) {
        if (this.collectors.isEmpty()) {
            this.options.getLogger().log(SentryLevel.INFO, "No collector found. Performance stats will not be captured during transactions.", new Object[0]);
            return;
        }
        if (!this.performanceDataMap.containsKey(iTransaction.getEventId().toString())) {
            this.performanceDataMap.put(iTransaction.getEventId().toString(), new ArrayList());
            try {
                this.options.getExecutorService().schedule(new DefaultTransactionPerformanceCollector$$ExternalSyntheticLambda0(this, iTransaction), 30000);
            } catch (RejectedExecutionException e) {
                this.options.getLogger().log(SentryLevel.ERROR, "Failed to call the executor. Performance collector will not be automatically finished. Did you call Sentry.close()?", (Throwable) e);
            }
        }
        if (!this.isStarted.getAndSet(true)) {
            synchronized (this.timerLock) {
                if (this.timer == null) {
                    this.timer = new Timer(true);
                }
                this.timer.schedule(new TimerTask() {
                    public void run() {
                        for (ICollector upVar : DefaultTransactionPerformanceCollector.this.collectors) {
                            upVar.setup();
                        }
                    }
                }, 0);
                this.timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        PerformanceCollectionData performanceCollectionData = new PerformanceCollectionData();
                        for (ICollector collect : DefaultTransactionPerformanceCollector.this.collectors) {
                            collect.collect(performanceCollectionData);
                        }
                        for (List add : DefaultTransactionPerformanceCollector.this.performanceDataMap.values()) {
                            add.add(performanceCollectionData);
                        }
                    }
                }, 100, 100);
            }
        }
    }

    /* renamed from: stop */
    public List<PerformanceCollectionData> m2371lambda$start$0$iosentryDefaultTransactionPerformanceCollector(ITransaction iTransaction) {
        List<PerformanceCollectionData> remove = this.performanceDataMap.remove(iTransaction.getEventId().toString());
        this.options.getLogger().log(SentryLevel.DEBUG, "stop collecting performance info for transactions %s (%s)", iTransaction.getName(), iTransaction.getSpanContext().getTraceId().toString());
        if (this.performanceDataMap.isEmpty() && this.isStarted.getAndSet(false)) {
            synchronized (this.timerLock) {
                if (this.timer != null) {
                    this.timer.cancel();
                    this.timer = null;
                }
            }
        }
        return remove;
    }

    public void close() {
        this.performanceDataMap.clear();
        this.options.getLogger().log(SentryLevel.DEBUG, "stop collecting all performance info for transactions", new Object[0]);
        if (this.isStarted.getAndSet(false)) {
            synchronized (this.timerLock) {
                if (this.timer != null) {
                    this.timer.cancel();
                    this.timer = null;
                }
            }
        }
    }
}
