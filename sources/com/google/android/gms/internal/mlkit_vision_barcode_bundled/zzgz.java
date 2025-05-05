package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzgz {
    private static final zzgz zza = new zzgz(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzgz() {
        this(0, new int[8], new Object[8], true);
    }

    private zzgz(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzgz zzc() {
        return zza;
    }

    static zzgz zze(zzgz zzgz, zzgz zzgz2) {
        int i = zzgz.zzb + zzgz2.zzb;
        int[] copyOf = Arrays.copyOf(zzgz.zzc, i);
        System.arraycopy(zzgz2.zzc, 0, copyOf, zzgz.zzb, zzgz2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzgz.zzd, i);
        System.arraycopy(zzgz2.zzd, 0, copyOf2, zzgz.zzb, zzgz2.zzb);
        return new zzgz(i, copyOf, copyOf2, true);
    }

    static zzgz zzf() {
        return new zzgz(0, new int[8], new Object[8], true);
    }

    private final void zzm(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzgz)) {
            return false;
        }
        zzgz zzgz = (zzgz) obj;
        int i = this.zzb;
        if (i == zzgz.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzgz.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzgz.zzd;
                    int i3 = this.zzb;
                    int i4 = 0;
                    while (i4 < i3) {
                        if (objArr[i4].equals(objArr2[i4])) {
                            i4++;
                        }
                    }
                    return true;
                } else if (iArr[i2] != iArr2[i2]) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = i + 527;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 * 31) + i4;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return (i6 * 31) + i3;
    }

    public final int zza() {
        int i;
        int i2;
        int i3;
        int i4 = this.zze;
        if (i4 != -1) {
            return i4;
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.zzb; i6++) {
            int i7 = this.zzc[i6];
            int i8 = i7 >>> 3;
            int i9 = i7 & 7;
            if (i9 != 0) {
                if (i9 == 1) {
                    ((Long) this.zzd[i6]).longValue();
                    i = zzdj.zzy(i8 << 3) + 8;
                } else if (i9 == 2) {
                    int i10 = zzdj.zzb;
                    int zzd2 = ((zzdb) this.zzd[i6]).zzd();
                    i = zzdj.zzy(i8 << 3) + zzdj.zzy(zzd2) + zzd2;
                } else if (i9 == 3) {
                    int i11 = i8 << 3;
                    int i12 = zzdj.zzb;
                    i2 = ((zzgz) this.zzd[i6]).zza();
                    int zzy = zzdj.zzy(i11);
                    i3 = zzy + zzy;
                } else if (i9 == 5) {
                    ((Integer) this.zzd[i6]).intValue();
                    i = zzdj.zzy(i8 << 3) + 4;
                } else {
                    throw new IllegalStateException(zzeo.zza());
                }
                i5 += i;
            } else {
                int i13 = i8 << 3;
                i2 = zzdj.zzz(((Long) this.zzd[i6]).longValue());
                i3 = zzdj.zzy(i13);
            }
            i = i3 + i2;
            i5 += i;
        }
        this.zze = i5;
        return i5;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = zzdj.zzb;
            int zzd2 = ((zzdb) this.zzd[i3]).zzd();
            int zzy = zzdj.zzy(zzd2) + zzd2;
            int zzy2 = zzdj.zzy(16);
            int zzy3 = zzdj.zzy(this.zzc[i3] >>> 3);
            int zzy4 = zzdj.zzy(8);
            i2 += zzy4 + zzy4 + zzy2 + zzy3 + zzdj.zzy(24) + zzy;
        }
        this.zze = i2;
        return i2;
    }

    /* access modifiers changed from: package-private */
    public final zzgz zzd(zzgz zzgz) {
        if (zzgz.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzgz.zzb;
        zzm(i);
        System.arraycopy(zzgz.zzc, 0, this.zzc, this.zzb, zzgz.zzb);
        System.arraycopy(zzgz.zzd, 0, this.zzd, this.zzb, zzgz.zzb);
        this.zzb = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void zzg() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final void zzh() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzi(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzfq.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzj(int i, Object obj) {
        zzg();
        zzm(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    /* access modifiers changed from: package-private */
    public final void zzk(zzhq zzhq) throws IOException {
        for (int i = 0; i < this.zzb; i++) {
            zzhq.zzw(this.zzc[i] >>> 3, this.zzd[i]);
        }
    }

    public final void zzl(zzhq zzhq) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                if (i3 == 0) {
                    zzhq.zzt(i4, ((Long) obj).longValue());
                } else if (i3 == 1) {
                    zzhq.zzm(i4, ((Long) obj).longValue());
                } else if (i3 == 2) {
                    zzhq.zzd(i4, (zzdb) obj);
                } else if (i3 == 3) {
                    zzhq.zzF(i4);
                    ((zzgz) obj).zzl(zzhq);
                    zzhq.zzh(i4);
                } else if (i3 == 5) {
                    zzhq.zzk(i4, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzeo.zza());
                }
            }
        }
    }
}
