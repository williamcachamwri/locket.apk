package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.Taggable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 82\u00020\u0001:\u000278B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0000¢\u0006\u0002\b*J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u001cH\u0002J\b\u0010.\u001a\u00020/H\u0016J(\u00100\u001a\u0004\u0018\u0001H1\"\b\b\u0000\u00101*\u00020\u001c2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H102H\u0001¢\u0006\u0002\u00103J(\u00100\u001a\u0004\u0018\u0001H1\"\b\b\u0000\u00101*\u00020\u001c2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H10\u001bH\u0001¢\u0006\u0002\u00104J\u001c\u00105\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u001f\u001a\u00020 H\u0007J\b\u00106\u001a\u00020\u0016H\u0016R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R$\u0010\u0019\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b\u0012\u0004\u0012\u00020\u001c0\u001a8VX\u0005¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u000b¨\u00069"}, d2 = {"Lcom/squareup/kotlinpoet/TypeAliasSpec;", "Lcom/squareup/kotlinpoet/Taggable;", "builder", "Lcom/squareup/kotlinpoet/TypeAliasSpec$Builder;", "tagMap", "Lcom/squareup/kotlinpoet/TagMap;", "(Lcom/squareup/kotlinpoet/TypeAliasSpec$Builder;Lcom/squareup/kotlinpoet/TagMap;)V", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotations", "()Ljava/util/List;", "kdoc", "Lcom/squareup/kotlinpoet/CodeBlock;", "getKdoc", "()Lcom/squareup/kotlinpoet/CodeBlock;", "modifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "getModifiers", "()Ljava/util/Set;", "name", "", "getName", "()Ljava/lang/String;", "tags", "", "Lkotlin/reflect/KClass;", "", "getTags", "()Ljava/util/Map;", "type", "Lcom/squareup/kotlinpoet/TypeName;", "getType", "()Lcom/squareup/kotlinpoet/TypeName;", "typeVariables", "Lcom/squareup/kotlinpoet/TypeVariableName;", "getTypeVariables", "emit", "", "codeWriter", "Lcom/squareup/kotlinpoet/CodeWriter;", "emit$kotlinpoet", "equals", "", "other", "hashCode", "", "tag", "T", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toBuilder", "toString", "Builder", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeAliasSpec.kt */
public final class TypeAliasSpec implements Taggable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<AnnotationSpec> annotations;
    private final CodeBlock kdoc;
    private final Set<KModifier> modifiers;
    private final String name;
    private final TagMap tagMap;
    private final TypeName type;
    private final List<TypeVariableName> typeVariables;

    @JvmStatic
    public static final Builder builder(String str, TypeName typeName) {
        return Companion.builder(str, typeName);
    }

    @JvmStatic
    public static final Builder builder(String str, Type type2) {
        return Companion.builder(str, type2);
    }

    @JvmStatic
    public static final Builder builder(String str, KClass<?> kClass) {
        return Companion.builder(str, kClass);
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

    private TypeAliasSpec(Builder builder, TagMap tagMap2) {
        this.tagMap = tagMap2;
        this.name = builder.getName$kotlinpoet();
        this.type = builder.getType$kotlinpoet();
        this.modifiers = UtilKt.toImmutableSet(builder.getModifiers());
        this.typeVariables = UtilKt.toImmutableList(builder.getTypeVariables());
        this.kdoc = builder.getKdoc$kotlinpoet().build();
        this.annotations = UtilKt.toImmutableList(builder.getAnnotations());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ TypeAliasSpec(Builder builder, TagMap tagMap2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder, (i & 2) != 0 ? TaggableKt.buildTagMap(builder) : tagMap2);
    }

    public final String getName() {
        return this.name;
    }

    public final TypeName getType() {
        return this.type;
    }

    public final Set<KModifier> getModifiers() {
        return this.modifiers;
    }

    public final List<TypeVariableName> getTypeVariables() {
        return this.typeVariables;
    }

    public final CodeBlock getKdoc() {
        return this.kdoc;
    }

    public final List<AnnotationSpec> getAnnotations() {
        return this.annotations;
    }

    public final void emit$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        codeWriter.emitKdoc(UtilKt.ensureEndsWithNewLine(this.kdoc));
        codeWriter.emitAnnotations(this.annotations, false);
        codeWriter.emitModifiers(this.modifiers, SetsKt.setOf(KModifier.PUBLIC));
        codeWriter.emitCode("typealias %N", this.name);
        codeWriter.emitTypeVariables(this.typeVariables);
        codeWriter.emitCode(" = %T", this.type);
        CodeWriter.emit$default(codeWriter, "\n", false, 2, (Object) null);
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

    public static /* synthetic */ Builder toBuilder$default(TypeAliasSpec typeAliasSpec, String str, TypeName typeName, int i, Object obj) {
        if ((i & 1) != 0) {
            str = typeAliasSpec.name;
        }
        if ((i & 2) != 0) {
            typeName = typeAliasSpec.type;
        }
        return typeAliasSpec.toBuilder(str, typeName);
    }

    public final Builder toBuilder(String str, TypeName typeName) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(typeName, "type");
        Builder builder = new Builder(str, typeName);
        CollectionsKt.addAll(builder.getModifiers(), this.modifiers);
        CollectionsKt.addAll(builder.getTypeVariables(), this.typeVariables);
        builder.getKdoc$kotlinpoet().add(this.kdoc);
        CollectionsKt.addAll(builder.getAnnotations(), this.annotations);
        builder.getTags().putAll(this.tagMap.getTags());
        return builder;
    }

    @Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 ;2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001;B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\tJ\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%J\u0014\u0010\"\u001a\u00020\u00002\n\u0010$\u001a\u0006\u0012\u0002\b\u00030&H\u0007J\u0012\u0010\"\u001a\u00020\u00002\n\u0010$\u001a\u0006\u0012\u0002\b\u00030\u0019J\u0014\u0010'\u001a\u00020\u00002\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0)J\u000e\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,J'\u0010*\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u00032\u0012\u0010.\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001a0/\"\u00020\u001a¢\u0006\u0002\u00100J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0012H\u0002J\u001f\u00104\u001a\u00020\u00002\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120/\"\u00020\u0012¢\u0006\u0002\u00105J\u0014\u00104\u001a\u00020\u00002\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120)J\u000e\u00106\u001a\u00020\u00002\u0006\u00107\u001a\u00020 J\u0014\u00108\u001a\u00020\u00002\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0)J\u0006\u00109\u001a\u00020:R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0019\u0012\u0004\u0012\u00020\u001a0\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0011¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014¨\u0006<"}, d2 = {"Lcom/squareup/kotlinpoet/TypeAliasSpec$Builder;", "Lcom/squareup/kotlinpoet/Taggable$Builder;", "name", "", "type", "Lcom/squareup/kotlinpoet/TypeName;", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/TypeName;)V", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "getAnnotations", "()Ljava/util/List;", "kdoc", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "getKdoc$kotlinpoet", "()Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "modifiers", "", "Lcom/squareup/kotlinpoet/KModifier;", "getModifiers", "()Ljava/util/Set;", "getName$kotlinpoet", "()Ljava/lang/String;", "tags", "", "Lkotlin/reflect/KClass;", "", "getTags", "()Ljava/util/Map;", "getType$kotlinpoet", "()Lcom/squareup/kotlinpoet/TypeName;", "typeVariables", "Lcom/squareup/kotlinpoet/TypeVariableName;", "getTypeVariables", "addAnnotation", "annotationSpec", "annotation", "Lcom/squareup/kotlinpoet/ClassName;", "Ljava/lang/Class;", "addAnnotations", "annotationSpecs", "", "addKdoc", "block", "Lcom/squareup/kotlinpoet/CodeBlock;", "format", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/TypeAliasSpec$Builder;", "addModifier", "", "modifier", "addModifiers", "([Lcom/squareup/kotlinpoet/KModifier;)Lcom/squareup/kotlinpoet/TypeAliasSpec$Builder;", "addTypeVariable", "typeVariable", "addTypeVariables", "build", "Lcom/squareup/kotlinpoet/TypeAliasSpec;", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: TypeAliasSpec.kt */
    public static final class Builder implements Taggable.Builder<Builder> {
        @Deprecated
        private static final Set<KModifier> ALLOWABLE_MODIFIERS = SetsKt.setOf(KModifier.PUBLIC, KModifier.INTERNAL, KModifier.PRIVATE, KModifier.ACTUAL);
        private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final List<AnnotationSpec> annotations = new ArrayList();
        private final CodeBlock.Builder kdoc = CodeBlock.Companion.builder();
        private final Set<KModifier> modifiers = new LinkedHashSet();
        private final String name;
        private final Map<KClass<?>, Object> tags = new LinkedHashMap();
        private final TypeName type;
        private final Set<TypeVariableName> typeVariables = new LinkedHashSet();

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

        public final CodeBlock.Builder getKdoc$kotlinpoet() {
            return this.kdoc;
        }

        public final Set<KModifier> getModifiers() {
            return this.modifiers;
        }

        public final Set<TypeVariableName> getTypeVariables() {
            return this.typeVariables;
        }

        public final List<AnnotationSpec> getAnnotations() {
            return this.annotations;
        }

        public Map<KClass<?>, Object> getTags() {
            return this.tags;
        }

        public final Builder addModifiers(KModifier... kModifierArr) {
            Intrinsics.checkNotNullParameter(kModifierArr, "modifiers");
            Builder builder = this;
            for (KModifier addModifier : kModifierArr) {
                addModifier(addModifier);
            }
            return this;
        }

        public final Builder addModifiers(Iterable<? extends KModifier> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "modifiers");
            Builder builder = this;
            for (KModifier addModifier : iterable) {
                addModifier(addModifier);
            }
            return this;
        }

        private final void addModifier(KModifier kModifier) {
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

        public final TypeAliasSpec build() {
            for (KModifier next : this.modifiers) {
                if (!ALLOWABLE_MODIFIERS.contains(next)) {
                    throw new IllegalArgumentException(("unexpected typealias modifier " + next).toString());
                }
            }
            return new TypeAliasSpec(this, (TagMap) null, 2, (DefaultConstructorMarker) null);
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/squareup/kotlinpoet/TypeAliasSpec$Builder$Companion;", "", "()V", "ALLOWABLE_MODIFIERS", "", "Lcom/squareup/kotlinpoet/KModifier;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
        /* compiled from: TypeAliasSpec.kt */
        private static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\tH\u0007J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/squareup/kotlinpoet/TypeAliasSpec$Companion;", "", "()V", "builder", "Lcom/squareup/kotlinpoet/TypeAliasSpec$Builder;", "name", "", "type", "Lcom/squareup/kotlinpoet/TypeName;", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KClass;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: TypeAliasSpec.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Builder builder(String str, TypeName typeName) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(typeName, "type");
            return new Builder(str, typeName);
        }

        @JvmStatic
        public final Builder builder(String str, Type type) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            return builder(str, TypeNames.get(type));
        }

        @JvmStatic
        public final Builder builder(String str, KClass<?> kClass) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(kClass, "type");
            return builder(str, (TypeName) TypeNames.get(kClass));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0036, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0032, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
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
            com.squareup.kotlinpoet.CodeWriter r1 = (com.squareup.kotlinpoet.CodeWriter) r1     // Catch:{ all -> 0x0030 }
            r11.emit$kotlinpoet(r1)     // Catch:{ all -> 0x0030 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0030 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r10, r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "stringBuilder.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        L_0x0030:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r10, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.TypeAliasSpec.toString():java.lang.String");
    }
}
