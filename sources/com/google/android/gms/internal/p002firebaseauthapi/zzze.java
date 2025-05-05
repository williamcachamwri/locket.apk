package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzze  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzze {
    private final zzzc zza;

    public final int zza() {
        return this.zza.zza();
    }

    public static zzze zza(byte[] bArr, zzcn zzcn) {
        if (zzcn != null) {
            return new zzze(zzzc.zza(bArr));
        }
        throw new NullPointerException("SecretKeyAccess required");
    }

    public static zzze zza(int i) {
        return new zzze(zzzc.zza(zzpe.zza(i)));
    }

    private zzze(zzzc zzzc) {
        this.zza = zzzc;
    }

    public final byte[] zza(zzcn zzcn) {
        if (zzcn != null) {
            return this.zza.zzb();
        }
        throw new NullPointerException("SecretKeyAccess required");
    }
}
