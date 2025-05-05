package com.squareup.kotlinpoet;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "importName", "", "invoke", "(Ljava/lang/String;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: FileSpec.kt */
final class FileSpec$emit$1 extends Lambda implements Function1<String, Boolean> {
    final /* synthetic */ List<String> $defaultImports;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileSpec$emit$1(List<String> list) {
        super(1);
        this.$defaultImports = list;
    }

    public final Boolean invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "importName");
        return Boolean.valueOf(this.$defaultImports.contains(StringsKt.substringBeforeLast$default(str, ".", (String) null, 2, (Object) null)));
    }
}
