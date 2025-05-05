package com.google.android.gms.internal.pal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzadm extends zzabj implements RandomAccess, zzadn {
    public static final zzadn zza;
    private static final zzadm zzb;
    private final List zzc;

    static {
        zzadm zzadm = new zzadm(10);
        zzb = zzadm;
        zzadm.zzb();
        zza = zzadm;
    }

    public zzadm() {
        this(10);
    }

    private static String zzj(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzaby) {
            return ((zzaby) obj).zzr(zzadg.zzb);
        }
        return zzadg.zzh((byte[]) obj);
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection collection) {
        zza();
        if (collection instanceof zzadn) {
            collection = ((zzadn) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object remove = this.zzc.remove(i);
        this.modCount++;
        return zzj(remove);
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzj(this.zzc.set(i, (String) obj));
    }

    public final int size() {
        return this.zzc.size();
    }

    public final /* bridge */ /* synthetic */ zzadf zzd(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzc);
            return new zzadm(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final zzadn zze() {
        return zzc() ? new zzafn(this) : this;
    }

    public final Object zzf(int i) {
        return this.zzc.get(i);
    }

    /* renamed from: zzg */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzaby) {
            zzaby zzaby = (zzaby) obj;
            String zzr = zzaby.zzr(zzadg.zzb);
            if (zzaby.zzk()) {
                this.zzc.set(i, zzr);
            }
            return zzr;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzadg.zzh(bArr);
        if (zzadg.zzi(bArr)) {
            this.zzc.set(i, zzh);
        }
        return zzh;
    }

    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public final void zzi(zzaby zzaby) {
        zza();
        this.zzc.add(zzaby);
        this.modCount++;
    }

    public zzadm(int i) {
        this.zzc = new ArrayList(i);
    }

    private zzadm(ArrayList arrayList) {
        this.zzc = arrayList;
    }

    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
