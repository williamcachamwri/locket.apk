package com.google.devtools.ksp;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "result", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: utils.kt */
final class UtilsKt$asArray$1 extends Lambda implements Function1<Object, Object> {
    final /* synthetic */ Method $method;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UtilsKt$asArray$1(Method method) {
        super(1);
        this.$method = method;
    }

    public final Object invoke(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "result");
        Class<?> componentType = this.$method.getReturnType().getComponentType();
        Intrinsics.checkNotNullExpressionValue(componentType, "method.returnType.componentType");
        Object access$asEnum = UtilsKt.asEnum(obj, componentType);
        Intrinsics.checkNotNullExpressionValue(access$asEnum, "result.asEnum(method.returnType.componentType)");
        return access$asEnum;
    }
}
