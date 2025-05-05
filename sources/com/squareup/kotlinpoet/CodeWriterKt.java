package com.squareup.kotlinpoet;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\fH\u0000\u001a%\u0010\u0006\u001a\u00020\u00012\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\fH\bø\u0001\u0000\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\u0002\u0007\n\u0005\b20\u0001¨\u0006\u000f"}, d2 = {"NO_PACKAGE", "", "NULLABLE_ANY", "Lcom/squareup/kotlinpoet/TypeName;", "getNULLABLE_ANY", "()Lcom/squareup/kotlinpoet/TypeName;", "buildCodeString", "codeWriter", "Lcom/squareup/kotlinpoet/CodeWriter;", "builderAction", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "extractMemberName", "part", "kotlinpoet"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: CodeWriter.kt */
public final class CodeWriterKt {
    /* access modifiers changed from: private */
    public static final String NO_PACKAGE = new String();
    private static final TypeName NULLABLE_ANY = TypeName.copy$default(TypeNames.ANY, true, (List) null, 2, (Object) null);

    public static final TypeName getNULLABLE_ANY() {
        return NULLABLE_ANY;
    }

    /* access modifiers changed from: private */
    public static final String extractMemberName(String str) {
        if (Character.isJavaIdentifierStart(str.charAt(0))) {
            int length = str.length();
            if (1 <= length) {
                int i = 1;
                while (true) {
                    String substring = str.substring(0, i);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    if (UtilKt.isIdentifier(substring)) {
                        if (i == length) {
                            break;
                        }
                        i++;
                    } else {
                        String substring2 = str.substring(0, i - 1);
                        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                        return substring2;
                    }
                }
            }
            return str;
        }
        throw new IllegalArgumentException(("not an identifier: " + str).toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0048, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003f, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlin.io.CloseableKt.closeFinally(r10, r11);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String buildCodeString(kotlin.jvm.functions.Function1<? super com.squareup.kotlinpoet.CodeWriter, kotlin.Unit> r11) {
        /*
            java.lang.String r0 = "builderAction"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.squareup.kotlinpoet.CodeWriter r10 = new com.squareup.kotlinpoet.CodeWriter
            r2 = r0
            java.lang.Appendable r2 = (java.lang.Appendable) r2
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 2147483647(0x7fffffff, float:NaN)
            r8 = 30
            r9 = 0
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            java.io.Closeable r10 = (java.io.Closeable) r10
            r1 = 1
            r2 = r10
            com.squareup.kotlinpoet.CodeWriter r2 = (com.squareup.kotlinpoet.CodeWriter) r2     // Catch:{ all -> 0x003c }
            r11.invoke(r2)     // Catch:{ all -> 0x003c }
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003c }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r11 = 0
            kotlin.io.CloseableKt.closeFinally(r10, r11)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            java.lang.String r11 = r0.toString()
            java.lang.String r0 = "stringBuilder.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r0)
            return r11
        L_0x003c:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x003e }
        L_0x003e:
            r0 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlin.io.CloseableKt.closeFinally(r10, r11)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.CodeWriterKt.buildCodeString(kotlin.jvm.functions.Function1):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0044, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0040, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0041, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String buildCodeString(com.squareup.kotlinpoet.CodeWriter r5, kotlin.jvm.functions.Function1<? super com.squareup.kotlinpoet.CodeWriter, kotlin.Unit> r6) {
        /*
            java.lang.String r0 = "codeWriter"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "builderAction"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.squareup.kotlinpoet.LineWrapper r1 = new com.squareup.kotlinpoet.LineWrapper
            r2 = r0
            java.lang.Appendable r2 = (java.lang.Appendable) r2
            java.lang.String r3 = "  "
            r4 = 2147483647(0x7fffffff, float:NaN)
            r1.<init>(r2, r3, r4)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = r1
            com.squareup.kotlinpoet.LineWrapper r2 = (com.squareup.kotlinpoet.LineWrapper) r2     // Catch:{ all -> 0x003e }
            com.squareup.kotlinpoet.LineWrapper r3 = r5.out     // Catch:{ all -> 0x003e }
            r5.out = r2     // Catch:{ all -> 0x003e }
            r6.invoke(r5)     // Catch:{ all -> 0x003e }
            r5.out = r3     // Catch:{ all -> 0x003e }
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003e }
            r5 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r5)
            java.lang.String r5 = r0.toString()
            java.lang.String r6 = "stringBuilder.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            return r5
        L_0x003e:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0040 }
        L_0x0040:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.kotlinpoet.CodeWriterKt.buildCodeString(com.squareup.kotlinpoet.CodeWriter, kotlin.jvm.functions.Function1):java.lang.String");
    }
}
