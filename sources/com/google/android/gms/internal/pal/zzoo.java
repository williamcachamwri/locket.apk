package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzoo implements zzoc {
    private final zznx zza;

    zzoo(zznx zznx) {
        this.zza = zznx;
    }

    public final zzod zza(byte[] bArr) throws GeneralSecurityException {
        byte[] zzb = zzyt.zzb();
        byte[] zza2 = zzyt.zza(zzb, bArr);
        byte[] zzc = zzyt.zzc(zzb);
        byte[] zzc2 = zzxo.zzc(zzc, bArr);
        byte[] zzd = zzol.zzd(zzol.zzb);
        zznx zznx = this.zza;
        return new zzod(zznx.zzb((byte[]) null, zza2, "eae_prk", zzc2, "shared_secret", zzd, zznx.zza()), zzc);
    }

    public final byte[] zzb() throws GeneralSecurityException {
        if (Arrays.equals(this.zza.zzc(), zzol.zzf)) {
            return zzol.zzb;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
