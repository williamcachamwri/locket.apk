package expo.modules.kotlin.views;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "R", "T", "Landroid/view/View;", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: ViewDefinitionBuilder.kt */
public final class ViewDefinitionBuilder$AsyncFunction$3 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ Function0<R> $body;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewDefinitionBuilder$AsyncFunction$3(Function0<? extends R> function0) {
        super(1);
        this.$body = function0;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        return this.$body.invoke();
    }
}
