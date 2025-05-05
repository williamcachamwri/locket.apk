package com.squareup.kotlinpoet.ksp;

import com.squareup.kotlinpoet.TypeVariableName;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/squareup/kotlinpoet/TypeVariableName;", "id", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeParameterResolver.kt */
final class TypeParameterResolverKt$toTypeParameterResolver$typeParamResolver$1 extends Lambda implements Function1<String, TypeVariableName> {
    final /* synthetic */ LinkedHashMap<String, TypeVariableName> $parametersMap;
    final /* synthetic */ TypeParameterResolver $parent;
    final /* synthetic */ String $sourceTypeHint;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TypeParameterResolverKt$toTypeParameterResolver$typeParamResolver$1(LinkedHashMap<String, TypeVariableName> linkedHashMap, TypeParameterResolver typeParameterResolver, String str) {
        super(1);
        this.$parametersMap = linkedHashMap;
        this.$parent = typeParameterResolver;
        this.$sourceTypeHint = str;
    }

    public final TypeVariableName invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
        TypeVariableName typeVariableName = this.$parametersMap.get(str);
        if (typeVariableName == null) {
            TypeParameterResolver typeParameterResolver = this.$parent;
            typeVariableName = typeParameterResolver != null ? typeParameterResolver.get(str) : null;
            if (typeVariableName == null) {
                throw new IllegalStateException("No type argument found for " + str + "! Analyzed " + this.$sourceTypeHint + " with known parameters " + this.$parametersMap.keySet());
            }
        }
        return typeVariableName;
    }
}
