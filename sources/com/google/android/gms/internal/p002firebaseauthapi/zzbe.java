package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbe  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzbe {
    public static long zza(long j, long j2) {
        long j3 = j + j2;
        boolean z = true;
        boolean z2 = (j ^ j2) < 0;
        if ((j ^ j3) < 0) {
            z = false;
        }
        zzbd.zza(z2 | z, "checkedAdd", j, j2);
        return j3;
    }

    public static long zzb(long j, long j2) {
        long j3 = j - 1;
        boolean z = true;
        boolean z2 = (1 ^ j) >= 0;
        if ((j ^ j3) < 0) {
            z = false;
        }
        zzbd.zza(z2 | z, "checkedSubtract", j, 1);
        return j3;
    }
}
