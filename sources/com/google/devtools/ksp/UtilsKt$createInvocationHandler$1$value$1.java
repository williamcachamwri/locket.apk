package com.google.devtools.ksp;

import java.lang.reflect.Method;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: utils.kt */
final class UtilsKt$createInvocationHandler$1$value$1 extends Lambda implements Function0<Object> {
    final /* synthetic */ Class<?> $clazz;
    final /* synthetic */ Method $method;
    final /* synthetic */ Object $result;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UtilsKt$createInvocationHandler$1$value$1(Object obj, Method method, Class<?> cls) {
        super(0);
        this.$result = obj;
        this.$method = method;
        this.$clazz = cls;
    }

    public final Object invoke() {
        Object obj = this.$result;
        Intrinsics.checkNotNullExpressionValue(obj, "result");
        Method method = this.$method;
        Intrinsics.checkNotNullExpressionValue(method, "method");
        return UtilsKt.asArray((List<?>) (List) obj, method, this.$clazz);
    }
}
