package com.google.android.gms.internal.p002firebaseauthapi;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzj extends zzk {
    private final char zza;

    public final String toString() {
        char c = this.zza;
        char[] cArr = {'\\', AbstractJsonLexerKt.UNICODE_ESC, 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return "CharMatcher.is('" + String.copyValueOf(cArr) + "')";
    }

    zzj(char c) {
        this.zza = c;
    }

    public final boolean zza(char c) {
        return c == this.zza;
    }
}
