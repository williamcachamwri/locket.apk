package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzli {
    public static final String zza = "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    public static final String zzb = "type.googleapis.com/google.crypto.tink.AesGcmKey";
    @Deprecated
    public static final zzwx zzc;
    @Deprecated
    public static final zzwx zzd;
    @Deprecated
    public static final zzwx zze;

    static {
        new zzlo();
        new zzlx();
        new zzma();
        new zzlu();
        new zzmg();
        new zzmk();
        new zzmd();
        new zzmn();
        zzwx zzc2 = zzwx.zzc();
        zzc = zzc2;
        zzd = zzc2;
        zze = zzc2;
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzlf.zzo(new zzll());
        zzqs.zza();
        zzlf.zzn(new zzlo(), true);
        zzlf.zzn(new zzlx(), true);
        if (!zznb.zzb()) {
            zzlf.zzn(new zzlu(), true);
            zzma.zzg(true);
            zzlf.zzn(new zzmd(), true);
            zzlf.zzn(new zzmg(), true);
            zzlf.zzn(new zzmk(), true);
            zzlf.zzn(new zzmn(), true);
        }
    }
}
