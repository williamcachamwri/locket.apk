package org.koin.java;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;

@Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0004\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "T", "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: KoinJavaComponent.kt */
final class KoinJavaComponent$inject$1 extends Lambda implements Function0<T> {
    final /* synthetic */ Class<?> $clazz;
    final /* synthetic */ Function0<ParametersHolder> $parameters;
    final /* synthetic */ Qualifier $qualifier;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KoinJavaComponent$inject$1(Class<?> cls, Qualifier qualifier, Function0<? extends ParametersHolder> function0) {
        super(0);
        this.$clazz = cls;
        this.$qualifier = qualifier;
        this.$parameters = function0;
    }

    public final T invoke() {
        return KoinJavaComponent.get(this.$clazz, this.$qualifier, this.$parameters);
    }
}
