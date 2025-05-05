package org.koin.core.instance;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.parameter.ParametersHolder;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/koin/core/parameter/ParametersHolder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: InstanceBuilder.kt */
final class InstanceBuilderKt$getArguments$1 extends Lambda implements Function0<ParametersHolder> {
    final /* synthetic */ ParametersHolder $parameters;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InstanceBuilderKt$getArguments$1(ParametersHolder parametersHolder) {
        super(0);
        this.$parameters = parametersHolder;
    }

    public final ParametersHolder invoke() {
        return this.$parameters;
    }
}
