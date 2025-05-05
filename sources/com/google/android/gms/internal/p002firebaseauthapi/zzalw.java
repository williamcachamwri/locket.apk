package com.google.android.gms.internal.p002firebaseauthapi;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
class zzalw<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    /* access modifiers changed from: private */
    public Object[] zza;
    /* access modifiers changed from: private */
    public int zzb;
    /* access modifiers changed from: private */
    public Map<K, V> zzc;
    private boolean zzd;
    private volatile zzamc zze;
    /* access modifiers changed from: private */
    public Map<K, V> zzf;

    private final int zza(K k) {
        int i = this.zzb - 1;
        if (i >= 0) {
            int compareTo = k.compareTo((Comparable) ((zzama) this.zza[i]).getKey());
            if (compareTo > 0) {
                return -(i + 2);
            }
            if (compareTo == 0) {
                return i;
            }
        }
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) / 2;
            int compareTo2 = k.compareTo((Comparable) ((zzama) this.zza[i3]).getKey());
            if (compareTo2 < 0) {
                i = i3 - 1;
            } else if (compareTo2 <= 0) {
                return i3;
            } else {
                i2 = i3 + 1;
            }
        }
        return -(i2 + 1);
    }

    public final int zza() {
        return this.zzb;
    }

    public int hashCode() {
        int i = this.zzb;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += this.zza[i3].hashCode();
        }
        return this.zzc.size() > 0 ? i2 + this.zzc.hashCode() : i2;
    }

    public int size() {
        return this.zzb + this.zzc.size();
    }

    public final Iterable<Map.Entry<K, V>> zzb() {
        if (this.zzc.isEmpty()) {
            return Collections.emptySet();
        }
        return this.zzc.entrySet();
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza2 = zza(comparable);
        if (zza2 >= 0) {
            return ((zzama) this.zza[zza2]).getValue();
        }
        return this.zzc.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzg();
        int zza2 = zza(k);
        if (zza2 >= 0) {
            return ((zzama) this.zza[zza2]).setValue(v);
        }
        zzg();
        if (this.zza == null) {
            this.zza = new Object[16];
        }
        int i = -(zza2 + 1);
        if (i >= 16) {
            return zzf().put(k, v);
        }
        int i2 = this.zzb;
        if (i2 == 16) {
            zzama zzama = (zzama) this.zza[15];
            this.zzb = i2 - 1;
            zzf().put((Comparable) zzama.getKey(), zzama.getValue());
        }
        Object[] objArr = this.zza;
        System.arraycopy(objArr, i, objArr, i + 1, (objArr.length - i) - 1);
        this.zza[i] = new zzama(this, k, v);
        this.zzb++;
        return null;
    }

    public V remove(Object obj) {
        zzg();
        Comparable comparable = (Comparable) obj;
        int zza2 = zza(comparable);
        if (zza2 >= 0) {
            return zzb(zza2);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    /* access modifiers changed from: private */
    public final V zzb(int i) {
        zzg();
        V value = ((zzama) this.zza[i]).getValue();
        Object[] objArr = this.zza;
        System.arraycopy(objArr, i + 1, objArr, i, (this.zzb - i) - 1);
        this.zzb--;
        if (!this.zzc.isEmpty()) {
            Iterator it = zzf().entrySet().iterator();
            this.zza[this.zzb] = new zzama(this, (Map.Entry) it.next());
            this.zzb++;
            it.remove();
        }
        return value;
    }

    public final Map.Entry<K, V> zza(int i) {
        if (i < this.zzb) {
            return (zzama) this.zza[i];
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    /* access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzc() {
        return new zzamb(this);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zze == null) {
            this.zze = new zzamc(this);
        }
        return this.zze;
    }

    private final SortedMap<K, V> zzf() {
        zzg();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            TreeMap treeMap2 = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    private zzalw() {
        this.zzc = Collections.emptyMap();
        this.zzf = Collections.emptyMap();
    }

    /* access modifiers changed from: private */
    public final void zzg() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    public void clear() {
        zzg();
        if (this.zzb != 0) {
            this.zza = null;
            this.zzb = 0;
        }
        if (!this.zzc.isEmpty()) {
            this.zzc.clear();
        }
    }

    public void zzd() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzd) {
            if (this.zzc.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzc);
            }
            this.zzc = map;
            if (this.zzf.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zzf);
            }
            this.zzf = map2;
            this.zzd = true;
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzalw)) {
            return super.equals(obj);
        }
        zzalw zzalw = (zzalw) obj;
        int size = size();
        if (size != zzalw.size()) {
            return false;
        }
        int i = this.zzb;
        if (i != zzalw.zzb) {
            return entrySet().equals(zzalw.entrySet());
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (!zza(i2).equals(zzalw.zza(i2))) {
                return false;
            }
        }
        if (i != size) {
            return this.zzc.equals(zzalw.zzc);
        }
        return true;
    }

    public final boolean zze() {
        return this.zzd;
    }
}
