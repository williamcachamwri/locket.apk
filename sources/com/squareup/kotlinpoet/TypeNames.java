package com.squareup.kotlinpoet;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u00104\u001a\u000205\"\u0006\b\u0000\u00106\u0018\u0001H\b\u001a\u0011\u00107\u001a\u000205*\u000208H\u0007¢\u0006\u0002\b9\u001a\u0011\u00107\u001a\u000205*\u00020:H\u0007¢\u0006\u0002\b9\u001a\u0015\u00107\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030;H\u0007¢\u0006\u0002\b9\"\u0010\u0010\u0000\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0005\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0007\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\b\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\t\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\n\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u000b\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\f\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\r\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u000e\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u000f\u001a\u00020\u00108\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0011\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0012\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0013\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0014\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0015\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0016\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0017\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0018\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0019\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u001a\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u001b\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u001c\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u001d\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u001e\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u001f\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010 \u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010!\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\"\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010#\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010$\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010%\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010&\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010'\u001a\u00020(8\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010)\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010*\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010+\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010,\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010-\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010.\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010/\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u00100\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u00101\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u00102\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000\"\u0010\u00103\u001a\u00020\u00018\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"ANNOTATION", "Lcom/squareup/kotlinpoet/ClassName;", "ANY", "ARRAY", "BOOLEAN", "BOOLEAN_ARRAY", "BYTE", "BYTE_ARRAY", "CHAR", "CHAR_ARRAY", "CHAR_SEQUENCE", "COLLECTION", "COMPARABLE", "DOUBLE", "DOUBLE_ARRAY", "DYNAMIC", "Lcom/squareup/kotlinpoet/Dynamic;", "ENUM", "FLOAT", "FLOAT_ARRAY", "INT", "INT_ARRAY", "ITERABLE", "LIST", "LONG", "LONG_ARRAY", "MAP", "MAP_ENTRY", "MUTABLE_COLLECTION", "MUTABLE_ITERABLE", "MUTABLE_LIST", "MUTABLE_MAP", "MUTABLE_MAP_ENTRY", "MUTABLE_SET", "NOTHING", "NUMBER", "SET", "SHORT", "SHORT_ARRAY", "STAR", "Lcom/squareup/kotlinpoet/WildcardTypeName;", "STRING", "THROWABLE", "UNIT", "U_BYTE", "U_BYTE_ARRAY", "U_INT", "U_INT_ARRAY", "U_LONG", "U_LONG_ARRAY", "U_SHORT", "U_SHORT_ARRAY", "typeNameOf", "Lcom/squareup/kotlinpoet/TypeName;", "T", "asTypeName", "Ljava/lang/reflect/Type;", "get", "Ljavax/lang/model/type/TypeMirror;", "Lkotlin/reflect/KClass;", "kotlinpoet"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeName.kt */
public final class TypeNames {
    public static final ClassName ANNOTATION = new ClassName("kotlin", "Annotation");
    public static final ClassName ANY;
    public static final ClassName ARRAY = new ClassName("kotlin", "Array");
    public static final ClassName BOOLEAN = new ClassName("kotlin", "Boolean");
    public static final ClassName BOOLEAN_ARRAY = new ClassName("kotlin", "BooleanArray");
    public static final ClassName BYTE = new ClassName("kotlin", "Byte");
    public static final ClassName BYTE_ARRAY = new ClassName("kotlin", "ByteArray");
    public static final ClassName CHAR = new ClassName("kotlin", "Char");
    public static final ClassName CHAR_ARRAY = new ClassName("kotlin", "CharArray");
    public static final ClassName CHAR_SEQUENCE = new ClassName("kotlin", "CharSequence");
    public static final ClassName COLLECTION = new ClassName("kotlin.collections", "Collection");
    public static final ClassName COMPARABLE = new ClassName("kotlin", "Comparable");
    public static final ClassName DOUBLE = new ClassName("kotlin", "Double");
    public static final ClassName DOUBLE_ARRAY = new ClassName("kotlin", "DoubleArray");
    public static final Dynamic DYNAMIC = Dynamic.INSTANCE;
    public static final ClassName ENUM = new ClassName("kotlin", "Enum");
    public static final ClassName FLOAT = new ClassName("kotlin", "Float");
    public static final ClassName FLOAT_ARRAY = new ClassName("kotlin", "FloatArray");
    public static final ClassName INT = new ClassName("kotlin", "Int");
    public static final ClassName INT_ARRAY = new ClassName("kotlin", "IntArray");
    public static final ClassName ITERABLE = new ClassName("kotlin.collections", "Iterable");
    public static final ClassName LIST = new ClassName("kotlin.collections", "List");
    public static final ClassName LONG = new ClassName("kotlin", "Long");
    public static final ClassName LONG_ARRAY = new ClassName("kotlin", "LongArray");
    public static final ClassName MAP;
    public static final ClassName MAP_ENTRY;
    public static final ClassName MUTABLE_COLLECTION = new ClassName("kotlin.collections", "MutableCollection");
    public static final ClassName MUTABLE_ITERABLE = new ClassName("kotlin.collections", "MutableIterable");
    public static final ClassName MUTABLE_LIST = new ClassName("kotlin.collections", "MutableList");
    public static final ClassName MUTABLE_MAP;
    public static final ClassName MUTABLE_MAP_ENTRY;
    public static final ClassName MUTABLE_SET = new ClassName("kotlin.collections", "MutableSet");
    public static final ClassName NOTHING = new ClassName("kotlin", "Nothing");
    public static final ClassName NUMBER = new ClassName("kotlin", "Number");
    public static final ClassName SET = new ClassName("kotlin.collections", "Set");
    public static final ClassName SHORT = new ClassName("kotlin", "Short");
    public static final ClassName SHORT_ARRAY = new ClassName("kotlin", "ShortArray");
    public static final WildcardTypeName STAR;
    public static final ClassName STRING = new ClassName("kotlin", "String");
    public static final ClassName THROWABLE = new ClassName("kotlin", "Throwable");
    public static final ClassName UNIT = new ClassName("kotlin", "Unit");
    public static final ClassName U_BYTE = new ClassName("kotlin", "UByte");
    public static final ClassName U_BYTE_ARRAY = new ClassName("kotlin", "UByteArray");
    public static final ClassName U_INT = new ClassName("kotlin", "UInt");
    public static final ClassName U_INT_ARRAY = new ClassName("kotlin", "UIntArray");
    public static final ClassName U_LONG = new ClassName("kotlin", "ULong");
    public static final ClassName U_LONG_ARRAY = new ClassName("kotlin", "ULongArray");
    public static final ClassName U_SHORT = new ClassName("kotlin", "UShort");
    public static final ClassName U_SHORT_ARRAY = new ClassName("kotlin", "UShortArray");

    static {
        ClassName className = new ClassName("kotlin", "Any");
        ANY = className;
        ClassName className2 = new ClassName("kotlin.collections", "Map");
        MAP = className2;
        MAP_ENTRY = className2.nestedClass("Entry");
        ClassName className3 = new ClassName("kotlin.collections", "MutableMap");
        MUTABLE_MAP = className3;
        MUTABLE_MAP_ENTRY = className3.nestedClass("Entry");
        STAR = WildcardTypeName.Companion.producerOf(TypeName.copy$default(className, true, (List) null, 2, (Object) null));
    }

    public static final TypeName get(TypeMirror typeMirror) {
        Intrinsics.checkNotNullParameter(typeMirror, "<this>");
        return TypeName.Companion.get$kotlinpoet(typeMirror, (Map<TypeParameterElement, TypeVariableName>) new LinkedHashMap());
    }

    public static final ClassName get(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return ClassNames.get(kClass);
    }

    public static final TypeName get(Type type) {
        Intrinsics.checkNotNullParameter(type, "<this>");
        return TypeName.Companion.get$kotlinpoet(type, (Map<Type, TypeVariableName>) new LinkedHashMap());
    }
}
