package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.AnnotationSpec;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB[\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u0012\u0018\b\u0002\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\u0010\u000fJ6\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\u0016\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u000e0\fH\u0016JJ\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\u0018\b\u0002\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u000e0\f2\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006J\u0015\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0010¢\u0006\u0002\b\u0018J\u001c\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006J\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0001J\u0012\u0010\u001c\u001a\u00020\u00002\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001eJ\u0012\u0010\u001c\u001a\u00020\u00002\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\rR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "Lcom/squareup/kotlinpoet/TypeName;", "enclosingType", "rawType", "Lcom/squareup/kotlinpoet/ClassName;", "typeArguments", "", "nullable", "", "annotations", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "tags", "", "Lkotlin/reflect/KClass;", "", "(Lcom/squareup/kotlinpoet/TypeName;Lcom/squareup/kotlinpoet/ClassName;Ljava/util/List;ZLjava/util/List;Ljava/util/Map;)V", "getRawType", "()Lcom/squareup/kotlinpoet/ClassName;", "getTypeArguments", "()Ljava/util/List;", "copy", "emit", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "nestedClass", "name", "", "plusParameter", "typeArgument", "Ljava/lang/Class;", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: ParameterizedTypeName.kt */
public final class ParameterizedTypeName extends TypeName {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final TypeName enclosingType;
    private final ClassName rawType;
    private final List<TypeName> typeArguments;

    @JvmStatic
    public static final ParameterizedTypeName get(ClassName className, TypeName typeName) {
        return Companion.get(className, typeName);
    }

    @JvmStatic
    public static final ParameterizedTypeName get(ClassName className, List<? extends TypeName> list) {
        return Companion.get(className, list);
    }

    @JvmStatic
    public static final ParameterizedTypeName get(ClassName className, TypeName... typeNameArr) {
        return Companion.get(className, typeNameArr);
    }

    @JvmStatic
    public static final ParameterizedTypeName get(Class<?> cls, Class<?> cls2) {
        return Companion.get(cls, cls2);
    }

    @JvmStatic
    public static final ParameterizedTypeName get(Class<?> cls, Iterable<? extends Type> iterable) {
        return Companion.get(cls, iterable);
    }

    @JvmStatic
    public static final ParameterizedTypeName get(Class<?> cls, Type... typeArr) {
        return Companion.get(cls, typeArr);
    }

    @JvmStatic
    public static final ParameterizedTypeName get(KClass<?> kClass, Iterable<? extends KClass<?>> iterable) {
        return Companion.get(kClass, iterable);
    }

    @JvmStatic
    public static final ParameterizedTypeName get(KClass<?> kClass, KClass<?> kClass2) {
        return Companion.get(kClass, kClass2);
    }

    @JvmStatic
    public static final ParameterizedTypeName get(KClass<?> kClass, KClass<?>... kClassArr) {
        return Companion.get(kClass, kClassArr);
    }

    public final ClassName getRawType() {
        return this.rawType;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ParameterizedTypeName(TypeName typeName, ClassName className, List list, boolean z, List list2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeName, className, list, (i & 8) != 0 ? false : z, (i & 16) != 0 ? CollectionsKt.emptyList() : list2, (i & 32) != 0 ? MapsKt.emptyMap() : map);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ParameterizedTypeName(TypeName typeName, ClassName className, List<? extends TypeName> list, boolean z, List<AnnotationSpec> list2, Map<KClass<?>, ? extends Object> map) {
        super(z, list2, new TagMap(map), (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(className, "rawType");
        Intrinsics.checkNotNullParameter(list, "typeArguments");
        Intrinsics.checkNotNullParameter(list2, "annotations");
        Intrinsics.checkNotNullParameter(map, "tags");
        this.enclosingType = typeName;
        this.rawType = className;
        Collection collection = list;
        this.typeArguments = UtilKt.toImmutableList(collection);
        boolean z2 = true;
        if (!(!collection.isEmpty()) && typeName == null) {
            z2 = false;
        }
        if (!z2) {
            throw new IllegalArgumentException(("no type arguments: " + className).toString());
        }
    }

    public final List<TypeName> getTypeArguments() {
        return this.typeArguments;
    }

    public ParameterizedTypeName copy(boolean z, List<AnnotationSpec> list, Map<KClass<?>, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(list, "annotations");
        Intrinsics.checkNotNullParameter(map, "tags");
        return new ParameterizedTypeName(this.enclosingType, this.rawType, this.typeArguments, z, list, map);
    }

    public static /* synthetic */ ParameterizedTypeName copy$default(ParameterizedTypeName parameterizedTypeName, boolean z, List<AnnotationSpec> list, Map<KClass<?>, Object> map, List<TypeName> list2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = parameterizedTypeName.isNullable();
        }
        if ((i & 2) != 0) {
            list = parameterizedTypeName.getAnnotations();
        }
        if ((i & 4) != 0) {
            map = parameterizedTypeName.getTags();
        }
        if ((i & 8) != 0) {
            list2 = parameterizedTypeName.typeArguments;
        }
        return parameterizedTypeName.copy(z, list, map, list2);
    }

    public final ParameterizedTypeName copy(boolean z, List<AnnotationSpec> list, Map<KClass<?>, ? extends Object> map, List<? extends TypeName> list2) {
        Intrinsics.checkNotNullParameter(list, "annotations");
        Intrinsics.checkNotNullParameter(map, "tags");
        Intrinsics.checkNotNullParameter(list2, "typeArguments");
        return new ParameterizedTypeName(this.enclosingType, this.rawType, list2, z, list, map);
    }

    public final ParameterizedTypeName plusParameter(TypeName typeName) {
        Intrinsics.checkNotNullParameter(typeName, "typeArgument");
        return new ParameterizedTypeName(this.enclosingType, this.rawType, CollectionsKt.plus(this.typeArguments, typeName), isNullable(), getAnnotations(), (Map) null, 32, (DefaultConstructorMarker) null);
    }

    public final ParameterizedTypeName plusParameter(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "typeArgument");
        return plusParameter((TypeName) ClassNames.get(kClass));
    }

    public final ParameterizedTypeName plusParameter(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "typeArgument");
        return plusParameter((TypeName) ClassNames.get(cls));
    }

    public CodeWriter emit$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "out");
        TypeName typeName = this.enclosingType;
        if (typeName != null) {
            typeName.emitAnnotations$kotlinpoet(codeWriter);
            this.enclosingType.emit$kotlinpoet(codeWriter);
            CodeWriter.emit$default(codeWriter, "." + this.rawType.getSimpleName(), false, 2, (Object) null);
        } else {
            this.rawType.emitAnnotations$kotlinpoet(codeWriter);
            this.rawType.emit$kotlinpoet(codeWriter);
        }
        if (!this.typeArguments.isEmpty()) {
            CodeWriter.emit$default(codeWriter, "<", false, 2, (Object) null);
            int i = 0;
            for (Object next : this.typeArguments) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                TypeName typeName2 = (TypeName) next;
                if (i > 0) {
                    CodeWriter.emit$default(codeWriter, ",·", false, 2, (Object) null);
                }
                typeName2.emitAnnotations$kotlinpoet(codeWriter);
                typeName2.emit$kotlinpoet(codeWriter);
                typeName2.emitNullable$kotlinpoet(codeWriter);
                i = i2;
            }
            CodeWriter.emit$default(codeWriter, ">", false, 2, (Object) null);
        }
        return codeWriter;
    }

    public final ParameterizedTypeName nestedClass(String str, List<? extends TypeName> list) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(list, "typeArguments");
        return new ParameterizedTypeName(this, this.rawType.nestedClass(str), list, false, (List) null, (Map) null, 56, (DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0000¢\u0006\u0002\b\u000bJ/\u0010\u0003\u001a\u00020\f2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0000¢\u0006\u0002\b\u000bJ'\u0010\u0013\u001a\u00020\u0004*\u00020\u00142\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0015\"\u00020\fH\u0007¢\u0006\u0004\b\u0003\u0010\u0016J\u001f\u0010\u0013\u001a\u00020\u0004*\u00020\u00142\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0011H\u0007¢\u0006\u0002\b\u0003J+\u0010\u0013\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u00172\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u0015\"\u00020\tH\u0007¢\u0006\u0004\b\u0003\u0010\u0018J#\u0010\u0013\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u00172\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u0019H\u0007¢\u0006\u0002\b\u0003J3\u0010\u0013\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\r2\u001a\u0010\u0010\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\r0\u0015\"\u0006\u0012\u0002\b\u00030\rH\u0007¢\u0006\u0004\b\u0003\u0010\u001aJ'\u0010\u0013\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\r2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\u0019H\u0007¢\u0006\u0002\b\u0003J\u0019\u0010\u001b\u001a\u00020\u0004*\u00020\u00142\u0006\u0010\u001c\u001a\u00020\fH\u0007¢\u0006\u0002\b\u0003J!\u0010\u001b\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u00172\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0007¢\u0006\u0002\b\u0003J!\u0010\u001b\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\r2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\rH\u0007¢\u0006\u0002\b\u0003¨\u0006\u001d"}, d2 = {"Lcom/squareup/kotlinpoet/ParameterizedTypeName$Companion;", "", "()V", "get", "Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "type", "Ljava/lang/reflect/ParameterizedType;", "map", "", "Ljava/lang/reflect/Type;", "Lcom/squareup/kotlinpoet/TypeVariableName;", "get$kotlinpoet", "Lcom/squareup/kotlinpoet/TypeName;", "Lkotlin/reflect/KClass;", "nullable", "", "typeArguments", "", "Lkotlin/reflect/KTypeProjection;", "parameterizedBy", "Lcom/squareup/kotlinpoet/ClassName;", "", "(Lcom/squareup/kotlinpoet/ClassName;[Lcom/squareup/kotlinpoet/TypeName;)Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "Ljava/lang/Class;", "(Ljava/lang/Class;[Ljava/lang/reflect/Type;)Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "", "(Lkotlin/reflect/KClass;[Lkotlin/reflect/KClass;)Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "plusParameter", "typeArgument", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: ParameterizedTypeName.kt */
    public static final class Companion {

        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        /* compiled from: ParameterizedTypeName.kt */
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

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ParameterizedTypeName get(ClassName className, TypeName... typeNameArr) {
            Intrinsics.checkNotNullParameter(className, "<this>");
            Intrinsics.checkNotNullParameter(typeNameArr, "typeArguments");
            return new ParameterizedTypeName((TypeName) null, className, ArraysKt.toList((T[]) typeNameArr), false, (List) null, (Map) null, 56, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final ParameterizedTypeName get(KClass<?> kClass, KClass<?>... kClassArr) {
            Intrinsics.checkNotNullParameter(kClass, "<this>");
            Intrinsics.checkNotNullParameter(kClassArr, "typeArguments");
            ClassName className = ClassNames.get(kClass);
            Collection arrayList = new ArrayList(kClassArr.length);
            for (KClass<?> kClass2 : kClassArr) {
                arrayList.add(TypeNames.get(kClass2));
            }
            return new ParameterizedTypeName((TypeName) null, className, (List) arrayList, false, (List) null, (Map) null, 56, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final ParameterizedTypeName get(Class<?> cls, Type... typeArr) {
            Intrinsics.checkNotNullParameter(cls, "<this>");
            Intrinsics.checkNotNullParameter(typeArr, "typeArguments");
            ClassName className = ClassNames.get(cls);
            Collection arrayList = new ArrayList(typeArr.length);
            for (Type type : typeArr) {
                arrayList.add(TypeNames.get(type));
            }
            return new ParameterizedTypeName((TypeName) null, className, (List) arrayList, false, (List) null, (Map) null, 56, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final ParameterizedTypeName get(ClassName className, List<? extends TypeName> list) {
            Intrinsics.checkNotNullParameter(className, "<this>");
            Intrinsics.checkNotNullParameter(list, "typeArguments");
            return new ParameterizedTypeName((TypeName) null, className, list, false, (List) null, (Map) null, 56, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final ParameterizedTypeName get(KClass<?> kClass, Iterable<? extends KClass<?>> iterable) {
            Intrinsics.checkNotNullParameter(kClass, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "typeArguments");
            ClassName className = ClassNames.get(kClass);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (KClass kClass2 : iterable) {
                arrayList.add(TypeNames.get((KClass<?>) kClass2));
            }
            return new ParameterizedTypeName((TypeName) null, className, (List) arrayList, false, (List) null, (Map) null, 56, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final ParameterizedTypeName get(Class<?> cls, Iterable<? extends Type> iterable) {
            Intrinsics.checkNotNullParameter(cls, "<this>");
            Intrinsics.checkNotNullParameter(iterable, "typeArguments");
            ClassName className = ClassNames.get(cls);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Type type : iterable) {
                arrayList.add(TypeNames.get(type));
            }
            return new ParameterizedTypeName((TypeName) null, className, (List) arrayList, false, (List) null, (Map) null, 56, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final ParameterizedTypeName get(ClassName className, TypeName typeName) {
            Intrinsics.checkNotNullParameter(className, "<this>");
            Intrinsics.checkNotNullParameter(typeName, "typeArgument");
            return get(className, typeName);
        }

        @JvmStatic
        public final ParameterizedTypeName get(KClass<?> kClass, KClass<?> kClass2) {
            Intrinsics.checkNotNullParameter(kClass, "<this>");
            Intrinsics.checkNotNullParameter(kClass2, "typeArgument");
            return get(kClass, (KClass<?>[]) new KClass[]{kClass2});
        }

        @JvmStatic
        public final ParameterizedTypeName get(Class<?> cls, Class<?> cls2) {
            Intrinsics.checkNotNullParameter(cls, "<this>");
            Intrinsics.checkNotNullParameter(cls2, "typeArgument");
            return get(cls, cls2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x007f  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0058 A[LOOP:0: B:7:0x0056->B:8:0x0058, LOOP_END] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.squareup.kotlinpoet.ParameterizedTypeName get$kotlinpoet(java.lang.reflect.ParameterizedType r12, java.util.Map<java.lang.reflect.Type, com.squareup.kotlinpoet.TypeVariableName> r13) {
            /*
                r11 = this;
                java.lang.String r0 = "type"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
                java.lang.String r0 = "map"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
                java.lang.reflect.Type r0 = r12.getRawType()
                java.lang.String r1 = "null cannot be cast to non-null type java.lang.Class<*>"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
                java.lang.Class r0 = (java.lang.Class) r0
                com.squareup.kotlinpoet.ClassName r4 = com.squareup.kotlinpoet.ClassNames.get((java.lang.Class<?>) r0)
                java.lang.reflect.Type r0 = r12.getOwnerType()
                boolean r0 = r0 instanceof java.lang.reflect.ParameterizedType
                if (r0 == 0) goto L_0x0040
                java.lang.reflect.Type r0 = r12.getRawType()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
                java.lang.Class r0 = (java.lang.Class) r0
                int r0 = r0.getModifiers()
                boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
                if (r0 != 0) goto L_0x0040
                java.lang.reflect.Type r0 = r12.getOwnerType()
                java.lang.String r1 = "null cannot be cast to non-null type java.lang.reflect.ParameterizedType"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
                java.lang.reflect.ParameterizedType r0 = (java.lang.reflect.ParameterizedType) r0
                goto L_0x0041
            L_0x0040:
                r0 = 0
            L_0x0041:
                java.lang.reflect.Type[] r12 = r12.getActualTypeArguments()
                java.lang.String r1 = "type.actualTypeArguments"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r1)
                java.lang.Object[] r12 = (java.lang.Object[]) r12
                java.util.ArrayList r1 = new java.util.ArrayList
                int r2 = r12.length
                r1.<init>(r2)
                java.util.Collection r1 = (java.util.Collection) r1
                int r2 = r12.length
                r3 = 0
            L_0x0056:
                if (r3 >= r2) goto L_0x006d
                r5 = r12[r3]
                java.lang.reflect.Type r5 = (java.lang.reflect.Type) r5
                com.squareup.kotlinpoet.TypeName$Companion r6 = com.squareup.kotlinpoet.TypeName.Companion
                java.lang.String r7 = "it"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r7)
                com.squareup.kotlinpoet.TypeName r5 = r6.get$kotlinpoet((java.lang.reflect.Type) r5, (java.util.Map<java.lang.reflect.Type, com.squareup.kotlinpoet.TypeVariableName>) r13)
                r1.add(r5)
                int r3 = r3 + 1
                goto L_0x0056
            L_0x006d:
                r5 = r1
                java.util.List r5 = (java.util.List) r5
                if (r0 == 0) goto L_0x007f
                com.squareup.kotlinpoet.ParameterizedTypeName r12 = r11.get$kotlinpoet(r0, r13)
                java.lang.String r13 = r4.getSimpleName()
                com.squareup.kotlinpoet.ParameterizedTypeName r12 = r12.nestedClass(r13, r5)
                goto L_0x008c
            L_0x007f:
                com.squareup.kotlinpoet.ParameterizedTypeName r12 = new com.squareup.kotlinpoet.ParameterizedTypeName
                r3 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                r9 = 56
                r10 = 0
                r2 = r12
                r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            L_0x008c:
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.ParameterizedTypeName.Companion.get$kotlinpoet(java.lang.reflect.ParameterizedType, java.util.Map):com.squareup.kotlinpoet.ParameterizedTypeName");
        }

        public final TypeName get$kotlinpoet(KClass<?> kClass, boolean z, List<KTypeProjection> list) {
            TypeName typeName;
            int i;
            List<KTypeProjection> list2 = list;
            KClass<?> kClass2 = kClass;
            Intrinsics.checkNotNullParameter(kClass2, "type");
            Intrinsics.checkNotNullParameter(list2, "typeArguments");
            if (list.isEmpty()) {
                TypeName typeName2 = TypeNames.get(kClass);
                return z ? TypeName.copy$default(typeName2, true, (List) null, 2, (Object) null) : typeName2;
            }
            KClass<?> orCreateKotlinClass = JvmClassMappingKt.getJavaClass(kClass).isArray() ? Reflection.getOrCreateKotlinClass(Unit[].class) : kClass2;
            Class<?> enclosingClass = JvmClassMappingKt.getJavaClass(kClass).getEnclosingClass();
            KClass<?> kotlinClass = enclosingClass != null ? JvmClassMappingKt.getKotlinClass(enclosingClass) : null;
            TypeName typeName3 = kotlinClass != null ? ParameterizedTypeName.Companion.get$kotlinpoet(kotlinClass, false, CollectionsKt.drop(list2, orCreateKotlinClass.getTypeParameters().size())) : null;
            ClassName className = TypeNames.get(orCreateKotlinClass);
            Iterable<KTypeProjection> take = CollectionsKt.take(list2, orCreateKotlinClass.getTypeParameters().size());
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(take, 10));
            for (KTypeProjection kTypeProjection : take) {
                KVariance component1 = kTypeProjection.component1();
                KType component2 = kTypeProjection.component2();
                if (component2 == null || (typeName = ParameterizedTypeNames.asTypeName(component2)) == null) {
                    typeName = TypeNames.STAR;
                } else {
                    if (component1 == null) {
                        i = -1;
                    } else {
                        i = WhenMappings.$EnumSwitchMapping$0[component1.ordinal()];
                    }
                    if (i == -1) {
                        typeName = TypeNames.STAR;
                    } else if (i == 1) {
                        continue;
                    } else if (i == 2) {
                        typeName = WildcardTypeName.Companion.consumerOf(typeName);
                    } else if (i == 3) {
                        typeName = WildcardTypeName.Companion.producerOf(typeName);
                    } else {
                        throw new NoWhenBranchMatchedException();
                    }
                }
                arrayList.add(typeName);
            }
            List list3 = (List) arrayList;
            Iterable<Annotation> annotations = orCreateKotlinClass.getAnnotations();
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations, 10));
            for (Annotation annotation : annotations) {
                arrayList2.add(AnnotationSpec.Companion.get$default(AnnotationSpec.Companion, annotation, false, 2, (Object) null));
            }
            return new ParameterizedTypeName(typeName3, className, list3, z, (List) arrayList2, (Map) null, 32, (DefaultConstructorMarker) null);
        }
    }
}
