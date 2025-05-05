package com.reactnativekeyboardcontroller.log;

import android.util.Log;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bJ$\u0010\f\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/reactnativekeyboardcontroller/log/Logger;", "", "()V", "enabled", "", "i", "", "tag", "", "message", "throwable", "", "w", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Logger.kt */
public final class Logger {
    public static final Logger INSTANCE = new Logger();
    private static final boolean enabled = false;

    private Logger() {
    }

    public static /* synthetic */ void i$default(Logger logger, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        logger.i(str, str2, th);
    }

    public final void i(String str, String str2, Throwable th) {
        Intrinsics.checkNotNullParameter(str2, "message");
        if (enabled) {
            Log.i(str, str2, th);
        }
    }

    public static /* synthetic */ void w$default(Logger logger, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        logger.w(str, str2, th);
    }

    public final void w(String str, String str2, Throwable th) {
        Intrinsics.checkNotNullParameter(str2, "message");
        if (enabled) {
            SentryLogcatAdapter.w(str, str2, th);
        }
    }
}
