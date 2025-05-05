package com.google.android.gms.internal.fido;

import com.google.common.primitives.SignedBytes;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public final class zzdk extends zzdr {
    private final zzcz zza;

    zzdk(zzcz zzcz) {
        this.zza = zzcz;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzdr zzdr = (zzdr) obj;
        if (zzd(SignedBytes.MAX_POWER_OF_TWO) != zzdr.zza()) {
            return zzd(SignedBytes.MAX_POWER_OF_TWO) - zzdr.zza();
        }
        zzdk zzdk = (zzdk) zzdr;
        zzcz zzcz = this.zza;
        int zzd = zzcz.zzd();
        zzcz zzcz2 = zzdk.zza;
        if (zzd != zzcz2.zzd()) {
            return zzcz.zzd() - zzcz2.zzd();
        }
        return zzco.zza().compare(zzcz.zzm(), zzdk.zza.zzm());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.zza.equals(((zzdk) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzd(SignedBytes.MAX_POWER_OF_TWO)), this.zza});
    }

    public final String toString() {
        zzch zzd = zzch.zzf().zzd();
        byte[] zzm = this.zza.zzm();
        String zzg = zzd.zzg(zzm, 0, zzm.length);
        return "h'" + zzg + "'";
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        return zzd(SignedBytes.MAX_POWER_OF_TWO);
    }

    public final zzcz zzc() {
        return this.zza;
    }
}
