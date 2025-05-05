package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzpo {
    private static final zzpo zza = new zzpo(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzpo() {
        this(0, new int[8], new Object[8], true);
    }

    private zzpo(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzpo zzc() {
        return zza;
    }

    static zzpo zze(zzpo zzpo, zzpo zzpo2) {
        int i = zzpo.zzb + zzpo2.zzb;
        int[] copyOf = Arrays.copyOf(zzpo.zzc, i);
        System.arraycopy(zzpo2.zzc, 0, copyOf, zzpo.zzb, zzpo2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzpo.zzd, i);
        System.arraycopy(zzpo2.zzd, 0, copyOf2, zzpo.zzb, zzpo2.zzb);
        return new zzpo(i, copyOf, copyOf2, true);
    }

    static zzpo zzf() {
        return new zzpo(0, new int[8], new Object[8], true);
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
        if (obj == null || !(obj instanceof zzpo)) {
            return false;
        }
        zzpo zzpo = (zzpo) obj;
        int i = this.zzb;
        if (i == zzpo.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzpo.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzpo.zzd;
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
                    i = zzlp.zzA(i8 << 3) + 8;
                } else if (i9 == 2) {
                    int zzA = zzlp.zzA(i8 << 3);
                    int zzd2 = ((zzlg) this.zzd[i6]).zzd();
                    i = zzA + zzlp.zzA(zzd2) + zzd2;
                } else if (i9 == 3) {
                    int zzA2 = zzlp.zzA(i8 << 3);
                    i3 = zzA2 + zzA2;
                    i2 = ((zzpo) this.zzd[i6]).zza();
                } else if (i9 == 5) {
                    ((Integer) this.zzd[i6]).intValue();
                    i = zzlp.zzA(i8 << 3) + 4;
                } else {
                    throw new IllegalStateException(new zzno("Protocol message tag had invalid wire type."));
                }
                i5 += i;
            } else {
                int i10 = i8 << 3;
                long longValue = ((Long) this.zzd[i6]).longValue();
                i3 = zzlp.zzA(i10);
                i2 = zzlp.zzB(longValue);
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
            int zzA = zzlp.zzA(8);
            int zzA2 = zzlp.zzA(16) + zzlp.zzA(this.zzc[i3] >>> 3);
            int zzA3 = zzlp.zzA(24);
            int zzd2 = ((zzlg) this.zzd[i3]).zzd();
            i2 += zzA + zzA + zzA2 + zzA3 + zzlp.zzA(zzd2) + zzd2;
        }
        this.zze = i2;
        return i2;
    }

    /* access modifiers changed from: package-private */
    public final zzpo zzd(zzpo zzpo) {
        if (zzpo.equals(zza)) {
            return this;
        }
        zzg();
        int i = this.zzb + zzpo.zzb;
        zzm(i);
        System.arraycopy(zzpo.zzc, 0, this.zzc, this.zzb, zzpo.zzb);
        System.arraycopy(zzpo.zzd, 0, this.zzd, this.zzb, zzpo.zzb);
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
            zzom.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
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
    public final void zzk(zzqa zzqa) throws IOException {
        for (int i = 0; i < this.zzb; i++) {
            zzqa.zzw(this.zzc[i] >>> 3, this.zzd[i]);
        }
    }

    public final void zzl(zzqa zzqa) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                if (i3 == 0) {
                    zzqa.zzt(i4, ((Long) obj).longValue());
                } else if (i3 == 1) {
                    zzqa.zzm(i4, ((Long) obj).longValue());
                } else if (i3 == 2) {
                    zzqa.zzd(i4, (zzlg) obj);
                } else if (i3 == 3) {
                    zzqa.zzF(i4);
                    ((zzpo) obj).zzl(zzqa);
                    zzqa.zzh(i4);
                } else if (i3 == 5) {
                    zzqa.zzk(i4, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(new zzno("Protocol message tag had invalid wire type."));
                }
            }
        }
    }
}
