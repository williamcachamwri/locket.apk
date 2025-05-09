package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* compiled from: CompanionObjectMapping.kt */
public final class CompanionObjectMapping {
    public static final CompanionObjectMapping INSTANCE = new CompanionObjectMapping();
    private static final Set<ClassId> classIds;

    private CompanionObjectMapping() {
    }

    public final Set<ClassId> getClassIds() {
        return classIds;
    }

    static {
        Iterable<PrimitiveType> iterable = PrimitiveType.NUMBER_TYPES;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (PrimitiveType primitiveFqName : iterable) {
            arrayList.add(StandardNames.getPrimitiveFqName(primitiveFqName));
        }
        FqName safe = StandardNames.FqNames.string.toSafe();
        Intrinsics.checkNotNullExpressionValue(safe, "toSafe(...)");
        FqName safe2 = StandardNames.FqNames._boolean.toSafe();
        Intrinsics.checkNotNullExpressionValue(safe2, "toSafe(...)");
        FqName safe3 = StandardNames.FqNames._enum.toSafe();
        Intrinsics.checkNotNullExpressionValue(safe3, "toSafe(...)");
        Collection linkedHashSet = new LinkedHashSet();
        for (FqName fqName : CollectionsKt.plus(CollectionsKt.plus(CollectionsKt.plus((List) arrayList, safe), safe2), safe3)) {
            linkedHashSet.add(ClassId.topLevel(fqName));
        }
        classIds = (Set) linkedHashSet;
    }

    public final Set<ClassId> allClassesWithIntrinsicCompanions() {
        return classIds;
    }
}
