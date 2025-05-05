package com.google.ads.interactivemedia.v3.internal;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzrv extends zzrr implements NavigableSet, zzsq {
    final transient Comparator zza;
    @CheckForNull
    transient zzrv zzb;

    zzrv(Comparator comparator) {
        this.zza = comparator;
    }

    static zzsl zzs(Comparator comparator) {
        if (zzsb.zza.equals(comparator)) {
            return zzsl.zzc;
        }
        int i = zzrm.zzd;
        return new zzsl(zzse.zza, comparator);
    }

    @Deprecated
    public final void addFirst(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void addLast(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final Comparator comparator() {
        return this.zza;
    }

    public Object first() {
        return iterator().next();
    }

    public final Object getFirst() {
        return first();
    }

    public final Object getLast() {
        return last();
    }

    public Object last() {
        return descendingIterator().next();
    }

    @CheckForNull
    @Deprecated
    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    @Deprecated
    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object removeFirst() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object removeLast() {
        throw new UnsupportedOperationException();
    }

    public final /* bridge */ /* synthetic */ SortedSet subSet(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    /* renamed from: zze */
    public abstract zzss iterator();

    /* access modifiers changed from: package-private */
    public abstract zzrv zzh();

    /* renamed from: zzn */
    public final zzrv descendingSet() {
        zzrv zzrv = this.zzb;
        if (zzrv != null) {
            return zzrv;
        }
        zzrv zzh = zzh();
        this.zzb = zzh;
        zzh.zzb = this;
        return zzh;
    }

    /* access modifiers changed from: package-private */
    public abstract zzrv zzo(Object obj, boolean z);

    /* access modifiers changed from: package-private */
    public abstract zzrv zzq(Object obj, boolean z, Object obj2, boolean z2);

    /* access modifiers changed from: package-private */
    public abstract zzrv zzr(Object obj, boolean z);

    /* renamed from: zzt */
    public abstract zzss descendingIterator();

    public final /* synthetic */ SortedSet headSet(Object obj) {
        obj.getClass();
        return zzo(obj, false);
    }

    public final /* synthetic */ SortedSet tailSet(Object obj) {
        obj.getClass();
        return zzr(obj, true);
    }

    @CheckForNull
    public Object ceiling(Object obj) {
        obj.getClass();
        return zzrw.zza(zzr(obj, true), (Object) null);
    }

    @CheckForNull
    public Object floor(Object obj) {
        obj.getClass();
        return zzry.zza(zzo(obj, true).descendingIterator(), (Object) null);
    }

    @CheckForNull
    public Object higher(Object obj) {
        obj.getClass();
        return zzrw.zza(zzr(obj, false), (Object) null);
    }

    @CheckForNull
    public Object lower(Object obj) {
        obj.getClass();
        return zzry.zza(zzo(obj, false).descendingIterator(), (Object) null);
    }

    public final /* synthetic */ NavigableSet headSet(Object obj, boolean z) {
        obj.getClass();
        return zzo(obj, z);
    }

    public final /* synthetic */ NavigableSet tailSet(Object obj, boolean z) {
        obj.getClass();
        return zzr(obj, z);
    }

    /* renamed from: zzp */
    public final zzrv subSet(Object obj, boolean z, Object obj2, boolean z2) {
        obj.getClass();
        obj2.getClass();
        zzqh.zzd(this.zza.compare(obj, obj2) <= 0);
        return zzq(obj, z, obj2, z2);
    }
}
