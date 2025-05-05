package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public final class zzdo extends zzdr {
    private final int zza;
    private final zzbg zzb;

    zzdo(zzbg zzbg) throws zzdh {
        zzbg.getClass();
        this.zzb = zzbg;
        zzcb zzd = zzbg.entrySet().iterator();
        int i = 0;
        while (zzd.hasNext()) {
            Map.Entry entry = (Map.Entry) zzd.next();
            int zzb2 = ((zzdr) entry.getKey()).zzb();
            i = i < zzb2 ? zzb2 : i;
            int zzb3 = ((zzdr) entry.getValue()).zzb();
            if (i < zzb3) {
                i = zzb3;
            }
        }
        int i2 = i + 1;
        this.zza = i2;
        if (i2 > 4) {
            throw new zzdh("Exceeded cutoff limit for max depth of cbor value");
        }
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        int compareTo;
        int size;
        int size2;
        zzdr zzdr = (zzdr) obj;
        if (zzd((byte) -96) != zzdr.zza()) {
            size2 = zzdr.zza();
            size = zzd((byte) -96);
        } else {
            zzdo zzdo = (zzdo) zzdr;
            if (this.zzb.size() != zzdo.zzb.size()) {
                size = this.zzb.size();
                size2 = zzdo.zzb.size();
            } else {
                zzcb zzd = this.zzb.entrySet().iterator();
                zzcb zzd2 = zzdo.zzb.entrySet().iterator();
                do {
                    if (!zzd.hasNext() && !zzd2.hasNext()) {
                        return 0;
                    }
                    Map.Entry entry = (Map.Entry) zzd.next();
                    Map.Entry entry2 = (Map.Entry) zzd2.next();
                    int compareTo2 = ((zzdr) entry.getKey()).compareTo((zzdr) entry2.getKey());
                    if (compareTo2 != 0) {
                        return compareTo2;
                    }
                    compareTo = ((zzdr) entry.getValue()).compareTo((zzdr) entry2.getValue());
                } while (compareTo == 0);
                return compareTo;
            }
        }
        return size - size2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.zzb.equals(((zzdo) obj).zzb);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzd((byte) -96)), this.zzb});
    }

    public final String toString() {
        if (this.zzb.isEmpty()) {
            return "{}";
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        zzcb zzd = this.zzb.entrySet().iterator();
        while (zzd.hasNext()) {
            Map.Entry entry = (Map.Entry) zzd.next();
            linkedHashMap.put(((zzdr) entry.getKey()).toString().replace("\n", "\n  "), ((zzdr) entry.getValue()).toString().replace("\n", "\n  "));
        }
        zzag zza2 = zzag.zza(",\n  ");
        StringBuilder sb = new StringBuilder("{\n  ");
        try {
            zzaf.zza(sb, linkedHashMap.entrySet().iterator(), zza2, " : ");
            sb.append("\n}");
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        return zzd((byte) -96);
    }

    /* access modifiers changed from: protected */
    public final int zzb() {
        return this.zza;
    }

    public final zzbg zzc() {
        return this.zzb;
    }
}
