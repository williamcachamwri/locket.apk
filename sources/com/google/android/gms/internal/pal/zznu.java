package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zznu implements zzxr {
    private final String zza;
    private final int zzb;
    private zzst zzc;
    private zzrv zzd;
    private int zze;
    private zztf zzf;

    zznu(zzvt zzvt) throws GeneralSecurityException {
        String zzf2 = zzvt.zzf();
        this.zza = zzf2;
        if (zzf2.equals(zzli.zzb)) {
            try {
                zzsw zze2 = zzsw.zze(zzvt.zze(), zzacm.zza());
                this.zzc = (zzst) zzlf.zzd(zzvt);
                this.zzb = zze2.zza();
            } catch (zzadi e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        } else if (zzf2.equals(zzli.zza)) {
            try {
                zzry zzd2 = zzry.zzd(zzvt.zze(), zzacm.zza());
                this.zzd = (zzrv) zzlf.zzd(zzvt);
                this.zze = zzd2.zze().zza();
                this.zzb = this.zze + zzd2.zzf().zza();
            } catch (zzadi e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e2);
            }
        } else if (zzf2.equals(zznf.zza)) {
            try {
                zzti zze3 = zzti.zze(zzvt.zze(), zzacm.zza());
                this.zzf = (zztf) zzlf.zzd(zzvt);
                this.zzb = zze3.zza();
            } catch (zzadi e3) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e3);
            }
        } else {
            throw new GeneralSecurityException("unsupported AEAD DEM key type: ".concat(String.valueOf(zzf2)));
        }
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzoq zzb(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != this.zzb) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        } else if (this.zza.equals(zzli.zzb)) {
            zzss zzc2 = zzst.zzc();
            zzc2.zzal(this.zzc);
            zzc2.zza(zzaby.zzo(bArr, 0, this.zzb));
            return new zzoq((zzjt) zzlf.zzh(this.zza, (zzst) zzc2.zzan(), zzjt.class));
        } else if (this.zza.equals(zzli.zza)) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.zze);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.zze, this.zzb);
            zzsa zzc3 = zzsb.zzc();
            zzc3.zzal(this.zzd.zzf());
            zzc3.zza(zzaby.zzn(copyOfRange));
            zzuo zzc4 = zzup.zzc();
            zzc4.zzal(this.zzd.zzg());
            zzc4.zza(zzaby.zzn(copyOfRange2));
            zzru zzc5 = zzrv.zzc();
            zzc5.zzc(this.zzd.zza());
            zzc5.zza((zzsb) zzc3.zzan());
            zzc5.zzb((zzup) zzc4.zzan());
            return new zzoq((zzjt) zzlf.zzh(this.zza, (zzrv) zzc5.zzan(), zzjt.class));
        } else if (this.zza.equals(zznf.zza)) {
            zzte zzc6 = zztf.zzc();
            zzc6.zzal(this.zzf);
            zzc6.zza(zzaby.zzo(bArr, 0, this.zzb));
            return new zzoq((zzjw) zzlf.zzh(this.zza, (zztf) zzc6.zzan(), zzjw.class));
        } else {
            throw new GeneralSecurityException("unknown DEM key type");
        }
    }
}
