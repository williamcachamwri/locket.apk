package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
class zzamc extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzalw zza;

    public int size() {
        return this.zza.size();
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new zzamd(this.zza);
    }

    private zzamc(zzalw zzalw) {
        this.zza = zzalw;
    }

    public void clear() {
        this.zza.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zza.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zza.get(entry.getKey());
        Object value = entry.getValue();
        if (obj2 != value) {
            return obj2 != null && obj2.equals(value);
        }
        return true;
    }

    public boolean remove(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zza.remove(entry.getKey());
        return true;
    }
}
