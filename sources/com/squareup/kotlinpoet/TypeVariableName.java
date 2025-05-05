package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.TypeName;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000  2\u00020\u0001:\u0001 Bg\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005\u0012\u0018\b\u0002\u0010\r\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\u0010\u0011JT\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u001a\u001a\u00020\t2\u0018\b\u0002\u0010\r\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0004\u0012\u00020\u00100\u000eJ6\u0010\u0019\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u0016\u0010\r\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0004\u0012\u00020\u00100\u000eH\u0016J\u0015\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0010¢\u0006\u0002\b\u001eJ\u0018\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005*\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006!"}, d2 = {"Lcom/squareup/kotlinpoet/TypeVariableName;", "Lcom/squareup/kotlinpoet/TypeName;", "name", "", "bounds", "", "variance", "Lcom/squareup/kotlinpoet/KModifier;", "isReified", "", "nullable", "annotations", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "tags", "", "Lkotlin/reflect/KClass;", "", "(Ljava/lang/String;Ljava/util/List;Lcom/squareup/kotlinpoet/KModifier;ZZLjava/util/List;Ljava/util/Map;)V", "getBounds", "()Ljava/util/List;", "()Z", "getName", "()Ljava/lang/String;", "getVariance", "()Lcom/squareup/kotlinpoet/KModifier;", "copy", "reified", "emit", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "withoutImplicitBound", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeVariableName.kt */
public final class TypeVariableName extends TypeName {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final ClassName JAVA_OBJECT = new ClassName("java.lang", "Object");
    /* access modifiers changed from: private */
    public static final List<TypeName> NULLABLE_ANY_LIST = CollectionsKt.listOf(CodeWriterKt.getNULLABLE_ANY());
    private final List<TypeName> bounds;
    private final boolean isReified;
    private final String name;
    private final KModifier variance;

    @JvmStatic
    public static final TypeVariableName get(String str) {
        return Companion.get(str);
    }

    @JvmStatic
    public static final TypeVariableName get(String str, KModifier kModifier) {
        return Companion.get(str, kModifier);
    }

    @JvmStatic
    public static final TypeVariableName get(String str, List<? extends TypeName> list) {
        return Companion.get(str, list);
    }

    @JvmStatic
    public static final TypeVariableName get(String str, List<? extends TypeName> list, KModifier kModifier) {
        return Companion.get(str, list, kModifier);
    }

    @JvmStatic
    public static final TypeVariableName get(String str, TypeName... typeNameArr) {
        return Companion.get(str, typeNameArr);
    }

    @JvmStatic
    public static final TypeVariableName get(String str, TypeName[] typeNameArr, KModifier kModifier) {
        return Companion.get(str, typeNameArr, kModifier);
    }

    @JvmStatic
    public static final TypeVariableName get(String str, Type... typeArr) {
        return Companion.get(str, typeArr);
    }

    @JvmStatic
    public static final TypeVariableName get(String str, Type[] typeArr, KModifier kModifier) {
        return Companion.get(str, typeArr, kModifier);
    }

    @JvmStatic
    public static final TypeVariableName get(String str, KClass<?>... kClassArr) {
        return Companion.get(str, kClassArr);
    }

    @JvmStatic
    public static final TypeVariableName get(String str, KClass<?>[] kClassArr, KModifier kModifier) {
        return Companion.get(str, kClassArr, kModifier);
    }

    @JvmStatic
    public static final TypeVariableName getWithClasses(String str, Iterable<? extends KClass<?>> iterable) {
        return Companion.getWithClasses(str, iterable);
    }

    @JvmStatic
    public static final TypeVariableName getWithClasses(String str, Iterable<? extends KClass<?>> iterable, KModifier kModifier) {
        return Companion.getWithClasses(str, iterable, kModifier);
    }

    @JvmStatic
    public static final TypeVariableName getWithTypes(String str, Iterable<? extends Type> iterable) {
        return Companion.getWithTypes(str, iterable);
    }

