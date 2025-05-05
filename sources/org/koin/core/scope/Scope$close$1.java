package org.koin.core.scope;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Scope.kt */
final class Scope$close$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Scope this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Scope$close$1(Scope scope) {
        super(0);
        this.this$0 = scope;
    }

    public final void invoke() {
        Logger logger = this.this$0.get_koin().getLogger();
        String str = "|- (-) Scope - id:'" + this.this$0.getId() + '\'';
        Level level = Level.DEBUG;
        if (logger.isAt(level)) {
            logger.display(level, str);
        }
        Scope scope = this.this$0;
        for (ScopeCallback onScopeClose : this.this$0._callbacks) {
            onScopeClose.onScopeClose(scope);
        }
        this.this$0._callbacks.clear();
        this.this$0.set_source((Object) null);
        this.this$0._closed = true;
        this.this$0.get_koin().getScopeRegistry().deleteScope$koin_core(this.this$0);
    }
}
