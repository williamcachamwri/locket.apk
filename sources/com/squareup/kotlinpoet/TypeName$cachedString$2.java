package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeName.kt */
final class TypeName$cachedString$2 extends Lambda implements Function0<String> {
    final /* synthetic */ TypeName this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TypeName$cachedString$2(TypeName typeName) {
        super(0);
        this.this$0 = typeName;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        kotlin.io.CloseableKt.closeFinally(r11, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0048, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String invoke() {
        /*
            r12 = this;
            com.squareup.kotlinpoet.TypeName r0 = r12.this$0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            com.squareup.kotlinpoet.CodeWriter r11 = new com.squareup.kotlinpoet.CodeWriter
            r3 = r1
            java.lang.Appendable r3 = (java.lang.Appendable) r3
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 2147483647(0x7fffffff, float:NaN)
            r9 = 30
            r10 = 0
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            java.io.Closeable r11 = (java.io.Closeable) r11
            r2 = r11
            com.squareup.kotlinpoet.CodeWriter r2 = (com.squareup.kotlinpoet.CodeWriter) r2     // Catch:{ all -> 0x0042 }
            r0.emitAnnotations$kotlinpoet(r2)     // Catch:{ all -> 0x0042 }
            r0.emit$kotlinpoet(r2)     // Catch:{ all -> 0x0042 }
            boolean r0 = r0.isNullable()     // Catch:{ all -> 0x0042 }
            r3 = 0
            if (r0 == 0) goto L_0x0033
            java.lang.String r0 = "?"
            r4 = 0
            r5 = 2
            com.squareup.kotlinpoet.CodeWriter.emit$default(r2, r0, r4, r5, r3)     // Catch:{ all -> 0x0042 }
        L_0x0033:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0042 }
            kotlin.io.CloseableKt.closeFinally(r11, r3)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "stringBuilder.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            return r0
        L_0x0042:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r11, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.TypeName$cachedString$2.invoke():java.lang.String");
    }
}
