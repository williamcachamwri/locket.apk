package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzkn {
    static void zza(boolean z, String str, long j, long j2) {
        if (!z) {
            throw new ArithmeticException("overflow: " + str + "(" + j + ", " + j2 + ")");
        }
    }

    static void zzb(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }
}
