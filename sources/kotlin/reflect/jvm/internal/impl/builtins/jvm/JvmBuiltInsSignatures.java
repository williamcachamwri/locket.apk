package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

/* compiled from: JvmBuiltInsSignatures.kt */
public final class JvmBuiltInsSignatures {
    private static final Set<String> DEPRECATED_LIST_METHODS = SignatureBuildingComponents.INSTANCE.inJavaUtil("List", "getFirst()Ljava/lang/Object;", "getLast()Ljava/lang/Object;");
    private static final Set<String> DROP_LIST_METHOD_SIGNATURES = SetsKt.plus(SignatureBuildingComponents.INSTANCE.inJavaUtil("Collection", "toArray()[Ljava/lang/Object;", "toArray([Ljava/lang/Object;)[Ljava/lang/Object;"), "java/lang/annotation/Annotation.annotationType()Ljava/lang/Class;");
    private static final Set<String> HIDDEN_CONSTRUCTOR_SIGNATURES;
    private static final Set<String> HIDDEN_METHOD_SIGNATURES;
    public static final JvmBuiltInsSignatures INSTANCE;
    private static final Set<String> MUTABLE_METHOD_SIGNATURES;
    private static final Set<String> VISIBLE_CONSTRUCTOR_SIGNATURES;
    private static final Set<String> VISIBLE_METHOD_SIGNATURES;

    private JvmBuiltInsSignatures() {
    }

    public final Set<String> getDROP_LIST_METHOD_SIGNATURES() {
        return DROP_LIST_METHOD_SIGNATURES;
    }

