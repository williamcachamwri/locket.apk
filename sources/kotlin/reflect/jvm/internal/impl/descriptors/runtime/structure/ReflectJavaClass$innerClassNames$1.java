package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: ReflectJavaClass.kt */
final class ReflectJavaClass$innerClassNames$1 extends Lambda implements Function1<Class<?>, Boolean> {
    public static final ReflectJavaClass$innerClassNames$1 INSTANCE = new ReflectJavaClass$innerClassNames$1();

    ReflectJavaClass$innerClassNames$1() {
        super(1);
    }

    public final Boolean invoke(Class<?> cls) {
        String simpleName = cls.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        return Boolean.valueOf(simpleName.length() == 0);
    }
}