    @JvmStatic
    public static final TypeVariableName getWithTypes(String str, Iterable<? extends Type> iterable, KModifier kModifier) {
        return Companion.getWithTypes(str, iterable, kModifier);
    }

    public final String getName() {
        return this.name;
    }

    public final List<TypeName> getBounds() {
        return this.bounds;
    }

    public final KModifier getVariance() {
        return this.variance;
    }

    public final boolean isReified() {
        return this.isReified;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* synthetic */ TypeVariableName(java.lang.String r10, java.util.List r11, com.squareup.kotlinpoet.KModifier r12, boolean r13, boolean r14, java.util.List r15, java.util.Map r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
        /*
            r9 = this;
            r0 = r17 & 4
            if (r0 == 0) goto L_0x0007
            r0 = 0
            r4 = r0
            goto L_0x0008
        L_0x0007:
            r4 = r12
        L_0x0008:
            r0 = r17 & 8
            r1 = 0
            if (r0 == 0) goto L_0x000f
            r5 = r1
            goto L_0x0010
        L_0x000f:
            r5 = r13
        L_0x0010:
            r0 = r17 & 16
            if (r0 == 0) goto L_0x0016
            r6 = r1
            goto L_0x0017
        L_0x0016:
            r6 = r14
        L_0x0017:
            r0 = r17 & 32
            if (r0 == 0) goto L_0x0021
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            r7 = r0
            goto L_0x0022
        L_0x0021:
            r7 = r15
        L_0x0022:
            r0 = r17 & 64
            if (r0 == 0) goto L_0x002c
            java.util.Map r0 = kotlin.collections.MapsKt.emptyMap()
            r8 = r0
            goto L_0x002e
        L_0x002c:
            r8 = r16
        L_0x002e:
            r1 = r9
            r2 = r10
            r3 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.TypeVariableName.<init>(java.lang.String, java.util.List, com.squareup.kotlinpoet.KModifier, boolean, boolean, java.util.List, java.util.Map, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    private TypeVariableName(String str, List<? extends TypeName> list, KModifier kModifier, boolean z, boolean z2, List<AnnotationSpec> list2, Map<KClass<?>, ? extends Object> map) {
        super(z2, list2, new TagMap(map), (DefaultConstructorMarker) null);
        this.name = str;
        this.bounds = list;
        this.variance = kModifier;
        this.isReified = z;
    }

    public TypeVariableName copy(boolean z, List<AnnotationSpec> list, Map<KClass<?>, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(list, "annotations");
        Intrinsics.checkNotNullParameter(map, "tags");
        return copy(z, list, this.bounds, this.isReified, map);
    }

    public static /* synthetic */ TypeVariableName copy$default(TypeVariableName typeVariableName, boolean z, List list, List list2, boolean z2, Map<KClass<?>, Object> map, int i, Object obj) {
        if ((i & 1) != 0) {
            z = typeVariableName.isNullable();
        }
        if ((i & 2) != 0) {
            list = CollectionsKt.toList(typeVariableName.getAnnotations());
        }
        List list3 = list;
        if ((i & 4) != 0) {
            list2 = CollectionsKt.toList(typeVariableName.bounds);
        }
        List list4 = list2;
        if ((i & 8) != 0) {
            z2 = typeVariableName.isReified;
        }
        boolean z3 = z2;
        if ((i & 16) != 0) {
            map = typeVariableName.getTagMap$kotlinpoet().getTags();
        }
        return typeVariableName.copy(z, list3, list4, z3, map);
    }

    public final TypeVariableName copy(boolean z, List<AnnotationSpec> list, List<? extends TypeName> list2, boolean z2, Map<KClass<?>, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(list, "annotations");
        Intrinsics.checkNotNullParameter(list2, "bounds");
        Intrinsics.checkNotNullParameter(map, "tags");
        return new TypeVariableName(this.name, withoutImplicitBound(list2), this.variance, z2, z, list, map);
    }

    private final List<TypeName> withoutImplicitBound(List<? extends TypeName> list) {
        if (list.size() == 1) {
            return list;
        }
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            if (!Intrinsics.areEqual((Object) (TypeName) next, (Object) CodeWriterKt.getNULLABLE_ANY())) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    public CodeWriter emit$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "out");
        return CodeWriter.emit$default(codeWriter, this.name, false, 2, (Object) null);
    }

    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J/\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000b0\u000fH\u0000¢\u0006\u0002\b\u0011J)\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000b0\u000fH\u0000¢\u0006\u0002\b\u0011J\"\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0002\b\nJ8\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u001c\"\u00020\u00072\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0004\b\n\u0010\u001dJ8\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u001c\"\u00020\u00102\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0004\b\n\u0010\u001eJ@\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u001a\u0010\u001b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u001f0\u001c\"\u0006\u0012\u0002\b\u00030\u001f2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0004\b\n\u0010 J0\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100!2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0002\b\"J4\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0010\u0010\u001b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f0!2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0002\b#J0\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0002\b\nJ-\u0010$\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0000¢\u0006\u0002\b%R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006&"}, d2 = {"Lcom/squareup/kotlinpoet/TypeVariableName$Companion;", "", "()V", "JAVA_OBJECT", "Lcom/squareup/kotlinpoet/ClassName;", "NULLABLE_ANY_LIST", "", "Lcom/squareup/kotlinpoet/TypeName;", "getNULLABLE_ANY_LIST$kotlinpoet", "()Ljava/util/List;", "get", "Lcom/squareup/kotlinpoet/TypeVariableName;", "type", "Ljava/lang/reflect/TypeVariable;", "map", "", "Ljava/lang/reflect/Type;", "get$kotlinpoet", "mirror", "Ljavax/lang/model/type/TypeVariable;", "typeVariables", "Ljavax/lang/model/element/TypeParameterElement;", "invoke", "name", "", "variance", "Lcom/squareup/kotlinpoet/KModifier;", "bounds", "", "(Ljava/lang/String;[Lcom/squareup/kotlinpoet/TypeName;Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/TypeVariableName;", "(Ljava/lang/String;[Ljava/lang/reflect/Type;Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/TypeVariableName;", "Lkotlin/reflect/KClass;", "(Ljava/lang/String;[Lkotlin/reflect/KClass;Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/TypeVariableName;", "", "getWithTypes", "getWithClasses", "of", "of$kotlinpoet", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: TypeVariableName.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final TypeVariableName get(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return get$default(this, str, (KModifier) null, 2, (Object) null);
        }

        @JvmStatic
        public final TypeVariableName get(String str, List<? extends TypeName> list) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(list, "bounds");
            return get$default(this, str, (List) list, (KModifier) null, 4, (Object) null);
        }

        @JvmStatic
        public final TypeVariableName get(String str, TypeName... typeNameArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeNameArr, "bounds");
            return get$default(this, str, typeNameArr, (KModifier) null, 4, (Object) null);
        }

        @JvmStatic
        public final TypeVariableName get(String str, Type... typeArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeArr, "bounds");
            return get$default(this, str, typeArr, (KModifier) null, 4, (Object) null);
        }

        @JvmStatic
        public final TypeVariableName get(String str, KClass<?>... kClassArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(kClassArr, "bounds");
            return get$default(this, str, (KClass[]) kClassArr, (KModifier) null, 4, (Object) null);
        }

        @JvmStatic
        public final TypeVariableName getWithClasses(String str, Iterable<? extends KClass<?>> iterable) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(iterable, "bounds");
            return getWithClasses$default(this, str, iterable, (KModifier) null, 4, (Object) null);
        }

        @JvmStatic
        public final TypeVariableName getWithTypes(String str, Iterable<? extends Type> iterable) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(iterable, "bounds");
            return getWithTypes$default(this, str, iterable, (KModifier) null, 4, (Object) null);
        }

        private Companion() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x005d  */
        /* JADX WARNING: Removed duplicated region for block: B:7:0x0025  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.squareup.kotlinpoet.TypeVariableName of$kotlinpoet(java.lang.String r12, java.util.List<? extends com.squareup.kotlinpoet.TypeName> r13, com.squareup.kotlinpoet.KModifier r14) {
            /*
                r11 = this;
                java.lang.String r0 = "name"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
                java.lang.String r0 = "bounds"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
                r0 = 1
                if (r14 == 0) goto L_0x0022
                com.squareup.kotlinpoet.KModifier r2 = com.squareup.kotlinpoet.KModifier.IN
                com.squareup.kotlinpoet.KModifier r3 = com.squareup.kotlinpoet.KModifier.OUT
                r4 = 0
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 60
                r9 = 0
                r1 = r14
                boolean r1 = com.squareup.kotlinpoet.UtilKt.isOneOf$default(r1, r2, r3, r4, r5, r6, r7, r8, r9)
                if (r1 == 0) goto L_0x0020
                goto L_0x0022
            L_0x0020:
                r1 = 0
                goto L_0x0023
            L_0x0022:
                r1 = r0
            L_0x0023:
                if (r1 == 0) goto L_0x005d
                r1 = r13
                java.util.Collection r1 = (java.util.Collection) r1
                boolean r1 = r1.isEmpty()
                r0 = r0 ^ r1
                if (r0 == 0) goto L_0x0040
                com.squareup.kotlinpoet.TypeVariableName r0 = new com.squareup.kotlinpoet.TypeVariableName
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                r9 = 120(0x78, float:1.68E-43)
                r10 = 0
                r1 = r0
                r2 = r12
                r3 = r13
                r4 = r14
                r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
                return r0
            L_0x0040:
                java.lang.StringBuilder r13 = new java.lang.StringBuilder
                r13.<init>()
                java.lang.StringBuilder r12 = r13.append(r12)
                java.lang.String r13 = " has no bounds"
                java.lang.StringBuilder r12 = r12.append(r13)
                java.lang.String r12 = r12.toString()
                java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
                java.lang.String r12 = r12.toString()
                r13.<init>(r12)
                throw r13
            L_0x005d:
                java.lang.StringBuilder r12 = new java.lang.StringBuilder
                r12.<init>()
                java.lang.StringBuilder r12 = r12.append(r14)
                java.lang.String r13 = " is an invalid variance modifier, the only allowed values are in and out!"
                java.lang.StringBuilder r12 = r12.append(r13)
                java.lang.String r12 = r12.toString()
                java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
                java.lang.String r12 = r12.toString()
                r13.<init>(r12)
                throw r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.TypeVariableName.Companion.of$kotlinpoet(java.lang.String, java.util.List, com.squareup.kotlinpoet.KModifier):com.squareup.kotlinpoet.TypeVariableName");
        }

        public static /* synthetic */ TypeVariableName get$default(Companion companion, String str, KModifier kModifier, int i, Object obj) {
            if ((i & 2) != 0) {
                kModifier = null;
            }
            return companion.get(str, kModifier);
        }

        @JvmStatic
        public final TypeVariableName get(String str, KModifier kModifier) {
            Intrinsics.checkNotNullParameter(str, "name");
            return of$kotlinpoet(str, getNULLABLE_ANY_LIST$kotlinpoet(), kModifier);
        }

        public static /* synthetic */ TypeVariableName get$default(Companion companion, String str, TypeName[] typeNameArr, KModifier kModifier, int i, Object obj) {
            if ((i & 4) != 0) {
                kModifier = null;
            }
            return companion.get(str, typeNameArr, kModifier);
        }

        @JvmStatic
        public final TypeVariableName get(String str, TypeName[] typeNameArr, KModifier kModifier) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeNameArr, "bounds");
            Collection list = ArraysKt.toList((T[]) typeNameArr);
            if (list.isEmpty()) {
                list = getNULLABLE_ANY_LIST$kotlinpoet();
            }
            return of$kotlinpoet(str, (List) list, kModifier);
        }

        public static /* synthetic */ TypeVariableName get$default(Companion companion, String str, KClass[] kClassArr, KModifier kModifier, int i, Object obj) {
            if ((i & 4) != 0) {
                kModifier = null;
            }
            return companion.get(str, (KClass<?>[]) kClassArr, kModifier);
        }

        public static /* synthetic */ TypeVariableName get$default(Companion companion, String str, Type[] typeArr, KModifier kModifier, int i, Object obj) {
            if ((i & 4) != 0) {
                kModifier = null;
            }
            return companion.get(str, typeArr, kModifier);
        }

        public static /* synthetic */ TypeVariableName get$default(Companion companion, String str, List list, KModifier kModifier, int i, Object obj) {
            if ((i & 4) != 0) {
                kModifier = null;
            }
            return companion.get(str, (List<? extends TypeName>) list, kModifier);
        }

        @JvmStatic
        public final TypeVariableName get(String str, List<? extends TypeName> list, KModifier kModifier) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(list, "bounds");
            Collection collection = list;
            if (collection.isEmpty()) {
                collection = getNULLABLE_ANY_LIST$kotlinpoet();
            }
            return of$kotlinpoet(str, (List) collection, kModifier);
        }

        public static /* synthetic */ TypeVariableName getWithClasses$default(Companion companion, String str, Iterable iterable, KModifier kModifier, int i, Object obj) {
            if ((i & 4) != 0) {
                kModifier = null;
            }
            return companion.getWithClasses(str, iterable, kModifier);
        }

        public static /* synthetic */ TypeVariableName getWithTypes$default(Companion companion, String str, Iterable iterable, KModifier kModifier, int i, Object obj) {
            if ((i & 4) != 0) {
                kModifier = null;
            }
            return companion.getWithTypes(str, iterable, kModifier);
        }

        public final TypeVariableName get$kotlinpoet(TypeVariable typeVariable, Map<TypeParameterElement, TypeVariableName> map) {
            Intrinsics.checkNotNullParameter(typeVariable, "mirror");
            Intrinsics.checkNotNullParameter(map, "typeVariables");
            TypeParameterElement asElement = typeVariable.asElement();
            Intrinsics.checkNotNull(asElement, "null cannot be cast to non-null type javax.lang.model.element.TypeParameterElement");
            TypeParameterElement typeParameterElement = asElement;
            TypeVariableName typeVariableName = map.get(typeParameterElement);
            if (typeVariableName != null) {
                return typeVariableName;
            }
            List arrayList = new ArrayList();
            List unmodifiableList = Collections.unmodifiableList(arrayList);
            String obj = typeParameterElement.getSimpleName().toString();
            Intrinsics.checkNotNullExpressionValue(unmodifiableList, "visibleBounds");
            TypeVariableName typeVariableName2 = new TypeVariableName(obj, unmodifiableList, (KModifier) null, false, false, (List) null, (Map) null, 124, (DefaultConstructorMarker) null);
            map.put(typeParameterElement, typeVariableName2);
            for (TypeMirror typeMirror : typeParameterElement.getBounds()) {
                TypeName.Companion companion = TypeName.Companion;
                Intrinsics.checkNotNullExpressionValue(typeMirror, "typeMirror");
                arrayList.add(companion.get$kotlinpoet(typeMirror, map));
            }
            arrayList.remove(TypeNames.ANY);
            arrayList.remove(TypeVariableName.JAVA_OBJECT);
            if (arrayList.isEmpty()) {
                arrayList.add(CodeWriterKt.getNULLABLE_ANY());
            }
            return typeVariableName2;
        }

        public static /* synthetic */ TypeVariableName get$kotlinpoet$default(Companion companion, java.lang.reflect.TypeVariable typeVariable, Map map, int i, Object obj) {
            if ((i & 2) != 0) {
                map = new LinkedHashMap();
            }
            return companion.get$kotlinpoet((java.lang.reflect.TypeVariable<?>) typeVariable, (Map<Type, TypeVariableName>) map);
        }

        public final TypeVariableName get$kotlinpoet(java.lang.reflect.TypeVariable<?> typeVariable, Map<Type, TypeVariableName> map) {
            Intrinsics.checkNotNullParameter(typeVariable, "type");
            Intrinsics.checkNotNullParameter(map, "map");
            TypeVariableName typeVariableName = map.get(typeVariable);
            if (typeVariableName != null) {
                return typeVariableName;
            }
            List arrayList = new ArrayList();
            List unmodifiableList = Collections.unmodifiableList(arrayList);
            String name = typeVariable.getName();
            Intrinsics.checkNotNullExpressionValue(name, "type.name");
            Intrinsics.checkNotNullExpressionValue(unmodifiableList, "visibleBounds");
            TypeVariableName typeVariableName2 = new TypeVariableName(name, unmodifiableList, (KModifier) null, false, false, (List) null, (Map) null, 124, (DefaultConstructorMarker) null);
            map.put(typeVariable, typeVariableName2);
            Type[] bounds = typeVariable.getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "type.bounds");
            for (Type type : bounds) {
                TypeName.Companion companion = TypeName.Companion;
                Intrinsics.checkNotNullExpressionValue(type, "bound");
                arrayList.add(companion.get$kotlinpoet(type, map));
            }
            arrayList.remove(TypeNames.ANY);
            arrayList.remove(TypeVariableName.JAVA_OBJECT);
            if (arrayList.isEmpty()) {
                arrayList.add(CodeWriterKt.getNULLABLE_ANY());
            }
            return typeVariableName2;
        }

        public final List<TypeName> getNULLABLE_ANY_LIST$kotlinpoet() {
            return TypeVariableName.NULLABLE_ANY_LIST;
        }

        @JvmStatic
        public final TypeVariableName get(String str, KClass<?>[] kClassArr, KModifier kModifier) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(kClassArr, "bounds");
            Collection arrayList = new ArrayList(kClassArr.length);
            for (KClass<?> kClass : kClassArr) {
                arrayList.add(TypeNames.get(kClass));
            }
            Collection collection = (List) arrayList;
            if (collection.isEmpty()) {
                collection = getNULLABLE_ANY_LIST$kotlinpoet();
            }
            return of$kotlinpoet(str, (List) collection, kModifier);
        }

        @JvmStatic
        public final TypeVariableName get(String str, Type[] typeArr, KModifier kModifier) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeArr, "bounds");
            Collection arrayList = new ArrayList(typeArr.length);
            for (Type type : typeArr) {
                arrayList.add(TypeNames.get(type));
            }
            Collection collection = (List) arrayList;
            if (collection.isEmpty()) {
                collection = getNULLABLE_ANY_LIST$kotlinpoet();
            }
            return of$kotlinpoet(str, (List) collection, kModifier);
        }

        @JvmStatic
        public final TypeVariableName getWithClasses(String str, Iterable<? extends KClass<?>> iterable, KModifier kModifier) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(iterable, "bounds");
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (KClass kClass : iterable) {
                arrayList.add(TypeNames.get((KClass<?>) kClass));
            }
            Collection collection = (List) arrayList;
            if (collection.isEmpty()) {
                collection = getNULLABLE_ANY_LIST$kotlinpoet();
            }
            return of$kotlinpoet(str, (List) collection, kModifier);
        }

        @JvmStatic
        public final TypeVariableName getWithTypes(String str, Iterable<? extends Type> iterable, KModifier kModifier) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(iterable, "bounds");
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Type type : iterable) {
                arrayList.add(TypeNames.get(type));
            }
            Collection collection = (List) arrayList;
            if (collection.isEmpty()) {
                collection = getNULLABLE_ANY_LIST$kotlinpoet();
            }
            return of$kotlinpoet(str, (List) collection, kModifier);
        }
    }
}
