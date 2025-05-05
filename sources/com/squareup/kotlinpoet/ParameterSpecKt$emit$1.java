package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/squareup/kotlinpoet/ParameterSpec;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: ParameterSpec.kt */
final class ParameterSpecKt$emit$1 extends Lambda implements Function1<ParameterSpec, Unit> {
    final /* synthetic */ CodeWriter $codeWriter;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ParameterSpecKt$emit$1(CodeWriter codeWriter) {
        super(1);
        this.$codeWriter = codeWriter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ParameterSpec) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ParameterSpec parameterSpec) {
        Intrinsics.checkNotNullParameter(parameterSpec, "it");
        ParameterSpec.emit$kotlinpoet$default(parameterSpec, this.$codeWriter, false, false, false, 14, (Object) null);
    }
}
