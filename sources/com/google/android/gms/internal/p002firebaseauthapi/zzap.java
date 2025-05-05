package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.io.FileUtils;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzap  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzap<K, V> implements Serializable, Map<K, V> {
    @CheckForNull
    private transient zzau<Map.Entry<K, V>> zza;
    @CheckForNull
    private transient zzau<K> zzb;
    @CheckForNull
    private transient zzak<V> zzc;

    @CheckForNull
    public abstract V get(@CheckForNull Object obj);

    public int hashCode() {
        return zzaz.zza((zzau) entrySet());
    }

    /* access modifiers changed from: package-private */
    public abstract zzak<V> zza();

    /* access modifiers changed from: package-private */
    public abstract zzau<Map.Entry<K, V>> zzb();

    /* access modifiers changed from: package-private */
    public abstract zzau<K> zzc();

    /* access modifiers changed from: package-private */
    public abstract boolean zzd();

    public static <K, V> zzap<K, V> zza(Map<? extends K, ? extends V> map) {
        if (!(map instanceof zzap) || (map instanceof SortedMap)) {
            Set<Map.Entry<? extends K, ? extends V>> entrySet = map.entrySet();
            zzas zzas = new zzas(entrySet instanceof Collection ? entrySet.size() : 4);
            zzas.zza(entrySet);
            return zzas.zza();
        }
        zzap<K, V> zzap = (zzap) map;
        zzap.zzd();
        return zzap;
    }

    @CheckForNull
    public final V getOrDefault(@CheckForNull Object obj, @CheckForNull V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    @CheckForNull
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    @Deprecated
    public final V remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        int size = size();
        zzai.zza(size, "size");
        StringBuilder append = new StringBuilder((int) Math.min(((long) size) << 3, FileUtils.ONE_GB)).append(AbstractJsonLexerKt.BEGIN_OBJ);
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                append.append(", ");
            }
            append.append(entry.getKey()).append('=').append(entry.getValue());
            z = false;
        }
        return append.append(AbstractJsonLexerKt.END_OBJ).toString();
    }

    public /* synthetic */ Collection values() {
        zzak<V> zzak = this.zzc;
        if (zzak != null) {
            return zzak;
        }
        zzak<V> zza2 = zza();
        this.zzc = zza2;
        return zza2;
    }

    public /* synthetic */ Set entrySet() {
        zzau<Map.Entry<K, V>> zzau = this.zza;
        if (zzau != null) {
            return zzau;
        }
        zzau<Map.Entry<K, V>> zzb2 = zzb();
        this.zza = zzb2;
        return zzb2;
    }

    public /* synthetic */ Set keySet() {
        zzau<K> zzau = this.zzb;
        if (zzau != null) {
            return zzau;
        }
        zzau<K> zzc2 = zzc();
        this.zzb = zzc2;
        return zzc2;
    }

    zzap() {
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(@CheckForNull Object obj) {
        return ((zzak) values()).contains(obj);
    }

    public boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
