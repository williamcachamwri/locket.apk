package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.StubTypeForBuilderInference;

/* compiled from: DescriptorRendererImpl.kt */
final class DescriptorRendererImpl$renderTypeConstructor$1 extends Lambda implements Function1<KotlinType, Object> {
    public static final DescriptorRendererImpl$renderTypeConstructor$1 INSTANCE = new DescriptorRendererImpl$renderTypeConstructor$1();

    DescriptorRendererImpl$renderTypeConstructor$1() {
        super(1);
    }

    public final Object invoke(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, "it");
        return kotlinType instanceof StubTypeForBuilderInference ? ((StubTypeForBuilderInference) kotlinType).getOriginalTypeVariable() : kotlinType;
    }
}
