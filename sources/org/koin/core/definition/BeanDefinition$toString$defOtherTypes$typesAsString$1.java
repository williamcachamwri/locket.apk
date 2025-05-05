package org.koin.core.definition;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import org.koin.ext.KClassExtKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "it", "Lkotlin/reflect/KClass;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeanDefinition.kt */
final class BeanDefinition$toString$defOtherTypes$typesAsString$1 extends Lambda implements Function1<KClass<?>, CharSequence> {
    public static final BeanDefinition$toString$defOtherTypes$typesAsString$1 INSTANCE = new BeanDefinition$toString$defOtherTypes$typesAsString$1();

    BeanDefinition$toString$defOtherTypes$typesAsString$1() {
        super(1);
    }

    public final CharSequence invoke(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "it");
        return KClassExtKt.getFullName(kClass);
    }
}
