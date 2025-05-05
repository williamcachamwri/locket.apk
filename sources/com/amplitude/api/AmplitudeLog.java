package com.amplitude.api;

import android.util.Log;
import io.sentry.android.core.SentryLogcatAdapter;

public class AmplitudeLog {
    protected static AmplitudeLog instance = new AmplitudeLog();
    private AmplitudeLogCallback amplitudeLogCallback = null;
    private volatile boolean enableLogging = true;
    private volatile int logLevel = 4;

    public static AmplitudeLog getLogger() {
        return instance;
    }

    private AmplitudeLog() {
    }

    /* access modifiers changed from: package-private */
    public AmplitudeLog setEnableLogging(boolean z) {
        this.enableLogging = z;
        return instance;
    }

    /* access modifiers changed from: package-private */
    public AmplitudeLog setLogLevel(int i) {
        this.logLevel = i;
        return instance;
    }

    /* access modifiers changed from: package-private */
    public int d(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 3) {
            return 0;
        }
        return Log.d(str, str2);
    }

    /* access modifiers changed from: package-private */
    public int d(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 3) {
            return 0;
        }
        return Log.d(str, str2, th);
    }

    /* access modifiers changed from: package-private */
    public int e(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 6) {
            return 0;
        }
        AmplitudeLogCallback amplitudeLogCallback2 = this.amplitudeLogCallback;
        if (amplitudeLogCallback2 != null) {
            amplitudeLogCallback2.onError(str, str2);
        }
        return SentryLogcatAdapter.e(str, str2);
    }

    /* access modifiers changed from: package-private */
    public int e(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 6) {
            return 0;
        }
        AmplitudeLogCallback amplitudeLogCallback2 = this.amplitudeLogCallback;
        if (amplitudeLogCallback2 != null) {
            amplitudeLogCallback2.onError(str, str2);
        }
        return SentryLogcatAdapter.e(str, str2, th);
    }

    /* access modifiers changed from: package-private */
    public String getStackTraceString(Throwable th) {
        return Log.getStackTraceString(th);
    }

    /* access modifiers changed from: package-private */
    public int i(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 4) {
            return 0;
        }
        return Log.i(str, str2);
    }

    /* access modifiers changed from: package-private */
    public int i(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 4) {
            return 0;
        }
        return Log.i(str, str2, th);
    }

    /* access modifiers changed from: package-private */
    public boolean isLoggable(String str, int i) {
        return Log.isLoggable(str, i);
    }

    /* access modifiers changed from: package-private */
    public int println(int i, String str, String str2) {
        return Log.println(i, str, str2);
    }

    /* access modifiers changed from: package-private */
    public int v(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 2) {
            return 0;
        }
        return Log.v(str, str2);
    }

    /* access modifiers changed from: package-private */
    public int v(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 2) {
            return 0;
        }
        return Log.v(str, str2, th);
    }

    /* access modifiers changed from: package-private */
    public int w(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 5) {
            return 0;
        }
        return SentryLogcatAdapter.w(str, str2);
    }

    /* access modifiers changed from: package-private */
    public int w(String str, Throwable th) {
        if (!this.enableLogging || this.logLevel > 5) {
            return 0;
        }
        return SentryLogcatAdapter.w(str, th);
    }

    /* access modifiers changed from: package-private */
    public int w(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 5) {
            return 0;
        }
        return SentryLogcatAdapter.w(str, str2, th);
    }

    /* access modifiers changed from: package-private */
    public int wtf(String str, String str2) {
        if (!this.enableLogging || this.logLevel > 7) {
            return 0;
        }
        return SentryLogcatAdapter.wtf(str, str2);
    }

    /* access modifiers changed from: package-private */
    public int wtf(String str, Throwable th) {
        if (!this.enableLogging || this.logLevel > 7) {
            return 0;
        }
        return SentryLogcatAdapter.wtf(str, th);
    }

    /* access modifiers changed from: package-private */
    public int wtf(String str, String str2, Throwable th) {
        if (!this.enableLogging || this.logLevel > 7) {
            return 0;
        }
        return SentryLogcatAdapter.wtf(str, str2, th);
    }

    /* access modifiers changed from: package-private */
    public void setAmplitudeLogCallback(AmplitudeLogCallback amplitudeLogCallback2) {
        this.amplitudeLogCallback = amplitudeLogCallback2;
    }
}
