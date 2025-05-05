package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzoa implements zzjx {
    private final zzoe zza;
    private final zzoc zzb;
    private final zzny zzc;
    private final zznx zzd;

    private zzoa(zzoe zzoe, zzoc zzoc, zznx zznx, zzny zzny, int i, byte[] bArr) {
        this.zza = zzoe;
        this.zzb = zzoc;
        this.zzd = zznx;
        this.zzc = zzny;
    }

    static zzoa zza(zzvg zzvg) throws GeneralSecurityException {
        zzoe zzoe;
        if (!zzvg.zzk()) {
            throw new IllegalArgumentException("HpkePrivateKey is missing public_key field.");
        } else if (!zzvg.zzf().zzl()) {
            throw new IllegalArgumentException("HpkePrivateKey.public_key is missing params field.");
        } else if (!zzvg.zzg().zzs()) {
            zzvd zzc2 = zzvg.zzf().zzc();
            zzoc zzb2 = zzof.zzb(zzc2);
            zznx zzc3 = zzof.zzc(zzc2);
            zzny zza2 = zzof.zza(zzc2);
            int zzg = zzc2.zzg();
            int i = 1;
            if (zzg - 2 == 1) {
                int zzg2 = zzvg.zzf().zzc().zzg() - 2;
                if (zzg2 == 1) {
                    zzoe = zzop.zza(zzvg.zzg().zzt());
                } else if (zzg2 == 2 || zzg2 == 3 || zzg2 == 4) {
                    byte[] zzt = zzvg.zzg().zzt();
                    byte[] zzt2 = zzvg.zzf().zzh().zzt();
                    int zzg3 = zzvg.zzf().zzc().zzg() - 2;
                    if (zzg3 != 2) {
                        if (zzg3 == 3) {
                            i = 2;
                        } else if (zzg3 == 4) {
                            i = 3;
                        } else {
                            throw new GeneralSecurityException("Unrecognized NIST HPKE KEM identifier");
                        }
                    }
                    zzoe = zzon.zza(zzt, zzt2, i);
                } else {
                    throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
                }
                return new zzoa(zzoe, zzb2, zzc3, zza2, 32, (byte[]) null);
            }
            throw new IllegalArgumentException("Unable to determine KEM-encoding length for ".concat(zzux.zza(zzg)));
        } else {
            throw new IllegalArgumentException("HpkePrivateKey.private_key is empty.");
        }
    }
}
