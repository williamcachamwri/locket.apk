package org.koin.core.registry;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;

@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "T", "Lorg/koin/core/scope/Scope;", "it", "Lorg/koin/core/parameter/ParametersHolder;", "invoke", "(Lorg/koin/core/scope/Scope;Lorg/koin/core/parameter/ParametersHolder;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 176)
/* compiled from: InstanceRegistry.kt */
public final class InstanceRegistry$declareScopedInstance$def$1 extends Lambda implements Function2<Scope, ParametersHolder, T> {
    final /* synthetic */ T $instance;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InstanceRegistry$declareScopedInstance$def$1(T t) {
        super(2);
        this.$instance = t;
    }

    public final T invoke(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$_createDefinition");
        Intrinsics.checkNotNullParameter(parametersHolder, "it");
        return this.$instance;
    }
}
