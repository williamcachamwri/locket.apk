package org.koin.core.component;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.scope.Scope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lorg/koin/core/scope/Scope;", "T", "Lorg/koin/core/component/KoinScopeComponent;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: KoinScopeComponent.kt */
final class KoinScopeComponentKt$getOrCreateScope$1 extends Lambda implements Function0<Scope> {
    final /* synthetic */ T $this_getOrCreateScope;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KoinScopeComponentKt$getOrCreateScope$1(T t) {
        super(0);
        this.$this_getOrCreateScope = t;
    }

    public final Scope invoke() {
        Scope scopeOrNull = KoinScopeComponentKt.getScopeOrNull(this.$this_getOrCreateScope);
        return scopeOrNull == null ? KoinScopeComponentKt.createScope$default(this.$this_getOrCreateScope, (Object) null, 1, (Object) null) : scopeOrNull;
    }
}
