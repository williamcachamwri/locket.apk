package com.squareup.kotlinpoet;

import androidx.webkit.ProxyConfig;
import com.squareup.kotlinpoet.TypeName;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016BW\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u0018\b\u0002\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\u0010\rJ6\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0016\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\f0\nH\u0016J\u0015\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0010¢\u0006\u0002\b\u0015R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u0017"}, d2 = {"Lcom/squareup/kotlinpoet/WildcardTypeName;", "Lcom/squareup/kotlinpoet/TypeName;", "outTypes", "", "inTypes", "nullable", "", "annotations", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "tags", "", "Lkotlin/reflect/KClass;", "", "(Ljava/util/List;Ljava/util/List;ZLjava/util/List;Ljava/util/Map;)V", "getInTypes", "()Ljava/util/List;", "getOutTypes", "copy", "emit", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: WildcardTypeName.kt */
public final class WildcardTypeName extends TypeName {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<TypeName> inTypes;
    private final List<TypeName> outTypes;

    @JvmStatic
    public static final WildcardTypeName consumerOf(TypeName typeName) {
        return Companion.consumerOf(typeName);
    }

    @JvmStatic
    public static final WildcardTypeName consumerOf(Type type) {
        return Companion.consumerOf(type);
    }

    @JvmStatic
    public static final WildcardTypeName consumerOf(KClass<?> kClass) {
        return Companion.consumerOf(kClass);
    }

    @JvmStatic
    public static final WildcardTypeName producerOf(TypeName typeName) {
        return Companion.producerOf(typeName);
    }

    @JvmStatic
    public static final WildcardTypeName producerOf(Type type) {
        return Companion.producerOf(type);
    }

    @JvmStatic
    public static final WildcardTypeName producerOf(KClass<?> kClass) {
        return Companion.producerOf(kClass);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ WildcardTypeName(List list, List list2, boolean z, List list3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? CollectionsKt.emptyList() : list3, (i & 16) != 0 ? MapsKt.emptyMap() : map);
    }

    private WildcardTypeName(List<? extends TypeName> list, List<? extends TypeName> list2, boolean z, List<AnnotationSpec> list3, Map<KClass<?>, ? extends Object> map) {
        super(z, list3, new TagMap(map), (DefaultConstructorMarker) null);
        List<TypeName> immutableList = UtilKt.toImmutableList(list);
        this.outTypes = immutableList;
        this.inTypes = UtilKt.toImmutableList(list2);
        if (!(immutableList.size() != 1 ? false : true)) {
            throw new IllegalArgumentException(("unexpected out types: " + list).toString());
        }
    }

    public final List<TypeName> getOutTypes() {
        return this.outTypes;
    }

    public final List<TypeName> getInTypes() {
        return this.inTypes;
    }

    public WildcardTypeName copy(boolean z, List<AnnotationSpec> list, Map<KClass<?>, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(list, "annotations");
        Intrinsics.checkNotNullParameter(map, "tags");
        return new WildcardTypeName(this.outTypes, this.inTypes, z, list, map);
    }

