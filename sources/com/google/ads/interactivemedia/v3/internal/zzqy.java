package com.google.ads.interactivemedia.v3.internal;

import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzqy extends zzre {
    final /* synthetic */ zzrf zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzqy(zzrf zzrf) {
        super(zzrf);
        this.zza = zzrf;
    }

    public final boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            int zzc = this.zza.zzc(key, zzrg.zzc(key));
            if (zzc == -1 || !zzqe.zza(value, this.zza.zzb[zzc])) {
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
        int zzc2 = this.zza.zzc(key, zzc);
        if (zzc2 == -1 || !zzqe.zza(value, this.zza.zzb[zzc2])) {
            return false;
        }
        this.zza.zzl(zzc2, zzc);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zza(int i) {
        return new zzqw(this.zza, i);
    }
}
