package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzak  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzak<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] zza = new Object[0];

    public abstract boolean contains(@CheckForNull Object obj);

    /* access modifiers changed from: package-private */
    public int zza(Object[] objArr, int i) {
        zzbc zzbc = (zzbc) iterator();
        while (zzbc.hasNext()) {
            objArr[i] = zzbc.next();
            i++;
        }
        return i;
    }

    /* renamed from: zzd */
    public abstract zzbc<E> iterator();

    /* access modifiers changed from: package-private */
    public abstract boolean zze();

    /* access modifiers changed from: package-private */
    @CheckForNull
    public Object[] zzf() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public int zza() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int zzb() {
        throw new UnsupportedOperationException();
    }

    public zzal<E> zzc() {
        return isEmpty() ? zzal.zzh() : zzal.zza(toArray());
    }

    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 1296);
    }

    zzak() {
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray() {
        return toArray(zza);
    }

    public final <T> T[] toArray(T[] tArr) {
        zzy.zza(tArr);
        int size = size();
        if (tArr.length < size) {
            Object[] zzf = zzf();
            if (zzf != null) {
                return Arrays.copyOfRange(zzf, zzb(), zza(), tArr.getClass());
            }
            if (tArr.length != 0) {
                tArr = Arrays.copyOf(tArr, 0);
            }
            tArr = Arrays.copyOf(tArr, size);
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        zza(tArr, 0);
        return tArr;
    }
}
