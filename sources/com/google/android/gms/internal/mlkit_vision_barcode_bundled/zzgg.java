package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzgg extends zzdb {
    static final int[] zza = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    private final int zzc;
    /* access modifiers changed from: private */
    public final zzdb zzd;
    /* access modifiers changed from: private */
    public final zzdb zze;
    private final int zzf;
    private final int zzg;

    private zzgg(zzdb zzdb, zzdb zzdb2) {
        this.zzd = zzdb;
        this.zze = zzdb2;
        int zzd2 = zzdb.zzd();
        this.zzf = zzd2;
        this.zzc = zzd2 + zzdb2.zzd();
        this.zzg = Math.max(zzdb.zzf(), zzdb2.zzf()) + 1;
    }

    private static zzdb zzA(zzdb zzdb, zzdb zzdb2) {
        int zzd2 = zzdb.zzd();
        int zzd3 = zzdb2.zzd();
        byte[] bArr = new byte[(zzd2 + zzd3)];
        zzdb.zzw(bArr, 0, 0, zzd2);
        zzdb2.zzw(bArr, 0, zzd2, zzd3);
        return new zzcy(bArr);
    }

    static int zzc(int i) {
        int[] iArr = zza;
        int length = iArr.length;
        if (i >= 47) {
            return Integer.MAX_VALUE;
        }
        return iArr[i];
    }

    static zzdb zzz(zzdb zzdb, zzdb zzdb2) {
        if (zzdb2.zzd() == 0) {
            return zzdb;
        }
        if (zzdb.zzd() == 0) {
            return zzdb2;
        }
        int zzd2 = zzdb.zzd() + zzdb2.zzd();
        if (zzd2 < 128) {
            return zzA(zzdb, zzdb2);
        }
        if (zzdb instanceof zzgg) {
            zzgg zzgg = (zzgg) zzdb;
            if (zzgg.zze.zzd() + zzdb2.zzd() < 128) {
                return new zzgg(zzgg.zzd, zzA(zzgg.zze, zzdb2));
            } else if (zzgg.zzd.zzf() > zzgg.zze.zzf() && zzgg.zzg > zzdb2.zzf()) {
                return new zzgg(zzgg.zzd, new zzgg(zzgg.zze, zzdb2));
            }
        }
        if (zzd2 >= zzc(Math.max(zzdb.zzf(), zzdb2.zzf()) + 1)) {
            return new zzgg(zzdb, zzdb2);
        }
        return zzgc.zza(new zzgc((zzgb) null), zzdb, zzdb2);
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdb)) {
            return false;
        }
        zzdb zzdb = (zzdb) obj;
        if (this.zzc != zzdb.zzd()) {
            return false;
        }
        if (this.zzc == 0) {
            return true;
        }
        int zzp = zzp();
        int zzp2 = zzdb.zzp();
        if (zzp != 0 && zzp2 != 0 && zzp != zzp2) {
            return false;
        }
        zzge zzge = new zzge(this, (zzgd) null);
        zzcx zza2 = zzge.next();
        zzge zzge2 = new zzge(zzdb, (zzgd) null);
        zzcx zza3 = zzge2.next();
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
                    zza2 = zzge.next();
                    i = 0;
                } else {
                    i += min;
                }
                if (min == zzd3) {
                    zza3 = zzge2.next();
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
        return new zzga(this);
    }

    public final byte zza(int i) {
        zzv(i, this.zzc);
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

    /* access modifiers changed from: protected */
    public final int zzj(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.zzf;
        if (i4 <= i5) {
            return this.zzd.zzj(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.zze.zzj(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.zze.zzj(this.zzd.zzj(i, i2, i6), 0, i3 - i6);
    }

    public final zzdb zzk(int i, int i2) {
        int zzo = zzo(i, i2, this.zzc);
        if (zzo == 0) {
            return zzdb.zzb;
        }
        if (zzo == this.zzc) {
            return this;
        }
        int i3 = this.zzf;
        if (i2 <= i3) {
            return this.zzd.zzk(i, i2);
        }
        if (i >= i3) {
            return this.zze.zzk(i - i3, i2 - i3);
        }
        zzdb zzdb = this.zzd;
        return new zzgg(zzdb.zzk(i, zzdb.zzd()), this.zze.zzk(0, i2 - this.zzf));
    }

    /* access modifiers changed from: protected */
    public final String zzl(Charset charset) {
        return new String(zzx(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void zzm(zzcr zzcr) throws IOException {
        this.zzd.zzm(zzcr);
        this.zze.zzm(zzcr);
    }

    public final boolean zzn() {
        int zzj = this.zzd.zzj(0, 0, this.zzf);
        zzdb zzdb = this.zze;
        if (zzdb.zzj(zzj, 0, zzdb.zzd()) == 0) {
            return true;
        }
        return false;
    }

    public final zzcw zzq() {
        return new zzga(this);
    }
}
