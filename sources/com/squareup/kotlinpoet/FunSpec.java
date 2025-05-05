package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.AnnotationSpec;
import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.OriginatingElementsHolder;
import com.squareup.kotlinpoet.Taggable;
import java.lang.annotation.Annotation;
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
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Types;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.Strictfp;
import kotlin.jvm.Synchronized;
import kotlin.jvm.Throws;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 c2\u00020\u00012\u00020\u0002:\u0002bcB#\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002¢\u0006\u0002\u0010\bJ\u0016\u0010D\u001a\u00020\u001e2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020'0&H\u0002J\u0016\u0010F\u001a\u00020\u001e2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020'0&H\u0002J7\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\u00182\f\u0010E\u001a\b\u0012\u0004\u0012\u00020'0&2\b\b\u0002\u0010L\u001a\u00020\u001eH\u0000¢\u0006\u0002\bMJ\u001a\u0010N\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010O\u001a\u00020\u001eH\u0002J\u0013\u0010P\u001a\u00020\u001e2\b\u0010Q\u001a\u0004\u0018\u00010>H\u0002J\b\u0010R\u001a\u00020SH\u0016J\b\u0010T\u001a\u00020\u000fH\u0002J\u0017\u0010U\u001a\u0004\u0018\u0001002\u0006\u0010*\u001a\u00020\u0018H\u0000¢\u0006\u0002\bVJ\u0016\u0010W\u001a\u00020\u001e2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020'0&H\u0002J(\u0010X\u001a\u0004\u0018\u0001HY\"\b\b\u0000\u0010Y*\u00020>2\f\u0010Z\u001a\b\u0012\u0004\u0012\u0002HY0[H\u0001¢\u0006\u0002\u0010\\J(\u0010X\u001a\u0004\u0018\u0001HY\"\b\b\u0000\u0010Y*\u00020>2\f\u0010Z\u001a\b\u0012\u0004\u0012\u0002HY0=H\u0001¢\u0006\u0002\u0010]J\u0012\u0010^\u001a\u00020\u00042\b\b\u0002\u0010*\u001a\u00020\u0018H\u0007J\b\u0010_\u001a\u00020\u0018H\u0016J\u000e\u0010`\u001a\u0004\u0018\u00010\u000f*\u00020\u000fH\u0002J\f\u0010a\u001a\u00020\u000f*\u00020\u000fH\u0002R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\n8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\rR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000f0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\rR\u000e\u0010\u0007\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0011\u0010 \u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b \u0010\u001fR\u000e\u0010!\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010#\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0011R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001aR\u0018\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\nX\u0005¢\u0006\u0006\u001a\u0004\b.\u0010\rR\u0017\u0010/\u001a\b\u0012\u0004\u0012\u0002000\n¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\rR\u0011\u00102\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0011R\u0013\u00104\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0011\u00107\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0011R\u0013\u00109\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b:\u00106R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R$\u0010;\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030=\u0012\u0004\u0012\u00020>0<8VX\u0005¢\u0006\u0006\u001a\u0004\b?\u0010@R\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\n¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\r¨\u0006d"}, d2 = {"Lcom/squareup/kotlinpoet/FunSpec;", "Lcom/squareup/kotlinpoet/Taggable;", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder;", "builder", "Lcom/squareup/kotlinpoet/FunSpec$Builder;", "tagMap", "Lcom/squareup/kotlinpoet/TagMap;", "delegateOriginatingElementsHolder", "(Lcom/squareup/kotlinpoet/FunSpec$Builder;Lcom/squareup/kotlinpoet/TagMap;Lcom/squareup/kotlinpoet/OriginatingElementsHolder;)V", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotations", "()Ljava/util/List;", "body", "Lcom/squareup/kotlinpoet/CodeBlock;", "getBody", "()Lcom/squareup/kotlinpoet/CodeBlock;", "contextReceiverTypes", "Lcom/squareup/kotlinpoet/TypeName;", "getContextReceiverTypes$annotations", "()V", "getContextReceiverTypes", "delegateConstructor", "", "getDelegateConstructor", "()Ljava/lang/String;", "delegateConstructorArguments", "getDelegateConstructorArguments", "isAccessor", "", "()Z", "isConstructor", "isEmptySetter", "isExternalGetter", "kdoc", "getKdoc", "modifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "getModifiers", "()Ljava/util/Set;", "name", "getName", "originatingElements", "Ljavax/lang/model/element/Element;", "getOriginatingElements", "parameters", "Lcom/squareup/kotlinpoet/ParameterSpec;", "getParameters", "receiverKdoc", "getReceiverKdoc", "receiverType", "getReceiverType", "()Lcom/squareup/kotlinpoet/TypeName;", "returnKdoc", "getReturnKdoc", "returnType", "getReturnType", "tags", "", "Lkotlin/reflect/KClass;", "", "getTags", "()Ljava/util/Map;", "typeVariables", "Lcom/squareup/kotlinpoet/TypeVariableName;", "getTypeVariables", "canBodyBeOmitted", "implicitModifiers", "canNotHaveBody", "emit", "", "codeWriter", "Lcom/squareup/kotlinpoet/CodeWriter;", "enclosingName", "includeKdocTags", "emit$kotlinpoet", "emitSignature", "emitUnitReturnType", "equals", "other", "hashCode", "", "kdocWithTags", "parameter", "parameter$kotlinpoet", "shouldOmitBody", "tag", "T", "type", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toBuilder", "toString", "asExpressionBody", "returnsWithoutLinebreak", "Builder", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: FunSpec.kt */
public final class FunSpec implements Taggable, OriginatingElementsHolder {
    private static final String CONSTRUCTOR = "constructor()";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String GETTER = "get()";
    private static final CodeBlock RETURN_EXPRESSION_BODY_PREFIX_NBSP = CodeBlock.Companion.of("return·", new Object[0]);
    private static final CodeBlock RETURN_EXPRESSION_BODY_PREFIX_SPACE = CodeBlock.Companion.of("return ", new Object[0]);
    public static final String SETTER = "set()";
    private static final CodeBlock THROW_EXPRESSION_BODY_PREFIX_NBSP = CodeBlock.Companion.of("throw·", new Object[0]);
    private static final CodeBlock THROW_EXPRESSION_BODY_PREFIX_SPACE = CodeBlock.Companion.of("throw ", new Object[0]);
    private final List<AnnotationSpec> annotations;
    private final CodeBlock body;
    private final List<TypeName> contextReceiverTypes;
    private final String delegateConstructor;
    private final List<CodeBlock> delegateConstructorArguments;
    private final OriginatingElementsHolder delegateOriginatingElementsHolder;
    private final boolean isEmptySetter;
    private final boolean isExternalGetter;
    private final CodeBlock kdoc;
    private final Set<KModifier> modifiers;
    private final String name;
    private final List<ParameterSpec> parameters;
    private final CodeBlock receiverKdoc;
    private final TypeName receiverType;
    private final CodeBlock returnKdoc;
    private final TypeName returnType;
    private final TagMap tagMap;
    private final List<TypeVariableName> typeVariables;

    @JvmStatic
    public static final Builder builder(String str) {
        return Companion.builder(str);
    }

    @JvmStatic
    public static final Builder constructorBuilder() {
        return Companion.constructorBuilder();
    }

    public static /* synthetic */ void getContextReceiverTypes$annotations() {
    }

    @JvmStatic
    public static final Builder getterBuilder() {
        return Companion.getterBuilder();
    }

    @JvmStatic
    public static final Builder overriding(ExecutableElement executableElement) {
        return Companion.overriding(executableElement);
    }

