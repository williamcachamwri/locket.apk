package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzqo {
    public static final /* synthetic */ int zza = 0;
    private static final zzyv zzb;
    private static final zzpp zzc = zzpp.zzc(zzqk.zza, zzqj.class, zzpt.class);
    private static final zzpm zzd;
    private static final zzox zze = zzox.zzc(zzqm.zza, zzqe.class, zzps.class);
    private static final zzou zzf;

    static {
        zzyv zza2 = zzqd.zza("type.googleapis.com/google.crypto.tink.AesCmacKey");
        zzb = zza2;
        zzd = zzpm.zzc(zzql.zza, zza2, zzpt.class);
        zzf = zzou.zzb(zzqn.zza, zza2, zzps.class);
    }

    public static void zza() throws GeneralSecurityException {
        zzpj zzb2 = zzpj.zzb();
        zzb2.zzf(zzc);
        zzb2.zze(zzd);
        zzb2.zzd(zze);
        zzb2.zzc(zzf);
    }
}
