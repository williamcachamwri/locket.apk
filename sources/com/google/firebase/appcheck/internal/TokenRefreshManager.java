package com.google.firebase.appcheck.internal;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.appcheck.internal.util.Clock;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public final class TokenRefreshManager {
    private static final long FIVE_MINUTES_IN_MILLIS = 300000;
    private static final long REFRESH_BUFFER_ABSOLUTE_MILLIS = 60000;
    private static final double REFRESH_BUFFER_FRACTION = 0.5d;
    private static final long UNSET_REFRESH_TIME = -1;
    private final Clock clock;
    private volatile int currentListenerCount;
    private volatile boolean isAutoRefreshEnabled;
    /* access modifiers changed from: private */
    public volatile boolean isBackgrounded;
    /* access modifiers changed from: private */
    public volatile long nextRefreshTimeMillis;
    private final DefaultTokenRefresher tokenRefresher;

    TokenRefreshManager(Context context, DefaultFirebaseAppCheck defaultFirebaseAppCheck, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        this((Context) Preconditions.checkNotNull(context), new DefaultTokenRefresher((DefaultFirebaseAppCheck) Preconditions.checkNotNull(defaultFirebaseAppCheck), executor, scheduledExecutorService), new Clock.DefaultClock());
    }

    TokenRefreshManager(Context context, final DefaultTokenRefresher defaultTokenRefresher, final Clock clock2) {
        this.tokenRefresher = defaultTokenRefresher;
        this.clock = clock2;
        this.nextRefreshTimeMillis = -1;
        BackgroundDetector.initialize((Application) context.getApplicationContext());
        BackgroundDetector.getInstance().addListener(new BackgroundDetector.BackgroundStateChangeListener() {
            public void onBackgroundStateChanged(boolean z) {
                boolean unused = TokenRefreshManager.this.isBackgrounded = z;
                if (z) {
                    defaultTokenRefresher.cancel();
                } else if (TokenRefreshManager.this.shouldScheduleRefresh()) {
                    defaultTokenRefresher.scheduleRefresh(TokenRefreshManager.this.nextRefreshTimeMillis - clock2.currentTimeMillis());
                }
            }
        });
    }

    public void maybeScheduleTokenRefresh(AppCheckToken appCheckToken) {
        DefaultAppCheckToken defaultAppCheckToken;
        if (appCheckToken instanceof DefaultAppCheckToken) {
            defaultAppCheckToken = (DefaultAppCheckToken) appCheckToken;
        } else {
            defaultAppCheckToken = DefaultAppCheckToken.constructFromRawToken(appCheckToken.getToken());
        }
        this.nextRefreshTimeMillis = defaultAppCheckToken.getReceivedAtTimestamp() + ((long) (((double) defaultAppCheckToken.getExpiresInMillis()) * 0.5d)) + 300000;
        if (this.nextRefreshTimeMillis > defaultAppCheckToken.getExpireTimeMillis()) {
            this.nextRefreshTimeMillis = defaultAppCheckToken.getExpireTimeMillis() - 60000;
        }
        if (shouldScheduleRefresh()) {
            this.tokenRefresher.scheduleRefresh(this.nextRefreshTimeMillis - this.clock.currentTimeMillis());
        }
    }

    public void onListenerCountChanged(int i) {
        if (this.currentListenerCount == 0 && i > 0) {
            this.currentListenerCount = i;
            if (shouldScheduleRefresh()) {
                this.tokenRefresher.scheduleRefresh(this.nextRefreshTimeMillis - this.clock.currentTimeMillis());
            }
        } else if (this.currentListenerCount > 0 && i == 0) {
            this.tokenRefresher.cancel();
        }
        this.currentListenerCount = i;
    }

    public void setIsAutoRefreshEnabled(boolean z) {
        this.isAutoRefreshEnabled = z;
    }

    /* access modifiers changed from: private */
    public boolean shouldScheduleRefresh() {
        return this.isAutoRefreshEnabled && !this.isBackgrounded && this.currentListenerCount > 0 && this.nextRefreshTimeMillis != -1;
    }
}
