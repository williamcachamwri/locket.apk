package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzof {
    static zzny zza(zzvd zzvd) throws GeneralSecurityException {
        if (zzvd.zze() == 3) {
            return new zznv(16);
        }
        if (zzvd.zze() == 4) {
            return new zznv(32);
        }
        if (zzvd.zze() == 5) {
            return new zznw();
        }
        throw new IllegalArgumentException("Unrecognized HPKE AEAD identifier");
    }

    static zzoc zzb(zzvd zzvd) throws GeneralSecurityException {
        if (zzvd.zzg() == 3) {
            return new zzoo(new zznx("HmacSha256"));
        }
        if (zzvd.zzg() == 4) {
            return zzom.zzc(1);
        }
        if (zzvd.zzg() == 5) {
            return zzom.zzc(2);
        }
        if (zzvd.zzg() == 6) {
            return zzom.zzc(3);
        }
        throw new IllegalArgumentException("Unrecognized HPKE KEM identifier");
    }

    static zznx zzc(zzvd zzvd) {
        if (zzvd.zzf() == 3) {
            return new zznx("HmacSha256");
        }
        if (zzvd.zzf() == 4) {
            return new zznx("HmacSha384");
        }
        if (zzvd.zzf() == 5) {
            return new zznx("HmacSha512");
        }
        throw new IllegalArgumentException("Unrecognized HPKE KDF identifier");
    }
}
