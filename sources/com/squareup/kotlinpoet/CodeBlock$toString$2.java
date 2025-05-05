package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/squareup/kotlinpoet/CodeWriter;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: CodeBlock.kt */
final class CodeBlock$toString$2 extends Lambda implements Function1<CodeWriter, Unit> {
    final /* synthetic */ CodeBlock this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CodeBlock$toString$2(CodeBlock codeBlock) {
        super(1);
        this.this$0 = codeBlock;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((CodeWriter) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "$this$buildCodeString");
        CodeWriter.emitCode$default(codeWriter, this.this$0, false, false, 6, (Object) null);
    }
}
