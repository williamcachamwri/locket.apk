package com.squareup.kotlinpoet;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¢\u0006\u0002\b\u0003\u001a\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006¨\u0006\u0007"}, d2 = {"asParameterizedTypeName", "Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "Ljava/lang/reflect/ParameterizedType;", "get", "asTypeName", "Lcom/squareup/kotlinpoet/TypeName;", "Lkotlin/reflect/KType;", "kotlinpoet"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: ParameterizedTypeName.kt */
public final class ParameterizedTypeNames {
    public static final ParameterizedTypeName get(ParameterizedType parameterizedType) {
        Intrinsics.checkNotNullParameter(parameterizedType, "<this>");
        return ParameterizedTypeName.Companion.get$kotlinpoet(parameterizedType, new LinkedHashMap());
    }

    public static final TypeName asTypeName(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        KClassifier classifier = kType.getClassifier();
        if (classifier instanceof KTypeParameter) {
            TypeVariableName asTypeVariableName = TypeVariableNames.asTypeVariableName((KTypeParameter) classifier);
            return kType.isMarkedNullable() ? TypeName.copy$default(asTypeVariableName, true, (List) null, 2, (Object) null) : asTypeVariableName;
        } else if (classifier != null && (classifier instanceof KClass)) {
            return ParameterizedTypeName.Companion.get$kotlinpoet((KClass) classifier, kType.isMarkedNullable(), kType.getArguments());
        } else {
            throw new IllegalArgumentException("Cannot build TypeName for " + kType);
        }
    }
}
