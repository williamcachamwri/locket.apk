package com.google.ads.interactivemedia.v3.internal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzrb extends zzre {
    final /* synthetic */ zzrf zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzrb(zzrf zzrf) {
        super(zzrf);
        this.zza = zzrf;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.containsKey(obj);
    }

    public final boolean remove(@CheckForNull Object obj) {
        zzrf zzrf = this.zza;
        int zzc = zzrg.zzc(obj);
        int zzc2 = zzrf.zzc(obj, zzc);
        if (zzc2 == -1) {
            return false;
        }
        this.zza.zzl(zzc2, zzc);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final Object zza(int i) {
        return this.zza.zza[i];
    }
}
