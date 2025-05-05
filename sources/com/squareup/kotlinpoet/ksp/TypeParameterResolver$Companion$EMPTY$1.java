package com.squareup.kotlinpoet.ksp;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.kotlinpoet.TypeVariableName;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0011\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0004H\u0002R \u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"com/squareup/kotlinpoet/ksp/TypeParameterResolver$Companion$EMPTY$1", "Lcom/squareup/kotlinpoet/ksp/TypeParameterResolver;", "parametersMap", "", "", "Lcom/squareup/kotlinpoet/TypeVariableName;", "getParametersMap", "()Ljava/util/Map;", "get", "index", "ksp"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeParameterResolver.kt */
public final class TypeParameterResolver$Companion$EMPTY$1 implements TypeParameterResolver {
    private final Map<String, TypeVariableName> parametersMap = MapsKt.emptyMap();

    TypeParameterResolver$Companion$EMPTY$1() {
    }

    public Map<String, TypeVariableName> getParametersMap() {
        return this.parametersMap;
    }

    public TypeVariableName get(String str) {
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.INDEX);
        throw new NoSuchElementException("No TypeParameter found for index " + str);
    }
}
