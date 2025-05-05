package expo.modules.kotlin.classcomponent;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\b\b\u0001\u0010\u0003*\u00020\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "P0", "SharedObjectType", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: ClassComponentBuilder.kt */
public final class ClassComponentBuilder$Constructor$4 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ Function1<P0, SharedObjectType> $body;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClassComponentBuilder$Constructor$4(Function1<? super P0, ? extends SharedObjectType> function1) {
        super(1);
        this.$body = function1;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        Function1<P0, SharedObjectType> function1 = this.$body;
        Object obj = objArr[0];
        Intrinsics.reifiedOperationMarker(1, "P0");
        Object obj2 = obj;
        return function1.invoke(obj);
    }
}
