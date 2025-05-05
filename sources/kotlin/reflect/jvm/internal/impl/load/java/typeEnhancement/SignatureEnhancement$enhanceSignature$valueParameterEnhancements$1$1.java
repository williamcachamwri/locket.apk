package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* compiled from: signatureEnhancement.kt */
final class SignatureEnhancement$enhanceSignature$valueParameterEnhancements$1$1 extends Lambda implements Function1<CallableMemberDescriptor, KotlinType> {
    final /* synthetic */ ValueParameterDescriptor $p;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SignatureEnhancement$enhanceSignature$valueParameterEnhancements$1$1(ValueParameterDescriptor valueParameterDescriptor) {
        super(1);
        this.$p = valueParameterDescriptor;
    }

    public final KotlinType invoke(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "it");
        KotlinType type = callableMemberDescriptor.getValueParameters().get(this.$p.getIndex()).getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        return type;
    }
}
