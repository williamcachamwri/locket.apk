package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzjw extends zzie<Integer> implements zzka, zzlo, RandomAccess {
    private static final int[] zza;
    private static final zzjw zzb;
    private int[] zzc;
    private int zzd;

    public final int zzb(int i) {
        zzh(i);
        return this.zzc[i];
    }

    private static int zzf(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzd; i2++) {
            i = (i * 31) + this.zzc[i2];
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.zzc[i] == intValue) {
                return i;
            }
        }
        return -1;
    }

    public final int size() {
        return this.zzd;
    }

    public static zzjw zzd() {
        return zzb;
    }

    /* renamed from: zzc */
    public final zzka zza(int i) {
        if (i >= this.zzd) {
            return new zzjw(i == 0 ? zza : Arrays.copyOf(this.zzc, i), this.zzd, true);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(zzb(i));
    }

    public final /* synthetic */ Object remove(int i) {
        zza();
        zzh(i);
        int[] iArr = this.zzc;
        int i2 = iArr[i];
        int i3 = this.zzd;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i3 - i) - 1);
        }
        this.zzd--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zza();
        zzh(i);
        int[] iArr = this.zzc;
        int i2 = iArr[i];
        iArr[i] = intValue;
        return Integer.valueOf(i2);
    }

    private final String zzg(int i) {
        return "Index:" + i + ", Size:" + this.zzd;
    }

    static {
        int[] iArr = new int[0];
        zza = iArr;
        zzb = new zzjw(iArr, 0, false);
    }

    zzjw() {
        this(zza, 0, true);
    }

    private zzjw(int[] iArr, int i, boolean z) {
        super(z);
        this.zzc = iArr;
        this.zzd = i;
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        int intValue = ((Integer) obj).intValue();
        zza();
        if (i < 0 || i > (i2 = this.zzd)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        int[] iArr = this.zzc;
        if (i2 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i2 - i);
        } else {
            int[] iArr2 = new int[zzf(iArr.length)];
            System.arraycopy(this.zzc, 0, iArr2, 0, i);
            System.arraycopy(this.zzc, i, iArr2, i + 1, this.zzd - i);
            this.zzc = iArr2;
        }
        this.zzc[i] = intValue;
        this.zzd++;
        this.modCount++;
    }

    public final void zzd(int i) {
        zza();
        int i2 = this.zzd;
        int[] iArr = this.zzc;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[zzf(iArr.length)];
            System.arraycopy(this.zzc, 0, iArr2, 0, this.zzd);
            this.zzc = iArr2;
        }
        int[] iArr3 = this.zzc;
        int i3 = this.zzd;
        this.zzd = i3 + 1;
        iArr3[i3] = i;
    }

    /* access modifiers changed from: package-private */
    public final void zze(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            if (iArr.length == 0) {
                this.zzc = new int[Math.max(i, 10)];
                return;
            }
            int length = iArr.length;
            while (length < i) {
                length = zzf(length);
            }
            this.zzc = Arrays.copyOf(this.zzc, length);
        }
    }

    private final void zzh(int i) {
        if (i < 0 || i >= this.zzd) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zza();
        if (i2 >= i) {
            int[] iArr = this.zzc;
            System.arraycopy(iArr, i2, iArr, i, this.zzd - i2);
            this.zzd -= i2 - i;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzd(((Integer) obj).intValue());
        return true;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zza();
        zzjv.zza(collection);
        if (!(collection instanceof zzjw)) {
            return super.addAll(collection);
        }
        zzjw zzjw = (zzjw) collection;
        int i = zzjw.zzd;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzd;
        if (Integer.MAX_VALUE - i2 >= i) {
            int i3 = i2 + i;
            int[] iArr = this.zzc;
            if (i3 > iArr.length) {
                this.zzc = Arrays.copyOf(iArr, i3);
            }
            System.arraycopy(zzjw.zzc, 0, this.zzc, this.zzd, zzjw.zzd);
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
        if (!(obj instanceof zzjw)) {
            return super.equals(obj);
        }
        zzjw zzjw = (zzjw) obj;
        if (this.zzd != zzjw.zzd) {
            return false;
        }
        int[] iArr = zzjw.zzc;
        for (int i = 0; i < this.zzd; i++) {
            if (this.zzc[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }
}
