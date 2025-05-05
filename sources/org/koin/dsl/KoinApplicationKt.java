package org.koin.dsl;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.koin.core.KoinApplication;
import org.koin.core.module.KoinApplicationDslMarker;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010\u0000\u001a\u00020\u00012!\b\u0002\u0010\u0002\u001a\u001b\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u0005¢\u0006\u0002\b\u0006H\u0007*,\u0010\u0007\"\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u00062\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0006¨\u0006\b"}, d2 = {"koinApplication", "Lorg/koin/core/KoinApplication;", "appDeclaration", "Lkotlin/Function1;", "", "Lorg/koin/dsl/KoinAppDeclaration;", "Lkotlin/ExtensionFunctionType;", "KoinAppDeclaration", "koin-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: KoinApplication.kt */
public final class KoinApplicationKt {
    public static /* synthetic */ KoinApplication koinApplication$default(Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        return koinApplication(function1);
    }

    @KoinApplicationDslMarker
    public static final KoinApplication koinApplication(Function1<? super KoinApplication, Unit> function1) {
        KoinApplication init = KoinApplication.Companion.init();
        if (function1 != null) {
            function1.invoke(init);
        }
        init.createEagerInstances();
        return init;
    }
}
