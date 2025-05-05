package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzma extends zzpa {
    zzma() {
        super(zzsz.class, new zzly(zzjt.class));
    }

    public static void zzg(boolean z) throws GeneralSecurityException {
        if (zzm()) {
            zzlf.zzn(new zzma(), true);
        }
    }

    static /* bridge */ /* synthetic */ zzoy zzh(int i, int i2) {
        zztb zzc = zztc.zzc();
        zzc.zza(i);
        return new zzoy((zztc) zzc.zzan(), i2);
    }

    private static boolean zzm() {
        try {
            Cipher.getInstance("AES/GCM-SIV/NoPadding");
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return false;
        }
    }

    public final zzoz zza() {
        return new zzlz(this, zztc.class);
    }

    public final zzvn zzb() {
        return zzvn.SYMMETRIC;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzsz.zze(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesGcmSivKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzsz zzsz = (zzsz) zzaef;
        zzys.zzb(zzsz.zza(), 0);
        zzys.zza(zzsz.zzf().zzd());
    }
}
