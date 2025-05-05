package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzrr<E> extends zzri<E> implements Set<E> {
    @CheckForNull
    private transient zzrm zza;

    zzrr() {
    }

    static int zzj(int i) {
        int max = Math.max(i, 2);
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            do {
                highestOneBit += highestOneBit;
            } while (((double) highestOneBit) * 0.7d < ((double) max));
            return highestOneBit;
        }
        zzqh.zze(max < 1073741824, "collection too large");
        return 1073741824;
    }

    public static zzrr zzl(Collection collection) {
        if ((collection instanceof zzrr) && !(collection instanceof SortedSet)) {
            zzrr zzrr = (zzrr) collection;
            if (!zzrr.zzf()) {
                return zzrr;
            }
        }
        Object[] array = collection.toArray();
        return zzh(array.length, array);
    }

    public static zzrr zzm(Object obj) {
        return new zzsp(obj);
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzrr) && zzi() && ((zzrr) obj).zzi() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size() && containsAll(set)) {
                    return true;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public int hashCode() {
        return zzso.zza(this);
    }

    public zzrm zzd() {
        zzrm zzrm = this.zza;
        if (zzrm != null) {
            return zzrm;
        }
        zzrm zzk = zzk();
        this.zza = zzk;
        return zzk;
    }

    /* renamed from: zze */
    public abstract zzss iterator();

    /* access modifiers changed from: package-private */
    public boolean zzi() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public zzrm zzk() {
        Object[] array = toArray();
        int i = zzrm.zzd;
        return zzrm.zzj(array, array.length);
    }

    private static zzrr zzh(int i, Object... objArr) {
        if (i == 0) {
            return zzsk.zza;
        }
        if (i == 1) {
            return new zzsp(Objects.requireNonNull(objArr[0]));
        }
        int zzj = zzj(i);
        Object[] objArr2 = new Object[zzj];
        int i2 = zzj - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj = objArr[i5];
            zzsc.zza(obj, i5);
            int hashCode = obj.hashCode();
            int zzb = zzrg.zzb(hashCode);
            while (true) {
                int i6 = zzb & i2;
                Object obj2 = objArr2[i6];
                if (obj2 != null) {
                    if (obj2.equals(obj)) {
                        break;
                    }
                    zzb++;
                } else {
                    objArr[i4] = obj;
                    objArr2[i6] = obj;
                    i3 += hashCode;
                    i4++;
                    break;
                }
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            return new zzsp(Objects.requireNonNull(objArr[0]));
        }
        if (zzj(i4) < zzj / 2) {
            return zzh(i4, objArr);
        }
        int length = objArr.length;
        if (i4 < (length >> 1) + (length >> 2)) {
            objArr = Arrays.copyOf(objArr, i4);
        }
        return new zzsk(objArr, i3, objArr2, i2, i4);
    }
}
