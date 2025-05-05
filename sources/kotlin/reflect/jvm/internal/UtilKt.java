package kotlin.reflect.jvm.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference;
import kotlin.jvm.internal.RepeatableContainer;
import kotlin.reflect.KAnnotatedElement;
import kotlin.reflect.KType;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectAnnotationSource;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectJavaClassFinderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotation;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinarySourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000à\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\u001al\u0010\u0013\u001a\u0002H\u0014\"\b\b\u0000\u0010\u0015*\u00020\u0016\"\b\b\u0001\u0010\u0014*\u00020\u00062\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00182\u0006\u0010\u0019\u001a\u0002H\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u001d\u0010 \u001a\u0019\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u00140!¢\u0006\u0002\b#H\u0000¢\u0006\u0002\u0010$\u001a.\u0010%\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020,H\u0002\u001a(\u0010%\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010-\u001a\u00020.2\b\b\u0002\u0010+\u001a\u00020,H\u0002\u001a%\u0010/\u001a\u0002H0\"\u0004\b\u0000\u001002\f\u00101\u001a\b\u0012\u0004\u0012\u0002H002H\bø\u0001\u0000¢\u0006\u0002\u00103\u001a\u0016\u00104\u001a\u0004\u0018\u00010\u0010*\u0002052\u0006\u0010&\u001a\u00020'H\u0002\u001a\u0014\u00106\u001a\b\u0012\u0002\b\u0003\u0018\u000107*\u0004\u0018\u00010\u0010H\u0000\u001a\u0010\u00108\u001a\u0004\u0018\u000109*\u0004\u0018\u00010\u0010H\u0000\u001a\u0014\u0010:\u001a\b\u0012\u0002\b\u0003\u0018\u00010;*\u0004\u0018\u00010\u0010H\u0000\u001a\u0012\u0010<\u001a\b\u0012\u0004\u0012\u00020>0=*\u00020?H\u0000\u001a\u0014\u0010@\u001a\u0006\u0012\u0002\b\u00030\u0018*\u0006\u0012\u0002\b\u00030\u0018H\u0000\u001a\u001a\u0010A\u001a\u0004\u0018\u00010B*\u0006\u0012\u0002\b\u00030\u00182\u0006\u0010C\u001a\u00020)H\u0000\u001a;\u0010D\u001a\u0004\u0018\u00010E*\u0006\u0012\u0002\b\u00030\u00182\u0006\u0010C\u001a\u00020)2\u001a\u0010F\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00180G\"\u0006\u0012\u0002\b\u00030\u0018H\u0000¢\u0006\u0002\u0010H\u001a\u000e\u0010I\u001a\u0004\u0018\u00010>*\u00020JH\u0002\u001a\u0012\u0010K\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0018*\u00020LH\u0000\u001a\u000e\u0010M\u001a\u0004\u0018\u00010N*\u00020OH\u0000\u001a\u001a\u0010P\u001a\u0004\u0018\u00010\u0010*\u0006\u0012\u0002\b\u00030Q2\u0006\u0010&\u001a\u00020'H\u0002\u001a\u0018\u0010R\u001a\b\u0012\u0004\u0012\u00020>0=*\b\u0012\u0004\u0012\u00020>0=H\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00068@X\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0018\u0010\t\u001a\u00020\n*\u00020\u000b8@X\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\f\"\u0018\u0010\r\u001a\u00020\n*\u00020\u000b8@X\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f\u0002\u0007\n\u0005\b20\u0001¨\u0006S"}, d2 = {"JVM_STATIC", "Lkotlin/reflect/jvm/internal/impl/name/FqName;", "getJVM_STATIC", "()Lorg/jetbrains/kotlin/name/FqName;", "instanceReceiverParameter", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;", "getInstanceReceiverParameter", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)Lorg/jetbrains/kotlin/descriptors/ReceiverParameterDescriptor;", "isInlineClassType", "", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)Z", "needsMultiFieldValueClassFlattening", "getNeedsMultiFieldValueClassFlattening", "defaultPrimitiveValue", "", "type", "Ljava/lang/reflect/Type;", "deserializeToDescriptor", "D", "M", "Lkotlin/reflect/jvm/internal/impl/protobuf/MessageLite;", "moduleAnchor", "Ljava/lang/Class;", "proto", "nameResolver", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolver;", "typeTable", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/TypeTable;", "metadataVersion", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/BinaryVersion;", "createDescriptor", "Lkotlin/Function2;", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/MemberDeserializer;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Class;Lorg/jetbrains/kotlin/protobuf/MessageLite;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "loadClass", "classLoader", "Ljava/lang/ClassLoader;", "packageName", "", "className", "arrayDimensions", "", "kotlinClassId", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "reflectionCall", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "arrayToRuntimeValue", "Lkotlin/reflect/jvm/internal/impl/resolve/constants/ArrayValue;", "asKCallableImpl", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "asKFunctionImpl", "Lkotlin/reflect/jvm/internal/KFunctionImpl;", "asKPropertyImpl", "Lkotlin/reflect/jvm/internal/KPropertyImpl;", "computeAnnotations", "", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/Annotated;", "createArrayType", "getDeclaredFieldOrNull", "Ljava/lang/reflect/Field;", "name", "getDeclaredMethodOrNull", "Ljava/lang/reflect/Method;", "parameterTypes", "", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", "toAnnotationInstance", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptor;", "toJavaClass", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "toKVisibility", "Lkotlin/reflect/KVisibility;", "Lkotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibility;", "toRuntimeValue", "Lkotlin/reflect/jvm/internal/impl/resolve/constants/ConstantValue;", "unwrapRepeatableAnnotations", "kotlin-reflection"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: util.kt */
public final class UtilKt {
    private static final FqName JVM_STATIC = new FqName("kotlin.jvm.JvmStatic");

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: util.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|19) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType[] r0 = kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType r1 = kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType r1 = kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType.CHAR     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType r1 = kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType.BYTE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType r1 = kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType.SHORT     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType r1 = kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType.INT     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType r1 = kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType.FLOAT     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType r1 = kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType.LONG     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType r1 = kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.UtilKt.WhenMappings.<clinit>():void");
        }
    }

    public static final FqName getJVM_STATIC() {
        return JVM_STATIC;
    }

    public static final Class<?> toJavaClass(ClassDescriptor classDescriptor) {
        Intrinsics.checkNotNullParameter(classDescriptor, "<this>");
        SourceElement source = classDescriptor.getSource();
        Intrinsics.checkNotNullExpressionValue(source, "getSource(...)");
        if (source instanceof KotlinJvmBinarySourceElement) {
            KotlinJvmBinaryClass binaryClass = ((KotlinJvmBinarySourceElement) source).getBinaryClass();
            Intrinsics.checkNotNull(binaryClass, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.components.ReflectKotlinClass");
            return ((ReflectKotlinClass) binaryClass).getKlass();
        } else if (source instanceof RuntimeSourceElementFactory.RuntimeSourceElement) {
            ReflectJavaElement javaElement = ((RuntimeSourceElementFactory.RuntimeSourceElement) source).getJavaElement();
            Intrinsics.checkNotNull(javaElement, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaClass");
            return ((ReflectJavaClass) javaElement).getElement();
        } else {
            ClassId classId = DescriptorUtilsKt.getClassId(classDescriptor);
            if (classId == null) {
                return null;
            }
            return loadClass(ReflectClassUtilKt.getSafeClassLoader(classDescriptor.getClass()), classId, 0);
        }
    }

    static /* synthetic */ Class loadClass$default(ClassLoader classLoader, ClassId classId, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return loadClass(classLoader, classId, i);
    }

    private static final Class<?> loadClass(ClassLoader classLoader, ClassId classId, int i) {
        JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
        FqNameUnsafe unsafe = classId.asSingleFqName().toUnsafe();
        Intrinsics.checkNotNullExpressionValue(unsafe, "toUnsafe(...)");
        ClassId mapKotlinToJava = javaToKotlinClassMap.mapKotlinToJava(unsafe);
        if (mapKotlinToJava != null) {
            classId = mapKotlinToJava;
        }
        String asString = classId.getPackageFqName().asString();
        Intrinsics.checkNotNullExpressionValue(asString, "asString(...)");
        String asString2 = classId.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(asString2, "asString(...)");
        return loadClass(classLoader, asString, asString2, i);
    }

    private static final Class<?> loadClass(ClassLoader classLoader, String str, String str2, int i) {
        if (Intrinsics.areEqual((Object) str, (Object) "kotlin")) {
            switch (str2.hashCode()) {
                case -901856463:
                    if (str2.equals("BooleanArray")) {
                        return boolean[].class;
                    }
                    break;
                case -763279523:
                    if (str2.equals("ShortArray")) {
                        return short[].class;
                    }
                    break;
                case -755911549:
                    if (str2.equals("CharArray")) {
                        return char[].class;
                    }
                    break;
                case -74930671:
                    if (str2.equals("ByteArray")) {
                        return byte[].class;
                    }
                    break;
                case 22374632:
                    if (str2.equals("DoubleArray")) {
                        return double[].class;
                    }
                    break;
                case 63537721:
                    if (str2.equals("Array")) {
                        return Object[].class;
                    }
                    break;
                case 601811914:
                    if (str2.equals("IntArray")) {
                        return int[].class;
                    }
                    break;
                case 948852093:
                    if (str2.equals("FloatArray")) {
                        return float[].class;
                    }
                    break;
                case 2104330525:
                    if (str2.equals("LongArray")) {
                        return long[].class;
                    }
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        if (i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                sb.append("[");
            }
            sb.append("L");
        }
        if (str.length() > 0) {
            z = true;
        }
        if (z) {
            sb.append(str + FilenameUtils.EXTENSION_SEPARATOR);
        }
        sb.append(StringsKt.replace$default(str2, (char) FilenameUtils.EXTENSION_SEPARATOR, (char) Typography.dollar, false, 4, (Object) null));
        if (i > 0) {
            sb.append(";");
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return ReflectJavaClassFinderKt.tryLoadClass(classLoader, sb2);
    }

    public static final Class<?> createArrayType(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        return Array.newInstance(cls, 0).getClass();
    }

    public static final KVisibility toKVisibility(DescriptorVisibility descriptorVisibility) {
        Intrinsics.checkNotNullParameter(descriptorVisibility, "<this>");
        if (Intrinsics.areEqual((Object) descriptorVisibility, (Object) DescriptorVisibilities.PUBLIC)) {
            return KVisibility.PUBLIC;
        }
        if (Intrinsics.areEqual((Object) descriptorVisibility, (Object) DescriptorVisibilities.PROTECTED)) {
            return KVisibility.PROTECTED;
        }
        if (Intrinsics.areEqual((Object) descriptorVisibility, (Object) DescriptorVisibilities.INTERNAL)) {
            return KVisibility.INTERNAL;
        }
        if (Intrinsics.areEqual((Object) descriptorVisibility, (Object) DescriptorVisibilities.PRIVATE) ? true : Intrinsics.areEqual((Object) descriptorVisibility, (Object) DescriptorVisibilities.PRIVATE_TO_THIS)) {
            return KVisibility.PRIVATE;
        }
        return null;
    }

    public static final List<Annotation> computeAnnotations(Annotated annotated) {
        Annotation annotation;
        Intrinsics.checkNotNullParameter(annotated, "<this>");
        Collection arrayList = new ArrayList();
        for (AnnotationDescriptor annotationDescriptor : annotated.getAnnotations()) {
            SourceElement source = annotationDescriptor.getSource();
            if (source instanceof ReflectAnnotationSource) {
                annotation = ((ReflectAnnotationSource) source).getAnnotation();
            } else if (source instanceof RuntimeSourceElementFactory.RuntimeSourceElement) {
                ReflectJavaElement javaElement = ((RuntimeSourceElementFactory.RuntimeSourceElement) source).getJavaElement();
                ReflectJavaAnnotation reflectJavaAnnotation = javaElement instanceof ReflectJavaAnnotation ? (ReflectJavaAnnotation) javaElement : null;
                annotation = reflectJavaAnnotation != null ? reflectJavaAnnotation.getAnnotation() : null;
            } else {
                annotation = toAnnotationInstance(annotationDescriptor);
            }
            if (annotation != null) {
                arrayList.add(annotation);
            }
        }
        return unwrapRepeatableAnnotations((List) arrayList);
    }

    private static final List<Annotation> unwrapRepeatableAnnotations(List<? extends Annotation> list) {
        boolean z;
        List list2;
        Iterable<Annotation> iterable = list;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (Intrinsics.areEqual((Object) JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass((Annotation) it.next())).getSimpleName(), (Object) "Container")) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z = false;
        if (!z) {
            return list;
        }
        Collection arrayList = new ArrayList();
        for (Annotation annotation : iterable) {
            Class javaClass = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation));
            if (!Intrinsics.areEqual((Object) javaClass.getSimpleName(), (Object) "Container") || javaClass.getAnnotation(RepeatableContainer.class) == null) {
                list2 = CollectionsKt.listOf(annotation);
            } else {
                Object invoke = javaClass.getDeclaredMethod("value", new Class[0]).invoke(annotation, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.Array<out kotlin.Annotation>");
                list2 = ArraysKt.asList((T[]) (Annotation[]) invoke);
            }
            CollectionsKt.addAll(arrayList, list2);
        }
        return (List) arrayList;
    }

    private static final Annotation toAnnotationInstance(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        Class<?> javaClass = annotationClass != null ? toJavaClass(annotationClass) : null;
        if (!(javaClass instanceof Class)) {
            javaClass = null;
        }
        if (javaClass == null) {
            return null;
        }
        Collection arrayList = new ArrayList();
        for (Map.Entry entry : annotationDescriptor.getAllValueArguments().entrySet()) {
            Name name = (Name) entry.getKey();
            ClassLoader classLoader = javaClass.getClassLoader();
            Intrinsics.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
            Object runtimeValue = toRuntimeValue((ConstantValue) entry.getValue(), classLoader);
            Pair pair = runtimeValue != null ? TuplesKt.to(name.asString(), runtimeValue) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return (Annotation) AnnotationConstructorCallerKt.createAnnotationInstance$default(javaClass, MapsKt.toMap((List) arrayList), (List) null, 4, (Object) null);
    }

    private static final Object toRuntimeValue(ConstantValue<?> constantValue, ClassLoader classLoader) {
        if (constantValue instanceof AnnotationValue) {
            return toAnnotationInstance((AnnotationDescriptor) ((AnnotationValue) constantValue).getValue());
        }
        if (constantValue instanceof ArrayValue) {
            return arrayToRuntimeValue((ArrayValue) constantValue, classLoader);
        }
        if (constantValue instanceof EnumValue) {
            Pair pair = (Pair) ((EnumValue) constantValue).getValue();
            Name name = (Name) pair.component2();
            Class loadClass$default = loadClass$default(classLoader, (ClassId) pair.component1(), 0, 4, (Object) null);
            if (loadClass$default != null) {
                return Util.getEnumConstantByName(loadClass$default, name.asString());
            }
        } else if (constantValue instanceof KClassValue) {
            KClassValue.Value value = (KClassValue.Value) ((KClassValue) constantValue).getValue();
            if (value instanceof KClassValue.Value.NormalClass) {
                KClassValue.Value.NormalClass normalClass = (KClassValue.Value.NormalClass) value;
                return loadClass(classLoader, normalClass.getClassId(), normalClass.getArrayDimensions());
            } else if (value instanceof KClassValue.Value.LocalClass) {
                ClassifierDescriptor declarationDescriptor = ((KClassValue.Value.LocalClass) value).getType().getConstructor().getDeclarationDescriptor();
                ClassDescriptor classDescriptor = declarationDescriptor instanceof ClassDescriptor ? (ClassDescriptor) declarationDescriptor : null;
                if (classDescriptor != null) {
                    return toJavaClass(classDescriptor);
                }
            } else {
                throw new NoWhenBranchMatchedException();
            }
        } else {
            if (!(constantValue instanceof ErrorValue ? true : constantValue instanceof NullValue)) {
                return constantValue.getValue();
            }
        }
        return null;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [boolean[], char[], byte[], short[]], vars: [r7v11 ?, r7v1 ?, r7v12 ?, r7v13 ?, r7v14 ?, r7v15 ?, r7v16 ?, r7v17 ?, r7v18 ?, r7v19 ?, r7v20 ?, r7v21 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:51)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    private static final java.lang.Object arrayToRuntimeValue(kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue r6, java.lang.ClassLoader r7) {
        /*
            boolean r0 = r6 instanceof kotlin.reflect.jvm.internal.impl.resolve.constants.TypedArrayValue
            r1 = 0
            if (r0 == 0) goto L_0x0009
            r0 = r6
            kotlin.reflect.jvm.internal.impl.resolve.constants.TypedArrayValue r0 = (kotlin.reflect.jvm.internal.impl.resolve.constants.TypedArrayValue) r0
            goto L_0x000a
        L_0x0009:
            r0 = r1
        L_0x000a:
            if (r0 == 0) goto L_0x0243
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getType()
            if (r0 != 0) goto L_0x0014
            goto L_0x0243
        L_0x0014:
            java.lang.Object r2 = r6.getValue()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r4)
            r3.<init>(r4)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r2 = r2.iterator()
        L_0x002b:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x003f
            java.lang.Object r4 = r2.next()
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r4 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r4
            java.lang.Object r4 = toRuntimeValue(r4, r7)
            r3.add(r4)
            goto L_0x002b
        L_0x003f:
            java.util.List r3 = (java.util.List) r3
            kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType r2 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.getPrimitiveArrayElementType(r0)
            if (r2 != 0) goto L_0x0049
            r2 = -1
            goto L_0x0051
        L_0x0049:
            int[] r4 = kotlin.reflect.jvm.internal.UtilKt.WhenMappings.$EnumSwitchMapping$0
            int r2 = r2.ordinal()
            r2 = r4[r2]
        L_0x0051:
            r4 = 0
            switch(r2) {
                case -1: goto L_0x016b;
                case 0: goto L_0x0055;
                case 1: goto L_0x0149;
                case 2: goto L_0x0127;
                case 3: goto L_0x0105;
                case 4: goto L_0x00e3;
                case 5: goto L_0x00c1;
                case 6: goto L_0x009f;
                case 7: goto L_0x007d;
                case 8: goto L_0x005b;
                default: goto L_0x0055;
            }
        L_0x0055:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        L_0x005b:
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            double[] r7 = new double[r6]
        L_0x0067:
            if (r4 >= r6) goto L_0x020f
            java.lang.Object r0 = r3.get(r4)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Double"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            java.lang.Double r0 = (java.lang.Double) r0
            double r0 = r0.doubleValue()
            r7[r4] = r0
            int r4 = r4 + 1
            goto L_0x0067
        L_0x007d:
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            long[] r7 = new long[r6]
        L_0x0089:
            if (r4 >= r6) goto L_0x020f
            java.lang.Object r0 = r3.get(r4)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Long"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            java.lang.Long r0 = (java.lang.Long) r0
            long r0 = r0.longValue()
            r7[r4] = r0
            int r4 = r4 + 1
            goto L_0x0089
        L_0x009f:
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            float[] r7 = new float[r6]
        L_0x00ab:
            if (r4 >= r6) goto L_0x020f
            java.lang.Object r0 = r3.get(r4)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Float"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            r7[r4] = r0
            int r4 = r4 + 1
            goto L_0x00ab
        L_0x00c1:
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            int[] r7 = new int[r6]
        L_0x00cd:
            if (r4 >= r6) goto L_0x020f
            java.lang.Object r0 = r3.get(r4)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Int"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r7[r4] = r0
            int r4 = r4 + 1
            goto L_0x00cd
        L_0x00e3:
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            short[] r7 = new short[r6]
        L_0x00ef:
            if (r4 >= r6) goto L_0x020f
            java.lang.Object r0 = r3.get(r4)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Short"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            java.lang.Short r0 = (java.lang.Short) r0
            short r0 = r0.shortValue()
            r7[r4] = r0
            int r4 = r4 + 1
            goto L_0x00ef
        L_0x0105:
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            byte[] r7 = new byte[r6]
        L_0x0111:
            if (r4 >= r6) goto L_0x020f
            java.lang.Object r0 = r3.get(r4)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Byte"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            java.lang.Byte r0 = (java.lang.Byte) r0
            byte r0 = r0.byteValue()
            r7[r4] = r0
            int r4 = r4 + 1
            goto L_0x0111
        L_0x0127:
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            char[] r7 = new char[r6]
        L_0x0133:
            if (r4 >= r6) goto L_0x020f
            java.lang.Object r0 = r3.get(r4)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Char"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            java.lang.Character r0 = (java.lang.Character) r0
            char r0 = r0.charValue()
            r7[r4] = r0
            int r4 = r4 + 1
            goto L_0x0133
        L_0x0149:
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            boolean[] r7 = new boolean[r6]
        L_0x0155:
            if (r4 >= r6) goto L_0x020f
            java.lang.Object r0 = r3.get(r4)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Boolean"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r7[r4] = r0
            int r4 = r4 + 1
            goto L_0x0155
        L_0x016b:
            boolean r2 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isArray(r0)
            if (r2 == 0) goto L_0x022a
            java.util.List r0 = r0.getArguments()
            java.lang.Object r0 = kotlin.collections.CollectionsKt.single(r0)
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r0 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection) r0
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getType()
            java.lang.String r2 = "getType(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r2 = r0.getConstructor()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r2 = r2.getDeclarationDescriptor()
            boolean r5 = r2 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            if (r5 == 0) goto L_0x0193
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r2
            goto L_0x0194
        L_0x0193:
            r2 = r1
        L_0x0194:
            if (r2 == 0) goto L_0x0211
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isString(r0)
            if (r0 == 0) goto L_0x01b8
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            java.lang.String[] r7 = new java.lang.String[r6]
        L_0x01a8:
            if (r4 >= r6) goto L_0x020f
            java.lang.Object r0 = r3.get(r4)
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.String"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            r7[r4] = r0
            int r4 = r4 + 1
            goto L_0x01a8
        L_0x01b8:
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isKClass(r2)
            if (r0 == 0) goto L_0x01da
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            java.lang.Class[] r7 = new java.lang.Class[r6]
        L_0x01ca:
            if (r4 >= r6) goto L_0x020f
            java.lang.Object r0 = r3.get(r4)
            java.lang.String r1 = "null cannot be cast to non-null type java.lang.Class<*>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            r7[r4] = r0
            int r4 = r4 + 1
            goto L_0x01ca
        L_0x01da:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r2 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor) r2
            kotlin.reflect.jvm.internal.impl.name.ClassId r0 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.getClassId(r2)
            if (r0 == 0) goto L_0x0210
            r2 = 4
            java.lang.Class r7 = loadClass$default(r7, r0, r4, r2, r1)
            if (r7 != 0) goto L_0x01ea
            goto L_0x0210
        L_0x01ea:
            java.lang.Object r6 = r6.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            java.lang.Object r6 = java.lang.reflect.Array.newInstance(r7, r6)
            java.lang.String r7 = "null cannot be cast to non-null type kotlin.Array<in kotlin.Any?>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r7)
            r7 = r6
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            int r6 = r3.size()
        L_0x0204:
            if (r4 >= r6) goto L_0x020f
            java.lang.Object r0 = r3.get(r4)
            r7[r4] = r0
            int r4 = r4 + 1
            goto L_0x0204
        L_0x020f:
            return r7
        L_0x0210:
            return r1
        L_0x0211:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r1 = "Not a class type: "
            r7.<init>(r1)
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.String r7 = r7.toString()
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x022a:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Not an array type: "
            r6.<init>(r7)
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        L_0x0243:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.UtilKt.arrayToRuntimeValue(kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue, java.lang.ClassLoader):java.lang.Object");
    }

    public static final KFunctionImpl asKFunctionImpl(Object obj) {
        KFunctionImpl kFunctionImpl = obj instanceof KFunctionImpl ? (KFunctionImpl) obj : null;
        if (kFunctionImpl != null) {
            return kFunctionImpl;
        }
        FunctionReference functionReference = obj instanceof FunctionReference ? (FunctionReference) obj : null;
        KAnnotatedElement compute = functionReference != null ? functionReference.compute() : null;
        if (compute instanceof KFunctionImpl) {
            return (KFunctionImpl) compute;
        }
        return null;
    }

    public static final KPropertyImpl<?> asKPropertyImpl(Object obj) {
        KPropertyImpl<?> kPropertyImpl = obj instanceof KPropertyImpl ? (KPropertyImpl) obj : null;
        if (kPropertyImpl != null) {
            return kPropertyImpl;
        }
        PropertyReference propertyReference = obj instanceof PropertyReference ? (PropertyReference) obj : null;
        KAnnotatedElement compute = propertyReference != null ? propertyReference.compute() : null;
        if (compute instanceof KPropertyImpl) {
            return (KPropertyImpl) compute;
        }
        return null;
    }

    public static final KCallableImpl<?> asKCallableImpl(Object obj) {
        KCallableImpl<?> kCallableImpl = obj instanceof KCallableImpl ? (KCallableImpl) obj : null;
        if (kCallableImpl != null) {
            return kCallableImpl;
        }
        KFunctionImpl asKFunctionImpl = asKFunctionImpl(obj);
        return asKFunctionImpl != null ? asKFunctionImpl : asKPropertyImpl(obj);
    }

    public static final ReceiverParameterDescriptor getInstanceReceiverParameter(CallableDescriptor callableDescriptor) {
        Intrinsics.checkNotNullParameter(callableDescriptor, "<this>");
        if (callableDescriptor.getDispatchReceiverParameter() == null) {
            return null;
        }
        DeclarationDescriptor containingDeclaration = callableDescriptor.getContainingDeclaration();
        Intrinsics.checkNotNull(containingDeclaration, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        return ((ClassDescriptor) containingDeclaration).getThisAsReceiverParameter();
    }

    public static final <M extends MessageLite, D extends CallableDescriptor> D deserializeToDescriptor(Class<?> cls, M m, NameResolver nameResolver, TypeTable typeTable, BinaryVersion binaryVersion, Function2<? super MemberDeserializer, ? super M, ? extends D> function2) {
        List<ProtoBuf.TypeParameter> typeParameterList;
        M m2 = m;
        Function2<? super MemberDeserializer, ? super M, ? extends D> function22 = function2;
        Class<?> cls2 = cls;
        Intrinsics.checkNotNullParameter(cls, "moduleAnchor");
        Intrinsics.checkNotNullParameter(m, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        Intrinsics.checkNotNullParameter(binaryVersion, "metadataVersion");
        Intrinsics.checkNotNullParameter(function22, "createDescriptor");
        RuntimeModuleData orCreateModule = ModuleByClassLoaderKt.getOrCreateModule(cls);
        if (m2 instanceof ProtoBuf.Function) {
            typeParameterList = ((ProtoBuf.Function) m2).getTypeParameterList();
        } else if (m2 instanceof ProtoBuf.Property) {
            typeParameterList = ((ProtoBuf.Property) m2).getTypeParameterList();
        } else {
            throw new IllegalStateException(("Unsupported message: " + m).toString());
        }
        List<ProtoBuf.TypeParameter> list = typeParameterList;
        VersionRequirementTable empty = VersionRequirementTable.Companion.getEMPTY();
        Intrinsics.checkNotNull(list);
        return (CallableDescriptor) function22.invoke(new MemberDeserializer(new DeserializationContext(orCreateModule.getDeserialization(), nameResolver, orCreateModule.getModule(), typeTable, empty, binaryVersion, (DeserializedContainerSource) null, (TypeDeserializer) null, list)), m);
    }

    public static final boolean isInlineClassType(KType kType) {
        KotlinType type;
        Intrinsics.checkNotNullParameter(kType, "<this>");
        KTypeImpl kTypeImpl = kType instanceof KTypeImpl ? (KTypeImpl) kType : null;
        return (kTypeImpl == null || (type = kTypeImpl.getType()) == null || !InlineClassesUtilsKt.isInlineClassType(type)) ? false : true;
    }

    public static final boolean getNeedsMultiFieldValueClassFlattening(KType kType) {
        KotlinType type;
        Intrinsics.checkNotNullParameter(kType, "<this>");
        KTypeImpl kTypeImpl = kType instanceof KTypeImpl ? (KTypeImpl) kType : null;
        return (kTypeImpl == null || (type = kTypeImpl.getType()) == null || !InlineClassesUtilsKt.needsMfvcFlattening(type)) ? false : true;
    }

    public static final Object defaultPrimitiveValue(Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (!(type instanceof Class) || !((Class) type).isPrimitive()) {
            return null;
        }
        if (Intrinsics.areEqual((Object) type, (Object) Boolean.TYPE)) {
            return false;
        }
        if (Intrinsics.areEqual((Object) type, (Object) Character.TYPE)) {
            return 0;
        }
        if (Intrinsics.areEqual((Object) type, (Object) Byte.TYPE)) {
            return (byte) 0;
        }
        if (Intrinsics.areEqual((Object) type, (Object) Short.TYPE)) {
            return (short) 0;
        }
        if (Intrinsics.areEqual((Object) type, (Object) Integer.TYPE)) {
            return 0;
        }
        if (Intrinsics.areEqual((Object) type, (Object) Float.TYPE)) {
            return Float.valueOf(0.0f);
        }
        if (Intrinsics.areEqual((Object) type, (Object) Long.TYPE)) {
            return 0L;
        }
        if (Intrinsics.areEqual((Object) type, (Object) Double.TYPE)) {
            return Double.valueOf(0.0d);
        }
        if (Intrinsics.areEqual((Object) type, (Object) Void.TYPE)) {
            throw new IllegalStateException("Parameter with void type is illegal");
        }
        throw new UnsupportedOperationException("Unknown primitive: " + type);
    }

    public static final Method getDeclaredMethodOrNull(Class<?> cls, String str, Class<?>... clsArr) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(clsArr, "parameterTypes");
        try {
            return cls.getDeclaredMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static final Field getDeclaredFieldOrNull(Class<?> cls, String str) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }
}
