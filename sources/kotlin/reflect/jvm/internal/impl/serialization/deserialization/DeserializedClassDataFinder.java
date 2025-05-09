package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* compiled from: DeserializedClassDataFinder.kt */
public final class DeserializedClassDataFinder implements ClassDataFinder {
    private final PackageFragmentProvider packageFragmentProvider;

    public DeserializedClassDataFinder(PackageFragmentProvider packageFragmentProvider2) {
        Intrinsics.checkNotNullParameter(packageFragmentProvider2, "packageFragmentProvider");
        this.packageFragmentProvider = packageFragmentProvider2;
    }

    public ClassData findClassData(ClassId classId) {
        ClassData findClassData;
        Intrinsics.checkNotNullParameter(classId, "classId");
        PackageFragmentProvider packageFragmentProvider2 = this.packageFragmentProvider;
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName, "getPackageFqName(...)");
        for (PackageFragmentDescriptor next : PackageFragmentProviderKt.packageFragments(packageFragmentProvider2, packageFqName)) {
            if ((next instanceof DeserializedPackageFragment) && (findClassData = ((DeserializedPackageFragment) next).getClassDataFinder().findClassData(classId)) != null) {
                return findClassData;
            }
        }
        return null;
    }
}
