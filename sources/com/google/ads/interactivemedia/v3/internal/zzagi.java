package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzagi {
    private static final zzagi zza = new zzagi(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzagi() {
        this(0, new int[8], new Object[8], true);
    }

    private zzagi(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzagi zzc() {
        return zza;
    }

    static zzagi zze(zzagi zzagi, zzagi zzagi2) {
        int i = zzagi.zzb + zzagi2.zzb;
        int[] copyOf = Arrays.copyOf(zzagi.zzc, i);
        System.arraycopy(zzagi2.zzc, 0, copyOf, zzagi.zzb, zzagi2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzagi.zzd, i);
        System.arraycopy(zzagi2.zzd, 0, copyOf2, zzagi.zzb, zzagi2.zzb);
        return new zzagi(i, copyOf, copyOf2, true);
    }

    static zzagi zzf() {
        return new zzagi(0, new int[8], new Object[8], true);
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
        if (obj == null || !(obj instanceof zzagi)) {
            return false;
        }
        zzagi zzagi = (zzagi) obj;
        int i = this.zzb;
        if (i == zzagi.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzagi.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzagi.zzd;
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
        int i6 = ((i2 * 31) + i4) * 31;
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
                    i = zzadf.zzz(i8 << 3) + 8;
                } else if (i9 == 2) {
                    int zzz = zzadf.zzz(i8 << 3);
                    int zzd2 = ((zzacw) this.zzd[i6]).zzd();
                    i = zzz + zzadf.zzz(zzd2) + zzd2;
                } else if (i9 == 3) {
                    int zzz2 = zzadf.zzz(i8 << 3);
                    i3 = zzz2 + zzz2;
                    i2 = ((zzagi) this.zzd[i6]).zza();
                } else if (i9 == 5) {
                    ((Integer) this.zzd[i6]).intValue();
                    i = zzadf.zzz(i8 << 3) + 4;
                } else {
                    throw new IllegalStateException(new zzaef("Protocol message tag had invalid wire type."));
                }
                i5 += i;
            } else {
                int i10 = i8 << 3;
                long longValue = ((Long) this.zzd[i6]).longValue();
                i3 = zzadf.zzz(i10);
                i2 = zzadf.zzA(longValue);
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
            int zzz = zzadf.zzz(8);
            int zzz2 = zzadf.zzz(16) + zzadf.zzz(this.zzc[i3] >>> 3);
            int zzz3 = zzadf.zzz(24);
            int zzd2 = ((zzacw) this.zzd[i3]).zzd();
            i2 += zzz + zzz + zzz2 + zzz3 + zzadf.zzz(zzd2) + zzd2;
        }
        this.zze = i2;
        return i2;
    }

    /* access modifiers changed from: package-private */
    public final zzagi zzd(zzagi zzagi) {
        if (zzagi.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzagi.zzb;
        zzm(i);
        System.arraycopy(zzagi.zzc, 0, this.zzc, this.zzb, zzagi.zzb);
        System.arraycopy(zzagi.zzd, 0, this.zzd, this.zzb, zzagi.zzb);
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
            zzafd.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
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
    public final void zzk(zzagu zzagu) throws IOException {
        for (int i = 0; i < this.zzb; i++) {
            zzagu.zzw(this.zzc[i] >>> 3, this.zzd[i]);
        }
    }

    public final void zzl(zzagu zzagu) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                if (i3 == 0) {
                    zzagu.zzt(i4, ((Long) obj).longValue());
                } else if (i3 == 1) {
                    zzagu.zzm(i4, ((Long) obj).longValue());
                } else if (i3 == 2) {
                    zzagu.zzd(i4, (zzacw) obj);
                } else if (i3 == 3) {
                    zzagu.zzF(i4);
                    ((zzagi) obj).zzl(zzagu);
                    zzagu.zzh(i4);
                } else if (i3 == 5) {
                    zzagu.zzk(i4, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(new zzaef("Protocol message tag had invalid wire type."));
                }
            }
        }
    }
}
