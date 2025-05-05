package com.squareup.kotlinpoet.ksp;

import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSName;
import com.squareup.kotlinpoet.ClassName;
import com.squareup.kotlinpoet.LambdaTypeName;
import com.squareup.kotlinpoet.ParameterizedTypeName;
import com.squareup.kotlinpoet.TypeName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0005H\u0000\u001a\u001a\u0010\u0006\u001a\u00020\u0002*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bH\u0000¨\u0006\t"}, d2 = {"findRawType", "Lcom/squareup/kotlinpoet/ClassName;", "Lcom/squareup/kotlinpoet/TypeName;", "rawType", "toClassNameInternal", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "withTypeArguments", "arguments", "", "ksp"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: utils.kt */
public final class UtilsKt {
    public static final ClassName rawType(TypeName typeName) {
        Intrinsics.checkNotNullParameter(typeName, "<this>");
        ClassName findRawType = findRawType(typeName);
        if (findRawType != null) {
            return findRawType;
        }
        throw new IllegalArgumentException("Cannot get raw type from " + typeName);
    }

    public static final ClassName findRawType(TypeName typeName) {
        String str;
        Intrinsics.checkNotNullParameter(typeName, "<this>");
        if (typeName instanceof ClassName) {
            return (ClassName) typeName;
        }
        if (typeName instanceof ParameterizedTypeName) {
            return ((ParameterizedTypeName) typeName).getRawType();
        }
        if (!(typeName instanceof LambdaTypeName)) {
            return null;
        }
        LambdaTypeName lambdaTypeName = (LambdaTypeName) typeName;
        int size = lambdaTypeName.getParameters().size();
        if (lambdaTypeName.getReceiver() != null) {
            size++;
        }
        if (size >= 23) {
            str = "FunctionN";
        } else {
            str = "Function" + size;
        }
        return new ClassName("kotlin.jvm.functions", str);
    }

    public static final TypeName withTypeArguments(ClassName className, List<? extends TypeName> list) {
        Intrinsics.checkNotNullParameter(className, "<this>");
        Intrinsics.checkNotNullParameter(list, "arguments");
        if (list.isEmpty()) {
            return className;
        }
        return ParameterizedTypeName.Companion.get(className, list);
    }

    public static final ClassName toClassNameInternal(KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        if (!com.google.devtools.ksp.UtilsKt.isLocal(kSDeclaration)) {
            String asString = kSDeclaration.getPackageName().asString();
            KSName qualifiedName = kSDeclaration.getQualifiedName();
            if (qualifiedName != null) {
                return new ClassName(asString, (List<String>) StringsKt.split$default((CharSequence) StringsKt.removePrefix(qualifiedName.asString(), (CharSequence) asString + FilenameUtils.EXTENSION_SEPARATOR), new String[]{"."}, false, 0, 6, (Object) null));
            }
            throw new IllegalStateException("Required value was null.".toString());
        }
        throw new IllegalArgumentException("Local/anonymous classes are not supported!".toString());
    }
}
