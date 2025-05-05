package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
abstract class zzdb extends zzdr {
    zzdb() {
    }

    public final void clear() {
        zza().clear();
    }

    public boolean contains(@CheckForNull Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object zza = zzdf.zza(zza(), key);
        if (!zzax.zza(zza, entry.getValue())) {
            return false;
        }
        if (zza != null) {
            return true;
        }
        if (!zzdf.zzb(((zzbi) zza()).zza, key)) {
            return false;
        }
        return true;
    }

    public final boolean isEmpty() {
        return zza().isEmpty();
    }

    public boolean remove(@CheckForNull Object obj) {
        if (!contains(obj) || !(obj instanceof Map.Entry)) {
            return false;
        }
        return ((zzbi) zza()).zzb.zzw().remove(((Map.Entry) obj).getKey());
    }

    public final int size() {
        return zza().size();
    }

    /* access modifiers changed from: package-private */
    public abstract Map zza();

    public final boolean retainAll(Collection collection) {
        int i;
        collection.getClass();
        try {
            Collection collection2 = collection;
            return super.retainAll(collection);
        } catch (UnsupportedOperationException unused) {
            int size = collection.size();
            if (size < 3) {
                zzby.zza(size, "expectedSize");
                i = size + 1;
            } else {
                i = size < 1073741824 ? (int) Math.ceil(((double) size) / 0.75d) : Integer.MAX_VALUE;
            }
            HashSet hashSet = new HashSet(i);
            for (Object next : collection) {
                if (contains(next) && (next instanceof Map.Entry)) {
                    hashSet.add(((Map.Entry) next).getKey());
                }
            }
            return ((zzbi) zza()).zzb.zzw().retainAll(hashSet);
        }
    }

    public final boolean removeAll(Collection collection) {
        collection.getClass();
        try {
            Collection collection2 = collection;
            return zzds.zzc(this, collection);
        } catch (UnsupportedOperationException unused) {
            return zzds.zzd(this, collection.iterator());
        }
    }
}
