package com.android.installreferrer.commons;

import android.util.Log;
import io.sentry.android.core.SentryLogcatAdapter;

public final class InstallReferrerCommons {
    public static void logVerbose(String str, String str2) {
        if (Log.isLoggable(str, 2)) {
            Log.v(str, str2);
        }
    }

    public static void logWarn(String str, String str2) {
        if (Log.isLoggable(str, 5)) {
            SentryLogcatAdapter.w(str, str2);
        }
    }
}
