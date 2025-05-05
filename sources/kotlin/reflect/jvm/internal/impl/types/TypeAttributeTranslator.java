package kotlin.reflect.jvm.internal.impl.types;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

/* compiled from: TypeAttributeTranslator.kt */
public interface TypeAttributeTranslator {
    TypeAttributes toAttributes(Annotations annotations, TypeConstructor typeConstructor, DeclarationDescriptor declarationDescriptor);

    /* compiled from: TypeAttributeTranslator.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ TypeAttributes toAttributes$default(TypeAttributeTranslator typeAttributeTranslator, Annotations annotations, TypeConstructor typeConstructor, DeclarationDescriptor declarationDescriptor, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    typeConstructor = null;
                }
                if ((i & 4) != 0) {
                    declarationDescriptor = null;
                }
                return typeAttributeTranslator.toAttributes(annotations, typeConstructor, declarationDescriptor);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toAttributes");
        }
    }
}
