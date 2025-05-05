package com.google.android.gms.internal.common;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzl extends zzk {
    private final char zza;

    zzl(char c) {
        this.zza = c;
    }

    public final String toString() {
        char[] cArr = {'\\', AbstractJsonLexerKt.UNICODE_ESC, 0, 0, 0, 0};
        int i = this.zza;
        for (int i2 = 0; i2 < 4; i2++) {
            cArr[5 - i2] = "0123456789ABCDEF".charAt(i & 15);
            i >>= 4;
        }
        String copyValueOf = String.copyValueOf(cArr);
        return "CharMatcher.is('" + copyValueOf + "')";
    }

    public final boolean zza(char c) {
        return c == this.zza;
    }
}
