package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.ParameterizedTypeName;
import com.squareup.kotlinpoet.TypeName;
import java.util.Map;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.SimpleTypeVisitor7;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000M\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001J\u001c\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0014J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\f\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u000e\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0010\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00112\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0012\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00132\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0014\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00152\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0016\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00172\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016¨\u0006\u0018"}, d2 = {"com/squareup/kotlinpoet/TypeName$Companion$get$1", "Ljavax/lang/model/util/SimpleTypeVisitor7;", "Lcom/squareup/kotlinpoet/TypeName;", "Ljava/lang/Void;", "defaultAction", "e", "Ljavax/lang/model/type/TypeMirror;", "p", "visitArray", "Lcom/squareup/kotlinpoet/ParameterizedTypeName;", "t", "Ljavax/lang/model/type/ArrayType;", "visitDeclared", "Ljavax/lang/model/type/DeclaredType;", "visitError", "Ljavax/lang/model/type/ErrorType;", "visitNoType", "Ljavax/lang/model/type/NoType;", "visitPrimitive", "Ljavax/lang/model/type/PrimitiveType;", "visitTypeVariable", "Ljavax/lang/model/type/TypeVariable;", "visitWildcard", "Ljavax/lang/model/type/WildcardType;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeName.kt */
public final class TypeName$Companion$get$1 extends SimpleTypeVisitor7<TypeName, Void> {
    final /* synthetic */ Map<TypeParameterElement, TypeVariableName> $typeVariables;

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* compiled from: TypeName.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TypeKind.values().length];
            iArr[TypeKind.BOOLEAN.ordinal()] = 1;
            iArr[TypeKind.BYTE.ordinal()] = 2;
            iArr[TypeKind.SHORT.ordinal()] = 3;
            iArr[TypeKind.INT.ordinal()] = 4;
            iArr[TypeKind.LONG.ordinal()] = 5;
            iArr[TypeKind.CHAR.ordinal()] = 6;
            iArr[TypeKind.FLOAT.ordinal()] = 7;
            iArr[TypeKind.DOUBLE.ordinal()] = 8;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    TypeName$Companion$get$1(Map<TypeParameterElement, TypeVariableName> map) {
        this.$typeVariables = map;
    }

    public TypeName visitPrimitive(PrimitiveType primitiveType, Void voidR) {
        Intrinsics.checkNotNullParameter(primitiveType, "t");
        TypeKind kind = primitiveType.getKind();
        switch (kind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
            case 1:
                return TypeNames.BOOLEAN;
            case 2:
                return TypeNames.BYTE;
            case 3:
                return TypeNames.SHORT;
            case 4:
                return TypeNames.INT;
            case 5:
                return TypeNames.LONG;
            case 6:
                return TypeNames.CHAR;
            case 7:
                return TypeNames.FLOAT;
            case 8:
                return TypeNames.DOUBLE;
            default:
                throw new AssertionError();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.squareup.kotlinpoet.TypeName} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.squareup.kotlinpoet.TypeName visitDeclared(javax.lang.model.type.DeclaredType r10, java.lang.Void r11) {
        /*
            r9 = this;
            java.lang.String r11 = "t"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r11)
            javax.lang.model.element.Element r11 = r10.asElement()
            java.lang.String r0 = "null cannot be cast to non-null type javax.lang.model.element.TypeElement"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11, r0)
            javax.lang.model.element.TypeElement r11 = (javax.lang.model.element.TypeElement) r11
            com.squareup.kotlinpoet.ClassName r2 = com.squareup.kotlinpoet.ClassNames.get((javax.lang.model.element.TypeElement) r11)
            javax.lang.model.type.TypeMirror r11 = r10.getEnclosingType()
            javax.lang.model.type.TypeKind r0 = r11.getKind()
            javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.NONE
            r3 = 0
            if (r0 == r1) goto L_0x003b
            javax.lang.model.element.Element r0 = r10.asElement()
            java.util.Set r0 = r0.getModifiers()
            javax.lang.model.element.Modifier r1 = javax.lang.model.element.Modifier.STATIC
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L_0x003b
            r0 = r9
            javax.lang.model.type.TypeVisitor r0 = (javax.lang.model.type.TypeVisitor) r0
            java.lang.Object r11 = r11.accept(r0, r3)
            r3 = r11
            com.squareup.kotlinpoet.TypeName r3 = (com.squareup.kotlinpoet.TypeName) r3
        L_0x003b:
            java.util.List r11 = r10.getTypeArguments()
            boolean r11 = r11.isEmpty()
            if (r11 == 0) goto L_0x004c
            boolean r11 = r3 instanceof com.squareup.kotlinpoet.ParameterizedTypeName
            if (r11 != 0) goto L_0x004c
            com.squareup.kotlinpoet.TypeName r2 = (com.squareup.kotlinpoet.TypeName) r2
            return r2
        L_0x004c:
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.List r11 = (java.util.List) r11
            java.util.List r10 = r10.getTypeArguments()
            java.util.Iterator r10 = r10.iterator()
        L_0x005b:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x007b
            java.lang.Object r0 = r10.next()
            javax.lang.model.type.TypeMirror r0 = (javax.lang.model.type.TypeMirror) r0
            r1 = r11
            java.util.Collection r1 = (java.util.Collection) r1
            com.squareup.kotlinpoet.TypeName$Companion r4 = com.squareup.kotlinpoet.TypeName.Companion
            java.lang.String r5 = "typeArgument"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r5)
            java.util.Map<javax.lang.model.element.TypeParameterElement, com.squareup.kotlinpoet.TypeVariableName> r5 = r9.$typeVariables
            com.squareup.kotlinpoet.TypeName r0 = r4.get$kotlinpoet((javax.lang.model.type.TypeMirror) r0, (java.util.Map<javax.lang.model.element.TypeParameterElement, com.squareup.kotlinpoet.TypeVariableName>) r5)
            r1.add(r0)
            goto L_0x005b
        L_0x007b:
            boolean r10 = r3 instanceof com.squareup.kotlinpoet.ParameterizedTypeName
            if (r10 == 0) goto L_0x008c
            com.squareup.kotlinpoet.ParameterizedTypeName r3 = (com.squareup.kotlinpoet.ParameterizedTypeName) r3
            java.lang.String r10 = r2.getSimpleName()
            com.squareup.kotlinpoet.ParameterizedTypeName r10 = r3.nestedClass(r10, r11)
            com.squareup.kotlinpoet.TypeName r10 = (com.squareup.kotlinpoet.TypeName) r10
            goto L_0x009c
        L_0x008c:
            com.squareup.kotlinpoet.ParameterizedTypeName r10 = new com.squareup.kotlinpoet.ParameterizedTypeName
            r1 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 56
            r8 = 0
            r0 = r10
            r3 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            com.squareup.kotlinpoet.TypeName r10 = (com.squareup.kotlinpoet.TypeName) r10
        L_0x009c:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.TypeName$Companion$get$1.visitDeclared(javax.lang.model.type.DeclaredType, java.lang.Void):com.squareup.kotlinpoet.TypeName");
    }

    public TypeName visitError(ErrorType errorType, Void voidR) {
        Intrinsics.checkNotNullParameter(errorType, "t");
        return visitDeclared((DeclaredType) errorType, voidR);
    }

    public ParameterizedTypeName visitArray(ArrayType arrayType, Void voidR) {
        Intrinsics.checkNotNullParameter(arrayType, "t");
        ParameterizedTypeName.Companion companion = ParameterizedTypeName.Companion;
        ClassName className = TypeNames.ARRAY;
        TypeName.Companion companion2 = TypeName.Companion;
        TypeMirror componentType = arrayType.getComponentType();
        Intrinsics.checkNotNullExpressionValue(componentType, "t.componentType");
        return companion.get(className, companion2.get$kotlinpoet(componentType, this.$typeVariables));
    }

    public TypeName visitTypeVariable(TypeVariable typeVariable, Void voidR) {
        Intrinsics.checkNotNullParameter(typeVariable, "t");
        return TypeVariableName.Companion.get$kotlinpoet(typeVariable, MapsKt.toMutableMap(this.$typeVariables));
    }

    public TypeName visitWildcard(WildcardType wildcardType, Void voidR) {
        Intrinsics.checkNotNullParameter(wildcardType, "t");
        return WildcardTypeName.Companion.get$kotlinpoet(wildcardType, this.$typeVariables);
    }

    public TypeName visitNoType(NoType noType, Void voidR) {
        Intrinsics.checkNotNullParameter(noType, "t");
        if (noType.getKind() == TypeKind.VOID) {
            return TypeNames.UNIT;
        }
        Object visitUnknown = TypeName$Companion$get$1.super.visitUnknown((TypeMirror) noType, voidR);
        Intrinsics.checkNotNullExpressionValue(visitUnknown, "super.visitUnknown(t, p)");
        return (TypeName) visitUnknown;
    }

    /* access modifiers changed from: protected */
    public TypeName defaultAction(TypeMirror typeMirror, Void voidR) {
        StringBuilder sb = new StringBuilder("Unexpected type mirror: ");
        Intrinsics.checkNotNull(typeMirror);
        throw new IllegalArgumentException(sb.append(typeMirror).toString());
    }
}
