package com.google.android.recaptcha.internal;

import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzex {
    public static final zzrn zza(zzbp zzbp, zzbp zzbp2) {
        zzrl zzf = zzrn.zzf();
        zzf.zzq(zzqd.zzb(zzbp.zzb()));
        zzf.zzr(zzqb.zza(zzbp.zza(TimeUnit.NANOSECONDS)));
        zzf.zze(zzqd.zzb(zzbp2.zzb()));
        zzf.zzf(zzqb.zza(zzbp2.zza(TimeUnit.NANOSECONDS)));
        return (zzrn) zzf.zzk();
    }
}
