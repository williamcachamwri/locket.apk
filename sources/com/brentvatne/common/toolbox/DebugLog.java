package com.brentvatne.common.toolbox;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.protocol.SentryThread;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0003J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0003J\u0018\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\b\u0010\u0013\u001a\u00020\nH\u0007J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0006H\u0007J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0018\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0018\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/brentvatne/common/toolbox/DebugLog;", "", "()V", "TAG_PREFIX", "", "displayThread", "", "level", "", "checkNotUIThread", "", "tag", "msg", "checkUIThread", "d", "e", "getMsg", "getTag", "i", "printCallStack", "setConfig", "_level", "_displayThread", "v", "w", "wtf", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DebugLog.kt */
public final class DebugLog {
    public static final DebugLog INSTANCE = new DebugLog();
    private static final String TAG_PREFIX = "RNV";
    private static boolean displayThread = true;
    private static int level = 5;

    private DebugLog() {
    }

    @JvmStatic
    public static final void setConfig(int i, boolean z) {
        level = i;
        displayThread = z;
    }

    @JvmStatic
    private static final String getTag(String str) {
        return TAG_PREFIX + str;
    }

    @JvmStatic
    private static final String getMsg(String str) {
        if (!displayThread) {
            return str;
        }
        return "[" + Thread.currentThread().getName() + "] " + str;
    }

    @JvmStatic
    public static final void v(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, NotificationCompat.CATEGORY_MESSAGE);
        if (level <= 2) {
            Log.v(getTag(str), getMsg(str2));
        }
    }

    @JvmStatic
    public static final void d(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, NotificationCompat.CATEGORY_MESSAGE);
        if (level <= 3) {
            Log.d(getTag(str), getMsg(str2));
        }
    }

    @JvmStatic
    public static final void i(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, NotificationCompat.CATEGORY_MESSAGE);
        if (level <= 4) {
            Log.i(getTag(str), getMsg(str2));
        }
    }

    @JvmStatic
    public static final void w(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, NotificationCompat.CATEGORY_MESSAGE);
        if (level <= 5) {
            SentryLogcatAdapter.w(getTag(str), getMsg(str2));
        }
    }

    @JvmStatic
    public static final void e(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, NotificationCompat.CATEGORY_MESSAGE);
        if (level <= 6) {
            SentryLogcatAdapter.e(getTag(str), getMsg(str2));
        }
    }

    @JvmStatic
    public static final void wtf(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, NotificationCompat.CATEGORY_MESSAGE);
        SentryLogcatAdapter.wtf(getTag(str), "--------------->" + getMsg(str2));
        printCallStack();
    }

    @JvmStatic
    public static final void printCallStack() {
        if (level <= 2) {
            new Exception().printStackTrace();
        }
    }

    @JvmStatic
    public static final void checkUIThread(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, NotificationCompat.CATEGORY_MESSAGE);
        if (!Intrinsics.areEqual((Object) Thread.currentThread().getName(), (Object) SentryThread.JsonKeys.MAIN)) {
            wtf(str, "------------------------>" + getMsg(str2));
        }
    }

    @JvmStatic
    public static final void checkNotUIThread(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, NotificationCompat.CATEGORY_MESSAGE);
        if (Intrinsics.areEqual((Object) Thread.currentThread().getName(), (Object) SentryThread.JsonKeys.MAIN)) {
            wtf(str, "------------------------>" + getMsg(str2));
        }
    }
}
