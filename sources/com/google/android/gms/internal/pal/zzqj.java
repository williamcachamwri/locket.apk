package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzqj extends zzqu {
    private final int zza;
    private final zzqi zzb;

    private zzqj(int i, zzqi zzqi) {
        this.zza = i;
        this.zzb = zzqi;
    }

    public static zzqj zzb(int i, zzqi zzqi) throws GeneralSecurityException {
        if (i >= 10 && i <= 16) {
            return new zzqj(i, zzqi);
        }
        throw new GeneralSecurityException("Invalid tag size for AesCmacParameters: " + i);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzqj)) {
            return false;
        }
        zzqj zzqj = (zzqj) obj;
        if (zzqj.zza() == zza() && zzqj.zzb == this.zzb) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zza), this.zzb});
    }

    public final String toString() {
        String obj = this.zzb.toString();
        int i = this.zza;
        return "AES-CMAC Parameters (variant: " + obj + ", " + i + "-byte tags)";
    }

    public final int zza() {
        zzqi zzqi = this.zzb;
        if (zzqi == zzqi.zzd) {
            return this.zza;
        }
        if (zzqi == zzqi.zza || zzqi == zzqi.zzb || zzqi == zzqi.zzc) {
            return this.zza + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public final boolean zzc() {
        return this.zzb != zzqi.zzd;
    }
}
