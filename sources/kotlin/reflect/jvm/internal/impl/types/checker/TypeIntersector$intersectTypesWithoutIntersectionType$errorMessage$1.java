package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

/* compiled from: IntersectionType.kt */
final class TypeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1 extends Lambda implements Function0<String> {
    final /* synthetic */ Set<SimpleType> $inputTypes;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TypeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1(Set<? extends SimpleType> set) {
        super(0);
        this.$inputTypes = set;
    }

    public final String invoke() {
        return "This collections cannot be empty! input types: " + CollectionsKt.joinToString$default(this.$inputTypes, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null);
    }
}
