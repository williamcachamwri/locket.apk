package io.sentry.android.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import io.sentry.SentryDate;

public final class SentryPerformanceProvider extends EmptySecureContentProvider implements Application.ActivityLifecycleCallbacks {
    private static long appStartMillis = SystemClock.uptimeMillis();
    private static SentryDate appStartTime = AndroidDateUtils.getCurrentSentryDateTime();
    private Application application;
    private boolean firstActivityCreated = false;
    private boolean firstActivityResumed = false;

    public String getType(Uri uri) {
        return null;
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public SentryPerformanceProvider() {
        AppStartState.getInstance().setAppStartTime(appStartMillis, appStartTime);
    }

    public boolean onCreate() {
        Context context = getContext();
        if (context == null) {
            return false;
        }
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        if (!(context instanceof Application)) {
            return true;
        }
        Application application2 = (Application) context;
        this.application = application2;
        application2.registerActivityLifecycleCallbacks(this);
        return true;
    }

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        if (!SentryPerformanceProvider.class.getName().equals(providerInfo.authority)) {
            super.attachInfo(context, providerInfo);
            return;
        }
        throw new IllegalStateException("An applicationId is required to fulfill the manifest placeholder.");
    }

    static void setAppStartTime(long j, SentryDate sentryDate) {
        appStartMillis = j;
        appStartTime = sentryDate;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (!this.firstActivityCreated) {
            AppStartState.getInstance().setColdStart(bundle == null);
            this.firstActivityCreated = true;
        }
    }

    public void onActivityResumed(Activity activity) {
        if (!this.firstActivityResumed) {
            this.firstActivityResumed = true;
            AppStartState.getInstance().setAppStartEnd();
        }
        Application application2 = this.application;
        if (application2 != null) {
            application2.unregisterActivityLifecycleCallbacks(this);
        }
    }
}
