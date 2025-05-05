package com.google.android.gms.internal.pal;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.io.FileUtils;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzjc implements Map, Serializable {
    @CheckForNull
    private transient zzjd zza;
    @CheckForNull
    private transient zzjd zzb;
    @CheckForNull
    private transient zziw zzc;

    zzjc() {
    }

    public static zzjc zzc() {
        return zzjj.zza;
    }

    public static zzjc zzd(Object obj, Object obj2) {
        zziu.zza(obj, obj2);
        return zzjj.zzk(1, new Object[]{obj, obj2}, (zzjb) null);
    }

    public static zzjc zze(Object obj, Object obj2, Object obj3, Object obj4) {
        zziu.zza(obj, obj2);
        zziu.zza(obj3, obj4);
        return zzjj.zzk(2, new Object[]{obj, obj2, obj3, obj4}, (zzjb) null);
    }

    public static zzjc zzf(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        zziu.zza(obj, obj2);
        zziu.zza(obj3, obj4);
        zziu.zza(obj5, obj6);
        return zzjj.zzk(3, new Object[]{obj, obj2, obj3, obj4, obj5, obj6}, (zzjb) null);
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return entrySet().equals(((Map) obj).entrySet());
    }

    @CheckForNull
    public abstract Object get(@CheckForNull Object obj);

    @CheckForNull
    public final Object getOrDefault(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    public final int hashCode() {
        return zzjk.zza(entrySet());
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    @CheckForNull
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    @Deprecated
    public final Object remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, FileUtils.ONE_GB));
            sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
            boolean z = true;
            for (Map.Entry entry : entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                z = false;
            }
            sb.append(AbstractJsonLexerKt.END_OBJ);
            return sb.toString();
        }
        throw new IllegalArgumentException("size cannot be negative but was: " + size);
    }

    /* access modifiers changed from: package-private */
    public abstract zziw zza();

    /* renamed from: zzb */
    public final zziw values() {
        zziw zziw = this.zzc;
        if (zziw != null) {
            return zziw;
        }
        zziw zza2 = zza();
        this.zzc = zza2;
        return zza2;
    }

    /* access modifiers changed from: package-private */
    public abstract zzjd zzg();

    /* access modifiers changed from: package-private */
    public abstract zzjd zzh();

    /* renamed from: zzi */
    public final zzjd entrySet() {
        zzjd zzjd = this.zza;
        if (zzjd != null) {
            return zzjd;
        }
        zzjd zzg = zzg();
        this.zza = zzg;
        return zzg;
    }

    /* renamed from: zzj */
    public final zzjd keySet() {
        zzjd zzjd = this.zzb;
        if (zzjd != null) {
            return zzjd;
        }
        zzjd zzh = zzh();
        this.zzb = zzh;
        return zzh;
    }
}
