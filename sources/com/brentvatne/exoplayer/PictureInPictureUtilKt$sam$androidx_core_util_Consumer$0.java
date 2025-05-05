package com.brentvatne.exoplayer;

import androidx.core.util.Consumer;
import io.sentry.protocol.SentryStackFrame;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PictureInPictureUtil.kt */
final class PictureInPictureUtilKt$sam$androidx_core_util_Consumer$0 implements Consumer, FunctionAdapter {
    private final /* synthetic */ Function1 function;

    PictureInPictureUtilKt$sam$androidx_core_util_Consumer$0(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, SentryStackFrame.JsonKeys.FUNCTION);
        this.function = function1;
    }

    public final /* synthetic */ void accept(Object obj) {
        this.function.invoke(obj);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Consumer) || !(obj instanceof FunctionAdapter)) {
            return false;
        }
        return Intrinsics.areEqual((Object) getFunctionDelegate(), (Object) ((FunctionAdapter) obj).getFunctionDelegate());
    }

    public final Function<?> getFunctionDelegate() {
        return this.function;
    }

    public final int hashCode() {
        return getFunctionDelegate().hashCode();
    }
}
