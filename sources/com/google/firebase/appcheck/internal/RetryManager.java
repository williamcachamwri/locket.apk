package com.google.firebase.appcheck.internal;

import com.google.firebase.appcheck.internal.util.Clock;

public class RetryManager {
    static final int BAD_REQUEST_ERROR_CODE = 400;
    private static final int EXPONENTIAL = 0;
    static final long MAX_EXP_BACKOFF_MILLIS = 14400000;
    private static final double MAX_JITTER_MULTIPLIER = 0.5d;
    static final int NOT_FOUND_ERROR_CODE = 404;
    private static final int ONE_DAY = 1;
    static final long ONE_DAY_MILLIS = 86400000;
    static final long ONE_SECOND_MILLIS = 1000;
    static final long UNSET_RETRY_TIME = -1;
    private final Clock clock;
    private long currentRetryCount;
    private long nextRetryTimeMillis;

    private static int getBackoffStrategyByErrorCode(int i) {
        return (i == 400 || i == NOT_FOUND_ERROR_CODE) ? 1 : 0;
    }

    public RetryManager() {
        this.currentRetryCount = 0;
        this.nextRetryTimeMillis = -1;
        this.clock = new Clock.DefaultClock();
    }

    RetryManager(Clock clock2) {
        this.currentRetryCount = 0;
        this.nextRetryTimeMillis = -1;
        this.clock = clock2;
    }

    public boolean canRetry() {
        return this.nextRetryTimeMillis <= this.clock.currentTimeMillis();
    }

    /* access modifiers changed from: package-private */
    public long getNextRetryTimeMillis() {
        return this.nextRetryTimeMillis;
    }

    public void resetBackoffOnSuccess() {
        this.currentRetryCount = 0;
        this.nextRetryTimeMillis = -1;
    }

    public void updateBackoffOnFailure(int i) {
        this.currentRetryCount++;
        if (getBackoffStrategyByErrorCode(i) == 1) {
            this.nextRetryTimeMillis = this.clock.currentTimeMillis() + ONE_DAY_MILLIS;
            return;
        }
        this.nextRetryTimeMillis = this.clock.currentTimeMillis() + Math.min((long) (Math.pow(2.0d, ((double) this.currentRetryCount) * ((Math.random() * 0.5d) + 1.0d)) * 1000.0d), MAX_EXP_BACKOFF_MILLIS);
    }
}
