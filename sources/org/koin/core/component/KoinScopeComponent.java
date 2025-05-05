package org.koin.core.component;

import kotlin.Deprecated;
import kotlin.Metadata;
import org.koin.core.Koin;
import org.koin.core.component.KoinComponent;
import org.koin.core.scope.Scope;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H\u0017R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Lorg/koin/core/component/KoinScopeComponent;", "Lorg/koin/core/component/KoinComponent;", "scope", "Lorg/koin/core/scope/Scope;", "getScope", "()Lorg/koin/core/scope/Scope;", "closeScope", "", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KoinScopeComponent.kt */
public interface KoinScopeComponent extends KoinComponent {
    @Deprecated(message = "not used internaly anymore")
    void closeScope();

    Scope getScope();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: KoinScopeComponent.kt */
    public static final class DefaultImpls {
        public static Koin getKoin(KoinScopeComponent koinScopeComponent) {
            return KoinComponent.DefaultImpls.getKoin(koinScopeComponent);
        }

        @Deprecated(message = "not used internaly anymore")
        public static void closeScope(KoinScopeComponent koinScopeComponent) {
            if (koinScopeComponent.getScope().isNotClosed()) {
                koinScopeComponent.getScope().close();
            }
        }
    }
}
