package com.mrousavy.camera.react;

import androidx.lifecycle.Observer;
import io.sentry.protocol.SentryStackFrame;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraView.kt */
final class CameraView$sam$androidx_lifecycle_Observer$0 implements Observer, FunctionAdapter {
    private final /* synthetic */ Function1 function;

    CameraView$sam$androidx_lifecycle_Observer$0(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, SentryStackFrame.JsonKeys.FUNCTION);
        this.function = function1;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Observer) || !(obj instanceof FunctionAdapter)) {
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

    public final /* synthetic */ void onChanged(Object obj) {
        this.function.invoke(obj);
    }
}
