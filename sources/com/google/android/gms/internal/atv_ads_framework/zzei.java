package com.google.android.gms.internal.atv_ads_framework;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzei extends LinkedHashMap {
    private static final zzei zza;
    private boolean zzb = true;

    static {
        zzei zzei = new zzei();
        zza = zzei;
        zzei.zzb = false;
    }

    private zzei() {
    }

    private static int zze(Object obj) {
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            byte[] bArr2 = zzdp.zzd;
            int length = bArr.length;
            int zzb2 = zzdp.zzb(length, bArr, 0, length);
            if (zzb2 == 0) {
                return 1;
            }
            return zzb2;
        } else if (!(obj instanceof zzdj)) {
            return obj.hashCode();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private final void zzf() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        zzf();
        super.clear();
    }

    public final Set entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        for (Map.Entry entry : entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            Object value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(value instanceof byte[]) || !(obj2 instanceof byte[])) {
                z = value.equals(obj2);
                continue;
            } else {
                z = Arrays.equals((byte[]) value, (byte[]) obj2);
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 0;
        for (Map.Entry entry : entrySet()) {
            i += zze(entry.getValue()) ^ zze(entry.getKey());
        }
        return i;
    }

    public final Object put(Object obj, Object obj2) {
        zzf();
        byte[] bArr = zzdp.zzd;
        obj.getClass();
        obj2.getClass();
        return super.put(obj, obj2);
    }

    public final void putAll(Map map) {
        zzf();
        for (Object next : map.keySet()) {
            byte[] bArr = zzdp.zzd;
            next.getClass();
            map.get(next).getClass();
        }
        super.putAll(map);
    }

    public final Object remove(Object obj) {
        zzf();
        return super.remove(obj);
    }

    public final zzei zza() {
        return isEmpty() ? new zzei() : new zzei(this);
    }

    public final void zzb() {
        this.zzb = false;
    }

    public final void zzc(zzei zzei) {
        zzf();
        if (!zzei.isEmpty()) {
            putAll(zzei);
        }
    }

    public final boolean zzd() {
        return this.zzb;
    }

    private zzei(Map map) {
        super(map);
    }
}
