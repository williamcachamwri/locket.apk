package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzgm {
    private final String zza;
    private final Object zzb;

    protected zzgm(String str, Object obj, int i) {
        this.zza = str;
        this.zzb = obj;
    }

    public static zzgm zza(String str, boolean z) {
        return new zzgm(str, Boolean.valueOf(z), 1);
    }

    public final Object zzb() {
        zzgp zza2 = zzgr.zza();
        if (zza2 != null) {
            return zza2.zza(this.zza, ((Boolean) this.zzb).booleanValue());
        }
        if (zzgr.zzb() != null) {
            zzgr.zzb().zza();
        }
        return this.zzb;
    }
}
