package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.calls.Caller;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0006\b\u0001\u0010\u0002 \u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/calls/Caller;", "V", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KPropertyImpl.kt */
final class KPropertyImpl$Setter$caller$2 extends Lambda implements Function0<Caller<?>> {
    final /* synthetic */ KPropertyImpl.Setter<V> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KPropertyImpl$Setter$caller$2(KPropertyImpl.Setter<V> setter) {
        super(0);
        this.this$0 = setter;
    }

    public final Caller<?> invoke() {
        return KPropertyImplKt.computeCallerForAccessor(this.this$0, false);
    }
}
