package org.koin.core.scope;

import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;

@Metadata(d1 = {"\u0000*\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aK\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\u0004\u0018\u0001`\u000bH\u0007¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"get", "T", "", "Lorg/koin/core/scope/Scope;", "clazz", "Ljava/lang/Class;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lorg/koin/core/scope/Scope;Ljava/lang/Class;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "koin-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ScopeJVM.kt */
public final class ScopeJVMKt {
    public static final <T> T get(Scope scope, Class<?> cls) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(cls, "clazz");
        return get$default(scope, cls, (Qualifier) null, (Function0) null, 6, (Object) null);
    }

    public static final <T> T get(Scope scope, Class<?> cls, Qualifier qualifier) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(cls, "clazz");
        return get$default(scope, cls, qualifier, (Function0) null, 4, (Object) null);
    }

    public static /* synthetic */ Object get$default(Scope scope, Class cls, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return get(scope, cls, qualifier, function0);
    }

    public static final <T> T get(Scope scope, Class<?> cls, Qualifier qualifier, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(cls, "clazz");
        return scope.get(JvmClassMappingKt.getKotlinClass(cls), qualifier, function0);
    }
}
