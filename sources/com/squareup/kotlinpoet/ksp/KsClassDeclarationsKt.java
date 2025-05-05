package com.squareup.kotlinpoet.ksp;

import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.squareup.kotlinpoet.ClassName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toClassName", "Lcom/squareup/kotlinpoet/ClassName;", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "ksp"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: ksClassDeclarations.kt */
public final class KsClassDeclarationsKt {
    public static final ClassName toClassName(KSClassDeclaration kSClassDeclaration) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "<this>");
        return UtilsKt.toClassNameInternal(kSClassDeclaration);
    }
}
