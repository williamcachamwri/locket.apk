package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzkm {
    public static long zza(long j, long j2) {
        boolean z = true;
        boolean z2 = (j ^ j2) < 0;
        long j3 = j + j2;
        if ((j ^ j3) < 0) {
            z = false;
        }
        zzkn.zza(z2 | z, "checkedAdd", j, j2);
        return j3;
    }

    public static long zzb(long j, long j2) {
        boolean z = true;
        boolean z2 = (1 ^ j) >= 0;
        long j3 = -1 + j;
        if ((j ^ j3) < 0) {
            z = false;
        }
        zzkn.zza(z2 | z, "checkedSubtract", j, 1);
        return j3;
    }
}
