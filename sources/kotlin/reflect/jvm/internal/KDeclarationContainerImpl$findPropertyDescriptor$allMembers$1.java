package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KDeclarationContainerImpl.kt */
final class KDeclarationContainerImpl$findPropertyDescriptor$allMembers$1 extends Lambda implements Function1<PropertyDescriptor, CharSequence> {
    public static final KDeclarationContainerImpl$findPropertyDescriptor$allMembers$1 INSTANCE = new KDeclarationContainerImpl$findPropertyDescriptor$allMembers$1();

    KDeclarationContainerImpl$findPropertyDescriptor$allMembers$1() {
        super(1);
    }

    public final CharSequence invoke(PropertyDescriptor propertyDescriptor) {
        Intrinsics.checkNotNullParameter(propertyDescriptor, "descriptor");
        return DescriptorRenderer.DEBUG_TEXT.render(propertyDescriptor) + " | " + RuntimeTypeMapper.INSTANCE.mapPropertySignature(propertyDescriptor).asString();
    }
}
