package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder;
import kotlin.reflect.jvm.internal.impl.utils.DeserializationHelpersKt;

/* compiled from: JavaClassDataFinder.kt */
public final class JavaClassDataFinder implements ClassDataFinder {
    private final DeserializedDescriptorResolver deserializedDescriptorResolver;
    private final KotlinClassFinder kotlinClassFinder;

    public JavaClassDataFinder(KotlinClassFinder kotlinClassFinder2, DeserializedDescriptorResolver deserializedDescriptorResolver2) {
        Intrinsics.checkNotNullParameter(kotlinClassFinder2, "kotlinClassFinder");
        Intrinsics.checkNotNullParameter(deserializedDescriptorResolver2, "deserializedDescriptorResolver");
        this.kotlinClassFinder = kotlinClassFinder2;
        this.deserializedDescriptorResolver = deserializedDescriptorResolver2;
    }

    public ClassData findClassData(ClassId classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        KotlinJvmBinaryClass findKotlinClass = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId, DeserializationHelpersKt.jvmMetadataVersionOrDefault(this.deserializedDescriptorResolver.getComponents().getConfiguration()));
        if (findKotlinClass == null) {
            return null;
        }
        Intrinsics.areEqual((Object) findKotlinClass.getClassId(), (Object) classId);
        return this.deserializedDescriptorResolver.readClassData$descriptors_jvm(findKotlinClass);
    }
}
