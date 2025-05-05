package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zznr implements zzjy {
    final zzlb zza;

    public zznr(zzlb zzlb) {
        this.zza = zzlb;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzlb zzlb = this.zza;
        if (zzlb.zza() != null) {
            return zzxo.zzc(zzlb.zza().zzd(), ((zzjy) this.zza.zza().zzc()).zza(bArr, bArr2));
        }
        throw new GeneralSecurityException("keyset without primary key");
    }
}
