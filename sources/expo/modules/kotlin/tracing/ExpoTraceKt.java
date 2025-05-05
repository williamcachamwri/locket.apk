package expo.modules.kotlin.tracing;

import androidx.tracing.Trace;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b\u001a\u0019\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\b\u001a#\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b\u001a\t\u0010\t\u001a\u00020\u0001H\b\u001a/\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\u0004\u001a\u00020\u00032\u000e\b\u0004\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a7\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u000e\b\u0004\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\rH\bø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0010"}, d2 = {"beginAsyncTraceBlock", "", "tag", "", "blockName", "cookie", "", "beginTraceBlock", "endAsyncTraceBlock", "endTraceBlock", "trace", "T", "block", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoTrace.kt */
public final class ExpoTraceKt {
    public static final <T> T trace(String str, String str2, Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, "blockName");
        Intrinsics.checkNotNullParameter(function0, "block");
        Trace.beginSection("[" + str + "] " + str2);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            Trace.endSection();
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void beginTraceBlock(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, "blockName");
        Trace.beginSection("[" + str + "] " + str2);
    }

    public static final void endTraceBlock() {
        Trace.endSection();
    }

    public static /* synthetic */ void beginAsyncTraceBlock$default(String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, "blockName");
        Trace.beginAsyncSection("[" + str + "] " + str2, i);
    }

    public static final void beginAsyncTraceBlock(String str, String str2, int i) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, "blockName");
        Trace.beginAsyncSection("[" + str + "] " + str2, i);
    }

    public static /* synthetic */ void endAsyncTraceBlock$default(String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, "blockName");
        Trace.endAsyncSection("[" + str + "] " + str2, i);
    }

    public static final void endAsyncTraceBlock(String str, String str2, int i) {
        Intrinsics.checkNotNullParameter(str, ViewHierarchyNode.JsonKeys.TAG);
        Intrinsics.checkNotNullParameter(str2, "blockName");
        Trace.endAsyncSection("[" + str + "] " + str2, i);
    }

    public static final <T> T trace(String str, Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(str, "blockName");
        Intrinsics.checkNotNullParameter(function0, "block");
        Trace.beginSection("[ExpoModulesCore] " + str);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            Trace.endSection();
            InlineMarker.finallyEnd(1);
        }
    }
}
