package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KPackageImpl;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00060\u0001R\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/KPackageImpl$Data;", "Lkotlin/reflect/jvm/internal/KPackageImpl;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KPackageImpl.kt */
final class KPackageImpl$data$1 extends Lambda implements Function0<KPackageImpl.Data> {
    final /* synthetic */ KPackageImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KPackageImpl$data$1(KPackageImpl kPackageImpl) {
        super(0);
        this.this$0 = kPackageImpl;
    }

    public final KPackageImpl.Data invoke() {
        return new KPackageImpl.Data();
    }
}
