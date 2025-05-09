package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

/* compiled from: IntersectionType.kt */
public final class IntersectionTypeKt {
    public static final UnwrappedType intersectTypes(List<? extends UnwrappedType> list) {
        SimpleType simpleType;
        Intrinsics.checkNotNullParameter(list, "types");
        int size = list.size();
        if (size == 0) {
            throw new IllegalStateException("Expected some types".toString());
        } else if (size == 1) {
            return (UnwrappedType) CollectionsKt.single(list);
        } else {
            Iterable<UnwrappedType> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            boolean z = false;
            boolean z2 = false;
            for (UnwrappedType unwrappedType : iterable) {
                z = z || KotlinTypeKt.isError(unwrappedType);
                if (unwrappedType instanceof SimpleType) {
                    simpleType = (SimpleType) unwrappedType;
                } else if (!(unwrappedType instanceof FlexibleType)) {
                    throw new NoWhenBranchMatchedException();
                } else if (DynamicTypesKt.isDynamic(unwrappedType)) {
                    return unwrappedType;
                } else {
                    simpleType = ((FlexibleType) unwrappedType).getLowerBound();
                    z2 = true;
                }
                arrayList.add(simpleType);
            }
            List list2 = (List) arrayList;
            if (z) {
                return ErrorUtils.createErrorType(ErrorTypeKind.INTERSECTION_OF_ERROR_TYPES, list.toString());
            }
            if (!z2) {
                return TypeIntersector.INSTANCE.intersectTypes$descriptors(list2);
            }
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (UnwrappedType upperIfFlexible : iterable) {
                arrayList2.add(FlexibleTypesKt.upperIfFlexible(upperIfFlexible));
            }
            return KotlinTypeFactory.flexibleType(TypeIntersector.INSTANCE.intersectTypes$descriptors(list2), TypeIntersector.INSTANCE.intersectTypes$descriptors((List) arrayList2));
        }
    }
}