    static {
        JvmBuiltInsSignatures jvmBuiltInsSignatures = new JvmBuiltInsSignatures();
        INSTANCE = jvmBuiltInsSignatures;
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        HIDDEN_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(jvmBuiltInsSignatures.buildPrimitiveValueMethodsSet(), signatureBuildingComponents.inJavaUtil("List", "sort(Ljava/util/Comparator;)V", "reversed()Ljava/util/List;")), signatureBuildingComponents.inJavaLang("String", "codePointAt(I)I", "codePointBefore(I)I", "codePointCount(II)I", "compareToIgnoreCase(Ljava/lang/String;)I", "concat(Ljava/lang/String;)Ljava/lang/String;", "contains(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/StringBuffer;)Z", "endsWith(Ljava/lang/String;)Z", "equalsIgnoreCase(Ljava/lang/String;)Z", "getBytes()[B", "getBytes(II[BI)V", "getBytes(Ljava/lang/String;)[B", "getBytes(Ljava/nio/charset/Charset;)[B", "getChars(II[CI)V", "indexOf(I)I", "indexOf(II)I", "indexOf(Ljava/lang/String;)I", "indexOf(Ljava/lang/String;I)I", "intern()Ljava/lang/String;", "isEmpty()Z", "lastIndexOf(I)I", "lastIndexOf(II)I", "lastIndexOf(Ljava/lang/String;)I", "lastIndexOf(Ljava/lang/String;I)I", "matches(Ljava/lang/String;)Z", "offsetByCodePoints(II)I", "regionMatches(ILjava/lang/String;II)Z", "regionMatches(ZILjava/lang/String;II)Z", "replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(CC)Ljava/lang/String;", "replaceFirst(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", "split(Ljava/lang/String;I)[Ljava/lang/String;", "split(Ljava/lang/String;)[Ljava/lang/String;", "startsWith(Ljava/lang/String;I)Z", "startsWith(Ljava/lang/String;)Z", "substring(II)Ljava/lang/String;", "substring(I)Ljava/lang/String;", "toCharArray()[C", "toLowerCase()Ljava/lang/String;", "toLowerCase(Ljava/util/Locale;)Ljava/lang/String;", "toUpperCase()Ljava/lang/String;", "toUpperCase(Ljava/util/Locale;)Ljava/lang/String;", "trim()Ljava/lang/String;", "isBlank()Z", "lines()Ljava/util/stream/Stream;", "repeat(I)Ljava/lang/String;")), signatureBuildingComponents.inJavaLang("Double", "isInfinite()Z", "isNaN()Z")), signatureBuildingComponents.inJavaLang("Float", "isInfinite()Z", "isNaN()Z")), signatureBuildingComponents.inJavaLang("Enum", "getDeclaringClass()Ljava/lang/Class;", "finalize()V")), signatureBuildingComponents.inJavaLang("CharSequence", "isEmpty()Z"));
        SignatureBuildingComponents signatureBuildingComponents2 = SignatureBuildingComponents.INSTANCE;
        VISIBLE_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(signatureBuildingComponents2.inJavaLang("CharSequence", "codePoints()Ljava/util/stream/IntStream;", "chars()Ljava/util/stream/IntStream;"), signatureBuildingComponents2.inJavaUtil("Iterator", "forEachRemaining(Ljava/util/function/Consumer;)V")), signatureBuildingComponents2.inJavaLang("Iterable", "forEach(Ljava/util/function/Consumer;)V", "spliterator()Ljava/util/Spliterator;")), signatureBuildingComponents2.inJavaLang("Throwable", "setStackTrace([Ljava/lang/StackTraceElement;)V", "fillInStackTrace()Ljava/lang/Throwable;", "getLocalizedMessage()Ljava/lang/String;", "printStackTrace()V", "printStackTrace(Ljava/io/PrintStream;)V", "printStackTrace(Ljava/io/PrintWriter;)V", "getStackTrace()[Ljava/lang/StackTraceElement;", "initCause(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "getSuppressed()[Ljava/lang/Throwable;", "addSuppressed(Ljava/lang/Throwable;)V")), signatureBuildingComponents2.inJavaUtil("Collection", "spliterator()Ljava/util/Spliterator;", "parallelStream()Ljava/util/stream/Stream;", "stream()Ljava/util/stream/Stream;", "removeIf(Ljava/util/function/Predicate;)Z")), signatureBuildingComponents2.inJavaUtil("List", "replaceAll(Ljava/util/function/UnaryOperator;)V", "addFirst(Ljava/lang/Object;)V", "addLast(Ljava/lang/Object;)V", "removeFirst()Ljava/lang/Object;", "removeLast()Ljava/lang/Object;")), signatureBuildingComponents2.inJavaUtil("Map", "getOrDefault(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "forEach(Ljava/util/function/BiConsumer;)V", "replaceAll(Ljava/util/function/BiFunction;)V", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;"));
        SignatureBuildingComponents signatureBuildingComponents3 = SignatureBuildingComponents.INSTANCE;
        MUTABLE_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus(signatureBuildingComponents3.inJavaUtil("Collection", "removeIf(Ljava/util/function/Predicate;)Z"), signatureBuildingComponents3.inJavaUtil("List", "replaceAll(Ljava/util/function/UnaryOperator;)V", "sort(Ljava/util/Comparator;)V", "addFirst(Ljava/lang/Object;)V", "addLast(Ljava/lang/Object;)V", "removeFirst()Ljava/lang/Object;", "removeLast()Ljava/lang/Object;")), signatureBuildingComponents3.inJavaUtil("Map", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove(Ljava/lang/Object;Ljava/lang/Object;)Z", "replaceAll(Ljava/util/function/BiFunction;)V", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z"));
        SignatureBuildingComponents signatureBuildingComponents4 = SignatureBuildingComponents.INSTANCE;
        Set<String> buildPrimitiveStringConstructorsSet = jvmBuiltInsSignatures.buildPrimitiveStringConstructorsSet();
        String[] constructors = signatureBuildingComponents4.constructors("D");
        Set<T> plus = SetsKt.plus(buildPrimitiveStringConstructorsSet, signatureBuildingComponents4.inJavaLang("Float", (String[]) Arrays.copyOf(constructors, constructors.length)));
        String[] constructors2 = signatureBuildingComponents4.constructors("[C", "[CII", "[III", "[BIILjava/lang/String;", "[BIILjava/nio/charset/Charset;", "[BLjava/lang/String;", "[BLjava/nio/charset/Charset;", "[BII", "[B", "Ljava/lang/StringBuffer;", "Ljava/lang/StringBuilder;");
        HIDDEN_CONSTRUCTOR_SIGNATURES = SetsKt.plus(plus, signatureBuildingComponents4.inJavaLang("String", (String[]) Arrays.copyOf(constructors2, constructors2.length)));
        SignatureBuildingComponents signatureBuildingComponents5 = SignatureBuildingComponents.INSTANCE;
        String[] constructors3 = signatureBuildingComponents5.constructors("Ljava/lang/String;Ljava/lang/Throwable;ZZ");
        VISIBLE_CONSTRUCTOR_SIGNATURES = signatureBuildingComponents5.inJavaLang("Throwable", (String[]) Arrays.copyOf(constructors3, constructors3.length));
    }

    public final Set<String> getHIDDEN_METHOD_SIGNATURES() {
        return HIDDEN_METHOD_SIGNATURES;
    }

    public final Set<String> getDEPRECATED_LIST_METHODS() {
        return DEPRECATED_LIST_METHODS;
    }

    public final Set<String> getVISIBLE_METHOD_SIGNATURES() {
        return VISIBLE_METHOD_SIGNATURES;
    }

    public final Set<String> getMUTABLE_METHOD_SIGNATURES() {
        return MUTABLE_METHOD_SIGNATURES;
    }

    public final Set<String> getHIDDEN_CONSTRUCTOR_SIGNATURES() {
        return HIDDEN_CONSTRUCTOR_SIGNATURES;
    }

    public final Set<String> getVISIBLE_CONSTRUCTOR_SIGNATURES() {
        return VISIBLE_CONSTRUCTOR_SIGNATURES;
    }

    public final boolean isSerializableInJava(FqNameUnsafe fqNameUnsafe) {
        Intrinsics.checkNotNullParameter(fqNameUnsafe, "fqName");
        if (isArrayOrPrimitiveArray(fqNameUnsafe)) {
            return true;
        }
        ClassId mapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(fqNameUnsafe);
        if (mapKotlinToJava == null) {
            return false;
        }
        try {
            return Serializable.class.isAssignableFrom(Class.forName(mapKotlinToJava.asSingleFqName().asString()));
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public final boolean isArrayOrPrimitiveArray(FqNameUnsafe fqNameUnsafe) {
        Intrinsics.checkNotNullParameter(fqNameUnsafe, "fqName");
        return Intrinsics.areEqual((Object) fqNameUnsafe, (Object) StandardNames.FqNames.array) || StandardNames.isPrimitiveArray(fqNameUnsafe);
    }

    private final Set<String> buildPrimitiveValueMethodsSet() {
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        JvmPrimitiveType[] jvmPrimitiveTypeArr = {JvmPrimitiveType.BOOLEAN, JvmPrimitiveType.CHAR};
        Collection linkedHashSet = new LinkedHashSet();
        for (JvmPrimitiveType jvmPrimitiveType : CollectionsKt.listOf(jvmPrimitiveTypeArr)) {
            String asString = jvmPrimitiveType.getWrapperFqName().shortName().asString();
            Intrinsics.checkNotNullExpressionValue(asString, "asString(...)");
            CollectionsKt.addAll(linkedHashSet, signatureBuildingComponents.inJavaLang(asString, jvmPrimitiveType.getJavaKeywordName() + "Value()" + jvmPrimitiveType.getDesc()));
        }
        return (LinkedHashSet) linkedHashSet;
    }

    private final Set<String> buildPrimitiveStringConstructorsSet() {
        SignatureBuildingComponents signatureBuildingComponents = SignatureBuildingComponents.INSTANCE;
        JvmPrimitiveType[] jvmPrimitiveTypeArr = {JvmPrimitiveType.BOOLEAN, JvmPrimitiveType.BYTE, JvmPrimitiveType.DOUBLE, JvmPrimitiveType.FLOAT, JvmPrimitiveType.BYTE, JvmPrimitiveType.INT, JvmPrimitiveType.LONG, JvmPrimitiveType.SHORT};
        Collection linkedHashSet = new LinkedHashSet();
        for (JvmPrimitiveType wrapperFqName : CollectionsKt.listOf(jvmPrimitiveTypeArr)) {
            String asString = wrapperFqName.getWrapperFqName().shortName().asString();
            Intrinsics.checkNotNullExpressionValue(asString, "asString(...)");
            String[] constructors = signatureBuildingComponents.constructors("Ljava/lang/String;");
            CollectionsKt.addAll(linkedHashSet, signatureBuildingComponents.inJavaLang(asString, (String[]) Arrays.copyOf(constructors, constructors.length)));
        }
        return (LinkedHashSet) linkedHashSet;
    }
}
