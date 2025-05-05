package com.squareup.kotlinpoet.ksp;

import com.google.devtools.ksp.symbol.AnnotationUseSiteTarget;
import com.google.devtools.ksp.symbol.ClassKind;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSName;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeAlias;
import com.google.devtools.ksp.symbol.KSValueArgument;
import com.squareup.kotlinpoet.AnnotationSpec;
import com.squareup.kotlinpoet.ClassName;
import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.ParameterizedTypeName;
import com.squareup.kotlinpoet.TypeName;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\n\u0010\r\u001a\u00020\u000e*\u00020\u000f\u001a\f\u0010\u0010\u001a\u00020\u0011*\u00020\u0011H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0012"}, d2 = {"kpAnalog", "Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;", "Lcom/google/devtools/ksp/symbol/AnnotationUseSiteTarget;", "getKpAnalog", "(Lcom/google/devtools/ksp/symbol/AnnotationUseSiteTarget;)Lcom/squareup/kotlinpoet/AnnotationSpec$UseSiteTarget;", "addValueToBlock", "", "value", "", "member", "Lcom/squareup/kotlinpoet/CodeBlock$Builder;", "memberForValue", "Lcom/squareup/kotlinpoet/CodeBlock;", "toAnnotationSpec", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "unwrapTypeAlias", "Lcom/google/devtools/ksp/symbol/KSType;", "ksp"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: annotations.kt */
public final class AnnotationsKt {

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* compiled from: annotations.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnnotationUseSiteTarget.values().length];
            iArr[AnnotationUseSiteTarget.FILE.ordinal()] = 1;
            iArr[AnnotationUseSiteTarget.PROPERTY.ordinal()] = 2;
            iArr[AnnotationUseSiteTarget.FIELD.ordinal()] = 3;
            iArr[AnnotationUseSiteTarget.GET.ordinal()] = 4;
            iArr[AnnotationUseSiteTarget.SET.ordinal()] = 5;
            iArr[AnnotationUseSiteTarget.RECEIVER.ordinal()] = 6;
            iArr[AnnotationUseSiteTarget.PARAM.ordinal()] = 7;
            iArr[AnnotationUseSiteTarget.SETPARAM.ordinal()] = 8;
            iArr[AnnotationUseSiteTarget.DELEGATE.ordinal()] = 9;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final AnnotationSpec toAnnotationSpec(KSAnnotation kSAnnotation) {
        AnnotationSpec.Builder builder;
        Intrinsics.checkNotNullParameter(kSAnnotation, "<this>");
        TypeName typeName$default = KsTypesKt.toTypeName$default(unwrapTypeAlias(kSAnnotation.getAnnotationType().resolve()), (TypeParameterResolver) null, 1, (Object) null);
        if (typeName$default instanceof ClassName) {
            builder = AnnotationSpec.Companion.builder((ClassName) typeName$default);
        } else if (typeName$default instanceof ParameterizedTypeName) {
            builder = AnnotationSpec.Companion.builder((ParameterizedTypeName) typeName$default);
        } else {
            throw new IllegalStateException("This is never possible.".toString());
        }
        AnnotationUseSiteTarget useSiteTarget = kSAnnotation.getUseSiteTarget();
        if (useSiteTarget != null) {
            builder.useSiteTarget(getKpAnalog(useSiteTarget));
        }
        for (KSValueArgument next : kSAnnotation.getArguments()) {
            CodeBlock.Builder builder2 = CodeBlock.Companion.builder();
            KSName name = next.getName();
            Intrinsics.checkNotNull(name);
            builder2.add("%N = ", name.getShortName());
            Object value = next.getValue();
            Intrinsics.checkNotNull(value);
            addValueToBlock(value, builder2);
            builder.addMember(builder2.build());
        }
        return builder.build();
    }

    private static final AnnotationSpec.UseSiteTarget getKpAnalog(AnnotationUseSiteTarget annotationUseSiteTarget) {
        switch (WhenMappings.$EnumSwitchMapping$0[annotationUseSiteTarget.ordinal()]) {
            case 1:
                return AnnotationSpec.UseSiteTarget.FILE;
            case 2:
                return AnnotationSpec.UseSiteTarget.PROPERTY;
            case 3:
                return AnnotationSpec.UseSiteTarget.FIELD;
            case 4:
                return AnnotationSpec.UseSiteTarget.GET;
            case 5:
                return AnnotationSpec.UseSiteTarget.SET;
            case 6:
                return AnnotationSpec.UseSiteTarget.RECEIVER;
            case 7:
                return AnnotationSpec.UseSiteTarget.PARAM;
            case 8:
                return AnnotationSpec.UseSiteTarget.SETPARAM;
            case 9:
                return AnnotationSpec.UseSiteTarget.DELEGATE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final KSType unwrapTypeAlias(KSType kSType) {
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        if (!(kSType.getDeclaration() instanceof KSTypeAlias)) {
            return kSType;
        }
        KSDeclaration declaration = kSType.getDeclaration();
        Intrinsics.checkNotNull(declaration, "null cannot be cast to non-null type com.google.devtools.ksp.symbol.KSTypeAlias");
        return ((KSTypeAlias) declaration).getType().resolve();
    }

    private static final void addValueToBlock(Object obj, CodeBlock.Builder builder) {
        boolean z = false;
        if (obj instanceof List) {
            builder.add("arrayOf(⇥⇥", new Object[0]);
            int i = 0;
            for (Object next : (Iterable) obj) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                if (i > 0) {
                    builder.add(", ", new Object[0]);
                }
                Intrinsics.checkNotNull(next);
                addValueToBlock(next, builder);
                i = i2;
            }
            builder.add("⇤⇤)", new Object[0]);
        } else if (obj instanceof KSType) {
            KSType unwrapTypeAlias = unwrapTypeAlias((KSType) obj);
            KSDeclaration declaration = unwrapTypeAlias.getDeclaration();
            Intrinsics.checkNotNull(declaration, "null cannot be cast to non-null type com.google.devtools.ksp.symbol.KSClassDeclaration");
            if (((KSClassDeclaration) declaration).getClassKind() == ClassKind.ENUM_ENTRY) {
                z = true;
            }
            if (z) {
                KSDeclaration parentDeclaration = unwrapTypeAlias.getDeclaration().getParentDeclaration();
                Intrinsics.checkNotNull(parentDeclaration, "null cannot be cast to non-null type com.google.devtools.ksp.symbol.KSClassDeclaration");
                builder.add("%T.%L", KsClassDeclarationsKt.toClassName((KSClassDeclaration) parentDeclaration), unwrapTypeAlias.getDeclaration().getSimpleName().getShortName());
                return;
            }
            builder.add("%T::class", KsTypesKt.toClassName(unwrapTypeAlias));
        } else if (obj instanceof KSName) {
            KSName kSName = (KSName) obj;
            builder.add("%T.%L", ClassName.Companion.bestGuess(kSName.getQualifier()), kSName.getShortName());
        } else if (obj instanceof KSAnnotation) {
            builder.add("%L", toAnnotationSpec((KSAnnotation) obj));
        } else {
            builder.add(memberForValue(obj));
        }
    }

    public static final CodeBlock memberForValue(Object obj) {
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
        if (obj instanceof Double) {
            return CodeBlock.Companion.of("%L", obj);
        }
        if (obj instanceof Character) {
            return CodeBlock.Companion.of(obj + ".toChar()", new Object[0]);
        }
        if (obj instanceof Byte) {
            return CodeBlock.Companion.of(obj + ".toByte()", new Object[0]);
        }
        if (obj instanceof Short) {
            return CodeBlock.Companion.of(obj + ".toShort()", new Object[0]);
        }
        return CodeBlock.Companion.of("%L", obj);
    }
}
