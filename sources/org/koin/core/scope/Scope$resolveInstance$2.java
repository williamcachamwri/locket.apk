package org.koin.core.scope;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.parameter.ParametersHolder;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Scope.kt */
final class Scope$resolveInstance$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ ParametersHolder $parameters;
    final /* synthetic */ Scope this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Scope$resolveInstance$2(Scope scope, ParametersHolder parametersHolder) {
        super(0);
        this.this$0 = scope;
        this.$parameters = parametersHolder;
    }

    public final void invoke() {
        this.this$0.get_parameterStack().addFirst(this.$parameters);
    }
}
