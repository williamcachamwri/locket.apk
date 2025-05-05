package expo.modules.devlauncher.logs;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0003\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"toRemoteLogString", "", "", "expo-dev-launcher_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherRemoteLogManager.kt */
public final class DevLauncherRemoteLogManagerKt {
    public static final String toRemoteLogString(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        StackTraceElement[] stackTrace = th.getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "getStackTrace(...)");
        String str = th + "\n  " + ArraysKt.joinToString$default((Object[]) stackTrace, (CharSequence) "\n  ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) DevLauncherRemoteLogManagerKt$toRemoteLogString$baseTrace$1.INSTANCE, 30, (Object) null);
        Throwable cause = th.getCause();
        if (cause == null) {
            return str;
        }
        return str + "\nCaused by " + toRemoteLogString(cause);
    }
}
