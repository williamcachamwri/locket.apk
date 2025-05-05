package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.Taggable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 @2\u00020\u0001:\u0002?@B+\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0002\u0010\tB%\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\u0010\u000bB\u0019\b\u0002\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J3\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020.2\b\b\u0002\u00100\u001a\u00020.H\u0000¢\u0006\u0002\b1J\u0015\u00102\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0000¢\u0006\u0002\b3J\u0013\u00104\u001a\u00020.2\b\u00105\u001a\u0004\u0018\u00010$H\u0002J\b\u00106\u001a\u000207H\u0016J(\u00108\u001a\u0004\u0018\u0001H9\"\b\b\u0000\u00109*\u00020$2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H90:H\u0001¢\u0006\u0002\u0010;J(\u00108\u001a\u0004\u0018\u0001H9\"\b\b\u0000\u00109*\u00020$2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H90#H\u0001¢\u0006\u0002\u0010<J\u001a\u0010=\u001a\u00020\r2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005J\b\u0010>\u001a\u00020\u0003H\u0016R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R$\u0010!\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030#\u0012\u0004\u0012\u00020$0\"8VX\u0005¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006A"}, d2 = {"Lcom/squareup/kotlinpoet/ParameterSpec;", "Lcom/squareup/kotlinpoet/Taggable;", "name", "", "type", "Lcom/squareup/kotlinpoet/TypeName;", "modifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/TypeName;[Lcom/squareup/kotlinpoet/KModifier;)V", "", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/TypeName;Ljava/lang/Iterable;)V", "builder", "Lcom/squareup/kotlinpoet/ParameterSpec$Builder;", "tagMap", "Lcom/squareup/kotlinpoet/TagMap;", "(Lcom/squareup/kotlinpoet/ParameterSpec$Builder;Lcom/squareup/kotlinpoet/TagMap;)V", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotations", "()Ljava/util/List;", "defaultValue", "Lcom/squareup/kotlinpoet/CodeBlock;", "getDefaultValue", "()Lcom/squareup/kotlinpoet/CodeBlock;", "kdoc", "getKdoc", "", "getModifiers", "()Ljava/util/Set;", "getName", "()Ljava/lang/String;", "tags", "", "Lkotlin/reflect/KClass;", "", "getTags", "()Ljava/util/Map;", "getType", "()Lcom/squareup/kotlinpoet/TypeName;", "emit", "", "codeWriter", "Lcom/squareup/kotlinpoet/CodeWriter;", "includeType", "", "emitKdoc", "inlineAnnotations", "emit$kotlinpoet", "emitDefaultValue", "emitDefaultValue$kotlinpoet", "equals", "other", "hashCode", "", "tag", "T", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toBuilder", "toString", "Builder", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: ParameterSpec.kt */
public final class ParameterSpec implements Taggable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<AnnotationSpec> annotations;
    private final CodeBlock defaultValue;
    private final CodeBlock kdoc;
    private final Set<KModifier> modifiers;
    private final String name;
    private final TagMap tagMap;
    private final TypeName type;

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

    @JvmStatic
    public static final ParameterSpec get(VariableElement variableElement) {
        return Companion.get(variableElement);
    }

    @JvmStatic
    public static final List<ParameterSpec> parametersOf(ExecutableElement executableElement) {
        return Companion.parametersOf(executableElement);
    }

    @JvmStatic
    public static final ParameterSpec unnamed(TypeName typeName) {
        return Companion.unnamed(typeName);
    }

    @JvmStatic
    public static final ParameterSpec unnamed(Type type2) {
        return Companion.unnamed(type2);
    }

    @JvmStatic
    public static final ParameterSpec unnamed(KClass<?> kClass) {
        return Companion.unnamed(kClass);
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

    private ParameterSpec(Builder builder, TagMap tagMap2) {
        this.tagMap = tagMap2;
        this.name = builder.getName$kotlinpoet();
        this.kdoc = builder.getKdoc().build();
        this.annotations = UtilKt.toImmutableList(builder.getAnnotations());
        Collection modifiers2 = builder.getModifiers();
        LinkedHashSet linkedHashSet = new LinkedHashSet(modifiers2);
        linkedHashSet.removeAll(ParameterSpecKt.ALLOWED_PARAMETER_MODIFIERS);
        if (linkedHashSet.isEmpty()) {
            this.modifiers = UtilKt.toImmutableSet(modifiers2);
            this.type = builder.getType$kotlinpoet();
            this.defaultValue = builder.getDefaultValue$kotlinpoet();
            return;
        }
        throw new IllegalArgumentException("Modifiers " + linkedHashSet + " are not allowed on Kotlin parameters. Allowed modifiers: " + ParameterSpecKt.ALLOWED_PARAMETER_MODIFIERS);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ ParameterSpec(Builder builder, TagMap tagMap2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder, (i & 2) != 0 ? TaggableKt.buildTagMap(builder) : tagMap2);
    }

    public final String getName() {
        return this.name;
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

    public final TypeName getType() {
        return this.type;
    }

    public final CodeBlock getDefaultValue() {
        return this.defaultValue;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ParameterSpec(String str, TypeName typeName, KModifier... kModifierArr) {
        this(Companion.builder(str, typeName, (KModifier[]) Arrays.copyOf(kModifierArr, kModifierArr.length)), (TagMap) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(typeName, "type");
        Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ParameterSpec(String str, TypeName typeName, Iterable<? extends KModifier> iterable) {
        this(Companion.builder(str, typeName, iterable), (TagMap) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(typeName, "type");
        Intrinsics.checkNotNullParameter(iterable, "modifiers");
    }

    public static /* synthetic */ void emit$kotlinpoet$default(ParameterSpec parameterSpec, CodeWriter codeWriter, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        if ((i & 8) != 0) {
            z3 = true;
        }
        parameterSpec.emit$kotlinpoet(codeWriter, z, z2, z3);
    }

    public final void emit$kotlinpoet(CodeWriter codeWriter, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        if (z2) {
            codeWriter.emitKdoc(UtilKt.ensureEndsWithNewLine(this.kdoc));
        }
        codeWriter.emitAnnotations(this.annotations, z3);
        CodeWriter.emitModifiers$default(codeWriter, this.modifiers, (Set) null, 2, (Object) null);
        boolean z4 = true;
        if (this.name.length() > 0) {
            codeWriter.emitCode("%N", this);
        }
        if (this.name.length() <= 0) {
            z4 = false;
        }
        if (z4 && z) {
            codeWriter.emitCode(":·");
        }
        if (z) {
            codeWriter.emitCode("%T", this.type);
        }
        emitDefaultValue$kotlinpoet(codeWriter);
    }

    public final void emitDefaultValue$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        CodeBlock codeBlock = this.defaultValue;
        if (codeBlock != null) {
            codeWriter.emitCode(codeBlock.hasStatements$kotlinpoet() ? " = %L" : " = «%L»", this.defaultValue);
        }
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

    public static /* synthetic */ Builder toBuilder$default(ParameterSpec parameterSpec, String str, TypeName typeName, int i, Object obj) {
        if ((i & 1) != 0) {
            str = parameterSpec.name;
        }
        if ((i & 2) != 0) {
            typeName = parameterSpec.type;
        }
        return parameterSpec.toBuilder(str, typeName);
    }

    public final Builder toBuilder(String str, TypeName typeName) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(typeName, "type");
        Builder builder = new Builder(str, typeName);
        builder.getKdoc().add(this.kdoc);
        CollectionsKt.addAll(builder.getAnnotations(), this.annotations);
        CollectionsKt.addAll(builder.getModifiers(), this.modifiers);
        builder.setDefaultValue$kotlinpoet(this.defaultValue);
        builder.getTags().putAll(this.tagMap.getTags());
        return builder;
    }

    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\tJ\u000e\u0010#\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&J\u0012\u0010#\u001a\u00020\u00002\n\u0010%\u001a\u0006\u0012\u0002\b\u00030'J\u0012\u0010#\u001a\u00020\u00002\n\u0010%\u001a\u0006\u0012\u0002\b\u00030\u001dJ\u0014\u0010(\u001a\u00020\u00002\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0*J\u000e\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\rJ'\u0010+\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u00032\u0012\u0010.\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001e0/\"\u00020\u001e¢\u0006\u0002\u00100J\u001f\u00101\u001a\u00020\u00002\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170/\"\u00020\u0017¢\u0006\u0002\u00102J\u0014\u00101\u001a\u00020\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170*J\u0006\u00103\u001a\u000204J\u0010\u0010\f\u001a\u00020\u00002\b\u00105\u001a\u0004\u0018\u00010\rJ+\u0010\f\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u00032\u0016\u0010.\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001e0/\"\u0004\u0018\u00010\u001e¢\u0006\u0002\u00100J\u0016\u00106\u001a\u00020\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002070*H\u0007R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d\u0012\u0004\u0012\u00020\u001e0\u001cX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00068"}, d2 = {"Lcom/squareup/kotlinpoet/ParameterSpec$Builder;", "Lcom/squareup/kotlinpoet/Taggable$Builder;", "name", "", "type", "Lcom/squareup/kotlinpoet/TypeName;", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/TypeName;)V", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotations", "()Ljava/util/List;", "defaultValue", "Lcom/squareup/kotlinpoet/CodeBlock;", "getDefaultValue$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock;", "setDefaultValue$kotlinpoet", "(Lcom/squareup/kotlinpoet/CodeBlock;)V", "kdoc", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "getKdoc", "()Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "modifiers", "Lcom/squareup/kotlinpoet/KModifier;", "getModifiers", "getName$kotlinpoet", "()Ljava/lang/String;", "tags", "", "Lkotlin/reflect/KClass;", "", "getTags", "()Ljava/util/Map;", "getType$kotlinpoet", "()Lcom/squareup/kotlinpoet/TypeName;", "addAnnotation", "annotationSpec", "annotation", "Lcom/squareup/kotlinpoet/ClassName;", "Ljava/lang/Class;", "addAnnotations", "annotationSpecs", "", "addKdoc", "block", "format", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/ParameterSpec$Builder;", "addModifiers", "([Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/ParameterSpec$Builder;", "build", "Lcom/squareup/kotlinpoet/ParameterSpec;", "codeBlock", "jvmModifiers", "Ljavax/lang/model/element/Modifier;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: ParameterSpec.kt */
    public static final class Builder implements Taggable.Builder<Builder> {
        private final List<AnnotationSpec> annotations = new ArrayList();
        private CodeBlock defaultValue;
        private final CodeBlock.Builder kdoc = CodeBlock.Companion.builder();
        private final List<KModifier> modifiers = new ArrayList();
        private final String name;
        private final Map<KClass<?>, Object> tags = new LinkedHashMap();
        private final TypeName type;

        public Builder(String str, TypeName typeName) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeName, "type");
            this.name = str;
            this.type = typeName;
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

        public final CodeBlock getDefaultValue$kotlinpoet() {
            return this.defaultValue;
        }

        public final void setDefaultValue$kotlinpoet(CodeBlock codeBlock) {
            this.defaultValue = codeBlock;
        }

        public final CodeBlock.Builder getKdoc() {
            return this.kdoc;
        }

        public final List<AnnotationSpec> getAnnotations() {
            return this.annotations;
        }

        public final List<KModifier> getModifiers() {
            return this.modifiers;
        }

        public Map<KClass<?>, Object> getTags() {
            return this.tags;
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

        @Deprecated(level = DeprecationLevel.ERROR, message = "There are no jvm modifiers applicable to parameters in Kotlin", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public final Builder jvmModifiers(Iterable<? extends Modifier> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            Builder builder = this;
            throw new IllegalArgumentException("JVM modifiers are not permitted on parameters in Kotlin");
        }

        public final Builder defaultValue(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            return defaultValue(CodeBlock.Companion.of(str, Arrays.copyOf(objArr, objArr.length)));
        }

        public final Builder defaultValue(CodeBlock codeBlock) {
            Builder builder = this;
            this.defaultValue = codeBlock;
            return this;
        }

        public final ParameterSpec build() {
            return new ParameterSpec(this, (TagMap) null, 2, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000bH\u0007¢\u0006\u0002\u0010\fJ&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0007J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000e2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000bH\u0007¢\u0006\u0002\u0010\u000fJ&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000e2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0007J5\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00102\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000bH\u0007¢\u0006\u0002\u0010\u0011J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u000eH\u0007J\u0014\u0010\u001a\u001a\u00020\u00132\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0007¨\u0006\u001b"}, d2 = {"Lcom/squareup/kotlinpoet/ParameterSpec$Companion;", "", "()V", "builder", "Lcom/squareup/kotlinpoet/ParameterSpec$Builder;", "name", "", "type", "Lcom/squareup/kotlinpoet/TypeName;", "modifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/TypeName;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/ParameterSpec$Builder;", "", "Ljava/lang/reflect/Type;", "(Ljava/lang/String;Ljava/lang/reflect/Type;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/ParameterSpec$Builder;", "Lkotlin/reflect/KClass;", "(Ljava/lang/String;Lkotlin/reflect/KClass;[Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/ParameterSpec$Builder;", "get", "Lcom/squareup/kotlinpoet/ParameterSpec;", "element", "Ljavax/lang/model/element/VariableElement;", "parametersOf", "", "method", "Ljavax/lang/model/element/ExecutableElement;", "unnamed", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: ParameterSpec.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ParameterSpec get(VariableElement variableElement) {
            Intrinsics.checkNotNullParameter(variableElement, "element");
            String obj = variableElement.getSimpleName().toString();
            TypeMirror asType = variableElement.asType();
            Intrinsics.checkNotNullExpressionValue(asType, "element.asType()");
            return builder(obj, TypeNames.get(asType), new KModifier[0]).build();
        }

        @JvmStatic
        public final List<ParameterSpec> parametersOf(ExecutableElement executableElement) {
            Intrinsics.checkNotNullParameter(executableElement, "method");
            List parameters = executableElement.getParameters();
            Intrinsics.checkNotNullExpressionValue(parameters, "method.parameters");
            Iterable<VariableElement> iterable = parameters;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (VariableElement variableElement : iterable) {
                arrayList.add(get(variableElement));
            }
            return (List) arrayList;
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

        @JvmStatic
        public final ParameterSpec unnamed(KClass<?> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "type");
            return unnamed((TypeName) TypeNames.get(kClass));
        }

        @JvmStatic
        public final ParameterSpec unnamed(Type type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return unnamed(TypeNames.get(type));
        }

        @JvmStatic
        public final ParameterSpec unnamed(TypeName typeName) {
            Intrinsics.checkNotNullParameter(typeName, "type");
            return new Builder("", typeName).build();
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
            r6 = 14
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
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.ParameterSpec.toString():java.lang.String");
    }
}
