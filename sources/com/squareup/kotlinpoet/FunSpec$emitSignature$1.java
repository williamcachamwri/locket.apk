package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "param", "Lcom/squareup/kotlinpoet/ParameterSpec;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: FunSpec.kt */
final class FunSpec$emitSignature$1 extends Lambda implements Function1<ParameterSpec, Unit> {
    final /* synthetic */ CodeWriter $codeWriter;
    final /* synthetic */ FunSpec this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FunSpec$emitSignature$1(CodeWriter codeWriter, FunSpec funSpec) {
        super(1);
        this.$codeWriter = codeWriter;
        this.this$0 = funSpec;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ParameterSpec) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ParameterSpec parameterSpec) {
        Intrinsics.checkNotNullParameter(parameterSpec, "param");
        ParameterSpec.emit$kotlinpoet$default(parameterSpec, this.$codeWriter, !Intrinsics.areEqual((Object) this.this$0.getName(), (Object) FunSpec.SETTER), false, false, 12, (Object) null);
    }
}
