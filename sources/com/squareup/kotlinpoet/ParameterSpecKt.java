package com.squareup.kotlinpoet;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a:\u0010\u0003\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\t2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0\u000bH\u0000\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"ALLOWED_PARAMETER_MODIFIERS", "", "Lcom/squareup/kotlinpoet/KModifier;", "emit", "Lcom/squareup/kotlinpoet/CodeWriter;", "", "Lcom/squareup/kotlinpoet/ParameterSpec;", "codeWriter", "forceNewLines", "", "emitBlock", "Lkotlin/Function1;", "", "kotlinpoet"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: ParameterSpec.kt */
public final class ParameterSpecKt {
    /* access modifiers changed from: private */
    public static final Set<KModifier> ALLOWED_PARAMETER_MODIFIERS = SetsKt.setOf(KModifier.VARARG, KModifier.NOINLINE, KModifier.CROSSINLINE);

    public static /* synthetic */ CodeWriter emit$default(List list, CodeWriter codeWriter, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function1 = new ParameterSpecKt$emit$1(codeWriter);
        }
        return emit(list, codeWriter, z, function1);
    }

    public static final CodeWriter emit(List<ParameterSpec> list, CodeWriter codeWriter, boolean z, Function1<? super ParameterSpec, Unit> function1) {
        String str;
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(codeWriter, "codeWriter");
        Intrinsics.checkNotNullParameter(function1, "emitBlock");
        CodeWriter.emit$default(codeWriter, "(", false, 2, (Object) null);
        if (!list.isEmpty()) {
            boolean z2 = list.size() > 2 || z;
            if (z2) {
                CodeWriter.emit$default(codeWriter, "\n", false, 2, (Object) null);
                codeWriter.indent(1);
            }
            int i = 0;
            for (Object next : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                ParameterSpec parameterSpec = (ParameterSpec) next;
                if (i > 0) {
                    if (z2) {
                        str = "\n";
                    } else {
                        str = ", ";
                    }
                    CodeWriter.emit$default(codeWriter, str, false, 2, (Object) null);
                }
                function1.invoke(parameterSpec);
                if (z2) {
                    CodeWriter.emit$default(codeWriter, ",", false, 2, (Object) null);
                }
                i = i2;
            }
            if (z2) {
                codeWriter.unindent(1);
                CodeWriter.emit$default(codeWriter, "\n", false, 2, (Object) null);
            }
        }
        return CodeWriter.emit$default(codeWriter, ")", false, 2, (Object) null);
    }
}
