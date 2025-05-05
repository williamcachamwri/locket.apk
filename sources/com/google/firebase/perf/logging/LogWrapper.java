package com.google.firebase.perf.logging;

import android.util.Log;
import io.sentry.android.core.SentryLogcatAdapter;

class LogWrapper {
    private static final String LOG_TAG = "FirebasePerformance";
    private static LogWrapper instance;

    public static synchronized LogWrapper getInstance() {
        LogWrapper logWrapper;
        synchronized (LogWrapper.class) {
            if (instance == null) {
                instance = new LogWrapper();
            }
            logWrapper = instance;
        }
        return logWrapper;
    }

    /* access modifiers changed from: package-private */
    public void d(String str) {
        Log.d(LOG_TAG, str);
    }

    /* access modifiers changed from: package-private */
    public void v(String str) {
        Log.v(LOG_TAG, str);
    }

    /* access modifiers changed from: package-private */
    public void i(String str) {
        Log.i(LOG_TAG, str);
    }

    /* access modifiers changed from: package-private */
    public void w(String str) {
        SentryLogcatAdapter.w(LOG_TAG, str);
    }

    /* access modifiers changed from: package-private */
    public void e(String str) {
        SentryLogcatAdapter.e(LOG_TAG, str);
    }

    private LogWrapper() {
    }
}
