package kotlin.reflect.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002 \u0003*\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "kotlin.jvm.PlatformType", "T", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KClassImpl.kt */
final class KClassImpl$Data$allStaticMembers$2 extends Lambda implements Function0<List<? extends KCallableImpl<?>>> {
    final /* synthetic */ KClassImpl<T>.Data this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KClassImpl$Data$allStaticMembers$2(KClassImpl<T>.Data data) {
        super(0);
        this.this$0 = data;
    }

    public final List<KCallableImpl<?>> invoke() {
        return CollectionsKt.plus(this.this$0.getDeclaredStaticMembers(), this.this$0.getInheritedStaticMembers());
    }
}
