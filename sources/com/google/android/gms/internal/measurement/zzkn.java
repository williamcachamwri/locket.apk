package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzkn extends zzie<Long> implements zzjz, zzlo, RandomAccess {
    private static final long[] zza;
    private static final zzkn zzb;
    private long[] zzc;
    private int zzd;

    private static int zze(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzd; i2++) {
            i = (i * 31) + zzjv.zza(this.zzc[i2]);
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.zzc[i] == longValue) {
                return i;
            }
        }
        return -1;
    }

    public final int size() {
        return this.zzd;
    }

    public final long zzb(int i) {
        zzg(i);
        return this.zzc[i];
    }

    /* renamed from: zzc */
    public final zzjz zza(int i) {
        if (i >= this.zzd) {
            return new zzkn(i == 0 ? zza : Arrays.copyOf(this.zzc, i), this.zzd, true);
        }
        throw new IllegalArgumentException();
    }

    public static zzkn zzd() {
        return zzb;
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(zzb(i));
    }

    public final /* synthetic */ Object remove(int i) {
        zza();
        zzg(i);
        long[] jArr = this.zzc;
        long j = jArr[i];
        int i2 = this.zzd;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.zzd--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zza();
        zzg(i);
        long[] jArr = this.zzc;
        long j = jArr[i];
        jArr[i] = longValue;
        return Long.valueOf(j);
    }

    private final String zzf(int i) {
        return "Index:" + i + ", Size:" + this.zzd;
    }

    static {
        long[] jArr = new long[0];
        zza = jArr;
        zzb = new zzkn(jArr, 0, false);
    }

    zzkn() {
        this(zza, 0, true);
    }

    private zzkn(long[] jArr, int i, boolean z) {
        super(z);
        this.zzc = jArr;
        this.zzd = i;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        long longValue = ((Long) obj).longValue();
        zza();
        if (i < 0 || i > (i2 = this.zzd)) {
            throw new IndexOutOfBoundsException(zzf(i));
        }
        long[] jArr = this.zzc;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[zze(jArr.length)];
            System.arraycopy(this.zzc, 0, jArr2, 0, i);
            System.arraycopy(this.zzc, i, jArr2, i + 1, this.zzd - i);
            this.zzc = jArr2;
        }
        this.zzc[i] = longValue;
        this.zzd++;
        this.modCount++;
    }

    public final void zza(long j) {
        zza();
        int i = this.zzd;
        long[] jArr = this.zzc;
        if (i == jArr.length) {
            long[] jArr2 = new long[zze(jArr.length)];
            System.arraycopy(this.zzc, 0, jArr2, 0, this.zzd);
            this.zzc = jArr2;
        }
        long[] jArr3 = this.zzc;
        int i2 = this.zzd;
        this.zzd = i2 + 1;
        jArr3[i2] = j;
    }

    /* access modifiers changed from: package-private */
    public final void zzd(int i) {
        long[] jArr = this.zzc;
        if (i > jArr.length) {
            if (jArr.length == 0) {
                this.zzc = new long[Math.max(i, 10)];
                return;
            }
            int length = jArr.length;
            while (length < i) {
                length = zze(length);
            }
            this.zzc = Arrays.copyOf(this.zzc, length);
        }
    }

    private final void zzg(int i) {
        if (i < 0 || i >= this.zzd) {
            throw new IndexOutOfBoundsException(zzf(i));
        }
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zza();
        if (i2 >= i) {
            long[] jArr = this.zzc;
            System.arraycopy(jArr, i2, jArr, i, this.zzd - i2);
            this.zzd -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ boolean add(Object obj) {
        zza(((Long) obj).longValue());
        return true;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zza();
        zzjv.zza(collection);
        if (!(collection instanceof zzkn)) {
            return super.addAll(collection);
        }
        zzkn zzkn = (zzkn) collection;
        int i = zzkn.zzd;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzd;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            long[] jArr = this.zzc;
            if (i3 > jArr.length) {
                this.zzc = Arrays.copyOf(jArr, i3);
            }
            System.arraycopy(zzkn.zzc, 0, this.zzc, this.zzd, zzkn.zzd);
            this.zzd = i3;
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
        if (!(obj instanceof zzkn)) {
            return super.equals(obj);
        }
        zzkn zzkn = (zzkn) obj;
        if (this.zzd != zzkn.zzd) {
            return false;
        }
        long[] jArr = zzkn.zzc;
        for (int i = 0; i < this.zzd; i++) {
            if (this.zzc[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }
}
