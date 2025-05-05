package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final /* synthetic */ class zzqn implements zzos {
    public static final /* synthetic */ zzqn zza = new zzqn();

    private /* synthetic */ zzqn() {
    }

    public final zzka zza(zzpu zzpu, zzlg zzlg) {
        zzqi zzqi;
        int i = zzqo.zza;
        if (((zzps) zzpu).zze().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            try {
                zzrm zze = zzrm.zze(((zzps) zzpu).zzc(), zzacm.zza());
                if (zze.zza() == 0) {
                    zzrs zzf = zze.zzf();
                    int zzg = ((zzps) zzpu).zzg();
                    int zza2 = zzf.zza();
                    int i2 = zzg - 2;
                    if (i2 == 1) {
                        zzqi = zzqi.zza;
                    } else if (i2 == 2) {
                        zzqi = zzqi.zzc;
                    } else if (i2 == 3) {
                        zzqi = zzqi.zzd;
                    } else if (i2 == 4) {
                        zzqi = zzqi.zzb;
                    } else {
                        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zzwu.zza(zzg));
                    }
                    return zzqe.zzb(zzqj.zzb(zza2, zzqi), zzyw.zzb(zze.zzg().zzt(), zzlg), ((zzps) zzpu).zzd());
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (zzadi | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing AesCmacKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to AesCmacParameters.parseParameters");
        }
    }
}
