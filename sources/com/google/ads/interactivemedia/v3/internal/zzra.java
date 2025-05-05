package com.google.ads.interactivemedia.v3.internal;

import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzra extends zzre {
    zzra(zzrf zzrf) {
        super(zzrf);
    }

    public final boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int zzd = this.zzb.zzd(key, zzrg.zzc(key));
            if (zzd == -1 || !zzqe.zza(this.zzb.zza[zzd], value)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final boolean remove(@CheckForNull Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        int zzc = zzrg.zzc(key);
        int zzd = this.zzb.zzd(key, zzc);
        if (zzd == -1 || !zzqe.zza(this.zzb.zza[zzd], value)) {
            return false;
        }
        this.zzb.zzm(zzd, zzc);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zza(int i) {
        return new zzqx(this.zzb, i);
    }
}
