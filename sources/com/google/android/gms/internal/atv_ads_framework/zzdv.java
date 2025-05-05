package com.google.android.gms.internal.atv_ads_framework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzdv extends zzcb implements RandomAccess, zzdw {
    @Deprecated
    public static final zzdw zza;
    private static final zzdv zzb;
    private final List zzc;

    static {
        zzdv zzdv = new zzdv(false);
        zzb = zzdv;
        zza = zzdv;
    }

    public zzdv() {
        this(10);
    }

    private static String zzi(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzcn) {
            return ((zzcn) obj).zzl(zzdp.zzb);
        }
        return zzdp.zzd((byte[]) obj);
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection collection) {
        zza();
        if (collection instanceof zzdw) {
            collection = ((zzdw) collection).zzh();
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
        return zzi(remove);
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzi(this.zzc.set(i, (String) obj));
    }

    public final int size() {
        return this.zzc.size();
    }

    public final zzdw zzd() {
        return zzc() ? new zzfu(this) : this;
    }

    public final Object zze(int i) {
        return this.zzc.get(i);
    }

    /* renamed from: zzf */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzcn) {
            zzcn zzcn = (zzcn) obj;
            String zzl = zzcn.zzl(zzdp.zzb);
            if (zzcn.zzi()) {
                this.zzc.set(i, zzl);
            }
            return zzl;
        }
        byte[] bArr = (byte[]) obj;
        String zzd = zzdp.zzd(bArr);
        if (zzgd.zzd(bArr)) {
            this.zzc.set(i, zzd);
        }
        return zzd;
    }

    public final /* bridge */ /* synthetic */ zzdo zzg(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzc);
            return new zzdv(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzdv(int i) {
        super(true);
        ArrayList arrayList = new ArrayList(i);
        this.zzc = arrayList;
    }

    private zzdv(ArrayList arrayList) {
        super(true);
        this.zzc = arrayList;
    }

    private zzdv(boolean z) {
        super(false);
        this.zzc = Collections.emptyList();
    }

    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
