package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzml {
    private final String zza;
    private final Object zzb;
    private final int zzc;

    protected zzml(String str, Object obj, int i) {
        this.zza = str;
        this.zzb = obj;
        this.zzc = i;
    }

    public static zzml zza(String str, long j) {
        return new zzml(str, Long.valueOf(j), 2);
    }

    public static zzml zzb(String str, boolean z) {
        return new zzml(str, Boolean.valueOf(z), 1);
    }

    public final Object zzc() {
        zzmq zza2 = zzms.zza();
        if (zza2 == null) {
            if (zzms.zzb() != null) {
                zzms.zzb().zza();
            }
            return this.zzb;
        } else if (this.zzc - 1 != 0) {
            return zza2.zzb(this.zza, ((Long) this.zzb).longValue());
        } else {
            return zza2.zza(this.zza, ((Boolean) this.zzb).booleanValue());
        }
    }
}
