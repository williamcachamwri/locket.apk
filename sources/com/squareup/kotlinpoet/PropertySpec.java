package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.KModifier;
import com.squareup.kotlinpoet.OriginatingElementsHolder;
import com.squareup.kotlinpoet.Taggable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 Y2\u00020\u00012\u00020\u0002:\u0002XYB#\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002¢\u0006\u0002\u0010\bJK\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020#0\"2\b\b\u0002\u0010D\u001a\u00020\u00142\b\b\u0002\u0010E\u001a\u00020\u00142\b\b\u0002\u0010F\u001a\u00020\u00142\b\b\u0002\u0010G\u001a\u00020\u0014H\u0000¢\u0006\u0002\bHJ\u0013\u0010I\u001a\u00020\u00142\b\u0010J\u001a\u0004\u0018\u000107H\u0002J\u0015\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020MH\u0000¢\u0006\u0002\bNJ\b\u0010O\u001a\u00020PH\u0016J(\u0010Q\u001a\u0004\u0018\u0001HR\"\b\b\u0000\u0010R*\u0002072\f\u0010:\u001a\b\u0012\u0004\u0012\u0002HR0SH\u0001¢\u0006\u0002\u0010TJ(\u0010Q\u001a\u0004\u0018\u0001HR\"\b\b\u0000\u0010R*\u0002072\f\u0010:\u001a\b\u0012\u0004\u0012\u0002HR06H\u0001¢\u0006\u0002\u0010UJ\u001c\u0010V\u001a\u00020\u00042\b\b\u0002\u0010(\u001a\u00020)2\b\b\u0002\u0010:\u001a\u00020\u000fH\u0007J\b\u0010W\u001a\u00020)H\u0016R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\rR\u000e\u0010\u0007\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0016R\u0011\u0010(\u001a\u00020)¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0018\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\nX\u0005¢\u0006\u0006\u001a\u0004\b.\u0010\rR\u0013\u0010/\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0013\u00102\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001aR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R$\u00104\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u000306\u0012\u0004\u0012\u000207058VX\u0005¢\u0006\u0006\u001a\u0004\b8\u00109R\u0011\u0010:\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b;\u00101R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\n¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\r¨\u0006Z"}, d2 = {"Lcom/squareup/kotlinpoet/PropertySpec;", "Lcom/squareup/kotlinpoet/Taggable;", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder;", "builder", "Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "tagMap", "Lcom/squareup/kotlinpoet/TagMap;", "delegateOriginatingElementsHolder", "(Lcom/squareup/kotlinpoet/PropertySpec$Builder;Lcom/squareup/kotlinpoet/TagMap;Lcom/squareup/kotlinpoet/OriginatingElementsHolder;)V", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotations", "()Ljava/util/List;", "contextReceiverTypes", "Lcom/squareup/kotlinpoet/TypeName;", "getContextReceiverTypes$annotations", "()V", "getContextReceiverTypes", "delegated", "", "getDelegated", "()Z", "getter", "Lcom/squareup/kotlinpoet/FunSpec;", "getGetter", "()Lcom/squareup/kotlinpoet/FunSpec;", "initializer", "Lcom/squareup/kotlinpoet/CodeBlock;", "getInitializer", "()Lcom/squareup/kotlinpoet/CodeBlock;", "kdoc", "getKdoc", "modifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "getModifiers", "()Ljava/util/Set;", "mutable", "getMutable", "name", "", "getName", "()Ljava/lang/String;", "originatingElements", "Ljavax/lang/model/element/Element;", "getOriginatingElements", "receiverType", "getReceiverType", "()Lcom/squareup/kotlinpoet/TypeName;", "setter", "getSetter", "tags", "", "Lkotlin/reflect/KClass;", "", "getTags", "()Ljava/util/Map;", "type", "getType", "typeVariables", "Lcom/squareup/kotlinpoet/TypeVariableName;", "getTypeVariables", "emit", "", "codeWriter", "Lcom/squareup/kotlinpoet/CodeWriter;", "implicitModifiers", "withInitializer", "emitKdoc", "inline", "inlineAnnotations", "emit$kotlinpoet", "equals", "other", "fromPrimaryConstructorParameter", "parameter", "Lcom/squareup/kotlinpoet/ParameterSpec;", "fromPrimaryConstructorParameter$kotlinpoet", "hashCode", "", "tag", "T", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toBuilder", "toString", "Builder", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: PropertySpec.kt */
public final class PropertySpec implements Taggable, OriginatingElementsHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<AnnotationSpec> annotations;
    private final List<TypeName> contextReceiverTypes;
    private final OriginatingElementsHolder delegateOriginatingElementsHolder;
    private final boolean delegated;
    private final FunSpec getter;
    private final CodeBlock initializer;
    private final CodeBlock kdoc;
    private final Set<KModifier> modifiers;
    private final boolean mutable;
    private final String name;
    private final TypeName receiverType;
    private final FunSpec setter;
    private final TagMap tagMap;
    private final TypeName type;
    private final List<TypeVariableName> typeVariables;

    @JvmStatic
    public static final Builder builder(String str, TypeName typeName, Iterable<? extends KModifier> iterable) {
        return Companion.builder(str, typeName, iterable);
    }

    @JvmStatic
    public static final Builder builder(String str, TypeName typeName, KModifier... kModifierArr) {
        return Companion.builder(str, typeName, kModifierArr);
    }

    @JvmStatic
    public static final Builder builder(String str, Type type2, Iterable<? extends KModifier> iterable) {
        return Companion.builder(str, type2, iterable);
    }

    @JvmStatic
    public static final Builder builder(String str, Type type2, KModifier... kModifierArr) {
        return Companion.builder(str, type2, kModifierArr);
    }

    @JvmStatic
    public static final Builder builder(String str, KClass<?> kClass, Iterable<? extends KModifier> iterable) {
        return Companion.builder(str, kClass, iterable);
    }

    @JvmStatic
    public static final Builder builder(String str, KClass<?> kClass, KModifier... kModifierArr) {
        return Companion.builder(str, kClass, kModifierArr);
    }

    public static /* synthetic */ void getContextReceiverTypes$annotations() {
    }

    public List<Element> getOriginatingElements() {
        return this.delegateOriginatingElementsHolder.getOriginatingElements();
    }

    public Map<KClass<?>, Object> getTags() {
        return this.tagMap.getTags();
    }

    public <T> T tag(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "type");
        return this.tagMap.tag(cls);
    }

    public <T> T tag(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "type");
        return this.tagMap.tag(kClass);
    }

    public final Builder toBuilder() {
        return toBuilder$default(this, (String) null, (TypeName) null, 3, (Object) null);
    }

    public final Builder toBuilder(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return toBuilder$default(this, str, (TypeName) null, 2, (Object) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x009d, code lost:
        r2 = r1.getter;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00b3, code lost:
        r2 = r1.setter;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private PropertySpec(com.squareup.kotlinpoet.PropertySpec.Builder r2, com.squareup.kotlinpoet.TagMap r3, com.squareup.kotlinpoet.OriginatingElementsHolder r4) {
        /*
            r1 = this;
            r1.<init>()
            r1.tagMap = r3
            r1.delegateOriginatingElementsHolder = r4
            boolean r3 = r2.getMutable$kotlinpoet()
            r1.mutable = r3
            java.lang.String r3 = r2.getName$kotlinpoet()
            r1.name = r3
            com.squareup.kotlinpoet.TypeName r3 = r2.getType$kotlinpoet()
            r1.type = r3
            com.squareup.kotlinpoet.CodeBlock$Builder r3 = r2.getKdoc$kotlinpoet()
            com.squareup.kotlinpoet.CodeBlock r3 = r3.build()
            r1.kdoc = r3
            java.util.List r3 = r2.getAnnotations()
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.List r3 = com.squareup.kotlinpoet.UtilKt.toImmutableList(r3)
            r1.annotations = r3
            java.util.List r3 = r2.getModifiers()
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Set r3 = com.squareup.kotlinpoet.UtilKt.toImmutableSet(r3)
            r1.modifiers = r3
            java.util.List r3 = r2.getTypeVariables()
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.List r3 = com.squareup.kotlinpoet.UtilKt.toImmutableList(r3)
            r1.typeVariables = r3
            com.squareup.kotlinpoet.CodeBlock r4 = r2.getInitializer$kotlinpoet()
            r1.initializer = r4
            boolean r4 = r2.getDelegated$kotlinpoet()
            r1.delegated = r4
            com.squareup.kotlinpoet.FunSpec r4 = r2.getGetter$kotlinpoet()
            r1.getter = r4
            com.squareup.kotlinpoet.FunSpec r4 = r2.getSetter$kotlinpoet()
            r1.setter = r4
            com.squareup.kotlinpoet.TypeName r4 = r2.getReceiverType$kotlinpoet()
            r1.receiverType = r4
            java.util.List r2 = r2.getContextReceiverTypes$kotlinpoet()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.List r2 = com.squareup.kotlinpoet.UtilKt.toImmutableList(r2)
            r1.contextReceiverTypes = r2
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            boolean r2 = r3 instanceof java.util.Collection
            r4 = 0
            r0 = 1
            if (r2 == 0) goto L_0x0084
            r2 = r3
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0084
        L_0x0082:
            r2 = r0
            goto L_0x009b
        L_0x0084:
            java.util.Iterator r2 = r3.iterator()
        L_0x0088:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0082
            java.lang.Object r3 = r2.next()
            com.squareup.kotlinpoet.TypeVariableName r3 = (com.squareup.kotlinpoet.TypeVariableName) r3
            boolean r3 = r3.isReified()
            if (r3 == 0) goto L_0x0088
            r2 = r4
        L_0x009b:
            if (r2 != 0) goto L_0x00c6
            com.squareup.kotlinpoet.FunSpec r2 = r1.getter
            if (r2 != 0) goto L_0x00a5
            com.squareup.kotlinpoet.FunSpec r3 = r1.setter
            if (r3 == 0) goto L_0x00c4
        L_0x00a5:
            if (r2 == 0) goto L_0x00b3
            java.util.Set r2 = r2.getModifiers()
            com.squareup.kotlinpoet.KModifier r3 = com.squareup.kotlinpoet.KModifier.INLINE
            boolean r2 = r2.contains(r3)
            if (r2 == 0) goto L_0x00c4
        L_0x00b3:
            com.squareup.kotlinpoet.FunSpec r2 = r1.setter
            if (r2 == 0) goto L_0x00c6
            java.util.Set r2 = r2.getModifiers()
            com.squareup.kotlinpoet.KModifier r3 = com.squareup.kotlinpoet.KModifier.INLINE
            boolean r2 = r2.contains(r3)
            if (r2 == 0) goto L_0x00c4
            goto L_0x00c6
        L_0x00c4:
            r2 = r4
            goto L_0x00c7
        L_0x00c6:
            r2 = r0
        L_0x00c7:
            if (r2 == 0) goto L_0x0111
            boolean r2 = r1.mutable
            if (r2 != 0) goto L_0x00d1
            com.squareup.kotlinpoet.FunSpec r2 = r1.setter
            if (r2 != 0) goto L_0x00d2
        L_0x00d1:
            r4 = r0
        L_0x00d2:
            if (r4 == 0) goto L_0x0105
            java.util.List<com.squareup.kotlinpoet.TypeName> r2 = r1.contextReceiverTypes
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            r2 = r2 ^ r0
            if (r2 == 0) goto L_0x0104
            com.squareup.kotlinpoet.FunSpec r2 = r1.getter
            if (r2 == 0) goto L_0x00f8
            boolean r2 = r1.mutable
            if (r2 == 0) goto L_0x0104
            com.squareup.kotlinpoet.FunSpec r2 = r1.setter
            if (r2 == 0) goto L_0x00ec
            goto L_0x0104
        L_0x00ec:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "mutable properties with context receivers require a set()"
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L_0x00f8:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "properties with context receivers require a get()"
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L_0x0104:
            return
        L_0x0105:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "only a mutable property can have a setter"
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L_0x0111:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "only type parameters of properties with inline getters and/or setters can be reified!"
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.PropertySpec.<init>(com.squareup.kotlinpoet.PropertySpec$Builder, com.squareup.kotlinpoet.TagMap, com.squareup.kotlinpoet.OriginatingElementsHolder):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ PropertySpec(Builder builder, TagMap tagMap2, OriginatingElementsHolder originatingElementsHolder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder, (i & 2) != 0 ? TaggableKt.buildTagMap(builder) : tagMap2, (i & 4) != 0 ? OriginatingElementsHolderKt.buildOriginatingElements((OriginatingElementsHolder.Builder<?>) builder) : originatingElementsHolder);
    }

    public final boolean getMutable() {
        return this.mutable;
    }

    public final String getName() {
        return this.name;
    }

    public final TypeName getType() {
        return this.type;
    }

    public final CodeBlock getKdoc() {
        return this.kdoc;
    }

    public final List<AnnotationSpec> getAnnotations() {
        return this.annotations;
    }

    public final Set<KModifier> getModifiers() {
        return this.modifiers;
    }

    public final List<TypeVariableName> getTypeVariables() {
        return this.typeVariables;
    }

    public final CodeBlock getInitializer() {
        return this.initializer;
    }

    public final boolean getDelegated() {
        return this.delegated;
    }

    public final FunSpec getGetter() {
        return this.getter;
    }

    public final FunSpec getSetter() {
        return this.setter;
    }

    public final TypeName getReceiverType() {
        return this.receiverType;
    }

    public final List<TypeName> getContextReceiverTypes() {
        return this.contextReceiverTypes;
    }

    public static /* synthetic */ void emit$kotlinpoet$default(PropertySpec propertySpec, CodeWriter codeWriter, Set set, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        boolean z5 = (i & 4) != 0 ? true : z;
        boolean z6 = (i & 8) != 0 ? true : z2;
        boolean z7 = (i & 16) != 0 ? false : z3;
        propertySpec.emit$kotlinpoet(codeWriter, set, z5, z6, z7, (i & 32) != 0 ? z7 : z4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        if (((r1 == null || (r1 = r1.getModifiers()) == null) ? false : r1.contains(com.squareup.kotlinpoet.KModifier.INLINE)) != false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0013, code lost:
        r1 = r1.getModifiers();
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void emit$kotlinpoet(com.squareup.kotlinpoet.CodeWriter r15, java.util.Set<? extends com.squareup.kotlinpoet.KModifier> r16, boolean r17, boolean r18, boolean r19, boolean r20) {
        /*
            r14 = this;
            r0 = r14
            r7 = r15
            r8 = r16
            java.lang.String r1 = "codeWriter"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r1)
            java.lang.String r1 = "implicitModifiers"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r1)
            com.squareup.kotlinpoet.FunSpec r1 = r0.getter
            r9 = 0
            if (r1 == 0) goto L_0x0020
            java.util.Set r1 = r1.getModifiers()
            if (r1 == 0) goto L_0x0020
            com.squareup.kotlinpoet.KModifier r2 = com.squareup.kotlinpoet.KModifier.INLINE
            boolean r1 = r1.contains(r2)
            goto L_0x0021
        L_0x0020:
            r1 = r9
        L_0x0021:
            r2 = 1
            if (r1 == 0) goto L_0x003e
            boolean r1 = r0.mutable
            if (r1 == 0) goto L_0x003c
            com.squareup.kotlinpoet.FunSpec r1 = r0.setter
            if (r1 == 0) goto L_0x0039
            java.util.Set r1 = r1.getModifiers()
            if (r1 == 0) goto L_0x0039
            com.squareup.kotlinpoet.KModifier r3 = com.squareup.kotlinpoet.KModifier.INLINE
            boolean r1 = r1.contains(r3)
            goto L_0x003a
        L_0x0039:
            r1 = r9
        L_0x003a:
            if (r1 == 0) goto L_0x003e
        L_0x003c:
            r10 = r2
            goto L_0x003f
        L_0x003e:
            r10 = r9
        L_0x003f:
            java.util.Set<com.squareup.kotlinpoet.KModifier> r1 = r0.modifiers
            if (r10 == 0) goto L_0x0049
            com.squareup.kotlinpoet.KModifier r3 = com.squareup.kotlinpoet.KModifier.INLINE
            java.util.Set r1 = kotlin.collections.SetsKt.plus(r1, r3)
        L_0x0049:
            if (r18 == 0) goto L_0x0054
            com.squareup.kotlinpoet.CodeBlock r3 = r0.kdoc
            com.squareup.kotlinpoet.CodeBlock r3 = com.squareup.kotlinpoet.UtilKt.ensureEndsWithNewLine(r3)
            r15.emitKdoc(r3)
        L_0x0054:
            java.util.List<com.squareup.kotlinpoet.TypeName> r3 = r0.contextReceiverTypes
            java.lang.String r11 = "\n"
            r15.emitContextReceivers(r3, r11)
            java.util.List<com.squareup.kotlinpoet.AnnotationSpec> r3 = r0.annotations
            r4 = r20
            r15.emitAnnotations(r3, r4)
            r15.emitModifiers(r1, r8)
            boolean r1 = r0.mutable
            if (r1 == 0) goto L_0x006c
            java.lang.String r1 = "var·"
            goto L_0x006e
        L_0x006c:
            java.lang.String r1 = "val·"
        L_0x006e:
            r15.emitCode(r1)
            java.util.List<com.squareup.kotlinpoet.TypeVariableName> r1 = r0.typeVariables
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ r2
            r12 = 2
            r13 = 0
            if (r1 == 0) goto L_0x0088
            java.util.List<com.squareup.kotlinpoet.TypeVariableName> r1 = r0.typeVariables
            r15.emitTypeVariables(r1)
            java.lang.String r1 = " "
            com.squareup.kotlinpoet.CodeWriter.emit$default(r15, r1, r9, r12, r13)
        L_0x0088:
            com.squareup.kotlinpoet.TypeName r1 = r0.receiverType
            if (r1 == 0) goto L_0x00a3
            boolean r2 = r1 instanceof com.squareup.kotlinpoet.LambdaTypeName
            if (r2 == 0) goto L_0x009a
            java.lang.String r2 = "(%T)."
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            r15.emitCode(r2, r1)
            goto L_0x00a3
        L_0x009a:
            java.lang.String r2 = "%T."
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            r15.emitCode(r2, r1)
        L_0x00a3:
            com.squareup.kotlinpoet.TypeName r1 = r0.type
            java.lang.Object[] r1 = new java.lang.Object[]{r14, r1}
            java.lang.String r2 = "%N: %T"
            r15.emitCode(r2, r1)
            if (r17 == 0) goto L_0x00eb
            com.squareup.kotlinpoet.CodeBlock r1 = r0.initializer
            if (r1 == 0) goto L_0x00eb
            boolean r1 = r0.delegated
            if (r1 == 0) goto L_0x00be
            java.lang.String r1 = " by "
            com.squareup.kotlinpoet.CodeWriter.emit$default(r15, r1, r9, r12, r13)
            goto L_0x00c3
        L_0x00be:
            java.lang.String r1 = " = "
            r15.emitCode(r1)
        L_0x00c3:
            com.squareup.kotlinpoet.CodeBlock r1 = r0.initializer
            boolean r1 = r1.hasStatements$kotlinpoet()
            if (r1 == 0) goto L_0x00ce
            java.lang.String r1 = "%L"
            goto L_0x00d0
        L_0x00ce:
            java.lang.String r1 = "«%L»"
        L_0x00d0:
            com.squareup.kotlinpoet.CodeBlock$Companion r2 = com.squareup.kotlinpoet.CodeBlock.Companion
            com.squareup.kotlinpoet.CodeBlock r3 = r0.initializer
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.squareup.kotlinpoet.CodeBlock r2 = r2.of(r1, r3)
            java.util.Set<com.squareup.kotlinpoet.KModifier> r1 = r0.modifiers
            com.squareup.kotlinpoet.KModifier r3 = com.squareup.kotlinpoet.KModifier.CONST
            boolean r3 = r1.contains(r3)
            r4 = 0
            r5 = 4
            r6 = 0
            r1 = r15
            com.squareup.kotlinpoet.CodeWriter.emitCode$default(r1, r2, r3, r4, r5, r6)
        L_0x00eb:
            java.util.List<com.squareup.kotlinpoet.TypeVariableName> r1 = r0.typeVariables
            r15.emitWhereBlock(r1)
            if (r19 != 0) goto L_0x00f5
            com.squareup.kotlinpoet.CodeWriter.emit$default(r15, r11, r9, r12, r13)
        L_0x00f5:
            java.lang.Class<com.squareup.kotlinpoet.KModifier> r1 = com.squareup.kotlinpoet.KModifier.class
            java.util.EnumSet r1 = java.util.EnumSet.noneOf(r1)
            java.util.Iterator r2 = r16.iterator()
        L_0x00ff:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0119
            java.lang.Object r3 = r2.next()
            com.squareup.kotlinpoet.KModifier r3 = (com.squareup.kotlinpoet.KModifier) r3
            java.util.Set r4 = com.squareup.kotlinpoet.KModifierKt.getVISIBILITY_MODIFIERS()
            boolean r4 = r4.contains(r3)
            if (r4 != 0) goto L_0x00ff
            r1.add(r3)
            goto L_0x00ff
        L_0x0119:
            if (r10 == 0) goto L_0x0120
            com.squareup.kotlinpoet.KModifier r2 = com.squareup.kotlinpoet.KModifier.INLINE
            r1.add(r2)
        L_0x0120:
            com.squareup.kotlinpoet.FunSpec r2 = r0.getter
            java.lang.String r3 = "⇤"
            java.lang.String r4 = "implicitAccessorModifiers"
            java.lang.String r5 = "⇥"
            if (r2 == 0) goto L_0x013b
            r15.emitCode(r5)
            com.squareup.kotlinpoet.FunSpec r2 = r0.getter
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            r6 = r1
            java.util.Set r6 = (java.util.Set) r6
            r2.emit$kotlinpoet(r15, r13, r6, r9)
            r15.emitCode(r3)
        L_0x013b:
            com.squareup.kotlinpoet.FunSpec r2 = r0.setter
            if (r2 == 0) goto L_0x014f
            r15.emitCode(r5)
            com.squareup.kotlinpoet.FunSpec r2 = r0.setter
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            java.util.Set r1 = (java.util.Set) r1
            r2.emit$kotlinpoet(r15, r13, r1, r9)
            r15.emitCode(r3)
        L_0x014f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.PropertySpec.emit$kotlinpoet(com.squareup.kotlinpoet.CodeWriter, java.util.Set, boolean, boolean, boolean, boolean):void");
    }

    public final PropertySpec fromPrimaryConstructorParameter$kotlinpoet(ParameterSpec parameterSpec) {
        Intrinsics.checkNotNullParameter(parameterSpec, "parameter");
        Builder addAnnotations = toBuilder$default(this, (String) null, (TypeName) null, 3, (Object) null).addAnnotations(parameterSpec.getAnnotations());
        addAnnotations.setPrimaryConstructorParameter$kotlinpoet(true);
        CollectionsKt.addAll(addAnnotations.getModifiers(), parameterSpec.getModifiers());
        if (addAnnotations.getKdoc$kotlinpoet().isEmpty()) {
            addAnnotations.addKdoc(parameterSpec.getKdoc());
        }
        return addAnnotations.build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Intrinsics.areEqual((Object) getClass(), (Object) obj.getClass())) {
            return Intrinsics.areEqual((Object) toString(), (Object) obj.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public static /* synthetic */ Builder toBuilder$default(PropertySpec propertySpec, String str, TypeName typeName, int i, Object obj) {
        if ((i & 1) != 0) {
            str = propertySpec.name;
        }
        if ((i & 2) != 0) {
            typeName = propertySpec.type;
        }
        return propertySpec.toBuilder(str, typeName);
    }

    public final Builder toBuilder(String str, TypeName typeName) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(typeName, "type");
        Builder builder = new Builder(str, typeName);
        builder.setMutable$kotlinpoet(this.mutable);
        builder.getKdoc$kotlinpoet().add(this.kdoc);
        CollectionsKt.addAll(builder.getAnnotations(), this.annotations);
        CollectionsKt.addAll(builder.getModifiers(), this.modifiers);
        CollectionsKt.addAll(builder.getTypeVariables(), this.typeVariables);
        builder.setInitializer$kotlinpoet(this.initializer);
        builder.setDelegated$kotlinpoet(this.delegated);
        builder.setSetter$kotlinpoet(this.setter);
        builder.setGetter$kotlinpoet(this.getter);
        builder.setReceiverType$kotlinpoet(this.receiverType);
        builder.getTags().putAll(this.tagMap.getTags());
        CollectionsKt.addAll(builder.getOriginatingElements(), getOriginatingElements());
        return builder;
    }

    @Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010E\u001a\u00020\u00002\u0006\u0010F\u001a\u00020\nJ\u000e\u0010E\u001a\u00020\u00002\u0006\u0010G\u001a\u00020HJ\u0014\u0010E\u001a\u00020\u00002\n\u0010G\u001a\u0006\u0012\u0002\b\u00030IH\u0007J\u0012\u0010E\u001a\u00020\u00002\n\u0010G\u001a\u0006\u0012\u0002\b\u00030=J\u0014\u0010J\u001a\u00020\u00002\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\n0LJ\u000e\u0010M\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u001cJ'\u0010M\u001a\u00020\u00002\u0006\u0010O\u001a\u00020\u00042\u0012\u0010P\u001a\n\u0012\u0006\b\u0001\u0012\u00020>0Q\"\u00020>¢\u0006\u0002\u0010RJ\u001f\u0010S\u001a\u00020\u00002\u0012\u0010(\u001a\n\u0012\u0006\b\u0001\u0012\u00020)0Q\"\u00020)¢\u0006\u0002\u0010TJ\u0014\u0010S\u001a\u00020\u00002\f\u0010(\u001a\b\u0012\u0004\u0012\u00020)0LJ\u000e\u0010U\u001a\u00020\u00002\u0006\u0010V\u001a\u00020CJ\u0014\u0010W\u001a\u00020\u00002\f\u0010B\u001a\b\u0012\u0004\u0012\u00020C0LJ\u0006\u0010X\u001a\u00020YJ!\u0010Z\u001a\u00020\u00002\u0012\u00103\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060Q\"\u00020\u0006H\u0007¢\u0006\u0002\u0010[J\u0016\u0010Z\u001a\u00020\u00002\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00060LH\u0007J\u000e\u0010]\u001a\u00020\u00002\u0006\u0010^\u001a\u00020\u001cJ+\u0010]\u001a\u00020\u00002\u0006\u0010O\u001a\u00020\u00042\u0016\u0010P\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010>0Q\"\u0004\u0018\u00010>¢\u0006\u0002\u0010RJ\u0010\u0010\u0015\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u0010\u0010\u001b\u001a\u00020\u00002\b\u0010^\u001a\u0004\u0018\u00010\u001cJ+\u0010\u001b\u001a\u00020\u00002\u0006\u0010O\u001a\u00020\u00042\u0016\u0010P\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010>0Q\"\u0004\u0018\u00010>¢\u0006\u0002\u0010RJ\u0010\u0010+\u001a\u00020\u00002\b\b\u0002\u0010+\u001a\u00020\u0010J\u0010\u0010_\u001a\u00020\u00002\b\u00103\u001a\u0004\u0018\u00010\u0006J\u0010\u0010_\u001a\u00020\u00002\u0006\u00103\u001a\u00020`H\u0007J\u0012\u0010_\u001a\u00020\u00002\n\u00103\u001a\u0006\u0012\u0002\b\u00030=J\u0010\u00108\u001a\u00020\u00002\b\u00108\u001a\u0004\u0018\u00010\u0016R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014R\u0014\u0010$\u001a\u00020%X\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\t¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\fR\u001a\u0010+\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0012\"\u0004\b-\u0010\u0014R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001a\u00100\u001a\b\u0012\u0004\u0012\u0002010\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\fR\u001c\u00103\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001c\u00108\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0018\"\u0004\b:\u0010\u001aR$\u0010;\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030=\u0012\u0004\u0012\u00020>0<X\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u00105R\u0017\u0010B\u001a\b\u0012\u0004\u0012\u00020C0\t¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\f¨\u0006a"}, d2 = {"Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "Lcom/squareup/kotlinpoet/Taggable$Builder;", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder$Builder;", "name", "", "type", "Lcom/squareup/kotlinpoet/TypeName;", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/TypeName;)V", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotations", "()Ljava/util/List;", "contextReceiverTypes", "getContextReceiverTypes$kotlinpoet", "delegated", "", "getDelegated$kotlinpoet", "()Z", "setDelegated$kotlinpoet", "(Z)V", "getter", "Lcom/squareup/kotlinpoet/FunSpec;", "getGetter$kotlinpoet", "()Lcom/squareup/kotlinpoet/FunSpec;", "setGetter$kotlinpoet", "(Lcom/squareup/kotlinpoet/FunSpec;)V", "initializer", "Lcom/squareup/kotlinpoet/CodeBlock;", "getInitializer$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock;", "setInitializer$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeBlock;)V", "isPrimaryConstructorParameter", "isPrimaryConstructorParameter$kotlinpoet", "setPrimaryConstructorParameter$kotlinpoet", "kdoc", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "getKdoc$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "modifiers", "Lcom/squareup/kotlinpoet/KModifier;", "getModifiers", "mutable", "getMutable$kotlinpoet", "setMutable$kotlinpoet", "getName$kotlinpoet", "()Ljava/lang/String;", "originatingElements", "Ljavax/lang/model/element/Element;", "getOriginatingElements", "receiverType", "getReceiverType$kotlinpoet", "()Lcom/squareup/kotlinpoet/TypeName;", "setReceiverType$kotlinpoet", "(Lcom/squareup/kotlinpoet/TypeName;)V", "setter", "getSetter$kotlinpoet", "setSetter$kotlinpoet", "tags", "", "Lkotlin/reflect/KClass;", "", "getTags", "()Ljava/util/Map;", "getType$kotlinpoet", "typeVariables", "Lcom/squareup/kotlinpoet/TypeVariableName;", "getTypeVariables", "addAnnotation", "annotationSpec", "annotation", "Lcom/squareup/kotlinpoet/ClassName;", "Ljava/lang/Class;", "addAnnotations", "annotationSpecs", "", "addKdoc", "block", "format", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "addModifiers", "([Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "addTypeVariable", "typeVariable", "addTypeVariables", "build", "Lcom/squareup/kotlinpoet/PropertySpec;", "contextReceivers", "([Lcom/squareup/kotlinpoet/TypeName;)Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "receiverTypes", "delegate", "codeBlock", "receiver", "Ljava/lang/reflect/Type;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: PropertySpec.kt */
    public static final class Builder implements Taggable.Builder<Builder>, OriginatingElementsHolder.Builder<Builder> {
        private final List<AnnotationSpec> annotations = new ArrayList();
        private final List<TypeName> contextReceiverTypes = new ArrayList();
        private boolean delegated;
        private FunSpec getter;
        private CodeBlock initializer;
        private boolean isPrimaryConstructorParameter;
        private final CodeBlock.Builder kdoc = CodeBlock.Companion.builder();
        private final List<KModifier> modifiers = new ArrayList();
        private boolean mutable;
        private final String name;
        private final List<Element> originatingElements = new ArrayList();
        private TypeName receiverType;
        private FunSpec setter;
        private final Map<KClass<?>, Object> tags = new LinkedHashMap();
        private final TypeName type;
        private final List<TypeVariableName> typeVariables = new ArrayList();

        public Builder(String str, TypeName typeName) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeName, "type");
            this.name = str;
            this.type = typeName;
        }

        public Builder addOriginatingElement(Element element) {
            return (Builder) OriginatingElementsHolder.Builder.DefaultImpls.addOriginatingElement(this, element);
        }

        public Builder tag(Class<?> cls, Object obj) {
            return (Builder) Taggable.Builder.DefaultImpls.tag(this, cls, obj);
        }

        public Builder tag(KClass<?> kClass, Object obj) {
            return (Builder) Taggable.Builder.DefaultImpls.tag(this, kClass, obj);
        }

        public final String getName$kotlinpoet() {
            return this.name;
        }

        public final TypeName getType$kotlinpoet() {
            return this.type;
        }

        public final boolean isPrimaryConstructorParameter$kotlinpoet() {
            return this.isPrimaryConstructorParameter;
        }

        public final void setPrimaryConstructorParameter$kotlinpoet(boolean z) {
            this.isPrimaryConstructorParameter = z;
        }

        public final boolean getMutable$kotlinpoet() {
            return this.mutable;
        }

        public final void setMutable$kotlinpoet(boolean z) {
            this.mutable = z;
        }

        public final CodeBlock.Builder getKdoc$kotlinpoet() {
            return this.kdoc;
        }

        public final CodeBlock getInitializer$kotlinpoet() {
            return this.initializer;
        }

        public final void setInitializer$kotlinpoet(CodeBlock codeBlock) {
            this.initializer = codeBlock;
        }

        public final boolean getDelegated$kotlinpoet() {
            return this.delegated;
        }

        public final void setDelegated$kotlinpoet(boolean z) {
            this.delegated = z;
        }

        public final FunSpec getGetter$kotlinpoet() {
            return this.getter;
        }

        public final void setGetter$kotlinpoet(FunSpec funSpec) {
            this.getter = funSpec;
        }

        public final FunSpec getSetter$kotlinpoet() {
            return this.setter;
        }

        public final void setSetter$kotlinpoet(FunSpec funSpec) {
            this.setter = funSpec;
        }

        public final TypeName getReceiverType$kotlinpoet() {
            return this.receiverType;
        }

        public final void setReceiverType$kotlinpoet(TypeName typeName) {
            this.receiverType = typeName;
        }

        public final List<TypeName> getContextReceiverTypes$kotlinpoet() {
            return this.contextReceiverTypes;
        }

        public final List<AnnotationSpec> getAnnotations() {
            return this.annotations;
        }

        public final List<KModifier> getModifiers() {
            return this.modifiers;
        }

        public final List<TypeVariableName> getTypeVariables() {
            return this.typeVariables;
        }

        public Map<KClass<?>, Object> getTags() {
            return this.tags;
        }

        public List<Element> getOriginatingElements() {
            return this.originatingElements;
        }

        public static /* synthetic */ Builder mutable$default(Builder builder, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = true;
            }
            return builder.mutable(z);
        }

        public final Builder mutable(boolean z) {
            Builder builder = this;
            this.mutable = z;
            return this;
        }

        public final Builder addKdoc(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            this.kdoc.add(str, Arrays.copyOf(objArr, objArr.length));
            return this;
        }

        public final Builder addKdoc(CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(codeBlock, "block");
            Builder builder = this;
            this.kdoc.add(codeBlock);
            return this;
        }

        public final Builder addAnnotations(Iterable<AnnotationSpec> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "annotationSpecs");
            Builder builder = this;
            CollectionsKt.addAll(this.annotations, iterable);
            return this;
        }

        public final Builder addAnnotation(AnnotationSpec annotationSpec) {
            Intrinsics.checkNotNullParameter(annotationSpec, "annotationSpec");
            Builder builder = this;
            this.annotations.add(annotationSpec);
            return this;
        }

        public final Builder addAnnotation(ClassName className) {
            Intrinsics.checkNotNullParameter(className, "annotation");
            Builder builder = this;
            this.annotations.add(AnnotationSpec.Companion.builder(className).build());
            return this;
        }

        public final Builder addAnnotation(Class<?> cls) {
            Intrinsics.checkNotNullParameter(cls, "annotation");
            return addAnnotation(ClassNames.get(cls));
        }

        public final Builder addAnnotation(KClass<?> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "annotation");
            return addAnnotation(ClassNames.get(kClass));
        }

        public final Builder addModifiers(KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            Builder builder = this;
            CollectionsKt.addAll(this.modifiers, (T[]) kModifierArr);
            return this;
        }

        public final Builder addModifiers(Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            Builder builder = this;
            CollectionsKt.addAll(this.modifiers, iterable);
            return this;
        }

        public final Builder addTypeVariables(Iterable<TypeVariableName> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "typeVariables");
            Builder builder = this;
            CollectionsKt.addAll(this.typeVariables, iterable);
            return this;
        }

        public final Builder addTypeVariable(TypeVariableName typeVariableName) {
            Intrinsics.checkNotNullParameter(typeVariableName, "typeVariable");
            Builder builder = this;
            this.typeVariables.add(typeVariableName);
            return this;
        }

        public final Builder initializer(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            return initializer(CodeBlock.Companion.of(str, Arrays.copyOf(objArr, objArr.length)));
        }

        public final Builder initializer(CodeBlock codeBlock) {
            Builder builder = this;
            this.initializer = codeBlock;
            this.delegated = false;
            return this;
        }

        public final Builder delegate(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            return delegate(CodeBlock.Companion.of(str, Arrays.copyOf(objArr, objArr.length)));
        }

        public final Builder delegate(CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(codeBlock, "codeBlock");
            Builder builder = this;
            this.initializer = codeBlock;
            this.delegated = true;
            return this;
        }

        public final Builder getter(FunSpec funSpec) {
            Builder builder = this;
            if (funSpec == null || Intrinsics.areEqual((Object) funSpec.getName(), (Object) FunSpec.GETTER)) {
                this.getter = funSpec;
                return this;
            }
            StringBuilder sb = new StringBuilder();
            Intrinsics.checkNotNull(funSpec);
            throw new IllegalArgumentException(sb.append(funSpec.getName()).append(" is not a getter").toString().toString());
        }

        public final Builder setter(FunSpec funSpec) {
            Builder builder = this;
            if (funSpec == null || Intrinsics.areEqual((Object) funSpec.getName(), (Object) FunSpec.SETTER)) {
                this.setter = funSpec;
                return this;
            }
            StringBuilder sb = new StringBuilder();
            Intrinsics.checkNotNull(funSpec);
            throw new IllegalArgumentException(sb.append(funSpec.getName()).append(" is not a setter").toString().toString());
        }

        public final Builder receiver(TypeName typeName) {
            Builder builder = this;
            this.receiverType = typeName;
            return this;
        }

        public final Builder receiver(Type type2) {
            Intrinsics.checkNotNullParameter(type2, "receiverType");
            return receiver(TypeNames.get(type2));
        }

        public final Builder receiver(KClass<?> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "receiverType");
            return receiver((TypeName) TypeNames.get(kClass));
        }

        public final Builder contextReceivers(Iterable<? extends TypeName> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "receiverTypes");
            Builder builder = this;
            CollectionsKt.addAll(this.contextReceiverTypes, iterable);
            return this;
        }

        public final Builder contextReceivers(TypeName... typeNameArr) {
            Intrinsics.checkNotNullParameter(typeNameArr, "receiverType");
            return contextReceivers((Iterable<? extends TypeName>) ArraysKt.toList((T[]) typeNameArr));
        }

        public final PropertySpec build() {
            if (!this.modifiers.contains(KModifier.INLINE)) {
                for (KModifier next : this.modifiers) {
                    if (!this.isPrimaryConstructorParameter) {
                        next.checkTarget$kotlinpoet(KModifier.Target.PROPERTY);
                    }
                }
                return new PropertySpec(this, (TagMap) null, (OriginatingElementsHolder) null, 6, (DefaultConstructorMarker) null);
            }
            throw new IllegalArgumentException("KotlinPoet doesn't allow setting the inline modifier on properties. You should mark either the getter, the setter, or both inline.");
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000bH\u0007¢\u0006\u0002\u0010\fJ&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0007J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000e2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000bH\u0007¢\u0006\u0002\u0010\u000fJ&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000e2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0007J5\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00102\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000bH\u0007¢\u0006\u0002\u0010\u0011J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0007¨\u0006\u0012"}, d2 = {"Lcom/squareup/kotlinpoet/PropertySpec$Companion;", "", "()V", "builder", "Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "name", "", "type", "Lcom/squareup/kotlinpoet/TypeName;", "modifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/TypeName;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "", "Ljava/lang/reflect/Type;", "(Ljava/lang/String;Ljava/lang/reflect/Type;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "Lkotlin/reflect/KClass;", "(Ljava/lang/String;Lkotlin/reflect/KClass;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/PropertySpec$Builder;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: PropertySpec.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Builder builder(String str, TypeName typeName, KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeName, "type");
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            return new Builder(str, typeName).addModifiers((KModifier[]) Arrays.copyOf(kModifierArr, kModifierArr.length));
        }

        @JvmStatic
        public final Builder builder(String str, Type type, KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            return builder(str, TypeNames.get(type), (KModifier[]) Arrays.copyOf(kModifierArr, kModifierArr.length));
        }

        @JvmStatic
        public final Builder builder(String str, KClass<?> kClass, KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(kClass, "type");
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            return builder(str, (TypeName) TypeNames.get(kClass), (KModifier[]) Arrays.copyOf(kModifierArr, kModifierArr.length));
        }

        @JvmStatic
        public final Builder builder(String str, TypeName typeName, Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeName, "type");
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            return new Builder(str, typeName).addModifiers(iterable);
        }

        @JvmStatic
        public final Builder builder(String str, Type type, Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            return builder(str, TypeNames.get(type), iterable);
        }

        @JvmStatic
        public final Builder builder(String str, KClass<?> kClass, Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(kClass, "type");
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            return builder(str, (TypeName) TypeNames.get(kClass), iterable);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0042, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003f, code lost:
        kotlin.io.CloseableKt.closeFinally(r10, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r11 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.squareup.kotlinpoet.CodeWriter r10 = new com.squareup.kotlinpoet.CodeWriter
            r2 = r0
            java.lang.Appendable r2 = (java.lang.Appendable) r2
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 2147483647(0x7fffffff, float:NaN)
            r8 = 30
            r9 = 0
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            java.io.Closeable r10 = (java.io.Closeable) r10
            r2 = r10
            com.squareup.kotlinpoet.CodeWriter r2 = (com.squareup.kotlinpoet.CodeWriter) r2     // Catch:{ all -> 0x003c }
            java.util.Set r3 = kotlin.collections.SetsKt.emptySet()     // Catch:{ all -> 0x003c }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 60
            r9 = 0
            r1 = r11
            emit$kotlinpoet$default(r1, r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x003c }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003c }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r10, r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "stringBuilder.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        L_0x003c:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x003e }
        L_0x003e:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r10, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.PropertySpec.toString():java.lang.String");
    }
}
