package com.google.ads.interactivemedia.v3.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.util.Iterator;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzafs extends zzacw {
    static final int[] zza = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    private final int zzc;
    /* access modifiers changed from: private */
    public final zzacw zzd;
    /* access modifiers changed from: private */
    public final zzacw zze;
    private final int zzf;
    private final int zzg;

    private zzafs(zzacw zzacw, zzacw zzacw2) {
        this.zzd = zzacw;
        this.zze = zzacw2;
        int zzd2 = zzacw.zzd();
        this.zzf = zzd2;
        this.zzc = zzd2 + zzacw2.zzd();
        this.zzg = Math.max(zzacw.zzf(), zzacw2.zzf()) + 1;
    }

    static int zzc(int i) {
        int[] iArr = zza;
        int length = iArr.length;
        if (i >= 47) {
            return Integer.MAX_VALUE;
        }
        return iArr[i];
    }

    static zzacw zzv(zzacw zzacw, zzacw zzacw2) {
        if (zzacw2.zzd() == 0) {
            return zzacw;
        }
        if (zzacw.zzd() == 0) {
            return zzacw2;
        }
        int zzd2 = zzacw.zzd() + zzacw2.zzd();
        if (zzd2 < 128) {
            return zzw(zzacw, zzacw2);
        }
        if (zzacw instanceof zzafs) {
            zzafs zzafs = (zzafs) zzacw;
            if (zzafs.zze.zzd() + zzacw2.zzd() < 128) {
                return new zzafs(zzafs.zzd, zzw(zzafs.zze, zzacw2));
            }
            if (zzafs.zzd.zzf() > zzafs.zze.zzf() && zzafs.zzg > zzacw2.zzf()) {
                return new zzafs(zzafs.zzd, new zzafs(zzafs.zze, zzacw2));
            }
        }
        if (zzd2 >= zzc(Math.max(zzacw.zzf(), zzacw2.zzf()) + 1)) {
            return new zzafs(zzacw, zzacw2);
        }
        return zzafo.zza(new zzafo((zzafn) null), zzacw, zzacw2);
    }

    private static zzacw zzw(zzacw zzacw, zzacw zzacw2) {
        int zzd2 = zzacw.zzd();
        int zzd3 = zzacw2.zzd();
        byte[] bArr = new byte[(zzd2 + zzd3)];
        zzacw.zzs(bArr, 0, 0, zzd2);
        zzacw2.zzs(bArr, 0, zzd2, zzd3);
        return new zzacv(bArr);
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacw)) {
            return false;
        }
        zzacw zzacw = (zzacw) obj;
        if (this.zzc != zzacw.zzd()) {
            return false;
        }
        if (this.zzc == 0) {
            return true;
        }
        int zzn = zzn();
        int zzn2 = zzacw.zzn();
        if (zzn != 0 && zzn2 != 0 && zzn != zzn2) {
            return false;
        }
        zzafq zzafq = new zzafq(this, (zzafp) null);
        zzacu zza2 = zzafq.next();
        zzacu zzacu = zza2;
        zzafq zzafq2 = new zzafq(zzacw, (zzafp) null);
        zzacu zza3 = zzafq2.next();
        zzacu zzacu2 = zza3;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int zzd2 = zza2.zzd() - i;
            int zzd3 = zza3.zzd() - i2;
            int min = Math.min(zzd2, zzd3);
            if (i == 0) {
                z = zza2.zzg(zza3, i2, min);
            } else {
                z = zza3.zzg(zza2, i, min);
            }
            if (!z) {
                return false;
            }
            i3 += min;
            int i4 = this.zzc;
            if (i3 < i4) {
                if (min == zzd2) {
                    zzacu zza4 = zzafq.next();
                    zzacu zzacu3 = zza4;
                    zza2 = zza4;
                    i = 0;
                } else {
                    i += min;
                }
                if (min == zzd3) {
                    zza3 = zzafq2.next();
                    zzacu zzacu4 = zza3;
                    i2 = 0;
                } else {
                    i2 += min;
                }
            } else if (i3 == i4) {
                return true;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public final /* synthetic */ Iterator iterator() {
        return new zzafm(this);
    }

    public final byte zza(int i) {
        zzr(i, this.zzc);
        return zzb(i);
    }

    /* access modifiers changed from: package-private */
    public final byte zzb(int i) {
        int i2 = this.zzf;
        if (i < i2) {
            return this.zzd.zzb(i);
        }
        return this.zze.zzb(i - i2);
    }

    public final int zzd() {
        return this.zzc;
    }

    /* access modifiers changed from: protected */
    public final void zze(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.zzf;
        if (i4 <= i5) {
            this.zzd.zze(bArr, i, i2, i3);
        } else if (i >= i5) {
            this.zze.zze(bArr, i - i5, i2, i3);
        } else {
            int i6 = i5 - i;
            this.zzd.zze(bArr, i, i2, i6);
            this.zze.zze(bArr, 0, i2 + i6, i3 - i6);
        }
    }

    /* access modifiers changed from: protected */
    public final int zzf() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final boolean zzh() {
        return this.zzc >= zzc(this.zzg);
    }

    /* access modifiers changed from: protected */
    public final int zzi(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.zzf;
        if (i4 <= i5) {
            return this.zzd.zzi(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.zze.zzi(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.zze.zzi(this.zzd.zzi(i, i2, i6), 0, i3 - i6);
    }

    public final zzacw zzj(int i, int i2) {
        int zzm = zzm(i, i2, this.zzc);
        if (zzm == 0) {
            return zzacw.zzb;
        }
        if (zzm == this.zzc) {
            return this;
        }
        int i3 = this.zzf;
        if (i2 <= i3) {
            return this.zzd.zzj(i, i2);
        }
        if (i >= i3) {
            return this.zze.zzj(i - i3, i2 - i3);
        }
        zzacw zzacw = this.zzd;
        return new zzafs(zzacw.zzj(i, zzacw.zzd()), this.zze.zzj(0, i2 - this.zzf));
    }

    public final zzada zzk() {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public final void zzl(zzaco zzaco) throws IOException {
        this.zzd.zzl(zzaco);
        this.zze.zzl(zzaco);
    }

    public final zzacs zzo() {
        return new zzafm(this);
    }
}
