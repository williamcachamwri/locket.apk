package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "it", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: FileSpec.kt */
final class FileSpec$emit$imports$2 extends Lambda implements Function1<String, String> {
    public static final FileSpec$emit$imports$2 INSTANCE = new FileSpec$emit$imports$2();

    FileSpec$emit$imports$2() {
        super(1);
    }

    public final String invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        return UtilKt.escapeSegmentsIfNecessary$default(str, 0, 1, (Object) null);
    }
}
