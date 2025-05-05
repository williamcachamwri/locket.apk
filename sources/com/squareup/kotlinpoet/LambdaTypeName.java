package com.squareup.kotlinpoet;

import androidx.media3.extractor.ts.PsExtractor;
import com.google.firebase.dynamiclinks.DynamicLink;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 !2\u00020\u0001:\u0001!B{\b\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0001\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004\u0012\u0018\b\u0002\u0010\r\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\u0010\u0011JD\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\b\b\u0002\u0010\u001c\u001a\u00020\t2\u0018\b\u0002\u0010\r\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0004\u0012\u00020\u00100\u000eJ6\u0010\u001b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\u0016\u0010\r\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0004\u0012\u00020\u00100\u000eH\u0016J\u0015\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0010¢\u0006\u0002\b R\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u00048\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019¨\u0006\""}, d2 = {"Lcom/squareup/kotlinpoet/LambdaTypeName;", "Lcom/squareup/kotlinpoet/TypeName;", "receiver", "contextReceivers", "", "parameters", "Lcom/squareup/kotlinpoet/ParameterSpec;", "returnType", "nullable", "", "isSuspending", "annotations", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "tags", "", "Lkotlin/reflect/KClass;", "", "(Lcom/squareup/kotlinpoet/TypeName;Ljava/util/List;Ljava/util/List;Lcom/squareup/kotlinpoet/TypeName;ZZLjava/util/List;Ljava/util/Map;)V", "getContextReceivers$annotations", "()V", "getContextReceivers", "()Ljava/util/List;", "()Z", "getParameters", "getReceiver", "()Lcom/squareup/kotlinpoet/TypeName;", "getReturnType", "copy", "suspending", "emit", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: LambdaTypeName.kt */
public final class LambdaTypeName extends TypeName {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<TypeName> contextReceivers;
    private final boolean isSuspending;
    private final List<ParameterSpec> parameters;
    private final TypeName receiver;
    private final TypeName returnType;

    @JvmStatic
    public static final LambdaTypeName get(TypeName typeName, List<ParameterSpec> list, TypeName typeName2) {
        return Companion.get(typeName, list, typeName2);
    }

    @JvmStatic
    public static final LambdaTypeName get(TypeName typeName, List<ParameterSpec> list, TypeName typeName2, List<? extends TypeName> list2) {
        return Companion.get(typeName, list, typeName2, list2);
    }

    @JvmStatic
    public static final LambdaTypeName get(TypeName typeName, ParameterSpec[] parameterSpecArr, TypeName typeName2) {
        return Companion.get(typeName, parameterSpecArr, typeName2);
    }

    @JvmStatic
    public static final LambdaTypeName get(TypeName typeName, TypeName[] typeNameArr, TypeName typeName2) {
        return Companion.get(typeName, typeNameArr, typeName2);
    }

    public static /* synthetic */ void getContextReceivers$annotations() {
    }

    public final TypeName getReceiver() {
        return this.receiver;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* synthetic */ LambdaTypeName(com.squareup.kotlinpoet.TypeName r9, java.util.List r10, java.util.List r11, com.squareup.kotlinpoet.TypeName r12, boolean r13, boolean r14, java.util.List r15, java.util.Map r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
        /*
            r8 = this;
            r0 = r17
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0008
            r1 = 0
            goto L_0x0009
        L_0x0008:
            r1 = r9
        L_0x0009:
            r2 = r0 & 2
            if (r2 == 0) goto L_0x0012
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            goto L_0x0013
        L_0x0012:
            r2 = r10
        L_0x0013:
            r3 = r0 & 4
            if (r3 == 0) goto L_0x001c
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
            goto L_0x001d
        L_0x001c:
            r3 = r11
        L_0x001d:
            r4 = r0 & 8
            if (r4 == 0) goto L_0x0026
            com.squareup.kotlinpoet.ClassName r4 = com.squareup.kotlinpoet.TypeNames.UNIT
            com.squareup.kotlinpoet.TypeName r4 = (com.squareup.kotlinpoet.TypeName) r4
            goto L_0x0027
        L_0x0026:
            r4 = r12
        L_0x0027:
            r5 = r0 & 16
            r6 = 0
            if (r5 == 0) goto L_0x002e
            r5 = r6
            goto L_0x002f
        L_0x002e:
            r5 = r13
        L_0x002f:
            r7 = r0 & 32
            if (r7 == 0) goto L_0x0034
            goto L_0x0035
        L_0x0034:
            r6 = r14
        L_0x0035:
            r7 = r0 & 64
            if (r7 == 0) goto L_0x003e
            java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()
            goto L_0x003f
        L_0x003e:
            r7 = r15
        L_0x003f:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0048
            java.util.Map r0 = kotlin.collections.MapsKt.emptyMap()
            goto L_0x004a
        L_0x0048:
            r0 = r16
        L_0x004a:
            r9 = r8
            r10 = r1
            r11 = r2
            r12 = r3
            r13 = r4
            r14 = r5
            r15 = r6
            r16 = r7
            r17 = r0
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.LambdaTypeName.<init>(com.squareup.kotlinpoet.TypeName, java.util.List, java.util.List, com.squareup.kotlinpoet.TypeName, boolean, boolean, java.util.List, java.util.Map, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final List<TypeName> getContextReceivers() {
        return this.contextReceivers;
    }

    public final TypeName getReturnType() {
        return this.returnType;
    }

    public final boolean isSuspending() {
        return this.isSuspending;
    }

    private LambdaTypeName(TypeName typeName, List<? extends TypeName> list, List<ParameterSpec> list2, TypeName typeName2, boolean z, boolean z2, List<AnnotationSpec> list3, Map<KClass<?>, ? extends Object> map) {
        super(z, list3, new TagMap(map), (DefaultConstructorMarker) null);
        boolean z3;
        this.receiver = typeName;
        this.contextReceivers = list;
        this.returnType = typeName2;
        this.isSuspending = z2;
        this.parameters = UtilKt.toImmutableList(list2);
        for (ParameterSpec next : list2) {
            if (!next.getAnnotations().isEmpty()) {
                throw new IllegalArgumentException("Parameters with annotations are not allowed".toString());
            } else if (next.getModifiers().isEmpty()) {
                if (next.getDefaultValue() == null) {
                    z3 = true;
                    continue;
                } else {
                    z3 = false;
                    continue;
                }
                if (!z3) {
                    throw new IllegalArgumentException("Parameters with default values are not allowed".toString());
                }
            } else {
                throw new IllegalArgumentException("Parameters with modifiers are not allowed".toString());
            }
        }
    }

    public final List<ParameterSpec> getParameters() {
        return this.parameters;
    }

    public LambdaTypeName copy(boolean z, List<AnnotationSpec> list, Map<KClass<?>, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(list, "annotations");
        Intrinsics.checkNotNullParameter(map, "tags");
        return copy(z, list, this.isSuspending, map);
    }

    public static /* synthetic */ LambdaTypeName copy$default(LambdaTypeName lambdaTypeName, boolean z, List list, boolean z2, Map<KClass<?>, Object> map, int i, Object obj) {
        if ((i & 1) != 0) {
            z = lambdaTypeName.isNullable();
        }
        if ((i & 2) != 0) {
            list = CollectionsKt.toList(lambdaTypeName.getAnnotations());
        }
        if ((i & 4) != 0) {
            z2 = lambdaTypeName.isSuspending;
        }
        if ((i & 8) != 0) {
            map = MapsKt.toMap(lambdaTypeName.getTags());
        }
        return lambdaTypeName.copy(z, list, z2, map);
    }

    public final LambdaTypeName copy(boolean z, List<AnnotationSpec> list, boolean z2, Map<KClass<?>, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(list, "annotations");
        Intrinsics.checkNotNullParameter(map, "tags");
        return new LambdaTypeName(this.receiver, this.contextReceivers, this.parameters, this.returnType, z, z2, list, map);
    }

    public CodeWriter emit$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "out");
        codeWriter.emitContextReceivers(this.contextReceivers, "·");
        if (isNullable()) {
            CodeWriter.emit$default(codeWriter, "(", false, 2, (Object) null);
        }
        if (this.isSuspending) {
            CodeWriter.emit$default(codeWriter, "suspend·", false, 2, (Object) null);
        }
        TypeName typeName = this.receiver;
        if (typeName != null) {
            if (typeName.isAnnotated()) {
                codeWriter.emitCode("(%T).", typeName);
            } else {
                codeWriter.emitCode("%T.", typeName);
            }
        }
        ParameterSpecKt.emit$default(this.parameters, codeWriter, false, (Function1) null, 6, (Object) null);
        TypeName typeName2 = this.returnType;
        codeWriter.emitCode(typeName2 instanceof LambdaTypeName ? "·->·(%T)" : "·->·%T", typeName2);
        if (isNullable()) {
            CodeWriter.emit$default(codeWriter, ")", false, 2, (Object) null);
        }
        return codeWriter;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J7\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u000bJ7\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0014\b\u0002\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\b\"\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\fJ,\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\n\u001a\u00020\u0006H\u0007J<\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\n\u001a\u00020\u00062\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0007¨\u0006\u000f"}, d2 = {"Lcom/squareup/kotlinpoet/LambdaTypeName$Companion;", "", "()V", "get", "Lcom/squareup/kotlinpoet/LambdaTypeName;", "receiver", "Lcom/squareup/kotlinpoet/TypeName;", "parameters", "", "Lcom/squareup/kotlinpoet/ParameterSpec;", "returnType", "(Lcom/squareup/kotlinpoet/TypeName;[Lcom/squareup/kotlinpoet/ParameterSpec;Lcom/squareup/kotlinpoet/TypeName;)Lcom/squareup/kotlinpoet/LambdaTypeName;", "(Lcom/squareup/kotlinpoet/TypeName;[Lcom/squareup/kotlinpoet/TypeName;Lcom/squareup/kotlinpoet/TypeName;)Lcom/squareup/kotlinpoet/LambdaTypeName;", "", "contextReceivers", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: LambdaTypeName.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ LambdaTypeName get$default(Companion companion, TypeName typeName, List list, TypeName typeName2, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                typeName = null;
            }
            if ((i & 2) != 0) {
                list = CollectionsKt.emptyList();
            }
            if ((i & 8) != 0) {
                list2 = CollectionsKt.emptyList();
            }
            return companion.get(typeName, list, typeName2, list2);
        }

        @JvmStatic
        public final LambdaTypeName get(TypeName typeName, List<ParameterSpec> list, TypeName typeName2, List<? extends TypeName> list2) {
            Intrinsics.checkNotNullParameter(list, DynamicLink.Builder.KEY_DYNAMIC_LINK_PARAMETERS);
            Intrinsics.checkNotNullParameter(typeName2, "returnType");
            List<? extends TypeName> list3 = list2;
            Intrinsics.checkNotNullParameter(list3, "contextReceivers");
            return new LambdaTypeName(typeName, list3, list, typeName2, false, false, (List) null, (Map) null, PsExtractor.VIDEO_STREAM_MASK, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ LambdaTypeName get$default(Companion companion, TypeName typeName, List list, TypeName typeName2, int i, Object obj) {
            if ((i & 1) != 0) {
                typeName = null;
            }
            if ((i & 2) != 0) {
                list = CollectionsKt.emptyList();
            }
            return companion.get(typeName, (List<ParameterSpec>) list, typeName2);
        }

        @JvmStatic
        public final LambdaTypeName get(TypeName typeName, List<ParameterSpec> list, TypeName typeName2) {
            Intrinsics.checkNotNullParameter(list, DynamicLink.Builder.KEY_DYNAMIC_LINK_PARAMETERS);
            Intrinsics.checkNotNullParameter(typeName2, "returnType");
            return new LambdaTypeName(typeName, CollectionsKt.emptyList(), list, typeName2, false, false, (List) null, (Map) null, PsExtractor.VIDEO_STREAM_MASK, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final LambdaTypeName get(TypeName typeName, TypeName[] typeNameArr, TypeName typeName2) {
            Intrinsics.checkNotNullParameter(typeNameArr, DynamicLink.Builder.KEY_DYNAMIC_LINK_PARAMETERS);
            Intrinsics.checkNotNullParameter(typeName2, "returnType");
            List emptyList = CollectionsKt.emptyList();
            Iterable<TypeName> list = ArraysKt.toList((T[]) typeNameArr);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (TypeName unnamed : list) {
                arrayList.add(ParameterSpec.Companion.unnamed(unnamed));
            }
            return new LambdaTypeName(typeName, emptyList, (List) arrayList, typeName2, false, false, (List) null, (Map) null, PsExtractor.VIDEO_STREAM_MASK, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final LambdaTypeName get(TypeName typeName, ParameterSpec[] parameterSpecArr, TypeName typeName2) {
            Intrinsics.checkNotNullParameter(parameterSpecArr, DynamicLink.Builder.KEY_DYNAMIC_LINK_PARAMETERS);
            Intrinsics.checkNotNullParameter(typeName2, "returnType");
            return new LambdaTypeName(typeName, CollectionsKt.emptyList(), ArraysKt.toList((T[]) parameterSpecArr), typeName2, false, false, (List) null, (Map) null, PsExtractor.VIDEO_STREAM_MASK, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ LambdaTypeName get$default(Companion companion, TypeName typeName, TypeName[] typeNameArr, TypeName typeName2, int i, Object obj) {
            if ((i & 1) != 0) {
                typeName = null;
            }
            if ((i & 2) != 0) {
                typeNameArr = (TypeName[]) ((Object[]) new TypeName[0]);
            }
            return companion.get(typeName, typeNameArr, typeName2);
        }

        public static /* synthetic */ LambdaTypeName get$default(Companion companion, TypeName typeName, ParameterSpec[] parameterSpecArr, TypeName typeName2, int i, Object obj) {
            if ((i & 1) != 0) {
                typeName = null;
            }
            if ((i & 2) != 0) {
                parameterSpecArr = (ParameterSpec[]) ((Object[]) new ParameterSpec[0]);
            }
            return companion.get(typeName, parameterSpecArr, typeName2);
        }
    }
}
