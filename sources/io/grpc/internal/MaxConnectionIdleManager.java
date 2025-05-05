package io.grpc.internal;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;

public final class MaxConnectionIdleManager {
    private static final Ticker systemTicker = new Ticker() {
        public long nanoTime() {
            return System.nanoTime();
        }
    };
    /* access modifiers changed from: private */
    public boolean isActive;
    private final long maxConnectionIdleInNanos;
    /* access modifiers changed from: private */
    public long nextIdleMonitorTime;
    private ScheduledExecutorService scheduler;
    /* access modifiers changed from: private */
    public boolean shutdownDelayed;
    /* access modifiers changed from: private */
    @CheckForNull
    public ScheduledFuture<?> shutdownFuture;
    /* access modifiers changed from: private */
    public Runnable shutdownTask;
    /* access modifiers changed from: private */
    public final Ticker ticker;

    public interface Ticker {
        long nanoTime();
    }

    public MaxConnectionIdleManager(long j) {
        this(j, systemTicker);
    }

    public MaxConnectionIdleManager(long j, Ticker ticker2) {
        this.maxConnectionIdleInNanos = j;
        this.ticker = ticker2;
    }

    public void start(final Runnable runnable, final ScheduledExecutorService scheduledExecutorService) {
        this.scheduler = scheduledExecutorService;
        this.nextIdleMonitorTime = this.ticker.nanoTime() + this.maxConnectionIdleInNanos;
        LogExceptionRunnable logExceptionRunnable = new LogExceptionRunnable(new Runnable() {
            public void run() {
                if (!MaxConnectionIdleManager.this.shutdownDelayed) {
                    runnable.run();
                    ScheduledFuture unused = MaxConnectionIdleManager.this.shutdownFuture = null;
                } else if (!MaxConnectionIdleManager.this.isActive) {
                    MaxConnectionIdleManager maxConnectionIdleManager = MaxConnectionIdleManager.this;
                    ScheduledFuture unused2 = maxConnectionIdleManager.shutdownFuture = scheduledExecutorService.schedule(maxConnectionIdleManager.shutdownTask, MaxConnectionIdleManager.this.nextIdleMonitorTime - MaxConnectionIdleManager.this.ticker.nanoTime(), TimeUnit.NANOSECONDS);
                    boolean unused3 = MaxConnectionIdleManager.this.shutdownDelayed = false;
                }
            }
        });
        this.shutdownTask = logExceptionRunnable;
        this.shutdownFuture = scheduledExecutorService.schedule(logExceptionRunnable, this.maxConnectionIdleInNanos, TimeUnit.NANOSECONDS);
    }

    public void onTransportActive() {
        this.isActive = true;
        this.shutdownDelayed = true;
    }

    public void onTransportIdle() {
        this.isActive = false;
        ScheduledFuture<?> scheduledFuture = this.shutdownFuture;
        if (scheduledFuture != null) {
            if (scheduledFuture.isDone()) {
                this.shutdownDelayed = false;
                this.shutdownFuture = this.scheduler.schedule(this.shutdownTask, this.maxConnectionIdleInNanos, TimeUnit.NANOSECONDS);
                return;
            }
            this.nextIdleMonitorTime = this.ticker.nanoTime() + this.maxConnectionIdleInNanos;
        }
    }

    public void onTransportTermination() {
        ScheduledFuture<?> scheduledFuture = this.shutdownFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.shutdownFuture = null;
        }
    }
}
