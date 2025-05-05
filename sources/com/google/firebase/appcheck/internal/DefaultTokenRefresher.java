package com.google.firebase.appcheck.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DefaultTokenRefresher {
    static final long INITIAL_DELAY_SECONDS = 30;
    static final long MAX_DELAY_SECONDS = 960;
    private static final long UNSET_DELAY = -1;
    private volatile long delayAfterFailureSeconds = -1;
    private final DefaultFirebaseAppCheck firebaseAppCheck;
    private final Executor liteExecutor;
    private volatile ScheduledFuture<?> refreshFuture;
    private final ScheduledExecutorService scheduledExecutorService;

    DefaultTokenRefresher(DefaultFirebaseAppCheck defaultFirebaseAppCheck, Executor executor, ScheduledExecutorService scheduledExecutorService2) {
        this.firebaseAppCheck = (DefaultFirebaseAppCheck) Preconditions.checkNotNull(defaultFirebaseAppCheck);
        this.liteExecutor = executor;
        this.scheduledExecutorService = scheduledExecutorService2;
    }

    public void scheduleRefresh(long j) {
        cancel();
        this.delayAfterFailureSeconds = -1;
        this.refreshFuture = this.scheduledExecutorService.schedule(new DefaultTokenRefresher$$ExternalSyntheticLambda0(this), Math.max(0, j), TimeUnit.MILLISECONDS);
    }

    private void scheduleRefreshAfterFailure() {
        cancel();
        this.delayAfterFailureSeconds = getNextRefreshMillis();
        this.refreshFuture = this.scheduledExecutorService.schedule(new DefaultTokenRefresher$$ExternalSyntheticLambda0(this), this.delayAfterFailureSeconds, TimeUnit.SECONDS);
    }

    private long getNextRefreshMillis() {
        if (this.delayAfterFailureSeconds == -1) {
            return INITIAL_DELAY_SECONDS;
        }
        if (this.delayAfterFailureSeconds * 2 < MAX_DELAY_SECONDS) {
            return this.delayAfterFailureSeconds * 2;
        }
        return MAX_DELAY_SECONDS;
    }

    /* access modifiers changed from: private */
    public void onRefresh() {
        this.firebaseAppCheck.fetchTokenFromProvider().addOnFailureListener(this.liteExecutor, (OnFailureListener) new DefaultTokenRefresher$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onRefresh$0$com-google-firebase-appcheck-internal-DefaultTokenRefresher  reason: not valid java name */
    public /* synthetic */ void m577lambda$onRefresh$0$comgooglefirebaseappcheckinternalDefaultTokenRefresher(Exception exc) {
        scheduleRefreshAfterFailure();
    }

    public void cancel() {
        if (this.refreshFuture != null && !this.refreshFuture.isDone()) {
            this.refreshFuture.cancel(false);
        }
    }
}
