package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaeq extends zzacj implements RandomAccess, zzaed {
    private long[] zza;
    private int zzb;

    static {
        new zzaeq(new long[0], 0, false);
    }

    zzaeq() {
        this(new long[10], 0, true);
    }

    private final String zzg(int i) {
        int i2 = this.zzb;
        return "Index:" + i + ", Size:" + i2;
    }

    private final void zzh(int i) {
        if (i < 0 || i >= this.zzb) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        long longValue = ((Long) obj).longValue();
        zza();
        if (i < 0 || i > (i2 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        int i3 = i + 1;
        long[] jArr = this.zza;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i3, i2 - i);
        } else {
            long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.zza, i, jArr2, i3, this.zzb - i);
            this.zza = jArr2;
        }
        this.zza[i] = longValue;
        this.zzb++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzaee.zzb;
        collection.getClass();
        if (!(collection instanceof zzaeq)) {
            return super.addAll(collection);
        }
        zzaeq zzaeq = (zzaeq) collection;
        int i = zzaeq.zzb;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzb;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.zza;
            if (i3 > jArr.length) {
                this.zza = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(zzaeq.zza, 0, this.zza, this.zzb, zzaeq.zzb);
            this.zzb = i3;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaeq)) {
            return super.equals(obj);
        }
        zzaeq zzaeq = (zzaeq) obj;
        if (this.zzb != zzaeq.zzb) {
            return false;
        }
        long[] jArr = zzaeq.zza;
        for (int i = 0; i < this.zzb; i++) {
            if (this.zza[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        zzh(i);
        return Long.valueOf(this.zza[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            long j = this.zza[i2];
            byte[] bArr = zzaee.zzb;
            i = (i * 31) + ((int) (j ^ (j >>> 32)));
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int i = this.zzb;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zza[i2] == longValue) {
                return i2;
            }
        }
        return -1;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        zzh(i);
        long[] jArr = this.zza;
        long j = jArr[i];
        int i2 = this.zzb;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.zzb--;
        this.modCount++;
        return Long.valueOf(j);
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zza();
        if (i2 >= i) {
            long[] jArr = this.zza;
            System.arraycopy(jArr, i2, jArr, i, this.zzb - i2);
            this.zzb -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zza();
        zzh(i);
        long[] jArr = this.zza;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* bridge */ /* synthetic */ zzaed zzd(int i) {
        if (i >= this.zzb) {
            return new zzaeq(Arrays.copyOf(this.zza, i), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final long zze(int i) {
        zzh(i);
        return this.zza[i];
    }

    public final void zzf(long j) {
        zza();
        int i = this.zzb;
        long[] jArr = this.zza;
        if (i == jArr.length) {
            long[] jArr2 = new long[(((i * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.zza = jArr2;
        }
        long[] jArr3 = this.zza;
        int i2 = this.zzb;
        this.zzb = i2 + 1;
        jArr3[i2] = j;
    }

    private zzaeq(long[] jArr, int i, boolean z) {
        super(z);
        this.zza = jArr;
        this.zzb = i;
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzf(((Long) obj).longValue());
        return true;
    }
}
