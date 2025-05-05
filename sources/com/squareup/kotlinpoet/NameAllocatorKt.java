package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¨\u0006\u0003"}, d2 = {"toJavaIdentifier", "", "suggestion", "kotlinpoet"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: NameAllocator.kt */
public final class NameAllocatorKt {
    /* access modifiers changed from: private */
    public static final String toJavaIdentifier(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            int codePointAt = str.codePointAt(i);
            if (i == 0 && !Character.isJavaIdentifierStart(codePointAt) && Character.isJavaIdentifierPart(codePointAt)) {
                sb.append("_");
            }
            sb.appendCodePoint(Character.isJavaIdentifierPart(codePointAt) ? codePointAt : 95);
            i += Character.charCount(codePointAt);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
