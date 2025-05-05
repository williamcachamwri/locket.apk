package com.google.android.gms.internal.pal;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzafj {
    private static final zzafj zza = new zzafj(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzafj() {
        this(0, new int[8], new Object[8], true);
    }

    private zzafj(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzafj zzc() {
        return zza;
    }

    static zzafj zzd(zzafj zzafj, zzafj zzafj2) {
        int i = zzafj.zzb + zzafj2.zzb;
        int[] copyOf = Arrays.copyOf(zzafj.zzc, i);
        System.arraycopy(zzafj2.zzc, 0, copyOf, zzafj.zzb, zzafj2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzafj.zzd, i);
        System.arraycopy(zzafj2.zzd, 0, copyOf2, zzafj.zzb, zzafj2.zzb);
        return new zzafj(i, copyOf, copyOf2, true);
    }

    static zzafj zze() {
        return new zzafj(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzafj)) {
            return false;
        }
        zzafj zzafj = (zzafj) obj;
        int i = this.zzb;
        if (i == zzafj.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzafj.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzafj.zzd;
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
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
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
                    i = zzach.zzA(i8 << 3) + 8;
                } else if (i9 == 2) {
                    int zzA = zzach.zzA(i8 << 3);
                    int zzd2 = ((zzaby) this.zzd[i6]).zzd();
                    i5 += zzA + zzach.zzA(zzd2) + zzd2;
                } else if (i9 == 3) {
                    int zzz = zzach.zzz(i8);
                    i3 = zzz + zzz;
                    i2 = ((zzafj) this.zzd[i6]).zza();
                } else if (i9 == 5) {
                    ((Integer) this.zzd[i6]).intValue();
                    i = zzach.zzA(i8 << 3) + 4;
                } else {
                    throw new IllegalStateException(zzadi.zza());
                }
                i5 += i;
            } else {
                long longValue = ((Long) this.zzd[i6]).longValue();
                i3 = zzach.zzA(i8 << 3);
                i2 = zzach.zzB(longValue);
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
            int i4 = this.zzc[i3];
            int zzA = zzach.zzA(8);
            int zzd2 = ((zzaby) this.zzd[i3]).zzd();
            i2 += zzA + zzA + zzach.zzA(16) + zzach.zzA(i4 >>> 3) + zzach.zzA(24) + zzach.zzA(zzd2) + zzd2;
        }
        this.zze = i2;
        return i2;
    }

    public final void zzf() {
        this.zzf = false;
    }

    /* access modifiers changed from: package-private */
    public final void zzg(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzaeh.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzh(int i, Object obj) {
        if (this.zzf) {
            int i2 = this.zzb;
            int[] iArr = this.zzc;
            if (i2 == iArr.length) {
                int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
                this.zzc = Arrays.copyOf(iArr, i3);
                this.zzd = Arrays.copyOf(this.zzd, i3);
            }
            int[] iArr2 = this.zzc;
            int i4 = this.zzb;
            iArr2[i4] = i;
            this.zzd[i4] = obj;
            this.zzb = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final void zzi(zzaga zzaga) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 >>> 3;
                int i4 = i2 & 7;
                if (i4 == 0) {
                    zzaga.zzt(i3, ((Long) obj).longValue());
                } else if (i4 == 1) {
                    zzaga.zzm(i3, ((Long) obj).longValue());
                } else if (i4 == 2) {
                    zzaga.zzd(i3, (zzaby) obj);
                } else if (i4 == 3) {
                    zzaga.zzE(i3);
                    ((zzafj) obj).zzi(zzaga);
                    zzaga.zzh(i3);
                } else if (i4 == 5) {
                    zzaga.zzk(i3, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzadi.zza());
                }
            }
        }
    }
}
