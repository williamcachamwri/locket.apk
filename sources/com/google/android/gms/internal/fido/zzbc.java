package com.google.android.gms.internal.fido;

import java.util.Arrays;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public abstract class zzbc extends zzav implements Set {
    @CheckForNull
    private transient zzaz zza;

    zzbc() {
    }

    static int zzh(int i) {
        int max = Math.max(i, 2);
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            do {
                highestOneBit += highestOneBit;
            } while (((double) highestOneBit) * 0.7d < ((double) max));
            return highestOneBit;
        } else if (max < 1073741824) {
            return 1073741824;
        } else {
            throw new IllegalArgumentException("collection too large");
        }
    }

    public static zzbc zzk(Object obj, Object obj2) {
        return zzf(2, obj, obj2);
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzbc) && zzg() && ((zzbc) obj).zzg() && hashCode() != obj.hashCode()) {
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
        return zzbx.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzcb iterator();

    /* access modifiers changed from: package-private */
    public boolean zzg() {
        return false;
    }

    public zzaz zzi() {
        zzaz zzaz = this.zza;
        if (zzaz != null) {
            return zzaz;
        }
        zzaz zzj = zzj();
        this.zza = zzj;
        return zzj;
    }

    /* access modifiers changed from: package-private */
    public zzaz zzj() {
        Object[] array = toArray();
        int i = zzaz.zzd;
        return zzaz.zzh(array, array.length);
    }

    private static zzbc zzf(int i, Object... objArr) {
        if (i == 0) {
            return zzbt.zza;
        }
        if (i != 1) {
            int zzh = zzh(i);
            Object[] objArr2 = new Object[zzh];
            int i2 = zzh - 1;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                Object obj = objArr[i5];
                zzbq.zza(obj, i5);
                int hashCode = obj.hashCode();
                int zza2 = zzau.zza(hashCode);
                while (true) {
                    int i6 = zza2 & i2;
                    Object obj2 = objArr2[i6];
                    if (obj2 != null) {
                        if (obj2.equals(obj)) {
                            break;
                        }
                        zza2++;
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
                Object obj3 = objArr[0];
                obj3.getClass();
                return new zzby(obj3);
            }
            if (zzh(i4) < zzh / 2) {
                return zzf(i4, objArr);
            }
            if (i4 <= 0) {
                objArr = Arrays.copyOf(objArr, i4);
            }
            return new zzbt(objArr, i3, objArr2, i2, i4);
        }
        Object obj4 = objArr[0];
        obj4.getClass();
        return new zzby(obj4);
    }
}
