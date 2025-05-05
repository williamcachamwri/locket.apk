package com.google.ads.interactivemedia.v3.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzsl extends zzrv {
    static final zzsl zzc = new zzsl(zzse.zza, zzsb.zza);
    final transient zzrm zzd;

    static {
        int i = zzrm.zzd;
    }

    zzsl(zzrm zzrm, Comparator comparator) {
        super(comparator);
        this.zzd = zzrm;
    }

    @CheckForNull
    public final Object ceiling(Object obj) {
        zzrm zzrm = this.zzd;
        int zzv = zzv(obj, true);
        if (zzv == zzrm.size()) {
            return null;
        }
        return this.zzd.get(zzv);
    }

    public final boolean contains(@CheckForNull Object obj) {
        if (obj != null) {
            try {
                if (Collections.binarySearch(this.zzd, obj, this.zza) >= 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    public final boolean containsAll(Collection collection) {
        if (collection instanceof zzsa) {
            collection = ((zzsa) collection).zza();
        }
        if (!zzsr.zza(this.zza, collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        zzst zzn = this.zzd.listIterator(0);
        Iterator it = collection.iterator();
        if (!zzn.hasNext()) {
            return false;
        }
        Object next = it.next();
        Object next2 = zzn.next();
        while (true) {
            try {
                int compare = this.zza.compare(next2, next);
                if (compare >= 0) {
                    if (compare != 0) {
                        break;
                    } else if (!it.hasNext()) {
                        return true;
                    } else {
                        next = it.next();
                    }
                } else if (!zzn.hasNext()) {
                    return false;
                } else {
                    next2 = zzn.next();
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0038 A[Catch:{ ClassCastException | NoSuchElementException -> 0x004c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(@javax.annotation.CheckForNull java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != r6) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r7 instanceof java.util.Set
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            java.util.Set r7 = (java.util.Set) r7
            com.google.ads.interactivemedia.v3.internal.zzrm r1 = r6.zzd
            int r1 = r1.size()
            int r3 = r7.size()
            if (r1 == r3) goto L_0x0019
            return r2
        L_0x0019:
            boolean r1 = r6.isEmpty()
            if (r1 == 0) goto L_0x0020
            return r0
        L_0x0020:
            java.util.Comparator r1 = r6.zza
            boolean r1 = com.google.ads.interactivemedia.v3.internal.zzsr.zza(r1, r7)
            if (r1 == 0) goto L_0x004d
            java.util.Iterator r7 = r7.iterator()
            com.google.ads.interactivemedia.v3.internal.zzrm r1 = r6.zzd     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            com.google.ads.interactivemedia.v3.internal.zzst r1 = r1.listIterator(r2)     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
        L_0x0032:
            boolean r3 = r1.hasNext()     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            if (r3 == 0) goto L_0x004b
            java.lang.Object r3 = r1.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            java.lang.Object r4 = r7.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            if (r4 == 0) goto L_0x004a
            java.util.Comparator r5 = r6.zza     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            int r3 = r5.compare(r3, r4)     // Catch:{ ClassCastException | NoSuchElementException -> 0x004c }
            if (r3 == 0) goto L_0x0032
        L_0x004a:
            return r2
        L_0x004b:
            return r0
        L_0x004c:
            return r2
        L_0x004d:
            boolean r7 = r6.containsAll(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzsl.equals(java.lang.Object):boolean");
    }

    public final Object first() {
        if (!isEmpty()) {
            return this.zzd.get(0);
        }
        throw new NoSuchElementException();
    }

    @CheckForNull
    public final Object floor(Object obj) {
        int zzu = zzu(obj, true) - 1;
        if (zzu == -1) {
            return null;
        }
        return this.zzd.get(zzu);
    }

    @CheckForNull
    public final Object higher(Object obj) {
        zzrm zzrm = this.zzd;
        int zzv = zzv(obj, false);
        if (zzv == zzrm.size()) {
            return null;
        }
        return this.zzd.get(zzv);
    }

    public final /* synthetic */ Iterator iterator() {
        return this.zzd.listIterator(0);
    }

    public final Object last() {
        if (!isEmpty()) {
            zzrm zzrm = this.zzd;
            return zzrm.get(zzrm.size() - 1);
        }
        throw new NoSuchElementException();
    }

    @CheckForNull
    public final Object lower(Object obj) {
        int zzu = zzu(obj, false) - 1;
        if (zzu == -1) {
            return null;
        }
        return this.zzd.get(zzu);
    }

    public final int size() {
        return this.zzd.size();
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return this.zzd.zza(objArr, 0);
    }

    /* access modifiers changed from: package-private */
    public final int zzb() {
        return this.zzd.zzb();
    }

    /* access modifiers changed from: package-private */
    public final int zzc() {
        return this.zzd.zzc();
    }

    public final zzrm zzd() {
        return this.zzd;
    }

    public final zzss zze() {
        return this.zzd.listIterator(0);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return this.zzd.zzf();
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public final Object[] zzg() {
        return this.zzd.zzg();
    }

    /* access modifiers changed from: package-private */
    public final zzrv zzh() {
        Comparator reverseOrder = Collections.reverseOrder(this.zza);
        if (isEmpty()) {
            return zzs(reverseOrder);
        }
        return new zzsl(this.zzd.zzh(), reverseOrder);
    }

    /* access modifiers changed from: package-private */
    public final zzrv zzo(Object obj, boolean z) {
        return zzw(0, zzu(obj, z));
    }

    /* access modifiers changed from: package-private */
    public final zzrv zzq(Object obj, boolean z, Object obj2, boolean z2) {
        return zzr(obj, z).zzo(obj2, z2);
    }

    /* access modifiers changed from: package-private */
    public final zzrv zzr(Object obj, boolean z) {
        return zzw(zzv(obj, z), this.zzd.size());
    }

    /* renamed from: zzt */
    public final zzss descendingIterator() {
        return this.zzd.zzh().listIterator(0);
    }

    /* access modifiers changed from: package-private */
    public final zzsl zzw(int i, int i2) {
        if (i == 0) {
            if (i2 == this.zzd.size()) {
                return this;
            }
            i = 0;
        }
        if (i >= i2) {
            return zzs(this.zza);
        }
        zzrm zzrm = this.zzd;
        return new zzsl(zzrm.subList(i, i2), this.zza);
    }

    /* access modifiers changed from: package-private */
    public final int zzu(Object obj, boolean z) {
        obj.getClass();
        int binarySearch = Collections.binarySearch(this.zzd, obj, this.zza);
        if (binarySearch >= 0) {
            return z ? binarySearch + 1 : binarySearch;
        }
        return ~binarySearch;
    }

    /* access modifiers changed from: package-private */
    public final int zzv(Object obj, boolean z) {
        obj.getClass();
        int binarySearch = Collections.binarySearch(this.zzd, obj, this.zza);
        if (binarySearch >= 0) {
            return z ? binarySearch : binarySearch + 1;
        }
        return ~binarySearch;
    }
}
