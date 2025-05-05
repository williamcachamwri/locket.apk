package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zznp {
    private final zzbr zza;
    private final int zzb;
    private final String zzc;
    private final String zzd;

    public final int zza() {
        return this.zzb;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, Integer.valueOf(this.zzb), this.zzc, this.zzd});
    }

    public final String toString() {
        return String.format("(status=%s, keyId=%s, keyType='%s', keyPrefix='%s')", new Object[]{this.zza, Integer.valueOf(this.zzb), this.zzc, this.zzd});
    }

    private zznp(zzbr zzbr, int i, String str, String str2) {
        this.zza = zzbr;
        this.zzb = i;
        this.zzc = str;
        this.zzd = str2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zznp)) {
            return false;
        }
        zznp zznp = (zznp) obj;
        if (this.zza != zznp.zza || this.zzb != zznp.zzb || !this.zzc.equals(zznp.zzc) || !this.zzd.equals(zznp.zzd)) {
            return false;
        }
        return true;
    }
}
