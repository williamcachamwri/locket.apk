package org.koin.core.scope;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.parameter.ParametersHolder;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lorg/koin/core/parameter/ParametersHolder;", "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Scope.kt */
final class Scope$resolveInstance$3 extends Lambda implements Function0<ParametersHolder> {
    final /* synthetic */ Scope this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Scope$resolveInstance$3(Scope scope) {
        super(0);
        this.this$0 = scope;
    }

    public final ParametersHolder invoke() {
        return this.this$0.get_parameterStack().removeFirstOrNull();
    }
}
