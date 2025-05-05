package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzqw implements zzkq {
    private final zzlb zza;
    private final zzrc zzb;
    private final zzrc zzc;

    /* synthetic */ zzqw(zzlb zzlb, zzqv zzqv) {
        zzrc zzrc;
        this.zza = zzlb;
        if (zzlb.zze()) {
            zzrd zzb2 = zzpi.zza().zzb();
            zzri zza2 = zzpf.zza(zzlb);
            this.zzb = zzb2.zza(zza2, "mac", "compute");
            zzrc = zzb2.zza(zza2, "mac", "verify");
        } else {
            zzrc = zzpf.zza;
            this.zzb = zzrc;
        }
        this.zzc = zzrc;
    }

    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        if (this.zza.zza().zzf() == 4) {
            bArr = zzxo.zzc(bArr, zzqx.zzb);
        }
        byte[] zzc2 = zzxo.zzc(this.zza.zza().zzd(), ((zzkq) this.zza.zza().zzc()).zza(bArr));
        this.zza.zza().zza();
        return zzc2;
    }
}
