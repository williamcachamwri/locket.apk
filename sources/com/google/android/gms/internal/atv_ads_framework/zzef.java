package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzef implements zzem {
    private final zzem[] zza;

    zzef(zzem... zzemArr) {
        this.zza = zzemArr;
    }

    public final zzel zzb(Class cls) {
        zzem[] zzemArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzem zzem = zzemArr[i];
            if (zzem.zzc(cls)) {
                return zzem.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    public final boolean zzc(Class cls) {
        zzem[] zzemArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzemArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