    public CodeWriter emit$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "out");
        if (this.inTypes.size() == 1) {
            return codeWriter.emitCode("in·%T", this.inTypes.get(0));
        }
        if (Intrinsics.areEqual((Object) this.outTypes, (Object) TypeNames.STAR.outTypes)) {
            return CodeWriter.emit$default(codeWriter, ProxyConfig.MATCH_ALL_SCHEMES, false, 2, (Object) null);
        }
        return codeWriter.emitCode("out·%T", this.outTypes.get(0));
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007H\u0007J\u0014\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\bH\u0007J)\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e0\rH\u0000¢\u0006\u0002\b\u000fJ)\u0010\t\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000e0\u0013H\u0000¢\u0006\u0002\b\u000fJ\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0006H\u0007J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0007H\u0007J\u0014\u0010\u0015\u001a\u00020\u00042\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\bH\u0007¨\u0006\u0017"}, d2 = {"Lcom/squareup/kotlinpoet/WildcardTypeName$Companion;", "", "()V", "consumerOf", "Lcom/squareup/kotlinpoet/WildcardTypeName;", "inType", "Lcom/squareup/kotlinpoet/TypeName;", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KClass;", "get", "wildcardName", "Ljava/lang/reflect/WildcardType;", "map", "", "Lcom/squareup/kotlinpoet/TypeVariableName;", "get$kotlinpoet", "mirror", "Ljavax/lang/model/type/WildcardType;", "typeVariables", "", "Ljavax/lang/model/element/TypeParameterElement;", "producerOf", "outType", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: WildcardTypeName.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final WildcardTypeName producerOf(TypeName typeName) {
            Intrinsics.checkNotNullParameter(typeName, "outType");
            return new WildcardTypeName(CollectionsKt.listOf(typeName), CollectionsKt.emptyList(), false, (List) null, (Map) null, 28, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final WildcardTypeName producerOf(Type type) {
            Intrinsics.checkNotNullParameter(type, "outType");
            return producerOf(TypeNames.get(type));
        }

        @JvmStatic
        public final WildcardTypeName producerOf(KClass<?> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "outType");
            return producerOf((TypeName) TypeNames.get(kClass));
        }

        @JvmStatic
        public final WildcardTypeName consumerOf(TypeName typeName) {
            Intrinsics.checkNotNullParameter(typeName, "inType");
            return new WildcardTypeName(CollectionsKt.listOf(TypeNames.ANY), CollectionsKt.listOf(typeName), false, (List) null, (Map) null, 28, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final WildcardTypeName consumerOf(Type type) {
            Intrinsics.checkNotNullParameter(type, "inType");
            return consumerOf(TypeNames.get(type));
        }

        @JvmStatic
        public final WildcardTypeName consumerOf(KClass<?> kClass) {
            Intrinsics.checkNotNullParameter(kClass, "inType");
            return consumerOf((TypeName) TypeNames.get(kClass));
        }

        public final TypeName get$kotlinpoet(WildcardType wildcardType, Map<TypeParameterElement, TypeVariableName> map) {
            WildcardTypeName wildcardTypeName;
            Intrinsics.checkNotNullParameter(wildcardType, "mirror");
            Intrinsics.checkNotNullParameter(map, "typeVariables");
            TypeMirror extendsBound = wildcardType.getExtendsBound();
            if (extendsBound != null) {
                return producerOf(TypeName.Companion.get$kotlinpoet(extendsBound, map));
            }
            TypeMirror superBound = wildcardType.getSuperBound();
            if (superBound == null) {
                wildcardTypeName = TypeNames.STAR;
            } else {
                wildcardTypeName = consumerOf(TypeName.Companion.get$kotlinpoet(superBound, map));
            }
            return wildcardTypeName;
        }

        public final TypeName get$kotlinpoet(java.lang.reflect.WildcardType wildcardType, Map<Type, TypeVariableName> map) {
            Intrinsics.checkNotNullParameter(wildcardType, "wildcardName");
            Intrinsics.checkNotNullParameter(map, "map");
            Type[] upperBounds = wildcardType.getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "wildcardName.upperBounds");
            Object[] objArr = (Object[]) upperBounds;
            Collection arrayList = new ArrayList(objArr.length);
            for (Object obj : objArr) {
                Type type = (Type) obj;
                TypeName.Companion companion = TypeName.Companion;
                Intrinsics.checkNotNullExpressionValue(type, "it");
                arrayList.add(companion.get$kotlinpoet(type, map));
            }
            List list = (List) arrayList;
            Type[] lowerBounds = wildcardType.getLowerBounds();
            Intrinsics.checkNotNullExpressionValue(lowerBounds, "wildcardName.lowerBounds");
            Object[] objArr2 = (Object[]) lowerBounds;
            Collection arrayList2 = new ArrayList(objArr2.length);
            for (Object obj2 : objArr2) {
                Type type2 = (Type) obj2;
                TypeName.Companion companion2 = TypeName.Companion;
                Intrinsics.checkNotNullExpressionValue(type2, "it");
                arrayList2.add(companion2.get$kotlinpoet(type2, map));
            }
            return new WildcardTypeName(list, (List) arrayList2, false, (List) null, (Map) null, 28, (DefaultConstructorMarker) null);
        }
    }
}
