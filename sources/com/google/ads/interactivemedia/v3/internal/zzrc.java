package com.google.ads.interactivemedia.v3.internal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzrc extends zzre {
    final /* synthetic */ zzrf zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzrc(zzrf zzrf) {
        super(zzrf);
        this.zza = zzrf;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.containsValue(obj);
    }

    public final boolean remove(@CheckForNull Object obj) {
        zzrf zzrf = this.zza;
        int zzc = zzrg.zzc(obj);
        int zzd = zzrf.zzd(obj, zzc);
        if (zzd == -1) {
            return false;
        }
        this.zza.zzm(zzd, zzc);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final Object zza(int i) {
        return this.zza.zzb[i];
    }
}
