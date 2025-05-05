package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzea extends zzec {
    private zzea() {
        super((zzeb) null);
    }

    /* synthetic */ zzea(zzdz zzdz) {
        super((zzeb) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        ((zzdo) zzfz.zzf(obj, j)).zzb();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, Object obj2, long j) {
        zzdo zzdo = (zzdo) zzfz.zzf(obj, j);
        zzdo zzdo2 = (zzdo) zzfz.zzf(obj2, j);
        int size = zzdo.size();
        int size2 = zzdo2.size();
        if (size > 0 && size2 > 0) {
            if (!zzdo.zzc()) {
                zzdo = zzdo.zzg(size2 + size);
            }
            zzdo.addAll(zzdo2);
        }
        if (size > 0) {
            zzdo2 = zzdo;
        }
        zzfz.zzs(obj, j, zzdo2);
    }
}
