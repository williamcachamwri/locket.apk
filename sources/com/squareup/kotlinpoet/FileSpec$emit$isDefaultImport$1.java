package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/String;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: FileSpec.kt */
final class FileSpec$emit$isDefaultImport$1 extends Lambda implements Function1<String, Boolean> {
    public static final FileSpec$emit$isDefaultImport$1 INSTANCE = new FileSpec$emit$isDefaultImport$1();

    FileSpec$emit$isDefaultImport$1() {
        super(1);
    }

    public final Boolean invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        return false;
    }
}
