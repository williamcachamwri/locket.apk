package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
public final class zzmj {
    private static final zzmj zza = new zzmj(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    public final int zza() {
        int i;
        int i2 = this.zze;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            int i5 = this.zzc[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 == 0) {
                i = zzjc.zzg(i6, ((Long) this.zzd[i4]).longValue());
            } else if (i7 == 1) {
                i = zzjc.zzc(i6, ((Long) this.zzd[i4]).longValue());
            } else if (i7 == 2) {
                i = zzjc.zzc(i6, (zzik) this.zzd[i4]);
            } else if (i7 == 3) {
                i = (zzjc.zzi(i6) << 1) + ((zzmj) this.zzd[i4]).zza();
            } else if (i7 == 5) {
                i = zzjc.zzf(i6, ((Integer) this.zzd[i4]).intValue());
            } else {
                throw new IllegalStateException(zzkb.zza());
            }
            i3 += i;
        }
        this.zze = i3;
        return i3;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            i2 += zzjc.zzd(this.zzc[i3] >>> 3, (zzik) this.zzd[i3]);
        }
        this.zze = i2;
        return i2;
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

    public static zzmj zzc() {
        return zza;
    }

    /* access modifiers changed from: package-private */
    public final zzmj zza(zzmj zzmj) {
        if (zzmj.equals(zza)) {
            return this;
        }
        zzf();
        int i = this.zzb + zzmj.zzb;
        zza(i);
        System.arraycopy(zzmj.zzc, 0, this.zzc, this.zzb, zzmj.zzb);
        System.arraycopy(zzmj.zzd, 0, this.zzd, this.zzb, zzmj.zzb);
        this.zzb = i;
        return this;
    }

    static zzmj zza(zzmj zzmj, zzmj zzmj2) {
        int i = zzmj.zzb + zzmj2.zzb;
        int[] copyOf = Arrays.copyOf(zzmj.zzc, i);
        System.arraycopy(zzmj2.zzc, 0, copyOf, zzmj.zzb, zzmj2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzmj.zzd, i);
        System.arraycopy(zzmj2.zzd, 0, copyOf2, zzmj.zzb, zzmj2.zzb);
        return new zzmj(i, copyOf, copyOf2, true);
    }

    static zzmj zzd() {
        return new zzmj();
    }

    private zzmj() {
        this(0, new int[8], new Object[8], true);
    }

    private zzmj(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    private final void zzf() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    private final void zza(int i) {
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

    public final void zze() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzld.zza(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, Object obj) {
        zzf();
        zza(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zznb zznb) throws IOException {
        if (zznb.zza() == 2) {
            for (int i = this.zzb - 1; i >= 0; i--) {
                zznb.zza(this.zzc[i] >>> 3, this.zzd[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zznb.zza(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    private static void zza(int i, Object obj, zznb zznb) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zznb.zzb(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            zznb.zza(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            zznb.zza(i2, (zzik) obj);
        } else if (i3 != 3) {
            if (i3 == 5) {
                zznb.zzb(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzkb.zza());
        } else if (zznb.zza() == 1) {
            zznb.zzb(i2);
            ((zzmj) obj).zzb(zznb);
            zznb.zza(i2);
        } else {
            zznb.zza(i2);
            ((zzmj) obj).zzb(zznb);
            zznb.zzb(i2);
        }
    }

    public final void zzb(zznb zznb) throws IOException {
        if (this.zzb != 0) {
            if (zznb.zza() == 1) {
                for (int i = 0; i < this.zzb; i++) {
                    zza(this.zzc[i], this.zzd[i], zznb);
                }
                return;
            }
            for (int i2 = this.zzb - 1; i2 >= 0; i2--) {
                zza(this.zzc[i2], this.zzd[i2], zznb);
            }
        }
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzmj)) {
            return false;
        }
        zzmj zzmj = (zzmj) obj;
        int i = this.zzb;
        if (i == zzmj.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzmj.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                } else if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Object[] objArr = this.zzd;
                Object[] objArr2 = zzmj.zzd;
                int i3 = this.zzb;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                return z2;
            }
        }
    }
}
