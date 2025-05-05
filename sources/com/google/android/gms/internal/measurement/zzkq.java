package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzkq implements zzlt {
    private static final zzkz zza = new zzkp();
    private final zzkz zzb;

    public final <T> zzlu<T> zza(Class<T> cls) {
        zzlw.zza((Class<?>) cls);
        zzla zza2 = this.zzb.zza(cls);
        if (zza2.zzc()) {
            return zzli.zza(zzlw.zza(), zzjj.zza(), zza2.zza());
        }
        zzlk zza3 = zzlm.zza();
        zzkm zza4 = zzko.zza();
        zzmk<?, ?> zza5 = zzlw.zza();
        boolean z = true;
        if (zzks.zza[zza2.zzb().ordinal()] == 1) {
            z = false;
        }
        return zzlg.zza(cls, zza2, zza3, zza4, zza5, z ? zzjj.zza() : null, zzkx.zza());
    }

    public zzkq() {
        this(new zzkr(zzju.zza(), zza));
    }

    private zzkq(zzkz zzkz) {
        this.zzb = (zzkz) zzjv.zza(zzkz, "messageInfoFactory");
    }
}
