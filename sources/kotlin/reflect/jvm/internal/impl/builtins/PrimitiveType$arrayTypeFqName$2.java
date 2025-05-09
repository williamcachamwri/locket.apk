package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* compiled from: PrimitiveType.kt */
final class PrimitiveType$arrayTypeFqName$2 extends Lambda implements Function0<FqName> {
    final /* synthetic */ PrimitiveType this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PrimitiveType$arrayTypeFqName$2(PrimitiveType primitiveType) {
        super(0);
        this.this$0 = primitiveType;
    }

    public final FqName invoke() {
        FqName child = StandardNames.BUILT_INS_PACKAGE_FQ_NAME.child(this.this$0.getArrayTypeName());
        Intrinsics.checkNotNullExpressionValue(child, "child(...)");
        return child;
    }
}
