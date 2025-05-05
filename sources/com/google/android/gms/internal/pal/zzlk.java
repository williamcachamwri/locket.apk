package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzlk implements zzjt {
    private final zzlb zza;
    private final zzrc zzb;
    private final zzrc zzc;

    /* synthetic */ zzlk(zzlb zzlb, zzlj zzlj) {
        zzrc zzrc;
        this.zza = zzlb;
        if (zzlb.zze()) {
            zzrd zzb2 = zzpi.zza().zzb();
            zzri zza2 = zzpf.zza(zzlb);
            this.zzb = zzb2.zza(zza2, "aead", "encrypt");
            zzrc = zzb2.zza(zza2, "aead", "decrypt");
        } else {
            zzrc = zzpf.zza;
            this.zzb = zzrc;
        }
        this.zzc = zzrc;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zzc2 = zzxo.zzc(this.zza.zza().zzd(), ((zzjt) this.zza.zza().zzc()).zza(bArr, bArr2));
        this.zza.zza().zza();
        int length = bArr.length;
        return zzc2;
    }
}
