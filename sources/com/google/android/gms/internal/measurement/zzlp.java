package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzlp<E> extends zzie<E> implements RandomAccess {
    private static final Object[] zza;
    private static final zzlp<Object> zzb;
    private E[] zzc;
    private int zzd;

    private static int zzc(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    public final int size() {
        return this.zzd;
    }

    public final /* synthetic */ zzkc zza(int i) {
        if (i >= this.zzd) {
            return new zzlp(i == 0 ? zza : Arrays.copyOf(this.zzc, i), this.zzd, true);
        }
        throw new IllegalArgumentException();
    }

    public static <E> zzlp<E> zzd() {
        return zzb;
    }

    public final E get(int i) {
        zze(i);
        return this.zzc[i];
    }

    public final E remove(int i) {
        zza();
        zze(i);
        E[] eArr = this.zzc;
        E e = eArr[i];
        int i2 = this.zzd;
        if (i < i2 - 1) {
            System.arraycopy(eArr, i + 1, eArr, i, (i2 - i) - 1);
        }
        this.zzd--;
        this.modCount++;
        return e;
    }

    public final E set(int i, E e) {
        zza();
        zze(i);
        E[] eArr = this.zzc;
        E e2 = eArr[i];
        eArr[i] = e;
        this.modCount++;
        return e2;
    }

    private final String zzd(int i) {
        return "Index:" + i + ", Size:" + this.zzd;
    }

    static {
        Object[] objArr = new Object[0];
        zza = objArr;
        zzb = new zzlp<>(objArr, 0, false);
    }

    zzlp() {
        this(zza, 0, true);
    }

    private zzlp(E[] eArr, int i, boolean z) {
        super(z);
        this.zzc = eArr;
        this.zzd = i;
    }

    public final void add(int i, E e) {
        int i2;
        zza();
        if (i < 0 || i > (i2 = this.zzd)) {
            throw new IndexOutOfBoundsException(zzd(i));
        }
        E[] eArr = this.zzc;
        if (i2 < eArr.length) {
            System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
        } else {
            E[] eArr2 = new Object[zzc(eArr.length)];
            System.arraycopy(this.zzc, 0, eArr2, 0, i);
            System.arraycopy(this.zzc, i, eArr2, i + 1, this.zzd - i);
            this.zzc = eArr2;
        }
        this.zzc[i] = e;
        this.zzd++;
        this.modCount++;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(int i) {
        E[] eArr = this.zzc;
        if (i > eArr.length) {
            if (eArr.length == 0) {
                this.zzc = new Object[Math.max(i, 10)];
                return;
            }
            int length = eArr.length;
            while (length < i) {
                length = zzc(length);
            }
            this.zzc = Arrays.copyOf(this.zzc, length);
        }
    }

    private final void zze(int i) {
        if (i < 0 || i >= this.zzd) {
            throw new IndexOutOfBoundsException(zzd(i));
        }
    }

    public final boolean add(E e) {
        zza();
        int i = this.zzd;
        E[] eArr = this.zzc;
        if (i == eArr.length) {
            this.zzc = Arrays.copyOf(this.zzc, zzc(eArr.length));
        }
        E[] eArr2 = this.zzc;
        int i2 = this.zzd;
        this.zzd = i2 + 1;
        eArr2[i2] = e;
        this.modCount++;
        return true;
    }
}