    @JvmStatic
    @Deprecated(level = DeprecationLevel.WARNING, message = "Element APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
    public static final Builder overriding(ExecutableElement executableElement, DeclaredType declaredType, Types types) {
        return Companion.overriding(executableElement, declaredType, types);
    }

    @JvmStatic
    public static final Builder setterBuilder() {
        return Companion.setterBuilder();
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
        return toBuilder$default(this, (String) null, 1, (Object) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0179, code lost:
        if (r12 != false) goto L_0x017b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x018b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private FunSpec(com.squareup.kotlinpoet.FunSpec.Builder r12, com.squareup.kotlinpoet.TagMap r13, com.squareup.kotlinpoet.OriginatingElementsHolder r14) {
        /*
            r11 = this;
            r11.<init>()
            r11.tagMap = r13
            r11.delegateOriginatingElementsHolder = r14
            java.lang.String r13 = r12.getName$kotlinpoet()
            r11.name = r13
            com.squareup.kotlinpoet.CodeBlock$Builder r14 = r12.getKdoc$kotlinpoet()
            com.squareup.kotlinpoet.CodeBlock r14 = r14.build()
            r11.kdoc = r14
            com.squareup.kotlinpoet.CodeBlock r14 = r12.getReturnKdoc$kotlinpoet()
            r11.returnKdoc = r14
            com.squareup.kotlinpoet.CodeBlock r14 = r12.getReceiverKdoc$kotlinpoet()
            r11.receiverKdoc = r14
            java.util.List r14 = r12.getAnnotations()
            java.util.Collection r14 = (java.util.Collection) r14
            java.util.List r14 = com.squareup.kotlinpoet.UtilKt.toImmutableList(r14)
            r11.annotations = r14
            java.util.List r14 = r12.getModifiers()
            java.util.Collection r14 = (java.util.Collection) r14
            java.util.Set r14 = com.squareup.kotlinpoet.UtilKt.toImmutableSet(r14)
            r11.modifiers = r14
            java.util.List r0 = r12.getTypeVariables()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.List r0 = com.squareup.kotlinpoet.UtilKt.toImmutableList(r0)
            r11.typeVariables = r0
            com.squareup.kotlinpoet.TypeName r1 = r12.getReceiverType$kotlinpoet()
            r11.receiverType = r1
            java.util.List r1 = r12.getContextReceiverTypes$kotlinpoet()
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.List r1 = com.squareup.kotlinpoet.UtilKt.toImmutableList(r1)
            r11.contextReceiverTypes = r1
            com.squareup.kotlinpoet.TypeName r1 = r12.getReturnType$kotlinpoet()
            r11.returnType = r1
            java.util.List r1 = r12.getParameters()
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.List r1 = com.squareup.kotlinpoet.UtilKt.toImmutableList(r1)
            r11.parameters = r1
            java.lang.String r2 = r12.getDelegateConstructor$kotlinpoet()
            r11.delegateConstructor = r2
            java.util.List r2 = r12.getDelegateConstructorArguments$kotlinpoet()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.List r2 = com.squareup.kotlinpoet.UtilKt.toImmutableList(r2)
            r11.delegateConstructorArguments = r2
            com.squareup.kotlinpoet.CodeBlock$Builder r2 = r12.getBody$kotlinpoet()
            com.squareup.kotlinpoet.CodeBlock r2 = r2.build()
            r11.body = r2
            java.lang.String r3 = "get()"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r3)
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x009f
            java.util.List r4 = r12.getModifiers()
            com.squareup.kotlinpoet.KModifier r7 = com.squareup.kotlinpoet.KModifier.EXTERNAL
            boolean r4 = r4.contains(r7)
            if (r4 == 0) goto L_0x009f
            r4 = r6
            goto L_0x00a0
        L_0x009f:
            r4 = r5
        L_0x00a0:
            r11.isExternalGetter = r4
            java.lang.String r7 = "set()"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r7)
            if (r8 == 0) goto L_0x00b2
            boolean r8 = r1.isEmpty()
            if (r8 == 0) goto L_0x00b2
            r8 = r6
            goto L_0x00b3
        L_0x00b2:
            r8 = r5
        L_0x00b3:
            r11.isEmptySetter = r8
            boolean r8 = r2.isEmpty()
            if (r8 != 0) goto L_0x00d5
            java.util.List r8 = r12.getModifiers()
            java.util.Collection r8 = (java.util.Collection) r8
            r9 = 2
            com.squareup.kotlinpoet.KModifier[] r9 = new com.squareup.kotlinpoet.KModifier[r9]
            com.squareup.kotlinpoet.KModifier r10 = com.squareup.kotlinpoet.KModifier.ABSTRACT
            r9[r5] = r10
            com.squareup.kotlinpoet.KModifier r10 = com.squareup.kotlinpoet.KModifier.EXPECT
            r9[r6] = r10
            boolean r8 = com.squareup.kotlinpoet.UtilKt.containsAnyOf(r8, r9)
            if (r8 != 0) goto L_0x00d3
            goto L_0x00d5
        L_0x00d3:
            r8 = r5
            goto L_0x00d6
        L_0x00d5:
            r8 = r6
        L_0x00d6:
            if (r8 == 0) goto L_0x018b
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r3)
            if (r12 == 0) goto L_0x00f9
            if (r4 == 0) goto L_0x00e9
            boolean r12 = r2.isEmpty()
            if (r12 == 0) goto L_0x00e7
            goto L_0x00e9
        L_0x00e7:
            r12 = r5
            goto L_0x00ea
        L_0x00e9:
            r12 = r6
        L_0x00ea:
            if (r12 == 0) goto L_0x00ed
            goto L_0x0149
        L_0x00ed:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "external getter cannot have code"
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L_0x00f9:
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r7)
            if (r12 == 0) goto L_0x0149
            int r12 = r1.size()
            if (r12 > r6) goto L_0x0107
            r12 = r6
            goto L_0x0108
        L_0x0107:
            r12 = r5
        L_0x0108:
            if (r12 == 0) goto L_0x012c
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r12 = r1.isEmpty()
            r12 = r12 ^ r6
            if (r12 != 0) goto L_0x011c
            boolean r12 = r2.isEmpty()
            if (r12 == 0) goto L_0x011a
            goto L_0x011c
        L_0x011a:
            r12 = r5
            goto L_0x011d
        L_0x011c:
            r12 = r6
        L_0x011d:
            if (r12 == 0) goto L_0x0120
            goto L_0x0149
        L_0x0120:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "parameterless setter cannot have code"
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L_0x012c:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r13 = " can have at most one parameter"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r12 = r12.toString()
            r13.<init>(r12)
            throw r13
        L_0x0149:
            com.squareup.kotlinpoet.KModifier r12 = com.squareup.kotlinpoet.KModifier.INLINE
            boolean r12 = r14.contains(r12)
            if (r12 != 0) goto L_0x017b
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r12 = r0 instanceof java.util.Collection
            if (r12 == 0) goto L_0x0162
            r12 = r0
            java.util.Collection r12 = (java.util.Collection) r12
            boolean r12 = r12.isEmpty()
            if (r12 == 0) goto L_0x0162
        L_0x0160:
            r12 = r6
            goto L_0x0179
        L_0x0162:
            java.util.Iterator r12 = r0.iterator()
        L_0x0166:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x0160
            java.lang.Object r13 = r12.next()
            com.squareup.kotlinpoet.TypeVariableName r13 = (com.squareup.kotlinpoet.TypeVariableName) r13
            boolean r13 = r13.isReified()
            if (r13 == 0) goto L_0x0166
            r12 = r5
        L_0x0179:
            if (r12 == 0) goto L_0x017c
        L_0x017b:
            r5 = r6
        L_0x017c:
            if (r5 == 0) goto L_0x017f
            return
        L_0x017f:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "only type parameters of inline functions can be reified!"
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L_0x018b:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r14 = "abstract or expect function "
            r13.<init>(r14)
            java.lang.String r12 = r12.getName$kotlinpoet()
            java.lang.StringBuilder r12 = r13.append(r12)
            java.lang.String r13 = " cannot have code"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r12 = r12.toString()
            r13.<init>(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.FunSpec.<init>(com.squareup.kotlinpoet.FunSpec$Builder, com.squareup.kotlinpoet.TagMap, com.squareup.kotlinpoet.OriginatingElementsHolder):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ FunSpec(Builder builder, TagMap tagMap2, OriginatingElementsHolder originatingElementsHolder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder, (i & 2) != 0 ? TaggableKt.buildTagMap(builder) : tagMap2, (i & 4) != 0 ? OriginatingElementsHolderKt.buildOriginatingElements((OriginatingElementsHolder.Builder<?>) builder) : originatingElementsHolder);
    }

    public final String getName() {
        return this.name;
    }

    public final CodeBlock getKdoc() {
        return this.kdoc;
    }

    public final CodeBlock getReturnKdoc() {
        return this.returnKdoc;
    }

    public final CodeBlock getReceiverKdoc() {
        return this.receiverKdoc;
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

    public final TypeName getReceiverType() {
        return this.receiverType;
    }

    public final List<TypeName> getContextReceiverTypes() {
        return this.contextReceiverTypes;
    }

    public final TypeName getReturnType() {
        return this.returnType;
    }

    public final List<ParameterSpec> getParameters() {
        return this.parameters;
    }

    public final String getDelegateConstructor() {
        return this.delegateConstructor;
    }

    public final List<CodeBlock> getDelegateConstructorArguments() {
        return this.delegateConstructorArguments;
    }

    public final CodeBlock getBody() {
        return this.body;
    }

    public final ParameterSpec parameter$kotlinpoet(String str) {
        Object obj;
        Intrinsics.checkNotNullParameter(str, "name");
        Iterator it = this.parameters.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) ((ParameterSpec) obj).getName(), (Object) str)) {
                break;
            }
        }
        return (ParameterSpec) obj;
    }

    public static /* synthetic */ void emit$kotlinpoet$default(FunSpec funSpec, CodeWriter codeWriter, String str, Set set, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        funSpec.emit$kotlinpoet(codeWriter, str, set, z);
    }

    public final void emit$kotlinpoet(CodeWriter codeWriter, String str, Set<? extends KModifier> set, boolean z) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        Intrinsics.checkNotNullParameter(set, "implicitModifiers");
        if (z) {
            codeWriter.emitKdoc(kdocWithTags());
        } else {
            codeWriter.emitKdoc(UtilKt.ensureEndsWithNewLine(this.kdoc));
        }
        codeWriter.emitContextReceivers(this.contextReceiverTypes, "\n");
        codeWriter.emitAnnotations(this.annotations, false);
        codeWriter.emitModifiers(this.modifiers, set);
        if (!isConstructor() && !Companion.isAccessor$kotlinpoet(this.name)) {
            codeWriter.emitCode("fun·");
        }
        if (!this.typeVariables.isEmpty()) {
            codeWriter.emitTypeVariables(this.typeVariables);
            CodeWriter.emit$default(codeWriter, " ", false, 2, (Object) null);
        }
        emitSignature(codeWriter, str);
        codeWriter.emitWhereBlock(this.typeVariables);
        if (shouldOmitBody(set)) {
            CodeWriter.emit$default(codeWriter, "\n", false, 2, (Object) null);
            return;
        }
        CodeBlock asExpressionBody = asExpressionBody(this.body);
        if (asExpressionBody != null) {
            CodeWriter.emitCode$default(codeWriter, CodeBlock.Companion.of(" = %L", asExpressionBody), false, true, 2, (Object) null);
        } else if (!this.isEmptySetter) {
            codeWriter.emitCode("·{\n");
            CodeWriter.indent$default(codeWriter, 0, 1, (Object) null);
            CodeWriter.emitCode$default(codeWriter, returnsWithoutLinebreak(this.body), false, true, 2, (Object) null);
            CodeWriter.unindent$default(codeWriter, 0, 1, (Object) null);
            CodeWriter.emit$default(codeWriter, "}\n", false, 2, (Object) null);
        } else {
            CodeWriter.emit$default(codeWriter, "\n", false, 2, (Object) null);
        }
    }

    private final boolean shouldOmitBody(Set<? extends KModifier> set) {
        if (canNotHaveBody(set)) {
            if (this.body.isEmpty()) {
                return true;
            }
            throw new IllegalStateException(("function " + this.name + " cannot have code").toString());
        } else if (!canBodyBeOmitted(set) || !this.body.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private final boolean canNotHaveBody(Set<? extends KModifier> set) {
        return this.modifiers.contains(KModifier.ABSTRACT) || SetsKt.plus(this.modifiers, set).contains(KModifier.EXPECT);
    }

    private final boolean canBodyBeOmitted(Set<? extends KModifier> set) {
        return isConstructor() || SetsKt.plus(this.modifiers, set).contains(KModifier.EXTERNAL) || this.modifiers.contains(KModifier.ABSTRACT);
    }

    private final void emitSignature(CodeWriter codeWriter, String str) {
        if (isConstructor()) {
            codeWriter.emitCode("constructor", str);
        } else if (Intrinsics.areEqual((Object) this.name, (Object) GETTER)) {
            codeWriter.emitCode("get");
        } else if (Intrinsics.areEqual((Object) this.name, (Object) SETTER)) {
            codeWriter.emitCode("set");
        } else {
            TypeName typeName = this.receiverType;
            if (typeName != null) {
                if (typeName instanceof LambdaTypeName) {
                    codeWriter.emitCode("(%T).", typeName);
                } else {
                    codeWriter.emitCode("%T.", typeName);
                }
            }
            codeWriter.emitCode("%N", this);
        }
        if (!this.isEmptySetter && !this.isExternalGetter) {
            ParameterSpecKt.emit$default(this.parameters, codeWriter, false, new FunSpec$emitSignature$1(codeWriter, this), 2, (Object) null);
        }
        TypeName typeName2 = this.returnType;
        if (typeName2 != null) {
            codeWriter.emitCode(": %T", typeName2);
        } else if (emitUnitReturnType()) {
            codeWriter.emitCode(": %T", TypeNames.UNIT);
        }
        if (this.delegateConstructor != null) {
            CodeWriter.emitCode$default(codeWriter, CodeBlocks.joinToCode$default(this.delegateConstructorArguments, (CharSequence) null, " : " + this.delegateConstructor + '(', ")", 1, (Object) null), false, false, 6, (Object) null);
        }
    }

    public final boolean isConstructor() {
        return Companion.isConstructor$kotlinpoet(this.name);
    }

    public final boolean isAccessor() {
        return Companion.isAccessor$kotlinpoet(this.name);
    }

    private final CodeBlock kdocWithTags() {
        boolean z;
        CodeBlock.Builder builder = UtilKt.ensureEndsWithNewLine(this.kdoc).toBuilder();
        boolean isNotEmpty = builder.isNotEmpty();
        if (this.receiverKdoc.isNotEmpty()) {
            if (isNotEmpty) {
                builder.add("\n", new Object[0]);
                z = true;
            } else {
                z = false;
            }
            builder.add("@receiver %L", UtilKt.ensureEndsWithNewLine(this.receiverKdoc));
        } else {
            z = false;
        }
        int i = 0;
        for (Object next : this.parameters) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ParameterSpec parameterSpec = (ParameterSpec) next;
            if (parameterSpec.getKdoc().isNotEmpty()) {
                if (!z && i == 0 && isNotEmpty) {
                    builder.add("\n", new Object[0]);
                    z = true;
                }
                builder.add("@param %L %L", parameterSpec.getName(), UtilKt.ensureEndsWithNewLine(parameterSpec.getKdoc()));
            }
            i = i2;
        }
        if (this.returnKdoc.isNotEmpty()) {
            if (!z && isNotEmpty) {
                builder.add("\n", new Object[0]);
            }
            builder.add("@return %L", UtilKt.ensureEndsWithNewLine(this.returnKdoc));
        }
        return builder.build();
    }

    private final boolean emitUnitReturnType() {
        if (!isConstructor() && !Intrinsics.areEqual((Object) this.name, (Object) GETTER) && !Intrinsics.areEqual((Object) this.name, (Object) SETTER) && asExpressionBody(this.body) == null) {
            return true;
        }
        return false;
    }

    private final CodeBlock asExpressionBody(CodeBlock codeBlock) {
        CodeBlock trim$kotlinpoet = codeBlock.trim$kotlinpoet();
        CodeBlock withoutPrefix$kotlinpoet = trim$kotlinpoet.withoutPrefix$kotlinpoet(RETURN_EXPRESSION_BODY_PREFIX_SPACE);
        if (withoutPrefix$kotlinpoet == null) {
            withoutPrefix$kotlinpoet = trim$kotlinpoet.withoutPrefix$kotlinpoet(RETURN_EXPRESSION_BODY_PREFIX_NBSP);
        }
        if (withoutPrefix$kotlinpoet != null) {
            return withoutPrefix$kotlinpoet;
        }
        if (trim$kotlinpoet.withoutPrefix$kotlinpoet(THROW_EXPRESSION_BODY_PREFIX_SPACE) == null && trim$kotlinpoet.withoutPrefix$kotlinpoet(THROW_EXPRESSION_BODY_PREFIX_NBSP) == null) {
            return null;
        }
        return trim$kotlinpoet;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0059, code lost:
        r0 = r2.build();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.squareup.kotlinpoet.CodeBlock returnsWithoutLinebreak(com.squareup.kotlinpoet.CodeBlock r16) {
        /*
            r15 = this;
            com.squareup.kotlinpoet.CodeBlock r0 = RETURN_EXPRESSION_BODY_PREFIX_SPACE
            java.util.List r0 = r0.getFormatParts$kotlinpoet()
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            com.squareup.kotlinpoet.CodeBlock r2 = RETURN_EXPRESSION_BODY_PREFIX_NBSP
            java.util.List r2 = r2.getFormatParts$kotlinpoet()
            java.lang.Object r2 = r2.get(r1)
            r8 = r2
            java.lang.String r8 = (java.lang.String) r8
            java.util.List r2 = r16.getFormatParts$kotlinpoet()
            java.util.Iterator r9 = r2.iterator()
            r10 = 0
            r11 = r1
            r2 = r10
        L_0x0025:
            boolean r3 = r9.hasNext()
            if (r3 == 0) goto L_0x0057
            int r12 = r11 + 1
            java.lang.Object r3 = r9.next()
            java.lang.String r3 = (java.lang.String) r3
            r4 = 2
            boolean r4 = kotlin.text.StringsKt.startsWith$default(r3, r0, r1, r4, r10)
            if (r4 == 0) goto L_0x0055
            if (r2 != 0) goto L_0x0040
            com.squareup.kotlinpoet.CodeBlock$Builder r2 = r16.toBuilder()
        L_0x0040:
            r13 = r2
            java.util.List r14 = r13.getFormatParts$kotlinpoet()
            r5 = 0
            r6 = 4
            r7 = 0
            r2 = r3
            r3 = r0
            r4 = r8
            java.lang.String r2 = kotlin.text.StringsKt.replaceFirst$default((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (boolean) r5, (int) r6, (java.lang.Object) r7)
            r14.set(r11, r2)
            r11 = r12
            r2 = r13
            goto L_0x0025
        L_0x0055:
            r11 = r12
            goto L_0x0025
        L_0x0057:
            if (r2 == 0) goto L_0x005f
            com.squareup.kotlinpoet.CodeBlock r0 = r2.build()
            if (r0 != 0) goto L_0x0061
        L_0x005f:
            r0 = r16
        L_0x0061:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.FunSpec.returnsWithoutLinebreak(com.squareup.kotlinpoet.CodeBlock):com.squareup.kotlinpoet.CodeBlock");
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

    public static /* synthetic */ Builder toBuilder$default(FunSpec funSpec, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = funSpec.name;
        }
        return funSpec.toBuilder(str);
    }

    public final Builder toBuilder(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        Builder builder = new Builder(str);
        builder.getKdoc$kotlinpoet().add(this.kdoc);
        builder.setReturnKdoc$kotlinpoet(this.returnKdoc);
        builder.setReceiverKdoc$kotlinpoet(this.receiverKdoc);
        CollectionsKt.addAll(builder.getAnnotations(), this.annotations);
        CollectionsKt.addAll(builder.getModifiers(), this.modifiers);
        CollectionsKt.addAll(builder.getTypeVariables(), this.typeVariables);
        builder.setReturnType$kotlinpoet(this.returnType);
        CollectionsKt.addAll(builder.getParameters(), this.parameters);
        builder.setDelegateConstructor$kotlinpoet(this.delegateConstructor);
        builder.setDelegateConstructorArguments$kotlinpoet(CollectionsKt.plus(builder.getDelegateConstructorArguments$kotlinpoet(), this.delegateConstructorArguments));
        builder.getBody$kotlinpoet().add(this.body);
        builder.setReceiverType$kotlinpoet(this.receiverType);
        builder.getTags().putAll(this.tagMap.getTags());
        CollectionsKt.addAll(builder.getOriginatingElements(), getOriginatingElements());
        return builder;
    }

    @Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010A\u001a\u00020\u00002\u0006\u0010B\u001a\u00020\bJ\u000e\u0010A\u001a\u00020\u00002\u0006\u0010C\u001a\u00020DJ\u0012\u0010A\u001a\u00020\u00002\n\u0010C\u001a\u0006\u0012\u0002\b\u00030EJ\u0012\u0010A\u001a\u00020\u00002\n\u0010C\u001a\u0006\u0012\u0002\b\u00030:J\u0014\u0010F\u001a\u00020\u00002\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\b0HJ\u000e\u0010I\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\u0018J+\u0010I\u001a\u00020\u00002\u0006\u0010K\u001a\u00020\u00042\u0016\u0010L\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010;0M\"\u0004\u0018\u00010;¢\u0006\u0002\u0010NJ'\u0010O\u001a\u00020\u00002\u0006\u0010K\u001a\u00020\u00042\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020;0M\"\u00020;¢\u0006\u0002\u0010NJ\u000e\u0010P\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\u0018J'\u0010P\u001a\u00020\u00002\u0006\u0010K\u001a\u00020\u00042\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020;0M\"\u00020;¢\u0006\u0002\u0010NJ\u001f\u0010R\u001a\u00020\u00002\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0M\"\u00020\u001f¢\u0006\u0002\u0010SJ\u0014\u0010R\u001a\u00020\u00002\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0HJ \u0010T\u001a\u00020\u00002\u0006\u0010K\u001a\u00020\u00042\u0010\u0010L\u001a\f\u0012\u0004\u0012\u00020\u0004\u0012\u0002\b\u00030UJ\u000e\u0010V\u001a\u00020\u00002\u0006\u0010W\u001a\u00020&J/\u0010V\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00102\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0M\"\u00020\u001f¢\u0006\u0002\u0010YJ$\u0010V\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00102\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0HJ/\u0010V\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010X\u001a\u00020Z2\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0M\"\u00020\u001f¢\u0006\u0002\u0010[J$\u0010V\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010X\u001a\u00020Z2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0HJ3\u0010V\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\n\u0010X\u001a\u0006\u0012\u0002\b\u00030:2\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0M\"\u00020\u001f¢\u0006\u0002\u0010\\J(\u0010V\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\n\u0010X\u001a\u0006\u0012\u0002\b\u00030:2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0HJ\u0014\u0010]\u001a\u00020\u00002\f\u0010^\u001a\b\u0012\u0004\u0012\u00020&0HJ'\u0010_\u001a\u00020\u00002\u0006\u0010K\u001a\u00020\u00042\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020;0M\"\u00020;¢\u0006\u0002\u0010NJ\u000e\u0010`\u001a\u00020\u00002\u0006\u0010a\u001a\u00020?J\u0014\u0010b\u001a\u00020\u00002\f\u0010>\u001a\b\u0012\u0004\u0012\u00020?0HJ'\u0010c\u001a\u00020\u00002\u0006\u0010d\u001a\u00020\u00042\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020;0M\"\u00020;¢\u0006\u0002\u0010NJ\u0006\u0010e\u001a\u00020fJ\u001e\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020\u00042\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J!\u0010j\u001a\u00020\u00002\u0014\b\u0002\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180M\"\u00020\u0018¢\u0006\u0002\u0010kJ\u001f\u0010j\u001a\u00020\u00002\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040M\"\u00020\u0004¢\u0006\u0002\u0010lJ\u0014\u0010j\u001a\u00020\u00002\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00180HJ\u0014\u0010j\u001a\u00020\u00002\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017J!\u0010m\u001a\u00020\u00002\u0014\b\u0002\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180M\"\u00020\u0018¢\u0006\u0002\u0010kJ\u001f\u0010m\u001a\u00020\u00002\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040M\"\u00020\u0004¢\u0006\u0002\u0010lJ\u0014\u0010m\u001a\u00020\u00002\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00180HJ\u0014\u0010m\u001a\u00020\u00002\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017J\u0006\u0010n\u001a\u00020\u0000J!\u0010o\u001a\u00020\u00002\u0012\u0010-\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100M\"\u00020\u0010H\u0007¢\u0006\u0002\u0010pJ\u0016\u0010o\u001a\u00020\u00002\f\u0010q\u001a\b\u0012\u0004\u0012\u00020\u00100HH\u0007J\u0006\u0010r\u001a\u00020\u0000J\u0014\u0010s\u001a\u00020h2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020t0HJ'\u0010u\u001a\u00020\u00002\u0006\u0010d\u001a\u00020\u00042\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020;0M\"\u00020;¢\u0006\u0002\u0010NJ\u001a\u0010v\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u0018H\u0007J\u001a\u0010v\u001a\u00020\u00002\u0006\u0010-\u001a\u00020Z2\b\b\u0002\u0010\u001c\u001a\u00020\u0018H\u0007J/\u0010v\u001a\u00020\u00002\u0006\u0010-\u001a\u00020Z2\u0006\u0010\u001c\u001a\u00020\u00042\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020;0M\"\u00020;¢\u0006\u0002\u0010wJ\u001e\u0010v\u001a\u00020\u00002\n\u0010-\u001a\u0006\u0012\u0002\b\u00030:2\b\b\u0002\u0010\u001c\u001a\u00020\u0018H\u0007J3\u0010v\u001a\u00020\u00002\n\u0010-\u001a\u0006\u0012\u0002\b\u00030:2\u0006\u0010\u001c\u001a\u00020\u00042\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020;0M\"\u00020;¢\u0006\u0002\u0010xJ\u001a\u0010y\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u0018H\u0007J\u001a\u0010y\u001a\u00020\u00002\u0006\u00105\u001a\u00020Z2\b\b\u0002\u0010\u001c\u001a\u00020\u0018H\u0007J/\u0010y\u001a\u00020\u00002\u0006\u00105\u001a\u00020Z2\u0006\u0010\u001c\u001a\u00020\u00042\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020;0M\"\u00020;¢\u0006\u0002\u0010wJ\u001e\u0010y\u001a\u00020\u00002\n\u00105\u001a\u0006\u0012\u0002\b\u00030:2\b\b\u0002\u0010\u001c\u001a\u00020\u0018H\u0007J3\u0010y\u001a\u00020\u00002\n\u00105\u001a\u0006\u0012\u0002\b\u00030:2\u0006\u0010\u001c\u001a\u00020\u00042\u0012\u0010L\u001a\n\u0012\u0006\b\u0001\u0012\u00020;0M\"\u00020;¢\u0006\u0002\u0010xR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0005R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\n\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000eR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010\nR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\nR\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u0007¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\nR\u001a\u0010(\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010-\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u00102\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010*\"\u0004\b4\u0010,R\u001c\u00105\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010/\"\u0004\b7\u00101R$\u00108\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030:\u0012\u0004\u0012\u00020;09X\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0017\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u0007¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\n¨\u0006z"}, d2 = {"Lcom/squareup/kotlinpoet/FunSpec$Builder;", "Lcom/squareup/kotlinpoet/Taggable$Builder;", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder$Builder;", "name", "", "(Ljava/lang/String;)V", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotations", "()Ljava/util/List;", "body", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "getBody$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "contextReceiverTypes", "Lcom/squareup/kotlinpoet/TypeName;", "getContextReceiverTypes$kotlinpoet", "delegateConstructor", "getDelegateConstructor$kotlinpoet", "()Ljava/lang/String;", "setDelegateConstructor$kotlinpoet", "delegateConstructorArguments", "", "Lcom/squareup/kotlinpoet/CodeBlock;", "getDelegateConstructorArguments$kotlinpoet", "setDelegateConstructorArguments$kotlinpoet", "(Ljava/util/List;)V", "kdoc", "getKdoc$kotlinpoet", "modifiers", "Lcom/squareup/kotlinpoet/KModifier;", "getModifiers", "getName$kotlinpoet", "originatingElements", "Ljavax/lang/model/element/Element;", "getOriginatingElements", "parameters", "Lcom/squareup/kotlinpoet/ParameterSpec;", "getParameters", "receiverKdoc", "getReceiverKdoc$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock;", "setReceiverKdoc$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeBlock;)V", "receiverType", "getReceiverType$kotlinpoet", "()Lcom/squareup/kotlinpoet/TypeName;", "setReceiverType$kotlinpoet", "(Lcom/squareup/kotlinpoet/TypeName;)V", "returnKdoc", "getReturnKdoc$kotlinpoet", "setReturnKdoc$kotlinpoet", "returnType", "getReturnType$kotlinpoet", "setReturnType$kotlinpoet", "tags", "", "Lkotlin/reflect/KClass;", "", "getTags", "()Ljava/util/Map;", "typeVariables", "Lcom/squareup/kotlinpoet/TypeVariableName;", "getTypeVariables", "addAnnotation", "annotationSpec", "annotation", "Lcom/squareup/kotlinpoet/ClassName;", "Ljava/lang/Class;", "addAnnotations", "annotationSpecs", "", "addCode", "codeBlock", "format", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/FunSpec$Builder;", "addComment", "addKdoc", "block", "addModifiers", "([Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/FunSpec$Builder;", "addNamedCode", "", "addParameter", "parameterSpec", "type", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/TypeName;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/FunSpec$Builder;", "Ljava/lang/reflect/Type;", "(Ljava/lang/String;Ljava/lang/reflect/Type;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/FunSpec$Builder;", "(Ljava/lang/String;Lkotlin/reflect/KClass;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/FunSpec$Builder;", "addParameters", "parameterSpecs", "addStatement", "addTypeVariable", "typeVariable", "addTypeVariables", "beginControlFlow", "controlFlow", "build", "Lcom/squareup/kotlinpoet/FunSpec;", "callConstructor", "", "constructor", "callSuperConstructor", "([Lcom/squareup/kotlinpoet/CodeBlock;)Lcom/squareup/kotlinpoet/FunSpec$Builder;", "([Ljava/lang/String;)Lcom/squareup/kotlinpoet/FunSpec$Builder;", "callThisConstructor", "clearBody", "contextReceivers", "([Lcom/squareup/kotlinpoet/TypeName;)Lcom/squareup/kotlinpoet/FunSpec$Builder;", "receiverTypes", "endControlFlow", "jvmModifiers", "Ljavax/lang/model/element/Modifier;", "nextControlFlow", "receiver", "(Ljava/lang/reflect/Type;Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/FunSpec$Builder;", "(Lkotlin/reflect/KClass;Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/FunSpec$Builder;", "returns", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: FunSpec.kt */
    public static final class Builder implements Taggable.Builder<Builder>, OriginatingElementsHolder.Builder<Builder> {
        private final List<AnnotationSpec> annotations = new ArrayList();
        private final CodeBlock.Builder body = CodeBlock.Companion.builder();
        private final List<TypeName> contextReceiverTypes = new ArrayList();
        private String delegateConstructor;
        private List<CodeBlock> delegateConstructorArguments = CollectionsKt.emptyList();
        private final CodeBlock.Builder kdoc = CodeBlock.Companion.builder();
        private final List<KModifier> modifiers = new ArrayList();
        private final String name;
        private final List<Element> originatingElements = new ArrayList();
        private final List<ParameterSpec> parameters = new ArrayList();
        private CodeBlock receiverKdoc = CodeBlock.Companion.getEMPTY$kotlinpoet();
        private TypeName receiverType;
        private CodeBlock returnKdoc = CodeBlock.Companion.getEMPTY$kotlinpoet();
        private TypeName returnType;
        private final Map<KClass<?>, Object> tags = new LinkedHashMap();
        private final List<TypeVariableName> typeVariables = new ArrayList();

        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        /* compiled from: FunSpec.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Modifier.values().length];
                iArr[Modifier.PUBLIC.ordinal()] = 1;
                iArr[Modifier.PROTECTED.ordinal()] = 2;
                iArr[Modifier.PRIVATE.ordinal()] = 3;
                iArr[Modifier.ABSTRACT.ordinal()] = 4;
                iArr[Modifier.FINAL.ordinal()] = 5;
                iArr[Modifier.NATIVE.ordinal()] = 6;
                iArr[Modifier.DEFAULT.ordinal()] = 7;
                iArr[Modifier.STATIC.ordinal()] = 8;
                iArr[Modifier.SYNCHRONIZED.ordinal()] = 9;
                iArr[Modifier.STRICTFP.ordinal()] = 10;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public final Builder receiver(TypeName typeName) {
            Intrinsics.checkNotNullParameter(typeName, "receiverType");
            return receiver$default(this, typeName, (CodeBlock) null, 2, (Object) null);
        }

        public final Builder receiver(Type type) {
            Intrinsics.checkNotNullParameter(type, "receiverType");
            return receiver$default(this, type, (CodeBlock) null, 2, (Object) null);
        }

        public final Builder receiver(KClass<?> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "receiverType");
            return receiver$default(this, (KClass) kClass, (CodeBlock) null, 2, (Object) null);
        }

        public final Builder returns(TypeName typeName) {
            Intrinsics.checkNotNullParameter(typeName, "returnType");
            return returns$default(this, typeName, (CodeBlock) null, 2, (Object) null);
        }

        public final Builder returns(Type type) {
            Intrinsics.checkNotNullParameter(type, "returnType");
            return returns$default(this, type, (CodeBlock) null, 2, (Object) null);
        }

        public final Builder returns(KClass<?> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "returnType");
            return returns$default(this, (KClass) kClass, (CodeBlock) null, 2, (Object) null);
        }

        public Builder(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            this.name = str;
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

        public final CodeBlock.Builder getKdoc$kotlinpoet() {
            return this.kdoc;
        }

        public final CodeBlock getReturnKdoc$kotlinpoet() {
            return this.returnKdoc;
        }

        public final void setReturnKdoc$kotlinpoet(CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(codeBlock, "<set-?>");
            this.returnKdoc = codeBlock;
        }

        public final CodeBlock getReceiverKdoc$kotlinpoet() {
            return this.receiverKdoc;
        }

        public final void setReceiverKdoc$kotlinpoet(CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(codeBlock, "<set-?>");
            this.receiverKdoc = codeBlock;
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

        public final TypeName getReturnType$kotlinpoet() {
            return this.returnType;
        }

        public final void setReturnType$kotlinpoet(TypeName typeName) {
            this.returnType = typeName;
        }

        public final String getDelegateConstructor$kotlinpoet() {
            return this.delegateConstructor;
        }

        public final void setDelegateConstructor$kotlinpoet(String str) {
            this.delegateConstructor = str;
        }

        public final List<CodeBlock> getDelegateConstructorArguments$kotlinpoet() {
            return this.delegateConstructorArguments;
        }

        public final void setDelegateConstructorArguments$kotlinpoet(List<CodeBlock> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.delegateConstructorArguments = list;
        }

        public final CodeBlock.Builder getBody$kotlinpoet() {
            return this.body;
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

        public final List<ParameterSpec> getParameters() {
            return this.parameters;
        }

        public Map<KClass<?>, Object> getTags() {
            return this.tags;
        }

        public List<Element> getOriginatingElements() {
            return this.originatingElements;
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

        public final void jvmModifiers(Iterable<? extends Modifier> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            KModifier kModifier = KModifier.INTERNAL;
            for (Modifier next : iterable) {
                switch (WhenMappings.$EnumSwitchMapping$0[next.ordinal()]) {
                    case 1:
                        kModifier = KModifier.PUBLIC;
                        break;
                    case 2:
                        kModifier = KModifier.PROTECTED;
                        break;
                    case 3:
                        kModifier = KModifier.PRIVATE;
                        break;
                    case 4:
                        this.modifiers.add(KModifier.ABSTRACT);
                        break;
                    case 5:
                        this.modifiers.add(KModifier.FINAL);
                        break;
                    case 6:
                        this.modifiers.add(KModifier.EXTERNAL);
                        break;
                    case 7:
                        break;
                    case 8:
                        addAnnotation((KClass<?>) Reflection.getOrCreateKotlinClass(JvmStatic.class));
                        break;
                    case 9:
                        addAnnotation((KClass<?>) Reflection.getOrCreateKotlinClass(Synchronized.class));
                        break;
                    case 10:
                        addAnnotation((KClass<?>) Reflection.getOrCreateKotlinClass(Strictfp.class));
                        break;
                    default:
                        throw new IllegalArgumentException("unexpected fun modifier " + next);
                }
            }
            this.modifiers.add(kModifier);
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

        public final Builder contextReceivers(Iterable<? extends TypeName> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "receiverTypes");
            Builder builder = this;
            if (!(!FunSpec.Companion.isConstructor$kotlinpoet(this.name))) {
                throw new IllegalStateException("constructors cannot have context receivers".toString());
            } else if (!FunSpec.Companion.isAccessor$kotlinpoet(this.name)) {
                CollectionsKt.addAll(this.contextReceiverTypes, iterable);
                return this;
            } else {
                throw new IllegalStateException((this.name + " cannot have context receivers").toString());
            }
        }

        public final Builder contextReceivers(TypeName... typeNameArr) {
            Intrinsics.checkNotNullParameter(typeNameArr, "receiverType");
            return contextReceivers((Iterable<? extends TypeName>) ArraysKt.toList((T[]) typeNameArr));
        }

        public static /* synthetic */ Builder receiver$default(Builder builder, TypeName typeName, CodeBlock codeBlock, int i, Object obj) {
            if ((i & 2) != 0) {
                codeBlock = CodeBlock.Companion.getEMPTY$kotlinpoet();
            }
            return builder.receiver(typeName, codeBlock);
        }

        public final Builder receiver(TypeName typeName, CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(typeName, "receiverType");
            Intrinsics.checkNotNullParameter(codeBlock, "kdoc");
            Builder builder = this;
            if (!FunSpec.Companion.isConstructor$kotlinpoet(this.name)) {
                this.receiverType = typeName;
                this.receiverKdoc = codeBlock;
                return this;
            }
            throw new IllegalStateException((this.name + " cannot have receiver type").toString());
        }

        public static /* synthetic */ Builder receiver$default(Builder builder, Type type, CodeBlock codeBlock, int i, Object obj) {
            if ((i & 2) != 0) {
                codeBlock = CodeBlock.Companion.getEMPTY$kotlinpoet();
            }
            return builder.receiver(type, codeBlock);
        }

        public final Builder receiver(Type type, CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(type, "receiverType");
            Intrinsics.checkNotNullParameter(codeBlock, "kdoc");
            return receiver(TypeNames.get(type), codeBlock);
        }

        public final Builder receiver(Type type, String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(type, "receiverType");
            Intrinsics.checkNotNullParameter(str, "kdoc");
            Intrinsics.checkNotNullParameter(objArr, "args");
            return receiver(type, CodeBlock.Companion.of(str, objArr));
        }

        public static /* synthetic */ Builder receiver$default(Builder builder, KClass kClass, CodeBlock codeBlock, int i, Object obj) {
            if ((i & 2) != 0) {
                codeBlock = CodeBlock.Companion.getEMPTY$kotlinpoet();
            }
            return builder.receiver((KClass<?>) kClass, codeBlock);
        }

        public final Builder receiver(KClass<?> kClass, CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(kClass, "receiverType");
            Intrinsics.checkNotNullParameter(codeBlock, "kdoc");
            return receiver((TypeName) TypeNames.get(kClass), codeBlock);
        }

        public final Builder receiver(KClass<?> kClass, String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(kClass, "receiverType");
            Intrinsics.checkNotNullParameter(str, "kdoc");
            Intrinsics.checkNotNullParameter(objArr, "args");
            return receiver(kClass, CodeBlock.Companion.of(str, objArr));
        }

        public static /* synthetic */ Builder returns$default(Builder builder, TypeName typeName, CodeBlock codeBlock, int i, Object obj) {
            if ((i & 2) != 0) {
                codeBlock = CodeBlock.Companion.getEMPTY$kotlinpoet();
            }
            return builder.returns(typeName, codeBlock);
        }

        public final Builder returns(TypeName typeName, CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(typeName, "returnType");
            Intrinsics.checkNotNullParameter(codeBlock, "kdoc");
            Builder builder = this;
            if (!FunSpec.Companion.isConstructor$kotlinpoet(this.name) && !FunSpec.Companion.isAccessor$kotlinpoet(this.name)) {
                this.returnType = typeName;
                this.returnKdoc = codeBlock;
                return this;
            }
            throw new IllegalStateException((this.name + " cannot have a return type").toString());
        }

        public static /* synthetic */ Builder returns$default(Builder builder, Type type, CodeBlock codeBlock, int i, Object obj) {
            if ((i & 2) != 0) {
                codeBlock = CodeBlock.Companion.getEMPTY$kotlinpoet();
            }
            return builder.returns(type, codeBlock);
        }

        public final Builder returns(Type type, CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(type, "returnType");
            Intrinsics.checkNotNullParameter(codeBlock, "kdoc");
            return returns(TypeNames.get(type), codeBlock);
        }

        public final Builder returns(Type type, String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(type, "returnType");
            Intrinsics.checkNotNullParameter(str, "kdoc");
            Intrinsics.checkNotNullParameter(objArr, "args");
            return returns(TypeNames.get(type), CodeBlock.Companion.of(str, objArr));
        }

        public static /* synthetic */ Builder returns$default(Builder builder, KClass kClass, CodeBlock codeBlock, int i, Object obj) {
            if ((i & 2) != 0) {
                codeBlock = CodeBlock.Companion.getEMPTY$kotlinpoet();
            }
            return builder.returns((KClass<?>) kClass, codeBlock);
        }

        public final Builder returns(KClass<?> kClass, CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(kClass, "returnType");
            Intrinsics.checkNotNullParameter(codeBlock, "kdoc");
            return returns((TypeName) TypeNames.get(kClass), codeBlock);
        }

        public final Builder returns(KClass<?> kClass, String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(kClass, "returnType");
            Intrinsics.checkNotNullParameter(str, "kdoc");
            Intrinsics.checkNotNullParameter(objArr, "args");
            return returns((TypeName) TypeNames.get(kClass), CodeBlock.Companion.of(str, objArr));
        }

        public final Builder addParameters(Iterable<ParameterSpec> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "parameterSpecs");
            Builder builder = this;
            for (ParameterSpec addParameter : iterable) {
                addParameter(addParameter);
            }
            return this;
        }

        public final Builder addParameter(ParameterSpec parameterSpec) {
            Intrinsics.checkNotNullParameter(parameterSpec, "parameterSpec");
            Builder builder = this;
            this.parameters.add(parameterSpec);
            return this;
        }

        public final Builder callThisConstructor(List<CodeBlock> list) {
            Intrinsics.checkNotNullParameter(list, "args");
            Builder builder = this;
            callConstructor("this", list);
            return this;
        }

        public final Builder callThisConstructor(Iterable<CodeBlock> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "args");
            Builder builder = this;
            callConstructor("this", CollectionsKt.toList(iterable));
            return this;
        }

        public final Builder callThisConstructor(String... strArr) {
            Intrinsics.checkNotNullParameter(strArr, "args");
            Builder builder = this;
            Collection arrayList = new ArrayList(strArr.length);
            for (String of : strArr) {
                arrayList.add(CodeBlock.Companion.of(of, new Object[0]));
            }
            callConstructor("this", (List) arrayList);
            return this;
        }

        public final Builder callThisConstructor(CodeBlock... codeBlockArr) {
            Intrinsics.checkNotNullParameter(codeBlockArr, "args");
            Builder builder = this;
            callConstructor("this", ArraysKt.toList((T[]) codeBlockArr));
            return this;
        }

        public final Builder callSuperConstructor(Iterable<CodeBlock> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "args");
            Builder builder = this;
            callConstructor("super", CollectionsKt.toList(iterable));
            return this;
        }

        public final Builder callSuperConstructor(List<CodeBlock> list) {
            Intrinsics.checkNotNullParameter(list, "args");
            Builder builder = this;
            callConstructor("super", list);
            return this;
        }

        public final Builder callSuperConstructor(String... strArr) {
            Intrinsics.checkNotNullParameter(strArr, "args");
            Builder builder = this;
            Collection arrayList = new ArrayList(strArr.length);
            for (String of : strArr) {
                arrayList.add(CodeBlock.Companion.of(of, new Object[0]));
            }
            callConstructor("super", (List) arrayList);
            return this;
        }

        public final Builder callSuperConstructor(CodeBlock... codeBlockArr) {
            Intrinsics.checkNotNullParameter(codeBlockArr, "args");
            Builder builder = this;
            callConstructor("super", ArraysKt.toList((T[]) codeBlockArr));
            return this;
        }

        private final void callConstructor(String str, List<CodeBlock> list) {
            if (FunSpec.Companion.isConstructor$kotlinpoet(this.name)) {
                this.delegateConstructor = str;
                this.delegateConstructorArguments = list;
                return;
            }
            throw new IllegalStateException("only constructors can delegate to other constructors!".toString());
        }

        public final Builder addParameter(String str, TypeName typeName, KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeName, "type");
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            return addParameter(ParameterSpec.Companion.builder(str, typeName, (KModifier[]) Arrays.copyOf(kModifierArr, kModifierArr.length)).build());
        }

        public final Builder addParameter(String str, Type type, KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            return addParameter(str, TypeNames.get(type), (KModifier[]) Arrays.copyOf(kModifierArr, kModifierArr.length));
        }

        public final Builder addParameter(String str, KClass<?> kClass, KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(kClass, "type");
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            return addParameter(str, (TypeName) TypeNames.get(kClass), (KModifier[]) Arrays.copyOf(kModifierArr, kModifierArr.length));
        }

        public final Builder addParameter(String str, TypeName typeName, Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeName, "type");
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            return addParameter(ParameterSpec.Companion.builder(str, typeName, iterable).build());
        }

        public final Builder addParameter(String str, Type type, Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            return addParameter(str, TypeNames.get(type), iterable);
        }

        public final Builder addParameter(String str, KClass<?> kClass, Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(kClass, "type");
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            return addParameter(str, (TypeName) TypeNames.get(kClass), iterable);
        }

        public final Builder addCode(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            this.body.add(str, Arrays.copyOf(objArr, objArr.length));
            return this;
        }

        public final Builder addNamedCode(String str, Map<String, ?> map) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(map, "args");
            Builder builder = this;
            this.body.addNamed(str, map);
            return this;
        }

        public final Builder addCode(CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(codeBlock, "codeBlock");
            Builder builder = this;
            this.body.add(codeBlock);
            return this;
        }

        public final Builder addComment(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            this.body.add("//·" + StringsKt.replace$default(str, ' ', (char) Typography.middleDot, false, 4, (Object) null) + 10, Arrays.copyOf(objArr, objArr.length));
            return this;
        }

        public final Builder beginControlFlow(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "controlFlow");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            this.body.beginControlFlow(str, Arrays.copyOf(objArr, objArr.length));
            return this;
        }

        public final Builder nextControlFlow(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "controlFlow");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            this.body.nextControlFlow(str, Arrays.copyOf(objArr, objArr.length));
            return this;
        }

        public final Builder endControlFlow() {
            Builder builder = this;
            this.body.endControlFlow();
            return this;
        }

        public final Builder addStatement(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Builder builder = this;
            this.body.addStatement(str, Arrays.copyOf(objArr, objArr.length));
            return this;
        }

        public final Builder clearBody() {
            Builder builder = this;
            this.body.clear();
            return this;
        }

        public final FunSpec build() {
            boolean z = false;
            if (this.typeVariables.isEmpty() || !FunSpec.Companion.isAccessor$kotlinpoet(this.name)) {
                if (!Intrinsics.areEqual((Object) this.name, (Object) FunSpec.GETTER) || !(this.parameters.isEmpty() ^ true)) {
                    if (!Intrinsics.areEqual((Object) this.name, (Object) FunSpec.SETTER) || this.parameters.size() <= 1) {
                        z = true;
                    }
                    if (z) {
                        return new FunSpec(this, (TagMap) null, (OriginatingElementsHolder) null, 6, (DefaultConstructorMarker) null);
                    }
                    throw new IllegalStateException((this.name + " can have at most one parameter").toString());
                }
                throw new IllegalStateException((this.name + " cannot have parameters").toString());
            }
            throw new IllegalStateException((this.name + " cannot have type variables").toString());
        }

        public static /* synthetic */ Builder callThisConstructor$default(Builder builder, CodeBlock[] codeBlockArr, int i, Object obj) {
            if ((i & 1) != 0) {
                codeBlockArr = (CodeBlock[]) ((Object[]) new CodeBlock[0]);
            }
            return builder.callThisConstructor(codeBlockArr);
        }

        public static /* synthetic */ Builder callSuperConstructor$default(Builder builder, CodeBlock[] codeBlockArr, int i, Object obj) {
            if ((i & 1) != 0) {
                codeBlockArr = (CodeBlock[]) ((Object[]) new CodeBlock[0]);
            }
            return builder.callSuperConstructor(codeBlockArr);
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004H\u0007J\b\u0010\u0015\u001a\u00020\u0013H\u0007J\b\u0010\u0016\u001a\u00020\u0013H\u0007J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J \u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\b\u0010\u001e\u001a\u00020\u0013H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\u00020\r*\u00020\u00048@X\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\r*\u00020\u00048@X\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/squareup/kotlinpoet/FunSpec$Companion;", "", "()V", "CONSTRUCTOR", "", "GETTER", "RETURN_EXPRESSION_BODY_PREFIX_NBSP", "Lcom/squareup/kotlinpoet/CodeBlock;", "RETURN_EXPRESSION_BODY_PREFIX_SPACE", "SETTER", "THROW_EXPRESSION_BODY_PREFIX_NBSP", "THROW_EXPRESSION_BODY_PREFIX_SPACE", "isAccessor", "", "isAccessor$kotlinpoet", "(Ljava/lang/String;)Z", "isConstructor", "isConstructor$kotlinpoet", "builder", "Lcom/squareup/kotlinpoet/FunSpec$Builder;", "name", "constructorBuilder", "getterBuilder", "overriding", "method", "Ljavax/lang/model/element/ExecutableElement;", "enclosing", "Ljavax/lang/model/type/DeclaredType;", "types", "Ljavax/lang/model/util/Types;", "setterBuilder", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: FunSpec.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isConstructor$kotlinpoet(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return Intrinsics.areEqual((Object) str, (Object) FunSpec.CONSTRUCTOR);
        }

        public final boolean isAccessor$kotlinpoet(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return UtilKt.isOneOf$default(str, FunSpec.GETTER, FunSpec.SETTER, (Object) null, (Object) null, (Object) null, (Object) null, 60, (Object) null);
        }

        @JvmStatic
        public final Builder builder(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            return new Builder(str);
        }

        @JvmStatic
        public final Builder constructorBuilder() {
            return new Builder(FunSpec.CONSTRUCTOR);
        }

        @JvmStatic
        public final Builder getterBuilder() {
            return new Builder(FunSpec.GETTER);
        }

        @JvmStatic
        public final Builder setterBuilder() {
            return new Builder(FunSpec.SETTER);
        }

        @JvmStatic
        public final Builder overriding(ExecutableElement executableElement) {
            Intrinsics.checkNotNullParameter(executableElement, "method");
            Set modifiers = executableElement.getModifiers();
            Intrinsics.checkNotNullExpressionValue(modifiers, "method.modifiers");
            if (!modifiers.contains(Modifier.PRIVATE) && !modifiers.contains(Modifier.FINAL) && !modifiers.contains(Modifier.STATIC)) {
                Builder builder = builder(executableElement.getSimpleName().toString());
                builder.addModifiers(KModifier.OVERRIDE);
                Set mutableSet = CollectionsKt.toMutableSet(modifiers);
                mutableSet.remove(Modifier.ABSTRACT);
                builder.jvmModifiers(mutableSet);
                List typeParameters = executableElement.getTypeParameters();
                Intrinsics.checkNotNullExpressionValue(typeParameters, "method.typeParameters");
                Iterable<TypeParameterElement> iterable = typeParameters;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (TypeParameterElement asType : iterable) {
                    TypeVariable asType2 = asType.asType();
                    Intrinsics.checkNotNull(asType2, "null cannot be cast to non-null type javax.lang.model.type.TypeVariable");
                    arrayList.add(asType2);
                }
                Iterable<TypeVariable> iterable2 = (List) arrayList;
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                for (TypeVariable typeVariable : iterable2) {
                    arrayList2.add(TypeVariableNames.get(typeVariable));
                }
                for (TypeVariableName addTypeVariable : (List) arrayList2) {
                    builder.addTypeVariable(addTypeVariable);
                }
                TypeMirror returnType = executableElement.getReturnType();
                Intrinsics.checkNotNullExpressionValue(returnType, "method.returnType");
                Builder.returns$default(builder, TypeNames.get(returnType), (CodeBlock) null, 2, (Object) null);
                builder.addParameters(ParameterSpec.Companion.parametersOf(executableElement));
                if (executableElement.isVarArgs()) {
                    builder.getParameters().set(CollectionsKt.getLastIndex(builder.getParameters()), ParameterSpec.toBuilder$default((ParameterSpec) CollectionsKt.last(builder.getParameters()), (String) null, (TypeName) null, 3, (Object) null).addModifiers(KModifier.VARARG).build());
                }
                List thrownTypes = executableElement.getThrownTypes();
                Intrinsics.checkNotNullExpressionValue(thrownTypes, "method.thrownTypes");
                if (!thrownTypes.isEmpty()) {
                    List thrownTypes2 = executableElement.getThrownTypes();
                    Intrinsics.checkNotNullExpressionValue(thrownTypes2, "method.thrownTypes");
                    String joinToString$default = CollectionsKt.joinToString$default(thrownTypes2, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, FunSpec$Companion$overriding$throwsValueString$1.INSTANCE, 31, (Object) null);
                    AnnotationSpec.Builder builder2 = AnnotationSpec.Companion.builder((KClass<? extends Annotation>) Reflection.getOrCreateKotlinClass(Throws.class));
                    List thrownTypes3 = executableElement.getThrownTypes();
                    Intrinsics.checkNotNullExpressionValue(thrownTypes3, "method.thrownTypes");
                    Object[] array = thrownTypes3.toArray(new TypeMirror[0]);
                    Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    TypeMirror[] typeMirrorArr = (TypeMirror[]) array;
                    builder.addAnnotation(builder2.addMember(joinToString$default, Arrays.copyOf(typeMirrorArr, typeMirrorArr.length)).build());
                }
                return builder;
            }
            throw new IllegalArgumentException(("cannot override method with modifiers: " + modifiers).toString());
        }

        @JvmStatic
        @Deprecated(level = DeprecationLevel.WARNING, message = "Element APIs don't give complete information on Kotlin types. Consider using the kotlinpoet-metadata APIs instead.")
        public final Builder overriding(ExecutableElement executableElement, DeclaredType declaredType, Types types) {
            Intrinsics.checkNotNullParameter(executableElement, "method");
            Intrinsics.checkNotNullParameter(declaredType, "enclosing");
            Intrinsics.checkNotNullParameter(types, "types");
            ExecutableType asMemberOf = types.asMemberOf(declaredType, (Element) executableElement);
            Intrinsics.checkNotNull(asMemberOf, "null cannot be cast to non-null type javax.lang.model.type.ExecutableType");
            ExecutableType executableType = asMemberOf;
            List parameterTypes = executableType.getParameterTypes();
            TypeMirror returnType = executableType.getReturnType();
            Builder overriding = overriding(executableElement);
            Intrinsics.checkNotNullExpressionValue(returnType, "resolvedReturnType");
            Builder.returns$default(overriding, TypeNames.get(returnType), (CodeBlock) null, 2, (Object) null);
            int size = overriding.getParameters().size();
            for (int i = 0; i < size; i++) {
                ParameterSpec parameterSpec = overriding.getParameters().get(i);
                Object obj = parameterTypes.get(i);
                Intrinsics.checkNotNullExpressionValue(obj, "resolvedParameterTypes[i]");
                overriding.getParameters().set(i, parameterSpec.toBuilder(parameterSpec.getName(), TypeNames.get((TypeMirror) obj)).build());
            }
            return overriding;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003f, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003c, code lost:
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
            r1 = r10
            com.squareup.kotlinpoet.CodeWriter r1 = (com.squareup.kotlinpoet.CodeWriter) r1     // Catch:{ all -> 0x0039 }
            java.lang.String r2 = "Constructor"
            com.squareup.kotlinpoet.TypeSpec$Kind r3 = com.squareup.kotlinpoet.TypeSpec.Kind.CLASS     // Catch:{ all -> 0x0039 }
            r4 = 1
            r5 = 0
            java.util.Set r3 = com.squareup.kotlinpoet.TypeSpec.Kind.implicitFunctionModifiers$kotlinpoet$default(r3, r5, r4, r5)     // Catch:{ all -> 0x0039 }
            r11.emit$kotlinpoet(r1, r2, r3, r4)     // Catch:{ all -> 0x0039 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0039 }
            kotlin.io.CloseableKt.closeFinally(r10, r5)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "stringBuilder.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        L_0x0039:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x003b }
        L_0x003b:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r10, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.FunSpec.toString():java.lang.String");
    }
}
