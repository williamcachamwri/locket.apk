package com.google.android.gms.internal.mlkit_vision_barcode;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
abstract class zzbr extends zzbv implements Serializable {
    /* access modifiers changed from: private */
    public final transient Map zza;
    /* access modifiers changed from: private */
    public transient int zzb;

    protected zzbr(Map map) {
        zzaz.zzd(map.isEmpty());
        this.zza = map;
    }

    static /* bridge */ /* synthetic */ void zzr(zzbr zzbr, Object obj) {
        Object obj2;
        try {
            obj2 = zzbr.zza.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            zzbr.zzb -= size;
        }
    }

    /* access modifiers changed from: package-private */
    public abstract Collection zza();

    /* access modifiers changed from: package-private */
    public Collection zzb() {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public Collection zzc(Collection collection) {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public Collection zzd(Object obj, Collection collection) {
        throw null;
    }

    public final int zzh() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final Collection zzi() {
        if (this instanceof zzdq) {
            return new zzbu(this);
        }
        return new zzbt(this);
    }

    public final Collection zzj(Object obj) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection == null) {
            collection = zza();
        }
        return zzd(obj, collection);
    }

    public final Collection zzk(@CheckForNull Object obj) {
        Collection collection = (Collection) this.zza.remove(obj);
        if (collection == null) {
            return zzb();
        }
        Collection zza2 = zza();
        zza2.addAll(collection);
        this.zzb -= collection.size();
        collection.clear();
        return zzc(zza2);
    }

    /* access modifiers changed from: package-private */
    public final Iterator zzl() {
        return new zzbf(this);
    }

    /* access modifiers changed from: package-private */
    public final List zzm(Object obj, List list, @CheckForNull zzbo zzbo) {
        if (list instanceof RandomAccess) {
            return new zzbm(this, obj, list, zzbo);
        }
        return new zzbq(this, obj, list, zzbo);
    }

    /* access modifiers changed from: package-private */
    public final Map zzo() {
        return new zzbi(this, this.zza);
    }

    /* access modifiers changed from: package-private */
    public final Set zzp() {
        return new zzbl(this, this.zza);
    }

    public final void zzs() {
        for (Collection clear : this.zza.values()) {
            clear.clear();
        }
        this.zza.clear();
        this.zzb = 0;
    }

    public final boolean zzt(Object obj, Object obj2) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection == null) {
            Collection zza2 = zza();
            if (zza2.add(obj2)) {
                this.zzb++;
                this.zza.put(obj, zza2);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(obj2)) {
            return false;
        } else {
            this.zzb++;
            return true;
        }
    }
}
