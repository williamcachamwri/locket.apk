package kotlin.reflect.jvm.internal.impl.load.java;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;

public final class JvmAnnotationNames {
    public static final Name DEFAULT_ANNOTATION_MEMBER_NAME = Name.identifier("value");
    public static final FqName DEPRECATED_ANNOTATION = new FqName(Deprecated.class.getName());
    public static final FqName DOCUMENTED_ANNOTATION = new FqName(Documented.class.getName());
    public static final FqName ELEMENT_TYPE_ENUM = new FqName(ElementType.class.getName());
    public static final FqName ENHANCED_MUTABILITY_ANNOTATION = new FqName("kotlin.jvm.internal.EnhancedMutability");
    public static final FqName ENHANCED_NULLABILITY_ANNOTATION = new FqName("kotlin.jvm.internal.EnhancedNullability");
    public static final FqName JETBRAINS_MUTABLE_ANNOTATION = new FqName("org.jetbrains.annotations.Mutable");
    public static final FqName JETBRAINS_NOT_NULL_ANNOTATION = new FqName("org.jetbrains.annotations.NotNull");
    public static final FqName JETBRAINS_NULLABLE_ANNOTATION = new FqName("org.jetbrains.annotations.Nullable");
    public static final FqName JETBRAINS_READONLY_ANNOTATION = new FqName("org.jetbrains.annotations.ReadOnly");
    public static final FqName KOTLIN_JVM_INTERNAL = new FqName("kotlin.jvm.internal");
    public static final String METADATA_DESC;
    public static final FqName METADATA_FQ_NAME;
    public static final FqName MUTABLE_ANNOTATION = new FqName("kotlin.annotations.jvm.Mutable");
    public static final FqName OVERRIDE_ANNOTATION = new FqName(Override.class.getName());
    public static final FqName PURELY_IMPLEMENTS_ANNOTATION = new FqName("kotlin.jvm.PurelyImplements");
    public static final FqName READONLY_ANNOTATION = new FqName("kotlin.annotations.jvm.ReadOnly");
    public static final FqName REPEATABLE_ANNOTATION = new FqName("java.lang.annotation.Repeatable");
    public static final FqName RETENTION_ANNOTATION = new FqName(Retention.class.getName());
    public static final FqName RETENTION_POLICY_ENUM = new FqName(RetentionPolicy.class.getName());
    public static final String SERIALIZED_IR_DESC;
    public static final FqName SERIALIZED_IR_FQ_NAME;
    public static final FqName TARGET_ANNOTATION = new FqName(Target.class.getName());

    static {
        FqName fqName = new FqName("kotlin.Metadata");
        METADATA_FQ_NAME = fqName;
        METADATA_DESC = "L" + JvmClassName.byFqNameWithoutInnerClasses(fqName).getInternalName() + ";";
        FqName fqName2 = new FqName("kotlin.jvm.internal.SerializedIr");
        SERIALIZED_IR_FQ_NAME = fqName2;
        SERIALIZED_IR_DESC = "L" + JvmClassName.byFqNameWithoutInnerClasses(fqName2).getInternalName() + ";";
    }
}
