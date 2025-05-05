package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzalq<E> extends zzaij<E> implements RandomAccess {
    private static final Object[] zza;
    private static final zzalq<Object> zzb;
    private E[] zzc;
    private int zzd;

    private static int zzb(int i) {
        return Math.max(((i * 3) / 2) + 1, 10);
    }

    public final int size() {
        return this.zzd;
    }

    public final /* synthetic */ zzakc zza(int i) {
        if (i >= this.zzd) {
            return new zzalq(i == 0 ? zza : Arrays.copyOf(this.zzc, i), this.zzd, true);
        }
        throw new IllegalArgumentException();
    }

    public static <E> zzalq<E> zzd() {
        return zzb;
    }

    public final E get(int i) {
        zzd(i);
        return this.zzc[i];
    }

    public final E remove(int i) {
        zza();
        zzd(i);
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
        zzd(i);
        E[] eArr = this.zzc;
        E e2 = eArr[i];
        eArr[i] = e;
        this.modCount++;
        return e2;
    }

    private final String zzc(int i) {
        return "Index:" + i + ", Size:" + this.zzd;
    }

    static {
        Object[] objArr = new Object[0];
        zza = objArr;
        zzb = new zzalq<>(objArr, 0, false);
    }

    zzalq() {
        this(zza, 0, true);
    }

    private zzalq(E[] eArr, int i, boolean z) {
        super(z);
        this.zzc = eArr;
        this.zzd = i;
    }

    public final void add(int i, E e) {
        int i2;
        zza();
        if (i < 0 || i > (i2 = this.zzd)) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
        E[] eArr = this.zzc;
        if (i2 < eArr.length) {
            System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
        } else {
            E[] eArr2 = new Object[zzb(eArr.length)];
            System.arraycopy(this.zzc, 0, eArr2, 0, i);
            System.arraycopy(this.zzc, i, eArr2, i + 1, this.zzd - i);
            this.zzc = eArr2;
        }
        this.zzc[i] = e;
        this.zzd++;
        this.modCount++;
    }

    private final void zzd(int i) {
        if (i < 0 || i >= this.zzd) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
    }

    public final boolean add(E e) {
        zza();
        int i = this.zzd;
        E[] eArr = this.zzc;
        if (i == eArr.length) {
            this.zzc = Arrays.copyOf(this.zzc, zzb(eArr.length));
        }
        E[] eArr2 = this.zzc;
        int i2 = this.zzd;
        this.zzd = i2 + 1;
        eArr2[i2] = e;
        this.modCount++;
        return true;
    }
}
