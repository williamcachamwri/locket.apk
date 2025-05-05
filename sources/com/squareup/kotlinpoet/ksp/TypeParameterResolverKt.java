package com.squareup.kotlinpoet.ksp;

import androidx.camera.core.CameraInfo;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.squareup.kotlinpoet.KModifier;
import com.squareup.kotlinpoet.TypeVariableName;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a&\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"toTypeParameterResolver", "Lcom/squareup/kotlinpoet/ksp/TypeParameterResolver;", "", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "parent", "sourceTypeHint", "", "ksp"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeParameterResolver.kt */
public final class TypeParameterResolverKt {
    public static /* synthetic */ TypeParameterResolver toTypeParameterResolver$default(List list, TypeParameterResolver typeParameterResolver, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            typeParameterResolver = null;
        }
        if ((i & 2) != 0) {
            str = CameraInfo.IMPLEMENTATION_TYPE_UNKNOWN;
        }
        return toTypeParameterResolver(list, typeParameterResolver, str);
    }

    public static final TypeParameterResolver toTypeParameterResolver(List<? extends KSTypeParameter> list, TypeParameterResolver typeParameterResolver, String str) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(str, "sourceTypeHint");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        TypeParameterResolverKt$toTypeParameterResolver$resolver$1 typeParameterResolverKt$toTypeParameterResolver$resolver$1 = new TypeParameterResolverKt$toTypeParameterResolver$resolver$1(linkedHashMap, new TypeParameterResolverKt$toTypeParameterResolver$typeParamResolver$1(linkedHashMap, typeParameterResolver, str));
        for (KSTypeParameter name : list) {
            String shortName = name.getName().getShortName();
            linkedHashMap.put(shortName, TypeVariableName.Companion.get$default(TypeVariableName.Companion, shortName, (KModifier) null, 2, (Object) null));
        }
        for (KSTypeParameter kSTypeParameter : list) {
            linkedHashMap.put(kSTypeParameter.getName().getShortName(), KsTypesKt.toTypeVariableName(kSTypeParameter, typeParameterResolverKt$toTypeParameterResolver$resolver$1));
        }
        return typeParameterResolverKt$toTypeParameterResolver$resolver$1;
    }
}
