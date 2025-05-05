package org.koin.core.scope;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Scope.kt */
final class Scope$resolveValue$4$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Scope $this_run;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Scope$resolveValue$4$1(Scope scope) {
        super(0);
        this.$this_run = scope;
    }

    public final void invoke() {
        this.$this_run.get_parameterStack().clear();
    }
}
