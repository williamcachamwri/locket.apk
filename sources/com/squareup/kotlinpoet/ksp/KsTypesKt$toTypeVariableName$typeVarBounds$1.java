package com.squareup.kotlinpoet.ksp;

import com.google.devtools.ksp.symbol.KSTypeReference;
import com.squareup.kotlinpoet.TypeName;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/squareup/kotlinpoet/TypeName;", "it", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: ksTypes.kt */
final class KsTypesKt$toTypeVariableName$typeVarBounds$1 extends Lambda implements Function1<KSTypeReference, TypeName> {
    final /* synthetic */ TypeParameterResolver $typeParamResolver;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KsTypesKt$toTypeVariableName$typeVarBounds$1(TypeParameterResolver typeParameterResolver) {
        super(1);
        this.$typeParamResolver = typeParameterResolver;
    }

    public final TypeName invoke(KSTypeReference kSTypeReference) {
        Intrinsics.checkNotNullParameter(kSTypeReference, "it");
        return KsTypesKt.toTypeName(kSTypeReference, this.$typeParamResolver);
    }
}
