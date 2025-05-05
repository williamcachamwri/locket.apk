package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: Util.kt */
final class UtilKt$escapeSegmentsIfNecessary$2 extends Lambda implements Function1<String, CharSequence> {
    public static final UtilKt$escapeSegmentsIfNecessary$2 INSTANCE = new UtilKt$escapeSegmentsIfNecessary$2();

    UtilKt$escapeSegmentsIfNecessary$2() {
        super(1);
    }

    public final CharSequence invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        return UtilKt.escapeIfNecessary$default(str, false, 1, (Object) null);
    }
}
