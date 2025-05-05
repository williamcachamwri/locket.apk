package com.google.ads.interactivemedia.v3.internal;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzru extends zzrp implements NavigableMap {
    private static final zzru zza;
    /* access modifiers changed from: private */
    public final transient zzsl zzb;
    /* access modifiers changed from: private */
    public final transient zzrm zzc;
    @CheckForNull
    private final transient zzru zzd;

    static {
        zzsl zzs = zzrv.zzs(zzsb.zza);
        int i = zzrm.zzd;
        zza = new zzru(zzs, zzse.zza, (zzru) null);
    }

    zzru(zzsl zzsl, zzrm zzrm, @CheckForNull zzru zzru) {
        this.zzb = zzsl;
        this.zzc = zzrm;
        this.zzd = zzru;
    }

    static zzru zzm(Comparator comparator) {
        if (zzsb.zza.equals(comparator)) {
            return zza;
        }
        zzsl zzs = zzrv.zzs(comparator);
        int i = zzrm.zzd;
        return new zzru(zzs, zzse.zza, (zzru) null);
    }

    public static zzru zzo() {
        return zza;
    }

    private final zzru zzs(int i, int i2) {
        if (i == 0) {
            if (i2 == this.zzc.size()) {
                return this;
            }
            i = 0;
        }
        if (i == i2) {
            return zzm(this.zzb.zza);
        }
        return new zzru(this.zzb.zzw(i, i2), this.zzc.subList(i, i2), (zzru) null);
    }

    @CheckForNull
    public final Map.Entry ceilingEntry(Object obj) {
        return tailMap(obj, true).firstEntry();
    }

    @CheckForNull
    public final Object ceilingKey(Object obj) {
        return zzrz.zza(ceilingEntry(obj));
    }

    public final Comparator comparator() {
        return this.zzb.zza;
    }

    public final /* synthetic */ NavigableSet descendingKeySet() {
        return this.zzb.descendingSet();
    }

    public final /* bridge */ /* synthetic */ NavigableMap descendingMap() {
        zzsd zzsd;
        zzru zzru = this.zzd;
        if (zzru != null) {
            return zzru;
        }
        if (!isEmpty()) {
            return new zzru((zzsl) this.zzb.descendingSet(), this.zzc.zzh(), this);
        }
        Comparator comparator = this.zzb.zza;
        if (comparator instanceof zzsd) {
            zzsd = (zzsd) comparator;
        } else {
            zzsd = new zzqu(comparator);
        }
        return zzm(zzsd.zza());
    }

    public final /* bridge */ /* synthetic */ Set entrySet() {
        return entrySet();
    }

    @CheckForNull
    public final Map.Entry firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().zzd().get(0);
    }

    public final Object firstKey() {
        return this.zzb.first();
    }

    @CheckForNull
    public final Map.Entry floorEntry(Object obj) {
        return headMap(obj, true).lastEntry();
    }

    @CheckForNull
    public final Object floorKey(Object obj) {
        return zzrz.zza(floorEntry(obj));
    }

    public final /* synthetic */ SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    @CheckForNull
    public final Map.Entry higherEntry(Object obj) {
        return tailMap(obj, false).firstEntry();
    }

    @CheckForNull
    public final Object higherKey(Object obj) {
        return zzrz.zza(higherEntry(obj));
    }

    public final /* synthetic */ Set keySet() {
        return this.zzb;
    }

    @CheckForNull
    public final Map.Entry lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().zzd().get(this.zzc.size() - 1);
    }

    public final Object lastKey() {
        return this.zzb.last();
    }

    @CheckForNull
    public final Map.Entry lowerEntry(Object obj) {
        return headMap(obj, false).lastEntry();
    }

    @CheckForNull
    public final Object lowerKey(Object obj) {
        return zzrz.zza(lowerEntry(obj));
    }

    public final /* synthetic */ NavigableSet navigableKeySet() {
        return this.zzb;
    }

    @CheckForNull
    @Deprecated
    public final Map.Entry pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    @Deprecated
    public final Map.Entry pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        return this.zzc.size();
    }

    public final /* bridge */ /* synthetic */ SortedMap subMap(Object obj, Object obj2) {
        return subMap(obj, true, obj2, false);
    }

    public final /* synthetic */ SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }

    public final /* synthetic */ Collection values() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final zzri zza() {
        throw new AssertionError("should never be called");
    }

    public final zzri zzb() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final zzrr zzg() {
        if (isEmpty()) {
            return zzsk.zza;
        }
        return new zzrt(this);
    }

    /* access modifiers changed from: package-private */
    public final zzrr zzh() {
        throw new AssertionError("should never be called");
    }

    public final /* synthetic */ zzrr zzj() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzk() {
        return this.zzb.zzd.zzf() || this.zzc.zzf();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        if (r4 < 0) goto L_0x0005;
     */
    @javax.annotation.CheckForNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r4) {
        /*
            r3 = this;
            com.google.ads.interactivemedia.v3.internal.zzsl r0 = r3.zzb
            r1 = -1
            if (r4 != 0) goto L_0x0007
        L_0x0005:
            r4 = r1
            goto L_0x0012
        L_0x0007:
            com.google.ads.interactivemedia.v3.internal.zzrm r2 = r0.zzd     // Catch:{ ClassCastException -> 0x0005 }
            java.util.Comparator r0 = r0.zza     // Catch:{ ClassCastException -> 0x0005 }
            int r4 = java.util.Collections.binarySearch(r2, r4, r0)     // Catch:{ ClassCastException -> 0x0005 }
            if (r4 >= 0) goto L_0x0012
            goto L_0x0005
        L_0x0012:
            if (r4 != r1) goto L_0x0016
            r4 = 0
            return r4
        L_0x0016:
            com.google.ads.interactivemedia.v3.internal.zzrm r0 = r3.zzc
            java.lang.Object r4 = r0.get(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzru.get(java.lang.Object):java.lang.Object");
    }

    /* renamed from: zzn */
    public final zzru headMap(Object obj, boolean z) {
        obj.getClass();
        return zzs(0, this.zzb.zzu(obj, z));
    }

    /* renamed from: zzq */
    public final zzru tailMap(Object obj, boolean z) {
        obj.getClass();
        return zzs(this.zzb.zzv(obj, z), this.zzc.size());
    }

    /* renamed from: zzp */
    public final zzru subMap(Object obj, boolean z, Object obj2, boolean z2) {
        obj.getClass();
        obj2.getClass();
        if (this.zzb.zza.compare(obj, obj2) <= 0) {
            return headMap(obj2, z2).tailMap(obj, z);
        }
        throw new IllegalArgumentException(zzqm.zzb("expected fromKey <= toKey but %s > %s", obj, obj2));
    }
}
