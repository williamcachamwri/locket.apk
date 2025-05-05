package expo.modules.clipboard;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/clipboard/InvalidImageException;", "Lexpo/modules/kotlin/exception/CodedException;", "image", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardExceptions.kt */
public final class InvalidImageException extends CodedException {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InvalidImageException(java.lang.String r4, java.lang.Throwable r5) {
        /*
            r3 = this;
            java.lang.String r0 = "image"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            int r0 = r4.length()
            r1 = 32
            int r0 = java.lang.Math.min(r0, r1)
            r2 = 0
            java.lang.String r0 = r4.substring(r2, r0)
            java.lang.String r2 = "substring(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            int r4 = r4.length()
            if (r4 <= r1) goto L_0x0022
            java.lang.String r4 = "..."
            goto L_0x0024
        L_0x0022:
            java.lang.String r4 = ""
        L_0x0024:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r4 = r4.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid base64 image: "
            r0.<init>(r1)
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.clipboard.InvalidImageException.<init>(java.lang.String, java.lang.Throwable):void");
    }
}
