package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzom implements zzoc {
    private final zznx zza;
    private final int zzb;

    private zzom(zznx zznx, int i) {
        this.zza = zznx;
        this.zzb = i;
    }

    static zzom zzc(int i) throws GeneralSecurityException {
        int i2 = i - 1;
        if (i2 == 0) {
            return new zzom(new zznx("HmacSha256"), 1);
        }
        if (i2 != 1) {
            return new zzom(new zznx("HmacSha512"), 3);
        }
        return new zzom(new zznx("HmacSha384"), 2);
    }

    public final zzod zza(byte[] bArr) throws GeneralSecurityException {
        KeyPair zzc = zzxx.zzc(zzxx.zzk(this.zzb));
        byte[] zzg = zzxx.zzg((ECPrivateKey) zzc.getPrivate(), zzxx.zzj(zzxx.zzk(this.zzb), 1, bArr));
        int i = this.zzb;
        byte[] zzl = zzxx.zzl(zzxx.zzk(i).getCurve(), 1, ((ECPublicKey) zzc.getPublic()).getW());
        byte[] zzc2 = zzxo.zzc(zzl, bArr);
        byte[] zzd = zzol.zzd(zzb());
        zznx zznx = this.zza;
        return new zzod(zznx.zzb((byte[]) null, zzg, "eae_prk", zzc2, "shared_secret", zzd, zznx.zza()), zzl);
    }

    public final byte[] zzb() throws GeneralSecurityException {
        int i = this.zzb - 1;
        if (i == 0) {
            return zzol.zzc;
        }
        if (i != 1) {
            return zzol.zze;
        }
        return zzol.zzd;
    }
}
