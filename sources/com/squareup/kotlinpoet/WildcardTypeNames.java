package com.squareup.kotlinpoet;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.WildcardType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¢\u0006\u0002\b\u0003\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0007¢\u0006\u0002\b\u0003¨\u0006\u0005"}, d2 = {"asWildcardTypeName", "Lcom/squareup/kotlinpoet/TypeName;", "Ljava/lang/reflect/WildcardType;", "get", "Ljavax/lang/model/type/WildcardType;", "kotlinpoet"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: WildcardTypeName.kt */
public final class WildcardTypeNames {
    public static final TypeName get(WildcardType wildcardType) {
        Intrinsics.checkNotNullParameter(wildcardType, "<this>");
        return WildcardTypeName.Companion.get$kotlinpoet(wildcardType, (Map<TypeParameterElement, TypeVariableName>) new LinkedHashMap());
    }

    public static final TypeName get(java.lang.reflect.WildcardType wildcardType) {
        Intrinsics.checkNotNullParameter(wildcardType, "<this>");
        return WildcardTypeName.Companion.get$kotlinpoet(wildcardType, (Map<Type, TypeVariableName>) new LinkedHashMap());
    }
}
