package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/squareup/kotlinpoet/Import;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: FileSpec.kt */
final class FileSpec$emit$imports$3 extends Lambda implements Function1<Import, String> {
    public static final FileSpec$emit$imports$3 INSTANCE = new FileSpec$emit$imports$3();

    FileSpec$emit$imports$3() {
        super(1);
    }

    public final String invoke(Import importR) {
        Intrinsics.checkNotNullParameter(importR, "it");
        return importR.toString();
    }
}
