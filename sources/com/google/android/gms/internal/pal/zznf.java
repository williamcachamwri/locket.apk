package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zznf {
    public static final String zza = "type.googleapis.com/google.crypto.tink.AesSivKey";
    @Deprecated
    public static final zzwx zzb = zzwx.zzc();
    @Deprecated
    public static final zzwx zzc = zzwx.zzc();

    static {
        new zzne();
        try {
            zzlf.zzo(new zznh());
            if (!zznb.zzb()) {
                zzlf.zzn(new zzne(), true);
            }
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
