package com.google.devtools.ksp;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Long;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: utils.kt */
final class UtilsKt$createInvocationHandler$1$value$7 extends Lambda implements Function0<Long> {
    final /* synthetic */ Object $result;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UtilsKt$createInvocationHandler$1$value$7(Object obj) {
        super(0);
        this.$result = obj;
    }

    public final Long invoke() {
        Object obj = this.$result;
        Intrinsics.checkNotNullExpressionValue(obj, "result");
        return Long.valueOf(UtilsKt.asLong(obj));
    }
}
