package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzrf extends AbstractMap implements Serializable, zzqs {
    transient Object[] zza = new Object[2];
    transient Object[] zzb = new Object[2];
    transient int zzc = 0;
    transient int zzd;
    private transient int[] zze;
    private transient int[] zzf;
    private transient int[] zzg;
    private transient int[] zzh;
    /* access modifiers changed from: private */
    public transient int zzi;
    private transient int zzj;
    private transient int[] zzk;
    /* access modifiers changed from: private */
    public transient int[] zzl;
    private transient Set zzm;
    private transient Set zzn;
    private transient Set zzo;
    @CheckForNull
    private transient zzqs zzp;

    private zzrf(int i) {
        int zza2 = zzrg.zza(2, 1.0d);
        this.zze = zzy(zza2);
        this.zzf = zzy(zza2);
        this.zzg = zzy(2);
        this.zzh = zzy(2);
        this.zzi = -2;
        this.zzj = -2;
        this.zzk = zzy(2);
        this.zzl = zzy(2);
    }

    public static zzrf zzf(int i) {
        return new zzrf(2);
    }

    private final int zzo(int i) {
        return i & (this.zze.length - 1);
    }

    private final void zzp(int i, int i2) {
        zzqh.zzd(i != -1);
        int zzo2 = zzo(i2);
        int[] iArr = this.zze;
        int i3 = iArr[zzo2];
        if (i3 == i) {
            int[] iArr2 = this.zzg;
            iArr[zzo2] = iArr2[i];
            iArr2[i] = -1;
            return;
        }
        int i4 = this.zzg[i3];
        while (true) {
            int i5 = i3;
            i3 = i4;
            int i6 = i5;
            if (i3 == -1) {
                throw new AssertionError("Expected to find entry with key ".concat(String.valueOf(String.valueOf(this.zza[i]))));
            } else if (i3 == i) {
                int[] iArr3 = this.zzg;
                iArr3[i6] = iArr3[i];
                iArr3[i] = -1;
                return;
            } else {
                i4 = this.zzg[i3];
            }
        }
    }

    private final void zzq(int i, int i2) {
        zzqh.zzd(i != -1);
        int zzo2 = zzo(i2);
        int[] iArr = this.zzf;
        int i3 = iArr[zzo2];
        if (i3 == i) {
            int[] iArr2 = this.zzh;
            iArr[zzo2] = iArr2[i];
            iArr2[i] = -1;
            return;
        }
        int i4 = this.zzh[i3];
        while (true) {
            int i5 = i3;
            i3 = i4;
            int i6 = i5;
            if (i3 == -1) {
                throw new AssertionError("Expected to find entry with value ".concat(String.valueOf(String.valueOf(this.zzb[i]))));
            } else if (i3 == i) {
                int[] iArr3 = this.zzh;
                iArr3[i6] = iArr3[i];
                iArr3[i] = -1;
                return;
            } else {
                i4 = this.zzh[i3];
            }
        }
    }

    private final void zzr(int i) {
        int length = this.zzg.length;
        if (length < i) {
            int zza2 = zzrh.zza(length, i);
            this.zza = Arrays.copyOf(this.zza, zza2);
            this.zzb = Arrays.copyOf(this.zzb, zza2);
            this.zzg = zzz(this.zzg, zza2);
            this.zzh = zzz(this.zzh, zza2);
            this.zzk = zzz(this.zzk, zza2);
            this.zzl = zzz(this.zzl, zza2);
        }
        if (this.zze.length < i) {
            int zza3 = zzrg.zza(i, 1.0d);
            this.zze = zzy(zza3);
            this.zzf = zzy(zza3);
            for (int i2 = 0; i2 < this.zzc; i2++) {
                int zzo2 = zzo(zzrg.zzc(this.zza[i2]));
                int[] iArr = this.zzg;
                int[] iArr2 = this.zze;
                iArr[i2] = iArr2[zzo2];
                iArr2[zzo2] = i2;
                int zzo3 = zzo(zzrg.zzc(this.zzb[i2]));
                int[] iArr3 = this.zzh;
                int[] iArr4 = this.zzf;
                iArr3[i2] = iArr4[zzo3];
                iArr4[zzo3] = i2;
            }
        }
    }

    private final void zzs(int i, int i2) {
        zzqh.zzd(i != -1);
        int zzo2 = zzo(i2);
        int[] iArr = this.zzg;
        int[] iArr2 = this.zze;
        iArr[i] = iArr2[zzo2];
        iArr2[zzo2] = i;
    }

    private final void zzt(int i, int i2) {
        zzqh.zzd(i != -1);
        int zzo2 = zzo(i2);
        int[] iArr = this.zzh;
        int[] iArr2 = this.zzf;
        iArr[i] = iArr2[zzo2];
        iArr2[zzo2] = i;
    }

    private final void zzu(int i, int i2, int i3) {
        int i4;
        int i5;
        zzqh.zzd(i != -1);
        zzp(i, i2);
        zzq(i, i3);
        zzx(this.zzk[i], this.zzl[i]);
        int i6 = this.zzc - 1;
        if (i6 != i) {
            int i7 = this.zzk[i6];
            int i8 = this.zzl[i6];
            zzx(i7, i);
            zzx(i, i8);
            Object[] objArr = this.zza;
            Object obj = objArr[i6];
            Object[] objArr2 = this.zzb;
            Object obj2 = objArr2[i6];
            objArr[i] = obj;
            objArr2[i] = obj2;
            int zzo2 = zzo(zzrg.zzc(obj));
            int[] iArr = this.zze;
            int i9 = iArr[zzo2];
            if (i9 == i6) {
                iArr[zzo2] = i;
            } else {
                int i10 = this.zzg[i9];
                while (true) {
                    int i11 = i9;
                    i9 = i10;
                    i5 = i11;
                    if (i9 == i6) {
                        break;
                    }
                    i10 = this.zzg[i9];
                }
                this.zzg[i5] = i;
            }
            int[] iArr2 = this.zzg;
            iArr2[i] = iArr2[i6];
            iArr2[i6] = -1;
            int zzo3 = zzo(zzrg.zzc(obj2));
            int[] iArr3 = this.zzf;
            int i12 = iArr3[zzo3];
            if (i12 == i6) {
                iArr3[zzo3] = i;
            } else {
                int i13 = this.zzh[i12];
                while (true) {
                    int i14 = i12;
                    i12 = i13;
                    i4 = i14;
                    if (i12 == i6) {
                        break;
                    }
                    i13 = this.zzh[i12];
                }
                this.zzh[i4] = i;
            }
            int[] iArr4 = this.zzh;
            iArr4[i] = iArr4[i6];
            iArr4[i6] = -1;
        }
        Object[] objArr3 = this.zza;
        int i15 = this.zzc - 1;
        objArr3[i15] = null;
        this.zzb[i15] = null;
        this.zzc = i15;
        this.zzd++;
    }

    /* access modifiers changed from: private */
    public final void zzv(int i, Object obj, boolean z) {
        zzqh.zzd(i != -1);
        int zzc2 = zzc(obj, zzrg.zzc(obj));
        int i2 = this.zzj;
        if (zzc2 == -1) {
            if (i2 == i) {
                i2 = this.zzk[i];
            } else if (i2 == this.zzc) {
                i2 = zzc2;
            }
            if (i == -2) {
                zzc2 = this.zzl[-2];
            } else if (this.zzc != -2) {
                zzc2 = -2;
            }
            zzx(this.zzk[i], this.zzl[i]);
            zzp(i, zzrg.zzc(this.zza[i]));
            this.zza[i] = obj;
            zzs(i, zzrg.zzc(obj));
            zzx(i2, i);
            zzx(i, zzc2);
            return;
        }
        throw new IllegalArgumentException("Key already present in map: ".concat(String.valueOf(String.valueOf(obj))));
    }

    /* access modifiers changed from: private */
    public final void zzw(int i, Object obj, boolean z) {
        zzqh.zzd(i != -1);
        int zzc2 = zzrg.zzc(obj);
        if (zzd(obj, zzc2) == -1) {
            zzq(i, zzrg.zzc(this.zzb[i]));
            this.zzb[i] = obj;
            zzt(i, zzc2);
            return;
        }
        throw new IllegalArgumentException("Value already present in map: ".concat(String.valueOf(String.valueOf(obj))));
    }

    private static int[] zzy(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private static int[] zzz(int[] iArr, int i) {
        int length = iArr.length;
        int[] copyOf = Arrays.copyOf(iArr, i);
        Arrays.fill(copyOf, length, i, -1);
        return copyOf;
    }

    public final void clear() {
        Arrays.fill(this.zza, 0, this.zzc, (Object) null);
        Arrays.fill(this.zzb, 0, this.zzc, (Object) null);
        Arrays.fill(this.zze, -1);
        Arrays.fill(this.zzf, -1);
        Arrays.fill(this.zzg, 0, this.zzc, -1);
        Arrays.fill(this.zzh, 0, this.zzc, -1);
        Arrays.fill(this.zzk, 0, this.zzc, -1);
        Arrays.fill(this.zzl, 0, this.zzc, -1);
        this.zzc = 0;
        this.zzi = -2;
        this.zzj = -2;
        this.zzd++;
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        return zzc(obj, zzrg.zzc(obj)) != -1;
    }

    public final boolean containsValue(@CheckForNull Object obj) {
        return zzd(obj, zzrg.zzc(obj)) != -1;
    }

    public final Set entrySet() {
        Set set = this.zzo;
        if (set != null) {
            return set;
        }
        zzqy zzqy = new zzqy(this);
        this.zzo = zzqy;
        return zzqy;
    }

    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        int zzc2 = zzc(obj, zzrg.zzc(obj));
        if (zzc2 == -1) {
            return null;
        }
        return this.zzb[zzc2];
    }

    public final Set keySet() {
        Set set = this.zzm;
        if (set != null) {
            return set;
        }
        zzrb zzrb = new zzrb(this);
        this.zzm = zzrb;
        return zzrb;
    }

    @CheckForNull
    public final Object put(Object obj, Object obj2) {
        return zzg(obj, obj2, false);
    }

    @CheckForNull
    public final Object remove(@CheckForNull Object obj) {
        int zzc2 = zzrg.zzc(obj);
        int zzc3 = zzc(obj, zzc2);
        if (zzc3 == -1) {
            return null;
        }
        Object obj2 = this.zzb[zzc3];
        zzl(zzc3, zzc2);
        return obj2;
    }

    public final int size() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final int zzb(@CheckForNull Object obj, int i, int[] iArr, int[] iArr2, Object[] objArr) {
        int i2 = iArr[zzo(i)];
        while (i2 != -1) {
            if (zzqe.zza(objArr[i2], obj)) {
                return i2;
            }
            i2 = iArr2[i2];
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(@CheckForNull Object obj, int i) {
        return zzb(obj, i, this.zze, this.zzg, this.zza);
    }

    /* access modifiers changed from: package-private */
    public final int zzd(@CheckForNull Object obj, int i) {
        return zzb(obj, i, this.zzf, this.zzh, this.zzb);
    }

    public final zzqs zze() {
        zzqs zzqs = this.zzp;
        if (zzqs != null) {
            return zzqs;
        }
        zzqz zzqz = new zzqz(this);
        this.zzp = zzqz;
        return zzqz;
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public final Object zzg(Object obj, Object obj2, boolean z) {
        int zzc2 = zzrg.zzc(obj);
        int zzc3 = zzc(obj, zzc2);
        boolean z2 = false;
        if (zzc3 != -1) {
            Object obj3 = this.zzb[zzc3];
            if (zzqe.zza(obj3, obj2)) {
                return obj2;
            }
            zzw(zzc3, obj2, false);
            return obj3;
        }
        int zzc4 = zzrg.zzc(obj2);
        if (zzd(obj2, zzc4) == -1) {
            z2 = true;
        }
        zzqh.zzg(z2, "Value already present: %s", obj2);
        zzr(this.zzc + 1);
        Object[] objArr = this.zza;
        int i = this.zzc;
        objArr[i] = obj;
        this.zzb[i] = obj2;
        zzs(i, zzc2);
        zzt(this.zzc, zzc4);
        zzx(this.zzj, this.zzc);
        zzx(this.zzc, -2);
        this.zzc++;
        this.zzd++;
        return null;
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public final Object zzh(Object obj, Object obj2, boolean z) {
        int i;
        int zzc2 = zzrg.zzc(obj);
        int zzd2 = zzd(obj, zzc2);
        boolean z2 = false;
        if (zzd2 != -1) {
            Object obj3 = this.zza[zzd2];
            if (zzqe.zza(obj3, obj2)) {
                return obj2;
            }
            zzv(zzd2, obj2, false);
            return obj3;
        }
        int i2 = this.zzj;
        int zzc3 = zzrg.zzc(obj2);
        if (zzc(obj2, zzc3) == -1) {
            z2 = true;
        }
        zzqh.zzg(z2, "Key already present: %s", obj2);
        zzr(this.zzc + 1);
        Object[] objArr = this.zza;
        int i3 = this.zzc;
        objArr[i3] = obj2;
        this.zzb[i3] = obj;
        zzs(i3, zzc3);
        zzt(this.zzc, zzc2);
        if (i2 == -2) {
            i = this.zzi;
        } else {
            i = this.zzl[i2];
        }
        zzx(i2, this.zzc);
        zzx(this.zzc, i);
        this.zzc++;
        this.zzd++;
        return null;
    }

    /* renamed from: zzi */
    public final Set values() {
        Set set = this.zzn;
        if (set != null) {
            return set;
        }
        zzrc zzrc = new zzrc(this);
        this.zzn = zzrc;
        return zzrc;
    }

    /* access modifiers changed from: package-private */
    public final void zzl(int i, int i2) {
        zzu(i, i2, zzrg.zzc(this.zzb[i]));
    }

    /* access modifiers changed from: package-private */
    public final void zzm(int i, int i2) {
        zzu(i, zzrg.zzc(this.zza[i]), i2);
    }

    private final void zzx(int i, int i2) {
        if (i == -2) {
            this.zzi = i2;
        } else {
            this.zzl[i] = i2;
        }
        if (i2 == -2) {
            this.zzj = i;
        } else {
            this.zzk[i2] = i;
        }
    }
}
