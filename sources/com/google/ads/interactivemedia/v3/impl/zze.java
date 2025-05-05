package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.internal.zzqe;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zze implements AdEvent {
    private final AdEvent.AdEventType zza;
    private final Ad zzb;
    private final Map zzc;
    private final AdProgressInfo zzd;

    zze(AdEvent.AdEventType adEventType, Ad ad, Map map, AdProgressInfo adProgressInfo) {
        this.zza = adEventType;
        this.zzb = ad;
        this.zzc = map;
        this.zzd = adProgressInfo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zze)) {
            return false;
        }
        zze zze = (zze) obj;
        return this.zza == zze.zza && zzqe.zza(this.zzb, zze.zzb) && zzqe.zza(this.zzc, zze.zzc) && zzqe.zza(this.zzd, zze.zzd);
    }

    public final Ad getAd() {
        return this.zzb;
    }

    public final Map<String, String> getAdData() {
        return this.zzc;
    }

    public final AdEvent.AdEventType getType() {
        return this.zza;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd});
    }

    public final String toString() {
        String str;
        String format = String.format("AdEvent[type=%s, ad=%s, adProgressInfo=%s", new Object[]{this.zza, this.zzb, this.zzd});
        if (this.zzc == null) {
            str = "]";
        } else {
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder("{");
            Iterator it = this.zzc.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                sb.append((String) entry.getKey());
                sb.append(": ");
                sb.append((String) entry.getValue());
                if (it.hasNext()) {
                    sb.append(", ");
                }
            }
            sb.append("}");
            objArr[0] = sb.toString();
            str = String.format(", adData=%s]", objArr);
        }
        return String.valueOf(format).concat(String.valueOf(str));
    }
}
