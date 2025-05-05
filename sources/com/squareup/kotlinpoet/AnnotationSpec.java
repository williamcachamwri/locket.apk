package com.squareup.kotlinpoet;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.Taggable;
import expo.modules.notifications.service.NotificationsService;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 62\u00020\u0001:\u00045678B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J'\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020%H\u0000¢\u0006\u0002\b'J\u0013\u0010(\u001a\u00020%2\b\u0010)\u001a\u0004\u0018\u00010\u0015H\u0002J\b\u0010*\u001a\u00020+H\u0016J(\u0010,\u001a\u0004\u0018\u0001H-\"\b\b\u0000\u0010-*\u00020\u00152\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H-0/H\u0001¢\u0006\u0002\u00100J(\u0010,\u001a\u0004\u0018\u0001H-\"\b\b\u0000\u0010-*\u00020\u00152\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H-0\u0014H\u0001¢\u0006\u0002\u00101J\u0006\u00102\u001a\u00020\u0003J\b\u00103\u001a\u000204H\u0016R\u001a\u0010\u0007\u001a\u00020\b8FX\u0004¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R$\u0010\u0012\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020\u00150\u00138VX\u0005¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00069"}, d2 = {"Lcom/squareup/kotlinpoet/AnnotationSpec;", "Lcom/squareup/kotlinpoet/Taggable;", "builder", "Lcom/squareup/kotlinpoet/AnnotationSpec$Builder;", "tagMap", "Lcom/squareup/kotlinpoet/TagMap;", "(Lcom/squareup/kotlinpoet/AnnotationSpec$Builder;Lcom/squareup/kotlinpoet/TagMap;)V", "className", "Lcom/squareup/kotlinpoet/ClassName;", "getClassName$annotations", "()V", "getClassName", "()Lcom/squareup/kotlinpoet/ClassName;", "members", "", "Lcom/squareup/kotlinpoet/CodeBlock;", "getMembers", "()Ljava/util/List;", "tags", "", "Lkotlin/reflect/KClass;", "", "getTags", "()Ljava/util/Map;", "typeName", "Lcom/squareup/kotlinpoet/TypeName;", "getTypeName", "()Lcom/squareup/kotlinpoet/TypeName;", "useSiteTarget", "Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;", "getUseSiteTarget", "()Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;", "emit", "", "codeWriter", "Lcom/squareup/kotlinpoet/CodeWriter;", "inline", "", "asParameter", "emit$kotlinpoet", "equals", "other", "hashCode", "", "tag", "T", "type", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toBuilder", "toString", "", "Builder", "Companion", "UseSiteTarget", "Visitor", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: AnnotationSpec.kt */
public final class AnnotationSpec implements Taggable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<CodeBlock> members;
    private final TagMap tagMap;
    private final TypeName typeName;
    private final UseSiteTarget useSiteTarget;

    @JvmStatic
    public static final Builder builder(ClassName className) {
        return Companion.builder(className);
    }

    @JvmStatic
    public static final Builder builder(ParameterizedTypeName parameterizedTypeName) {
        return Companion.builder(parameterizedTypeName);
    }

    @JvmStatic
    public static final Builder builder(Class<? extends Annotation> cls) {
        return Companion.builder(cls);
    }

    @JvmStatic
    public static final Builder builder(KClass<? extends Annotation> kClass) {
        return Companion.builder(kClass);
    }

    @JvmStatic
    public static final AnnotationSpec get(Annotation annotation) {
        return Companion.get(annotation);
    }

    @JvmStatic
    public static final AnnotationSpec get(Annotation annotation, boolean z) {
        return Companion.get(annotation, z);
    }

    @JvmStatic
    public static final AnnotationSpec get(AnnotationMirror annotationMirror) {
        return Companion.get(annotationMirror);
    }

    @Deprecated(message = "Use typeName instead. This property will be removed in KotlinPoet 2.0.", replaceWith = @ReplaceWith(expression = "typeName", imports = {}))
    public static /* synthetic */ void getClassName$annotations() {
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

    private AnnotationSpec(Builder builder, TagMap tagMap2) {
        this.tagMap = tagMap2;
        this.typeName = builder.getTypeName$kotlinpoet();
        this.members = UtilKt.toImmutableList(builder.getMembers());
        this.useSiteTarget = builder.getUseSiteTarget$kotlinpoet();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ AnnotationSpec(Builder builder, TagMap tagMap2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder, (i & 2) != 0 ? TaggableKt.buildTagMap(builder) : tagMap2);
    }

    public final ClassName getClassName() {
        TypeName typeName2 = this.typeName;
        ClassName className = typeName2 instanceof ClassName ? (ClassName) typeName2 : null;
        if (className != null) {
            return className;
        }
        throw new IllegalStateException("ClassName is not available. Call typeName instead.".toString());
    }

    public final TypeName getTypeName() {
        return this.typeName;
    }

    public final List<CodeBlock> getMembers() {
        return this.members;
    }

    public final UseSiteTarget getUseSiteTarget() {
        return this.useSiteTarget;
    }

    public static /* synthetic */ void emit$kotlinpoet$default(AnnotationSpec annotationSpec, CodeWriter codeWriter, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        annotationSpec.emit$kotlinpoet(codeWriter, z, z2);
    }

    public final void emit$kotlinpoet(CodeWriter codeWriter, boolean z, boolean z2) {
        String str;
        CodeWriter codeWriter2 = codeWriter;
        Intrinsics.checkNotNullParameter(codeWriter2, "codeWriter");
        if (!z2) {
            CodeWriter.emit$default(codeWriter2, "@", false, 2, (Object) null);
        }
        if (this.useSiteTarget != null) {
            CodeWriter.emit$default(codeWriter2, this.useSiteTarget.getKeyword$kotlinpoet() + AbstractJsonLexerKt.COLON, false, 2, (Object) null);
        }
        codeWriter2.emitCode("%T", this.typeName);
        if (!this.members.isEmpty() || z2) {
            String str2 = z ? "" : "\n";
            String str3 = z ? ", " : ",\n";
            if (z || this.members.size() <= 1) {
                str = "";
            } else {
                str = ",";
            }
            CodeWriter.emit$default(codeWriter2, "(", false, 2, (Object) null);
            if (this.members.size() > 1) {
                CodeWriter.emit$default(codeWriter2, str2, false, 2, (Object) null).indent(1);
            }
            Iterable<CodeBlock> iterable = this.members;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (CodeBlock codeBlock : iterable) {
                if (z) {
                    codeBlock = codeBlock.replaceAll$kotlinpoet("[⇥|⇤]", "");
                }
                arrayList.add(codeBlock);
            }
            CodeWriter.emitCode$default(codeWriter, CodeBlocks.joinToCode$default((List) arrayList, str3, (CharSequence) null, str, 2, (Object) null), true, false, 4, (Object) null);
            if (this.members.size() > 1) {
                CodeWriter.emit$default(codeWriter2.unindent(1), str2, false, 2, (Object) null);
            }
            CodeWriter.emit$default(codeWriter2, ")", false, 2, (Object) null);
        }
    }

    public final Builder toBuilder() {
        Builder builder = new Builder(this.typeName);
        CollectionsKt.addAll(builder.getMembers(), this.members);
        builder.setUseSiteTarget$kotlinpoet(this.useSiteTarget);
        builder.getTags().putAll(this.tagMap.getTags());
        return builder;
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

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;", "", "keyword", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKeyword$kotlinpoet", "()Ljava/lang/String;", "FILE", "PROPERTY", "FIELD", "GET", "SET", "RECEIVER", "PARAM", "SETPARAM", "DELEGATE", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: AnnotationSpec.kt */
    public enum UseSiteTarget {
        FILE("file"),
        PROPERTY("property"),
        FIELD("field"),
        GET("get"),
        SET("set"),
        RECEIVER(NotificationsService.RECEIVER_KEY),
        PARAM("param"),
        SETPARAM("setparam"),
        DELEGATE("delegate");
        
        private final String keyword;

        private UseSiteTarget(String str) {
            this.keyword = str;
        }

        public final String getKeyword$kotlinpoet() {
            return this.keyword;
        }
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 !2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001!B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0007J'\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u001d\"\u00020\r¢\u0006\u0002\u0010\u001eJ\u0006\u0010\u001f\u001a\u00020 J\u0010\u0010\u0012\u001a\u00020\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR$\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u0004\u0012\u00020\r0\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\""}, d2 = {"Lcom/squareup/kotlinpoet/AnnotationSpec$Builder;", "Lcom/squareup/kotlinpoet/Taggable$Builder;", "typeName", "Lcom/squareup/kotlinpoet/TypeName;", "(Lcom/squareup/kotlinpoet/TypeName;)V", "members", "", "Lcom/squareup/kotlinpoet/CodeBlock;", "getMembers", "()Ljava/util/List;", "tags", "", "Lkotlin/reflect/KClass;", "", "getTags", "()Ljava/util/Map;", "getTypeName$kotlinpoet", "()Lcom/squareup/kotlinpoet/TypeName;", "useSiteTarget", "Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;", "getUseSiteTarget$kotlinpoet", "()Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;", "setUseSiteTarget$kotlinpoet", "(Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;)V", "addMember", "codeBlock", "format", "", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)Lcom/squareup/kotlinpoet/AnnotationSpec$Builder;", "build", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: AnnotationSpec.kt */
    public static final class Builder implements Taggable.Builder<Builder> {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final List<CodeBlock> members = new ArrayList();
        private final Map<KClass<?>, Object> tags = new LinkedHashMap();
        private final TypeName typeName;
        private UseSiteTarget useSiteTarget;

        public Builder(TypeName typeName2) {
            Intrinsics.checkNotNullParameter(typeName2, "typeName");
            this.typeName = typeName2;
        }

        public Builder tag(Class<?> cls, Object obj) {
            return (Builder) Taggable.Builder.DefaultImpls.tag(this, cls, obj);
        }

        public Builder tag(KClass<?> kClass, Object obj) {
            return (Builder) Taggable.Builder.DefaultImpls.tag(this, kClass, obj);
        }

        public final TypeName getTypeName$kotlinpoet() {
            return this.typeName;
        }

        public final UseSiteTarget getUseSiteTarget$kotlinpoet() {
            return this.useSiteTarget;
        }

        public final void setUseSiteTarget$kotlinpoet(UseSiteTarget useSiteTarget2) {
            this.useSiteTarget = useSiteTarget2;
        }

        public final List<CodeBlock> getMembers() {
            return this.members;
        }

        public Map<KClass<?>, Object> getTags() {
            return this.tags;
        }

        public final Builder addMember(String str, Object... objArr) {
            Intrinsics.checkNotNullParameter(str, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            return addMember(CodeBlock.Companion.of(str, Arrays.copyOf(objArr, objArr.length)));
        }

        public final Builder addMember(CodeBlock codeBlock) {
            Intrinsics.checkNotNullParameter(codeBlock, "codeBlock");
            Builder builder = this;
            this.members.add(codeBlock);
            return this;
        }

        public final Builder useSiteTarget(UseSiteTarget useSiteTarget2) {
            Builder builder = this;
            this.useSiteTarget = useSiteTarget2;
            return this;
        }

        public final AnnotationSpec build() {
            return new AnnotationSpec(this, (TagMap) null, 2, (DefaultConstructorMarker) null);
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/squareup/kotlinpoet/AnnotationSpec$Builder$Companion;", "", "()V", "memberForValue", "Lcom/squareup/kotlinpoet/CodeBlock;", "value", "memberForValue$kotlinpoet", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
        /* compiled from: AnnotationSpec.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final CodeBlock memberForValue$kotlinpoet(Object obj) {
                Intrinsics.checkNotNullParameter(obj, "value");
                if (obj instanceof Class) {
                    return CodeBlock.Companion.of("%T::class", obj);
                }
                if (obj instanceof Enum) {
                    return CodeBlock.Companion.of("%T.%L", obj.getClass(), ((Enum) obj).name());
                }
                if (obj instanceof String) {
                    return CodeBlock.Companion.of("%S", obj);
                }
                if (obj instanceof Float) {
                    return CodeBlock.Companion.of("%Lf", obj);
                }
                if (obj instanceof Character) {
                    return CodeBlock.Companion.of("'%L'", UtilKt.characterLiteralWithoutSingleQuotes(((Character) obj).charValue()));
                }
                return CodeBlock.Companion.of("%L", obj);
            }
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\u0018\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0014J\u0018\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\u001e\u0010\u000f\u001a\u00020\u00022\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\u0018\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcom/squareup/kotlinpoet/AnnotationSpec$Visitor;", "Ljavax/lang/model/util/SimpleAnnotationValueVisitor7;", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "", "builder", "(Lcom/squareup/kotlinpoet/CodeBlock$Builder;)V", "getBuilder", "()Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "defaultAction", "o", "", "name", "visitAnnotation", "a", "Ljavax/lang/model/element/AnnotationMirror;", "visitArray", "values", "", "Ljavax/lang/model/element/AnnotationValue;", "visitEnumConstant", "c", "Ljavax/lang/model/element/VariableElement;", "visitType", "t", "Ljavax/lang/model/type/TypeMirror;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: AnnotationSpec.kt */
    private static final class Visitor extends SimpleAnnotationValueVisitor7<CodeBlock.Builder, String> {
        private final CodeBlock.Builder builder;

        public final CodeBlock.Builder getBuilder() {
            return this.builder;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Visitor(CodeBlock.Builder builder2) {
            super(builder2);
            Intrinsics.checkNotNullParameter(builder2, "builder");
            this.builder = builder2;
        }

        /* access modifiers changed from: protected */
        public CodeBlock.Builder defaultAction(Object obj, String str) {
            Intrinsics.checkNotNullParameter(obj, "o");
            Intrinsics.checkNotNullParameter(str, "name");
            return this.builder.add(Builder.Companion.memberForValue$kotlinpoet(obj));
        }

        public CodeBlock.Builder visitAnnotation(AnnotationMirror annotationMirror, String str) {
            Intrinsics.checkNotNullParameter(annotationMirror, CmcdData.Factory.OBJECT_TYPE_AUDIO_ONLY);
            Intrinsics.checkNotNullParameter(str, "name");
            return this.builder.add("%L", AnnotationSpec.Companion.get(annotationMirror));
        }

        public CodeBlock.Builder visitEnumConstant(VariableElement variableElement, String str) {
            Intrinsics.checkNotNullParameter(variableElement, "c");
            Intrinsics.checkNotNullParameter(str, "name");
            CodeBlock.Builder builder2 = this.builder;
            TypeMirror asType = variableElement.asType();
            Intrinsics.checkNotNullExpressionValue(asType, "c.asType()");
            return builder2.add("%T.%L", TypeNames.get(asType), variableElement.getSimpleName());
        }

        public CodeBlock.Builder visitType(TypeMirror typeMirror, String str) {
            Intrinsics.checkNotNullParameter(typeMirror, "t");
            Intrinsics.checkNotNullParameter(str, "name");
            return this.builder.add("%T::class", TypeNames.get(typeMirror));
        }

        public CodeBlock.Builder visitArray(List<? extends AnnotationValue> list, String str) {
            Intrinsics.checkNotNullParameter(list, "values");
            Intrinsics.checkNotNullParameter(str, "name");
            this.builder.add("arrayOf(⇥⇥", new Object[0]);
            int i = 0;
            for (Object next : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                AnnotationValue annotationValue = (AnnotationValue) next;
                if (i > 0) {
                    this.builder.add(", ", new Object[0]);
                }
                annotationValue.accept((AnnotationValueVisitor) this, str);
                i = i2;
            }
            this.builder.add("⇤⇤)", new Object[0]);
            return this.builder;
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007H\u0007J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\bH\u0007J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¨\u0006\u0011"}, d2 = {"Lcom/squareup/kotlinpoet/AnnotationSpec$Companion;", "", "()V", "builder", "Lcom/squareup/kotlinpoet/AnnotationSpec$Builder;", "type", "Lcom/squareup/kotlinpoet/ClassName;", "Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "Ljava/lang/Class;", "", "Lkotlin/reflect/KClass;", "get", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "annotation", "Ljavax/lang/model/element/AnnotationMirror;", "includeDefaultValues", "", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: AnnotationSpec.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final AnnotationSpec get(Annotation annotation) {
            Intrinsics.checkNotNullParameter(annotation, "annotation");
            return get$default(this, annotation, false, 2, (Object) null);
        }

        private Companion() {
        }

        public static /* synthetic */ AnnotationSpec get$default(Companion companion, Annotation annotation, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.get(annotation, z);
        }

        @JvmStatic
        public final AnnotationSpec get(Annotation annotation, boolean z) {
            Intrinsics.checkNotNullParameter(annotation, "annotation");
            try {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                Intrinsics.checkNotNullExpressionValue(annotationType, "javaAnnotation.annotationType()");
                Builder builder = (Builder) builder(annotationType).tag((KClass<?>) Reflection.getOrCreateKotlinClass(Object.class), (Object) annotation);
                Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
                Intrinsics.checkNotNullExpressionValue(declaredMethods, "annotation.annotationType().declaredMethods");
                for (Method method : ArraysKt.sortedWith((T[]) (Object[]) declaredMethods, new AnnotationSpec$Companion$get$$inlined$sortedBy$1())) {
                    Object invoke = method.invoke(annotation, new Object[0]);
                    if (z || !Objects.deepEquals(invoke, method.getDefaultValue())) {
                        CodeBlock.Builder builder2 = CodeBlock.Companion.builder();
                        builder2.add("%L = ", method.getName());
                        if (invoke.getClass().isArray()) {
                            builder2.add("arrayOf(⇥⇥", new Object[0]);
                            int length = Array.getLength(invoke);
                            for (int i = 0; i < length; i++) {
                                if (i > 0) {
                                    builder2.add(", ", new Object[0]);
                                }
                                Builder.Companion companion = Builder.Companion;
                                Object obj = Array.get(invoke, i);
                                Intrinsics.checkNotNullExpressionValue(obj, "get(value, i)");
                                builder2.add(companion.memberForValue$kotlinpoet(obj));
                            }
                            builder2.add("⇤⇤)", new Object[0]);
                            builder.addMember(builder2.build());
                        } else if (invoke instanceof Annotation) {
                            builder2.add("%L", get$default(this, (Annotation) invoke, false, 2, (Object) null));
                            builder.addMember(builder2.build());
                        } else {
                            Builder.Companion companion2 = Builder.Companion;
                            Intrinsics.checkNotNullExpressionValue(invoke, "value");
                            builder2.add("%L", companion2.memberForValue$kotlinpoet(invoke));
                            builder.addMember(builder2.build());
                        }
                    }
                }
                return builder.build();
            } catch (Exception e) {
                throw new RuntimeException("Reflecting " + annotation + " failed!", e);
            }
        }

        @JvmStatic
        public final AnnotationSpec get(AnnotationMirror annotationMirror) {
            Intrinsics.checkNotNullParameter(annotationMirror, "annotation");
            TypeElement asElement = annotationMirror.getAnnotationType().asElement();
            Intrinsics.checkNotNull(asElement, "null cannot be cast to non-null type javax.lang.model.element.TypeElement");
            Builder builder = (Builder) builder(ClassNames.get(asElement)).tag((KClass<?>) Reflection.getOrCreateKotlinClass(AnnotationMirror.class), (Object) annotationMirror);
            for (ExecutableElement executableElement : annotationMirror.getElementValues().keySet()) {
                CodeBlock.Builder builder2 = CodeBlock.Companion.builder();
                AnnotationValueVisitor visitor = new Visitor(builder2);
                String obj = executableElement.getSimpleName().toString();
                builder2.add("%L = ", obj);
                Map elementValues = annotationMirror.getElementValues();
                Intrinsics.checkNotNullExpressionValue(elementValues, "annotation.elementValues");
                Object obj2 = elementValues.get(executableElement);
                Intrinsics.checkNotNull(obj2);
                ((AnnotationValue) obj2).accept(visitor, obj);
                builder.addMember(builder2.build());
            }
            return builder.build();
        }

        @JvmStatic
        public final Builder builder(ClassName className) {
            Intrinsics.checkNotNullParameter(className, "type");
            return new Builder(className);
        }

        @JvmStatic
        public final Builder builder(ParameterizedTypeName parameterizedTypeName) {
            Intrinsics.checkNotNullParameter(parameterizedTypeName, "type");
            return new Builder(parameterizedTypeName);
        }

        @JvmStatic
        public final Builder builder(Class<? extends Annotation> cls) {
            Intrinsics.checkNotNullParameter(cls, "type");
            return builder(ClassNames.get((Class<?>) cls));
        }

        @JvmStatic
        public final Builder builder(KClass<? extends Annotation> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "type");
            return builder(ClassNames.get((KClass<?>) kClass));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0038, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0034, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
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
            com.squareup.kotlinpoet.CodeWriter r1 = (com.squareup.kotlinpoet.CodeWriter) r1     // Catch:{ all -> 0x0032 }
            r2 = 1
            r3 = 0
            r11.emit$kotlinpoet(r1, r2, r3)     // Catch:{ all -> 0x0032 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0032 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r10, r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "stringBuilder.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        L_0x0032:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r10, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.AnnotationSpec.toString():java.lang.String");
    }
}
