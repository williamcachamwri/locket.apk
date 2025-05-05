package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.TypeVariableName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVariance;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¢\u0006\u0002\b\u0003\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0007¢\u0006\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0005¨\u0006\u0006"}, d2 = {"asTypeVariableName", "Lcom/squareup/kotlinpoet/TypeVariableName;", "Ljavax/lang/model/element/TypeParameterElement;", "get", "Ljavax/lang/model/type/TypeVariable;", "Lkotlin/reflect/KTypeParameter;", "kotlinpoet"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeVariableName.kt */
public final class TypeVariableNames {

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* compiled from: TypeVariableName.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KVariance.values().length];
            iArr[KVariance.INVARIANT.ordinal()] = 1;
            iArr[KVariance.IN.ordinal()] = 2;
            iArr[KVariance.OUT.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final TypeVariableName get(TypeVariable typeVariable) {
        Intrinsics.checkNotNullParameter(typeVariable, "<this>");
        TypeParameterElement asElement = typeVariable.asElement();
        Intrinsics.checkNotNull(asElement, "null cannot be cast to non-null type javax.lang.model.element.TypeParameterElement");
        return get(asElement);
    }

    public static final TypeVariableName get(TypeParameterElement typeParameterElement) {
        Intrinsics.checkNotNullParameter(typeParameterElement, "<this>");
        String obj = typeParameterElement.getSimpleName().toString();
        List bounds = typeParameterElement.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "bounds");
        Iterable<TypeMirror> iterable = bounds;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (TypeMirror typeMirror : iterable) {
            arrayList.add(TypeNames.get(typeMirror));
        }
        Collection collection = (List) arrayList;
        TypeVariableName.Companion companion = TypeVariableName.Companion;
        if (collection.isEmpty()) {
            collection = companion.getNULLABLE_ANY_LIST$kotlinpoet();
        }
        return TypeVariableName.Companion.of$kotlinpoet(obj, (List) collection, (KModifier) null);
    }

    public static final TypeVariableName asTypeVariableName(KTypeParameter kTypeParameter) {
        KModifier kModifier;
        Intrinsics.checkNotNullParameter(kTypeParameter, "<this>");
        TypeVariableName.Companion companion = TypeVariableName.Companion;
        String name = kTypeParameter.getName();
        Iterable<KType> upperBounds = kTypeParameter.getUpperBounds();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(upperBounds, 10));
        for (KType asTypeName : upperBounds) {
            arrayList.add(ParameterizedTypeNames.asTypeName(asTypeName));
        }
        Collection collection = (List) arrayList;
        TypeVariableName.Companion companion2 = TypeVariableName.Companion;
        if (collection.isEmpty()) {
            collection = companion2.getNULLABLE_ANY_LIST$kotlinpoet();
        }
        List list = (List) collection;
        int i = WhenMappings.$EnumSwitchMapping$0[kTypeParameter.getVariance().ordinal()];
        if (i == 1) {
            kModifier = null;
        } else if (i == 2) {
            kModifier = KModifier.IN;
        } else if (i == 3) {
            kModifier = KModifier.OUT;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return companion.of$kotlinpoet(name, list, kModifier);
    }
}
