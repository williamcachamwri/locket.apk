package com.squareup.kotlinpoet.ksp;

import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSReferenceElement;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeAlias;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.Variance;
import com.squareup.kotlinpoet.ClassName;
import com.squareup.kotlinpoet.KModifier;
import com.squareup.kotlinpoet.TypeName;
import com.squareup.kotlinpoet.TypeNames;
import com.squareup.kotlinpoet.TypeVariableName;
import com.squareup.kotlinpoet.WildcardTypeName;
import com.squareup.kotlinpoet.tags.TypeAliasTag;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\n\u0010\b\u001a\u00020\t*\u00020\u0007\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\r\u001a\"\u0010\n\u001a\u00020\u000b*\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0000\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\u000f2\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0014\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\b\b\u0002\u0010\f\u001a\u00020\r¨\u0006\u0013"}, d2 = {"mapTypeArgumentsFromTypeAliasToAbbreviatedType", "", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "typeAlias", "Lcom/google/devtools/ksp/symbol/KSTypeAlias;", "typeAliasTypeArguments", "abbreviatedType", "Lcom/google/devtools/ksp/symbol/KSType;", "toClassName", "Lcom/squareup/kotlinpoet/ClassName;", "toTypeName", "Lcom/squareup/kotlinpoet/TypeName;", "typeParamResolver", "Lcom/squareup/kotlinpoet/ksp/TypeParameterResolver;", "typeArguments", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "toTypeVariableName", "Lcom/squareup/kotlinpoet/TypeVariableName;", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "ksp"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: ksTypes.kt */
public final class KsTypesKt {

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* compiled from: ksTypes.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            iArr[Variance.COVARIANT.ordinal()] = 1;
            iArr[Variance.CONTRAVARIANT.ordinal()] = 2;
            iArr[Variance.STAR.ordinal()] = 3;
            iArr[Variance.INVARIANT.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final ClassName toClassName(KSType kSType) {
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        KSDeclaration declaration = kSType.getDeclaration();
        if (declaration instanceof KSClassDeclaration) {
            return KsClassDeclarationsKt.toClassName((KSClassDeclaration) declaration);
        }
        throw new IllegalStateException(("Declaration was not a KSClassDeclaration: " + kSType).toString());
    }

    public static /* synthetic */ TypeName toTypeName$default(KSType kSType, TypeParameterResolver typeParameterResolver, int i, Object obj) {
        if ((i & 1) != 0) {
            typeParameterResolver = TypeParameterResolver.Companion.getEMPTY();
        }
        return toTypeName(kSType, typeParameterResolver);
    }

    public static final TypeName toTypeName(KSType kSType, TypeParameterResolver typeParameterResolver) {
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        Intrinsics.checkNotNullParameter(typeParameterResolver, "typeParamResolver");
        return toTypeName(kSType, typeParameterResolver, CollectionsKt.emptyList());
    }

    public static final TypeName toTypeName(KSType kSType, TypeParameterResolver typeParameterResolver, List<? extends KSTypeArgument> list) {
        TypeName typeName;
        KSType resolve;
        TypeParameterResolver typeParameterResolver2;
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        Intrinsics.checkNotNullParameter(typeParameterResolver, "typeParamResolver");
        Intrinsics.checkNotNullParameter(list, "typeArguments");
        if (!kSType.isError()) {
            KSDeclaration declaration = kSType.getDeclaration();
            if (declaration instanceof KSClassDeclaration) {
                ClassName className = KsClassDeclarationsKt.toClassName((KSClassDeclaration) declaration);
                Iterable<KSTypeArgument> arguments = kSType.getArguments();
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
                for (KSTypeArgument typeName2 : arguments) {
                    arrayList.add(toTypeName(typeName2, typeParameterResolver));
                }
                typeName = UtilsKt.withTypeArguments(className, (List) arrayList);
            } else if (declaration instanceof KSTypeParameter) {
                typeName = typeParameterResolver.get(((KSTypeParameter) declaration).getName().getShortName());
            } else if (declaration instanceof KSTypeAlias) {
                KSTypeAlias kSTypeAlias = (KSTypeAlias) declaration;
                List<KSTypeArgument> arguments2 = kSType.getArguments();
                while (true) {
                    resolve = kSTypeAlias.getType().resolve();
                    arguments2 = mapTypeArgumentsFromTypeAliasToAbbreviatedType(kSTypeAlias, arguments2, resolve);
                    if (kSTypeAlias.getTypeParameters().isEmpty()) {
                        typeParameterResolver2 = typeParameterResolver;
                    } else {
                        typeParameterResolver2 = TypeParameterResolverKt.toTypeParameterResolver$default(kSTypeAlias.getTypeParameters(), typeParameterResolver, (String) null, 2, (Object) null);
                    }
                    KSDeclaration declaration2 = resolve.getDeclaration();
                    KSTypeAlias kSTypeAlias2 = declaration2 instanceof KSTypeAlias ? (KSTypeAlias) declaration2 : null;
                    if (kSTypeAlias2 == null) {
                        break;
                    }
                    kSTypeAlias = kSTypeAlias2;
                }
                ClassName rawType = UtilsKt.rawType(TypeName.copy$default(toTypeName(resolve, typeParameterResolver2), kSType.isMarkedNullable(), (List) null, 2, (Object) null));
                Iterable<KSTypeArgument> iterable = arguments2;
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (KSTypeArgument typeName3 : iterable) {
                    arrayList2.add(toTypeName(typeName3, typeParameterResolver));
                }
                TypeName withTypeArguments = UtilsKt.withTypeArguments(rawType, (List) arrayList2);
                Iterable<KSTypeArgument> iterable2 = list;
                Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                for (KSTypeArgument typeName4 : iterable2) {
                    arrayList3.add(toTypeName(typeName4, typeParameterResolver));
                }
                typeName = TypeName.copy$default(UtilsKt.withTypeArguments(UtilsKt.toClassNameInternal(declaration), (List) arrayList3), false, (List) null, MapsKt.mapOf(TuplesKt.to(Reflection.getOrCreateKotlinClass(TypeAliasTag.class), new TypeAliasTag(withTypeArguments))), 3, (Object) null);
            } else {
                throw new IllegalStateException(("Unsupported type: " + kSType.getDeclaration()).toString());
            }
            return TypeName.copy$default(typeName, kSType.isMarkedNullable(), (List) null, 2, (Object) null);
        }
        throw new IllegalArgumentException(("Error type '" + kSType + "' is not resolvable in the current round of processing.").toString());
    }

    private static final List<KSTypeArgument> mapTypeArgumentsFromTypeAliasToAbbreviatedType(KSTypeAlias kSTypeAlias, List<? extends KSTypeArgument> list, KSType kSType) {
        Iterable<KSTypeArgument> arguments = kSType.getArguments();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        for (KSTypeArgument kSTypeArgument : arguments) {
            Iterator<KSTypeParameter> it = kSTypeAlias.getTypeParameters().iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                } else if (Intrinsics.areEqual((Object) it.next().getName().asString(), (Object) String.valueOf(kSTypeArgument.getType()))) {
                    break;
                } else {
                    i++;
                }
            }
            if (i >= 0) {
                kSTypeArgument = (KSTypeArgument) list.get(i);
            }
            arrayList.add(kSTypeArgument);
        }
        return (List) arrayList;
    }

    public static /* synthetic */ TypeVariableName toTypeVariableName$default(KSTypeParameter kSTypeParameter, TypeParameterResolver typeParameterResolver, int i, Object obj) {
        if ((i & 1) != 0) {
            typeParameterResolver = TypeParameterResolver.Companion.getEMPTY();
        }
        return toTypeVariableName(kSTypeParameter, typeParameterResolver);
    }

    public static final TypeVariableName toTypeVariableName(KSTypeParameter kSTypeParameter, TypeParameterResolver typeParameterResolver) {
        KModifier kModifier;
        Intrinsics.checkNotNullParameter(kSTypeParameter, "<this>");
        Intrinsics.checkNotNullParameter(typeParameterResolver, "typeParamResolver");
        String shortName = kSTypeParameter.getName().getShortName();
        List<R> list = SequencesKt.toList(SequencesKt.map(kSTypeParameter.getBounds(), new KsTypesKt$toTypeVariableName$typeVarBounds$1(typeParameterResolver)));
        int i = WhenMappings.$EnumSwitchMapping$0[kSTypeParameter.getVariance().ordinal()];
        if (i == 1) {
            kModifier = KModifier.OUT;
        } else if (i != 2) {
            kModifier = null;
        } else {
            kModifier = KModifier.IN;
        }
        return TypeVariableName.Companion.get(shortName, (List<? extends TypeName>) list, kModifier);
    }

    public static /* synthetic */ TypeName toTypeName$default(KSTypeArgument kSTypeArgument, TypeParameterResolver typeParameterResolver, int i, Object obj) {
        if ((i & 1) != 0) {
            typeParameterResolver = TypeParameterResolver.Companion.getEMPTY();
        }
        return toTypeName(kSTypeArgument, typeParameterResolver);
    }

    public static final TypeName toTypeName(KSTypeArgument kSTypeArgument, TypeParameterResolver typeParameterResolver) {
        TypeName typeName;
        Intrinsics.checkNotNullParameter(kSTypeArgument, "<this>");
        Intrinsics.checkNotNullParameter(typeParameterResolver, "typeParamResolver");
        KSTypeReference type = kSTypeArgument.getType();
        if (type == null || (typeName = toTypeName(type, typeParameterResolver)) == null) {
            return TypeNames.STAR;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[kSTypeArgument.getVariance().ordinal()];
        if (i == 1) {
            return WildcardTypeName.Companion.producerOf(typeName);
        }
        if (i == 2) {
            return WildcardTypeName.Companion.consumerOf(typeName);
        }
        if (i == 3) {
            return TypeNames.STAR;
        }
        if (i == 4) {
            return typeName;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static /* synthetic */ TypeName toTypeName$default(KSTypeReference kSTypeReference, TypeParameterResolver typeParameterResolver, int i, Object obj) {
        if ((i & 1) != 0) {
            typeParameterResolver = TypeParameterResolver.Companion.getEMPTY();
        }
        return toTypeName(kSTypeReference, typeParameterResolver);
    }

    public static final TypeName toTypeName(KSTypeReference kSTypeReference, TypeParameterResolver typeParameterResolver) {
        Intrinsics.checkNotNullParameter(kSTypeReference, "<this>");
        Intrinsics.checkNotNullParameter(typeParameterResolver, "typeParamResolver");
        KSType resolve = kSTypeReference.resolve();
        KSReferenceElement element = kSTypeReference.getElement();
        List<KSTypeArgument> typeArguments = element != null ? element.getTypeArguments() : null;
        if (typeArguments == null) {
            typeArguments = CollectionsKt.emptyList();
        }
        return toTypeName(resolve, typeParameterResolver, typeArguments);
    }
}
