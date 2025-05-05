package androidx.camera.core;

import android.util.Log;
import io.sentry.android.core.SentryLogcatAdapter;

public final class Logger {
    static final int DEFAULT_MIN_LOG_LEVEL = 3;
    private static final int MAX_TAG_LENGTH = 23;
    private static int sMinLogLevel = 3;

    private static String truncateTag(String str) {
        return str;
    }

    private Logger() {
    }

    private static boolean isLogLevelEnabled(String str, int i) {
        return sMinLogLevel <= i || Log.isLoggable(str, i);
    }

    static void setMinLogLevel(int i) {
        sMinLogLevel = i;
    }

    static int getMinLogLevel() {
        return sMinLogLevel;
    }

    static void resetMinLogLevel() {
        sMinLogLevel = 3;
    }

    public static boolean isVerboseEnabled(String str) {
        return isLogLevelEnabled(truncateTag(str), 2);
    }

    public static boolean isDebugEnabled(String str) {
        return isLogLevelEnabled(truncateTag(str), 3);
    }

    public static boolean isInfoEnabled(String str) {
        return isLogLevelEnabled(truncateTag(str), 4);
    }

    public static boolean isWarnEnabled(String str) {
        return isLogLevelEnabled(truncateTag(str), 5);
    }

    public static boolean isErrorEnabled(String str) {
        return isLogLevelEnabled(truncateTag(str), 6);
    }

    public static void d(String str, String str2) {
        String truncateTag = truncateTag(str);
        if (isLogLevelEnabled(truncateTag, 3)) {
            Log.d(truncateTag, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        String truncateTag = truncateTag(str);
        if (isLogLevelEnabled(truncateTag, 3)) {
            Log.d(truncateTag, str2, th);
        }
    }

    public static void i(String str, String str2) {
        String truncateTag = truncateTag(str);
        if (isLogLevelEnabled(truncateTag, 4)) {
            Log.i(truncateTag, str2);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        String truncateTag = truncateTag(str);
        if (isLogLevelEnabled(truncateTag, 4)) {
            Log.i(truncateTag, str2, th);
        }
    }

    public static void w(String str, String str2) {
        String truncateTag = truncateTag(str);
        if (isLogLevelEnabled(truncateTag, 5)) {
            SentryLogcatAdapter.w(truncateTag, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        String truncateTag = truncateTag(str);
        if (isLogLevelEnabled(truncateTag, 5)) {
            SentryLogcatAdapter.w(truncateTag, str2, th);
        }
    }

    public static void e(String str, String str2) {
        String truncateTag = truncateTag(str);
        if (isLogLevelEnabled(truncateTag, 6)) {
            SentryLogcatAdapter.e(truncateTag, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        String truncateTag = truncateTag(str);
        if (isLogLevelEnabled(truncateTag, 6)) {
            SentryLogcatAdapter.e(truncateTag, str2, th);
        }
    }
}
