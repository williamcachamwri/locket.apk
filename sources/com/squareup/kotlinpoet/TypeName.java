package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.ParameterizedTypeName;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 22\u00020\u0001:\u00012B%\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ \u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u001d\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J<\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u001d\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0018\b\u0002\u0010\u0016\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0018\u0012\u0004\u0012\u00020\u00190\u0017H&J\u0015\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH ¢\u0006\u0002\b!J\u0015\u0010\"\u001a\u00020#2\u0006\u0010 \u001a\u00020\u001fH\u0000¢\u0006\u0002\b$J\u0015\u0010%\u001a\u00020#2\u0006\u0010 \u001a\u00020\u001fH\u0000¢\u0006\u0002\b&J\u0013\u0010'\u001a\u00020\u00032\b\u0010(\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010)\u001a\u00020*H\u0016J(\u0010+\u001a\u0004\u0018\u0001H,\"\b\b\u0000\u0010,*\u00020\u00192\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H,0.H\u0001¢\u0006\u0002\u0010/J(\u0010+\u001a\u0004\u0018\u0001H,\"\b\b\u0000\u0010,*\u00020\u00192\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H,0\u0018H\u0001¢\u0006\u0002\u00100J\b\u00101\u001a\u00020\rH\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0013R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0018\u0012\u0004\u0012\u00020\u00190\u00178VX\u0005¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\u0001\u0006345678¨\u00069"}, d2 = {"Lcom/squareup/kotlinpoet/TypeName;", "Lcom/squareup/kotlinpoet/Taggable;", "isNullable", "", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "tagMap", "Lcom/squareup/kotlinpoet/TagMap;", "(ZLjava/util/List;Lcom/squareup/kotlinpoet/TagMap;)V", "getAnnotations", "()Ljava/util/List;", "cachedString", "", "getCachedString", "()Ljava/lang/String;", "cachedString$delegate", "Lkotlin/Lazy;", "isAnnotated", "()Z", "getTagMap$kotlinpoet", "()Lcom/squareup/kotlinpoet/TagMap;", "tags", "", "Lkotlin/reflect/KClass;", "", "getTags", "()Ljava/util/Map;", "copy", "nullable", "emit", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "emitAnnotations", "", "emitAnnotations$kotlinpoet", "emitNullable", "emitNullable$kotlinpoet", "equals", "other", "hashCode", "", "tag", "T", "type", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toString", "Companion", "Lcom/squareup/kotlinpoet/ClassName;", "Lcom/squareup/kotlinpoet/Dynamic;", "Lcom/squareup/kotlinpoet/LambdaTypeName;", "Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "Lcom/squareup/kotlinpoet/TypeVariableName;", "Lcom/squareup/kotlinpoet/WildcardTypeName;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeName.kt */
public abstract class TypeName implements Taggable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<AnnotationSpec> annotations;
    private final Lazy cachedString$delegate;
    private final boolean isNullable;
    private final TagMap tagMap;

    public /* synthetic */ TypeName(boolean z, List list, TagMap tagMap2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, list, tagMap2);
    }

    public abstract TypeName copy(boolean z, List<AnnotationSpec> list, Map<KClass<?>, ? extends Object> map);

    public abstract CodeWriter emit$kotlinpoet(CodeWriter codeWriter);

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

    private TypeName(boolean z, List<AnnotationSpec> list, TagMap tagMap2) {
        this.isNullable = z;
        this.tagMap = tagMap2;
        this.annotations = UtilKt.toImmutableList(list);
        this.cachedString$delegate = LazyKt.lazy(new TypeName$cachedString$2(this));
    }

    public final boolean isNullable() {
        return this.isNullable;
    }

    public final TagMap getTagMap$kotlinpoet() {
        return this.tagMap;
    }

    public final List<AnnotationSpec> getAnnotations() {
        return this.annotations;
    }

    private final String getCachedString() {
        return (String) this.cachedString$delegate.getValue();
    }

    public static /* synthetic */ TypeName copy$default(TypeName typeName, boolean z, List list, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = typeName.isNullable;
            }
            if ((i & 2) != 0) {
                list = CollectionsKt.toList(typeName.annotations);
            }
            return typeName.copy(z, list);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copy");
    }

    public final TypeName copy(boolean z, List<AnnotationSpec> list) {
        Intrinsics.checkNotNullParameter(list, "annotations");
        return copy(z, list, getTags());
    }

    public static /* synthetic */ TypeName copy$default(TypeName typeName, boolean z, List list, Map<KClass<?>, Object> map, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = typeName.isNullable;
            }
            if ((i & 2) != 0) {
                list = CollectionsKt.toList(typeName.annotations);
            }
            if ((i & 4) != 0) {
                map = typeName.getTags();
            }
            return typeName.copy(z, list, map);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copy");
    }

    public final boolean isAnnotated() {
        return !this.annotations.isEmpty();
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

    public String toString() {
        return getCachedString();
    }

    public final void emitAnnotations$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "out");
        for (AnnotationSpec emit$kotlinpoet$default : this.annotations) {
            AnnotationSpec.emit$kotlinpoet$default(emit$kotlinpoet$default, codeWriter, true, false, 4, (Object) null);
            CodeWriter.emit$default(codeWriter, " ", false, 2, (Object) null);
        }
    }

    public final void emitNullable$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "out");
        if (this.isNullable) {
            CodeWriter.emit$default(codeWriter, "?", false, 2, (Object) null);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bH\u0000¢\u0006\u0002\b\nJ)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t0\u000eH\u0000¢\u0006\u0002\b\n¨\u0006\u0010"}, d2 = {"Lcom/squareup/kotlinpoet/TypeName$Companion;", "", "()V", "get", "Lcom/squareup/kotlinpoet/TypeName;", "type", "Ljava/lang/reflect/Type;", "map", "", "Lcom/squareup/kotlinpoet/TypeVariableName;", "get$kotlinpoet", "mirror", "Ljavax/lang/model/type/TypeMirror;", "typeVariables", "", "Ljavax/lang/model/element/TypeParameterElement;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: TypeName.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TypeName get$kotlinpoet(TypeMirror typeMirror, Map<TypeParameterElement, TypeVariableName> map) {
            Intrinsics.checkNotNullParameter(typeMirror, "mirror");
            Intrinsics.checkNotNullParameter(map, "typeVariables");
            Object accept = typeMirror.accept(new TypeName$Companion$get$1(map), (Object) null);
            Intrinsics.checkNotNullExpressionValue(accept, "typeVariables: Map<TypeP…  },\n        null\n      )");
            return (TypeName) accept;
        }

        public final TypeName get$kotlinpoet(Type type, Map<Type, TypeVariableName> map) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(map, "map");
            if (type instanceof Class) {
                if (type == Void.TYPE) {
                    return TypeNames.UNIT;
                }
                if (type == Boolean.TYPE) {
                    return TypeNames.BOOLEAN;
                }
                if (type == Byte.TYPE) {
                    return TypeNames.BYTE;
                }
                if (type == Short.TYPE) {
                    return TypeNames.SHORT;
                }
                if (type == Integer.TYPE) {
                    return TypeNames.INT;
                }
                if (type == Long.TYPE) {
                    return TypeNames.LONG;
                }
                if (type == Character.TYPE) {
                    return TypeNames.CHAR;
                }
                if (type == Float.TYPE) {
                    return TypeNames.FLOAT;
                }
                if (type == Double.TYPE) {
                    return TypeNames.DOUBLE;
                }
                Class cls = (Class) type;
                if (!cls.isArray()) {
                    return ClassNames.get((Class<?>) cls);
                }
                ParameterizedTypeName.Companion companion = ParameterizedTypeName.Companion;
                ClassName className = TypeNames.ARRAY;
                Class<?> componentType = cls.getComponentType();
                Intrinsics.checkNotNullExpressionValue(componentType, "type.componentType");
                return companion.get(className, get$kotlinpoet((Type) componentType, map));
            } else if (type instanceof ParameterizedType) {
                return ParameterizedTypeName.Companion.get$kotlinpoet((ParameterizedType) type, map);
            } else {
                if (type instanceof WildcardType) {
                    return WildcardTypeName.Companion.get$kotlinpoet((WildcardType) type, map);
                }
                if (type instanceof TypeVariable) {
                    return TypeVariableName.Companion.get$kotlinpoet((TypeVariable<?>) (TypeVariable) type, map);
                }
                if (type instanceof GenericArrayType) {
                    ParameterizedTypeName.Companion companion2 = ParameterizedTypeName.Companion;
                    ClassName className2 = TypeNames.ARRAY;
                    Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
                    Intrinsics.checkNotNullExpressionValue(genericComponentType, "type.genericComponentType");
                    return companion2.get(className2, get$kotlinpoet(genericComponentType, map));
                }
                throw new IllegalArgumentException("unexpected type: " + type);
            }
        }
    }
}
