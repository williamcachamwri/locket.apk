package expo.modules.devlauncher.logs;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Ljava/lang/StackTraceElement;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherRemoteLogManager.kt */
final class DevLauncherRemoteLogManagerKt$toRemoteLogString$baseTrace$1 extends Lambda implements Function1<StackTraceElement, CharSequence> {
    public static final DevLauncherRemoteLogManagerKt$toRemoteLogString$baseTrace$1 INSTANCE = new DevLauncherRemoteLogManagerKt$toRemoteLogString$baseTrace$1();

    DevLauncherRemoteLogManagerKt$toRemoteLogString$baseTrace$1() {
        super(1);
    }

    public final CharSequence invoke(StackTraceElement stackTraceElement) {
        String stackTraceElement2 = stackTraceElement.toString();
        Intrinsics.checkNotNullExpressionValue(stackTraceElement2, "toString(...)");
        return stackTraceElement2;
    }
}
