package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzob implements zzjy {
    private static final byte[] zza = new byte[0];
    private final zzvj zzb;
    private final zzoc zzc;
    private final zzny zzd;
    private final zznx zze;

    private zzob(zzvj zzvj, zzoc zzoc, zznx zznx, zzny zzny, byte[] bArr) {
        this.zzb = zzvj;
        this.zzc = zzoc;
        this.zze = zznx;
        this.zzd = zzny;
    }

    static zzob zzb(zzvj zzvj) throws GeneralSecurityException {
        if (!zzvj.zzh().zzs()) {
            zzvd zzc2 = zzvj.zzc();
            return new zzob(zzvj, zzof.zzb(zzc2), zzof.zzc(zzc2), zzof.zza(zzc2), (byte[]) null);
        }
        throw new IllegalArgumentException("HpkePublicKey.public_key is empty.");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        byte[] bArr3 = bArr2;
        zzvj zzvj = this.zzb;
        zzoc zzoc = this.zzc;
        zznx zznx = this.zze;
        zzny zzny = this.zzd;
        zzod zza2 = zzoc.zza(zzvj.zzh().zzt());
        zznz zzc2 = zznz.zzc(zza2.zza(), zza2.zzb(), zzoc, zznx, zzny, bArr3);
        return zzxo.zzc(zzc2.zza(), zzc2.zzb(bArr, zza));
    }
}
