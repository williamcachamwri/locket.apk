package expo.modules.kotlin.classcomponent;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00030\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "SharedObjectType", "", "it", "", "invoke", "([Ljava/lang/Object;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClassComponentBuilder.kt */
final class ClassComponentBuilder$buildClass$constructor$1 extends Lambda implements Function1<Object[], Unit> {
    public static final ClassComponentBuilder$buildClass$constructor$1 INSTANCE = new ClassComponentBuilder$buildClass$constructor$1();

    ClassComponentBuilder$buildClass$constructor$1() {
        super(1);
    }

    public final void invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Object[]) obj);
        return Unit.INSTANCE;
    }
}
