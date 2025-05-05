package com.google.android.gms.internal.pal;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzrg {
    private final zzkj zza;
    private final int zzb;
    private final zzks zzc;

    /* synthetic */ zzrg(zzkj zzkj, int i, zzks zzks, zzrf zzrf) {
        this.zza = zzkj;
        this.zzb = i;
        this.zzc = zzks;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzrg)) {
            return false;
        }
        zzrg zzrg = (zzrg) obj;
        if (this.zza == zzrg.zza && this.zzb == zzrg.zzb && this.zzc.equals(zzrg.zzc)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, Integer.valueOf(this.zzb), Integer.valueOf(this.zzc.hashCode())});
    }

    public final String toString() {
        return String.format("(status=%s, keyId=%s, parameters='%s')", new Object[]{this.zza, Integer.valueOf(this.zzb), this.zzc});
    }

    public final int zza() {
        return this.zzb;
    }
}
