package androidx.work;

import androidx.work.WorkRequest;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public final class PeriodicWorkRequest extends WorkRequest {
    public static final long MIN_PERIODIC_FLEX_MILLIS = 300000;
    public static final long MIN_PERIODIC_INTERVAL_MILLIS = 900000;

    PeriodicWorkRequest(Builder builder) {
        super(builder.mId, builder.mWorkSpec, builder.mTags);
    }

    public static final class Builder extends WorkRequest.Builder<Builder, PeriodicWorkRequest> {
        /* access modifiers changed from: package-private */
        public Builder getThis() {
            return this;
        }

        public Builder(Class<? extends ListenableWorker> cls, long j, TimeUnit timeUnit) {
            super(cls);
            this.mWorkSpec.setPeriodic(timeUnit.toMillis(j));
        }

        public Builder(Class<? extends ListenableWorker> cls, Duration duration) {
            super(cls);
            this.mWorkSpec.setPeriodic(duration.toMillis());
        }

        public Builder(Class<? extends ListenableWorker> cls, long j, TimeUnit timeUnit, long j2, TimeUnit timeUnit2) {
            super(cls);
            this.mWorkSpec.setPeriodic(timeUnit.toMillis(j), timeUnit2.toMillis(j2));
        }

        public Builder(Class<? extends ListenableWorker> cls, Duration duration, Duration duration2) {
            super(cls);
            this.mWorkSpec.setPeriodic(duration.toMillis(), duration2.toMillis());
        }

        /* access modifiers changed from: package-private */
        public PeriodicWorkRequest buildInternal() {
            if (!this.mBackoffCriteriaSet || !this.mWorkSpec.constraints.requiresDeviceIdle()) {
                return new PeriodicWorkRequest(this);
            }
            throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
        }
    }
}
