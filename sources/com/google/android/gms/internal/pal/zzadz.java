package com.google.android.gms.internal.pal;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzadz extends LinkedHashMap {
    private static final zzadz zza;
    private boolean zzb = true;

    static {
        zzadz zzadz = new zzadz();
        zza = zzadz;
        zzadz.zzb = false;
    }

    private zzadz() {
    }

    public static zzadz zza() {
        return zza;
    }

    private static int zzf(Object obj) {
        if (obj instanceof byte[]) {
            return zzadg.zzb((byte[]) obj);
        }
        if (!(obj instanceof zzadb)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    private final void zzg() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        zzg();
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
            i += zzf(entry.getValue()) ^ zzf(entry.getKey());
        }
        return i;
    }

    public final Object put(Object obj, Object obj2) {
        zzg();
        zzadg.zze(obj);
        zzadg.zze(obj2);
        return super.put(obj, obj2);
    }

    public final void putAll(Map map) {
        zzg();
        for (Object next : map.keySet()) {
            zzadg.zze(next);
            zzadg.zze(map.get(next));
        }
        super.putAll(map);
    }

    public final Object remove(Object obj) {
        zzg();
        return super.remove(obj);
    }

    public final zzadz zzb() {
        return isEmpty() ? new zzadz() : new zzadz(this);
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final void zzd(zzadz zzadz) {
        zzg();
        if (!zzadz.isEmpty()) {
            putAll(zzadz);
        }
    }

    public final boolean zze() {
        return this.zzb;
    }

    private zzadz(Map map) {
        super(map);
    }
}
