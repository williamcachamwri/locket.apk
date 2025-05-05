package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzkv implements zzkw {
    private final zzdg zza;
    private final int zzb;

    public final int zza() {
        return this.zzb;
    }

    public zzkv(zzdg zzdg) {
        this.zza = zzdg;
        this.zzb = zzdg.zzb() + zzdg.zzc();
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        if (bArr2.length >= i) {
            return zzyc.zza(zzcz.zzb().zza(this.zza).zza(zzze.zza(Arrays.copyOf(bArr, this.zza.zzb()), zzbq.zza())).zzb(zzze.zza(Arrays.copyOfRange(bArr, this.zza.zzb(), this.zza.zzb() + this.zza.zzc()), zzbq.zza())).zza()).zza(Arrays.copyOfRange(bArr2, i, bArr2.length), zzks.zza);
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}
