package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;

public final class KeepAliveEnforcer {
    public static final long IMPLICIT_PERMIT_TIME_NANOS = TimeUnit.HOURS.toNanos(2);
    public static final int MAX_PING_STRIKES = 2;
    private final long epoch;
    private boolean hasOutstandingCalls;
    private long lastValidPingTime;
    private final long minTimeNanos;
    private final boolean permitWithoutCalls;
    private int pingStrikes;
    private final Ticker ticker;

    interface Ticker {
        long nanoTime();
    }

    private static long compareNanos(long j, long j2) {
        return j - j2;
    }

    public KeepAliveEnforcer(boolean z, long j, TimeUnit timeUnit) {
        this(z, j, timeUnit, SystemTicker.INSTANCE);
    }

    KeepAliveEnforcer(boolean z, long j, TimeUnit timeUnit, Ticker ticker2) {
        Preconditions.checkArgument(j >= 0, "minTime must be non-negative: %s", j);
        this.permitWithoutCalls = z;
        this.minTimeNanos = Math.min(timeUnit.toNanos(j), IMPLICIT_PERMIT_TIME_NANOS);
        this.ticker = ticker2;
        long nanoTime = ticker2.nanoTime();
        this.epoch = nanoTime;
        this.lastValidPingTime = nanoTime;
    }

    @CheckReturnValue
    public boolean pingAcceptable() {
        long nanoTime = this.ticker.nanoTime();
        if (!(this.hasOutstandingCalls || this.permitWithoutCalls ? compareNanos(this.lastValidPingTime + this.minTimeNanos, nanoTime) <= 0 : compareNanos(this.lastValidPingTime + IMPLICIT_PERMIT_TIME_NANOS, nanoTime) <= 0)) {
            int i = this.pingStrikes + 1;
            this.pingStrikes = i;
            if (i <= 2) {
                return true;
            }
            return false;
        }
        this.lastValidPingTime = nanoTime;
        return true;
    }

    public void resetCounters() {
        this.lastValidPingTime = this.epoch;
        this.pingStrikes = 0;
    }

    public void onTransportActive() {
        this.hasOutstandingCalls = true;
    }

    public void onTransportIdle() {
        this.hasOutstandingCalls = false;
    }

    static class SystemTicker implements Ticker {
        public static final SystemTicker INSTANCE = new SystemTicker();

        SystemTicker() {
        }

        public long nanoTime() {
            return System.nanoTime();
        }
    }
}
