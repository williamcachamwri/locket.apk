package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.OriginatingElementsHolder;
import com.squareup.kotlinpoet.Taggable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 m2\u00020\u00012\u00020\u0002:\u0003lmnB#\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002¢\u0006\u0002\u0010\bJ\u0014\u0010S\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020@0\u000fH\u0002J9\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010\u00102\u000e\b\u0002\u0010Y\u001a\b\u0012\u0004\u0012\u000201002\b\b\u0002\u0010Z\u001a\u00020\u0017H\u0000¢\u0006\u0002\b[J\u0013\u0010\\\u001a\u00020\u00172\b\u0010]\u001a\u0004\u0018\u00010LH\u0002J\b\u0010^\u001a\u00020!H\u0016J\u0018\u0010_\u001a\u00020\u00172\u0006\u0010`\u001a\u00020@2\u0006\u0010a\u001a\u00020bH\u0002J\b\u0010c\u001a\u00020\u001dH\u0002J(\u0010d\u001a\u0004\u0018\u0001He\"\b\b\u0000\u0010e*\u00020L2\f\u0010f\u001a\b\u0012\u0004\u0012\u0002He0gH\u0001¢\u0006\u0002\u0010hJ(\u0010d\u001a\u0004\u0018\u0001He\"\b\b\u0000\u0010e*\u00020L2\f\u0010f\u001a\b\u0012\u0004\u0012\u0002He0KH\u0001¢\u0006\u0002\u0010iJ\u001e\u0010j\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020,2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0010H\u0007J\b\u0010k\u001a\u00020\u0010H\u0016R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0007\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00000\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\rR\u0014\u0010\u0016\u001a\u00020\u00178BX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00178BX\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010%\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0011\u0010&\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0011\u0010'\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0011\u0010(\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019R\u0011\u0010)\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u0011\u0010+\u001a\u00020,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020100¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0013\u00104\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u001c\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001000X\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u00103R\u0018\u00109\u001a\b\u0012\u0004\u0012\u00020:0\nX\u0005¢\u0006\u0006\u001a\u0004\b;\u0010\rR\u0013\u0010<\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0017\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\n¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\rR\u0011\u0010B\u001a\u00020C¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0017\u0010F\u001a\b\u0012\u0004\u0012\u00020\u001d0\n¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\rR\u001f\u0010H\u001a\u0010\u0012\u0004\u0012\u00020C\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u000f¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R$\u0010J\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030K\u0012\u0004\u0012\u00020L0\u000f8VX\u0005¢\u0006\u0006\u001a\u0004\bM\u0010\u0012R\u0017\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00000\n¢\u0006\b\n\u0000\u001a\u0004\bO\u0010\rR\u0017\u0010P\u001a\b\u0012\u0004\u0012\u00020Q0\n¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\r¨\u0006o"}, d2 = {"Lcom/squareup/kotlinpoet/TypeSpec;", "Lcom/squareup/kotlinpoet/Taggable;", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder;", "builder", "Lcom/squareup/kotlinpoet/TypeSpec$Builder;", "tagMap", "Lcom/squareup/kotlinpoet/TagMap;", "delegateOriginatingElements", "(Lcom/squareup/kotlinpoet/TypeSpec$Builder;Lcom/squareup/kotlinpoet/TagMap;Lcom/squareup/kotlinpoet/OriginatingElementsHolder;)V", "annotationSpecs", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotationSpecs", "()Ljava/util/List;", "enumConstants", "", "", "getEnumConstants", "()Ljava/util/Map;", "funSpecs", "Lcom/squareup/kotlinpoet/FunSpec;", "getFunSpecs", "hasInitializer", "", "getHasInitializer", "()Z", "hasNoBody", "getHasNoBody", "initializerBlock", "Lcom/squareup/kotlinpoet/CodeBlock;", "getInitializerBlock", "()Lcom/squareup/kotlinpoet/CodeBlock;", "initializerIndex", "", "getInitializerIndex", "()I", "isAnnotation", "isAnonymousClass", "isCompanion", "isEnum", "isFunctionalInterface", "kdoc", "getKdoc", "kind", "Lcom/squareup/kotlinpoet/TypeSpec$Kind;", "getKind", "()Lcom/squareup/kotlinpoet/TypeSpec$Kind;", "modifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "getModifiers", "()Ljava/util/Set;", "name", "getName", "()Ljava/lang/String;", "nestedTypesSimpleNames", "getNestedTypesSimpleNames$kotlinpoet", "originatingElements", "Ljavax/lang/model/element/Element;", "getOriginatingElements", "primaryConstructor", "getPrimaryConstructor", "()Lcom/squareup/kotlinpoet/FunSpec;", "propertySpecs", "Lcom/squareup/kotlinpoet/PropertySpec;", "getPropertySpecs", "superclass", "Lcom/squareup/kotlinpoet/TypeName;", "getSuperclass", "()Lcom/squareup/kotlinpoet/TypeName;", "superclassConstructorParameters", "getSuperclassConstructorParameters", "superinterfaces", "getSuperinterfaces", "tags", "Lkotlin/reflect/KClass;", "", "getTags", "typeSpecs", "getTypeSpecs", "typeVariables", "Lcom/squareup/kotlinpoet/TypeVariableName;", "getTypeVariables", "constructorProperties", "emit", "", "codeWriter", "Lcom/squareup/kotlinpoet/CodeWriter;", "enumName", "implicitModifiers", "isNestedExternal", "emit$kotlinpoet", "equals", "other", "hashCode", "isPropertyInitializerConstructorParameter", "property", "parameter", "Lcom/squareup/kotlinpoet/ParameterSpec;", "kdocWithConstructorParameters", "tag", "T", "type", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toBuilder", "toString", "Builder", "Companion", "Kind", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeSpec.kt */
public final class TypeSpec implements Taggable, OriginatingElementsHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<AnnotationSpec> annotationSpecs;
    private final OriginatingElementsHolder delegateOriginatingElements;
    private final Map<String, TypeSpec> enumConstants;
    private final List<FunSpec> funSpecs;
    private final CodeBlock initializerBlock;
    private final int initializerIndex;
    private final boolean isAnnotation;
    private final boolean isAnonymousClass;
    private final boolean isCompanion;
    private final boolean isEnum;
    private final boolean isFunctionalInterface;
    private final CodeBlock kdoc;
    private final Kind kind;
    private final Set<KModifier> modifiers;
    private final String name;
    private final Set<String> nestedTypesSimpleNames;
    private final FunSpec primaryConstructor;
    private final List<PropertySpec> propertySpecs;
    private final TypeName superclass;
    private final List<CodeBlock> superclassConstructorParameters;
    private final Map<TypeName, CodeBlock> superinterfaces;
    private final TagMap tagMap;
    private final List<TypeSpec> typeSpecs;
    private final List<TypeVariableName> typeVariables;

    @JvmStatic
    public static final Builder annotationBuilder(ClassName className) {
        return Companion.annotationBuilder(className);
    }

    @JvmStatic
    public static final Builder annotationBuilder(String str) {
        return Companion.annotationBuilder(str);
    }

    @JvmStatic
    public static final Builder anonymousClassBuilder() {
        return Companion.anonymousClassBuilder();
    }

    @JvmStatic
    public static final Builder classBuilder(ClassName className) {
        return Companion.classBuilder(className);
    }

    @JvmStatic
    public static final Builder classBuilder(String str) {
        return Companion.classBuilder(str);
    }

    @JvmStatic
    public static final Builder companionObjectBuilder() {
        return Companion.companionObjectBuilder();
    }

    @JvmStatic
    public static final Builder companionObjectBuilder(String str) {
        return Companion.companionObjectBuilder(str);
    }

    @JvmStatic
    public static final Builder enumBuilder(ClassName className) {
        return Companion.enumBuilder(className);
    }

    @JvmStatic
    public static final Builder enumBuilder(String str) {
        return Companion.enumBuilder(str);
    }

    @JvmStatic
    public static final Builder expectClassBuilder(ClassName className) {
        return Companion.expectClassBuilder(className);
    }

    @JvmStatic
    public static final Builder expectClassBuilder(String str) {
        return Companion.expectClassBuilder(str);
    }

    @JvmStatic
    public static final Builder funInterfaceBuilder(ClassName className) {
        return Companion.funInterfaceBuilder(className);
    }

    @JvmStatic
    public static final Builder funInterfaceBuilder(String str) {
        return Companion.funInterfaceBuilder(str);
    }

    @JvmStatic
    public static final Builder interfaceBuilder(ClassName className) {
        return Companion.interfaceBuilder(className);
    }

    @JvmStatic
    public static final Builder interfaceBuilder(String str) {
        return Companion.interfaceBuilder(str);
    }

    @JvmStatic
    public static final Builder objectBuilder(ClassName className) {
        return Companion.objectBuilder(className);
    }

    @JvmStatic
    public static final Builder objectBuilder(String str) {
        return Companion.objectBuilder(str);
    }

    @JvmStatic
    public static final Builder valueClassBuilder(String str) {
        return Companion.valueClassBuilder(str);
    }

    public List<Element> getOriginatingElements() {
        return this.delegateOriginatingElements.getOriginatingElements();
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
        return toBuilder$default(this, (Kind) null, (String) null, 3, (Object) null);
    }

    public final Builder toBuilder(Kind kind2) {
        Intrinsics.checkNotNullParameter(kind2, "kind");
        return toBuilder$default(this, kind2, (String) null, 2, (Object) null);
    }

    private TypeSpec(Builder builder, TagMap tagMap2, OriginatingElementsHolder originatingElementsHolder) {
        this.tagMap = tagMap2;
        this.delegateOriginatingElements = originatingElementsHolder;
        this.kind = builder.getKind$kotlinpoet();
        this.name = builder.getName$kotlinpoet();
        this.kdoc = builder.getKdoc$kotlinpoet().build();
        this.annotationSpecs = UtilKt.toImmutableList(builder.getAnnotationSpecs());
        this.modifiers = UtilKt.toImmutableSet(builder.getModifiers());
        this.typeVariables = UtilKt.toImmutableList(builder.getTypeVariables());
        this.primaryConstructor = builder.getPrimaryConstructor$kotlinpoet();
        this.superclass = builder.getSuperclass$kotlinpoet();
        this.superclassConstructorParameters = UtilKt.toImmutableList(builder.getSuperclassConstructorParameters());
        this.isEnum = builder.isEnum$kotlinpoet();
        this.isAnnotation = builder.isAnnotation$kotlinpoet();
        this.isCompanion = builder.isCompanion$kotlinpoet();
        this.isAnonymousClass = builder.isAnonymousClass$kotlinpoet();
        this.isFunctionalInterface = builder.isFunInterface$kotlinpoet();
        this.superinterfaces = UtilKt.toImmutableMap(builder.getSuperinterfaces());
        this.enumConstants = UtilKt.toImmutableMap(builder.getEnumConstants());
        this.propertySpecs = UtilKt.toImmutableList(builder.getPropertySpecs());
        this.initializerBlock = builder.getInitializerBlock$kotlinpoet().build();
        this.initializerIndex = builder.getInitializerIndex();
        this.funSpecs = UtilKt.toImmutableList(builder.getFunSpecs());
        List<TypeSpec> immutableList = UtilKt.toImmutableList(builder.getTypeSpecs());
        this.typeSpecs = immutableList;
        Iterable<TypeSpec> iterable = immutableList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (TypeSpec typeSpec : iterable) {
            arrayList.add(typeSpec.name);
        }
        this.nestedTypesSimpleNames = UtilKt.toImmutableSet((List) arrayList);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* synthetic */ TypeSpec(com.squareup.kotlinpoet.TypeSpec.Builder r2, com.squareup.kotlinpoet.TagMap r3, com.squareup.kotlinpoet.OriginatingElementsHolder r4, int r5, kotlin.jvm.internal.DefaultConstructorMarker r6) {
        /*
            r1 = this;
            r6 = r5 & 2
            if (r6 == 0) goto L_0x000b
            r3 = r2
            com.squareup.kotlinpoet.Taggable$Builder r3 = (com.squareup.kotlinpoet.Taggable.Builder) r3
            com.squareup.kotlinpoet.TagMap r3 = com.squareup.kotlinpoet.TaggableKt.buildTagMap(r3)
        L_0x000b:
            r5 = r5 & 4
            if (r5 == 0) goto L_0x004a
            java.util.List r4 = r2.getOriginatingElements()
            java.util.Collection r4 = (java.util.Collection) r4
            java.util.List r5 = r2.getTypeSpecs()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r5 = r5.iterator()
        L_0x0026:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x003c
            java.lang.Object r0 = r5.next()
            com.squareup.kotlinpoet.TypeSpec r0 = (com.squareup.kotlinpoet.TypeSpec) r0
            java.util.List r0 = r0.getOriginatingElements()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            kotlin.collections.CollectionsKt.addAll(r6, r0)
            goto L_0x0026
        L_0x003c:
            java.util.List r6 = (java.util.List) r6
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.List r4 = kotlin.collections.CollectionsKt.plus(r4, r6)
            com.squareup.kotlinpoet.OriginatingElements r4 = com.squareup.kotlinpoet.OriginatingElementsHolderKt.buildOriginatingElements((java.util.List<? extends javax.lang.model.element.Element>) r4)
            com.squareup.kotlinpoet.OriginatingElementsHolder r4 = (com.squareup.kotlinpoet.OriginatingElementsHolder) r4
        L_0x004a:
            r1.<init>(r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.TypeSpec.<init>(com.squareup.kotlinpoet.TypeSpec$Builder, com.squareup.kotlinpoet.TagMap, com.squareup.kotlinpoet.OriginatingElementsHolder, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Kind getKind() {
        return this.kind;
    }

    public final String getName() {
        return this.name;
    }

    public final CodeBlock getKdoc() {
        return this.kdoc;
    }

    public final List<AnnotationSpec> getAnnotationSpecs() {
        return this.annotationSpecs;
    }

    public final Set<KModifier> getModifiers() {
        return this.modifiers;
    }

    public final List<TypeVariableName> getTypeVariables() {
        return this.typeVariables;
    }

    public final FunSpec getPrimaryConstructor() {
        return this.primaryConstructor;
    }

    public final TypeName getSuperclass() {
        return this.superclass;
    }

    public final List<CodeBlock> getSuperclassConstructorParameters() {
        return this.superclassConstructorParameters;
    }

    public final boolean isEnum() {
        return this.isEnum;
    }

    public final boolean isAnnotation() {
        return this.isAnnotation;
    }

    public final boolean isCompanion() {
        return this.isCompanion;
    }

    public final boolean isAnonymousClass() {
        return this.isAnonymousClass;
    }

    public final boolean isFunctionalInterface() {
        return this.isFunctionalInterface;
    }

    public final Map<TypeName, CodeBlock> getSuperinterfaces() {
        return this.superinterfaces;
    }

    public final Map<String, TypeSpec> getEnumConstants() {
        return this.enumConstants;
    }

    public final List<PropertySpec> getPropertySpecs() {
        return this.propertySpecs;
    }

    public final CodeBlock getInitializerBlock() {
        return this.initializerBlock;
    }

    public final int getInitializerIndex() {
        return this.initializerIndex;
    }

    public final List<FunSpec> getFunSpecs() {
        return this.funSpecs;
    }

    public final List<TypeSpec> getTypeSpecs() {
        return this.typeSpecs;
    }

    public final Set<String> getNestedTypesSimpleNames$kotlinpoet() {
        return this.nestedTypesSimpleNames;
    }

    public static /* synthetic */ Builder toBuilder$default(TypeSpec typeSpec, Kind kind2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            kind2 = typeSpec.kind;
        }
        if ((i & 2) != 0) {
            str = typeSpec.name;
        }
        return typeSpec.toBuilder(kind2, str);
    }

    public final Builder toBuilder(Kind kind2, String str) {
        Intrinsics.checkNotNullParameter(kind2, "kind");
        Builder builder = new Builder(kind2, str, new KModifier[0]);
        CollectionsKt.addAll(builder.getModifiers(), this.modifiers);
        builder.getKdoc$kotlinpoet().add(this.kdoc);
        CollectionsKt.addAll(builder.getAnnotationSpecs(), this.annotationSpecs);
        CollectionsKt.addAll(builder.getTypeVariables(), this.typeVariables);
        builder.setSuperclass$kotlinpoet(this.superclass);
        CollectionsKt.addAll(builder.getSuperclassConstructorParameters(), this.superclassConstructorParameters);
        builder.getEnumConstants().putAll(this.enumConstants);
        CollectionsKt.addAll(builder.getPropertySpecs(), this.propertySpecs);
        CollectionsKt.addAll(builder.getFunSpecs(), this.funSpecs);
        CollectionsKt.addAll(builder.getTypeSpecs(), this.typeSpecs);
        builder.getInitializerBlock$kotlinpoet().add(this.initializerBlock);
        builder.setInitializerIndex(this.initializerIndex);
        builder.getSuperinterfaces().putAll(this.superinterfaces);
        builder.setPrimaryConstructor$kotlinpoet(this.primaryConstructor);
        builder.getTags().putAll(this.tagMap.getTags());
        CollectionsKt.addAll(builder.getOriginatingElements(), getOriginatingElements());
        return builder;
    }

    public static /* synthetic */ void emit$kotlinpoet$default(TypeSpec typeSpec, CodeWriter codeWriter, String str, Set set, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            set = SetsKt.emptySet();
        }
        if ((i & 8) != 0) {
            z = false;
        }
        typeSpec.emit$kotlinpoet(codeWriter, str, set, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:111:0x02d2 A[Catch:{ all -> 0x05a0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x02d3 A[Catch:{ all -> 0x05a0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x020a  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0224 A[Catch:{ all -> 0x05a0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0236 A[Catch:{ all -> 0x05a0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x023a A[Catch:{ all -> 0x05a0 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void emit$kotlinpoet(com.squareup.kotlinpoet.CodeWriter r29, java.lang.String r30, java.util.Set<? extends com.squareup.kotlinpoet.KModifier> r31, boolean r32) {
        /*
            r28 = this;
            r1 = r28
            r11 = r29
            r0 = r31
            java.lang.String r2 = "codeWriter"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r2)
            java.lang.String r2 = "implicitModifiers"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            java.util.Set<com.squareup.kotlinpoet.KModifier> r2 = r1.modifiers
            com.squareup.kotlinpoet.KModifier r3 = com.squareup.kotlinpoet.KModifier.EXTERNAL
            boolean r2 = r2.contains(r3)
            r13 = 0
            if (r2 != 0) goto L_0x0020
            if (r32 == 0) goto L_0x001e
            goto L_0x0020
        L_0x001e:
            r14 = r13
            goto L_0x0021
        L_0x0020:
            r14 = 1
        L_0x0021:
            int r15 = r29.getStatementLine()
            r2 = -1
            r11.setStatementLine(r2)
            java.util.Map r10 = r28.constructorProperties()
            java.util.List<com.squareup.kotlinpoet.CodeBlock> r2 = r1.superclassConstructorParameters
            r3 = r2
            java.util.Collection r3 = (java.util.Collection) r3
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 7
            r8 = 0
            com.squareup.kotlinpoet.CodeBlock r3 = com.squareup.kotlinpoet.CodeBlocks.joinToCode$default(r3, r4, r5, r6, r7, r8)
            java.lang.String r9 = ","
            java.lang.String r8 = " {\n"
            java.lang.String r7 = "\n"
            r6 = 2
            r5 = 0
            if (r30 == 0) goto L_0x0098
            com.squareup.kotlinpoet.CodeBlock r2 = r28.kdocWithConstructorParameters()     // Catch:{ all -> 0x05a0 }
            r11.emitKdoc(r2)     // Catch:{ all -> 0x05a0 }
            java.util.List<com.squareup.kotlinpoet.AnnotationSpec> r2 = r1.annotationSpecs     // Catch:{ all -> 0x05a0 }
            r11.emitAnnotations(r2, r13)     // Catch:{ all -> 0x05a0 }
            java.lang.String r2 = "%N"
            java.lang.Object[] r4 = new java.lang.Object[]{r30}     // Catch:{ all -> 0x05a0 }
            r11.emitCode(r2, r4)     // Catch:{ all -> 0x05a0 }
            boolean r2 = r3.isNotEmpty()     // Catch:{ all -> 0x05a0 }
            if (r2 == 0) goto L_0x0081
            java.lang.String r2 = "("
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r2, r13, r6, r5)     // Catch:{ all -> 0x05a0 }
            r4 = 0
            r16 = 0
            r17 = 6
            r18 = 0
            r2 = r29
            r12 = r5
            r5 = r16
            r6 = r17
            r19 = r7
            r7 = r18
            com.squareup.kotlinpoet.CodeWriter.emitCode$default(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x05a0 }
            java.lang.String r2 = ")"
            r7 = 2
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r2, r13, r7, r12)     // Catch:{ all -> 0x05a0 }
            goto L_0x0085
        L_0x0081:
            r12 = r5
            r19 = r7
            r7 = r6
        L_0x0085:
            boolean r2 = r28.getHasNoBody()     // Catch:{ all -> 0x05a0 }
            if (r2 == 0) goto L_0x008f
            r11.setStatementLine(r15)
            return
        L_0x008f:
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r8, r13, r7, r12)     // Catch:{ all -> 0x05a0 }
            r3 = r12
            r4 = r13
            r12 = r19
            goto L_0x03ad
        L_0x0098:
            r12 = r5
            r19 = r7
            r7 = r6
            boolean r2 = r1.isAnonymousClass     // Catch:{ all -> 0x05a0 }
            r4 = 10
            if (r2 == 0) goto L_0x017f
            java.lang.String r2 = "object"
            r11.emitCode(r2)     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeName r2 = r1.superclass     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.ClassName r5 = com.squareup.kotlinpoet.TypeNames.ANY     // Catch:{ all -> 0x05a0 }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r5)     // Catch:{ all -> 0x05a0 }
            java.lang.String r5 = " %T"
            if (r2 != 0) goto L_0x00e7
            if (r14 != 0) goto L_0x00d5
            java.util.Set<com.squareup.kotlinpoet.KModifier> r2 = r1.modifiers     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.KModifier r6 = com.squareup.kotlinpoet.KModifier.EXPECT     // Catch:{ all -> 0x05a0 }
            boolean r2 = r2.contains(r6)     // Catch:{ all -> 0x05a0 }
            if (r2 != 0) goto L_0x00d5
            com.squareup.kotlinpoet.CodeBlock$Companion r2 = com.squareup.kotlinpoet.CodeBlock.Companion     // Catch:{ all -> 0x05a0 }
            java.lang.String r6 = " %T(%L)"
            java.lang.Object[] r12 = new java.lang.Object[r7]     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeName r7 = r1.superclass     // Catch:{ all -> 0x05a0 }
            r12[r13] = r7     // Catch:{ all -> 0x05a0 }
            r7 = 1
            r12[r7] = r3     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeBlock r2 = r2.of(r6, r12)     // Catch:{ all -> 0x05a0 }
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)     // Catch:{ all -> 0x05a0 }
            goto L_0x00eb
        L_0x00d5:
            com.squareup.kotlinpoet.CodeBlock$Companion r2 = com.squareup.kotlinpoet.CodeBlock.Companion     // Catch:{ all -> 0x05a0 }
            r3 = 1
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeName r3 = r1.superclass     // Catch:{ all -> 0x05a0 }
            r6[r13] = r3     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeBlock r2 = r2.of(r5, r6)     // Catch:{ all -> 0x05a0 }
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)     // Catch:{ all -> 0x05a0 }
            goto L_0x00eb
        L_0x00e7:
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ all -> 0x05a0 }
        L_0x00eb:
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x05a0 }
            java.util.Map<com.squareup.kotlinpoet.TypeName, com.squareup.kotlinpoet.CodeBlock> r3 = r1.superinterfaces     // Catch:{ all -> 0x05a0 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x05a0 }
            r6 = 1
            r3 = r3 ^ r6
            if (r3 == 0) goto L_0x012b
            java.util.Map<com.squareup.kotlinpoet.TypeName, com.squareup.kotlinpoet.CodeBlock> r3 = r1.superinterfaces     // Catch:{ all -> 0x05a0 }
            java.util.Set r3 = r3.keySet()     // Catch:{ all -> 0x05a0 }
            java.lang.Iterable r3 = (java.lang.Iterable) r3     // Catch:{ all -> 0x05a0 }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x05a0 }
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r3, r4)     // Catch:{ all -> 0x05a0 }
            r6.<init>(r4)     // Catch:{ all -> 0x05a0 }
            java.util.Collection r6 = (java.util.Collection) r6     // Catch:{ all -> 0x05a0 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x05a0 }
        L_0x010e:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x05a0 }
            if (r4 == 0) goto L_0x0128
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeName r4 = (com.squareup.kotlinpoet.TypeName) r4     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeBlock$Companion r7 = com.squareup.kotlinpoet.CodeBlock.Companion     // Catch:{ all -> 0x05a0 }
            java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeBlock r4 = r7.of(r5, r4)     // Catch:{ all -> 0x05a0 }
            r6.add(r4)     // Catch:{ all -> 0x05a0 }
            goto L_0x010e
        L_0x0128:
            java.util.List r6 = (java.util.List) r6     // Catch:{ all -> 0x05a0 }
            goto L_0x012f
        L_0x012b:
            java.util.List r6 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ all -> 0x05a0 }
        L_0x012f:
            java.lang.Iterable r6 = (java.lang.Iterable) r6     // Catch:{ all -> 0x05a0 }
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r2, r6)     // Catch:{ all -> 0x05a0 }
            r3 = r2
            java.util.Collection r3 = (java.util.Collection) r3     // Catch:{ all -> 0x05a0 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x05a0 }
            r4 = 1
            r3 = r3 ^ r4
            if (r3 == 0) goto L_0x0164
            java.lang.String r3 = " :"
            r11.emitCode(r3)     // Catch:{ all -> 0x05a0 }
            r20 = r2
            java.util.Collection r20 = (java.util.Collection) r20     // Catch:{ all -> 0x05a0 }
            r21 = r9
            java.lang.CharSequence r21 = (java.lang.CharSequence) r21     // Catch:{ all -> 0x05a0 }
            r22 = 0
            r23 = 0
            r24 = 6
            r25 = 0
            com.squareup.kotlinpoet.CodeBlock r3 = com.squareup.kotlinpoet.CodeBlocks.joinToCode$default(r20, r21, r22, r23, r24, r25)     // Catch:{ all -> 0x05a0 }
            r4 = 0
            r5 = 0
            r6 = 6
            r7 = 0
            r2 = r29
            r12 = 2
            com.squareup.kotlinpoet.CodeWriter.emitCode$default(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x05a0 }
            goto L_0x0165
        L_0x0164:
            r12 = 2
        L_0x0165:
            boolean r2 = r28.getHasNoBody()     // Catch:{ all -> 0x05a0 }
            if (r2 == 0) goto L_0x0175
            java.lang.String r0 = " {\n}"
            r2 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r0, r13, r12, r2)     // Catch:{ all -> 0x05a0 }
            r11.setStatementLine(r15)
            return
        L_0x0175:
            r2 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r8, r13, r12, r2)     // Catch:{ all -> 0x05a0 }
            r4 = r13
            r12 = r19
            r3 = 0
            goto L_0x03ad
        L_0x017f:
            r12 = r7
            com.squareup.kotlinpoet.CodeBlock r2 = r28.kdocWithConstructorParameters()     // Catch:{ all -> 0x05a0 }
            r11.emitKdoc(r2)     // Catch:{ all -> 0x05a0 }
            java.util.List<com.squareup.kotlinpoet.AnnotationSpec> r2 = r1.annotationSpecs     // Catch:{ all -> 0x05a0 }
            r11.emitAnnotations(r2, r13)     // Catch:{ all -> 0x05a0 }
            java.util.Set<com.squareup.kotlinpoet.KModifier> r2 = r1.modifiers     // Catch:{ all -> 0x05a0 }
            if (r32 == 0) goto L_0x01a0
            com.squareup.kotlinpoet.KModifier[] r5 = new com.squareup.kotlinpoet.KModifier[r12]     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.KModifier r6 = com.squareup.kotlinpoet.KModifier.PUBLIC     // Catch:{ all -> 0x05a0 }
            r5[r13] = r6     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.KModifier r6 = com.squareup.kotlinpoet.KModifier.EXTERNAL     // Catch:{ all -> 0x05a0 }
            r7 = 1
            r5[r7] = r6     // Catch:{ all -> 0x05a0 }
            java.util.Set r5 = kotlin.collections.SetsKt.setOf(r5)     // Catch:{ all -> 0x05a0 }
            goto L_0x01a6
        L_0x01a0:
            com.squareup.kotlinpoet.KModifier r5 = com.squareup.kotlinpoet.KModifier.PUBLIC     // Catch:{ all -> 0x05a0 }
            java.util.Set r5 = kotlin.collections.SetsKt.setOf(r5)     // Catch:{ all -> 0x05a0 }
        L_0x01a6:
            r11.emitModifiers(r2, r5)     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeSpec$Kind r2 = r1.kind     // Catch:{ all -> 0x05a0 }
            java.lang.String r2 = r2.getDeclarationKeyword$kotlinpoet()     // Catch:{ all -> 0x05a0 }
            r5 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r2, r13, r12, r5)     // Catch:{ all -> 0x05a0 }
            java.lang.String r2 = r1.name     // Catch:{ all -> 0x05a0 }
            if (r2 == 0) goto L_0x01c0
            java.lang.String r2 = " %N"
            java.lang.Object[] r5 = new java.lang.Object[]{r28}     // Catch:{ all -> 0x05a0 }
            r11.emitCode(r2, r5)     // Catch:{ all -> 0x05a0 }
        L_0x01c0:
            java.util.List<com.squareup.kotlinpoet.TypeVariableName> r2 = r1.typeVariables     // Catch:{ all -> 0x05a0 }
            r11.emitTypeVariables(r2)     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.FunSpec r2 = r1.primaryConstructor     // Catch:{ all -> 0x05a0 }
            if (r2 == 0) goto L_0x0251
            r11.pushType(r1)     // Catch:{ all -> 0x05a0 }
            java.util.List r5 = r2.getAnnotations()     // Catch:{ all -> 0x05a0 }
            java.util.Collection r5 = (java.util.Collection) r5     // Catch:{ all -> 0x05a0 }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x05a0 }
            if (r5 != 0) goto L_0x01da
            r5 = 1
            goto L_0x01db
        L_0x01da:
            r5 = r13
        L_0x01db:
            java.util.List r6 = r2.getAnnotations()     // Catch:{ all -> 0x05a0 }
            java.util.Collection r6 = (java.util.Collection) r6     // Catch:{ all -> 0x05a0 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x05a0 }
            r7 = 1
            r6 = r6 ^ r7
            if (r6 != 0) goto L_0x01f9
            java.util.Set r6 = r2.getModifiers()     // Catch:{ all -> 0x05a0 }
            java.util.Collection r6 = (java.util.Collection) r6     // Catch:{ all -> 0x05a0 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x05a0 }
            r6 = r6 ^ r7
            if (r6 == 0) goto L_0x01f7
            goto L_0x01f9
        L_0x01f7:
            r6 = r13
            goto L_0x01fa
        L_0x01f9:
            r6 = 1
        L_0x01fa:
            java.util.List r7 = r2.getAnnotations()     // Catch:{ all -> 0x05a0 }
            java.util.Collection r7 = (java.util.Collection) r7     // Catch:{ all -> 0x05a0 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x05a0 }
            r4 = 1
            r7 = r7 ^ r4
            java.lang.String r4 = " "
            if (r7 == 0) goto L_0x0216
            r7 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r4, r13, r12, r7)     // Catch:{ all -> 0x05a0 }
            java.util.List r7 = r2.getAnnotations()     // Catch:{ all -> 0x05a0 }
            r12 = 1
            r11.emitAnnotations(r7, r12)     // Catch:{ all -> 0x05a0 }
        L_0x0216:
            java.util.Set r7 = r2.getModifiers()     // Catch:{ all -> 0x05a0 }
            java.util.Collection r7 = (java.util.Collection) r7     // Catch:{ all -> 0x05a0 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x05a0 }
            r12 = 1
            r7 = r7 ^ r12
            if (r7 == 0) goto L_0x0236
            if (r5 != 0) goto L_0x022c
            r5 = 2
            r7 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r4, r13, r5, r7)     // Catch:{ all -> 0x05a0 }
            goto L_0x022e
        L_0x022c:
            r5 = 2
            r7 = 0
        L_0x022e:
            java.util.Set r4 = r2.getModifiers()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeWriter.emitModifiers$default(r11, r4, r7, r5, r7)     // Catch:{ all -> 0x05a0 }
            goto L_0x0238
        L_0x0236:
            r5 = 2
            r7 = 0
        L_0x0238:
            if (r6 == 0) goto L_0x023f
            java.lang.String r4 = "constructor"
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r4, r13, r5, r7)     // Catch:{ all -> 0x05a0 }
        L_0x023f:
            java.util.List r2 = r2.getParameters()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeSpec$emit$1$1 r4 = new com.squareup.kotlinpoet.TypeSpec$emit$1$1     // Catch:{ all -> 0x05a0 }
            r4.<init>(r10, r11)     // Catch:{ all -> 0x05a0 }
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4     // Catch:{ all -> 0x05a0 }
            r5 = 1
            com.squareup.kotlinpoet.ParameterSpecKt.emit(r2, r11, r5, r4)     // Catch:{ all -> 0x05a0 }
            r29.popType()     // Catch:{ all -> 0x05a0 }
        L_0x0251:
            com.squareup.kotlinpoet.TypeName r2 = r1.superclass     // Catch:{ all -> 0x05a0 }
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)     // Catch:{ all -> 0x05a0 }
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ all -> 0x05a0 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x05a0 }
            r4.<init>()     // Catch:{ all -> 0x05a0 }
            java.util.Collection r4 = (java.util.Collection) r4     // Catch:{ all -> 0x05a0 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x05a0 }
        L_0x0264:
            boolean r5 = r2.hasNext()     // Catch:{ all -> 0x05a0 }
            if (r5 == 0) goto L_0x027f
            java.lang.Object r5 = r2.next()     // Catch:{ all -> 0x05a0 }
            r6 = r5
            com.squareup.kotlinpoet.TypeName r6 = (com.squareup.kotlinpoet.TypeName) r6     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.ClassName r7 = com.squareup.kotlinpoet.TypeNames.ANY     // Catch:{ all -> 0x05a0 }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x05a0 }
            r7 = 1
            r6 = r6 ^ r7
            if (r6 == 0) goto L_0x0264
            r4.add(r5)     // Catch:{ all -> 0x05a0 }
            goto L_0x0264
        L_0x027f:
            java.util.List r4 = (java.util.List) r4     // Catch:{ all -> 0x05a0 }
            java.lang.Iterable r4 = (java.lang.Iterable) r4     // Catch:{ all -> 0x05a0 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x05a0 }
            r5 = 10
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r4, r5)     // Catch:{ all -> 0x05a0 }
            r2.<init>(r6)     // Catch:{ all -> 0x05a0 }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x05a0 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x05a0 }
        L_0x0294:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x05a0 }
            java.lang.String r6 = "%T"
            if (r5 == 0) goto L_0x0305
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeName r5 = (com.squareup.kotlinpoet.TypeName) r5     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.FunSpec r7 = r1.primaryConstructor     // Catch:{ all -> 0x05a0 }
            if (r7 != 0) goto L_0x02de
            java.util.List<com.squareup.kotlinpoet.FunSpec> r7 = r1.funSpecs     // Catch:{ all -> 0x05a0 }
            java.lang.Iterable r7 = (java.lang.Iterable) r7     // Catch:{ all -> 0x05a0 }
            boolean r12 = r7 instanceof java.util.Collection     // Catch:{ all -> 0x05a0 }
            if (r12 == 0) goto L_0x02b9
            r12 = r7
            java.util.Collection r12 = (java.util.Collection) r12     // Catch:{ all -> 0x05a0 }
            boolean r12 = r12.isEmpty()     // Catch:{ all -> 0x05a0 }
            if (r12 == 0) goto L_0x02b9
        L_0x02b7:
            r7 = 1
            goto L_0x02d0
        L_0x02b9:
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x05a0 }
        L_0x02bd:
            boolean r12 = r7.hasNext()     // Catch:{ all -> 0x05a0 }
            if (r12 == 0) goto L_0x02b7
            java.lang.Object r12 = r7.next()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.FunSpec r12 = (com.squareup.kotlinpoet.FunSpec) r12     // Catch:{ all -> 0x05a0 }
            boolean r12 = r12.isConstructor()     // Catch:{ all -> 0x05a0 }
            if (r12 == 0) goto L_0x02bd
            r7 = r13
        L_0x02d0:
            if (r7 == 0) goto L_0x02d3
            goto L_0x02de
        L_0x02d3:
            com.squareup.kotlinpoet.CodeBlock$Companion r7 = com.squareup.kotlinpoet.CodeBlock.Companion     // Catch:{ all -> 0x05a0 }
            java.lang.Object[] r5 = new java.lang.Object[]{r5}     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeBlock r5 = r7.of(r6, r5)     // Catch:{ all -> 0x05a0 }
            goto L_0x0301
        L_0x02de:
            if (r14 != 0) goto L_0x02f7
            java.util.Set<com.squareup.kotlinpoet.KModifier> r7 = r1.modifiers     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.KModifier r12 = com.squareup.kotlinpoet.KModifier.EXPECT     // Catch:{ all -> 0x05a0 }
            boolean r7 = r7.contains(r12)     // Catch:{ all -> 0x05a0 }
            if (r7 != 0) goto L_0x02f7
            com.squareup.kotlinpoet.CodeBlock$Companion r6 = com.squareup.kotlinpoet.CodeBlock.Companion     // Catch:{ all -> 0x05a0 }
            java.lang.String r7 = "%T(%L)"
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r3}     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeBlock r5 = r6.of(r7, r5)     // Catch:{ all -> 0x05a0 }
            goto L_0x0301
        L_0x02f7:
            com.squareup.kotlinpoet.CodeBlock$Companion r7 = com.squareup.kotlinpoet.CodeBlock.Companion     // Catch:{ all -> 0x05a0 }
            java.lang.Object[] r5 = new java.lang.Object[]{r5}     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeBlock r5 = r7.of(r6, r5)     // Catch:{ all -> 0x05a0 }
        L_0x0301:
            r2.add(r5)     // Catch:{ all -> 0x05a0 }
            goto L_0x0294
        L_0x0305:
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x05a0 }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x05a0 }
            java.util.Map<com.squareup.kotlinpoet.TypeName, com.squareup.kotlinpoet.CodeBlock> r3 = r1.superinterfaces     // Catch:{ all -> 0x05a0 }
            java.util.Set r3 = r3.entrySet()     // Catch:{ all -> 0x05a0 }
            java.lang.Iterable r3 = (java.lang.Iterable) r3     // Catch:{ all -> 0x05a0 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x05a0 }
            r5 = 10
            int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r3, r5)     // Catch:{ all -> 0x05a0 }
            r4.<init>(r5)     // Catch:{ all -> 0x05a0 }
            java.util.Collection r4 = (java.util.Collection) r4     // Catch:{ all -> 0x05a0 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x05a0 }
        L_0x0322:
            boolean r5 = r3.hasNext()     // Catch:{ all -> 0x05a0 }
            if (r5 == 0) goto L_0x0358
            java.lang.Object r5 = r3.next()     // Catch:{ all -> 0x05a0 }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ all -> 0x05a0 }
            java.lang.Object r7 = r5.getKey()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeName r7 = (com.squareup.kotlinpoet.TypeName) r7     // Catch:{ all -> 0x05a0 }
            java.lang.Object r5 = r5.getValue()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeBlock r5 = (com.squareup.kotlinpoet.CodeBlock) r5     // Catch:{ all -> 0x05a0 }
            if (r5 != 0) goto L_0x0347
            com.squareup.kotlinpoet.CodeBlock$Companion r5 = com.squareup.kotlinpoet.CodeBlock.Companion     // Catch:{ all -> 0x05a0 }
            java.lang.Object[] r7 = new java.lang.Object[]{r7}     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeBlock r5 = r5.of(r6, r7)     // Catch:{ all -> 0x05a0 }
            goto L_0x0353
        L_0x0347:
            com.squareup.kotlinpoet.CodeBlock$Companion r12 = com.squareup.kotlinpoet.CodeBlock.Companion     // Catch:{ all -> 0x05a0 }
            java.lang.String r13 = "%T by %L"
            java.lang.Object[] r5 = new java.lang.Object[]{r7, r5}     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeBlock r5 = r12.of(r13, r5)     // Catch:{ all -> 0x05a0 }
        L_0x0353:
            r4.add(r5)     // Catch:{ all -> 0x05a0 }
            r13 = 0
            goto L_0x0322
        L_0x0358:
            java.util.List r4 = (java.util.List) r4     // Catch:{ all -> 0x05a0 }
            java.lang.Iterable r4 = (java.lang.Iterable) r4     // Catch:{ all -> 0x05a0 }
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r2, r4)     // Catch:{ all -> 0x05a0 }
            r3 = r2
            java.util.Collection r3 = (java.util.Collection) r3     // Catch:{ all -> 0x05a0 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x05a0 }
            r4 = 1
            r3 = r3 ^ r4
            if (r3 == 0) goto L_0x038e
            r20 = r2
            java.util.Collection r20 = (java.util.Collection) r20     // Catch:{ all -> 0x05a0 }
            java.lang.String r2 = ", "
            r21 = r2
            java.lang.CharSequence r21 = (java.lang.CharSequence) r21     // Catch:{ all -> 0x05a0 }
            java.lang.String r2 = " : "
            r22 = r2
            java.lang.CharSequence r22 = (java.lang.CharSequence) r22     // Catch:{ all -> 0x05a0 }
            r23 = 0
            r24 = 4
            r25 = 0
            com.squareup.kotlinpoet.CodeBlock r3 = com.squareup.kotlinpoet.CodeBlocks.joinToCode$default(r20, r21, r22, r23, r24, r25)     // Catch:{ all -> 0x05a0 }
            r4 = 0
            r5 = 0
            r6 = 6
            r7 = 0
            r2 = r29
            com.squareup.kotlinpoet.CodeWriter.emitCode$default(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x05a0 }
        L_0x038e:
            java.util.List<com.squareup.kotlinpoet.TypeVariableName> r2 = r1.typeVariables     // Catch:{ all -> 0x05a0 }
            r11.emitWhereBlock(r2)     // Catch:{ all -> 0x05a0 }
            boolean r2 = r28.getHasNoBody()     // Catch:{ all -> 0x05a0 }
            if (r2 == 0) goto L_0x03a5
            r12 = r19
            r2 = 2
            r3 = 0
            r4 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r12, r4, r2, r3)     // Catch:{ all -> 0x05a0 }
            r11.setStatementLine(r15)
            return
        L_0x03a5:
            r12 = r19
            r2 = 2
            r3 = 0
            r4 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r8, r4, r2, r3)     // Catch:{ all -> 0x05a0 }
        L_0x03ad:
            r11.pushType(r1)     // Catch:{ all -> 0x05a0 }
            r2 = 1
            com.squareup.kotlinpoet.CodeWriter.indent$default(r11, r4, r2, r3)     // Catch:{ all -> 0x05a0 }
            kotlin.jvm.internal.Ref$BooleanRef r13 = new kotlin.jvm.internal.Ref$BooleanRef     // Catch:{ all -> 0x05a0 }
            r13.<init>()     // Catch:{ all -> 0x05a0 }
            r13.element = r2     // Catch:{ all -> 0x05a0 }
            java.util.Map<java.lang.String, com.squareup.kotlinpoet.TypeSpec> r2 = r1.enumConstants     // Catch:{ all -> 0x05a0 }
            java.util.Set r2 = r2.entrySet()     // Catch:{ all -> 0x05a0 }
            java.util.Iterator r18 = r2.iterator()     // Catch:{ all -> 0x05a0 }
        L_0x03c5:
            boolean r2 = r18.hasNext()     // Catch:{ all -> 0x05a0 }
            if (r2 == 0) goto L_0x03fb
            java.lang.Object r2 = r18.next()     // Catch:{ all -> 0x05a0 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x05a0 }
            java.lang.Object r3 = r2.getKey()     // Catch:{ all -> 0x05a0 }
            r4 = r3
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x05a0 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeSpec r2 = (com.squareup.kotlinpoet.TypeSpec) r2     // Catch:{ all -> 0x05a0 }
            boolean r3 = r13.element     // Catch:{ all -> 0x05a0 }
            if (r3 != 0) goto L_0x03e8
            r3 = 2
            r5 = 0
            r6 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r12, r6, r3, r5)     // Catch:{ all -> 0x05a0 }
        L_0x03e8:
            r5 = 0
            r6 = 0
            r7 = 12
            r8 = 0
            r3 = r29
            emit$kotlinpoet$default(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x05a0 }
            r2 = 2
            r3 = 0
            r4 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r9, r4, r2, r3)     // Catch:{ all -> 0x05a0 }
            r13.element = r4     // Catch:{ all -> 0x05a0 }
            goto L_0x03c5
        L_0x03fb:
            boolean r2 = r1.isEnum     // Catch:{ all -> 0x05a0 }
            if (r2 == 0) goto L_0x0433
            boolean r2 = r13.element     // Catch:{ all -> 0x05a0 }
            if (r2 != 0) goto L_0x0409
            r2 = 2
            r3 = 0
            r4 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r12, r4, r2, r3)     // Catch:{ all -> 0x05a0 }
        L_0x0409:
            java.util.List<com.squareup.kotlinpoet.PropertySpec> r2 = r1.propertySpecs     // Catch:{ all -> 0x05a0 }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x05a0 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x05a0 }
            r3 = 1
            r2 = r2 ^ r3
            if (r2 != 0) goto L_0x042b
            java.util.List<com.squareup.kotlinpoet.FunSpec> r2 = r1.funSpecs     // Catch:{ all -> 0x05a0 }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x05a0 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x05a0 }
            r2 = r2 ^ r3
            if (r2 != 0) goto L_0x042b
            java.util.List<com.squareup.kotlinpoet.TypeSpec> r2 = r1.typeSpecs     // Catch:{ all -> 0x05a0 }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x05a0 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x05a0 }
            r2 = r2 ^ r3
            if (r2 == 0) goto L_0x0433
        L_0x042b:
            java.lang.String r2 = ";\n"
            r3 = 2
            r4 = 0
            r5 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r2, r5, r3, r4)     // Catch:{ all -> 0x05a0 }
        L_0x0433:
            boolean r9 = r28.getHasInitializer()     // Catch:{ all -> 0x05a0 }
            kotlin.jvm.internal.Ref$BooleanRef r8 = new kotlin.jvm.internal.Ref$BooleanRef     // Catch:{ all -> 0x05a0 }
            r8.<init>()     // Catch:{ all -> 0x05a0 }
            java.util.List<com.squareup.kotlinpoet.PropertySpec> r2 = r1.propertySpecs     // Catch:{ all -> 0x05a0 }
            java.util.Iterator r18 = r2.iterator()     // Catch:{ all -> 0x05a0 }
            r2 = 0
        L_0x0443:
            boolean r3 = r18.hasNext()     // Catch:{ all -> 0x05a0 }
            if (r3 == 0) goto L_0x04a2
            int r19 = r2 + 1
            java.lang.Object r3 = r18.next()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.PropertySpec r3 = (com.squareup.kotlinpoet.PropertySpec) r3     // Catch:{ all -> 0x05a0 }
            int r4 = r1.initializerIndex     // Catch:{ all -> 0x05a0 }
            if (r2 != r4) goto L_0x0458
            emit$possiblyEmitInitializer(r8, r9, r13, r11, r1)     // Catch:{ all -> 0x05a0 }
        L_0x0458:
            java.lang.String r2 = r3.getName()     // Catch:{ all -> 0x05a0 }
            boolean r2 = r10.containsKey(r2)     // Catch:{ all -> 0x05a0 }
            if (r2 == 0) goto L_0x0469
            r26 = r8
            r27 = r9
            r20 = r10
            goto L_0x0499
        L_0x0469:
            boolean r2 = r13.element     // Catch:{ all -> 0x05a0 }
            if (r2 != 0) goto L_0x0473
            r2 = 2
            r4 = 0
            r5 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r12, r5, r2, r4)     // Catch:{ all -> 0x05a0 }
        L_0x0473:
            com.squareup.kotlinpoet.TypeSpec$Kind r2 = r1.kind     // Catch:{ all -> 0x05a0 }
            java.util.Set<com.squareup.kotlinpoet.KModifier> r4 = r1.modifiers     // Catch:{ all -> 0x05a0 }
            java.util.Set r4 = r2.implicitPropertyModifiers$kotlinpoet(r4)     // Catch:{ all -> 0x05a0 }
            r5 = 0
            r6 = 0
            r7 = 0
            r20 = 0
            r21 = 60
            r22 = 0
            r2 = r3
            r3 = r29
            r26 = r8
            r8 = r20
            r27 = r9
            r9 = r21
            r20 = r10
            r10 = r22
            com.squareup.kotlinpoet.PropertySpec.emit$kotlinpoet$default(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x05a0 }
            r2 = 0
            r13.element = r2     // Catch:{ all -> 0x05a0 }
        L_0x0499:
            r2 = r19
            r10 = r20
            r8 = r26
            r9 = r27
            goto L_0x0443
        L_0x04a2:
            r3 = r8
            r2 = r9
            emit$possiblyEmitInitializer(r3, r2, r13, r11, r1)     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.FunSpec r2 = r1.primaryConstructor     // Catch:{ all -> 0x05a0 }
            if (r2 == 0) goto L_0x04dc
            com.squareup.kotlinpoet.CodeBlock r2 = r2.getBody()     // Catch:{ all -> 0x05a0 }
            boolean r2 = r2.isNotEmpty()     // Catch:{ all -> 0x05a0 }
            if (r2 == 0) goto L_0x04dc
            java.lang.String r2 = "init {\n"
            r3 = 2
            r4 = 0
            r5 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r2, r5, r3, r4)     // Catch:{ all -> 0x05a0 }
            r2 = 1
            com.squareup.kotlinpoet.CodeWriter.indent$default(r11, r5, r2, r4)     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.FunSpec r2 = r1.primaryConstructor     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.CodeBlock r3 = r2.getBody()     // Catch:{ all -> 0x05a0 }
            r4 = 0
            r5 = 0
            r6 = 6
            r7 = 0
            r2 = r29
            com.squareup.kotlinpoet.CodeWriter.emitCode$default(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x05a0 }
            r2 = 1
            r3 = 0
            r4 = 0
            com.squareup.kotlinpoet.CodeWriter.unindent$default(r11, r4, r2, r3)     // Catch:{ all -> 0x05a0 }
            java.lang.String r2 = "}\n"
            r5 = 2
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r2, r4, r5, r3)     // Catch:{ all -> 0x05a0 }
        L_0x04dc:
            java.util.List<com.squareup.kotlinpoet.FunSpec> r2 = r1.funSpecs     // Catch:{ all -> 0x05a0 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x05a0 }
        L_0x04e2:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x05a0 }
            if (r3 == 0) goto L_0x0516
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.FunSpec r3 = (com.squareup.kotlinpoet.FunSpec) r3     // Catch:{ all -> 0x05a0 }
            boolean r4 = r3.isConstructor()     // Catch:{ all -> 0x05a0 }
            if (r4 == 0) goto L_0x04e2
            boolean r4 = r13.element     // Catch:{ all -> 0x05a0 }
            if (r4 != 0) goto L_0x04fe
            r4 = 2
            r5 = 0
            r6 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r12, r6, r4, r5)     // Catch:{ all -> 0x05a0 }
        L_0x04fe:
            java.lang.String r4 = r1.name     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeSpec$Kind r5 = r1.kind     // Catch:{ all -> 0x05a0 }
            java.util.Set<com.squareup.kotlinpoet.KModifier> r6 = r1.modifiers     // Catch:{ all -> 0x05a0 }
            r7 = r0
            java.lang.Iterable r7 = (java.lang.Iterable) r7     // Catch:{ all -> 0x05a0 }
            java.util.Set r6 = kotlin.collections.SetsKt.plus(r6, r7)     // Catch:{ all -> 0x05a0 }
            java.util.Set r5 = r5.implicitFunctionModifiers$kotlinpoet(r6)     // Catch:{ all -> 0x05a0 }
            r6 = 0
            r3.emit$kotlinpoet(r11, r4, r5, r6)     // Catch:{ all -> 0x05a0 }
            r13.element = r6     // Catch:{ all -> 0x05a0 }
            goto L_0x04e2
        L_0x0516:
            java.util.List<com.squareup.kotlinpoet.FunSpec> r2 = r1.funSpecs     // Catch:{ all -> 0x05a0 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x05a0 }
        L_0x051c:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x05a0 }
            if (r3 == 0) goto L_0x0551
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.FunSpec r3 = (com.squareup.kotlinpoet.FunSpec) r3     // Catch:{ all -> 0x05a0 }
            boolean r4 = r3.isConstructor()     // Catch:{ all -> 0x05a0 }
            if (r4 != 0) goto L_0x051c
            boolean r4 = r13.element     // Catch:{ all -> 0x05a0 }
            if (r4 != 0) goto L_0x0538
            r4 = 2
            r5 = 0
            r6 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r12, r6, r4, r5)     // Catch:{ all -> 0x05a0 }
        L_0x0538:
            java.lang.String r4 = r1.name     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeSpec$Kind r5 = r1.kind     // Catch:{ all -> 0x05a0 }
            java.util.Set<com.squareup.kotlinpoet.KModifier> r6 = r1.modifiers     // Catch:{ all -> 0x05a0 }
            r7 = r0
            java.lang.Iterable r7 = (java.lang.Iterable) r7     // Catch:{ all -> 0x05a0 }
            java.util.Set r6 = kotlin.collections.SetsKt.plus(r6, r7)     // Catch:{ all -> 0x05a0 }
            java.util.Set r5 = r5.implicitFunctionModifiers$kotlinpoet(r6)     // Catch:{ all -> 0x05a0 }
            r6 = 1
            r3.emit$kotlinpoet(r11, r4, r5, r6)     // Catch:{ all -> 0x05a0 }
            r3 = 0
            r13.element = r3     // Catch:{ all -> 0x05a0 }
            goto L_0x051c
        L_0x0551:
            java.util.List<com.squareup.kotlinpoet.TypeSpec> r2 = r1.typeSpecs     // Catch:{ all -> 0x05a0 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x05a0 }
        L_0x0557:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x05a0 }
            if (r3 == 0) goto L_0x0584
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x05a0 }
            com.squareup.kotlinpoet.TypeSpec r3 = (com.squareup.kotlinpoet.TypeSpec) r3     // Catch:{ all -> 0x05a0 }
            boolean r4 = r13.element     // Catch:{ all -> 0x05a0 }
            if (r4 != 0) goto L_0x056d
            r4 = 2
            r5 = 0
            r6 = 0
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r12, r6, r4, r5)     // Catch:{ all -> 0x05a0 }
        L_0x056d:
            com.squareup.kotlinpoet.TypeSpec$Kind r4 = r1.kind     // Catch:{ all -> 0x05a0 }
            java.util.Set<com.squareup.kotlinpoet.KModifier> r5 = r1.modifiers     // Catch:{ all -> 0x05a0 }
            r6 = r0
            java.lang.Iterable r6 = (java.lang.Iterable) r6     // Catch:{ all -> 0x05a0 }
            java.util.Set r5 = kotlin.collections.SetsKt.plus(r5, r6)     // Catch:{ all -> 0x05a0 }
            java.util.Set r4 = r4.implicitTypeModifiers$kotlinpoet(r5)     // Catch:{ all -> 0x05a0 }
            r5 = 0
            r3.emit$kotlinpoet(r11, r5, r4, r14)     // Catch:{ all -> 0x05a0 }
            r3 = 0
            r13.element = r3     // Catch:{ all -> 0x05a0 }
            goto L_0x0557
        L_0x0584:
            r0 = 1
            r2 = 0
            r3 = 0
            com.squareup.kotlinpoet.CodeWriter.unindent$default(r11, r3, r0, r2)     // Catch:{ all -> 0x05a0 }
            r29.popType()     // Catch:{ all -> 0x05a0 }
            java.lang.String r0 = "}"
            r4 = 2
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r0, r3, r4, r2)     // Catch:{ all -> 0x05a0 }
            if (r30 != 0) goto L_0x059c
            boolean r0 = r1.isAnonymousClass     // Catch:{ all -> 0x05a0 }
            if (r0 != 0) goto L_0x059c
            com.squareup.kotlinpoet.CodeWriter.emit$default(r11, r12, r3, r4, r2)     // Catch:{ all -> 0x05a0 }
        L_0x059c:
            r11.setStatementLine(r15)
            return
        L_0x05a0:
            r0 = move-exception
            r11.setStatementLine(r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.TypeSpec.emit$kotlinpoet(com.squareup.kotlinpoet.CodeWriter, java.lang.String, java.util.Set, boolean):void");
    }

    private static final void emit$possiblyEmitInitializer(Ref.BooleanRef booleanRef, boolean z, Ref.BooleanRef booleanRef2, CodeWriter codeWriter, TypeSpec typeSpec) {
        if (!booleanRef.element) {
            booleanRef.element = true;
            if (z) {
                if (!booleanRef2.element) {
                    CodeWriter.emit$default(codeWriter, "\n", false, 2, (Object) null);
                }
                CodeWriter.emitCode$default(codeWriter, typeSpec.initializerBlock, false, false, 6, (Object) null);
                booleanRef2.element = false;
            }
        }
    }

    private final Map<String, PropertySpec> constructorProperties() {
        IntRange intRange;
        ParameterSpec parameter$kotlinpoet;
        if (this.primaryConstructor == null) {
            return MapsKt.emptyMap();
        }
        if (getHasInitializer()) {
            intRange = RangesKt.until(0, this.initializerIndex);
        } else {
            intRange = CollectionsKt.getIndices(this.propertySpecs);
        }
        Map<String, PropertySpec> linkedHashMap = new LinkedHashMap<>();
        int first = intRange.getFirst();
        int last = intRange.getLast();
        if (first <= last) {
            while (true) {
                PropertySpec propertySpec = this.propertySpecs.get(first);
                if (propertySpec.getGetter() == null && propertySpec.getSetter() == null && (parameter$kotlinpoet = this.primaryConstructor.parameter$kotlinpoet(propertySpec.getName())) != null && Intrinsics.areEqual((Object) parameter$kotlinpoet.getType(), (Object) propertySpec.getType()) && isPropertyInitializerConstructorParameter(propertySpec, parameter$kotlinpoet)) {
                    linkedHashMap.put(propertySpec.getName(), propertySpec.fromPrimaryConstructorParameter$kotlinpoet(parameter$kotlinpoet));
                }
                if (first == last) {
                    break;
                }
                first++;
            }
        }
        return linkedHashMap;
    }

    private final boolean isPropertyInitializerConstructorParameter(PropertySpec propertySpec, ParameterSpec parameterSpec) {
        return Intrinsics.areEqual((Object) CodeBlock.Companion.of("%N", parameterSpec).toString(), (Object) UtilKt.escapeIfNecessary(String.valueOf(propertySpec.getInitializer()), false));
    }

    private final CodeBlock kdocWithConstructorParameters() {
        CodeBlock codeBlock;
        FunSpec funSpec = this.primaryConstructor;
        if (funSpec == null || funSpec.getParameters().isEmpty()) {
            return UtilKt.ensureEndsWithNewLine(this.kdoc);
        }
        Map<String, PropertySpec> constructorProperties = constructorProperties();
        Collection arrayList = new ArrayList();
        Iterator it = this.primaryConstructor.getParameters().iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            ParameterSpec parameterSpec = (ParameterSpec) next;
            PropertySpec propertySpec = constructorProperties.get(parameterSpec.getName());
            if (propertySpec == null || (codeBlock = propertySpec.getKdoc()) == null) {
                codeBlock = CodeBlock.Companion.getEMPTY$kotlinpoet();
            }
            if (parameterSpec.getKdoc().isNotEmpty() && codeBlock.isNotEmpty() && !Intrinsics.areEqual((Object) parameterSpec.getKdoc(), (Object) codeBlock)) {
                z = true;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        CodeBlock.Builder builder = UtilKt.ensureEndsWithNewLine(this.kdoc).toBuilder();
        int i = 0;
        for (Object next2 : (List) arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ParameterSpec parameterSpec2 = (ParameterSpec) next2;
            if (i == 0 && this.kdoc.isNotEmpty()) {
                builder.add("\n", new Object[0]);
            }
            builder.add("@param %L %L", parameterSpec2.getName(), UtilKt.ensureEndsWithNewLine(parameterSpec2.getKdoc()));
            i = i2;
        }
        return builder.build();
    }

    private final boolean getHasInitializer() {
        return this.initializerIndex != -1 && this.initializerBlock.isNotEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        r0 = r0.getBody();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean getHasNoBody() {
        /*
            r5 = this;
            java.util.List<com.squareup.kotlinpoet.PropertySpec> r0 = r5.propertySpecs
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r1 = 1
            r0 = r0 ^ r1
            r2 = 0
            if (r0 == 0) goto L_0x002e
            java.util.Map r0 = r5.constructorProperties()
            java.util.List<com.squareup.kotlinpoet.PropertySpec> r3 = r5.propertySpecs
            java.util.Iterator r3 = r3.iterator()
        L_0x0017:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x002e
            java.lang.Object r4 = r3.next()
            com.squareup.kotlinpoet.PropertySpec r4 = (com.squareup.kotlinpoet.PropertySpec) r4
            java.lang.String r4 = r4.getName()
            boolean r4 = r0.containsKey(r4)
            if (r4 != 0) goto L_0x0017
            return r2
        L_0x002e:
            java.util.Map<java.lang.String, com.squareup.kotlinpoet.TypeSpec> r0 = r5.enumConstants
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0061
            com.squareup.kotlinpoet.CodeBlock r0 = r5.initializerBlock
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0061
            com.squareup.kotlinpoet.FunSpec r0 = r5.primaryConstructor
            if (r0 == 0) goto L_0x004d
            com.squareup.kotlinpoet.CodeBlock r0 = r0.getBody()
            if (r0 == 0) goto L_0x004d
            boolean r0 = r0.isEmpty()
            goto L_0x004e
        L_0x004d:
            r0 = r1
        L_0x004e:
            if (r0 == 0) goto L_0x0061
            java.util.List<com.squareup.kotlinpoet.FunSpec> r0 = r5.funSpecs
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0061
            java.util.List<com.squareup.kotlinpoet.TypeSpec> r0 = r5.typeSpecs
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0061
            goto L_0x0062
        L_0x0061:
            r1 = r2
        L_0x0062:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.TypeSpec.getHasNoBody():boolean");
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

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B9\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\tJ#\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¢\u0006\u0002\b\u0012J!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¢\u0006\u0002\b\u0014J#\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¢\u0006\u0002\b\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rj\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/squareup/kotlinpoet/TypeSpec$Kind;", "", "declarationKeyword", "", "defaultImplicitPropertyModifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "defaultImplicitFunctionModifiers", "defaultImplicitTypeModifiers", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;)V", "getDeclarationKeyword$kotlinpoet", "()Ljava/lang/String;", "getDefaultImplicitFunctionModifiers$kotlinpoet", "()Ljava/util/Set;", "getDefaultImplicitPropertyModifiers$kotlinpoet", "getDefaultImplicitTypeModifiers$kotlinpoet", "implicitFunctionModifiers", "modifiers", "implicitFunctionModifiers$kotlinpoet", "implicitPropertyModifiers", "implicitPropertyModifiers$kotlinpoet", "implicitTypeModifiers", "implicitTypeModifiers$kotlinpoet", "CLASS", "OBJECT", "INTERFACE", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: TypeSpec.kt */
    public enum Kind {
        CLASS("class", SetsKt.setOf(KModifier.PUBLIC), SetsKt.setOf(KModifier.PUBLIC), SetsKt.emptySet()),
        OBJECT("object", SetsKt.setOf(KModifier.PUBLIC), SetsKt.setOf(KModifier.PUBLIC), SetsKt.emptySet()),
        INTERFACE("interface", SetsKt.setOf(KModifier.PUBLIC, KModifier.ABSTRACT), SetsKt.setOf(KModifier.PUBLIC, KModifier.ABSTRACT), SetsKt.emptySet());
        
        private final String declarationKeyword;
        private final Set<KModifier> defaultImplicitFunctionModifiers;
        private final Set<KModifier> defaultImplicitPropertyModifiers;
        private final Set<KModifier> defaultImplicitTypeModifiers;

        private Kind(String str, Set<? extends KModifier> set, Set<? extends KModifier> set2, Set<? extends KModifier> set3) {
            this.declarationKeyword = str;
            this.defaultImplicitPropertyModifiers = set;
            this.defaultImplicitFunctionModifiers = set2;
            this.defaultImplicitTypeModifiers = set3;
        }

        public final String getDeclarationKeyword$kotlinpoet() {
            return this.declarationKeyword;
        }

        public final Set<KModifier> getDefaultImplicitPropertyModifiers$kotlinpoet() {
            return this.defaultImplicitPropertyModifiers;
        }

        public final Set<KModifier> getDefaultImplicitFunctionModifiers$kotlinpoet() {
            return this.defaultImplicitFunctionModifiers;
        }

        public final Set<KModifier> getDefaultImplicitTypeModifiers$kotlinpoet() {
            return this.defaultImplicitTypeModifiers;
        }

        public final Set<KModifier> implicitPropertyModifiers$kotlinpoet(Set<? extends KModifier> set) {
            Set set2;
            Intrinsics.checkNotNullParameter(set, "modifiers");
            Set<KModifier> set3 = this.defaultImplicitPropertyModifiers;
            if (set.contains(KModifier.ANNOTATION)) {
                set2 = SetsKt.emptySet();
            } else if (set.contains(KModifier.EXPECT)) {
                set2 = SetsKt.setOf(KModifier.EXPECT);
            } else if (set.contains(KModifier.EXTERNAL)) {
                set2 = SetsKt.setOf(KModifier.EXTERNAL);
            } else {
                set2 = SetsKt.emptySet();
            }
            return SetsKt.plus(set3, set2);
        }

        public final Set<KModifier> implicitFunctionModifiers$kotlinpoet(Set<? extends KModifier> set) {
            Set set2;
            Intrinsics.checkNotNullParameter(set, "modifiers");
            Set<KModifier> set3 = this.defaultImplicitFunctionModifiers;
            if (set.contains(KModifier.ANNOTATION)) {
                set2 = SetsKt.setOf(KModifier.ABSTRACT);
            } else if (set.contains(KModifier.EXPECT)) {
                set2 = SetsKt.setOf(KModifier.EXPECT);
            } else if (set.contains(KModifier.EXTERNAL)) {
                set2 = SetsKt.setOf(KModifier.EXTERNAL);
            } else {
                set2 = SetsKt.emptySet();
            }
            return SetsKt.plus(set3, set2);
        }

        public final Set<KModifier> implicitTypeModifiers$kotlinpoet(Set<? extends KModifier> set) {
            Set set2;
            Intrinsics.checkNotNullParameter(set, "modifiers");
            Set<KModifier> set3 = this.defaultImplicitTypeModifiers;
            if (set.contains(KModifier.EXPECT)) {
                set2 = SetsKt.setOf(KModifier.EXPECT);
            } else if (set.contains(KModifier.EXTERNAL)) {
                set2 = SetsKt.setOf(KModifier.EXTERNAL);
            } else {
                set2 = SetsKt.emptySet();
            }
            return SetsKt.plus(set3, set2);
        }
    }

    @Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B-\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010^\u001a\u00020\u00002\u0006\u0010_\u001a\u00020\rJ\u000e\u0010^\u001a\u00020\u00002\u0006\u0010`\u001a\u00020aJ\u0014\u0010^\u001a\u00020\u00002\n\u0010`\u001a\u0006\u0012\u0002\b\u00030bH\u0007J\u0012\u0010^\u001a\u00020\u00002\n\u0010`\u001a\u0006\u0012\u0002\b\u00030VJ\u0014\u0010c\u001a\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0dJ\u001a\u0010e\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010f\u001a\u00020\u0012H\u0007J\u000e\u0010g\u001a\u00020\u00002\u0006\u0010h\u001a\u00020\u0016J\u0014\u0010i\u001a\u00020\u00002\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160dJ\u000e\u0010j\u001a\u00020\u00002\u0006\u0010k\u001a\u00020QJ\u000e\u0010l\u001a\u00020\u00002\u0006\u0010k\u001a\u00020QJ'\u0010l\u001a\u00020\u00002\u0006\u0010m\u001a\u00020\u00062\u0012\u0010n\u001a\n\u0012\u0006\b\u0001\u0012\u00020W0\b\"\u00020W¢\u0006\u0002\u0010oJ\u001f\u0010p\u001a\u00020\u00002\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0002\u0010qJ\u0014\u0010p\u001a\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0dJ\u0014\u0010r\u001a\u00020\u00002\f\u0010G\u001a\b\u0012\u0004\u0012\u00020H0dJ\u000e\u0010s\u001a\u00020\u00002\u0006\u0010t\u001a\u00020HJ/\u0010s\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010u\u001a\u00020K2\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0002\u0010vJ$\u0010s\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010u\u001a\u00020K2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0dJ1\u0010s\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010u\u001a\u00020w2\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0007¢\u0006\u0002\u0010xJ&\u0010s\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010u\u001a\u00020w2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0dH\u0007J3\u0010s\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010u\u001a\u0006\u0012\u0002\b\u00030V2\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0002\u0010yJ(\u0010s\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010u\u001a\u0006\u0012\u0002\b\u00030V2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0dJ\u000e\u0010z\u001a\u00020\u00002\u0006\u0010{\u001a\u00020QJ'\u0010z\u001a\u00020\u00002\u0006\u0010m\u001a\u00020\u00062\u0012\u0010n\u001a\n\u0012\u0006\b\u0001\u0012\u00020W0\b\"\u00020W¢\u0006\u0002\u0010oJ\u0018\u0010|\u001a\u00020\u00002\u0006\u0010}\u001a\u00020K2\b\b\u0002\u0010~\u001a\u00020QJ\u0016\u0010|\u001a\u00020\u00002\u0006\u0010}\u001a\u00020K2\u0006\u0010\u001a\u00020\u0006J\u001a\u0010|\u001a\u00020\u00002\u0006\u0010}\u001a\u00020w2\b\b\u0002\u0010~\u001a\u00020QH\u0007J\u001c\u0010|\u001a\u00020\u00002\n\u0010}\u001a\u0006\u0012\u0002\b\u00030V2\b\b\u0002\u0010~\u001a\u00020QJ\u001b\u0010|\u001a\u00020\u00002\n\u0010}\u001a\u0006\u0012\u0002\b\u00030V2\u0007\u0010\u0001\u001a\u00020\u0006J\u0015\u0010\u0001\u001a\u00020\u00002\f\u0010S\u001a\b\u0012\u0004\u0012\u00020K0dJ\u000f\u0010\u0001\u001a\u00020\u00002\u0006\u0010f\u001a\u00020\u0012J\u0010\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\\J\u0015\u0010\u0001\u001a\u00020\u00002\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0dJ\u0015\u0010\u0001\u001a\u00020\u00002\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00120dJ\u0007\u0010\u0001\u001a\u00020\u0012J\n\u0010\u0001\u001a\u00030\u0001H\u0002J\n\u0010\u0001\u001a\u00030\u0001H\u0002J\u0010\u0010B\u001a\u00020\u00002\b\u0010B\u001a\u0004\u0018\u00010\u0016J\u000e\u0010J\u001a\u00020\u00002\u0006\u0010J\u001a\u00020KJ\u0010\u0010J\u001a\u00020\u00002\u0006\u0010J\u001a\u00020wH\u0007J\u0012\u0010J\u001a\u00020\u00002\n\u0010J\u001a\u0006\u0012\u0002\b\u00030VR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0014\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020#8@X\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020#8@X\u0004¢\u0006\u0006\u001a\u0004\b'\u0010%R\u0014\u0010(\u001a\u00020#8@X\u0004¢\u0006\u0006\u001a\u0004\b)\u0010%R\u0014\u0010*\u001a\u00020#8@X\u0004¢\u0006\u0006\u001a\u0004\b+\u0010%R\u0014\u0010,\u001a\u00020#8@X\u0004¢\u0006\u0006\u001a\u0004\b-\u0010%R\u0014\u0010.\u001a\u00020#8@X\u0004¢\u0006\u0006\u001a\u0004\b/\u0010%R\u0014\u00100\u001a\u00020#8@X\u0004¢\u0006\u0006\u001a\u0004\b1\u0010%R\u0014\u00102\u001a\u00020#8@X\u0004¢\u0006\u0006\u001a\u0004\b3\u0010%R\u0014\u00104\u001a\u00020\u0019X\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001bR\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0:¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u001a\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u000fR\u001c\u0010B\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u0017\u0010G\u001a\b\u0012\u0004\u0012\u00020H0\f¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\u000fR\u001a\u0010J\u001a\u00020KX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u0017\u0010P\u001a\b\u0012\u0004\u0012\u00020Q0\f¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u000fR\u001f\u0010S\u001a\u0010\u0012\u0004\u0012\u00020K\u0012\u0006\u0012\u0004\u0018\u00010Q0\u0011¢\u0006\b\n\u0000\u001a\u0004\bT\u0010\u0014R$\u0010U\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030V\u0012\u0004\u0012\u00020W0\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010\u0014R\u0017\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00120\f¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u000fR\u0017\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0\f¢\u0006\b\n\u0000\u001a\u0004\b]\u0010\u000f¨\u0006\u0001"}, d2 = {"Lcom/squareup/kotlinpoet/TypeSpec$Builder;", "Lcom/squareup/kotlinpoet/Taggable$Builder;", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder$Builder;", "kind", "Lcom/squareup/kotlinpoet/TypeSpec$Kind;", "name", "", "modifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "(Lcom/squareup/kotlinpoet/TypeSpec$Kind;Ljava/lang/String;[Lcom/squareup/kotlinpoet/KModifier;)V", "annotationSpecs", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotationSpecs", "()Ljava/util/List;", "enumConstants", "", "Lcom/squareup/kotlinpoet/TypeSpec;", "getEnumConstants", "()Ljava/util/Map;", "funSpecs", "Lcom/squareup/kotlinpoet/FunSpec;", "getFunSpecs", "initializerBlock", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "getInitializerBlock$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "initializerIndex", "", "getInitializerIndex", "()I", "setInitializerIndex", "(I)V", "isAnnotation", "", "isAnnotation$kotlinpoet", "()Z", "isAnonymousClass", "isAnonymousClass$kotlinpoet", "isCompanion", "isCompanion$kotlinpoet", "isEnum", "isEnum$kotlinpoet", "isExternal", "isExternal$kotlinpoet", "isFunInterface", "isFunInterface$kotlinpoet", "isInlineOrValClass", "isInlineOrValClass$kotlinpoet", "isSimpleClass", "isSimpleClass$kotlinpoet", "kdoc", "getKdoc$kotlinpoet", "getKind$kotlinpoet", "()Lcom/squareup/kotlinpoet/TypeSpec$Kind;", "setKind$kotlinpoet", "(Lcom/squareup/kotlinpoet/TypeSpec$Kind;)V", "", "getModifiers", "()Ljava/util/Set;", "getName$kotlinpoet", "()Ljava/lang/String;", "originatingElements", "Ljavax/lang/model/element/Element;", "getOriginatingElements", "primaryConstructor", "getPrimaryConstructor$kotlinpoet", "()Lcom/squareup/kotlinpoet/FunSpec;", "setPrimaryConstructor$kotlinpoet", "(Lcom/squareup/kotlinpoet/FunSpec;)V", "propertySpecs", "Lcom/squareup/kotlinpoet/PropertySpec;", "getPropertySpecs", "superclass", "Lcom/squareup/kotlinpoet/TypeName;", "getSuperclass$kotlinpoet", "()Lcom/squareup/kotlinpoet/TypeName;", "setSuperclass$kotlinpoet", "(Lcom/squareup/kotlinpoet/TypeName;)V", "superclassConstructorParameters", "Lcom/squareup/kotlinpoet/CodeBlock;", "getSuperclassConstructorParameters", "superinterfaces", "getSuperinterfaces", "tags", "Lkotlin/reflect/KClass;", "", "getTags", "typeSpecs", "getTypeSpecs", "typeVariables", "Lcom/squareup/kotlinpoet/TypeVariableName;", "getTypeVariables", "addAnnotation", "annotationSpec", "annotation", "Lcom/squareup/kotlinpoet/ClassName;", "Ljava/lang/Class;", "addAnnotations", "", "addEnumConstant", "typeSpec", "addFunction", "funSpec", "addFunctions", "addInitializerBlock", "block", "addKdoc", "format", "args", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/TypeSpec$Builder;", "addModifiers", "([Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/TypeSpec$Builder;", "addProperties", "addProperty", "propertySpec", "type", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/TypeName;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/TypeSpec$Builder;", "Ljava/lang/reflect/Type;", "(Ljava/lang/String;Ljava/lang/reflect/Type;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/TypeSpec$Builder;", "(Ljava/lang/String;Lkotlin/reflect/KClass;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/TypeSpec$Builder;", "addSuperclassConstructorParameter", "codeBlock", "addSuperinterface", "superinterface", "delegate", "constructorParameter", "constructorParameterName", "addSuperinterfaces", "addType", "addTypeVariable", "typeVariable", "addTypeVariables", "addTypes", "build", "checkCanHaveInitializerBlocks", "", "checkCanHaveSuperclass", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: TypeSpec.kt */
    public static final class Builder implements Taggable.Builder<Builder>, OriginatingElementsHolder.Builder<Builder> {
        private final List<AnnotationSpec> annotationSpecs;
        private final Map<String, TypeSpec> enumConstants;
        private final List<FunSpec> funSpecs;
        private final CodeBlock.Builder initializerBlock = CodeBlock.Companion.builder();
        private int initializerIndex = -1;
        private final CodeBlock.Builder kdoc = CodeBlock.Companion.builder();
        private Kind kind;
        private final Set<KModifier> modifiers;
        private final String name;
        private final List<Element> originatingElements = new ArrayList();
        private FunSpec primaryConstructor;
        private final List<PropertySpec> propertySpecs;
        private TypeName superclass = TypeNames.ANY;
        private final List<CodeBlock> superclassConstructorParameters;
        private final Map<TypeName, CodeBlock> superinterfaces;
        private final Map<KClass<?>, Object> tags = new LinkedHashMap();
        private final List<TypeSpec> typeSpecs;
        private final List<TypeVariableName> typeVariables;

        public final Builder addEnumConstant(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return addEnumConstant$default(this, str, (TypeSpec) null, 2, (Object) null);
        }

        public Builder(Kind kind2, String str, KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(kind2, "kind");
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            this.kind = kind2;
            this.name = str;
            this.modifiers = SetsKt.mutableSetOf(Arrays.copyOf(kModifierArr, kModifierArr.length));
            this.superinterfaces = new LinkedHashMap();
            this.enumConstants = new LinkedHashMap();
            this.annotationSpecs = new ArrayList();
            this.typeVariables = new ArrayList();
            this.superclassConstructorParameters = new ArrayList();
            this.propertySpecs = new ArrayList();
            this.funSpecs = new ArrayList();
            this.typeSpecs = new ArrayList();
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

        public final Kind getKind$kotlinpoet() {
            return this.kind;
        }

        public final void setKind$kotlinpoet(Kind kind2) {
            Intrinsics.checkNotNullParameter(kind2, "<set-?>");
            this.kind = kind2;
        }

        public final String getName$kotlinpoet() {
            return this.name;
        }

        public final CodeBlock.Builder getKdoc$kotlinpoet() {
            return this.kdoc;
        }

        public final FunSpec getPrimaryConstructor$kotlinpoet() {
            return this.primaryConstructor;
        }

        public final void setPrimaryConstructor$kotlinpoet(FunSpec funSpec) {
            this.primaryConstructor = funSpec;
        }

        public final TypeName getSuperclass$kotlinpoet() {
            return this.superclass;
        }

        public final void setSuperclass$kotlinpoet(TypeName typeName) {
            Intrinsics.checkNotNullParameter(typeName, "<set-?>");
            this.superclass = typeName;
        }

        public final CodeBlock.Builder getInitializerBlock$kotlinpoet() {
            return this.initializerBlock;
        }

        public final int getInitializerIndex() {
            return this.initializerIndex;
        }

        public final void setInitializerIndex(int i) {
            this.initializerIndex = i;
        }

        public final boolean isAnonymousClass$kotlinpoet() {
            return this.name == null && this.kind == Kind.CLASS;
        }

        public final boolean isExternal$kotlinpoet() {
            return this.modifiers.contains(KModifier.EXTERNAL);
        }

        public final boolean isEnum$kotlinpoet() {
            return this.kind == Kind.CLASS && this.modifiers.contains(KModifier.ENUM);
        }

        public final boolean isAnnotation$kotlinpoet() {
            return this.kind == Kind.CLASS && this.modifiers.contains(KModifier.ANNOTATION);
        }

        public final boolean isCompanion$kotlinpoet() {
            return this.kind == Kind.OBJECT && this.modifiers.contains(KModifier.COMPANION);
        }

        public final boolean isInlineOrValClass$kotlinpoet() {
            return this.kind == Kind.CLASS && (this.modifiers.contains(KModifier.INLINE) || this.modifiers.contains(KModifier.VALUE));
        }

        public final boolean isSimpleClass$kotlinpoet() {
            return this.kind == Kind.CLASS && !isEnum$kotlinpoet() && !isAnnotation$kotlinpoet();
        }

        public final boolean isFunInterface$kotlinpoet() {
            return this.kind == Kind.INTERFACE && this.modifiers.contains(KModifier.FUN);
        }

        public Map<KClass<?>, Object> getTags() {
            return this.tags;
        }

        public List<Element> getOriginatingElements() {
            return this.originatingElements;
        }

        public final Set<KModifier> getModifiers() {
            return this.modifiers;
        }

        public final Map<TypeName, CodeBlock> getSuperinterfaces() {
            return this.superinterfaces;
        }

        public final Map<String, TypeSpec> getEnumConstants() {
            return this.enumConstants;
        }

        public final List<AnnotationSpec> getAnnotationSpecs() {
            return this.annotationSpecs;
        }

        public final List<TypeVariableName> getTypeVariables() {
            return this.typeVariables;
        }

        public final List<CodeBlock> getSuperclassConstructorParameters() {
            return this.superclassConstructorParameters;
        }

        public final List<PropertySpec> getPropertySpecs() {
            return this.propertySpecs;
        }

        public final List<FunSpec> getFunSpecs() {
            return this.funSpecs;
        }

        public final List<TypeSpec> getTypeSpecs() {
            return this.typeSpecs;
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
            CollectionsKt.addAll(this.annotationSpecs, iterable);
            return this;
        }

        public final Builder addAnnotation(AnnotationSpec annotationSpec) {
            Intrinsics.checkNotNullParameter(annotationSpec, "annotationSpec");
            Builder builder = this;
            this.annotationSpecs.add(annotationSpec);
            return this;
        }

        public final Builder addAnnotation(ClassName className) {
            Intrinsics.checkNotNullParameter(className, "annotation");
            return addAnnotation(AnnotationSpec.Companion.builder(className).build());
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
            if (!isAnonymousClass$kotlinpoet()) {
                CollectionsKt.addAll(this.modifiers, (T[]) kModifierArr);
                return this;
            }
            throw new IllegalStateException("forbidden on anonymous types.".toString());
        }

        public final Builder addModifiers(Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            Builder builder = this;
            if (!isAnonymousClass$kotlinpoet()) {
                CollectionsKt.addAll(this.modifiers, iterable);
                return this;
            }
            throw new IllegalStateException("forbidden on anonymous types.".toString());
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

        public final Builder primaryConstructor(FunSpec funSpec) {
            Builder builder = this;
            boolean z = false;
            if (this.kind == Kind.CLASS) {
                if (funSpec != null) {
                    if (!funSpec.isConstructor()) {
                        throw new IllegalArgumentException(("expected a constructor but was " + funSpec.getName()).toString());
                    } else if (isInlineOrValClass$kotlinpoet()) {
                        if (funSpec.getParameters().size() == 1) {
                            z = true;
                        }
                        if (!z) {
                            throw new IllegalStateException("value/inline classes must have 1 parameter in constructor".toString());
                        }
                    }
                }
                this.primaryConstructor = funSpec;
                return this;
            }
            throw new IllegalStateException((this.kind + " can't have a primary constructor").toString());
        }

        public final Builder superclass(TypeName typeName) {
            Intrinsics.checkNotNullParameter(typeName, "superclass");
            Builder builder = this;
            checkCanHaveSuperclass();
            if (this.superclass == TypeNames.ANY) {
                this.superclass = typeName;
                return this;
            }
            throw new IllegalStateException(("superclass already set to " + this.superclass).toString());
        }

        private final void checkCanHaveSuperclass() {
            if (!(isSimpleClass$kotlinpoet() || this.kind == Kind.OBJECT)) {
                throw new IllegalStateException(("only classes can have super classes, not " + this.kind).toString());
            } else if (!(!isInlineOrValClass$kotlinpoet())) {
                throw new IllegalStateException("value/inline classes cannot have super classes".toString());
            }
        }

        private final void checkCanHaveInitializerBlocks() {
            if (!(isSimpleClass$kotlinpoet() || isEnum$kotlinpoet() || this.kind == Kind.OBJECT)) {
                throw new IllegalStateException((this.kind + " can't have initializer blocks").toString());
            } else if (!(!this.modifiers.contains(KModifier.EXPECT))) {
                throw new IllegalStateException(("expect " + this.kind + " can't have initializer blocks").toString());
            }
        }

        public final Builder superclass(Type type) {
            Intrinsics.checkNotNullParameter(type, "superclass");
            return superclass(TypeNames.get(type));
        }

        public final Builder superclass(KClass<?> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "superclass");
            return superclass((TypeName) TypeNames.get(kClass));
        }

        public final Builder addSuperclassConstructorParameter(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            addSuperclassConstructorParameter(CodeBlock.Companion.of(str, Arrays.copyOf(objArr, objArr.length)));
            return this;
        }

        public final Builder addSuperclassConstructorParameter(CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(codeBlock, "codeBlock");
            Builder builder = this;
            checkCanHaveSuperclass();
            this.superclassConstructorParameters.add(codeBlock);
            return this;
        }

        public final Builder addSuperinterfaces(Iterable<? extends TypeName> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "superinterfaces");
            Builder builder = this;
            Map<TypeName, CodeBlock> map = this.superinterfaces;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (TypeName typeName : iterable) {
                arrayList.add(TuplesKt.to(typeName, null));
            }
            MapsKt.putAll(map, (List) arrayList);
            return this;
        }

        public static /* synthetic */ Builder addSuperinterface$default(Builder builder, TypeName typeName, CodeBlock codeBlock, int i, Object obj) {
            if ((i & 2) != 0) {
                codeBlock = CodeBlock.Companion.getEMPTY$kotlinpoet();
            }
            return builder.addSuperinterface(typeName, codeBlock);
        }

        public final Builder addSuperinterface(TypeName typeName, CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(typeName, "superinterface");
            Intrinsics.checkNotNullParameter(codeBlock, "delegate");
            Builder builder = this;
            if (codeBlock.isEmpty()) {
                this.superinterfaces.put(typeName, (Object) null);
            } else {
                boolean z = false;
                if (!(isSimpleClass$kotlinpoet() || this.kind == Kind.OBJECT)) {
                    throw new IllegalArgumentException(("delegation only allowed for classes and objects (found " + this.kind + " '" + this.name + "')").toString());
                } else if (!typeName.isNullable()) {
                    if (this.superinterfaces.get(typeName) == null) {
                        z = true;
                    }
                    if (z) {
                        this.superinterfaces.put(typeName, codeBlock);
                    } else {
                        throw new IllegalArgumentException(("'" + this.name + "' can not delegate to " + typeName + " by " + codeBlock + " with existing declaration by " + this.superinterfaces.get(typeName)).toString());
                    }
                } else {
                    throw new IllegalArgumentException(("expected non-nullable type but was '" + TypeName.copy$default(typeName, false, (List) null, 2, (Object) null) + '\'').toString());
                }
            }
            return this;
        }

        public static /* synthetic */ Builder addSuperinterface$default(Builder builder, Type type, CodeBlock codeBlock, int i, Object obj) {
            if ((i & 2) != 0) {
                codeBlock = CodeBlock.Companion.getEMPTY$kotlinpoet();
            }
            return builder.addSuperinterface(type, codeBlock);
        }

        public final Builder addSuperinterface(Type type, CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(type, "superinterface");
            Intrinsics.checkNotNullParameter(codeBlock, "delegate");
            return addSuperinterface(TypeNames.get(type), codeBlock);
        }

        public static /* synthetic */ Builder addSuperinterface$default(Builder builder, KClass kClass, CodeBlock codeBlock, int i, Object obj) {
            if ((i & 2) != 0) {
                codeBlock = CodeBlock.Companion.getEMPTY$kotlinpoet();
            }
            return builder.addSuperinterface((KClass<?>) kClass, codeBlock);
        }

        public final Builder addSuperinterface(KClass<?> kClass, CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(kClass, "superinterface");
            Intrinsics.checkNotNullParameter(codeBlock, "delegate");
            return addSuperinterface((TypeName) TypeNames.get(kClass), codeBlock);
        }

        public final Builder addSuperinterface(KClass<?> kClass, String str) {
            Intrinsics.checkNotNullParameter(kClass, "superinterface");
            Intrinsics.checkNotNullParameter(str, "constructorParameterName");
            return addSuperinterface((TypeName) TypeNames.get(kClass), str);
        }

        public final Builder addSuperinterface(TypeName typeName, String str) {
            Intrinsics.checkNotNullParameter(typeName, "superinterface");
            Intrinsics.checkNotNullParameter(str, "constructorParameter");
            Builder builder = this;
            FunSpec funSpec = this.primaryConstructor;
            if (funSpec != null) {
                if ((funSpec != null ? funSpec.parameter$kotlinpoet(str) : null) != null) {
                    addSuperinterface(typeName, CodeBlock.Companion.of(str, new Object[0]));
                    return this;
                }
                throw new IllegalArgumentException(("no such constructor parameter '" + str + "' to delegate to for type '" + this.name + '\'').toString());
            }
            throw new IllegalArgumentException("delegating to constructor parameter requires not-null constructor".toString());
        }

        public static /* synthetic */ Builder addEnumConstant$default(Builder builder, String str, TypeSpec typeSpec, int i, Object obj) {
            if ((i & 2) != 0) {
                typeSpec = TypeSpec.Companion.anonymousClassBuilder().build();
            }
            return builder.addEnumConstant(str, typeSpec);
        }

        public final Builder addEnumConstant(String str, TypeSpec typeSpec) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeSpec, "typeSpec");
            Builder builder = this;
            if (!Intrinsics.areEqual((Object) str, (Object) "name") && !Intrinsics.areEqual((Object) str, (Object) "ordinal")) {
                this.enumConstants.put(str, typeSpec);
                return this;
            }
            throw new IllegalArgumentException(("constant with name \"" + str + "\" conflicts with a supertype member with the same name").toString());
        }

        public final Builder addProperties(Iterable<PropertySpec> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "propertySpecs");
            Builder builder = this;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (PropertySpec addProperty : iterable) {
                arrayList.add(addProperty(addProperty));
            }
            List list = (List) arrayList;
            return this;
        }

        public final Builder addProperty(PropertySpec propertySpec) {
            Intrinsics.checkNotNullParameter(propertySpec, "propertySpec");
            Builder builder = this;
            boolean z = true;
            if (this.modifiers.contains(KModifier.EXPECT)) {
                if (propertySpec.getInitializer() == null) {
                    if (!(propertySpec.getGetter() == null && propertySpec.getSetter() == null)) {
                        throw new IllegalArgumentException("properties in expect classes can't have getters and setters".toString());
                    }
                } else {
                    throw new IllegalArgumentException("properties in expect classes can't have initializers".toString());
                }
            }
            if (isEnum$kotlinpoet()) {
                if (Intrinsics.areEqual((Object) propertySpec.getName(), (Object) "name") || Intrinsics.areEqual((Object) propertySpec.getName(), (Object) "ordinal")) {
                    z = false;
                }
                if (!z) {
                    throw new IllegalArgumentException((propertySpec.getName() + " is a final supertype member and can't be redeclared or overridden").toString());
                }
            }
            this.propertySpecs.add(propertySpec);
            return this;
        }

        public final Builder addProperty(String str, TypeName typeName, KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeName, "type");
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            return addProperty(PropertySpec.Companion.builder(str, typeName, (KModifier[]) Arrays.copyOf(kModifierArr, kModifierArr.length)).build());
        }

        public final Builder addProperty(String str, Type type, KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            return addProperty(str, TypeNames.get(type), (KModifier[]) Arrays.copyOf(kModifierArr, kModifierArr.length));
        }

        public final Builder addProperty(String str, KClass<?> kClass, KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(kClass, "type");
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            return addProperty(str, (TypeName) TypeNames.get(kClass), (KModifier[]) Arrays.copyOf(kModifierArr, kModifierArr.length));
        }

        public final Builder addProperty(String str, TypeName typeName, Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeName, "type");
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            return addProperty(PropertySpec.Companion.builder(str, typeName, iterable).build());
        }

        public final Builder addProperty(String str, Type type, Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            return addProperty(str, TypeNames.get(type), iterable);
        }

        public final Builder addProperty(String str, KClass<?> kClass, Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(kClass, "type");
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            return addProperty(str, (TypeName) TypeNames.get(kClass), iterable);
        }

        public final Builder addInitializerBlock(CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(codeBlock, "block");
            Builder builder = this;
            checkCanHaveInitializerBlocks();
            this.initializerIndex = this.propertySpecs.size();
            this.initializerBlock.add("init {\n", new Object[0]).indent().add(codeBlock).unindent().add("}\n", new Object[0]);
            return this;
        }

        public final Builder addFunctions(Iterable<FunSpec> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "funSpecs");
            Builder builder = this;
            for (FunSpec addFunction : iterable) {
                addFunction(addFunction);
            }
            return this;
        }

        public final Builder addFunction(FunSpec funSpec) {
            Intrinsics.checkNotNullParameter(funSpec, "funSpec");
            Builder builder = this;
            this.funSpecs.add(funSpec);
            return this;
        }

        public final Builder addTypes(Iterable<TypeSpec> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "typeSpecs");
            Builder builder = this;
            CollectionsKt.addAll(this.typeSpecs, iterable);
            return this;
        }

        public final Builder addType(TypeSpec typeSpec) {
            Intrinsics.checkNotNullParameter(typeSpec, "typeSpec");
            Builder builder = this;
            this.typeSpecs.add(typeSpec);
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: com.squareup.kotlinpoet.PropertySpec} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v25, resolved type: com.squareup.kotlinpoet.PropertySpec} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: com.squareup.kotlinpoet.PropertySpec} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: com.squareup.kotlinpoet.PropertySpec} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:194:0x03e1  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x008b  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0090  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.squareup.kotlinpoet.TypeSpec build() {
            /*
                r9 = this;
                java.util.Map<java.lang.String, com.squareup.kotlinpoet.TypeSpec> r0 = r9.enumConstants
                boolean r0 = r0.isEmpty()
                r1 = 1
                r0 = r0 ^ r1
                if (r0 == 0) goto L_0x0030
                boolean r0 = r9.isEnum$kotlinpoet()
                if (r0 == 0) goto L_0x0011
                goto L_0x0030
            L_0x0011:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = r9.name
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = " is not an enum and cannot have enum constants"
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                throw r1
            L_0x0030:
                java.util.List<com.squareup.kotlinpoet.CodeBlock> r0 = r9.superclassConstructorParameters
                java.util.Collection r0 = (java.util.Collection) r0
                boolean r0 = r0.isEmpty()
                r0 = r0 ^ r1
                java.lang.String r2 = "delegated constructor call in external class is not allowed"
                if (r0 == 0) goto L_0x0052
                r9.checkCanHaveSuperclass()
                boolean r0 = r9.isExternal$kotlinpoet()
                r0 = r0 ^ r1
                if (r0 == 0) goto L_0x0048
                goto L_0x0052
            L_0x0048:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = r2.toString()
                r0.<init>(r1)
                throw r0
            L_0x0052:
                boolean r0 = r9.isExternal$kotlinpoet()
                r3 = 0
                if (r0 == 0) goto L_0x008d
                java.util.List<com.squareup.kotlinpoet.FunSpec> r0 = r9.funSpecs
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r4 = r0 instanceof java.util.Collection
                if (r4 == 0) goto L_0x006c
                r4 = r0
                java.util.Collection r4 = (java.util.Collection) r4
                boolean r4 = r4.isEmpty()
                if (r4 == 0) goto L_0x006c
            L_0x006a:
                r0 = r3
                goto L_0x0088
            L_0x006c:
                java.util.Iterator r0 = r0.iterator()
            L_0x0070:
                boolean r4 = r0.hasNext()
                if (r4 == 0) goto L_0x006a
                java.lang.Object r4 = r0.next()
                com.squareup.kotlinpoet.FunSpec r4 = (com.squareup.kotlinpoet.FunSpec) r4
                java.lang.String r4 = r4.getDelegateConstructor()
                if (r4 == 0) goto L_0x0084
                r4 = r1
                goto L_0x0085
            L_0x0084:
                r4 = r3
            L_0x0085:
                if (r4 == 0) goto L_0x0070
                r0 = r1
            L_0x0088:
                if (r0 != 0) goto L_0x008b
                goto L_0x008d
            L_0x008b:
                r0 = r3
                goto L_0x008e
            L_0x008d:
                r0 = r1
            L_0x008e:
                if (r0 == 0) goto L_0x03e1
                boolean r0 = r9.isAnonymousClass$kotlinpoet()
                if (r0 == 0) goto L_0x00a4
                java.util.List<com.squareup.kotlinpoet.TypeVariableName> r0 = r9.typeVariables
                java.util.Collection r0 = (java.util.Collection) r0
                boolean r0 = r0.isEmpty()
                r0 = r0 ^ r1
                if (r0 != 0) goto L_0x00a2
                goto L_0x00a4
            L_0x00a2:
                r0 = r3
                goto L_0x00a5
            L_0x00a4:
                r0 = r1
            L_0x00a5:
                if (r0 == 0) goto L_0x03d5
                java.util.Set<com.squareup.kotlinpoet.KModifier> r0 = r9.modifiers
                com.squareup.kotlinpoet.KModifier r2 = com.squareup.kotlinpoet.KModifier.ABSTRACT
                boolean r0 = r0.contains(r2)
                if (r0 != 0) goto L_0x00ca
                java.util.Set<com.squareup.kotlinpoet.KModifier> r0 = r9.modifiers
                com.squareup.kotlinpoet.KModifier r2 = com.squareup.kotlinpoet.KModifier.SEALED
                boolean r0 = r0.contains(r2)
                if (r0 != 0) goto L_0x00ca
                com.squareup.kotlinpoet.TypeSpec$Kind r0 = r9.kind
                com.squareup.kotlinpoet.TypeSpec$Kind r2 = com.squareup.kotlinpoet.TypeSpec.Kind.CLASS
                if (r0 != r2) goto L_0x00ca
                boolean r0 = r9.isSimpleClass$kotlinpoet()
                if (r0 != 0) goto L_0x00c8
                goto L_0x00ca
            L_0x00c8:
                r0 = r3
                goto L_0x00cb
            L_0x00ca:
                r0 = r1
            L_0x00cb:
                java.util.List<com.squareup.kotlinpoet.FunSpec> r2 = r9.funSpecs
                java.util.Iterator r2 = r2.iterator()
            L_0x00d1:
                boolean r4 = r2.hasNext()
                if (r4 == 0) goto L_0x01ba
                java.lang.Object r4 = r2.next()
                com.squareup.kotlinpoet.FunSpec r4 = (com.squareup.kotlinpoet.FunSpec) r4
                if (r0 != 0) goto L_0x00ee
                java.util.Set r5 = r4.getModifiers()
                com.squareup.kotlinpoet.KModifier r6 = com.squareup.kotlinpoet.KModifier.ABSTRACT
                boolean r5 = r5.contains(r6)
                if (r5 != 0) goto L_0x00ec
                goto L_0x00ee
            L_0x00ec:
                r5 = r3
                goto L_0x00ef
            L_0x00ee:
                r5 = r1
            L_0x00ef:
                if (r5 == 0) goto L_0x0191
                com.squareup.kotlinpoet.TypeSpec$Kind r5 = r9.kind
                com.squareup.kotlinpoet.TypeSpec$Kind r6 = com.squareup.kotlinpoet.TypeSpec.Kind.INTERFACE
                if (r5 != r6) goto L_0x011b
                java.util.Set r5 = r4.getModifiers()
                r6 = 2
                com.squareup.kotlinpoet.KModifier[] r7 = new com.squareup.kotlinpoet.KModifier[r6]
                com.squareup.kotlinpoet.KModifier r8 = com.squareup.kotlinpoet.KModifier.INTERNAL
                r7[r3] = r8
                com.squareup.kotlinpoet.KModifier r8 = com.squareup.kotlinpoet.KModifier.PROTECTED
                r7[r1] = r8
                com.squareup.kotlinpoet.UtilKt.requireNoneOf(r5, r7)
                java.util.Set r4 = r4.getModifiers()
                com.squareup.kotlinpoet.KModifier[] r5 = new com.squareup.kotlinpoet.KModifier[r6]
                com.squareup.kotlinpoet.KModifier r6 = com.squareup.kotlinpoet.KModifier.ABSTRACT
                r5[r3] = r6
                com.squareup.kotlinpoet.KModifier r6 = com.squareup.kotlinpoet.KModifier.PRIVATE
                r5[r1] = r6
                com.squareup.kotlinpoet.UtilKt.requireNoneOrOneOf(r4, r5)
                goto L_0x00d1
            L_0x011b:
                boolean r5 = r9.isAnnotation$kotlinpoet()
                if (r5 == 0) goto L_0x016f
                java.util.Set r5 = r4.getModifiers()
                com.squareup.kotlinpoet.TypeSpec$Kind r6 = r9.kind
                java.util.Set<com.squareup.kotlinpoet.KModifier> r7 = r9.modifiers
                java.util.Set r6 = r6.implicitFunctionModifiers$kotlinpoet(r7)
                boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
                if (r5 == 0) goto L_0x0134
                goto L_0x00d1
            L_0x0134:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "annotation class "
                r0.<init>(r1)
                java.lang.String r1 = r9.name
                java.lang.StringBuilder r0 = r0.append(r1)
                r1 = 46
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = r4.getName()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = " requires modifiers "
                java.lang.StringBuilder r0 = r0.append(r1)
                com.squareup.kotlinpoet.TypeSpec$Kind r1 = r9.kind
                java.util.Set<com.squareup.kotlinpoet.KModifier> r2 = r9.modifiers
                java.util.Set r1 = r1.implicitFunctionModifiers$kotlinpoet(r2)
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                throw r1
            L_0x016f:
                java.util.Set<com.squareup.kotlinpoet.KModifier> r5 = r9.modifiers
                com.squareup.kotlinpoet.KModifier r6 = com.squareup.kotlinpoet.KModifier.EXPECT
                boolean r5 = r5.contains(r6)
                if (r5 == 0) goto L_0x00d1
                com.squareup.kotlinpoet.CodeBlock r4 = r4.getBody()
                boolean r4 = r4.isEmpty()
                if (r4 == 0) goto L_0x0185
                goto L_0x00d1
            L_0x0185:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r1 = "functions in expect classes can't have bodies"
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x0191:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "non-abstract type "
                r0.<init>(r1)
                java.lang.String r1 = r9.name
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = " cannot declare abstract function "
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = r4.getName()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                throw r1
            L_0x01ba:
                com.squareup.kotlinpoet.FunSpec r0 = r9.primaryConstructor
                if (r0 != 0) goto L_0x0205
                java.util.List<com.squareup.kotlinpoet.FunSpec> r0 = r9.funSpecs
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r2 = r0 instanceof java.util.Collection
                if (r2 == 0) goto L_0x01d1
                r2 = r0
                java.util.Collection r2 = (java.util.Collection) r2
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L_0x01d1
            L_0x01cf:
                r0 = r1
                goto L_0x01e8
            L_0x01d1:
                java.util.Iterator r0 = r0.iterator()
            L_0x01d5:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x01cf
                java.lang.Object r2 = r0.next()
                com.squareup.kotlinpoet.FunSpec r2 = (com.squareup.kotlinpoet.FunSpec) r2
                boolean r2 = r2.isConstructor()
                if (r2 == 0) goto L_0x01d5
                r0 = r3
            L_0x01e8:
                if (r0 != 0) goto L_0x01f5
                java.util.List<com.squareup.kotlinpoet.CodeBlock> r0 = r9.superclassConstructorParameters
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L_0x01f3
                goto L_0x01f5
            L_0x01f3:
                r0 = r3
                goto L_0x01f6
            L_0x01f5:
                r0 = r1
            L_0x01f6:
                if (r0 == 0) goto L_0x01f9
                goto L_0x0205
            L_0x01f9:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r1 = "types without a primary constructor cannot specify secondary constructors and superclass constructor parameters"
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x0205:
                boolean r0 = r9.isInlineOrValClass$kotlinpoet()
                if (r0 == 0) goto L_0x02c0
                com.squareup.kotlinpoet.FunSpec r0 = r9.primaryConstructor
                if (r0 == 0) goto L_0x022b
                java.util.List r0 = r0.getParameters()
                int r0 = r0.size()
                if (r0 != r1) goto L_0x021b
                r0 = r1
                goto L_0x021c
            L_0x021b:
                r0 = r3
            L_0x021c:
                if (r0 == 0) goto L_0x021f
                goto L_0x022b
            L_0x021f:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "value/inline classes must have 1 parameter in constructor"
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x022b:
                java.util.List<com.squareup.kotlinpoet.PropertySpec> r0 = r9.propertySpecs
                int r0 = r0.size()
                if (r0 <= 0) goto L_0x0235
                r0 = r1
                goto L_0x0236
            L_0x0235:
                r0 = r3
            L_0x0236:
                if (r0 == 0) goto L_0x02b4
                com.squareup.kotlinpoet.FunSpec r0 = r9.primaryConstructor
                r2 = 0
                if (r0 == 0) goto L_0x0250
                java.util.List r0 = r0.getParameters()
                if (r0 == 0) goto L_0x0250
                java.lang.Object r0 = kotlin.collections.CollectionsKt.firstOrNull(r0)
                com.squareup.kotlinpoet.ParameterSpec r0 = (com.squareup.kotlinpoet.ParameterSpec) r0
                if (r0 == 0) goto L_0x0250
                java.lang.String r0 = r0.getName()
                goto L_0x0251
            L_0x0250:
                r0 = r2
            L_0x0251:
                if (r0 == 0) goto L_0x0295
                java.util.List<com.squareup.kotlinpoet.PropertySpec> r4 = r9.propertySpecs
                java.lang.Iterable r4 = (java.lang.Iterable) r4
                java.util.Iterator r4 = r4.iterator()
            L_0x025b:
                boolean r5 = r4.hasNext()
                if (r5 == 0) goto L_0x0273
                java.lang.Object r5 = r4.next()
                r6 = r5
                com.squareup.kotlinpoet.PropertySpec r6 = (com.squareup.kotlinpoet.PropertySpec) r6
                java.lang.String r6 = r6.getName()
                boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r0)
                if (r6 == 0) goto L_0x025b
                r2 = r5
            L_0x0273:
                com.squareup.kotlinpoet.PropertySpec r2 = (com.squareup.kotlinpoet.PropertySpec) r2
                java.lang.String r0 = "value/inline classes must have a single read-only (val) property parameter."
                if (r2 == 0) goto L_0x028b
                boolean r2 = r2.getMutable()
                r2 = r2 ^ r1
                if (r2 == 0) goto L_0x0281
                goto L_0x0295
            L_0x0281:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                throw r1
            L_0x028b:
                java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                throw r1
            L_0x0295:
                com.squareup.kotlinpoet.TypeName r0 = r9.superclass
                java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
                kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
                com.squareup.kotlinpoet.ClassName r2 = com.squareup.kotlinpoet.TypeNames.get((kotlin.reflect.KClass<?>) r2)
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
                if (r0 == 0) goto L_0x02a8
                goto L_0x02c0
            L_0x02a8:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "value/inline classes cannot have super classes"
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x02b4:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "value/inline classes must have at least 1 property"
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x02c0:
                boolean r0 = r9.isFunInterface$kotlinpoet()
                if (r0 == 0) goto L_0x034f
                java.util.List<com.squareup.kotlinpoet.FunSpec> r0 = r9.funSpecs
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>()
                java.util.Collection r2 = (java.util.Collection) r2
                java.util.Iterator r0 = r0.iterator()
            L_0x02d5:
                boolean r4 = r0.hasNext()
                if (r4 == 0) goto L_0x02f2
                java.lang.Object r4 = r0.next()
                r5 = r4
                com.squareup.kotlinpoet.FunSpec r5 = (com.squareup.kotlinpoet.FunSpec) r5
                java.util.Set r5 = r5.getModifiers()
                com.squareup.kotlinpoet.KModifier r6 = com.squareup.kotlinpoet.KModifier.ABSTRACT
                boolean r5 = r5.contains(r6)
                if (r5 == 0) goto L_0x02d5
                r2.add(r4)
                goto L_0x02d5
            L_0x02f2:
                java.util.List r2 = (java.util.List) r2
                int r0 = r2.size()
                if (r0 != r1) goto L_0x02fc
                r0 = r1
                goto L_0x02fd
            L_0x02fc:
                r0 = r3
            L_0x02fd:
                if (r0 != 0) goto L_0x034f
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Functional interfaces must have exactly one abstract function. Contained "
                r0.<init>(r1)
                int r1 = r2.size()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = ": "
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.ArrayList r1 = new java.util.ArrayList
                r3 = 10
                int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r3)
                r1.<init>(r3)
                java.util.Collection r1 = (java.util.Collection) r1
                java.util.Iterator r2 = r2.iterator()
            L_0x0327:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x033b
                java.lang.Object r3 = r2.next()
                com.squareup.kotlinpoet.FunSpec r3 = (com.squareup.kotlinpoet.FunSpec) r3
                java.lang.String r3 = r3.getName()
                r1.add(r3)
                goto L_0x0327
            L_0x033b:
                java.util.List r1 = (java.util.List) r1
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                throw r1
            L_0x034f:
                java.util.List<com.squareup.kotlinpoet.TypeSpec> r0 = r9.typeSpecs
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                boolean r2 = r0 instanceof java.util.Collection
                if (r2 == 0) goto L_0x0362
                r2 = r0
                java.util.Collection r2 = (java.util.Collection) r2
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L_0x0362
                r2 = r3
                goto L_0x0381
            L_0x0362:
                java.util.Iterator r0 = r0.iterator()
                r2 = r3
            L_0x0367:
                boolean r4 = r0.hasNext()
                if (r4 == 0) goto L_0x0381
                java.lang.Object r4 = r0.next()
                com.squareup.kotlinpoet.TypeSpec r4 = (com.squareup.kotlinpoet.TypeSpec) r4
                boolean r4 = r4.isCompanion()
                if (r4 == 0) goto L_0x0367
                int r2 = r2 + 1
                if (r2 >= 0) goto L_0x0367
                kotlin.collections.CollectionsKt.throwCountOverflow()
                goto L_0x0367
            L_0x0381:
                if (r2 == 0) goto L_0x03c9
                if (r2 != r1) goto L_0x03c1
                boolean r0 = r9.isSimpleClass$kotlinpoet()
                if (r0 != 0) goto L_0x039f
                com.squareup.kotlinpoet.TypeSpec$Kind r0 = r9.kind
                com.squareup.kotlinpoet.TypeSpec$Kind r2 = com.squareup.kotlinpoet.TypeSpec.Kind.INTERFACE
                if (r0 == r2) goto L_0x039f
                boolean r0 = r9.isEnum$kotlinpoet()
                if (r0 != 0) goto L_0x039f
                boolean r0 = r9.isAnnotation$kotlinpoet()
                if (r0 == 0) goto L_0x039e
                goto L_0x039f
            L_0x039e:
                r1 = r3
            L_0x039f:
                if (r1 == 0) goto L_0x03a2
                goto L_0x03c9
            L_0x03a2:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                com.squareup.kotlinpoet.TypeSpec$Kind r1 = r9.kind
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = " types can't have a companion object"
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
                java.lang.String r0 = r0.toString()
                r1.<init>(r0)
                throw r1
            L_0x03c1:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r1 = "Multiple companion objects are present but only one is allowed."
                r0.<init>(r1)
                throw r0
            L_0x03c9:
                com.squareup.kotlinpoet.TypeSpec r0 = new com.squareup.kotlinpoet.TypeSpec
                r4 = 0
                r5 = 0
                r6 = 6
                r7 = 0
                r2 = r0
                r3 = r9
                r2.<init>(r3, r4, r5, r6, r7)
                return r0
            L_0x03d5:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "typevariables are forbidden on anonymous types"
                java.lang.String r1 = r1.toString()
                r0.<init>(r1)
                throw r0
            L_0x03e1:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = r2.toString()
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.TypeSpec.Builder.build():com.squareup.kotlinpoet.TypeSpec");
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0014\u0010\u000b\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\u0012"}, d2 = {"Lcom/squareup/kotlinpoet/TypeSpec$Companion;", "", "()V", "annotationBuilder", "Lcom/squareup/kotlinpoet/TypeSpec$Builder;", "className", "Lcom/squareup/kotlinpoet/ClassName;", "name", "", "anonymousClassBuilder", "classBuilder", "companionObjectBuilder", "enumBuilder", "expectClassBuilder", "funInterfaceBuilder", "interfaceBuilder", "objectBuilder", "valueClassBuilder", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: TypeSpec.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Builder companionObjectBuilder() {
            return companionObjectBuilder$default(this, (String) null, 1, (Object) null);
        }

        private Companion() {
        }

        @JvmStatic
        public final Builder classBuilder(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return new Builder(Kind.CLASS, str, new KModifier[0]);
        }

        @JvmStatic
        public final Builder classBuilder(ClassName className) {
            Intrinsics.checkNotNullParameter(className, "className");
            return classBuilder(className.getSimpleName());
        }

        @JvmStatic
        public final Builder expectClassBuilder(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return new Builder(Kind.CLASS, str, KModifier.EXPECT);
        }

        @JvmStatic
        public final Builder expectClassBuilder(ClassName className) {
            Intrinsics.checkNotNullParameter(className, "className");
            return expectClassBuilder(className.getSimpleName());
        }

        @JvmStatic
        public final Builder valueClassBuilder(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return new Builder(Kind.CLASS, str, KModifier.VALUE);
        }

        @JvmStatic
        public final Builder objectBuilder(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return new Builder(Kind.OBJECT, str, new KModifier[0]);
        }

        @JvmStatic
        public final Builder objectBuilder(ClassName className) {
            Intrinsics.checkNotNullParameter(className, "className");
            return objectBuilder(className.getSimpleName());
        }

        public static /* synthetic */ Builder companionObjectBuilder$default(Companion companion, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = null;
            }
            return companion.companionObjectBuilder(str);
        }

        @JvmStatic
        public final Builder companionObjectBuilder(String str) {
            return new Builder(Kind.OBJECT, str, KModifier.COMPANION);
        }

        @JvmStatic
        public final Builder interfaceBuilder(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return new Builder(Kind.INTERFACE, str, new KModifier[0]);
        }

        @JvmStatic
        public final Builder interfaceBuilder(ClassName className) {
            Intrinsics.checkNotNullParameter(className, "className");
            return interfaceBuilder(className.getSimpleName());
        }

        @JvmStatic
        public final Builder funInterfaceBuilder(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return new Builder(Kind.INTERFACE, str, KModifier.FUN);
        }

        @JvmStatic
        public final Builder funInterfaceBuilder(ClassName className) {
            Intrinsics.checkNotNullParameter(className, "className");
            return funInterfaceBuilder(className.getSimpleName());
        }

        @JvmStatic
        public final Builder enumBuilder(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return new Builder(Kind.CLASS, str, KModifier.ENUM);
        }

        @JvmStatic
        public final Builder enumBuilder(ClassName className) {
            Intrinsics.checkNotNullParameter(className, "className");
            return enumBuilder(className.getSimpleName());
        }

        @JvmStatic
        public final Builder anonymousClassBuilder() {
            return new Builder(Kind.CLASS, (String) null, new KModifier[0]);
        }

        @JvmStatic
        public final Builder annotationBuilder(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return new Builder(Kind.CLASS, str, KModifier.ANNOTATION);
        }

        @JvmStatic
        public final Builder annotationBuilder(ClassName className) {
            Intrinsics.checkNotNullParameter(className, "className");
            return annotationBuilder(className.getSimpleName());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003d, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0039, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003a, code lost:
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
            com.squareup.kotlinpoet.CodeWriter r2 = (com.squareup.kotlinpoet.CodeWriter) r2     // Catch:{ all -> 0x0037 }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 12
            r7 = 0
            r1 = r11
            emit$kotlinpoet$default(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0037 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0037 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r10, r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "stringBuilder.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        L_0x0037:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r10, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.TypeSpec.toString():java.lang.String");
    }
}
