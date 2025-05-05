package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: AbstractTypeAliasDescriptor.kt */
public final class AbstractTypeAliasDescriptor$typeConstructor$1 implements TypeConstructor {
    final /* synthetic */ AbstractTypeAliasDescriptor this$0;

    public boolean isDenotable() {
        return true;
    }

    AbstractTypeAliasDescriptor$typeConstructor$1(AbstractTypeAliasDescriptor abstractTypeAliasDescriptor) {
        this.this$0 = abstractTypeAliasDescriptor;
    }

    public TypeAliasDescriptor getDeclarationDescriptor() {
        return this.this$0;
    }

    public List<TypeParameterDescriptor> getParameters() {
        return this.this$0.getTypeConstructorTypeParameters();
    }

    public Collection<KotlinType> getSupertypes() {
        Collection<KotlinType> supertypes = getDeclarationDescriptor().getUnderlyingType().getConstructor().getSupertypes();
        Intrinsics.checkNotNullExpressionValue(supertypes, "getSupertypes(...)");
        return supertypes;
    }

    public KotlinBuiltIns getBuiltIns() {
        return DescriptorUtilsKt.getBuiltIns(getDeclarationDescriptor());
    }

    public String toString() {
        return "[typealias " + getDeclarationDescriptor().getName().asString() + AbstractJsonLexerKt.END_LIST;
    }

    public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return this;
    }
}
