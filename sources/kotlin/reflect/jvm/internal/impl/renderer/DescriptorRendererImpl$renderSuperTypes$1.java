package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* compiled from: DescriptorRendererImpl.kt */
final class DescriptorRendererImpl$renderSuperTypes$1 extends Lambda implements Function1<KotlinType, CharSequence> {
    final /* synthetic */ DescriptorRendererImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DescriptorRendererImpl$renderSuperTypes$1(DescriptorRendererImpl descriptorRendererImpl) {
        super(1);
        this.this$0 = descriptorRendererImpl;
    }

    public final CharSequence invoke(KotlinType kotlinType) {
        DescriptorRendererImpl descriptorRendererImpl = this.this$0;
        Intrinsics.checkNotNull(kotlinType);
        return descriptorRendererImpl.renderType(kotlinType);
    }
}
